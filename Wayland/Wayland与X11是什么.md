# Wayland 与 X11 都是什么他与 KDE Plasma 有什么区别

简单说：**X11 和 Wayland 是底层图形显示协议/显示服务器体系；KDE Plasma 是上层的
桌面环境。它们不是同一层的东西，也不是二选一的竞争关系。**  
KDE Plasma 可以运行在 **X11** 上，也可以运行在 **Wayland** 上。登录界面里常见的
`Plasma (X11)` 和 `Plasma (Wayland)` 就是在选这个。

## 1. X11 是什么？
X11，通常指 **X Window System / X.Org**，是历史很长的图形显示协议和实现。

结构大概是：
- **X Server**：管理屏幕、键盘、鼠标、显卡输出。
- **X Client**：各种应用程序。
- 应用通过 X11 协议和 X Server 通信。
- **窗口管理器、合成器**通常是单独组件。

优点：
- 兼容性极好，几乎所有 Linux 图形程序都支持。
- 支持网络透明，可以远程 X11 转发。
- 非常成熟。

缺点：
- 架构老旧。
- 安全性较弱，X11 下应用之间隔离差，一个程序可能截屏或监听全局输入。
- 多显示器混合 HiDPI、同步、触控手势等现代体验不如 Wayland。

## 2. Wayland 是什么？
Wayland 是较新的显示协议。它不是某一个具体软件，而是一套协议。

在 Wayland 里：
- 没有传统意义上独立的 X Server。
- **合成器就是显示服务器**，负责输入、输出、窗口合成。
- 应用程序直接和合成器通信。
- 窗口管理通常内置在合成器里。

常见的 Wayland 合成器有：
- KDE 的 **KWin**
- GNOME 的 **Mutter**
- **Sway**
- **Hyprland**
- **Weston**

优点：
- 安全性更好，应用之间隔离更强。
- 更流畅，现代显示特性支持更好。
- 对 HiDPI、多显示器、触控、手势、可变刷新率等更友好。

缺点：
- 旧 X11 程序需要 **XWayland** 兼容层。
- 某些远程桌面、屏幕共享、输入法、NVIDIA 驱动场景过去问题较多，现在逐渐改善。
- 不同桌面环境的 Wayland 实现体验不完全一样。

## 3. KDE Plasma 是什么？
KDE Plasma 是 **桌面环境**，和 GNOME、XFCE 是同一类东西。

它包含：
- Plasma 桌面、面板、小部件
- 系统设置
- KRunner
- Dolphin、Konsole 等 KDE 应用
- **KWin**：窗口管理器/合成器

KDE Plasma 本身不是显示服务器，也不是显示协议。它可以选择底层用哪种显示体系：
- `Plasma (X11)`：底层是 X11，KWin 作为 X11 窗口管理器/合成器。
- `Plasma (Wayland)`：底层是 Wayland，KWin 就是 Wayland 合成器/显示服务器。

## 4. 核心区别
| 名称 | 是什么 | 层级 | 和 KDE Plasma 的关系 |
|---|---|---|---|
| X11 | 显示协议/显示服务器体系 | 底层 | Plasma 可运行在 X11 会话上 |
| Wayland | 显示协议 | 底层 | Plasma 可运行在 Wayland 会话上，KWin 是合成器 |
| KDE Plasma | 桌面环境 | 上层 | 提供完整桌面体验，可选 X11 或 Wayland 后端 |

所以关系可以理解为：

**应用程序 → Qt/GTK 等工具包 → X11 或 Wayland → KWin/X.Org → 内核/驱动/硬件**

而 **KDE Plasma** 是用户看到的桌面、面板、菜单、设置和窗口管理体验。

## 5. 一句话总结
**X11 和 Wayland 是底层图形显示协议；KDE Plasma 是上层桌面环境。KDE Plasma 可以
跑在 X11 上，也可以跑在 Wayland 上。**  
如果你在登录界面看到 `Plasma (Wayland)` 和 `Plasma (X11)`，那是在选 KDE Plasma
这个桌面使用哪种底层显示协议。