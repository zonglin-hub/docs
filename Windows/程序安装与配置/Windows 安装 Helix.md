# Windows 安装 Helix

我看了 Helix 官方文档后不知道怎么才能安装指定语言的 LSP 服务器，我只会拉取所有语言 LSP 服务器的到本地，然后配置/激活指定语言的 LSP 服务器。

我的操作系统是 Windows11。

使用 `winget` 安装 Helix

```powershell
winget install Helix.Helix
```

Helix 自带 `rust-analyzer`，无需手动安装。但如果本地安装了 Rust 环境，以你安装的 `rust-analyzer` 为准

```powershell
PS C:\Users\zonglin> rustup component add rust-analyzer
info: downloading component 'rust-analyzer'
info: installing component 'rust-analyzer'
```

lldb-vscode 安装参考：[Github - llvm]

[Github - llvm]: https://github.com/llvm/llvm-project

```powershell
PS C:\Users\zonglin> lldb-vscode --help
OVERVIEW: LLDB VSCode

USAGE: lldb-vscode.exe options

OPTIONS: ...
```

检查 Rust 环境

```powershell
PS C:\Users\zonglin> hx --health rust
Configured language servers:
  ✓ rust-analyzer: C:\Users\zonglin\.cargo\bin\rust-analyzer.exe
Configured debug adapter: lldb-vscode
Binary for debug adapter: D:\Program Files\LLVM\bin\lldb-vscode.exe
Highlight queries: ✓
Textobject queries: ✓
Indent queries: ✓
```

Runtime directory does not exist: /home/untitled1/.config/helix/runtime [^1]

[^1]: [Runtime directory does not exist](https://github.com/helix-editor/helix/discussions/7859)

配置环境变量 `HELIX_RUNTIME=/home/untitled1/src/helix/runtime`

```powershell
PS C:\Users\zonglin\.config\helix> hx --health
Config file: default
Language file: default
Log file: C:\Users\zonglin\AppData\Local\helix\helix.log
Runtime directories: C:\Users\zonglin\AppData\Roaming\helix\runtime;C:\Users\zonglin\source\repos\helix\runtime;\\?\C:\Users\zonglin\AppData\Local\Microsoft\WinGet\Packages\Helix.Helix_Microsoft.Winget.Source_8wekyb3d8bbwe\helix-23.10-x86_64-windows\runtime
Runtime directory does not exist: C:\Users\zonglin\source\repos\helix\runtime
Clipboard provider: clipboard-win
System clipboard provider: clipboard-win
```

编写 `languages.toml` 配置文件

编写 `languages.toml` 保存在 `~/.config/helix/` 路径下。

参考官网的配置就好，内容如下

```toml
[[language]]
name = "rust"
auto-format = false

# If this doesn't work, you might be on a older release version which handles this differently.
# Try using [language.config.check] instead of [language-server.rust-analyzer.config.check]
[language-server.rust-analyzer.config.check]
command = "clippy"

[[grammar]]
name = "rust"
source = { git = "https://github.com/tree-sitter/tree-sitter-rust", rev = "3a56481f8d13b6874a28752502a58520b9139dc7" }
```

这些配置内容可以仿照 [Github - Wiki] 或参考 [Helix 官方文档] - [中文版]

[Github - Wiki]: https://github.com/helix-editor/helix/wiki/How-to-install-the-default-language-servers#rust
[Helix 官方文档]: https://docs.helix-editor.com/languages.html
[中文版]: https://zjp-cn.github.io/helix-book/languages.html

拉取与构建

```powershell
hx --grammar fetch # 拉取没有进度条，耗时几分钟。
```

这一步骤会拉取所有语言的 LSP 服务到本地，Windows 保存的路径为 `C:\Users\zonglin\AppData\Roaming\helix\runtime`。

```powershell
hx --grammar build # 也会卡住，过程中 CPU 占用极高
```

Error

> Note: 使用 nightly 版本 并更新 rust-analyzer

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

### 常用指令

撤销修改：<kbd>u</kbd>
恢复修改：<kbd>Shift + u</kbd>
选择当前行；如果已选择，延伸到下一行：<kbd>x</kbd>
删除：<kbd>d</kbd>
粘贴：<kbd>p</kbd>
函数跳转：<kbd>gd</kbd>
外部文本粘贴：<kbd>\<space> + p</kbd>
任意选择内容：<kbd></kbd>
查看函数文档：<kbd>\<space> + k</kbd>
到 12 行：<kbd>g 12 g</kbd>
