
### 扩展：Auto Move Windows
描述：当应用程序创建窗口时，将它们移动到特定的工作区。

![[Pasted image 20251213103920.png]]

### 扩展：AppIndicator and KStatusNotifierItem Support
Extension ID: appindicatorsupport@rgcjonas.gmail.com
Supported: Yes
描述：这个可以在 _Top Bar（顶栏）_ 显示后台应用图标
URL：https://extensions.gnome.org/extension/615/appindicator-support/

![[截图 2025-11-23 23-08-26.png]]


### 扩展：Coverflow Alt-Tab
Extension ID: CoverflowAltTab@palatis.blogspot.com
Supported: Yes
描述：替代 Alt-Tab 功能，以封面流形式循环切换窗口。
URL：https://extensions.gnome.org/extension/97/coverflow-alt-tab/

![[Pasted image 20251123224344.png]]



### 扩展：Compiz alike magic lamp effect
Extension ID: compiz-alike-magic-lamp-effect@hermes83.github.com
Supported: Yes
描述：神灯效果
URL：https://extensions.gnome.org/extension/3740/compiz-alike-magic-lamp-effect/

![[Pasted image 20251123225714.png]]

### 扩展：Dash to Dock
Extension ID: dash-to-dock@micxgx.gmail.com
Supported: Yes
描述：在你的 **工作区** 构建一个dock栏
URL：https://extensions.gnome.org/extension/307/dash-to-dock/

![[Pasted image 20251123225920.png]]

### 扩展：Blur my Shell
Extension ID: blur-my-shell@aunetx
Supported: Yes
描述：为GNOME Shell的不同部分添加模糊外观，包括顶部面板、破折号和概述。
URL：https://extensions.gnome.org/extension/3193/blur-my-shell/

![[Pasted image 20251123230100.png]]

### 扩展：Clipboard Indicator
Extension ID: clipboard-indicator@tudmotu.com
Supported: Yes
描述：剪贴板管理器
URL：https://extensions.gnome.org/extension/779/clipboard-indicator/

![[截图 2025-11-23 23-07-19.png]]

### 扩展：Burn My Windows
Extension ID: burn-my-windows@schneegans.github.com
Supported: Yes
描述：分解你的窗户。
URL：https://extensions.gnome.org/extension/4679/burn-my-windows/

![[Pasted image 20251123230402.png]]

### 扩展：Lock Keys
Extension ID: lockkeys@vaina.lt
Supported: Yes
描述：数字键，大小写切换提示
URL：https://extensions.gnome.org/extension/36/lock-keys/

![[截图 2025-11-23 23-06-38.png]]

### 扩展：TopHat
Extension ID: tophat@fflewddur.github.io
Supported: Yes
描述：可以在顶栏显示系统监控，自定义度挺高的，可以改显示的数据类型等，还可以显示CPU每个核心的状态
URL：https://extensions.gnome.org/extension/5219/tophat/

![[Pasted image 20251123232348.png]]

### 扩展：Removable Drive Menu
Extension ID: 
Supported: Yes
描述：用于访问和卸载可移动设备的状态菜单。
URL：https://extensions.gnome.org/extension/7/removable-drive-menu/

![[screenshot_7_H2ZqrXt.png]]

# 为什么在Gnome Tweak Tool中禁用了Shell主题？


在 Gnome Tweak 工具中，我无法更改 Shell 主题：

悬停在感叹号上会显示以下工具提示：`未启用Shell用户主题扩展`。我正在使用Ubuntu 14.10，并已安装gnome-shell-extensions。


你是真的在使用Gnome Shell，还是只是用Unity桌面的Ubuntu？ - xangua

我正在使用Gnome Shell。我成功地自己解决了这个问题。请见下方。 - miceagol


我自己解决了这个问题，而且解决方案非常简单：

1. 打开“Gnome Tweak Tool”。
2. 点击“Extensions”菜单项，将“User themes”滑块移动到“On”。
3. 关闭“Gnome Tweak Tool”，然后再次打开它。
4. 现在你应该能够在“Appearance”菜单中选择一个“Shell theme”。