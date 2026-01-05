
- 官网下载 [Microsoft C++ 生成工具][Microsoft C++ 生成工具]，并安装 <strong>Desktop development with C++</strong> 如果不安装无法正常编译。

[Microsoft C++ 生成工具]: https://visualstudio.microsoft.com/zh-hans/visual-cpp-build-tools/

- 这里我们需要一个完整的 `gcc` 运行时环境。
  请在 MinGW64 官网[进行下载 `x86_64-win32-seh`][MinGW64]。

[MinGW64]: https://sourceforge.net/projects/mingw-w64/files/

  如不安装，编辑代码时，会影响部分函数提示。

- 并于系统变量 `Path` 中编辑环境变量，指定 `mingw64\bin` 目录。

  ```text
  D:\program\mingw64\bin
  ```

- 使用 <kbd>gcc -v</kbd> 测试 gcc 环境是否正常。

- 官网下载 <kbd>rustup-init.exe</kbd> 并安装，[点击这里下载！][download_rustup]

[download_rustup]: https://www.rust-lang.org/zh-CN/tools/install


## 安装 Rust 环境

打开 Rust 官网

[https://www.rust-lang.org](https://www.rust-lang.org/)

安装 Rustup 这一工具

如果使用 msvc 环境的话，一切默认就行了，而 mingw 就需要手动选择 gnu toolchain

<pre>
Rust Visual C++ prerequisites
 
Rust requires a linker and Windows API libraries but they don't seem to be
available.
 
These components can be acquired through a Visual Studio installer.
 
1) Quick install via the Visual Studio Community installer
   (free for individuals, academic uses, and open source).
 
2) Manually install the prerequisites
   (for enterprise and advanced users).
 
3) Don't install the prerequisites
   (if you're targeting the GNU ABI).
 
>
</pre>

它说 `rust` 需要 `windows API` 库和链接器，你可以选择下面 3 项的一项。

4. 安装 Visual Studio。
5. 手动安装的预设条件。
6. 无需预设条件。

我们这里选择 3，mingw-w64 也是 C/C++ 编译器，就是 GCC 的 Windows 版本 。回车：

<pre>
Welcome to Rust!
 
This will download and install the official compiler for the Rust
programming language, and its package manager, Cargo.
 
Rustup metadata and toolchains will be installed into the Rustup
home directory, located at:
 
  C:\Users564\.rustup
 
This can be modified with the RUSTUP_HOME environment variable.
 
The Cargo home directory is located at:
 
  C:\Users564\.cargo
 
This can be modified with the CARGO_HOME environment variable.
 
The cargo, rustc, rustup and other commands will be added to
Cargo's bin directory, located at:
 
  C:\Users564\.cargo\bin
 
This path will then be added to your PATH environment variable by
modifying the HKEY_CURRENT_USER/Environment/PATH registry key.
 
You can uninstall at any time with rustup self uninstall and
these changes will be reverted.
 
Current installation options:
 
 
   default host triple: x86_64-pc-windows-msvc
     default toolchain: stable (default)
               profile: default
  modify PATH variable: yes
 
7) Proceed with standard installation (default - just press enter)
8) Customize installation
9) Cancel installation
>

</pre>

10. 继续进行标准安装（默认设置-只需按enter键）
11. 自定义安装
12. 取消安装

我们输入2，就是自定义安装，出现提示：

<pre>
I'm going to ask you the value of each of these installation options.
You may simply press the Enter key to leave unchanged.
 
Default host triple? [x86_64-pc-windows-msvc]
</pre>

输入`x86_64-pc-windows-gnu`，（小提示，你先复制x86_64-pc-windows-gnu，在rustup右键可以直接粘贴文字，可以不用手写输入。

出现提示：

<pre>
Default toolchain? (stable/beta/nightly/none) [stable]
</pre>

询问安装什么版本，当然是稳定版

直接回车，或者输入`stable`，表示稳定版。

出现提示：

<pre>
Profile (which tools and data to install)? (minimal/default/complete) [default]
</pre>

询问要安装哪些工具

也是直接回车，或者输入`default`，表示默认。

出现提示：

<pre>
Modify PATH variable? (Y/n)
</pre>

输入`Y`，表示修改环境变量，回车

此时，会回到了第一步，提示：

<pre>
Current installation options:
 
 
   default host triple: x86_64-pc-windows-gnu
     default toolchain: stable
               profile: default
  modify PATH variable: yes
 
13) Proceed with selected options (default - just press enter)
14) Customize installation
15) Cancel installation
>

</pre>

直接回车，或者输入1

进入安装流程

<pre>
info: profile set to 'default'
info: setting default host triple to x86_64-pc-windows-gnu
info: syncing channel updates for 'stable-x86_64-pc-windows-gnu'
info: latest update on 2024-11-28, rust version 1.83.0 (90b35a623 2024-11-26)
info: downloading component 'cargo'
info: downloading component 'clippy'
info: downloading component 'rust-docs'
info: downloading component 'rust-mingw'
info: downloading component 'rust-std'
info: downloading component 'rustc'
 81.1 MiB /  81.1 MiB (100 %)  36.1 MiB/s in  2s ETA:  0s
info: downloading component 'rustfmt'
info: installing component 'cargo'
info: installing component 'clippy'
info: installing component 'rust-docs'
 16.5 MiB /  16.5 MiB (100 %)   1.8 MiB/s in  6s ETA:  0s
info: installing component 'rust-mingw'
info: installing component 'rust-std'
 26.6 MiB /  26.6 MiB (100 %)  19.7 MiB/s in  2s ETA:  0s
info: installing component 'rustc'
 81.1 MiB /  81.1 MiB (100 %)  19.8 MiB/s in  4s ETA:  0s
info: installing component 'rustfmt'
info: default toolchain set to 'stable-x86_64-pc-windows-gnu'
 
  stable-x86_64-pc-windows-gnu installed - rustc 1.83.0 (90b35a623 2024-11-26)
 
 
Rust is installed now. Great!
 
To get started you may need to restart your current shell.
This would reload its PATH environment variable to include
Cargo's bin directory (%USERPROFILE%\.cargo\bin).
 
Press the Enter key to continue.

</pre>

最后敲击回车，关闭窗口完成安装！
