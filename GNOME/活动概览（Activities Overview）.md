---
标题: 活动概览（Activities Overview）中显示的应用程序路径说明
创建时间: 2026-11-25
修改时间: 2026-11-25
标签:
  - Gnome
---


活动概览（Activities Overview）中显示的应用程序
---

在GNOME桌面环境中，按两下Win键（或Super键）所显示的“所有应用”界面，通常被称为“活
动概览”（Activities Overview）。

### 功能说明：

- __活动概览__ 是GNOME Shell的核心交互界面，它集中展示了当前打开的窗口、虚拟桌面
  以及系统中安装的所有应用程序。
- 用户可以通过点击屏幕左上角的“活动”按钮，或按一次Win键（Super键）进入该界面，而
  按两次Win键则通常用于快速触发或切换至该视图。
- 在此界面中，用户可以通过搜索框快速查找应用、文件或设置，也可以通过拖拽窗口到不
  同工作区进行多任务管理。

### 设计特点：

活动概览体现了GNOME简洁高效的设计理念，将应用程序管理、窗口切换和工作区操作整合
到一个统一的界面中，提升用户的操作效率。如需进一步了解GNOME的快捷键或功能，可参
考相关文档或搜索来源。

### `.desktop` 文件主要存储在以下目录

在GNOME桌面环境中，“活动概览”（Activities Overview）中显示的应用程序，其快捷方式
（.desktop文件）主要存储在以下目录中：

| 类别 | 目录路径 | 作用说明 | 生效范围/备注 | 示例 |
|---|---|---|---|---|
| 系统级应用程序目录 | `/usr/share/applications/` | 存放系统中所有用户可用的应用程序快捷方式； | 对所有用户生效 | `firefox.desktop`、`gedit.desktop` |
| 用户级应用程序目录 | `~/.local/share/applications/` | 存放当前用户自定义的应用程序快捷方式； | 仅对当前用户生效 | 用户自定义的 `.desktop` 文件 |
| Snap 应用快捷方式目录 | `/var/lib/snapd/desktop/applications/` | 存放 Snap 应用的应用程序快捷方式 | 由 Snap 管理 | Snap 应用的 `.desktop` 文件 |
| Flatpak 应用快捷方式目录 | `/var/lib/flatpak/exports/share/applications/` | 存放 Flatpak 应用的应用程序快捷方式 | 由 Flatpak 管理 | Flatpak 应用的 `.desktop` 文件 |