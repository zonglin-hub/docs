---
标题: Ubuntu 24.04 更换默认终端 不适用
创建时间: 2025-10-25
修改时间: 2025-10-25
标签:
  - Terminal
---

在Ubuntu 24.04中更改默认终端，你可以通过命令行工具快速设置，也可以使用图形化界面
进行修改。为了帮助你快速了解，我先用一个表格来汇总主要的方法和要点：

### 🖥️ 使用命令行更改（推荐）

这是最常用且可靠的方法，适用于大多数情况。

1.  __查看可用的终端列表__：打开终端，运行以下命令：

```bash
sudo update-alternatives --config x-terminal-emulator
```

2.  __选择新的默认终端__：命令会列出所有已安装的终端仿真器。__当前默认项__ 前面
    会有一个星号(`*`)并处于"自动模式" 。你只需输入你希望设置为默认的终端 __前面
    的数字编号__，然后按回车即可 。

```bash
> sudo update-alternatives --config x-terminal-emulator
[sudo] zonglin 的密码：
有 2 个候选项可用于替换 x-terminal-emulator (提供 /usr/bin/x-terminal-emulator)。

  选择       路径                           优先级  状态
------------------------------------------------------------
* 0            /usr/bin/gnome-terminal.wrapper   40        自动模式
  1            /usr/bin/alacritty                20        手动模式
  2            /usr/bin/gnome-terminal.wrapper   40        手动模式

要维持当前值[*]请按<回车键>，或者键入选择的编号：1
update-alternatives: 使用 /usr/bin/alacritty 来在手动模式中提供 /usr/bin/x-terminal-emulator (x-terminal-emulator)
```

### 💎 重要提示

-   __方法适用性__：`update-alternatives` 方法是 __最通用和标准__的 。图形界面的
    位置可能因你使用的Ubuntu版本或桌面环境（如GNOME, LXQt等）而有所不同 。
-   __理解"自动模式"__：在使用 `update-alternatives` 时，你会看到"自动模式"和"手
    动模式"。通常建议让系统管理的项保持在"自动模式"，它会根据优先级自动选择最合
    适的版本 。你通过编号选择后，该选项会切换到"手动模式"。

希望这些方法能帮助你顺利地将Ubuntu的默认终端更改为你喜欢的工具。如果你在操作过程
中遇到任何问题，或者想了解特定终端的更多信息，随时可以再问我。