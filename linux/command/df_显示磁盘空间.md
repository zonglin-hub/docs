# df-显示磁盘空间使用量情况

**`df` 命令：** 用于显示磁盘分区上的可使用的磁盘空间。默认显示单位为KB。可以利用该命令来获取硬盘被占用了多少空间，目前还剩下多少空间等信息。

目录

- [df-显示磁盘空间使用量情况](#df-显示磁盘空间使用量情况)
    - [大小格式](#大小格式)
    - [示例](#示例)
    - [帮助文档](#帮助文档)

## 大小格式

显示值以 `--block-size` 和 `DF_BLOCK_SIZE`，`BLOCK_SIZE` 和 `BLOCKSIZE` 环境变量中的第一个可用 `SIZE` 为单位。 否则，单位默认为 `1024` 个字节（如果设置 `POSIXLY_CORRECT`，则为`512`）。

`SIZE` 是一个整数和可选单位。单位是 K，M，G，T，P，E，Z，Y（1024的幂）或 KB，MB，…（1000 的幂）。

## 示例

查看系统磁盘设备，默认是 KB 为单位：

```sh
root@zonglin-virtual-machine:~# df
文件系统          1K的块     已用      可用 已用% 挂载点
tmpfs            1318580     1952   1316628    1% /run
/dev/sda3      204793864 13040364 181277776    7% /
tmpfs            6592888        0   6592888    0% /dev/shm
tmpfs               5120        4      5116    1% /run/lock
/dev/sda2         524252     6220    518032    2% /boot/efi
tmpfs            1318576      164   1318412    1% /run/user/1000
```

使用 `-h` 选项以KB以上的单位来显示，可读性高：

```sh
[root@LinServ-1 ~]# df -h
文件系统              容量  已用 可用 已用% 挂载点
/dev/sda2             140G   27G  106G  21% /
/dev/sda1             996M   61M  884M   7% /boot
tmpfs                1009M     0 1009M   0% /dev/shm
/dev/sdb1             2.7T  209G  2.4T   8% /data1
```

查看全部文件系统：

```sh
[root@LinServ-1 ~]# df -a
文件系统               1K-块        已用     可用 已用% 挂载点
/dev/sda2            146294492  28244432 110498708  21% /
proc                         0         0         0   -  /proc
sysfs                        0         0         0   -  /sys
devpts                       0         0         0   -  /dev/pts
/dev/sda1              1019208     62360    904240   7% /boot
tmpfs                  1032204         0   1032204   0% /dev/shm
/dev/sdb1            2884284108 218826068 2518944764   8% /data1
none                         0         0         0   -  /proc/sys/fs/binfmt_misc
```

显示 `public` 目录中的可用空间量，如以下输出中所示：

```sh
df public
# Filesystem     1K-blocks     Used Available Use% Mounted on
# /dev/loop0      18761008 15246924   2554392  86% /d Avail
```

## 帮助文档

```text
$ df --help
用法：df [选项]... [文件]...
显示每个 <文件> 所在的文件系统的信息，默认显示所有文件系统。

长选项的必选参数对于短选项也是必选的。
  -a, --all             包含伪文件系统，以及重复的和无法访问的文件系统
  -B, --block-size=大小  打印大小前将其除以 <大小>；例如，"-BM" 将以
                           1,048,576 字节为单位显示大小。
                           参见下方的 <大小> 格式
  -h, --human-readable  以 1024 进制的单位显示大小（例如：1023M）
  -H, --si              以 1000 进制的单位显示大小（例如：1.1G）
  -i, --inodes          显示 inode 信息而非块使用量
  -k                    等于 --block-size=1K
  -l, --local           只列出本地的文件系统
      --no-sync         获取使用量信息前不调用 sync（默认）
      --output[=字段列表]  使用 <字段列表> 定义的输出格式，
                             若省略 <字段列表>，则输出所有字段。
  -P, --portability     使用 POSIX 输出格式
      --sync            获取使用量信息前调用 sync
      --total           省略所有对可用空间无显著影响的项，并生成总计值
  -t, --type=类型       只显示指定 <类型> 的文件系统的信息
  -T, --print-type      显示文件系统类型
  -x, --exclude-type=类型   只显示不是指定 <类型> 的文件系统的信息
  -v                    （被忽略）
      --help           显示此帮助信息并退出
      --version        显示版本信息并退出

所显示的数值的单位是 --block-size 选项的参数、DF_BLOCK_SIZE、BLOCK_SIZE
和 BLOCKSIZE 环境变量的值中，第一个可用的 <大小>。
否则，默认单位是 1024 字节（如果 POSIXLY_CORRECT 被设定，则为 512 字节）。

<大小> 参数是一个整数，后面可以跟一个单位（例如：10K 指 10*1024）。
可用的单位有 K、M、G、T、P、E、Z、Y（1024 的幂）以及 KB、MB、...（1000 的幂）。
也可以使用二进制前缀：KiB=K、MiB=M，以此类推。

"字段列表" 是以逗号分隔的列表，指示需要包含在内的列。有效的字段名称包括：
"source"、"fstype"、"itotal"、"iused"、"iavail"、"ipcent"、"size"、
"used"、"avail"、"pcent"、"file" 和 "target"（请参考 info 信息页）。

GNU coreutils 在线帮助：<https://www.gnu.org/software/coreutils/>
请向 <http://translationproject.org/team/zh_CN.html> 报告任何翻译错误
完整文档 <https://www.gnu.org/software/coreutils/df>
或者在本地使用：info '(coreutils) df invocation'
```
