---
标题: Fcitx（小企鹅输入法）剪贴板设置
创建时间: 2026-11-26
修改时间: 2026-11-26
标签:
  - Ubuntu
  - Fcitx
---

Fcitx（小企鹅输入法）剪贴板
---

快捷键 <kbd>Ctrl + ;</kbd> 是 __Fcitx（小企鹅输入法）__ 输入法在 Linux 上的默认
快捷键，用来呼出剪贴板历史，方便你选择之前复制过的内容。

### 🖥️ 方法一：通过图形化界面修改（推荐）

这是最直观的方式，适合绝大多数用户：

1. __打开配置工具__：在系统托盘找到 Fcitx 图标，右键选择“配置”；或直接在终端运行
   命令：

   ```bash
   fcitx5-configtool
   ```

2. __定位剪贴板插件__：在弹出的窗口中找到“ __附加组件__ ”（Addons）选项卡。
3. __修改快捷键__：在列表中找到“ __剪贴板__ ”（Clipboard）插件，点击右侧的“ __配
   置__ ”（Configure）按钮。
4. __重新绑定按键__：在设置页面中，你会看到触发键（Trigger Key）被设置为
   `Control+;`。点击它，按下你希望使用的新快捷键组合，或者直接 __清除（留空）__
   以彻底禁用这个快捷键。
5. __保存并重启__：保存设置后，建议在终端运行 `fcitx5 -r` 重启输入法，使更改生
   效。

### 📄 方法二：直接编辑配置文件

如果你更喜欢通过文本编辑，或者想进行批量配置，可以直接修改配置文件。

__配置文件路径__： Fcitx5 的用户配置文件通常位
于：`~/.config/fcitx5/conf/clipboard.conf`

__修改方法__：

1. 用文本编辑器打开该文件，例如：

    ```bash
    $ cat ~/.config/fcitx5/conf/clipboard.conf
    # 粘贴主选区
    PastePrimaryKey=
    # 项目个数
    Number of entries=5
    # 不要显示密码管理工具中的密码
    IgnorePasswordFromPasswordManager=False
    # 以普通文本方式显示密码
    ShowPassword=False
    # 自动清除密码的秒数
    ClearPasswordAfter=30

    [TriggerKey]
    0=Control+semicolon
    ```

2. 找到 `TriggerKey` 这一行。默认情况下，它可能被注释掉（以 `#` 开头）或直接写着
   `TriggerKey=Control+semicolon`。
3. __修改为新的快捷键__ ，或者 __将其注释掉/留空__ 来禁用：
   -   __禁用__：在行首添加 `#`，或写成 `TriggerKey=`。
   -   __改键__：修改为其他组合，例如 `TriggerKey=Control+period`（<kbd>Ctrl +
       .</kbd>）。
4. 保存文件并重启 Fcitx5（`fcitx5 -r`）。

> [!NOTE]
>
> `clipboard.conf` 文件不在 `~/.config/fcitx5/conf/` 目录里，是因为你__还没有通
> 过图形界面修改过剪贴板的设置__。Fcitx5 的设计是：当你第一次在配置工具里修改某
> 个插件的选项时，它才会在用户目录下生成对应的 `.conf` 文件。在此之前，它一直读
> 取的是系统级的默认配置。


💡 补充说明
---


系统级的基础配置文件位于 `/usr/share/fcitx5/addon/clipboard.conf`，其中定义了插
件的默认行为，但 __不建议直接修改这个系统文件__ ，因为它在软件包升级时可能会被覆
盖。用户级别的配置（即 `~/.config/fcitx5/conf/` 下的文件）优先级更高，是你进行个
性化设置的地方。