Ubuntu 22.04 安装 Clementine 音乐播放器

---

Clementine 是适用于多种操作系统（包括 Windows、Linux 和 macOS）的一流免费音乐播放器之一。
除了播放系统中的音乐之外，它还允许您自由地聆听互联网上的音乐，使其成为 Ubuntu 22.04 桌面的完美选择。

在本文中，我们将向您展示如何在 Ubuntu 22.04 上安装 Clementine 音乐播放器。

如何在 Ubuntu 22.04 上安装 Clementine 音乐播放器

Clementine 音乐播放器可以通过以下方法轻松安装：

- [通过 Ubuntu 存储库](#通过-ubuntu-存储库)
- [通过 Snap 商店](#通过-snap-商店)
- [结论](#结论)

### 通过 Ubuntu 存储库

Clementine 存储库已包含在 Ubuntu 22.4 中，使您可以轻松安装最新版本的 Clementine 音乐播放器。要执行此方法，请使用以下步骤。

第 1 步：首先，使用以下命令更新 Ubuntu 软件包列表：

```bash
sudo apt update
```

第2步：然后运行以下安装命令在 Ubuntu 22.04 上安装 Clementine。

```bash
sudo apt install clementine
```

要检查版本，请执行以下命令：

```bash
clementine --version
```

要打开该应用程序，请转到 Ubuntu 应用程序搜索栏，您将在那里找到该应用程序：

单击应用程序图标以在 Ubuntu 上运行它：

从 Ubuntu 22.04 中删除 Clementine 如果您不再需要 Clementine 的服务，可以使用以下命令将其从 Ubuntu 22.04 中完全删除：

```bash
sudo apt remove --autoremove clementine
```

### 通过 Snap 商店

您还可以通过 snap 商店安装 Clementine 音乐播放器。以下命令将在 Ubuntu 22.04 上安装该应用程序：

```bash
sudo snap install clementine
```

要运行该应用程序，请在命令终端中使用 `clementine`

从 Snap 存储中删除 Clementine 音乐播放器要从 Snap 存储中删除安装的 Clementine，请使用以下命令。

```bash
sudo snap remove clementine
```

### 结论

Clementine是一款出色的第三方应用程序，可让您轻松聆听任何音乐。
它可以通过不同的方法轻松安装在Ubuntu 22.04上。
是否通过 Ubuntu 存储库、快照存储或使用软件中心安装它完全取决于您。
在所有情况下，它都会在您的系统上成功运行。
