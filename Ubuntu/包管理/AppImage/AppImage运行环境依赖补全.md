---
标题: AppImage运行环境依赖补全
创建时间:
修改时间:
标签:
  - Ubuntu23.04
---
如果您是 AppImage 的忠实用户，并且想知道为什么它们似乎无法在 Ubuntu 23.04 中使用，本文可以为您解决这个问题。


在早期版本的 Ubuntu 中，您可以下载.appimage文件，将其标记为可执行文件（即通过文件管理器的属性对话框给予其运行权限），然后双击打开它。应用程序就会出现，并可以使用它，非常简单。

但在 Ubuntu 23.04 中，您无法这样做，因为该版本使用了一个更新版本的 [FUSE](https://docs.linuxkernel.org.cn/filesystems/fuse.html)「用户空间文件系统」接口 -> `FUSE 3`，而（大部分）AppImage 需要使用经典的 `FUSE 2` 才能运行，而旧版本已不再维护。

目前，AppImage 的开发人员和社区成员正在[讨论如何解决这个问题](https://github.com/AppImage/AppImageKit/issues/1120)，如果您感兴趣，可以去了解一下。

那么，回到正题：如何让 AppImage 在 Ubuntu 23.04 中正常工作呢？您需要安装 FUSE 2。

![Ubuntu 23.04 安装 FUSE 2](https://img.sysgeek.cn/img/2023/04/install-libfuse2.jpg)

Ubuntu 23.04 安装 FUSE 2

ibfuse2软件包已经包含在 Ubuntu 软件仓库中（大多数情况下，默认已启用），您可以在不发生任何冲突的情况下安装 FUSE 2 并与 FUSE 3 共存（不要安装 FUSE 1，有冲突。）：


```
sudo apt install libfuse2
```

安装好后，当您双击一个 AppImage 文件时，它将在一两秒后按预期运行。

---

许多受欢迎的应用程序以 AppImage 格式分发，其中包括 Kdenlive 视频编辑器、[Audacity 音频软件](https://www.sysgeek.cn/linux-best-audio-editor/)和 [USB 刷写工具 BalenaEtcher](https://www.sysgeek.cn/make-ubuntu-usb-flash-drive/)。如果您经常使用这些应用程序，那么这种解决方法很值得尝试。

