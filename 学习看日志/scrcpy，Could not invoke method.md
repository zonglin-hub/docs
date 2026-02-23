```java
zonglin@huawei-crem-wxx9:~$ scrcpy
scrcpy 1.25 <https://github.com/Genymobile/scrcpy>
/usr/share/scrcpy/scrcpy-server: 1 file pushed, 0 skipped. 310.9 MB/s (41650 bytes in 0.000s)
[server] INFO: Device: LENOVO TB320FC (Android 15)
[server] ERROR: Could not invoke method
java.lang.NoSuchMethodException: android.content.IClipboard$Stub$Proxy.addPrimaryClipChangedListener [interface android.content.IOnPrimaryClipChangedListener, class java.lang.String, class java.lang.String, int]
	at java.lang.Class.getMethod(Class.java:2950)
	at java.lang.Class.getMethod(Class.java:2450)
	at com.genymobile.scrcpy.wrappers.ClipboardManager.getAddPrimaryClipChangedListener(ClipboardManager.java:128)
	at com.genymobile.scrcpy.wrappers.ClipboardManager.addPrimaryClipChangedListener(ClipboardManager.java:138)
	at com.genymobile.scrcpy.Device.<init>(Device.java:100)
	at com.genymobile.scrcpy.Server.scrcpy(Server.java:64)
	at com.genymobile.scrcpy.Server.main(Server.java:334)
	at com.android.internal.os.RuntimeInit.nativeFinishInit(Native Method)
	at com.android.internal.os.RuntimeInit.main(RuntimeInit.java:373)
[server] ERROR: Exception on thread Thread[main,5,main]
java.lang.AssertionError: java.lang.NoSuchMethodException: android.view.SurfaceControl.createDisplay [class java.lang.String, boolean]
	at com.genymobile.scrcpy.wrappers.SurfaceControl.createDisplay(SurfaceControl.java:83)
	at com.genymobile.scrcpy.ScreenEncoder.createDisplay(ScreenEncoder.java:278)
	at com.genymobile.scrcpy.ScreenEncoder.internalStreamScreen(ScreenEncoder.java:84)
	at com.genymobile.scrcpy.ScreenEncoder.streamScreen(ScreenEncoder.java:74)
	at com.genymobile.scrcpy.Server.scrcpy(Server.java:100)
	at com.genymobile.scrcpy.Server.main(Server.java:334)
	at com.android.internal.os.RuntimeInit.nativeFinishInit(Native Method)
	at com.android.internal.os.RuntimeInit.main(RuntimeInit.java:373)
Caused by: java.lang.NoSuchMethodException: android.view.SurfaceControl.createDisplay [class java.lang.String, boolean]
	at java.lang.Class.getMethod(Class.java:2950)
	at java.lang.Class.getMethod(Class.java:2450)
	at com.genymobile.scrcpy.wrappers.SurfaceControl.createDisplay(SurfaceControl.java:81)
	... 7 more
ATTENTION: default value of option vblank_mode overridden by environment.
INFO: Renderer: opengl
INFO: OpenGL version: 4.6 (Compatibility Profile) Mesa 25.2.8-0ubuntu0.24.04.1
INFO: Trilinear filtering enabled
INFO: Initial texture: 1600x2560
WARN: Device disconnected
WARN: Killing the server...

```

从错误日志来看，你遇到的这个问题主要是因为 scrcpy 版本（1.25）与设备的 Android 系统版本（Android 15）不兼容。

🧐 问题分析

错误信息中的 java.lang.NoSuchMethodException 明确指出，scrcpy 服务端脚本试图调用 Android 系统里的一些方法，但这些方法在 Android 15 上已经不存在或已被修改。这通常发生在较新的系统版本中，因为旧版工具未能及时适配系统 API 的变化。

具体有两个关键错误：

1. Clipboard 相关方法缺失
2. SurfaceControl.createDisplay 方法缺失——这是导致无法创建投屏画面的直接原因。

✅ 解决方案

要解决这个问题，升级 scrcpy 到最新版本是最直接有效的方法。新版本通常会跟进最新的 Android 系统变更。

方法一：通过包管理器升级（推荐）

如果你是通过包管理器（如 apt）安装的，可以尝试更新：

```bash
# 更新软件源并升级 scrcpy
sudo apt update
sudo apt upgrade scrcpy
```

不过，Ubuntu 24.04 默认仓库中的 scrcpy 版本可能不是最新的，如果升级后问题依旧，请使用方法二。

方法二：使用官方最新预编译包

1. 访问 [scrcpy][scrcpy_Github] 的 官方发布页面，[Scrcpy-显示并控制你的 Android 设备][Scrcpy_Docs]
2. 下载适用于 Linux 的最新 .tar 文件。
3. 解压后，直接运行其中的 scrcpy 可执行文件即可，无需卸载旧版本。

[scrcpy_Github]: https://github.com/Genymobile/scrcpy
[Scrcpy_Docs]: https://scrcpyapp.org/

方法三：使用第三方 PPA（最便捷）

可以添加维护更及时的 PPA 来获取最新版：

```bash
# 添加 PPA
sudo add-apt-repository ppa:zhangsongcui3371/scrcpy
# 更新软件源
sudo apt update
# 安装或升级 scrcpy
sudo apt install scrcpy
```

🔧 补充检查

如果升级后仍然有问题，可以额外确认以下几点：

· adb 状态：执行 adb devices 确保设备已正常授权连接。
· 设备设置：部分设备（如小米、华为等）的“开发者选项”中，可能需要额外开启“USB调试（安全设置）”或“禁止权限监控”之类的选项，以允许模拟点击等操作。

如果升级到最新版本后问题依然存在，或者在这个过程中遇到新的错误提示，可以随时再告诉我。