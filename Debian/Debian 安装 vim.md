# Debian 安装 vim

```sh
❯ vim
bash: vim：未找到命令
```

根据输出信息，您的系统上可能没有安装 `vim` 编辑器。您可以尝试使用以下命令来安装 `vim` 编辑器：

```sh
apt-get install vim
```

安装完成后，再次尝试使用 `vim` 命令来编辑文件。
如果仍然提示 `vim: command not found`，可能是您的系统没有将 `vim` 的可执行文件路径添加到环境变量中。
您可以尝试使用以下命令来查找 `vim` 的可执行文件路径：

```sh
which vim
```

如果输出结果为 `/usr/bin/vim`，则说明可执行文件已经在 `/usr/bin` 目录下，
您可以尝试将 `/usr/bin` 目录添加到环境变量中。例如，在终端中输入以下命令：

```sh
export PATH=$PATH:/usr/bin
```

然后再次尝试使用 `vim` 命令来编辑文件。
