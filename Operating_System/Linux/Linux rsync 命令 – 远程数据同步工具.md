# Linux rsync 命令 – 远程数据同步工具

**参考文档：**

- [rsync命令 – 远程数据同步工具 – Linux命令大全(手册) (linuxcool.com)](https://www.linuxcool.com/rsync)
- [Rsync命令参数详解](https://www.cnblogs.com/subsir/articles/2565373.html)

---

**语法格式：ls** [参数] 文件

**常用参数：**

| 参数 | 解释           |
| ------ | ---------------- |
| -t   | 相当于去重了<br /> |
| -a<br /> | 归档模式，表示以递归方式传输文件，并保持所有文件属性，等于-rlptgoD<br />             |
| -u   | 仅仅进行更新，也就是跳过所有已经存在于DST，并且文件时间晚于要备份的文件。(不覆盖更新的文件)<br />             |
| -v   | 详细模式输出<br /> |
| -z   | 压缩文件       |
| -e   | 指定使用rsh、ssh方式进行数据同步               |

**参考案例：**

```sh
# -t 相当于去重了
rsync -auvzte 'ssh -p 220' /home/db_log ftp_log rdp_log sftp_log ssh_log telnet_log root@10.96.128.133:/home/
```
