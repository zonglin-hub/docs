# Shell 是什么

## 一句话

**Shell 是 Linux 里负责接收、解释并执行你输入命令的程序。**

中文可叫：**命令解释器 / 命令行解释器**。  
日常直接叫 **Shell** 最清楚。

它不是终端窗口，不是 Linux 内核，也不是脚本文件。

---

## 核心关系

```text
你
 ↓
终端窗口（GNOME Terminal、Konsole、xterm 等）
 ↓
Shell（bash、zsh、dash 等）
 ↓
系统程序 / Linux 内核
 ↓
结果返回给 Shell，再显示给你
```

你看到：

```bash
user@host:~$
```

这个提示符背后等待你输入命令的程序，通常就是 Shell。

---

## Shell 做什么

你输入：

```bash
ls -l /home
```

Shell 会：

1. 读懂这是命令 `ls`；
2. 识别参数 `-l` 和 `/home`；
3. 找到并调用 `ls` 程序；
4. 把执行结果显示给你。

Shell 还负责命令行语法，例如：

```bash
cat access.log | grep "ERROR" > errors.txt
```

其中：

- `|` 管道：把前一个命令的输出交给后一个命令；
- `>` 重定向：把输出写入文件；
- Shell 负责组织这些命令之间的关系。

---

## 容易混淆的概念

| 名称 | 是什么 | 简单理解 |
|---|---|---|
| Linux 内核 Kernel | 系统核心，管理 CPU、内存、文件、设备 | 系统底层核心 |
| Shell | 接收并解释命令的程序 | 命令解释器 |
| 终端 Terminal | 你输入命令的窗口/界面 | 交互场所 |
| Bash / Zsh | 具体的 Shell 程序 | 不同 Shell 实现 |
| Shell 脚本 | 写给 Shell 执行的一串命令文件 | 命令清单文件 |
| 命令 | 你让电脑执行的一项操作 | 如 `ls`、`cp`、`grep` |

---

## Shell 和 Shell 脚本的区别

**Shell 是程序，Shell 脚本是文件。**

Shell：

```bash
bash
```

这是一个命令解释器程序。

Shell 脚本：

```bash
#!/bin/bash
echo "开始备份"
cp -r /home/user/docs /backup/docs
echo "备份完成"
```

这是一个脚本文件，例如 `backup.sh`，交给 Shell 执行。

所以：

> Shell 是执行命令的程序；Shell 脚本是写给 Shell 执行的一串命令。

类似：

- Python 是解释器，Python 脚本是交给 Python 执行的文件；
- Shell 是命令解释器，Shell 脚本是交给 Shell 执行的文件。

---

## 常见 Shell

| Shell | 说明 |
|---|---|
| `sh` | POSIX Shell 统称，常指向 `dash` 或 `bash` |
| `bash` | 大多数 Linux 发行版默认 Shell |
| `zsh` | 功能丰富，常见于 macOS 和部分 Linux 用户 |
| `dash` | 轻量、启动快，Debian/Ubuntu 的 `/bin/sh` 常指向它 |
| `fish` | 交互友好，但语法和传统 Shell 不完全一样 |

---

## 查看自己用的 Shell

### 登录 Shell

```bash
echo $SHELL
```

示例：

```text
/bin/bash
```

注意：这通常表示登录 Shell，不一定等于当前交互 Shell。

### 当前交互 Shell 进程

```bash
ps -p $$
```

`$$` 表示当前 Shell 的进程号。

### 当前 Shell 名称

```bash
echo $0
```

交互式 Shell 中可能显示：

```text
bash
```

或：

```text
-bash
```

脚本中 `$0` 通常显示脚本名称。

---

## 记住这三句

1. **终端窗口**是你打字的地方。
2. **Shell** 是运行在终端里、读懂并执行你命令的程序。
3. **Shell 脚本**是写好一串命令、交给 Shell 执行的文件。

---

## 最终总结

> **Shell 不是脚本，也不是终端窗口。Shell 是 Linux 的命令解释器：你输入命令，它
> 读懂命令，调用程序或自己处理，再把结果返回给你。中文可叫“命令解释器”，日常直接
> 叫 Shell。**