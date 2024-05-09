# Debian ls 文件 文件夹颜色显示

原文地址：<https://blog.csdn.net/qq_34006340/article/details/103288843>

---

## 让 Debian 终端文件显示不同颜色

`vim /etc/vim/vimrc` 取消 “syntax on” 的注释

## 让 Debian 终端文件夹显示不同颜色

修改 .bashrc 文件，可以使用 `vim /root/.bashrc` 修改如下：

```text
# export LS_OPTIONS='--color=auto'
# eval `dircolors`
# alias ls='ls $LS_OPTIONS'
# alias ll='ls $LS_OPTIONS -l'
# alias l='ls $LS_OPTIONS -lA'

```

把上面行的注释全部去掉，重新打开终端即可。
