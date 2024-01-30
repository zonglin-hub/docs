# Windows 设置 Rust 环境

- 官网下载 [Microsoft C++ 生成工具][Microsoft C++ 生成工具]，并安装 <strong>Desktop development with C++</strong> 如果不安装无法正常编译。

- 这里我们需要一个完整的 `gcc` 运行时环境。
  请在 MinGW64 官网[进行下载 `x86_64-win32-seh`][MinGW64]。
  如不安装，编辑代码时，会影响部分函数提示。

    - 并于系统变量 `Path` 中编辑环境变量，指定 `mingw64\bin` 目录。

      ```text
      D:\program\mingw64\bin
      ```

    - 使用 <kbd>gcc -v</kbd> 测试 gcc 环境是否正常。

- 官网下载 <kbd>rustup-init.exe</kbd> 并安装，[点击这里下载！][download_rustup]

## Windows 安装 Rust 安装太慢解决办法

打开 `powershell` 分别执行下面两行代码：

作用：国内加速通道，注：这里时临时变量

```powershell
$ENV:RUSTUP_DIST_SERVER='https://mirrors.ustc.edu.cn/rust-static'
$ENV:RUSTUP_UPDATE_ROOT='https://mirrors.ustc.edu.cn/rust-static/rustup'
```

## 配置 cargo 国内源

字节跳动提供的公益镜像 [RsProxy](https://rsproxy.cn/#getStarted) [^1]

找到当前用户目录下 `.cargo` 文件夹，建立 `config` 文件：

```bash
touch ~/.cargo/config
vim ~/.cargo/config
```

并输入下面内容：

```toml
[source.crates-io]
registry = "https://github.com/rust-lang/crates.io-index"
# 指定镜像
replace-with = '镜像源名' # 如：tuna、sjtu、ustc，或者 rustcc

# 注：以下源配置一个即可，无需全部

# 中国科学技术大学
[source.ustc]
registry = "git://mirrors.ustc.edu.cn/crates.io-index"

# 上海交通大学
[source.sjtu]
registry = "https://mirrors.sjtug.sjtu.edu.cn/git/crates.io-index"

# 清华大学
[source.tuna]
registry = "https://mirrors.tuna.tsinghua.edu.cn/git/crates.io-index.git"

# rustcc社区
[source.rustcc]
registry = "https://code.aliyun.com/rustcc/crates.io-index.git"
```

删除 `.package-cache`

```bash
~/.cargo> rm -rf registry
~/.cargo> rm -rf .package-cache
```

## Rust 更新

**升级 Rust 「stable _稳定版_」**

可以执行 <code>rustup update</code> 来升级 Rust 「Stable Release _稳定版本_」

**安装 Rust 「Nightly Build _夜间构建_」**

可以执行 <code>rustup install nightly</code> 来安装 「Nightly Build <i>夜间构建</i>」

**切换默认版本**

选择稳定版或者 `nightly` 版，如果想长期使用 `nightly` 版。可以使用以下命令：`rustup default nightly`

**卸载 Rust**

在任何时候如果您想卸载 Rust，您可以运行 `rustup self uninstall`。

卸载 Rust nightly 可以使用 `rustup toolchain uninstall nightly-x86_64-pc-windows-msvc`。

查询 rustup 安装的工具链 请使用 `rustup show` 或 `rustup toolchain list`

<p>&nbsp;</p>

## 异常

<strong>Rust 版本更新错误记录：Os { code: 5, kind: PermissionDenied ...}</strong>

<pre>
PS C:\Users\liuzonglin> rustup update
info: syncing channel updates for 'stable-x86_64-pc-windows-msvc'
info: syncing channel updates for 'nightly-x86_64-pc-windows-msvc'
info: checking for self-update

   stable-x86_64-pc-windows-msvc unchanged - rustc 1.74.1 (a28077b28 2023-12-04)
  nightly-x86_64-pc-windows-msvc unchanged - rustc 1.77.0-nightly (2df6406b8 2023-12-26)

info: cleaning up downloads & tmp directories
thread 'main' panicked at 'Unable to clean up C:\Users\liuzonglin\.rustup\tmp: Os { code: 5, kind: PermissionDenied, message: "拒绝访问。" }', src\utils\utils.rs:650:13
note: run with `RUST_BACKTRACE=1` environment variable to display a backtrace
</pre>

<strong>原因：</strong>

<pre>
tmp 无法删除，目前没有正在运行的 rust 项目，应当是有其它 rust 的进程还在活动，检查 vscode, rust-analyzer 还在活动，停止插件或关闭 vscode。
</pre>

<p>&nbsp;</p>

<strong>如果在运行 cargo 的时候：Blocking waiting for file lock on package cache</strong>

请产生 `.cargo` 文件夹下面的 `.package_cache` 文件：

```sh
rm ~/.cargo/.package-cache
```

<strong>原因：</strong>

<pre>
cargo 堵塞问题。
</pre>

<p>&nbsp;</p>

## 参考文档

- [Substrate 开发系列 - 环境搭建][substrate_dev_install]
- [清华大学开源软件镜像站 Rust crates.io 索引][tsinghua_university]
- [RsProxy国内镜像代理][RsProxy国内镜像代理]
- [安装 Rust - Rust 程序设计语言 (rust-lang.org)][download_rustup]
- [Windows安装Rust指南 - 哔哩哔哩 (bilibili.com)][Windows安装Rust指南]

[Microsoft C++ 生成工具]: https://visualstudio.microsoft.com/zh-hans/visual-cpp-build-tools/
[download_rustup]: https://www.rust-lang.org/zh-CN/tools/install
[MinGW64]: https://sourceforge.net/projects/mingw-w64/files/
[substrate_dev_install]: https://learnblockchain.cn/article/1069
[tsinghua_university]: https://mirrors.tuna.tsinghua.edu.cn/help/crates.io-index.git/
[RsProxy国内镜像代理]: https://rsproxy.cn/
[Windows安装Rust指南]: https://www.bilibili.com/read/cv17841257

[^1]: 由于国内拉取 crates.io 以及安装 Rust 会面临流量出境不稳定的问题，我们提供了一个国内镜像代理以帮助国内 Rust 生态发展，欢迎大家使用。
