
## 查看内核加载的驱动模块

```bash
zonglin@localhost:~$ lspci -k | grep -A 2 "VGA"  # 查看内核加载的驱动模块
03:00.0 VGA compatible controller: Advanced Micro Devices, Inc. [AMD/ATI] Cezanne [Radeon Vega Series / Radeon Vega Mobile Series] (rev c6)
	DeviceName: Realtek RTL8111E Ethernet LOM
	Subsystem: Wingtech Group(HongKong)Limited Cezanne [Radeon Vega Series / Radeon Vega Mobile Series]
```

## 检查当前使用的 OpenGL 渲染器

```bash
zonglin@localhost:~$ # 1. 检查渲染器
zonglin@localhost:~$ glxinfo -B | grep "OpenGL renderer"
OpenGL renderer string: AMD Radeon Graphics (radeonsi, renoir, ACO, DRM 3.61, 6.14.0-27-generic)
```

## 检查 GPU 驱动是否加载
```bash
zonglin@localhost:~$ # 2. 检查内核消息
zonglin@localhost:~$ sudo dmesg | grep -i amdgpu
[sudo] zonglin 的密码： 
[    5.927954] [drm] amdgpu kernel modesetting enabled.
[    5.943830] amdgpu: Virtual CRAT table created for CPU
[    5.943850] amdgpu: Topology: Add CPU node
[    5.943972] amdgpu 0000:03:00.0: enabling device (0006 -> 0007)
[    5.963248] amdgpu 0000:03:00.0: amdgpu: detected ip block number 0 <soc15_common>
[    5.963252] amdgpu 0000:03:00.0: amdgpu: detected ip block number 1 <gmc_v9_0>
[    5.963255] amdgpu 0000:03:00.0: amdgpu: detected ip block number 2 <vega10_ih>
[    5.963257] amdgpu 0000:03:00.0: amdgpu: detected ip block number 3 <psp>
[    5.963259] amdgpu 0000:03:00.0: amdgpu: detected ip block number 4 <smu>
[    5.963262] amdgpu 0000:03:00.0: amdgpu: detected ip block number 5 <dm>
[    5.963264] amdgpu 0000:03:00.0: amdgpu: detected ip block number 6 <gfx_v9_0>
[    5.963266] amdgpu 0000:03:00.0: amdgpu: detected ip block number 7 <sdma_v4_0>
[    5.963268] amdgpu 0000:03:00.0: amdgpu: detected ip block number 8 <vcn_v2_0>
[    5.963270] amdgpu 0000:03:00.0: amdgpu: detected ip block number 9 <jpeg_v2_0>
[    5.963283] amdgpu 0000:03:00.0: amdgpu: Fetched VBIOS from VFCT
[    5.963286] amdgpu: ATOM BIOS: 113-CEZANNE-018
[    5.998516] amdgpu 0000:03:00.0: vgaarb: deactivate vga console
[    5.998524] amdgpu 0000:03:00.0: amdgpu: Trusted Memory Zone (TMZ) feature enabled
[    5.998529] amdgpu 0000:03:00.0: amdgpu: MODE2 reset
[    5.998636] amdgpu 0000:03:00.0: amdgpu: VRAM: 512M 0x000000F400000000 - 0x000000F41FFFFFFF (512M used)
[    5.998640] amdgpu 0000:03:00.0: amdgpu: GART: 1024M 0x0000000000000000 - 0x000000003FFFFFFF
[    5.998815] [drm] amdgpu: 512M of VRAM memory ready
[    5.998819] [drm] amdgpu: 7669M of GTT memory ready.
[    6.700050] amdgpu 0000:03:00.0: amdgpu: reserve 0x400000 from 0xf41f800000 for PSP TMR
[    6.787167] amdgpu 0000:03:00.0: amdgpu: RAS: optional ras ta ucode is not available
[    6.795937] amdgpu 0000:03:00.0: amdgpu: RAP: optional rap ta ucode is not available
[    6.795941] amdgpu 0000:03:00.0: amdgpu: SECUREDISPLAY: securedisplay ta ucode is not available
[    6.798088] amdgpu 0000:03:00.0: amdgpu: SMU is initialized successfully!
[    6.827542] snd_hda_intel 0000:03:00.1: bound 0000:03:00.0 (ops amdgpu_dm_audio_component_bind_ops [amdgpu])
[    6.940829] kfd kfd: amdgpu: Allocated 3969056 bytes on gart
[    6.940846] kfd kfd: amdgpu: Total number of KFD nodes to be created: 1
[    6.940998] amdgpu: Virtual CRAT table created for GPU
[    6.941123] amdgpu: Topology: Add dGPU node [0x1638:0x1002]
[    6.941126] kfd kfd: amdgpu: added device 1002:1638
[    6.941136] amdgpu 0000:03:00.0: amdgpu: SE 1, SH per SE 1, CU per SH 8, active_cu_number 7
[    6.941140] amdgpu 0000:03:00.0: amdgpu: ring gfx uses VM inv eng 0 on hub 0
[    6.941142] amdgpu 0000:03:00.0: amdgpu: ring comp_1.0.0 uses VM inv eng 1 on hub 0
[    6.941145] amdgpu 0000:03:00.0: amdgpu: ring comp_1.1.0 uses VM inv eng 4 on hub 0
[    6.941147] amdgpu 0000:03:00.0: amdgpu: ring comp_1.2.0 uses VM inv eng 5 on hub 0
[    6.941148] amdgpu 0000:03:00.0: amdgpu: ring comp_1.3.0 uses VM inv eng 6 on hub 0
[    6.941150] amdgpu 0000:03:00.0: amdgpu: ring comp_1.0.1 uses VM inv eng 7 on hub 0
[    6.941152] amdgpu 0000:03:00.0: amdgpu: ring comp_1.1.1 uses VM inv eng 8 on hub 0
[    6.941154] amdgpu 0000:03:00.0: amdgpu: ring comp_1.2.1 uses VM inv eng 9 on hub 0
[    6.941156] amdgpu 0000:03:00.0: amdgpu: ring comp_1.3.1 uses VM inv eng 10 on hub 0
[    6.941158] amdgpu 0000:03:00.0: amdgpu: ring kiq_0.2.1.0 uses VM inv eng 11 on hub 0
[    6.941160] amdgpu 0000:03:00.0: amdgpu: ring sdma0 uses VM inv eng 0 on hub 8
[    6.941161] amdgpu 0000:03:00.0: amdgpu: ring vcn_dec uses VM inv eng 1 on hub 8
[    6.941163] amdgpu 0000:03:00.0: amdgpu: ring vcn_enc0 uses VM inv eng 4 on hub 8
[    6.941165] amdgpu 0000:03:00.0: amdgpu: ring vcn_enc1 uses VM inv eng 5 on hub 8
[    6.941167] amdgpu 0000:03:00.0: amdgpu: ring jpeg_dec uses VM inv eng 6 on hub 8
[    6.946312] amdgpu 0000:03:00.0: amdgpu: Runtime PM not available
[    6.950663] amdgpu 0000:03:00.0: [drm] Registered 4 planes with drm panic
[    6.950667] [drm] Initialized amdgpu 3.61.0 for 0000:03:00.0 on minor 1
[    6.958980] fbcon: amdgpudrmfb (fb0) is primary device
[    6.958987] amdgpu 0000:03:00.0: [drm] fb0: amdgpudrmfb frame buffer device
```

下面，我将为您逐一分解这些参数和命令，并提供每个结论的验证方法。

---

### 一、系统状态验证命令与解释

您提供的4个参数，每一个都可以通过命令行进行验证：
1. GPU: AMD Radeon Vega Series (Cezanne)

    - 命令: `lspci | grep -i vga`
    - 输出解读： `03:00.0 VGA compatible controller: Advanced Micro Devices,
      Inc. [AMD/ATI] Cezanne [Radeon Vega Series / Radeon Vega Mobile Series]
      (rev c6)` 
    - 说明： `lspci` 列出所有PCI设备。`Cezanne` 是AMD的内部代号，指代搭载Radeon
      Vega显卡的Ryzen 5000系列APU。

2. 驱动: amdgpu 开源驱动已正常加载和工作

    - 命令：`lsmod | grep amdgpu` 
    - 输出解读：如果看到 `amdgpu` 模块，则表示驱动已加载到内核。
    - 更深层验证：`dmesg | grep -i "amdgpu"` 查看内核启动日志，能看到驱动初始化
      的详细信息，包括显存大小、功能模块等，证明其不仅加载且在工作。

3. 桌面环境: GNOME (Ubuntu 定制版)

    - 命令： `echo $XDG_CURRENT_DESKTOP` 
    - 输出解读：`ubuntu:GNOME` 
    - 说明：这个环境变量明确指明了当前运行的桌面环境是经过Ubuntu定制的GNOME。

4. OpenGL 渲染器: AMD Radeon Graphics (radeonsi, renoir, ACO)

    - 命令：`glxinfo -B | grep "OpenGL renderer"` 
    - 输出解读：`OpenGL renderer string: AMD Radeon Graphics (radeonsi, renoir,
      ACO, DRM 3.61, 6.14.0-27-generic)` 
    - `radeonsi`: 用户空间的开源OpenGL驱动程序。 
    - `renoir`: 您的GPU代号，再次确认硬件。 
    - `ACO`: **这是一个重要标志**。它是AMD开发的新一代编译器后端，取代了旧的LLVM
      管线，能显著提升游戏和应用的性能并减少编译卡顿。它的存在本身就是驱动优化良
      好的证明。 
    - 这个输出**100%确认**图形渲染是由您的AMD GPU硬件完成的，而不是CPU软渲染
      (`llvmpipe`)。

### 二、环境变量参数详解

这些变量用于微调Mesa（开源图形驱动栈）的行为，以获取更好的性能。它们不是Ubuntu或
GNOME的设置，而是直接传递给图形驱动程序的指令。

#### 组 1: AMD Vulkan 驱动 (RADV) 调优

```bash
export RADV_PERFTEST=aco,gpl,rt
```
- `RADV`:  AMD在Linux上的开源Vulkan驱动。
- `PERFTEST`:  允许启用一些处于性能测试阶段、可能尚未默认开启的特性。
- `aco`:  **A**md **C**ompiler **O**utput。上文提到的新编译器后端。**启用后能大
  幅提升Vulkan游戏的性能（帧数）和响应速度**，是单机最重要的优化选项。
- `gpl`:  **G**uarded **P**age **L**ist。一种内存管理特性，可以提升一些游戏的性
  能，但可能与极少数游戏兼容性不佳。如果遇到问题，可以从参数中移除它。
- `rt`:  为支持硬件光追（Ray Tracing）的游戏启用**光追加速**。如果您的GPU支持
  （部分Vega架构支持），并且玩光追游戏，这个选项会启用相关优化。

#### 组 2: AMD 调试开关 (通常用于关闭调试来提升性能)

```bash
export AMD_DEBUG=noft,nodcc,nowc
```
这是一个组合，通过逗号分隔多个选项。
- `noft`:  **No** **F**ault **T**olerance。禁用容错处理。驱动程序在遇到错误时可
  能会尝试慢速但安全的回退方案，关闭此功能可以避免性能损失，但遇到极端错误的指令
  可能会导致程序崩溃。
- `nodcc`:  **No** **D**elta **C**olor **C**ompression。禁用颜色压缩。**这是一个
  激进选项**。DCC是一种显存带宽节省技术，关闭它通常会**降低性能**。除非在特定、
  已知受益于此的游戏中，否则**不应使用**。建议从您的配置中移除 `nodcc`。
- `nowc`:  **No** **W**rite **C**ombine。禁用写入合并。写入合并是一种优化CPU向
  GPU显存写入数据的技术。关闭它通常也会**降低性能**。同样，除非有明确需求，否则
  **不应使用**。建议从您的配置中移除 `nowc`。

更正建议：这个变量更安全有效的用法是 `export AMD_DEBUG=noft`，或者如果您不
确定，可以暂时完全不设置它。

#### 组 3: 垂直同步 (Vsync) 控制

```bash
export __GL_SYNC_TO_VBLANK=0     # 主要影响基于NVIDIA闭源驱动的应用，但对某些开源驱动应用也有效
export vblank_mode=0             # 更通用的设置，影响大多数OpenGL应用
```
- 垂直同步 (Vsync)： 一种同步技术，强制游戏的帧率与显示器的刷新率（如60Hz）保持
  一致，以防止画面撕裂，但会引入操作延迟和帧数限制。
- `=0`: **禁用**垂直同步。
- 效果： 应用程序（尤其是游戏）的帧数将不再被限制，操作延迟会降低，感觉更“跟
  手”。但**可能会在画面快速移动时出现撕裂现象**。
- 区别： 设置两个是为了确保覆盖不同的图形驱动和应用程序。`vblank_mode=0` 的覆盖
  面更广。

---

### 总结与行动建议

1.  验证命令是根本：您现在已经掌握了 `lspci`, `lsmod`, `glxinfo` 这些强大的工
    具，可以随时自行确认系统状态。
2.  环境变量调整：
    - 强烈建议您从 `AMD_DEBUG` 中移除 `nodcc` 和 `nowc` 这两个可能降低性能的参
      数。
    - `RADV_PERFTEST=aco` 是性价比最高的优化，必选。`gpl` 和 `rt` 可以保留。
    - 垂直同步控制根据您的需求决定，玩游戏时禁用收益明显。

请您根据这个更详细的解释，重新评估和调整您的优化配置。再次为之前不够严谨的回答表
示歉意，希望这次的解释能真正满足您深入学习的需求。如果还有任何疑问，请随时提出。