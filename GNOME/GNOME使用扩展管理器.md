---
标题: GNOME 使用扩展管理器
创建时间: 2026-03-14
修改时间: 2026-03-14
标签:
  - Flatpak
  - Ubuntu 24.04
---

__参考文档__：

- [必先利其器之Ubuntu 24.04 LTS软件安装和美化攻略](ttps://www.ittce.com/course/linux/12939.html)

---

### GNOME 插件管理概述

GNOME 插件的管理主要依赖于命令行工具，例如：

- __GNOME桌面扩展__：通过`gnome-shell-extensions`管理桌面扩展，这些扩展可以增强
桌面功能。虽然这些工具功能强大，但命令行操作可能对部分用户不够直观，因此桌面端工
具的需求随之产生。

  ```bash
  sudo apt-get install gnome-shell-extensions
  ```

### 使用扩展管理器


- __功能__：这是一个图形化工具，可以调整GNOME桌面的各种设置，包括安装和管理扩展。

- __Debian/Ubuntu__：

    ```bash
    sudo apt install gnome-tweaks chrome-gnome-shell
    ```

- __使用方法__：通过该工具，用户可以方便地安装、启用或禁用GNOME扩展，无需使用命令行。

    你还可以使用 __Extension Manager__（扩展管理器）这类工具来更方便地浏览、安装和管
理扩展。有些管理器会直接显示扩展与当前系统的兼容性状态。

- __可以通过 Flatpak 安装 Extension Manager：__

    ```bash
    # 安装 Flatpak 并配置源（如尚未配置）
    sudo apt install flatpak  # 或根据你的发行版使用其他命令
    flatpak remote-add --if-not-exists flathub https://flathub.org/repo/flathub.flatpakrepo

    # 安装扩展管理器
    flatpak install flathub com.mattjakeman.ExtensionManager
    ```