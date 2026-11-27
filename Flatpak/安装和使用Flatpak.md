---
标题: 安装和使用 Flatpak
创建时间: 2025-10-24
修改时间: 2025-10-27
标签:
  - Flatpak
  - Ubuntu
---

![Flatpak](https://img.sysgeek.cn/img/logo/flatpak-1.jpg)

## 简介

Linux 世界有三种通用的打包格式，可以在任何 [Linux 发行
版](https://www.sysgeek.cn/tag/linux-distro/)中运行 Snap、Flatpak 和 AppImage。
虽然 Ubuntu 默认预装了 Snap，但由于其源代码不开放，大多数发行版和开发者偏向「避
免」使用它。更多人倾向于使用 Fedora 的 Flatpak 打包系统。

## 一、先理清两个核心概念：System 与 User

Flatpak 的应用和仓库分为两个独立的“安装域”，理解这一点是后续所有操作（尤其是你需
要的 system 清理）的前提。

| | __System 级__ | __User 级__ |
|---|---|---|
| 作用范围 | 全系统所有用户 | 仅当前用户 |
| 权限要求 | 需要 `sudo` | 无需 root |
| 应用存储 | `/var/lib/flatpak/app/` | `~/.local/share/flatpak/app/` |
| 仓库配置 | `/var/lib/flatpak/repo/config` | `~/.local/share/flatpak/repo/config` |
| 默认行为 | 不加 `--user` 或 `--system` 时，多数命令默认作用于 system | 需显式加 `--user` |

关键点：

- 两个域的 __仓库是分开配置的__。你在 system 级添加了 flathub，不代表 user 级也
  有；反之亦然。
- `flatpak list` 等命令会 __合并显示__ 两个级别的结果。同名应用如果同时存在于两
  级，user 级会覆盖 system 级。
- 卸载时如果 __不加级别参数__，而同一应用同时存在于 system 和 user，命令会报错；
  加上 `--system` 或 `--user` 才能明确目标。


## 二、安装与仓库配置

### 2.1 安装 Flatpak

- __Debian/Ubuntu__：

    ```bash
    sudo apt install flatpak
    ```

其他发行版按对应包管理器安装即可（Fedora `dnf`，Arch `pacman`，openSUSE
`zypper`）。

图形化软件中心插件（可选，注意会引入 DEB 版 GNOME Software）：

```bash
sudo apt install gnome-software-plugin-flatpak # flatpak 软件包的「软件中心」
```

### 2.2 添加 Flathub 仓库

__推荐为当前用户添加__（无需 root，日常使用最方便）：

```bash
flatpak remote-add --if-not-exists --user flathub \
  https://dl.flathub.org/repo/flathub.flatpakrepo
```

> [!NOTE]
> 
> 如果需要 `Flathub` 系统可用，就使用 `--system` 替换 `--user` 皆可

### 2.3 切换国内镜像（可选）

如果访问速度比较慢，可以使用 Flathub 国内镜像。确认仓库已存在后，用
`remote-modify` 替换 URL。__注意：要作用于哪个级别，就加对应参数__；不加参数时默
认改 system。

__中科大镜像__（目前相对稳定）：

```bash
# 修改当前用户的仓库
flatpak remote-modify --user flathub \
  --url=https://mirrors.ustc.edu.cn/flathub
```

__上海交大镜像__（可用但存在不稳定情况，酌情使用）：

```bash
flatpak remote-modify --user flathub \
  --url=https://mirror.sjtu.edu.cn/flathub
```

__恢复官方源__：

```bash
flatpak remote-modify --user flathub \
  --url=https://dl.flathub.org/repo
```

> [!NOTE]
> 
> 或直接编辑flatpak配置文件：`~/.local/share/flatpak/repo/config` 修改里面的软件
> 仓库地址即可。

查看仓库当前详情：

```bash
flatpak remotes --show-details
```

## 三、日常使用命令

| 操作 | 命令 |
|---|---|
| 搜索 | `flatpak search <关键词>` |
| 安装 | `flatpak install --user flathub <应用ID>` |
| 从 `.flatpakref` 安装 | `flatpak install --user <文件路径或URL>` |
| 运行 | `flatpak run <应用ID>` |
| 查看已安装 | `flatpak list`（合并）/ `--user` / `--system` 分别查看 |
| 查看应用详情 | `flatpak info <应用ID>` |
| 更新全部 | `flatpak update`（加 `--user` 或 `--system` 限定级别） |

__卸载单个应用__：

```bash
# 卸载但保留用户数据（~/.var/app/<ID> 目录仍在）
flatpak uninstall --user <应用ID>

# 卸载并删除该应用的用户数据与权限记录
flatpak uninstall --user --delete-data <应用ID>
```

`--delete-data` 删除的是 `~/.var/app/<应用ID>` 以及存储中属于该应用的条目。__对
与 `--system` 的应用，用户数据同样位于 `~/.var/app/`__，不会因为应用装在 system
级就误删。


## 四、清理与彻底卸载

### 4.1 常规清理：只清无用运行时

```bash
# 清理当前用户级别下不再被任何应用依赖的运行时
flatpak uninstall --user --unused

# 如 system 级也有安装，一并清理
sudo flatpak uninstall --system --unused
```

`--unused` 会自动找出并删除无人使用的 runtime 和 extension，是日常维护中安全且推
荐的做法。

### 4.2 彻底清理整个 System 级（对应你的原始需求）

如果目标是 __完全清空 system 级、保留 user 级不受影响__，按以下顺序执行：

__① 卸载 system 级所有应用__

```bash
sudo flatpak uninstall --system --all
```

如果希望同时删除这些应用在 `~/.var/app/` 下的用户数据（永久删除配置、存档、缓
存，操作前确认）：

```bash
sudo flatpak uninstall --system --all --delete-data
```

__② 删除 system 级 flathub 仓库__

```bash
sudo flatpak remote-delete --system flathub
```

__③ 清理 system 级残留运行时__

```bash
sudo flatpak uninstall --system --unused
```

__④ 验证__

```bash
flatpak remotes --system      # 应无输出
flatpak list --system          # 应无输出
```

执行完毕后，`flatpak remotes --show-details` 中应只剩 user 级的flathub；`flatpak
list --user` 完全正常，日常使用不受影响。

### 4.3 补充：手动兜底清理（可选）

如果怀疑有“应用已卸载、但 `~/.var/app/` 下残留了孤立数据目录”的情况，可以手动检
查并删除对应目录：

```bash
ls ~/.var/app/
# 对照 flatpak list 确认哪些目录已无对应应用，再删除
rm -rf ~/.var/app/<孤立的ID目录>
```

这一步不是必须的，但可以作为彻底清理的最后手段。


## 五、进阶管理：权限、修复与诊断

### 5.1 权限管理：Flatseal

Flatpak 应用运行在沙盒中，默认权限可能过宽或过窄。__Flatseal__ 是管理这些权限的
图形化工具：

```bash
flatpak install --user flathub com.github.tchx84.Flatseal
```

使用方式：打开 Flatseal → 左侧选择应用 → 在右侧开关文件系统、设备、socket 等权限
→ __重启该应用__使更改生效。改错了可以点 Reset 恢复默认。

Flatseal 本质上是在操作 Flatpak 的 __overrides__ 后端，与命令行 `flatpak
override` 等价；它不修改应用本身的打包权限，而是添加用户级的覆盖规则。

### 5.2 修复安装

当出现应用启动异常、库缓存损坏、更新报错等情况时，可以运行：

```bash
# 修复 system 级（需 root）
sudo flatpak repair

# 修复当前用户级
flatpak repair --user
```

`repair` 会扫描本地引用、清理无效对象，并从远端重新拉取缺失或损坏的数据。

### 5.3 一个实用的检查习惯

定期执行以下组合，可以保持 Flatpak 环境干净：

```bash
flatpak update --user
flatpak uninstall --user --unused
```

如果 system 级也在使用，对应加上 `sudo` 和 `--system` 即可。清理前若想确认具体会
删掉哪些运行时，可以先跑 `flatpak uninstall --user --unused --dry-run`（如版本支
持）做预演。


附：路径速查
---

| 内容 | 路径 |
|---|---|
| System 级应用 | `/var/lib/flatpak/app/` |
| System 级仓库配置 | `/var/lib/flatpak/repo/config` |
| User 级应用 | `~/.local/share/flatpak/app/` |
| User 级仓库配置 | `~/.local/share/flatpak/repo/config` |
| 应用用户数据（不分级别） | `~/.var/app/<应用ID>/` |

附：通过 Flathub 网站查找 Flatpak 软件包
---

在 Linux 桌面中，通过命令行搜索 Flatpak 软件包肯定没有图形界面方便直观。我们可以
通过 [Flathub 网站](https://flathub.org/zh-Hans)浏览和查找 Flatpak 应用程序，该
网站提供了更多的详细信息，如验证的发布者、总下载次数等。还可以在应用程序页面底部
获取它所需的命令。

附：推荐应用
---

- 微信 `com.tencent.WeChat`
- 开源笔记软件：Joplin `net.cozic.joplin_desktop`
- 思维导图软件：Minder 支持导出xmind格式 `com.github.phase1geo.minder`
- Markdown编辑器：Apostrophe `org.gnome.gitlab.somas.Apostrophe`
- AppImage管理工具：GearLever `it.mijorus.gearlever`
- Flatpak权限管理工具：Flatseal `com.github.tchx84.Flatseal`
- Flatpak残留清理器：Flatsweep `io.github.giantpinkrobots.flatsweep`
- 界面来控制复杂的 Flatpak 选项 `io.github.flattool.Warehouse`
- Ptyxis 是一个GNOME 终端`app.devsuite.Ptyxis`
- 图片快速编辑器`org.gnome.Loupe`
- 允许您检查控制系统资源的利用率 `net.nokyan.Resources`

附：问题排查：
---

#### 1）安装软件时提示

```bash
请注意 

'/var/lib/flatpak/exports/share'
'/home/dalong/.local/share/flatpak/exports/share'

目录不在由 XDG_DATA_DIRS 环境变量设置的搜索路径中，因此通过 Flatpak
安装的应用在会话重启前可能不会出现在您的桌面。
```

__解决方法__：

修改你的 shell 配置文件（如 .bashrc 或 .zshrc），将 Flatpak 的共享目录添加到
XDG_DATA_DIRS 环境变量中：

> 直接修改 ~/.bashrc，在最后添加一行：

```bash
export XDG_DATA_DIRS="$XDG_DATA_DIRS:/var/lib/flatpak/exports/share:/home/dalong/.local/share/flatpak/exports/share"
```

> 注意：把我的目录“/home/dalong”替换成你的用户目录  
> 警告：这个变量如果配置在/etc/bash.bashrc会出现ssh工具无法使用flatpak search命
> 令的情况

添加这行代码后，你需要重新加载配置文件或重新启动终端。

#### 2）依赖变动和用户权限导致的更新问题

安装了一个叫nook的软件，原来依赖于freedesktop 22.08，后来freedesktop 22.08不再维
护了，nook软件新版本不再依赖22.08，我执行flatpak update想更新nook时会尝试卸载
22.08，此时报错：  
`Failed to get revokefs-fuse socket from system-helper: Flatpak system operation
GetRevokefsFd not allowed for user`  

__解决方法__：  

更新需要卸载依赖22.08，我的仓库配置没有指定--user（没指定则为system级别仓库），
操作需要系统权限。  
解决方案：su切换root用户再更新即可

#### 3) ssh工具（如tabby）执行flatpak search命令报错

flatpak search xxxapp出现报错

```bash
: Could not parse XML data: Entity: line 1: parser error : Start tag expected, '<' not found
�
^
找不到匹配项
```

__解决方法__：

参考上文“问题1”，变量问题，修改 ~/.bashrc，在最后添加一行：

```bash
export XDG_DATA_DIRS="$XDG_DATA_DIRS:/var/lib/flatpak/exports/share:/home/dalong/.local/share/flatpak/exports/share"
```