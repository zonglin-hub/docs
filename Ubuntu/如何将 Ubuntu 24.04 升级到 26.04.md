
# 如何将 Ubuntu 24.04 升级到 26.04

Ubuntu 26.04 LTS（Resolute Raccoon）目前正在积极开发中，虽然最终版本还有几个月时间，但喜欢冒险的用户已经可以从 Ubuntu 24.04 升级，测试最新功能。本指南将通过 `do-release-upgrade` 命令引导你完成升级流程。

**在这个教程中，你将学习：**

- 如何将 Ubuntu 24.04 升级到 Ubuntu 26.04 开发版本
- 排查常见升级问题

如何将 Ubuntu 24.04 升级到 26.04

**文章状态**  

随着 Ubuntu 26.04 开发的推进，本文正在积极更新。随着新版本发布和功能最终确定，信息可能会发生变化。

## Ubuntu 快速升级至 26.04 命令摘要

| Task  任务         | Command  指挥                                |
| ---------------- | ------------------------------------------ |
| 更新系统包            | `$ sudo apt update && sudo apt upgrade -y` |
| 升级到 Ubuntu 26.04 | `$ sudo do-release-upgrade -d`             |
| 重启系统             | `$ sudo reboot`                            |

## 从 Ubuntu 24.04 升级到 26.04

**开发版本警告**  
Ubuntu 26.04 目前是开发版本。升级到该版本可能导致系统不稳定、软件不兼容以及潜在的数据丢失。只有在你了解风险并备份所有重要数据后，才可继续。此升级仅推荐用于测试，不适用于生产系统。

  
在尝试升级之前，请确保你的 Ubuntu 24.04 系统已完全更新，并且重要文件都有完整备份。想了解更多关于 Ubuntu 26.04 预期功能和发布时间表的信息，请查看我们的 Ubuntu [26.04 发布日期和新功能指南](https://linuxconfig.org/ubuntu-26-04-release-date-and-new-features-in-resolute-raccoon) 。

**更新当前系统** ：升级前，确保你 Ubuntu 24.04 系统上的所有软件包都是最新的。

```bash
$ sudo apt update && sudo apt upgrade -y
```

该命令更新包列表，并将所有已安装包升级到最新版本。

**启动升级** ：运行带有开发标志的发布升级命令。

```bash
$ sudo do-release-upgrade -d
```

`-d` 标志允许升级到开发版本。按照屏幕上的提示完成升级流程。

![Ubuntu 26.04 LTS Resolute Raccoon upgrade command running in terminal showing welcome message and release information](https://linuxconfig.org/wp-content/uploads/2025/10/01-upgrade-ubuntu-to-2604-image.avif)

成功启动 Ubuntu 26.04 LTS 升级，使用 do-release-upgrade -d 命令

![Ubuntu 26.04 upgrade confirmation prompt showing package changes and download size requirements](https://linuxconfig.org/wp-content/uploads/2025/10/02-upgrade-ubuntu-to-2604-image.avif)

升级提示显示11个待移除包、149个新包待安装和1297个待升级包

![Ubuntu upgrade package configuration dialog asking about automatic service restarts during library upgrades](https://linuxconfig.org/wp-content/uploads/2025/10/03-upgrade-ubuntu-to-2604-image.avif)

软件包配置提示，询问是否在软件包升级时自动重启服务，但没有提示

![Ubuntu upgrade dialog showing failure to restart CUPS service during GNU libc library upgrade](https://linuxconfig.org/wp-content/uploads/2025/10/04-upgrade-ubuntu-to-2604-image.avif)

配置对话框显示 CUPS 服务无法自动重启，需要人工干预

![Ubuntu 26.04 upgrade process showing snap package channel switching and obsolete package removal prompt](https://linuxconfig.org/wp-content/uploads/2025/10/05-upgrade-ubuntu-to-2604-image.avif)

升级流程：切换不同应用的快读信道，并提示移除134个过时软件包

![Ubuntu 26.04 upgrade completion screen showing configuration file purging and system restart requirement](https://linuxconfig.org/wp-content/uploads/2025/10/06-upgrade-ubuntu-to-2604-image.avif)

Ubuntu 升级的最后阶段显示已清除配置文件，完成 Pro 需要重启系统

**重启系统** ：升级成功完成后，重启电脑。

```bash
$ sudo reboot
```

你的系统将启动 Ubuntu 26.04。

![Ubuntu 26.04 Resolute Raccoon desktop with new raccoon-themed wallpaper and system information dialog](https://linuxconfig.org/wp-content/uploads/2025/10/07-upgrade-ubuntu-to-2604-image.avif)

Ubuntu 26.04 桌面，配备了新的 Resolute Raccoon 壁纸，系统信息显示升级成功


## Troubleshooting  故障排除

如果你在尝试升级时遇到以下错误：

```bash
$ do-release-upgrade
Checking for a new Ubuntu release
No new release found
```


这是因为 Ubuntu 26.04 仍在开发中，不被视为稳定版本。解决方案是使用 `-d` 标志，它专门支持升级到开发版本：

```bash
$ sudo do-release-upgrade -d
```

`-d` 标志指示升级工具检查并允许升级到预发布开发版本。

## 替代方案：全新安装

如果你更喜欢干净安装而不是升级，可以选择每日构建 ISO 镜像。请访问我们的 [Ubuntu 26.04 下载指南](https://linuxconfig.org/ubuntu-26-04-download) ，了解如何获取最新的每日版本。

## 结论

从 Ubuntu 24.04 升级到 Ubuntu 26.04 可以通过 `do-release-upgrade -d` 命令实现，但请记住，这只是一个仅供测试和开发目的的开发版本。随着 Ubuntu 26.04 接近最终版本，升级过程将变得更加稳定，并适用于生产环境。在尝试任何重大系统升级前，务必保持当前备份。
