
**Windows 安装 Rust 安装太慢解决办法**

[清华大学开源软件镜像站 Rust crates.io 索引][tsinghua_university]

[tsinghua_university]: https://mirrors.tuna.tsinghua.edu.cn/help/crates.io-index.git/


打开 `powershell` 分别执行下面两行代码：

作用：国内加速通道，注：这里时临时变量

> [!NOTE] powershell
> ```powershell
> $ENV:RUSTUP_DIST_SERVER='https://mirrors.ustc.edu.cn/rust-static'
> $ENV:RUSTUP_UPDATE_ROOT='https://mirrors.ustc.edu.cn/rust-static/rustup'
> ```

> [!NOTE] bash
> ```bash
> export RUSTUP_DIST_SERVER=https://mirrors.ustc.edu.cn/rust-static
> export RUSTUP_UPDATE_ROOT=https://mirrors.ustc.edu.cn/rust-static/rustup
> ```


**升级 Rust 「stable _稳定版_」**

可以执行 <code>rustup update stable</code> 来升级 Rust 「Stable Release _稳定版本_」


**安装 Rust 「Nightly Build _夜间构建_」**

可以执行 <code>rustup install nightly</code> 来安装 「Nightly Build <i>夜间构建</i>」


**切换默认版本**

选择稳定版或者 `nightly` 版，如果想长期使用 `nightly` 版。可以使用以下命令：`rustup default nightly`


**卸载 Rust**

在任何时候如果您想卸载 Rust，您可以运行 `rustup self uninstall`。

卸载 Rust nightly 可以使用 `rustup toolchain uninstall nightly-x86_64-pc-windows-msvc`。

查询 rustup 安装的工具链 请使用 `rustup show` 或 `rustup toolchain list`
