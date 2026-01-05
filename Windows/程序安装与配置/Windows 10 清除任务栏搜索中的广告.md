
### 修改注册表

1. __打开注册表编辑器__：

    - 按下`Win+R`键，输入`regedit`，然后点击“确定”。

2. __定位到注册表路径__：

    - 导航到`HKEY_CURRENT_USER\Software\Policies\Microsoft\Windows`。

3. __新建注册表项__：

    - 右键点击`Windows`项，选择“新建”>“项”，命名为`Explorer`。

    - 在`Explorer`项中，新建一个`DWORD (32位) 值`，命名为`DisableSearchBoxSuggestions`。

    - 双击该值，将其数值数据设置为`1`。

4. __重启电脑__：

    - 完成后重启电脑，搜索栏中的广告和热门搜索将被禁用。
