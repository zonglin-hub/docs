
在 Termux 上安 Zsh

```sh
pkg install zsh
```

安装完成后，如果你想将 Zsh 设置为默认的 Shell，可以使用以下命令：

```sh
chsh -s zsh
```

setting alias:

```sh
alias ll='ls -l'
```

使更改生效：

```sh
source ~/.zshrc
```

如果你不确定问题所在，你可以尝试逐行注释掉 `.zshrc` 文件中的内容，直到错误不再出现，这样可以帮助你定位问题所在。
