# Windows 修改用户名

需求：修改 `C:\Users\RUOK` 用户名

实现步骤如下：

(假设原始用户名为 RUOK；需要改成 Lee)

- <kbd>Win + R</kbd> 打开「运行」窗口，输入。`regedit`，回车打开注册表；

- 定位到`HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Windows NT\CurrentVersion\ProfileList；`

- 在左边的框里挨个检查 Profilelist 中的每一项，找到包含 ProfileImagePath 项并且其值为 `C:\Users\RUOK` 的那个 profile;

- 找到之后再双击 ProfileImagePath，将 RUOK 改为 Lee，确定后关闭注册表编辑器；

    重启电脑；然后在弹出的提示框点击关闭；（此时是使用的是 temp 临时账户登录；为的是能够有权限修改文件夹的名字）

- 到 `C:\Users` 文件夹下，把 RUOK 改成 Lee；

- 重启电脑；
