# Linux sz 命令 – 基于 ZModem 协议下载文件到本地

**参考文档：**

- [sz命令 – 基于ZModem协议下载文件到本地 – Linux命令大全(手册) (linuxcool.com)](https://www.linuxcool.com/sz)

---

**语法格式：sz** [参数] 文件

**常用参数：**

| 参数 | 解释             |
| ------ | ------------------ |
| -b   | 以二进制方式传输 |
| -a   | 以文本方式传输   |

**参考案例：**

```shell
sz -a /etc/*  # 以文本方式批量下载指定的多个文件
sz -b File.tar.gz # 以二进制方式下载指定的某个文件
```
