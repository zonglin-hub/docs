# Linux cp 命令 – 复制文件或目录

**参考文档：**

- [cp命令 – 复制文件或目录 – Linux命令大全(手册) (linuxcool.com)](https://www.linuxcool.com/cp)

---

**语法格式：** cp [参数] 源文件 目标文件

**常用参数：**

| 参数 | 解释                 |
| ------ | ---------------------- |
| -r   | 递归复制文件和目录<br /> |
| --path   | 选项指定在复制的时候主动创立不存在的子目录<br /> |

**参考案例：**

```sh
# 在当前工作目录中，将某个目录复制一份，并定义新目录名称
cp -r Documents Doc  

# 如果 $pa 目录下不存在 /etc/init.d/cluster 这一串子目录，cp --path 命令会主动创立 /etc/init.d/cluster 这一串子目录，而后把文件复制到对应的子目录下。
pa=$(pwd)

# 在下面命令中，$pa 目录必须存在，能力复制。cp --path 命令只会主动创立源文件门路蕴含的子目录，不会主动创立所给的目标目录。
cp --path /etc/init.d/cluster $pa/
```
