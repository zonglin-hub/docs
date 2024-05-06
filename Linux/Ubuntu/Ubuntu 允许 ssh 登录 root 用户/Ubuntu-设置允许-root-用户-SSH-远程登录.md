# Ubuntu 设置允许 root 用户 SSH 远程登录

参考资料

- [ubuntu 下允许 root 用户 ssh 远程登录]
- [Ubuntu 中开启 ssh 允许 root 远程 ssh 登录的方法]

[ubuntu 下允许 root 用户 ssh 远程登录]: https://www.cnblogs.com/ajianbeyourself/p/4220274.html
[Ubuntu 中开启 ssh 允许 root 远程 ssh 登录的方法]: https://cloud.tencent.com/developer/article/1445519

---

如果你使用的是树莓派或是云服务器，那么你会得到一个公网的IP地址，以及默认的用户名和密码。
由于服务器安装的 Ubuntu 并不是在我们的电脑上运行的，那么我们怎么去远程操作呢？

比如我们要远程操作一台 Windows 电脑，直接使用远程桌面连接即可。
但是 Ubuntu 上来就是命令行，这种情况下要实现远程连接就只能使用 SSH 终端。

SSH 是一种网络协议，用于计算机之间的加密登录。
如果一个用户从本地计算机，使用 SSH 协议登录另一台远程计算机，我们就可以认为，
这种登录是安全的，即使被中途截获，密码也不会泄露。
最早的时候，互联网通信都是明文通信，一旦被截获，内容就暴露无疑。
1995 年，芬兰学者 Tatu Ylonen 设计了 SSH 协议，将登录信息全部加密，
成为互联网安全的一个基本解决方案，迅速在全世界获得推广，目前已经成为 Linux 系统的标准配置。

云服务器上安装的 Ubuntu 默认都是自带了 OpenSSH 服务端的，我们可以直接连接。
如果你的 Ubuntu 服务器上没有安装 OpenSSH 服务器端，那么可以输入命令进行安装：

这里我们使用 WindTerm 来进行 SSH 登陆，WindTerm 官网：<https://github.com/kingToolbox/WindTerm>

查看 ip 信息

```shell
ifconfig
```

若提示找不到命令，则需安装 net-tools

```shell
sudo apt install net-tools
```

安装 openssl

```shell
sudo apt-get install update
sudo apt-get install openssh-server # 输入后还需要你输入当前用户的密码才可以执行，至于为什么我们后面会说
```

启用 root 用户

```shell
sudo passwd root # 修改密码后就启用了
```

> [!NOTE] 注意
> sshd_config 的配置无误，但是 SSH 依旧是无法连接，这可能就是 root 没有设置密码导致。
>
> 还有一种出现版本兼容问题，通常为低版本连接高版本会出现算法不兼容的情况
>
> ```shell
> KexAlgorithms curve25519-sha256@libssh.org,ecdh-sha2-nistp256,ecdh-sha2-nistp384,ecdh-sha2-nistp521,diffie-hellman-group14-sha1 # 启用过期算法。注意这里的算法可能存在漏洞，建议升级至最新版本
> PasswordAuthentication yes # 要禁用隧道明文密码，请在此处更改为否！
> ```

通过执行 `vim /etc/ssh/sshd_config` 来编辑 SSH 的配置文件

```shell
PermitRootLogin yes                   # 允许 root 用户以任何认证方式登录（貌似也就两种认证方式：用户名密码认证，公钥认证）
PermitRootLogin without-password      # 只允许 root 用 public key 认证方式登录
PermitRootLogin no                    # 不允许 root 用户以任何认证方式登录
```

启动 ssh 服务

```shell
sudo service ssh start  或者  sudo /etc/init.d/ssh start
```
