# Linux cat命令 – 在终端设备上显示文件内容

**参考文档：**

- [cat命令 – 在终端设备上显示文件内容 – Linux命令大全(手册) (linuxcool.com)](https://www.linuxcool.com/cat)

---

**语法格式：** cat [参数] 文件

**常用参数：**

| 参数 | 解释                                       |
| ------ | -------------------------------------------- |
| -n   | 递归复制文件和目录显示行数（空行也编号）<br /> |

**参考案例：**

```sh
# 查看文件内容;-n 行数
cat -n server.sh

# 查看文件内容，并添加行号后，输出到另一个文件当中。
cat -n server.sh > server.txt

# 清空指定文件内容
cat /dev/null > /root/anaconda-ks.txt

# 直接向文件中写入一个空
> anaconda-ks.txt
```
