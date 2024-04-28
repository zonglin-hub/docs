[在 Ubuntu 22.04 LTS 上安装 LLVM：完整指南]

```shell
sudo bash -c "$(wget -O - https://apt.llvm.org/llvm.sh)" # Instructions from https://apt.llvm.org/
cd $(dirname $(which lldb-17))
sudo ln -s lldb-vscode-17 lldb-vscode 
```

[How do I install lldb-vscode？ #5297]

[在 Ubuntu 22.04 LTS 上安装 LLVM：完整指南]: https://thelinuxcode.com/install-llvm-ubuntu/

[How do I install lldb-vscode？ #5297]: https://github.com/helix-editor/helix/discussions/5297

[在 Ubuntu 22.04 上安装 LLVM](https://cn.linux-console.net/?p=15141#google_vignette)
