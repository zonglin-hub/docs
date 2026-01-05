## Debian12 使用 x-cmd 安装 Rust

```sh
zonglin@debian:~$ eval "$(curl https://get.x-cmd.com)" # 采用 curl 安装 x-cmd
zonglin@debian:~$ x install # x-cmd 安装 rustup
- I|install: Selected software -> [rustup]
- I|install: Running the code
  more:
    code: curl --proto '=https' --tlsv1.2 -sSf https://sh.rustup.rs | sh
info: downloading installer

Welcome to Rust!

This will download and install the official compiler for the Rust
programming language, and its package manager, Cargo.

Rustup metadata and toolchains will be installed into the Rustup
home directory, located at:

  /home/zonglin/.rustup

This can be modified with the RUSTUP_HOME environment variable.

The Cargo home directory is located at:

  /home/zonglin/.cargo

This can be modified with the CARGO_HOME environment variable.

The cargo, rustc, rustup and other commands will be added to
Cargo's bin directory, located at:

  /home/zonglin/.cargo/bin

This path will then be added to your PATH environment variable by
modifying the profile files located at:

  /home/zonglin/.profile
  /home/zonglin/.bashrc

You can uninstall at any time with rustup self uninstall and
these changes will be reverted.

Current installation options:


   default host triple: x86_64-unknown-linux-gnu
     default toolchain: stable (default)
               profile: default
  modify PATH variable: yes

1) Proceed with standard installation (default - just press enter)
2) Customize installation
3) Cancel installation
>1

info: profile set to 'default'
info: default host triple is x86_64-unknown-linux-gnu
info: syncing channel updates for 'stable-x86_64-unknown-linux-gnu'
info: latest update on 2025-04-03, rust version 1.86.0 (05f9846f8 2025-03-31)
info: downloading component 'cargo'
  8.8 MiB /   8.8 MiB (100 %)   3.1 MiB/s in  3s
info: downloading component 'clippy'
  2.8 MiB /   2.8 MiB (100 %)   1.3 MiB/s in  2s
info: downloading component 'rust-docs'
 21.2 MiB /  21.2 MiB (100 %)   4.2 MiB/s in  6s
info: downloading component 'rust-std'
 27.1 MiB /  27.1 MiB (100 %)   1.6 MiB/s in 24s
info: downloading component 'rustc'
 72.8 MiB /  72.8 MiB (100 %)   5.8 MiB/s in 16s
info: downloading component 'rustfmt'
  2.4 MiB /   2.4 MiB (100 %) 693.3 KiB/s in  4s
info: installing component 'cargo'
info: installing component 'clippy'
info: installing component 'rust-docs'
 21.2 MiB /  21.2 MiB (100 %)   6.7 MiB/s in  2s
info: installing component 'rust-std'
 27.1 MiB /  27.1 MiB (100 %)  16.5 MiB/s in  1s
info: installing component 'rustc'
 72.8 MiB /  72.8 MiB (100 %)  16.4 MiB/s in  4s
info: installing component 'rustfmt'
info: default toolchain set to 'stable-x86_64-unknown-linux-gnu'

  stable-x86_64-unknown-linux-gnu installed - rustc 1.86.0 (05f9846f8 2025-03-31)


Rust is installed now. Great!

To get started you may need to restart your current shell.
This would reload your PATH environment variable to include
Cargo's bin directory ($HOME/.cargo/bin).

To configure your current shell, you need to source
the corresponding env file under $HOME/.cargo.

This is usually done by running one of the following (note the leading DOT):
. "$HOME/.cargo/env"            # For sh/bash/zsh/ash/dash/pdksh
source "$HOME/.cargo/env.fish"  # For fish
source "$HOME/.cargo/env.nu"    # For nushell
zonglin@debian:~$ . "$HOME/.cargo/env" # 配置环境变量
zonglin@debian:~$ rustup component add rust-analyzer # rustup 安装 rust-analyzer
info: downloading component 'rust-analyzer'
info: installing component 'rust-analyzer'
```

---

## error: linker `cc` not found

```sh
zonglin@debian:~/docs/Github/leet_code$ cargo b
   Compiling leet_code v0.1.0 (/home/zonglin/docs/Github/leet_code)
error: linker `cc` not found
  |
  = note: No such file or directory (os error 2)

error: could not compile `leet_code` (bin "hash_map") due to 1 previous error
```

你遇到的错误是因为系统缺少C语言编译器（`cc`或`gcc`）。
Rust在编译某些项目时需要链接C库，因此需要安装基础的C编译工具链。以下是解决方法：

---

### **1. 安装编译工具链**

在Debian/Ubuntu系统上，运行以下命令安装基础开发工具包（包含`gcc`、`make`等）：

```bash
sudo apt update
sudo apt install build-essential
```

---

### **2. 验证安装**

安装完成后，检查`gcc`是否可用：

```bash
gcc --version
```

正常输出应显示类似：

```bash
gcc (Debian 10.2.1-6) 10.2.1 20210110
```

---

### **3. 重新编译Rust项目**

返回项目目录，再次尝试编译：

```bash
cargo build
```

---

### **其他可能的问题**

如果仍然报错，可以检查：

1. **PATH环境变量**：确保`/usr/bin`在PATH中：

   ```bash
   echo $PATH
   ```

2. **Rust工具链**：更新Rust到最新版：

   ```bash
   rustup update
   ```

---

### **总结**

99%的情况下，安装 `build-essential` 即可解决问题。如果还有疑问，可以贴出完整错误日志。
