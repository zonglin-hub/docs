
# Ubuntu24.04系统启动日志，ACPI 有报错

```log
   我检查了系统日志，ACPI 有报错，
9月 12 20:28:11 localhost kernel: ACPI BIOS Error (bug): Could not resolve symbol [\_SB.PCI0.GP17.MP2], AE_NOT_FOUND (20240827/psargs-332)
9月 12 20:28:11 localhost kernel: 
9月 12 20:28:11 localhost kernel: No Local Variables are initialized for Method [_EVT]
9月 12 20:28:11 localhost kernel: 
9月 12 20:28:11 localhost kernel: Initialized Arguments for Method [_EVT]:  (1 arguments defined for method invocation)
9月 12 20:28:11 localhost kernel:   Arg0:   00000000fcd54431 <Obj>           Integer 0000000000000036
9月 12 20:28:11 localhost kernel: 
9月 12 20:28:11 localhost kernel: ACPI Error: Aborting method \_SB.GPIO._EVT due to previous error (AE_NOT_FOUND) (20240827/psparse-529)

9月 12 20:28:11 localhost kernel: ACPI BIOS Error (bug): Could not resolve symbol [^^^GPP0.PEGP], AE_NOT_FOUND (20240827/psargs-332)
9月 12 20:28:11 localhost kernel: 
9月 12 20:28:11 localhost kernel: 
                                   Initialized Local Variables for Method [_QC6]:
9月 12 20:28:11 localhost kernel:   Local1: 000000003d3261d4 <Obj>           Integer 000000000000002D
9月 12 20:28:11 localhost kernel:   Local2: 000000009de2e42f <Obj>           Integer 0000000000000036
9月 12 20:28:11 localhost kernel:   Local3: 00000000b32fd05b <Obj>           Integer 0000000000000041
9月 12 20:28:11 localhost kernel:   Local4: 000000001aca03bc <Obj>           Integer 00000000000000D1
9月 12 20:28:11 localhost kernel:   Local5: 00000000b4d76940 <Obj>           Integer 0000000000000000
9月 12 20:28:11 localhost kernel:   Local6: 00000000e9c991aa <Obj>           Integer 0000000000000000
9月 12 20:28:11 localhost kernel:   Local7: 000000006d6112a4 <Obj>           Integer 0000000000000000
9月 12 20:28:11 localhost kernel: 
9月 12 20:28:11 localhost kernel: No Arguments are initialized for method [_QC6]
9月 12 20:28:11 localhost kernel: 
9月 12 20:28:11 localhost kernel: ACPI Error: Aborting method \_SB.PCI0.LPC0.EC0._QC6 due to previous error (AE_NOT_FOUND) (20240827/psparse-529)
```

> deepseek

看到你正在尝试理解系统日志中的 ACPI 错误，这是学习系统底层和故障排查的好机会。我来帮你一步步拆解这些日志，并教你如何看懂它们。

1. 什么是 ACPI？

ACPI（高级配置与电源管理接口）是硬件和操作系统之间的一个接口标准，负责电源管理、设备发现、配置等功能。BIOS/UEFI 提供 ACPI 表（包含方法），内核通过执行这些方法来控制硬件。如果表中有错误或缺失，就会出现你看到的那些错误。

2. 解析你的日志

你提供了两段错误，我们逐一解剖。

第一段：\_SB.GPIO._EVT 错误

```
ACPI BIOS Error (bug): Could not resolve symbol [\_SB.PCI0.GP17.MP2], AE_NOT_FOUND
...
ACPI Error: Aborting method \_SB.GPIO._EVT due to previous error (AE_NOT_FOUND)
```

· \_SB.PCI0.GP17.MP2：这是 ACPI 命名空间中的一个路径。\_SB 表示系统总线（System Bus），PCI0 是 PCI 总线，GP17 可能是一个 PCI 设备（如 GPIO 控制器），MP2 可能是该设备下的一个子对象。内核试图访问这个路径，但找不到它。
· AE_NOT_FOUND：错误代码，表示“未找到”。
· \_SB.GPIO._EVT：这是正在执行的方法，_EVT 通常用于 GPIO 事件处理。因为找不到需要的对象（MP2），这个方法被中止。

第二段：\_SB.PCI0.LPC0.EC0._QC6 错误

```
ACPI BIOS Error (bug): Could not resolve symbol [^^^GPP0.PEGP], AE_NOT_FOUND
...
ACPI Error: Aborting method \_SB.PCI0.LPC0.EC0._QC6 due to previous error (AE_NOT_FOUND)
```

· ^^^GPP0.PEGP：^^^ 表示向上三级命名空间。它试图从当前方法向上查找 GPP0 下的 PEGP（可能是 PCI Express 图形端口），但找不到。
· \_SB.PCI0.LPC0.EC0._QC6：这是执行的方法，_QC6 可能是 EC（嵌入式控制器）的一个查询命令。它因为找不到 PEGP 而失败。

日志中还显示了局部变量的值（如 Local1 = 0x2D 等），这些是方法内部的临时数据，对于普通用户不太重要，但在深入调试时可能有用。

3. 为什么会发生这些错误？

常见原因：

· BIOS/UEFI 固件 Bug：ACPI 表是由主板厂商提供的，可能存在路径错误、缺失对象或逻辑错误。
· 内核更新后解析更严格：新内核可能对 ACPI 表的合法性检查更严，以前被忽略的错误现在暴露了。
· 硬件配置变化：比如更换了显卡、外设，导致某些 ACPI 路径不再有效。
· 电源管理相关：_EVT 和 _QC6 多与电源按钮、睡眠、温度监控等有关。

4. 如何进一步诊断？

查看完整 ACPI 表

可以导出 ACPI 表来查看这些路径是否存在：

```bash
# 安装 acpica-tools (Debian/Ubuntu: apt install acpica-tools)
iasl -l /sys/firmware/acpi/tables/DSDT > dsdt.txt  # 反编译 DSDT
```

然后在 dsdt.txt 中搜索 GP17、MP2、GPP0、PEGP，看看它们是否真的存在。如果不存在，说明 BIOS 表有误。

检查内核版本和硬件

```bash
uname -r
lspci -v | grep -i gpio   # 查看 GPIO 控制器
```

有时新内核有对应的修复补丁，可以尝试升级内核或回退到旧版本。

尝试内核启动参数

如果错误不影响系统稳定，可以忽略。如果导致问题（如无法休眠），可以尝试：

· acpi=off（完全禁用 ACPI，但会丢失电源管理，不推荐）
· acpi_osi=Linux 或 acpi_osi="!Windows 2020" 来改变 ACPI 执行路径（可能绕过错误）
· 对于特定设备，可以尝试 pci=noacpi 或 acpi=noirq

具体参数需根据错误涉及的设备选择。例如，如果错误与 GPIO 有关，可以试试 gpiolib.acpi=off（禁用 ACPI GPIO 操作）。

5. 如何学习和理解更多？

· 阅读内核文档：Documentation/acpi/ 目录下有丰富资料。
· 学习 ACPI 规范：可在 UEFI.org 下载，但很厚，先看术语部分。
· 社区论坛：查看 Arch Wiki 的 ACPI 页面，或搜索你的主板型号 + ACPI 错误，看是否有现成的解决方案。
· 内核 Bugzilla：如果确定是内核问题，可以报告 bug，附上完整的 dmesg 和 ACPI 表。

6. 总结

看懂 ACPI 日志的关键：

1. 识别错误路径：\_SB.PCI0.GP17.MP2 是出问题的对象。
2. 理解错误代码：AE_NOT_FOUND 是最常见的，还有 AE_AML_BAD_NAME 等。
3. 追溯方法上下文：\_SB.GPIO._EVT 是执行的方法，它因依赖对象缺失而失败。
4. 判断影响：如果只是启动时一闪而过，且系统工作正常，通常可以忽略。如果导致功能异常，才需要深究。

你提供的日志显示两个方法因为找不到引用的对象而失败，这很可能是 BIOS 表编写错误。你可以先更新 BIOS 到最新版，再看看错误是否消失。如果问题依旧，并且不影响日常使用，可以暂时忽略；如果影响使用，可以用上述方法进一步排查。

希望这些解释能帮你建立分析 ACPI 错误的思路。如果有后续疑问，欢迎继续交流！