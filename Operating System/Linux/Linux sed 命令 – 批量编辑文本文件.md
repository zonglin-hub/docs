# Linux sed 命令 – 批量编辑文本文件

**参考文档：**

- [sed命令 – 批量编辑文本文件 – Linux命令大全(手册) (linuxcool.com)](https://www.linuxcool.com/sed)

---

**语法格式：** sed 参数 文件

**常用参数：**

| 参数 | 解释                                       |
| ------ | -------------------------------------------- |
| -i   | 直接修改读取的文件内容，而不是输出到终端。 |
| -n<br /> | 展示指定行参数                             |

**参考案例：**

```shell

[root@localhost ~]# sed -i 's/"name": "liuzonglin"/"name": "Jack"/g' test.json  # 修改指定位置参数
[root@localhost ~]# cat test.json 
{
"password": 12345678
"name": "Jack"
}
[root@localhost ~]# cat -n file.txt 
     1  Line 1
     2  Line 2
     3  Line 3
     4  Line 4
     5  Line 5
     6  Line 6
     7  Line 7
     8  Line 8
     9  Line 9
    10  Line 10
[root@localhost ~]# sed -n '2p' file.txt      # -n 表示只输出匹配行，p 表示 Print
Line 2

[root@localhost ~]# sed -i 's/\r//' <filename>   # sed 指令清理文件中的隐藏符号
```
