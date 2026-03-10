如何查看 CPU 型号、个数、核心数、逻辑CPU个数

基本概念介绍

总核数 = 物理CPU个数 X 每颗物理CPU的核数总逻辑CPU数 = 物理CPU个数 X 每颗物理CPU
的核数 X 超线程数Linux 中 CPU 的信息，一般可以看 `/proc/cupinfo` 文件的信息关键
字意义

```bash
# 查看物理CPU型号
grep 'model name' /proc/cpuinfo

# 查看物理CPU个数
grep 'physical id' /proc/cpuinfo

# 查看每颗物理 CPU 的核心数
grep 'cpu cores' /proc/cpuinfo # 每颗 CPU 的核心数，不是总核心数

# 查看逻辑 CPU 个数
grep "processor" /proc/cpuinfo

# 查看 CPU 负载情况
top
```

- processor：逻辑CPU的标识
- model name：真实CPU的型号信息
- physical id：真实CPU和标识
- cpu cores：真实CPU的内核数