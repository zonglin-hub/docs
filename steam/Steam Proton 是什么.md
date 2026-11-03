**Steam Proton 是 Valve 开发的一个兼容层，用来让 Windows 游戏在 Linux 系统上运
行。** 它不是虚拟机，也不是模拟器，而更像一个“翻译官”：把 Windows 游戏发出的系统
调用和图形指令，翻译成 Linux 和 Vulkan 能理解的形式。

### 它由哪些东西组成？

Proton 基于 **Wine**，并集成了多个关键组件：

- **Wine**：把 Windows API 调用翻译成 Linux 系统调用。
- **DXVK**：把 DirectX 9/10/11 转换为 Vulkan。
- **VKD3D-Proton**：把 DirectX 12 转换为 Vulkan。
- **FAudio、GStreamer 等**：处理声音、视频播放等。
- **Steam Linux Runtime 容器**：给游戏提供统一、隔离的运行环境。

所以，一个 Windows 游戏在 Linux 上启动时，实际流程大致是：  
Steam 调用 Proton → Proton 用 Wine 加载游戏 → 游戏的 DirectX 调用被 DXVK/VKD3D
转成 Vulkan → 最后由你的显卡驱动执行。

### 在 Steam 里怎么体现？

你在 Steam 里看到的 **Steam Play** 功能，就是靠 Proton 实现的。启用后，Windows
游戏会像 Linux 游戏一样出现在库里，点击启动时 Steam 自动用 Proton 运行。

设置位置通常是：  
**Steam → 设置 → 兼容性 → 为所有其他产品启用 Steam Play → 选择 Proton 版本**。  
也可以对单个游戏右键 → **属性 → 兼容性 → 强制使用特定 Proton 版本**。

### 常见 Proton 版本

- **Proton 稳定版**：官方推荐，最稳。
- **Proton Experimental**：测试新功能，可能修复新游戏，也可能引入问题。
- **Proton Hotfix**：紧急修复版。
- **GE-Proton**：社区维护版，集成更多补丁，适合官方 Proton 跑不了的游戏。

### 有什么限制？

- **反作弊**：EAC、BattlEye 等需要游戏厂商主动支持，否则多人游戏可能进不去。
- **性能**：很多游戏接近 Windows，少数更好或更差。
- **兼容性**：不是所有游戏都能跑，可以查 **ProtonDB** 看别人反馈。
- **视频编码/启动器**：部分游戏需要额外配置。

### 和你之前的问题有关系吗？

**没有直接关系。**  
Proton 只影响“Windows 游戏怎么在 Linux 上启动”，不影响 Steam 客户端本身的登录界
面。你之前切中文后卡在登录模块，更可能是 **locale、中文字体、输入法** 的问题。  
另外，你的 AMD 核显在日志里显示为 `AMD Radeon Graphics (RADV RENOIR)`，这对
Proton 是比较友好的，因为 DXVK/VKD3D 依赖 Vulkan，而 Mesa RADV 驱动对 Vulkan 支
持不错。