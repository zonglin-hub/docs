```bash
zonglin@localhost:~$ lspci -k | grep -A 2 "VGA"  # 查看内核加载的驱动模块
03:00.0 VGA compatible controller: Advanced Micro Devices, Inc. [AMD/ATI] Cezanne [Radeon Vega Series / Radeon Vega Mobile Series] (rev c6)
	DeviceName: Realtek RTL8111E Ethernet LOM
	Subsystem: Wingtech Group(HongKong)Limited Cezanne [Radeon Vega Series / Radeon Vega Mobile Series]
```

## **检查当前使用的 OpenGL 渲染器**：

```bash
zonglin@localhost:~$ # 1. 检查渲染器
zonglin@localhost:~$ glxinfo -B | grep "OpenGL renderer"
OpenGL renderer string: AMD Radeon Graphics (radeonsi, renoir, ACO, DRM 3.61, 6.14.0-27-generic)
```

## **检查 GPU 驱动是否加载**：
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

---

## 为什么您感觉GPU"没怎么运行"？
1. **桌面环境本身负载低**：
   - 现代GPU在基本桌面操作中利用率很低（<5%）
   - 轻量任务时GPU会自动降频节能

2. **验证GPU活动的方法**：
   ```bash
   # 安装监控工具
   sudo apt install radeontop
   
   # 运行监控
   sudo radeontop
   ```
   观察：
   - `%bus`列：GPU利用率百分比
   - `MHz`列：当前GPU频率
   - 拖动窗口/播放视频时数值应上升

