---
标题: Markdown 标题
创建时间: 2025-10-03
修改时间: 2025-10-24
标签:
  - Ubuntu
  - Waddroid
  - Android
---

# Waydroid在Ubuntu24.04上无缝集成安卓子系统


## 一、Waydroid简介

`Waydroid` 是一种基于容器的、能在常规的 `Linux` 系统上启动完整 `Android` 系统的方法，它允许你在 `Linux` 桌面环境中像运行原生应用一样运行 `Android` 应用，实现无缝的用户体验。

> 注意：`Waydroid` 只在 `Wayland` 会话管理器中工作。

## 二、准备工作

### 内核模块

`Waydroid` 需要 `binder` 模块，一般默认就有，如果是自己编译的内核，请确保编译选项勾选该模块，或使用 `DKMS` 安装

### 安装 Waydroid

其他发行版参考官方教程：[https://docs.waydro.id/usage/install-on-desktops](https://docs.waydro.id/usage/install-on-desktops#ubuntu-debian-and-derivatives)
晚上下载最好，SourceForge下载加速
## 三、初始化Waydroid

### 普通初始化：

```bash
sudo waydroid init 
```

### 启动并启用服务：

```bash
sudo systemctl enable --now waydroid-container
```

你也可以自己在这个页面单独下载镜像：[https://sourceforge.net/projects/waydroid/](https://sourceforge.net/projects/waydroid/)

注意也要下载对应的Vendor镜像，下载下来的系统镜像和Vendor镜像默认放在`/var/lib/waydroid/images`下，并命名为`system.img`和`vendor.img`

## 四、用法

```bash
waydroid session stop                      # 停止 Waydroid 会话
waydroid session start                     # 启动 Waydroid 会话
sudo waydroid container start              # 启动 Waydroid 容器
waydroid show-full-ui                      # 启动GUI 
sudo waydroid shell                        # 启动Shell
waydroid app install $path_to_apk          # 安装应用
waydroid app launch $package-name          # 运行应用
waydroid app list                          # 获取应用列表
```


### 开启应用独立窗口

在会话启动后运行：

```bash
waydroid prop set persist.waydroid.multi_windows true
```

然后重启会话：

```bash
waydroid session stop
waydroid session start
```

### 设置窗口宽度为 600，高度为 800

```bash
waydroid prop set persist.waydroid.width 600
waydroid prop set persist.waydroid.height 800

#### 重置为默认大小
waydroid prop set persist.waydroid.width ""
waydroid prop set persist.waydroid.height ""
```

> [!NOTE]
> 在修改窗口大小后，需要重新启动 Waydroid 容器以使更改生效。可以使用以下命令停止并重新启动容器：


### 多窗口模式

如果需要开启多窗口模式，可以使用以下命令：

```bash
waydroid prop set persist.waydroid.multi_windows true
```




### 屏幕密度

如果需要调整屏幕密度，可以编辑 _/var/lib/waydroid/waydroid_base.prop_ 文件，添加或修改 _ro.sf.lcd_density_ 属性。例如：

```bash
sudo nano /var/lib/waydroid/waydroid_base.prop
```

添加或修改以下行 `ro.sf.lcd_density=240`

保存并退出（Ctrl+O, Ctrl+X）调整屏幕密度后，同样需要重新启动 Waydroid 容器。通过这些步骤，你可以根据需要调整  Waydroid 窗口的大小和显示效果。

## 五、扩展脚本

推荐使用这个项目：<https://github.com/casualsnek/waydroid_script>

性能优化：推荐在 `AMD` CPU上安装 `libndk`，`Intel` CPU上安装 `libhoudini`

如果需要安装 Gapps（Google 服务套件）或 ARM 转译，请先停止 Waydroid 会话和服务：

```bash
sudo waydroid session stop
sudo systemctl stop waydroid-container
sudo systemctl restart waydroid-container.service
```

- 按照 [Waydroid 官方文档](https://docs.waydro.id/faq/google-play-certification) 提交 Android ID 后，重启 Waydroid 即可完成 Google Play 的认证。

通过以上步骤，可以有效解决 Waydroid 的网络问题并优化其功能。


  




