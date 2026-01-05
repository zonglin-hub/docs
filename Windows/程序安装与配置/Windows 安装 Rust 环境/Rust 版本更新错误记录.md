
<strong>Rust 版本更新错误记录：Os { code: 5, kind: PermissionDenied ...}</strong>

<pre>
PS C:\Users\zonglin> rustup update
info: syncing channel updates for 'stable-x86_64-pc-windows-msvc'
info: syncing channel updates for 'nightly-x86_64-pc-windows-msvc'
info: checking for self-update

   stable-x86_64-pc-windows-msvc unchanged - rustc 1.74.1 (a28077b28 2023-12-04)
  nightly-x86_64-pc-windows-msvc unchanged - rustc 1.77.0-nightly (2df6406b8 2023-12-26)

info: cleaning up downloads & tmp directories
thread 'main' panicked at 'Unable to clean up C:\Users\zonglin\.rustup\tmp: Os { code: 5, kind: PermissionDenied, message: "拒绝访问。" }', src\utils\utils.rs:650:13
note: run with `RUST_BACKTRACE=1` environment variable to display a backtrace
</pre>

<strong>原因：</strong>

<pre>
tmp 无法删除，目前没有正在运行的 rust 项目，应当是有其它 rust 的进程还在活动，检查 vscode, rust-analyzer 还在活动，停止插件或关闭 vscode。
</pre>
