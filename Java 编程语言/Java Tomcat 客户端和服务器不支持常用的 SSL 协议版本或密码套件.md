参考文档：

- [MDN Web 文档术语表：Web 相关术语的定义](https://developer.mozilla.org/zh-CN/docs/Glossary/SSL)
- [Tomcat配置SSL证书实现 https 访问](https://zhuanlan.zhihu.com/p/108774894)

---

SSL（安全套接层）：SSL（Secure Sockets Layer，安全套接层）是旧的标准安全技术，用于在服务器和客户端之间创建加密的网络链路，确保传递的所有数据都是私密且安全的。SSL 的当前版本是 Netscape 于 1996 年发布的 3.0 版本，已被 TLS 协议取代。

TLS：传输层安全性协议 (Transport Layer Security，缩写作 TLS)，它的前身是安全套接层 (Secure Sockets Layer，缩写作 SSL)，是一个被应用程序用来在网络中安全通信的 protocol （通讯协议），防止电子邮件、网页、消息以及其他协议被篡改或是窃听。

#### 反馈问题

现在最新版的谷歌，Edge浏览器不支持1.2以下
![image](https://img2022.cnblogs.com/blog/2402369/202202/2402369-20220215190900202-2014380958.png)

#### 项目部署 tomcat 修改问题修改方法

```xml
    <!-- Define a SSL HTTP/1.1 Connector on port 8443
         This connector uses the BIO implementation that requires the JSSE
         style configuration. When using the APR/native implementation, the
         OpenSSL style configuration is required as described in the APR/native
         documentation -->

 <Connector port="4443" protocol="org.apache.coyote.http11.Http11NioProtocol"
               maxThreads="150"
               scheme="https" secure="true" SSLEnabled="true"
               clientAuth="false" sslProtocol="TLS"
               keystoreFile="${catalina.home}/conf/server.keystore"
               keystorePass="changeit"
      URIEncoding="UTF-8"
      sslEnabledProtocols="TLSv1.3,TLSv1.2" # 支持协议TLS版本 sslEnabledProtocols="TLSv1.3,TLSv1.2"
         />

    <!-- Define an AJP 1.3 Connector on port 8009 -->
 <!--
    <Connector port="8009" protocol="AJP/1.3" redirectPort="8443" />-->
```
