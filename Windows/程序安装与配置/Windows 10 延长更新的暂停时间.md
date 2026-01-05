使用注册表编辑器（适用于所有版本）

如果你希望更彻底地控制更新，可以通过修改注册表来实现。但请注意，修改注册表有一定风险，操作前请备份注册表。

1. __打开注册表编辑器__：

    - 按下`Win+R`键，输入`regedit`，然后点击“确定”。

2. __导航到相关路径__：

    - 在注册表编辑器中，导航到以下路径：

        `计算机\HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\WindowsUpdate\UX\Settings`

3. __修改或新建注册表项__：

    - 找到 Settings 项。如果不存在，选择“新建”>“DWORD (32位) 值”，命名为`FlightSettingsMaxPauseDays`。

    - 双击`FlightSettingsMaxPauseDays`项，将其值设置为`99999`。

    - 这样可以完全禁用自动更新。
4. __设置 Windows 更新__：

    - 打开 “设置” 进入 “更新和安全”。
    - 选择 “Windows更新”，点击 “高级选项”。
    - 在 “暂停更新” 中选择你设置的天数。
