# alias

参考连接：[alias(1p) - Linux manual page]

[alias(1p) - Linux manual page]: https://man7.org/linux/man-pages/man1/alias.1p.html

主要用途

- 简化较长的命令。
- 定义、修改或者显示一个或多个别名。

目录

- [alias\_定义或显示别名](#alias_定义或显示别名)
    - [选项](#选项)
    - [例子](#例子)
    - [知识点](#知识点)
    - [错误用法](#错误用法)

## 选项

<pre>
❯ alias --help
alias: alias [-p] [名称[=值] ... ]
    定义或显示别名。

    不带参数时，"alias" 以可重用的格式 "alias 名称=值" 将别名列表
    打印到标准输出。

    否则，对于每一个给出了 <值> 的 <名称> 定义一个别名。如果 <值> 的
    末尾是空格，那么在展开该别名后，还会继续检查下一个词是否可以
    进行别名替换。

    选项：
      -p        以可重用的格式打印所有已定义的别名

    退出状态：
    alias 返回真，除非提供了一个尚未定义别名的 <名称>。
</pre>

## 例子

```shell
# 显示全部已定义的别名
alias
alias -p

# 显示已定义的别名
alias ls
alias ls grep

# 定义或修改别名的值
alias ls='ls --color=auto'
alias ls='ls --color=never' grep='grep --color=never'
```

## 知识点

直接在 shell 里设定的命令别名，在终端关闭或者系统重新启动后都会失效，如何才能永久有效呢？

使用编辑器打开 "~/.bashrc"，在文件中加入别名设置，如：`alias rm='rm -i'`，
保存后执行 `source ~/.bashrc`，这样就可以永久保存命令的别名了。

因为修改的是当前用户目录下的 "~/.bashrc" 文件，所以这样的方式只对当前用户有用。
如果要对所有用户都有效，修改 "/etc/bashrc" 文件就可以了。

> 请注意，以下内容可能与您实际使用的系统有出入:
>
> 在 CentOS7 下，这个文件是 "/etc/bash.bashrc"。
> 此外在 CentOS7 下，细看 "~/.bashrc" 文件，会发现有这样一段代码：
>
> ```shell
> if [ -f ~/.bash_aliases ]; then
>   . ~/.bash_aliases
> fi
> ```
>
> 这个代码的意思就是如果存在那么就加载 ".bash_aliases" 文件，
> 所以也可以在用户根目录下新建该文件用于单独存放命令别名设置。

## 错误用法

- 要显示的别名未定义。
- 当您定义（修改）别名的值的时候，由于值的字符串有空格但您没有用**单引号扩起**，那么会导致严重的问题：

```shell
# 为方便演示，删除全部别名
unalias -a
# 没有用单引号扩起
alias rm=rm -rf
# 执行命令后报错 bash: alias: -rf: not found
# 这时使用alias查看rm的别名时返回 alias rm='rm'
```

```shell
# 更具有迷惑性的例子
# 为方便演示，删除全部别名
unalias -a
# 仍然没有用单引号括起
alias ls=ls --color=never
# 执行命令后看起来没有报错

# 使用alias查看全部别名会发现运行结果如下：
# alias --color=never
# alias ls='ls'
# alias处理时将它们看成了两组
```
