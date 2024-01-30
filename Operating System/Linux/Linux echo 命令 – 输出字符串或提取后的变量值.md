# Linux echo 命令 – 输出字符串或提取后的变量值

**参考文档：**

- [echo命令 – 输出字符串或提取后的变量值 – Linux命令大全(手册) (linuxcool.com)](https://www.linuxcool.com/echo)

---

**语法格式：**

 echo [短选项]... [字符串列表]...

 echo 长选项<br />

**参数描述：** 将字符串列表中的字符输出到标准输出。

```sh
-n       不输出尾随的换行符
-e       启用解释反斜杠的转义功能
-E       禁用解释反斜杠的转义功能（默认）
--help   显示此帮助信息并退出
--version 显示版本信息并退出

若 -e 可用，则以下序列即可识别：
\\     反斜线
\a     报警符(BEL)
\b     退格符
\c     禁止尾随的换行符
\e     escape 字符
\f     换页符
\n     另起一行
\r     回到行首
\t     水平制表符
\v     垂直制表符
\0NNN  字节数以八进制数 NNN (1至3位)表示
\xHH   字节数以十六进制数 HH (1至2位)表示
```

**参考案例：**

```shell
echo 'SELECT * FROM employee;' | mysql -uroot -p2wsx3edc fort > /root/liuzonglin/liu.xls # 查询数据导出 xls格式
```
