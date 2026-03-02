---
标题: 修复Dock/面板中丢失的应用程序图标
创建时间: 2026-01-05
修改时间: 2026-01-05
标签:
  - Ubuntu24.04
  - Gnome
---
# 修复 Ubuntu 24.04 中左侧 Dock/面板中丢失的应用程序图标

应用程序窗口在左侧（或底部）停靠面板上不显示图标？本教程可能有助于解决 Ubuntu 24.04 中的问题。

您在系统应用启动器中看到的应用图标由 `.desktop` 文件处理。这种配置文件通常包含一行 `Icon=app-icon-name`，因此 Ubuntu 和许多其他 Linux 可以在开始菜单（或应用程序菜单）中找到并显示应用程序的图标图像。

如果系统图标目录（通常是 `/usr/share/icons` 和 `/usr/share/pixmaps`）和用户本地图标文件夹（ `.loca/share/icons`），然后它显示通用可执行图标（带有灰色方形背景的齿轮图标，请参见上图）。

在 Ubuntu 24.04 中，当尝试启动应用程序（在我的例子中为 Transmission）时，它可能会在应用程序网格和搜索结果中显示其应用程序图标。这意味着应用程序图标文件已正确安装在您的系统上。然而，启动应用程序窗口后，Ubuntu Dock 会显示一个齿轮图标。


如果您将应用程序固定到 Dock，然后启动它，则会有 2 个图标。一个正确显示应用程序图标，另一个显示齿轮图标，表示找不到图标文件。
![[Pasted image 20260105231420.png]]

这是因为应用程序创建的窗口与用于启动应用程序的快捷方式没有关联。在 Ubuntu 22.04 及更早版本中，您将在 Dock 上看到重复的应用程序图标。然而，在 Ubuntu 24.04 中，它又退回到可执行图标（齿轮图标）。


在这种情况下，用户可以在`.desktop`配置文件中添加**StartupWMClass**字符串，并将该值设置为打开的窗口的类名。

### 找出应用程序窗口的类名

首先，您需要启动应用程序窗口。然后，按 **Alt + F2** 启动 **“Run a Command”** 对话框。当对话框打开时，输入 **lg** 并按 Enter 键。

在该窗口中，单击**Windows**按钮，然后找出目标应用程序窗口的类名称。就我而言，它们是：


```txt
- 传输：`com.transmissionbt.transmission_66310_4225300`
- VirtualBox：**Virtualbox 机器**
```

![[noble-lg.webp]]

### 添加`StartupWMClass`字符串

获得类名后，编辑相应的 .desktop 文件并添加 StarupVMClass 字符串。

`.desktop` 文件通常位于以下目录中：

- `/usr/share/applications` 用于 Deb 和 Snap 应用程序。
- `.local/share/applications` 用于用户手动创建或应用程序自动创建。
- `/var/lib/flatpak/exports/share/applications/` 用于 Flatpak 应用程序。

去检查前面的目录，找到您的应用程序对应的 .desktop 文件，然后对其进行编辑。

添加行 `StartupWMClass=Steam++`。保存文件后，只需重新启动软件，图标终于正确显示。

```desktop
[Desktop Entry]
Name=Watt Toolkit
Exec=/home/zonglin/programs/WattToolkit/Steam++.sh
Icon=/home/zonglin/programs/WattToolkit/Icons/Watt-Toolkit.png
Terminal=false
Type=Application
StartupNotify=false
StartupWMClass=Steam++
```


