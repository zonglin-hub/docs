# Windows 安装 Helix

参考文档：

- [给Helix编辑器配置Rust、Python的LSP服务，实现代码提示补全、格式化、高亮等功能](https://www.bilibili.com/read/cv25583084/)

- [Winget 安装 Helix](https://docs.helix-editor.com/install.html#winget)

- [How to install the default language servers](https://github.com/helix-editor/helix/wiki/How-to-install-the-default-language-servers#rust)

- [Runtime directory does not exist](https://github.com/helix-editor/helix/discussions/7859)

```powershell
winget install Helix.Helix
```

使用 nightly 版本 并更新 rust-analyzer

Error

```text
2024-03-20T00:40:07.772 helix_lsp::transport [ERROR] rust-analyzer err <- "error: 'rust-analyzer.exe' is not installed for the toolchain 'nightly-x86_64-pc-windows-msvc'\n"
2024-03-20T00:40:07.803 helix_lsp::transport [ERROR] rust-analyzer err: <- StreamClosed
2024-03-20T00:40:07.803 helix_lsp [ERROR] failed to initialize language server: server closed the stream
```

```text
2024-03-20T00:47:21.814 helix_lsp::transport [ERROR] rust-analyzer err <- "error: Unknown binary 'rust-analyzer.exe' in official toolchain 'nightly-x86_64-pc-windows-msvc'.\n"
2024-03-20T00:47:21.847 helix_lsp::transport [ERROR] rust-analyzer err: <- StreamClosed
2024-03-20T00:47:21.848 helix_lsp [ERROR] failed to initialize language server: server closed the stream
```
