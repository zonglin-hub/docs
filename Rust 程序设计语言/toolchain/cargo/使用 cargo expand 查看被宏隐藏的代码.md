# 使用 cargo expand 查看被宏隐藏的代码

使用 VScode 安装扩展 `Rust Macro Expand`

需要安装以下软件:

- [cargo-expand](https://github.com/dtolnay/cargo-expand) A cargo crate for easier handling of compiler commands。
- Rust nightly compiler, you can install it with `rustup toolchain install nightly`。

## cargo expand 简介

- cargo expand 目前这个需要安装 nightly 的工具链。

    你可以使用 `rustup toolchain install nightly` 命令进行安装。

- 再安装 cargo expand

    你可以使用以下命令 `cargo +nightly install cargo-expand` 命令进行安装。

## 使用方法

源代码为：

```rust
#![allow(non_camel_case_types)]
use async_trait::async_trait;
fn main() {}
#[async_trait]
trait example {
    async fn fetch(trace_id: &str, span_id: &str);
}
```

运行 `cargo expand` 你可以得到以下结果

```bash
~> cargo expand
    Checking lifetime_kata v0.1.0 (D:\.github\rust\lifetime_kata)
    Finished dev [unoptimized + debuginfo] target(s) in 0.04s

#![feature(prelude_import)]
#![allow(non_camel_case_types)]
#[prelude_import]
use std::prelude::rust_2021::*;
#[macro_use]
extern crate std;
use async_trait::async_trait;
fn main() {}
trait example {
    #[must_use]
    #[allow(clippy::type_complexity, clippy::type_repetition_in_bounds)]
    fn fetch<'life0, 'life1, 'async_trait>(
        trace_id: &'life0 str,
        span_id: &'life1 str,
    ) -> ::core::pin::Pin<
        Box<
            dyn ::core::future::Future<Output = ()> + ::core::marker::Send + 'async_trait,
        >,
    >
    where
        'life0: 'async_trait,
        'life1: 'async_trait;
}
```
