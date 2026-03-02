---
标题: 彻底移除 Snap
创建时间: 2025-12-31
修改时间: 2025-12-31
标签:
  - Ubuntu24.04
---



## 彻底移除 Snap

在 Ubuntu 系统中，默认捆绑了 Snap 容器化软件包管理器。甚至像 Firefox、Thunderbird 和「应用商店」这样的核心应用也只提供 Snap 包。然而，并不是所有用户都喜欢 Snap。

### 为什么从 Ubuntu 中移除 Snap

尽管 Snap 能够简化应用的安装流程，但它也带来了一些争议：

- **启动缓慢**：许多 Snap 应用在首次启动时，速度要明显慢于传统软件包
- **占用空间**：每个 Snap 包都会捆绑自身的依赖库，磁盘空间占用会更高
- **控制受限**：用户对于软件包版本的选择和控制非常有限
- **缺乏透明**：`snapd` 后台服务没有开源
- **强制捆绑**：某些核心应用被 Ubuntu 强制设为 Snap-only

### 快速一键卸载 Snap

此方法适合想要快速禁用 Snap 并阻止它自动重装的用户：

1. **卸载 `snapd` 核心服务**：

```bash
sudo apt purge snapd
```

2. **防止自动重装**：

```bash
sudo apt-mark hold snapd
```

3. **更新软件包列表**：

```bash
sudo apt update
```

4. **重启系统**

### 手动彻底移除 Snap 及软件包

以下步骤会一步步清理系统中的所有 Snap 相关组件：

1. **检查已安装的 Snap 包**：

```bash
snap list
```

2. **移除 Snap 应用**：

```bash
sudo snap remove --purge firefox thunderbird firmware-updater snap-store canonical-livepatch
```

3. **移除 Snap 桌面集成和主题**：

```bash
sudo snap remove --purge gtk-common-themes gnome-42-2204 snapd-desktop-integration
```

4. **移除 Snap 核心**：

```bash
sudo snap remove --purge bare core22
```

5. **卸载 `snapd` 守护进程**：

```bash
sudo apt purge --autoremove snapd
```

6. **删除残留的 Snap 文件夹**：

```bash
rm -rf ~/snap
sudo rm -rf /snap /var/snap /var/lib/snapd /var/cache/snapd /usr/lib/snapd
```

7. **防止自动重装**：

```bash
sudo apt-mark hold snapd
```

8. **更新软件包列表**：

```bash
sudo apt update
```

9. **重启系统**

### Snap 卸载后图标删除

```bash
ls /var/lib/snapd/desktop/applications/
```

删除相关的 `.desktop` 文件以清理残留图标。

至此，你已经彻底从 Ubuntu 中卸载了 Snap。如果你还要继续使用 Firefox 和 Thunderbird，可以手动添加 mozillateam/ppa 再重新安装。