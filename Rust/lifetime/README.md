# async-book

Rust 中的异步编程

## 环境要求

异步编程书籍使用 [`mdbook`] 构建，你可以通过 cargo 安装它。

```sh
cargo install mdbook
```

[`mdbook`]: https://github.com/rust-lang/mdBook

## 构建

要生成完整的书籍，请运行 `mdbook build`，它会在 `book/` 目录下生成书籍。

```sh
mdbook build
```

## 开发

在编写过程中，实时查看更改会很方便，`mdbook serve` 会启动一个本地 Web 服务器来提供书籍内容。

```sh
mdbook serve
```

---

[mdBook 官方文档][mdBook_doc]

[mdBook_doc]: https://rust-lang.github.io/mdBook/

[中文翻译:<mdBook> 一个从 Markdown 文件创建现代在线书籍的实用程序 ❤️ 更新 ✅ 2022-1-8][mdBook_zh_CN]
- [mdBook_zh_CN_Github](https://github.com/chinanf-boy/mdBook-zh)

[mdBook_zh_CN]: https://llever.com/mdBook-zh/


## 参考文档

- [Github - lifetime kata][Github - lifetime kata]
- [Rust 的生命周期]
- [协变 逆变]
- [Lifetimes in Rust]
- [RFC 1951]
- [RFC 2394]
- [rust reference]
- [rust nomicon]
- [Rust - 生命周期]

[Github - lifetime kata]: https://github.com/tfpk/lifetimekata
[Rust 的生命周期]: https://hashrust.com/blog/lifetimes-in-rust/
[协变 逆变]: https://www.cnblogs.com/allmignt/p/12353746.html
[Lifetimes in Rust]: https://blog.thoughtram.io/lifetimes-in-rust/
[RFC 1951]: https://rust-lang.github.io/rfcs/1951-expand-impl-trait.html
[RFC 2394]: https://rust-lang.github.io/rfcs/2394-async_await.html
[rust reference]: https://rustwiki.org/zh-CN/reference/subtyping.html
[rust nomicon]: https://nomicon.purewhite.io/subtyping.html
[Rust - 生命周期]: https://rustcc.cn/article?id=0d606476-0a98-4f5a-afba-951f999408e6