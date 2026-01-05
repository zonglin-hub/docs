---
标题: 使用 SSH 服务连接 Termux
创建时间: 2025-10-24
修改时间: 2025-10-24
标签:
  - Termux
  - SSH
---
Termux 官网：<https://termux.dev/en/>

Note：Termux 开启的 sshd 服务用的是8022端口，而不是常用的22端口

- 安装 openssh 并打开：

```bash
pkg install openssh
```

- 开启 openssh 服务：

```bash
sshd
```

- 使用 ifconfig 查询本地局域网 IP

```bash
~> ifconfig
Warning: cannot open /proc/net/dev (Permission denied). Limited output.
lo: flags=73<UP,LOOPBACK,RUNNING>  mtu 65536
	  inet 127.0.0.1  netmask 255.0.0.0
	  unspec 00-00-00-00-00-00-00-00-00-00-00-00-00-00-00-00  txqueuelen 1000  (UNSPEC)

rmnet_data3: flags=65<UP,RUNNING>  mtu 1400
	  inet 10.52.96.133  netmask 255.255.255.252
	  unspec 00-00-00-00-00-00-00-00-00-00-00-00-00-00-00-00  txqueuelen 1000  (UNSPEC)

wlan0: flags=4163<UP,BROADCAST,RUNNING,MULTICAST>  mtu 1500
	  inet 192.168.43.1  netmask 255.255.255.0  broadcast 192.168.43.255
	  unspec 00-00-00-00-00-00-00-00-00-00-00-00-00-00-00-00  txqueuelen 3000  (UNSPEC)
```

- 使用 whoami 查看 termux 用户名

```bash
whoami
```

- 使用命令 passwd 设置 Termux 密码

```bash
passwd
```

- Termux 设置自动开启 ssh

```bash
echo "sshd" >> ~/.bashrc
```

- 如果你想要SSH客户端自动接受新的主机密钥，可以使用以下命令：

```bash
ssh -o StrictHostKeyChecking=no -p 8022 user@192.168.43.1
```


> [!NOTE] Title
> 但请注意，这样做会降低安全性，因为它允许SSH客户端自动接受任何新的主机密钥，而不进行验证。

