# ipcs

- [ipcs命令 – 多进程间通信常用的工具 – Linux命令大全(手册) (linuxcool.com)](https://www.linuxcool.com/ipcs)

---

__语法格式：ipcs__ [参数]

__常用参数：__

| 参数     | 解释                                         |
| -------- | -------------------------------------------- |
| -m       | 打印出使用共享内存进行进程间通讯的信息       |
| -q<br /> | 打印出使用消息队列进行进程间通信的信息<br /> |
| -s       | 打印出使用信号进行进程间通信的信息<br />     |

__参考案例：__

```shell
ipcs -m | grep -E "^.*sshd.* 0 .*$" | ipcs -m | awk '$2 ~/[0-9]+/ {print $2}' | while read s; do ipcrm -m $s; done
```
