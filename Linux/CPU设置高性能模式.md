一、问题描述
  之前在网上找到的CPU设置高性能模式，只能设置CPU0单个CPU，下述是对多核CPU统一设置工作模式。

二、软件安装与设置 performance

执行下述命令`sudo apt install indicator-cpufreq`,然后重启电脑。此时，界面右上角
会出现如下图 icon 点击该图标，并设置为 performance 模式。

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
