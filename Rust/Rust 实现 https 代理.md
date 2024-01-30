# Rust 实现 https 代理

```tomls
[dependencies]
anyhow = "1.0.75"
colored = "2.0.4"
hyper = { version = "0.14.27", features = ["full"] }
tokio = { version = "1.32.0", features = ["full"] }
futures-util = "0.3.28"
hyper-rustls = { version = "0.24.1", features = [
    "rustls-native-certs",
    "http1",
    "http2",
] }
```

示例

```rust
use anyhow::{Context, Ok, Result};
use colored::Colorize;
use hyper::{
    service::{make_service_fn, service_fn},
    Body, Client, Request, Server,
};
use std::{
    net::{IpAddr, Ipv4Addr, SocketAddr},
    sync::Arc,
};

// 创建一个代理函数，用于处理请求并转发到目标服务器
pub fn create_proxy(req: &mut Request<Body>) -> Result<()> {
    // 定义一个需要移除的头部字段列表
    let par = vec![
        "content-length",
        "accept_encoding",
        "content-encoding",
        "transfer-encoding",
    ];

    // 遍历列表，移除请求中的这些头部字段
    for key in par {
        req.headers_mut().remove(key);
    }

    // 获取原始请求的URI和目标服务器的地址
    let uri = req.uri();
    let url = "https://lib.rs";

    // 根据原始请求的URI是否有查询参数，构造新的目标URI
    let uri_string = match uri.query() {
        Some(query_item) => {
            format!("{}{}?{}", url, uri.path(), query_item)
        }
        None => format!("{}{}", url, uri.path()),
    };

    // 将原始请求的URI替换为新的目标URI，并进行解析
    *req.uri_mut() = uri_string.parse()?;
    Ok(())
}

// 异步函数，用于初始化代理服务器并监听指定地址
pub async fn init() -> Result<()> {
    // 创建一个HTTPS连接器，配置HTTPS连接的相关参数
    let https = hyper_rustls::HttpsConnectorBuilder::new()
        .with_native_roots()
        .https_or_http()
        .enable_all_versions()
        .build();

    // 使用HTTPS连接器创建一个新的HTTP客户端
    let client = Client::builder().build(https);
    let arc_client = Arc::new(client);

    // 创建一个服务工厂函数，用于创建处理请求的服务实例
    let make_svc = make_service_fn(|_| {
        let client = Arc::clone(&arc_client);
        async move {
            Ok::<_>(service_fn(move |mut req| {
                let client = Arc::clone(&client);
                async move {
                    // 打印请求的路径信息，用于调试目的
                    println!("Proxy request details: {}", req.uri().path().cyan());
                    // 调用代理函数处理请求，并进行转发
                    let _ = create_proxy(&mut req);
                    client.request(req).await
                }
            }))
        }
    });

    // 指定代理服务器监听的地址和端口号
    let addr = SocketAddr::new(IpAddr::V4(Ipv4Addr::new(127, 0, 0, 1)), 8080);
    println!(
        "🚀 Server is listening on {}",
        format!("http://{}", addr).blue().to_string()
    );

    // 创建并启动代理服务器，监听指定的地址和端口号，并处理请求
    let _server = Server::bind(&addr)
        .serve(make_svc)
        .await
        .context("run server");

    Ok::<()>(())
}
```
