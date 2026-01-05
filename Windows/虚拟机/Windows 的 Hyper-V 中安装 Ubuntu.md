## Hyper-V

Hyper-V 是 Windows 专业版专属功能，但大多数（除商业本）品牌机内置的 Windows 都是家庭版。只能通过命令开启，方法如下：

__Windows 专业版请直接阅读启用 Hyper-V 部分__

### 安装 Hyper-V

在桌面空白处右键-新建-文本文档，命名为 `hyper-v.cmd`

> [!NOTE]  
> 必须开启显示文件扩展名选项，否则无效

单击这个文件，右键-显示更多选项-编辑，如图1-3

```powershell
PS C:\Users\zonglin> cat hyper-v.cmd
pushd "%~dp0"
dir /b %SystemRoot%\servicing\Packages\*Hyper-V*.mum >hyper-v.txt
for /f %%i in ('findstr /i . hyper-v.txt 2^>nul') do dism /online /norestart /add-package:"%SystemRoot%\servicing\Packages\%%i"
del hyper-v.txt
Dism /online /enable-feature /featurename:Microsoft-Hyper-V-All /LimitAccess /ALL
```

以管理员权限运行 `gsudo ./hyper-v.cmd` 等待加载完成即可，成功安装后，输入 `y` 并回车即可

### 启用 Hyper-V

点击搜索按钮，输入控制面板，并点击打开

![](https://pic2.zhimg.com/v2-e9928b035f582f721b125f7115d1c80d_1440w.jpg)

2-1

b.点击程序与功能，如图2-2

![](https://picx.zhimg.com/v2-a40268b5f21d4bb6e65fc0ad8f728a45_1440w.jpg)

2-2

c.点击启用或关闭Windows功能，如图2-3

![](https://pic1.zhimg.com/v2-5b2fd83044a6f16a42eef63b2ab672d2_1440w.jpg)

2-3

d.勾选Hyper-V，Windows虚拟机监控程序平台和虚拟机平台，如图2-4，2-5

![](https://pic3.zhimg.com/v2-b43728f010e90eea5d05c896788c931a_1440w.jpg)

2-4

![](https://pic2.zhimg.com/v2-6030b4b694330c81c88f454b9205c3b9_1440w.jpg)

2-5

e.点击确定，如图2-6

![](https://pica.zhimg.com/v2-6a0117ffccbccd3d6124b74ef92a0eac_1440w.jpg)

2-6

f.点击不重新启动即可

### Hyper-V 虚拟机无法启动提示 Start Pxe over IPv4 解决方法

最近Win10安装了后，创建了虚拟机。启动的时候报错“”的错误代码。一直进入不了系统，也是第一次使用微软的之前一直使用的是。怎么回事呢，经过一番度娘查询，发现是设置不当造成的。

> 错误代码

![](https://i0.hdslb.com/bfs/article/ebf308c86538c75064febf7966f0fcf9824b452a.png@1192w.webp)

一直卡在这个画面一分钟左右，会弹出这个页面 使用了微软的Hyper-V的第二代

> 解决方法

![](https://i0.hdslb.com/bfs/article/bcab5a80adf8a3ca90e19798af2a0b5581f5fc7c.png@1192w.webp)

![](https://i0.hdslb.com/bfs/article/d6d6a17a46810f714d03db33eb01852c9aea1d8a.png@1192w.webp)

![](https://i0.hdslb.com/bfs/article/50e12e0c465d74b62a1a2ae6365e251ebe76a683.png@1192w.webp)

把给取消勾选状态，要在虚拟机关机的情况下才能进行操作。不然无法修改任何信息。修改完成后点击保存或者应用设置。然后重新启动虚拟机，这时候就能进入正常的运行状态。
