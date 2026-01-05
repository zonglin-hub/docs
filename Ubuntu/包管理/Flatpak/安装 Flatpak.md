---
标题: Markdown 标题
创建时间: 2025-10-24
修改时间: 2025-10-24
标签:
  - Flatpak
---

![Flatpak](https://img.sysgeek.cn/img/logo/flatpak-1.jpg)

Linux 世界有三种通用的打包格式，可以在任何 [Linux 发行版](https://www.sysgeek.cn/tag/linux-distro/)中运行 Snap、Flatpak 和 AppImage。虽然 Ubuntu 默认预装了 Snap，但由于其源代码不开放，大多数发行版和开发者偏向「避免」使用它。更多人倾向于使用 Fedora 的 Flatpak 打包系统。

## 安装 Flatpak

要在 **Ubuntu 18.10 （Cosmic Cuttlefish） 或更高版本**上安装 Flatpak，请打开终端应用程序并运行：

```bash
sudo apt install flatpak
```


## 安装 Flatpak 「软件中心」

有一个专门的插件可为 GNOME 软件中心添加 Flatpak 支持，以便于图形化方式安装软件包。

自 Ubuntu 20.04 起，默认的软件中心是 Snap Store，并不支持 Flatpak 集成。因此，安装下面的软件包将导致同时存在两个软件中心：一个是 Snap，另一个是 DEB。

```bash
sudo apt install gnome-software-plugin-flatpak # flatpak 软件包的「软件中心」
```

  
> [!NOTE]
> **注意：** Ubuntu 20.04 ~ 23.04 版本中将 GNOME 软件作为 Snap 分发，并在 23.10 及更高版本中将其替换为 App Center ，两者都不支持安装 Flatpak 应用程序。安装 Flatpak 插件还将安装 GNOME 软件的 deb 版本，从而在 Ubuntu 20.04 到 23.04 上同时安装两个“软件”应用程序，并在 Ubuntu 23.10 及更高版本上安装一个新的“软件”应用程序。

## 添加 Flathub 软件仓库

在 Ubuntu 中安装好 Flatpak 后，如果直接尝试安装软件包，将会提示 `No remote refs found similar to ‘flathub’` 错误。这是因为尚未添加 Flatpak 软件源，它不知道从哪儿获取应用程序。

1. Flatpak 有一个名为 `Flathub` 的中央仓库，可以在其中找到并下载许多 Flatpak 应用程序。要添加 `Flathub` 中央仓库，请在「终端」中运行如下命令：

```bash
sudo flatpak remote-add --if-not-exists flathub https://dl.flathub.org/repo/flathub.flatpakrepo
```

2. （可选）如果访问速度比较慢，可以使用 Flathub 国内镜像

由 上海交通大学 Linux 用户组 (SJTUG) 维护 flathub 的镜像源。官方教程：<https://mirror.sjtu.edu.cn/docs/flathub>

如果已经添加了 flathub 的话，就用 remote-modify 修改：

```bash
sudo flatpak remote-modify flathub --url=https://mirror.sjtu.edu.cn/flathub
```


如果没有的话，就使用 remote-add 添加：

```shell
sudo flatpak remote-add flathub https://mirror.sjtu.edu.cn/flathub/flathub.flatpakrepo
```

留意上交镜像的官方教程里有这句话：

> [!NOTE]
> Flathub 镜像是 flathub.org 的智能缓存。当您请求镜像中的资源时， 如果文件没有被镜像服务器缓存，我们会将您重定向回原站，并在后台进行缓存。也就是说，如果你要安装的软件没有在上交镜像里缓存，那就会重定向到官方的flathub。如果没有代理的话，可能会显示这个：

```vbnet
Error: Could not connect: 网络不可达
error: Failed to install com.spotify.Client: Could not connect: 网络不可达
```

遇到这个情况，要么挂代理，要么等一段时间，等上交镜像把这个包缓存下来之后，再尝试。

如果需要换回官方源的话：

```shell
sudo flatpak remote-modify flathub --url=https://flathub.org/repo
```


3. 安装并配置 Flatpak 后，请重新启动 Ubuntu 系统，以便让安装好的 Flatpak 应用程序能够在系统菜单中显示。

## 在 Ubuntu 中使用 Flatpak

以下是一些用于软件包管理的常用 Flatpak 命令和使用方法：

### 搜索软件包

您可以使用 [Flathub 网站](https://flathub.org/zh-Hans)搜索软件包，如果知道应用程序名称，可以使用以下命令：

```
flatpak search <package-name>
```

除了搜索 Flatpak 软件包之外，在其他情况下，`<package-name>` 指的是 Flatpak 软件包名称，例如 `com.brave.Browser`（上述截图中的「应用程序 ID」）。您也可以使用「应用程序 ID」中的关键单词，如 `brave`。

### 安装 Flatpak 软件包

以下是安装 Flatpak 软件包的语法：

```
flatpak install <remote-repo> <package-name>
```

我们一般都是从 Flathub 获取应用程序，所以远程仓库指定为 `flathub`：

```
flatpak install flathub <package-name>
```

在一些特殊的情况下，可以直接从开发者的软件仓库安装 Flatpak 软件包。在这种情况下，可以使用以下命令：

```
flatpak install --from https://flathub.org/repo/appstream/com.spotify.Client.flatpakref
```

### 从 flatpakref 文件安装软件包

有时候，您可能会收到应用程序的 `.flatpakref` 文件，该文件不是离线安装包，但包含了获取软件包所需的必要详细信息。

要从 `.flatpakref` 文件进行安装，请打开「终端」并运行：

```
flatpak install <path-to-flatpakref-file>
```

### 从「终端」运行 Flatpak 应用程序

通常情况下，我们会在 Ubuntu 系统菜单中打开应用程序，但也可以使用以下命令从「终端」运行 Flatpak 应用：

```
flatpak run <package-name>
```

### 列出已安装的 Flatpak 软件包

要查看系统中已安装的 Flatpak 应用程序，可以使用以下命令：

```
flatpak list
```

### 卸载 Flatpak 软件包

可以使用以下命令移除已安装的 Flatpak 软件包：

```
flatpak uninstall <package-name>
```

如果要清除不再需要的软件包和运行时，请使用以下命令：

```
flatpak uninstall --unused
```

以下是上述命令的汇总表格：

| 操作                   | 命令                                        |
| -------------------- | ----------------------------------------- |
| 搜索软件包                | flatpak search                            |
| 安装软件包                | flatpak install                           |
| 列出已安装的软件包            | flatpak list                              |
| 从 flatpakref 文件安装软件包 | flatpak install <package-name.flatpakref> |
| 卸载软件包                | flatpak uninstall                         |
| 卸载未使用的运行时和软件包        | flatpak uninstall --unused                |



## 通过 Flathub 网站查找 Flatpak 软件包

在 Linux 桌面中，通过命令行搜索 Flatpak 软件包肯定没有图形界面方便直观。我们可以通过 [Flathub 网站](https://flathub.org/zh-Hans)浏览和查找 Flatpak 应用程序，该网站提供了更多的详细信息，如验证的发布者、总下载次数等。还可以在应用程序页面底部获取它所需的命令。

### Flatpak 安装的应用文件路径

默认路径：  

```bash
ls /var/lib/flatpak/app/
```

**文件结构示例**：

```
/var/lib/flatpak/app/com.github.d4nj1.tlpui
├── current                   # 当前活动版本
│   ├── active
│   │   ├── files            # 应用主文件（二进制、资源等）
│   │   │   └── bin/tlpui    # 可执行文件位置
│   │   └── metadata         # 应用元数据
│   └── x86_64/master        # 架构和分支信息
```

### **查看应用安装路径的命令**

**列出所有已安装的 Flatpak 应用**：

```bash
flatpak list
```

2. **查看应用详情（含路径）**：
```bash
flatpak info <应用程序 ID>
```

### **访问应用文件的方法**

**用户级安装**：直接进入 

```bash
ls ~/.local/share/flatpak/
```

**系统级安装**：需 root 权限：

```bash
ls /var/lib/flatpak/app/
```
