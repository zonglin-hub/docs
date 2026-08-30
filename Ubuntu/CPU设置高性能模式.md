既然你已经对 GPU 进行了优化，想让 Ubuntu 24.04 的桌面体验更上一层楼，那么对 CPU
进行针对性的调优就是非常合理的下一步。CPU 的优化核心在于 __减少处理延迟、合理分
配资源，并让关键任务（如你的桌面应用）能优先被响应__。

下面我为你梳理了几个在 Ubuntu 24.04 上非常有效的 CPU 优化方向，你可以根据自己的
硬件和具体需求尝试。

### ⚙️ 核心优化方向：调度器、频率与参数

#### 1. CPU 调度策略与频率调节

这是最直接有效的优化之一，目的是让 CPU 始终以最高性能状态响应任务，减少因频率切
换带来的延迟。

*   __目标__：将 CPU 频率调节器设置为 `performance` 模式，使其尽可能运行在最高频率。
*   __操作方法__：
    1.  安装 `cpufrequtils` 工具：`sudo apt install cpufrequtils`。
    2.  临时设置为性能模式：`sudo cpufreq-set -g performance`。
    3.  查看是否生效：
        `cat /sys/devices/system/cpu/cpu*/cpufreq/scaling_governor`
*   __注意__：此设置重启后会失效。如果想永久生效，可以通过 `systemd` 服务在每次
    启动时自动执行此命令。

#### 2. 内核调度器参数微调

Ubuntu 24.04 的内核（v6.8+）对调度器做了一些调整，偏向能效，但对延迟敏感的应用可
能不太友好。我们可以通过 `sysctl` 调整几个关键参数。

*   __目标__：降低调度延迟，让交互任务更快获得 CPU 资源。
*   __操作方法__：编辑 `/etc/sysctl.conf` 文件（需要 root 权限），在文件末尾添加
    以下内容：

    ```bash
    # 降低任务最小运行时间粒度，让短任务更快完成或被抢占
    kernel.sched_min_granularity_ns = 500000
    # 降低唤醒抢占阈值，让高优先级任务能更快打断当前任务
    kernel.sched_wakeup_granularity_ns = 300000
    # 调整调度延迟目标值
    kernel.sched_latency_ns = 6000000
    ```

    保存文件后，执行 `sudo sysctl -p` 使配置立即生效。

#### 3. 选择内核抢占模式

Ubuntu 默认的内核抢占模型是“voluntary”（自愿抢占）。这意味着运行在内核空间的任务
只能在特定的“安全点”被抢占。对于追求极致流畅的桌面或游戏体验，可以考虑使用
“full”（全抢占）模型。

*   __目标__：启用 `preempt=full` 内核参数，使内核态的任务几乎随时可以被抢占，从
    而大幅降低高优先级任务的调度延迟。
*   __操作方法__：编辑 `/etc/default/grub` 文件，找到
    `GRUB_CMDLINE_LINUX_DEFAULT` 这一行，在引号内添加 `preempt=full`。例如：

    ```
    GRUB_CMDLINE_LINUX_DEFAULT="quiet splash preempt=full"
    ```
    
    保存后，运行 `sudo update-grub` 更新引导配置，然后重启系统。

*   __注意__：全抢占模式可能会略微降低一些纯计算型任务的吞吐量，但能显著提升交互
    响应速度。如果你的系统主要用于开发、编译等计算密集型工作，可以保留默认设置。

#### 4. 内存与虚拟内存管理

即使有 GPU 加速，如果 CPU 因为内存不足而频繁与交换分区（swap）打交道，整个系统也
会变慢。

*   __降低 Swappiness 值__：该值控制系统使用 swap 的积极程度，默认是 60。对于内
    存较大的现代电脑，可以降低它，让系统优先使用物理内存。
    *   临时修改：`sudo sysctl vm.swappiness=10`。
    *   永久修改：将 `vm.swappiness=10` 添加到 `/etc/sysctl.conf` 文件末尾，然后
        执行 `sudo sysctl -p`。
*   __启用 ZRAM__：如果你的内存较小（例如 ≤ 4GB），可以考虑用 ZRAM 替代传统的交
    换分区/文件。ZRAM 在内存中创建一个压缩的块设备作为交换空间，通过压缩内存数据
    来变相增加可用内存，比读写磁盘快得多。
    *   Ubuntu 24.04 可以通过安装 `systemd-zram-generator` 包并简单配置来实现。

#### 5. 精简系统服务与开机启动

减少 CPU 的后台负担，让资源更集中于前台应用。

*   __目标__：使用工具或命令禁用不必要的开机自启服务和程序。
*   __操作方法__：
    *   __命令行方式__：使用 `systemctl list-unit-files --type=service
        --state=enabled` 列出所有开机自启的服务。然后，用 `sudo systemctl
        disable [服务名]` 禁用非必要的服务，如蓝牙（`bluetooth.service`，如果你
        不用的话）、打印服务（`cups.service`）等。
    *   __图形化工具（可选）__：你也可以安装 `Stacer` 这类系统优化工具，它提供了
        更友好的图形界面来管理启动项、服务和清理系统。

#### 6. 利用硬件特性：任务卸载

如果你的 CPU 支持特定硬件加速技术，可以利用它将某些计算密集型任务从 CPU 卸载到专
用硬件上。

*   __目标__：对于 Intel 平台，如果你的 CPU 集成了 Intel QAT 技术，可以安装相关
    驱动和引擎（如 `qatengine`），让支持的软件（如负载均衡器、加密应用）将加密/
    解密操作卸载到硬件加速器上，从而释放 CPU 核心。
*   __操作方法__：对于 Ubuntu 24.04，官方仓库已提供 Intel QAT 支持，可以通过
    `sudo apt install qatengine` 进行安装和探索。

### ✨ 如何开始？一个简单的优化流程

面对这么多选项，你可能不知道从哪开始。建议你按照以下步骤来操作，每一步后都感受一
下变化，这样能更清楚地知道哪些调整对你是最有效的：

1.  __第一步，也是最简单的一步__：设置 CPU 调节器为 `performance` 模式。这通常能
    带来立竿见“感”的流畅度提升。
2.  __第二步__：修改 `sysctl.conf` 调整调度器参数和 `swappiness` 值。这两个操作
    风险很低，但能改善多任务响应和内存使用策略。
3.  __第三步__：使用 `systemctl` 或 `Stacer` 等工具，仔细检查并禁用你确认不需要
    的开机自启服务。__操作时请务必小心，不要禁用你不确定的服务__。
4.  __第四步（进阶）__：如果对响应速度有极致要求，可以尝试在 GRUB 中添加
    `preempt=full` 参数。注意感受系统交互的变化，如果发现计算性能有不可接受的下
    降，可以随时移除该参数。
5.  __第五步（特定场景）__：如果你从事大量网络加密或数据压缩的工作，可以研究一下
    Intel QAT 或其他硬件卸载技术是否适用于你的 CPU 和工作负载。

---

一、问题描述
  之前在网上找到的CPU设置高性能模式，只能设置CPU0单个CPU，下述是对多核CPU统一设置工作模式。

二、软件安装与设置 performance

执行下述命令`sudo apt install indicator-cpufreq`,然后重启电脑。此时，界面右上角
会出现如下图 icon 点击该图标，并设置为performance 模式。

- performance ： 高性能模式，最大化 CPU 性能，将 CPU 频率设为最高值
- powersave ：节能模式，最大化节能，将 CPU 频率设为最低值
- ondemand : 根据 CPU 使用情况动态调整频率，适合一般的桌面应用
- conservative：比 ondemand 更加保守，适合省电模式下的移动设备使用

三、查看各CPU状态

执行下述命令`cat /sys/devices/system/cpu/cpu*/cpufreq/scaling_governor`,可以看
出各CPU均为performance 模式。

```bash
z:~$ cat /sys/devices/system/cpu/cpu*/cpufreq/scaling_governor
performance
performance
performance
performance
performance
performance
performance
performance
performance
performance
performance
performance
```

四、开机默认高性能

上述步骤图形化操作方便快捷，但只是能保证当前CPU状态,重启电脑后，又恢复默认设置。下述是设置开机默认高性能步骤：

4.1 安装 cpufrequtils

执行`sudo apt-get install cpufrequtils`进行软件安装。该软件常用命令

- `cpufreq-info`: 查看CPU状态；
- `sudo cpufreq-set -c 0 -g performance`: 设置指定CPU的状态。-c 1 指定要设置的 CPU
  核心编号。 0表示第1个 CPU 核心； -g performance 设置CPU状态为 performance；
- `sudo cpufreq-set -c 1 -d 900MHz`: 设置指定CPU的状态, -d 900MHz 指定CPU 最低频率；
- `sudo cpufreq-set -c 2 -u 2.6GHz`: 设置指定CPU的状态，-u 2.6GHz 指定CPU 最高频率；

4.2 编写脚本

编写set_cpu_performance.sh文件,并通过`sudo chmod +x set_cpu_performance.sh`赋予可执行权限

```bash
#!/bin/bash
# 检查 cpufrequtils 软件包是否已安装
if ! [ -x "$(command -v cpufreq-set)" ]; then
  echo "错误：未安装 cpufrequtils 软件包。请先安装它。"
  exit 1
fi

# 检查是否以 root 身份运行（因为需要修改系统设置）
if [ "$EUID" -ne 0 ]; then
   echo "请使用 sudo 运行此脚本，以便更改 CPU 频率调节器。"
   exit 1
fi

# 选择调节器模式：performance 或 powersave
cpu_mode=performance
# cpu_mode=powersave   # 如果需要省电模式，取消注释此行并注释上一行

# 获取 CPU 核心数
cpu_cores=$(nproc)

echo "正在设置所有 $cpu_cores 个 CPU 核心的调节器为: $cpu_mode"

# 为每个 CPU 核心设置指定的调节器
for ((cpu=0; cpu<$cpu_cores; cpu++)); do
  if cpufreq-set -c $cpu -g $cpu_mode; then
    echo "核心 $cpu 设置成功。"
  else
    echo "错误：核心 $cpu 设置失败。"
    exit 1
  fi
done

# 验证每个核心当前的调节器
echo "当前 CPU 调节器设置："
for ((cpu=0; cpu<$cpu_cores; cpu++)); do
  # 使用 cpufreq-info -p 获取当前策略，最后一个字段即为调节器名称
  governor=$(cpufreq-info -c $cpu -p | awk '{print $NF}')
  echo "CPU $cpu: $governor"
done

echo "所有 $cpu_cores 个 CPU 核心的调节器已设置为 $cpu_mode。"
# cat /proc/cpuinfo | grep processor | wc -l   # 备用方法获取核心数
```

- cpu_mode :可以在 powersave、performance切换
- cat /proc/cpuinfo | grep processor | wc -l: 获取当前CPU总个数，或者使用lscpu来查看

4.3 设为默认开机脚本

通过执行`sudo bash set_cpu_performance.sh`确认脚本是否起作用。
