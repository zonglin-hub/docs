---
标题: 安装和使用 Flatpak
创建时间: 2025-10-24
修改时间: 2025-10-24
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

| | **System 级** | **User 级** |
|---|---|---|
| 作用范围 | 全系统所有用户 | 仅当前用户 |
| 权限要求 | 需要 `sudo` | 无需 root |
| 应用存储 | `/var/lib/flatpak/app/` | `~/.local/share/flatpak/app/` |
| 仓库配置 | `/var/lib/flatpak/repo/config` | `~/.local/share/flatpak/repo/config` |
| 默认行为 | 不加 `--user` 或 `--system` 时，多数命令默认作用于 system | 需显式加 `--user` |

关键点：

- 两个域的**仓库是分开配置的**。你在 system 级添加了 flathub，不代表 user 级也
  有；反之亦然。
- `flatpak list` 等命令会**合并显示**两个级别的结果。同名应用如果同时存在于两
  级，user 级会覆盖 system 级。
- 卸载时如果**不加级别参数**，而同一应用同时存在于 system 和 user，命令会报错；
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

**推荐为当前用户添加**（无需 root，日常使用最方便）：

```bash
flatpak remote-add --if-not-exists --user flathub \
  https://dl.flathub.org/repo/flathub.flatpakrepo
```

如果确实需要全系统可用，再加上 system 级仓库：

```bash
sudo flatpak remote-add --if-not-exists --system flathub \
  https://dl.flathub.org/repo/flathub.flatpakrepo
```

### 2.3 切换国内镜像（可选）

确认仓库已存在后，用 `remote-modify` 替换 URL。**注意：要作用于哪个级别，就加对
应参数**；不加参数时默认改 system。

**中科大镜像**（目前相对稳定）：

```bash
# 修改当前用户的仓库
flatpak remote-modify --user flathub \
  --url=https://mirrors.ustc.edu.cn/flathub

# 如需同时修改 system 级
sudo flatpak remote-modify --system flathub \
  --url=https://mirrors.ustc.edu.cn/flathub
```

**上海交大镜像**（可用但存在不稳定情况，酌情使用）：

```bash
flatpak remote-modify --user flathub \
  --url=https://mirror.sjtu.edu.cn/flathub
```

**恢复官方源**：

```bash
flatpak remote-modify --user flathub \
  --url=https://dl.flathub.org/repo
```

查看仓库当前详情：

```bash
flatpak remotes --show-details
```

> 镜像的行为是“智能缓存”：已缓存的包走镜像，未缓存的会被重定向回官方源。如果遇到
> `网络不可达`，通常是该包尚未被镜像缓存，需要代理或等待缓存建立。


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

**安装时建议显式加 `--user`**，避免误装到 system 级；如果目标应用只在 system 级
有仓库，再改用 `sudo flatpak install --system flathub <ID>`。

**卸载单个应用**：

```bash
# 卸载但保留用户数据（~/.var/app/<ID> 目录仍在）
flatpak uninstall --user <应用ID>

# 卸载并删除该应用的用户数据与权限记录
flatpak uninstall --user --delete-data <应用ID>
```

`--delete-data` 删除的是 `~/.var/app/<应用ID>` 以及 Flatpak 权限存储中属于该应用
的条目。**对 system 级安装的应用，用户数据同样位于 `~/.var/app/`**，不会因为应用
装在 system 级就跑到别处。


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

如果目标是**完全清空 system 级、保留 user 级不受影响**，按以下顺序执行：

**① 卸载 system 级所有应用**

```bash
sudo flatpak uninstall --system --all
```

如果希望同时删除这些应用在 `~/.var/app/` 下的用户数据（永久删除配置、存档、缓
存，操作前确认）：

```bash
sudo flatpak uninstall --system --all --delete-data
```

**② 删除 system 级 flathub 仓库**

```bash
sudo flatpak remote-delete --system flathub
```

**③ 清理 system 级残留运行时**

```bash
sudo flatpak uninstall --system --unused
```

**④ 验证**

```bash
flatpak remotes --system      # 应无输出
flatpak list --system          # 应无输出
```

执行完毕后，`flatpak remotes --show-details` 中应只剩 user 级的flathub；`flatpak
list --user` 完全正常，日常使用不受影响。

### 4.3 补充：手动兜底清理

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

Flatpak 应用运行在沙盒中，默认权限可能过宽或过窄。**Flatseal** 是管理这些权限的
图形化工具：

```bash
flatpak install --user flathub com.github.tchx84.Flatseal
```

使用方式：打开 Flatseal → 左侧选择应用 → 在右侧开关文件系统、设备、socket 等权限
→ **重启该应用**使更改生效。改错了可以点 Reset 恢复默认。

Flatseal 本质上是在操作 Flatpak 的 **overrides** 后端，与命令行 `flatpak
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


## 附：路径速查

| 内容 | 路径 |
|---|---|
| System 级应用 | `/var/lib/flatpak/app/` |
| System 级仓库配置 | `/var/lib/flatpak/repo/config` |
| User 级应用 | `~/.local/share/flatpak/app/` |
| User 级仓库配置 | `~/.local/share/flatpak/repo/config` |
| 应用用户数据（不分级别） | `~/.var/app/<应用ID>/` |


## Flathub 使用国内镜像

如果访问速度比较慢，可以使用 Flathub 国内镜像

由 上海交通大学 Linux 用户组 (SJTUG) 维护 flathub 的镜像源。官方教
程：<https://mirror.sjtu.edu.cn/docs/flathub>

如果已经添加了 flathub 的话，就用 remote-modify 修改：

- __上海交大镜像__

  ```bash
  flatpak remote-modify flathub --url=https://mirror.sjtu.edu.cn/flathub
  ```

- __中科大镜像__

  ```bash
  flatpak remote-modify flathub --url=https://mirrors.ustc.edu.cn/flathub
  ```

- __恢复官方源__

  ```bash
  flatpak remote-modify flathub --url=https://dl.flathub.org/repo
  ```

查看flatpak仓库的详细信息

```bash
flatpak remotes --show-details
```

> [!NOTE]另一种方式：你可以直接编辑flatpak仓库配置文件：使用文本编辑器打开
~/.local/share/flatpak/repo/config（当前用户配置）或者
/var/lib/flatpak/repo/config （系统全局配置）修改里面的软件仓库地址即可。

如果没有的话，就使用 remote-add 添加：

```shell
sudo flatpak remote-add flathub https://mirror.sjtu.edu.cn/flathub/flathub.flatpakrepo
```

留意上交镜像的官方教程里有这句话：

> [!NOTE] Flathub 镜像是 flathub.org 的智能缓存。当您请求镜像中的资源时， 如果文
> 件没有被镜像服务器缓存，我们会将您重定向回原站，并在后台进行缓存。也就是说，如
> 果你要安装的软件没有在上交镜像里缓存，那就会重定向到官方的flathub。如果没有代
> 理的话，可能会显示这个：

```vbnet
Error: Could not connect: 网络不可达
error: Failed to install com.spotify.Client: Could not connect: 网络不可达
```

遇到这个情况，要么挂代理，要么等一段时间，等上交镜像把这个包缓存下来之后，再尝
试。

1. 安装并配置 Flatpak 后，请重新启动 Ubuntu 系统，以便让安装好的 Flatpak 应用程
   序能够在系统菜单中显示。


## 通过 Flathub 网站查找 Flatpak 软件包

在 Linux 桌面中，通过命令行搜索 Flatpak 软件包肯定没有图形界面方便直观。我们可以
通过 [Flathub 网站](https://flathub.org/zh-Hans)浏览和查找 Flatpak 应用程序，该
网站提供了更多的详细信息，如验证的发布者、总下载次数等。还可以在应用程序页面底部
获取它所需的命令。

## Flatpak 安装的应用文件路径

### 1. `~/.local/share/flatpak/`

- **作用**：这是当前用户的**个人 Flatpak 应用目录**，所有安装在该目录下的应用仅
  对当前用户可见，无需超级用户权限。
- **包含内容**：通常包含以下子目录：
  - `app/`：存放用户级安装的应用（例如 `org.mozilla.firefox/` 等）。
  - `runtime/`：存放应用依赖的运行时环境。
  - `repo/`：Flatpak 使用的 OSTree 本地仓库，存储应用和运行时的对象数据。
  - `overrides/`：用户自定义的应用覆盖配置。
- **适用场景**：用户希望只为自己的账户安装应用，或没有系统管理员权限时使用。

### 2. `/var/lib/flatpak/app/`

- **作用**：这是**系统级 Flatpak 应用目录**，所有用户共享该目录下安装的应用，通
  常需要 `root` 权限才能修改（安装、更新、删除）。
- **包含内容**：`/var/lib/flatpak/` 的结构与用户级类似，其中：
  - `app/`：存放系统级安装的应用（每个应用对应一个子目录，如
    `org.gnome.gedit/`）。
  - 其他子目录如 `runtime/`、`repo/` 等也存在于该根目录下。
- **适用场景**：管理员为系统所有用户统一安装应用，节省磁盘空间并集中管理。

## 推荐应用

- 微信  
    `flatpak install flathub com.tencent.WeChat`
- 开源笔记软件：Joplin  
    `flatpak install flathub net.cozic.joplin_desktop`
- 思维导图软件：Minder 支持导出xmind格式  
    `flatpak install flathub com.github.phase1geo.minder`
- Markdown编辑器：Apostrophe  
    `flatpak install flathub org.gnome.gitlab.somas.Apostrophe`
- AppImage管理工具：GearLever  
    `flatpak install flathub it.mijorus.gearlever`
- Flatpak权限管理工具：Flatseal  
    `flatpak install flathub com.github.tchx84.Flatseal`
- Flatpak残留清理器：Flatsweep  
    `flatpak install flathub io.github.giantpinkrobots.flatsweep`
- Warehouse提供了一个简单的用户界面来控制复杂的 Flatpak 选项，而且完全无需借助命
  令行。`flatpak install flathub io.github.flattool.Warehouse`
- Ptyxis 是一个专注于容器世界中易用性的 GNOME 终端 
  `flatpak install flathub app.devsuite.Ptyxis`
- 探索图片，查看其元数据，并通过裁剪、旋转或翻转进行快速编辑 
  `flatpak install flathub org.gnome.Loupe`
- 资源允许您检查系统资源的利用率，并控制正在运行的进程和应用程序。 
  `flatpak install flathub net.nokyan.Resources`