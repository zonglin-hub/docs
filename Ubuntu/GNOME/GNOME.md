## GNOME桌面扩展

在Ubuntu中，虽然插件的管理通常通过命令行或特定的工具完成，但确实存在一些桌面端工具或方法可以方便地管理和使用插件。以下是相关信息的整理：

<https://www.ittce.com/course/linux/12939.html>


### Ubuntu 插件管理概述

Ubuntu 插件的管理主要依赖于命令行工具，例如：

- __GNOME桌面扩展__：通过`gnome-shell-extensions`管理桌面扩展，这些扩展可以增强桌面功能。
虽然这些工具功能强大，但命令行操作可能对部分用户不够直观，因此桌面端工具的需求随之产生。

```bash
sudo apt-get install gnome-shell-extensions
```


### GNOME 桌面管理扩展工具

#### __GNOME Tweaks__

- __功能__：这是一个图形化工具，可以调整GNOME桌面的各种设置，包括安装和管理扩展。
- __安装命令__：

  ```bash
  sudo apt install gnome-tweaks chrome-gnome-shell
  ```

- __使用方法__：通过该工具，用户可以方便地安装、启用或禁用GNOME扩展，无需使用命令行。


## 活动概览（Activities Overview）


在GNOME桌面环境中，按两下Win键（或Super键）所显示的“所有应用”界面，通常被称为“活动概览”（Activities Overview）。

### 功能说明：

- **活动概览**是GNOME Shell的核心交互界面，它集中展示了当前打开的窗口、虚拟桌面以及系统中安装的所有应用程序。
- 用户可以通过点击屏幕左上角的“活动”按钮，或按一次Win键（Super键）进入该界面，而按两次Win键则通常用于快速触发或切换至该视图。
- 在此界面中，用户可以通过搜索框快速查找应用、文件或设置，也可以通过拖拽窗口到不同工作区进行多任务管理。

### 设计特点：

活动概览体现了GNOME简洁高效的设计理念，将应用程序管理、窗口切换和工作区操作整合到一个统一的界面中，提升用户的操作效率。
如需进一步了解GNOME的快捷键或功能，可参考相关文档或搜索来源。

### 快捷方式（.desktop文件）主要存储在以下目录

在GNOME桌面环境中，“活动概览”（Activities Overview）中显示的应用程序，其快捷方式（.desktop文件）主要存储在以下目录中：

1. 系统级应用程序目录：`/usr/share/applications/` 该目录存放了系统中所有用户可用的应用程序快捷方式。每个应用程序的启动信息以.desktop文件的形式存储，例如 `firefox.desktop` 或 `gedit.desktop`。这些文件定义了应用程序的名称、图标、启动命令等信息。
2. 用户级应用程序目录：`~/.local/share/applications/` 该目录用于存放当前用户自定义的应用程序快捷方式。用户可以在此目录下创建或修改.desktop文件，这些快捷方式仅对当前用户生效，不会影响其他用户。
3. 其他可能的目录：  
	- Snap 应用：应用程序的快捷方式存储在与 `/var/lib/snapd/desktop/applications/` 目录。
	- Flatpak 应用：应用程序的快捷方式存储在与 `/var/lib/flatpak/exports/share/applications/` 目录。
