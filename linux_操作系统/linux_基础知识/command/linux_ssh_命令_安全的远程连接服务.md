# ssh 安全的远程连接服务

openssh 套件中的客户端连接工具。\
ssh 命令 是 openssh 套件中的客户端连接工具，可以给予 ssh 加密协议实现安全的远程登录服务器。

目录

- [ssh 安全的远程连接服务](#ssh-安全的远程连接服务)
    - [语法](#语法)
        - [选项](#选项)
        - [参数](#参数)
    - [示例](#示例)

## 语法

```shell
ssh [选项] [参数]
```

### 选项

```shell
-1：强制使用ssh协议版本1；
-2：强制使用ssh协议版本2；
-4：强制使用IPv4地址；
-6：强制使用IPv6地址；
-A：开启认证代理连接转发功能；
-a：关闭认证代理连接转发功能；
-b：使用本机指定地址作为对应连接的源ip地址；
-C：请求压缩所有数据；
-F：指定ssh指令的配置文件；
-f：后台执行ssh指令；
-g：允许远程主机连接主机的转发端口；
-i：指定身份(私钥)文件；
-l：指定连接远程服务器登录用户名；
-N：不执行远程指令；
-o：指定配置选项；
-p：指定远程服务器上的端口；
-q：静默模式；
-X：开启X11转发功能；
-x：关闭X11转发功能；
-y：开启信任X11转发功能。
```

### 参数

- 远程主机：指定要连接的远程ssh服务器；
- 指令：要在远程ssh服务器上执行的指令。

## 示例

```shell
# ssh 用户名@远程服务器地址
ssh user1@172.24.210.101
# 指定端口
ssh -p 2211 root@140.206.185.170

# ssh 大家族
ssh -p 22 user@ip  # 默认用户名为当前用户名，默认端口为 22
ssh-keygen # 为当前用户生成 ssh 公钥 + 私钥
ssh-keygen -f keyfile -i -m key_format -e -m key_format # key_format: RFC4716/SSH2(default) PKCS8 PEM
ssh-copy-id user@ip:port # 将当前用户的公钥复制到需要 ssh 的服务器的 ~/.ssh/authorized_keys，之后可以免密登录
```

连接远程服务器

```shell
ssh username@remote_host
```

连接远程服务器并指定端口

```shell
ssh -p port username@remote_host
ssh -p '22' 192.168.1.102  # 指定 ip 端口 连接服务器
```

使用密钥文件连接远程服务器

```shell
ssh -i path/to/private_key username@remote_host
```

在本地执行远程命令

```shell
ssh username@remote_host "command"
```

在本地复制文件到远程服务器

```shell
scp path/to/local_file username@remote_host:/path/to/remote_directory
```

在远程服务器复制文件到本地

```shell
scp username@remote_host:/path/to/remote_file path/to/local_directory
```

在本地端口转发到远程服务器

```shell
ssh -L local_port:remote_host:remote_port username@remote_host
```

在远程服务器端口转发到本地

```shell
ssh -R remote_port:local_host:local_port username@remote_host
```
