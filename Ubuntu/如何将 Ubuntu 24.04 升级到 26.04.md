---
标题: 如何将 Ubuntu 24.04 LTS 升级到 26.04 LTS
创建时间: 2026-05-22
修改时间: 2026-05-22
标签:
  - Ubuntu24.04
  - Ubuntu26.04
---

# 如何将 Ubuntu 24.04 LTS 升级到 26.04 LTS


Ubuntu 26.04 LTS（Resolute Raccoon）目前正在积极开发中，虽然最终版本还有几个月时
间，但喜欢冒险的用户已经可以从 Ubuntu 24.04 升级，测试最新功能。本指南将通过
 `do-release-upgrade` 命令引导你完成升级流程。

**在这个教程中，你将学习：**

- 如何将 Ubuntu 24.04 LTS 升级到 Ubuntu 26.04 LTS
- 排查常见升级问题

如何将 Ubuntu 24.04 升级到 26.04

**文章状态**  

随着 Ubuntu 26.04 开发的推进，本文正在积极更新。随着新版本发布和功能最终确定，信
息可能会发生变化。

## 快速升级至 26.04 命令摘要

| Task  任务          | Command  指挥                              |
| ------------------- | ------------------------------------------ |
| 更新系统包          | `$ sudo apt update && sudo apt upgrade -y` |
| 升级到 Ubuntu 26.04 | `$ sudo do-release-upgrade -d`             |
| 重启系统            | `$ sudo reboot`                            |

## 从 Ubuntu 24.04 升级到 26.04

  
在尝试升级之前，请确保你的 Ubuntu 24.04 系统已完全更新，并且重要文件都有完整备
份。想了解更多关于 Ubuntu 26.04 预期功能和发布时间表的信息，请查看我们的
Ubuntu [26.04 发布日期和新功能指
南](https://linuxconfig.org/ubuntu-26-04-release-date-and-new-features-in-resolute-raccoon) 。

### 步骤 1：全面更新 Ubuntu 24.04

**更新当前系统** ：升级前，确保你 Ubuntu 24.04 系统上的所有软件包都是最新的。

```bash
$ sudo apt update && sudo apt upgrade -y
```

随后进行配送升级，以处理任何滞留的包裹：

```bash
sudo apt dist-upgrade -y
```

移除不再需要的包裹：

```bash
sudo apt autoremove -y
```

该命令更新包列表，并将所有已安装包升级到最新版本。

### 步骤 2：安装更新管理器


该 `update-manager-core` 软件包提供了工具 `do-release-upgrade` 。通常默认存在，
但请确认：

```bash
sudo apt install -y update-manager-core
```


确认升级提示设置为 LTS 发布：

```bash
cat /etc/update-manager/release-upgrades
```


提示设置为 LTS 发布：

```bash
Prompt=lts
```


如果写着 `never` ，就先改为 `lts` 然后继续。

### 步骤 3：检查可用升级

运行升级检查，看看是否提供 26.04：

```bash
sudo do-release-upgrade -c
```


如果 Ubuntu 26.04 已经正式发布（2026 年 4 月 23 日），你会看到确认升级可用的信
息。如果你是在正式发布日期之前运行的，请使用 `-d` 该标志访问开发版本：

```bash
sudo do-release-upgrade -d -c
```


确认 26.04 可用后，开始升级吧。
### 步骤 4：运行升级

**启动升级** ：开始升级。对于 SSH 会话，工具会在 1022 端口开启一个备份 SSH 监听
器，以防主连接中断：

```bash
$ sudo do-release-upgrade -d
```


LTS 到 LTS 升级的 `-d` 标记保持到 Ubuntu 发布 26.04.1，通常在 2026 年 7 月左右。
原因是结构上的： `do-release-upgrade` 在
`Prompt=lts` changelogs.ubuntu.com/meta-release-lts 模式读取了仅限 LTS 的元文
件，而 Canonical 只有在首批点发布后才会更新新的 LTS 版本。今天 24.04 日运行纯
`sudo do-release-upgrade` 正，它会恢复 `There is no development version of an
LTS available` 。该 `-d` 标志指向 meta-release-lts-development，该版本已将 26.04
列为有效升级目标。一旦 26.04.1 发布，普通路径 `sudo do-release-upgrade` 即可使
用。

升级工具将：

1. 禁用第三方仓库和 PPA（之后会重新启用兼容的）
2. 从 26.04 版本仓库下载新的包列表
3. 计算升级费用（预计 700+套餐会变动）
4. 下载前请你确认一下
5. 下载并安装所有新软件包
6. 询问一些配置文件冲突的问题（除非有特别理由，否则保留现有版本）
7. 清理过时的包裹
8. 重启提示

在我们的测试服务器（4 核，4GB 内存，100 Mbps 连接）上，整个过程从开始到重启提示
大约需要 12 分钟。

对于非交互式升级（在脚本或自动化中非常有用），请使用：

```bash
sudo do-release-upgrade -d -f DistUpgradeViewNonInteractive
```

这会自动对所有提示回答“是”，并保持现有配置文件冲突。只有在你已经在克隆机上测试过
升级后才用这个。

### 步骤 5：重启

**重启系统** ：升级成功完成后，重启电脑。

```bash
$ sudo reboot
```


等一会儿服务器恢复，然后重新连接。
### 步骤 6：验证升级

确认你使用的是 Ubuntu 26.04：

```bash
lsb_release -a
```

输出确认了 Ubuntu 26.04：

```bash
Distributor ID:	Ubuntu
Description:	Ubuntu Resolute Raccoon (development branch)
Release:	26.04
Codename:	resolute
```

检查内核版本（重启后应为 7.0.x）：

```bash
uname -r
```

内核 7.0 现已加载：

```bash
7.0.0-13-generic
```

验证关键系统组件：

```bash
python3 --version && sudo --version | head -1 && ssh -V 2>&1
```

所有关键组件都显示了新版本：

```bash
Python 3.14.4
sudo-rs 0.2.13-0ubuntu1
OpenSSH_10.2p1 Ubuntu-2ubuntu3, OpenSSL 3.5.5 27 Jan 2026
```

运行最终更新，捕捉升级工具构建后发布的任何包：

```bash
sudo apt update && sudo apt upgrade -y
```

系统已全面升级。接下来清理剩下的包裹。

### Post-Upgrade Cleanup  升级后清理

移除不再需要的旧内核和包：

```bash
sudo apt autoremove -y --purge
```

检查是否有禁用的第三方仓库。升级工具通过在文件名中添加 `.distUpgrade` 以下内容禁
用 PPA 和第三方来源：

```bash
ls /etc/apt/sources.list.d/*.distUpgrade 2>/dev/null
```

如果出现了，请检查这些仓库是否已更新为 Ubuntu 26.04（Resolute）。通过移除
`.distUpgrade` 扩展名并将代号从 `noble` 更新为 `resolute` 来重新启用它们。Docker
是一个常见的需要重新添加的插件;请参阅我们在 Ubuntu 26.04 上安装Docker CE 的指
南，了解更新后的仓库设置。

检查磁盘使用情况（升级通常会为根分区增加 500MB 到 1GB）：

```bash
df -h /
```

升级后，根分区增加了大约 1 GB：

```bash
Filesystem      Size  Used Avail Use% Mounted on
/dev/sda1        48G  3.5G   44G   8% /
```

清理工作完成。升级后的系统有几件事需要关注。


### Troubleshooting  故障排除

如果你在尝试升级时遇到以下错误：

```bash
$ do-release-upgrade
Checking for a new Ubuntu release
No new release found
```


## 升级后需要注意的事项

Sudo-RS 取代了 Sudo。常见操作的行为几乎相同，但某些边缘情况不同。依赖 sudo `-S`
标志（从 stdin 读取密码）或复杂 `sudoers` 语法的脚本应测试。命令仍然被调用
 `sudo` ，因此无需更改 PATH。

Python 3.14 取代了 3.12。如果你有 Python 虚拟环境，就需要重建。该 `venv` 模块无
法加载用旧 Python 创建的环境。用 重建它们。 `python3 -m venv --clear
/path/to/venv`

OpenSSH 10.2 的默认设置更严格。一些老旧的 SSH 客户端如果使用已废弃的算法，可能会
遇到连接困难。检查 `/etc/ssh/sshd_config` 一下遗留系统是否存在认证失败。我们的
SSH 服务器配置指南涵盖了相关设置。

Kernel 7.0 改变了 I/O 调度。如果你运行数据库或高 I/O 工作负载，升级后再做基准测
试。新内核默认通常更好，但你的具体调优参数可能需要调整。如果有任何异常，请查看我
们的 journalctl 日志过滤指南，查找系统 259 日志中的异常。

### 我可以直接从 Ubuntu 22.04 升级到 26.04 吗？

不。Ubuntu 需要顺序进行 LTS 升级：先从 22.04 升级到 24.04，然后从 24.04 升级到
26.04。每次跳跃需要 10 到 15 分钟，所以整个 22.04 到 26.04 的路径（包括重启）大
约要花 30 分钟。

### 升级生产服务器安全吗？

先在克隆体上测试。升级本身很可靠（我们在干净系统上从未遇到过 LTS 升级失败的情
况），但你的应用可能依赖于不同版本的 Python、PHP 或库版本。在接触生产环境前先验
证你的技术栈。升级成功后，按照 Ubuntu 26.04 服务器安装后的检查清单和完整的服务器
硬化指南，锁定新的默认设置。

### 如果升级中途失败怎么办？

升级工具会创建一个恢复状态。如果被中断，重新连接（可能通过 1022 端口的备份
SSH），然后运行 `sudo dpkg --configure -a` ，接着执行 `sudo apt -f install` 。如
果系统无法恢复，就从备份恢复。你确实做了备份，对吧？