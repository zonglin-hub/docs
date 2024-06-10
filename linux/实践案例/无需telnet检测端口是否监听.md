# 无需telnet检测端口是否监听

- 参照视频：<https://www.bilibili.com/video/BV1Er421j7m7/>

---

方式一：使用 ssh -v -p 检测端口

<pre>
❯ ssh -v 172.24.38.146 -p 22
OpenSSH_8.9p1 Ubuntu-3ubuntu0.7, OpenSSL 3.0.2 15 Mar 2022
debug1: Reading configuration data /home/zonglin/.ssh/config
debug1: Reading configuration data /etc/ssh/ssh_config
debug1: /etc/ssh/ssh_config line 19: include /etc/ssh/ssh_config.d/*.conf matched no files
debug1: /etc/ssh/ssh_config line 21: Applying options for *
debug1: Connecting to 172.24.38.146 [172.24.38.146] port 22.
<font color="red"><strong>
debug1: Connection established. # 如果出现 Connection established. 表明端口以打开
</strong></font>
</pre>

如果端口未打开则会显示

<pre>
❯ ssh -v 172.24.38.146 -p 1522
OpenSSH_8.9p1 Ubuntu-3ubuntu0.7, OpenSSL 3.0.2 15 Mar 2022
debug1: Reading configuration data /home/zonglin/.ssh/config
debug1: Reading configuration data /etc/ssh/ssh_config
debug1: /etc/ssh/ssh_config line 19: include /etc/ssh/ssh_config.d/*.conf matched no files
debug1: /etc/ssh/ssh_config line 21: Applying options for *
debug1: Connecting to 172.24.38.146 [172.24.38.146] port 1522.
<font color="red"><strong>
debug1: connect to address 172.24.38.146 port 1522: Connection refused # 端口未开启
ssh: connect to host 172.24.38.146 port 1522: Connection refused
</strong></font>
</pre>

方式二：使用 Bash 内置功能

<pre>
❯ echo >/dev/tcp/172.24.38.146/1521
bash: connect: 连接被拒绝
bash: /dev/tcp/172.24.38.146/1521: 连接被拒绝
</pre>

> [!NOTE] 注意
> 如果端口打开不会输出任何内容，反之端口未开启