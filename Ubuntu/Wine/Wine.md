---
标题: 安装和使用 Wine 运行 Windows 应用
创建时间: 2025-12-24
修改时间: 2025-12-29
标签:
---
![[wine9.avif]]
# 安装和使用 Wine 运行 Windows 应用

Wine（也就是 [WineHQ](https://www.winehq.org/)）是一款 Windows 兼容层，能让你在类 Unix 操作系统（如 Linux）上直接运行 Windows 应用程序，而无需安装双系统或借助虚拟机。

[Wine 10.x](https://www.sysgeek.cn/wine-10/) 是它的最新版本，带来了诸多改进。例如，支持高 DPI 缩放和 [Vulkan 视频解码器](https://www.sysgeek.cn/vulkan-vs-directx-12/)，提升线程优先级并引入动态 Wow64 模式，加入 Wayland 剪贴板支持，以及增强了对 [ARM64](https://www.sysgeek.cn/aarch64/) 的兼容性等。

接下来，本文将详细介绍如何通过 [WineHQ 官方仓库](https://wiki.winehq.org/Ubuntu)，在 Ubuntu 上安装、配置和使用 Wine 10.x。

以下步骤适用于 Ubuntu 20.04、22.04、24.04 LTS 和 Ubuntu 24.10 版本。

## 第 1 步：在 Ubuntu 上安装 Wine 10

### 1.1 准备工作

1. 在安装 Wine 10 之前，先通过「终端」更新系统中的软件包：


```bash
sudo apt update  # 更新软件包列表
sudo apt upgrade # 升级软件包
```

2. 接着，安装必要的依赖工具：


```bash
# dirmngr：GnuPG 的密钥管理守护进程
# ca-certificates：包含 Mozilla 的 CA 证书集合
# curl：命令行数据传输工具
# software-properties-common：软件源管理工具集 `add-apt-repository` 命令，用于轻松添加 PPA（个人软件包存档）
# apt-transport-https：使 APT 支持 HTTPS 协议传输
sudo apt install dirmngr ca-certificates curl software-properties-common apt-transport-https
```

3. 启用 32 位系统架构支持，增强对游戏和应用程序的兼容性：


```bash
sudo dpkg --add-architecture i386 # 在 Ubuntu 上启用 32 位支持
```


### 1.2 添加 WineHQ 官方仓库

> 清华大学开源软件镜像站 [Wine builds 软件仓库](https://mirrors-i.tuna.tsinghua.edu.cn/help/wine-builds/)

1. 首先，导入 WineHQ 的 GPG 密钥：


```bash
curl -s https://dl.winehq.org/wine-builds/winehq.key | sudo gpg --dearmor | sudo tee /usr/share/keyrings/winehq.gpg > /dev/null
```

2. 然后，添加 WineHQ 官方仓库：


```bash
echo deb [signed-by=/usr/share/keyrings/winehq.gpg] http://dl.winehq.org/wine-builds/ubuntu/ $(lsb_release -cs) main | sudo tee /etc/apt/sources.list.d/winehq.list
```


### 1.3 在 Ubuntu 上安装 Wine 10

1. 首先，在「终端」中更新软件包列表：


```
sudo apt update
```

2. 根据你的需求，选择安装不同版本的 Wine 10：

稳定版`winehq-stable`的小版本更新最慢，推荐安装预发布版`winehq-staging`或开发版`winehq-devel`。

- **预发布版**：功能和稳定性介于稳定版和开发版之间。


```
sudo apt install --install-recommends winehq-staging
```

- **开发版**：包含最新功能，但可能不稳定，适合开发者或高级用户。


```
sudo apt install --install-recommends winehq-devel
```


3. 安装完成后，检查 Wine 版本以确认安装成功：

```
wine --version
```


## 第 2 步：在 Ubuntu 上初始化 Wine 环境

### 2.1 配置 Wine 环境

1. 成功安装 Wine 后，通过以下命令开始设置 Wine 的运行环境，包括安装 wine-mono 组件以支持 .NET 应用：

```bash
winecfg
```

2. 根据提示安装 Mono 及相关依赖。

![[Snipaste_2025-12-27_22-48-36.png]]

### 2.2 配置 Wine 设置

1. 配置完成后，会自动弹出「Wine 设置」对话框。你可以选择要模拟的 Windows 版本，默认是「Windows 10」，也可以根据需要自行更改。

2. 整「音效」、「显示」等设置，完成后点击「确定」关闭对话框。

### 2.3 安装 Winetricks（优化 Wine 使用体验）

Winetricks 是一个辅助脚本，可以帮助你轻松安装和管理 Windows 应用程序和库，从而优化 Wine 的使用体验。

1. 在「终端」中运行以下命令安装 winetricks：

```bash
sudo apt install winetricks
```

2, 安装完成后，可以使用 Winetricks 安装必要的 Windows 组件，例如：

一些应用和组件需要配置 32 位应用和中文支持，后续会有介绍。

```bash
winetricks vcrun2022 # 安装 Visual C++ 运行库
winetricks allfonts corefonts cjkfonts # 安装常用字体，包括中文字体
winetricks d3dx9 d3dx10 # 安装 DirectX 提升兼容性和游戏性能
```


## 第 3 步：使用 Wine 运行 Windows 应用程序

要运行 Windows 的二进制文件，请右键点击文件并选择「打开方式」，然后选择 Wine。以下是使用 Wine 在 Ubuntu 中安装和运行 Notepad++ 的示例：

1. [下载 Noetpad ++](https://notepad-plus-plus.org/downloads/) 安装文件。

2. 右键点击安装文件，选择「打开方式」。

3. 选择「Wine Windows Program Loader」，然后点击「打开」。

4. 使用 Wine 运行 Windows 应用程序


## 在 Ubuntu 上管理 Wine 环境

### 管理 Wine 前缀（应用程序环境）

Wine 使用前缀（Prefix）来隔离不同的应用程序环境。默认情况下，主前缀路径为`~/.wine`。如果需要创建一个新的自定义环境，可以使用以下命令：

```bash
WINEPREFIX=~/.custom_wine_prefix winecfg
```

将`~/.custom_wine_prefix`替换为要使用的目录。执行命令后，会创建一个新的 Wine 环境，并自动打开「Wine 配置」对话框。

### 配置 32 位应用支持

Wine 默认支持 64 位 Windows 应用。如果要运行 32 位应用程序，可以通过以下命令设置一个新的 32 位环境：

```bash
WINEARCH=win32 WINEPREFIX=~/.wine32 winecfg
```

此命令会创建一个专门用于 32 位应用程序的 Wine 前缀。

### 配置中文支持

要创建一个支持中文的 Wine 前缀，请带上`LC_ALL=zh_CN.UTF-8`参数：

```bash
WINEPREFIX=/home/billyfu/wine_prefix LC_ALL=zh_CN.UTF-8 winecfg
```

### 浏览 Wine 应用程序数据库

Wine 应用程序数据库 (AppDB) 提供了各种应用程序的兼容性信息和优化配置建议。你可以访问 [Wine AppDB](https://appdb.winehq.org/) 了解更多详细信息，以及其他用户分享的经验。

## 从 Ubuntu 中移除 Wine

如果不再需要 Wine，可以按以下步骤从 Ubuntu 中卸载：

1. 卸载 Wine：

```bash
sudo apt remove winehq-staging # 卸载预发布版
sudo apt remove winehq-devel # 卸载开发版
```

2. 移除 WineHQ 仓库：

```bash
sudo rm /etc/apt/sources.list.d/winehq.list
```

3. 删除 GPG 密钥：

```bash
sudo rm /usr/share/keyrings/winehq.gpg
```

## 常见问题解答

**「Wine 设置」中出现中文乱码和方框怎么解决？**

![解决「Wine 设置」中文乱码](https://img.sysgeek.cn/img/2024/01/ubuntu-wine-p13.jpg)

解决「Wine 设置」中文乱码

- 请确保安装了中文字体：


```bash
winetricks cjkfonts
```

- Wine 的字体渲染可能存在问题，安装以下 Windows 库可以解决一些奇葩问题：


```bash
winetricks riched20 riched30
```

- 在 Prefix 设置中加上`LC_ALL=zh_CN.UTF-8`参数。



