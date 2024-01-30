# cargo 常用命令

| 指令    |               解释                                          |
| ------ | ---------------------------------------------------------- |
| `cargo --version`        | cargo 版本                                |
| `cargo new greeting`     | cargo 创建项目                             |
| `cargo build`            | 构建                                      |
| `cargo run`              | 运行                                      |
| `cargo check`            | 检查代码，确保能通过编译，但是不产生任何可执行文件 |
| `cargo build --release`  | 为发布构建                                 |

Cargo.toml

```toml
[package]
name = "rust_dome" # 项目名
version = "0.1.0"  # 项目版本
edition = "2021"   # cargo 版本

# See more keys and their definitions at https://doc.rust-lang.org/cargo/reference/manifest.html

# 四种依赖类型
[dependencies]        # 项目依赖包
[dev-dependencies]    # 用于测试（包括性能测试）的依赖包
[build-dependencies]  # 用于构建脚步的依赖包
[target]              # 平台特定依赖包
```

cargo包的四种配置

```toml
[dev]     # 缺失配置
[release] # 优化运行时性能，适用于生产发布
[test]    # 用于cargo test 命令， 构建用于测试的可执行文件
[bench]   # 用于cargo bench 命令，构建用于性能测试的可执行文件（运行带#[bench]注解的函数）
```
