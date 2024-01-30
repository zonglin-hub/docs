# Linux tail 命令 – 查看文件尾部内容

**参考文档：**

- [tail命令 – 查看文件尾部内容 – Linux命令大全(手册) (linuxcool.com)](https://www.linuxcool.com/tail)

---

**语法格式：** tail [参数] 文件

**常用参数：**

| 参数 | 解释                                     |
| ------ | ------------------------------------------ |
| -f   | 指定备份文件持续显示文件最新追加的内容<br /> |

**参考实例：**

```sh
# 查看 tomcat 的运行日志。
$ tail -f /usr/local/tomcat/logs/catalina.out

# tail -n +10 表示从第10行开始输出
$ tail -n +10 file.txt | head -1
Line 10
```
