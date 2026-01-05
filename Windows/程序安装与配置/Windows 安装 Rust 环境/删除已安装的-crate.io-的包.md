# 删除已安装的 crate.io 的包

在 Rust 的 cargo 包管理器中，如果您想要删除注册表（registry），您可以使用 `cargo clean` 命令来清理本地的缓存。
这个命令会删除本地的 `target` 目录，该目录包含了编译结果和缓存的各种信息，但并不会删除已安装的 crate。
如果您想要删除特定的 registry，您可以手动去清理 `.cargo/registry` 目录下的相应部分。
例如，如果您想要删除来自 crates.io 的包，您可以删除 `.cargo/registry/src` 目录下的对应文件夹。

> [!NOTE]
> 请注意，在执行这些操作之前，确保您了解这些命令的影响，并做好必要的备份，以免删除重要文件。

## 删除 registry

- `C:\Users\zonglin\.cargo\registry\index\mirrors.ustc.edu.cn-61ef6e0cd06fb9b8`
- `C:\Users\zonglin\.cargo\registry\cache\mirrors.ustc.edu.cn-61ef6e0cd06fb9b8`
- `C:\Users\zonglin\.cargo\registry\src\mirrors.ustc.edu.cn-61ef6e0cd06fb9b8`

- `C:\Users\zonglin\.cargo\registry\CACHEDIR.TAG`

```text
签名： 8a477f597d28d172789f06886806bc55
# 这个文件是一个缓存目录标签创建的货物。
# 有关缓存目录标签的信息，请参见 https://bford.info/cachedir/
```

## 删除 git

- `C:\Users\zonglin\.cargo\git\checkouts`
- `C:\Users\zonglin\.cargo\git\db`

- `C:\Users\zonglin\.cargo\git\CACHEDIR.TAG`

```text
签名： 8a477f597d28d172789f06886806bc55
# 这个文件是一个缓存目录标签创建的货物。
# 有关缓存目录标签的信息，请参见 https://bford.info/cachedir/
```

## 删除 `.cargo/bin` 不在需要的软件包

- `C:\Users\zonglin\.cargo\bin` 使用 `cargo uninstall [package-name]` 删除不在需要的软件包

## 删除 rust-toolchains

<pre>
❯ rustup show
Default host: x86_64-pc-windows-msvc
rustup home:  C:\Users\zonglin\.rustup

installed toolchains
--------------------

stable-x86_64-pc-windows-msvc (default)
nightly-x86_64-pc-windows-msvc

installed targets for active toolchain
--------------------------------------

wasm32-unknown-unknown
x86_64-pc-windows-msvc

active toolchain
----------------

stable-x86_64-pc-windows-msvc (default)
rustc 1.77.1 (7cf61ebde 2024-03-27)

~
❯ rustup toolchain uninstall nightly-x86_64-pc-windows-msvc
info: uninstalling toolchain 'nightly-x86_64-pc-windows-msvc'
info: toolchain 'nightly-x86_64-pc-windows-msvc' uninstalled

~ took 10s
❯ rustup show
Default host: x86_64-pc-windows-msvc
rustup home:  C:\Users\zonglin\.rustup

installed targets for active toolchain
--------------------------------------

wasm32-unknown-unknown
x86_64-pc-windows-msvc

active toolchain
----------------

stable-x86_64-pc-windows-msvc (default)
rustc 1.77.1 (7cf61ebde 2024-03-27)
</pre>

删除 `.package-cache`

```bash
~/.cargo> rm -rf registry
~/.cargo> rm -rf .package-cache
```
