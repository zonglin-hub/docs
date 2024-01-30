# Ubuntu下允许root用户ssh远程登录

**参考资料**

- [ubuntu下允许root用户ssh远程登录](https://www.cnblogs.com/ajianbeyourself/p/4220274.html)
- [Ubuntu中开启ssh允许root远程ssh登录的方法](https://cloud.tencent.com/developer/article/1445519)

---

如果你使用的是树莓派或是云服务器，那么你会得到一个公网的IP地址，以及默认的用户名和密码，由于服务器安装的Ubuntu并不是在我们的电脑上运行的，那么我们怎么去远程操作呢？

比如我们要远程操作一台Windows电脑，直接使用远程桌面连接即可，但是Ubuntu上来就是命令行，这种情况下要实现远程连接就只能使用SSH终端。

SSH是一种网络协议，用于计算机之间的加密登录。如果一个用户从本地计算机，使用SSH协议登录另一台远程计算机，我们就可以认为，这种登录是安全的，即使被中途截获，密码也不会泄露。最早的时候，互联网通信都是明文通信，一旦被截获，内容就暴露无疑。1995年，芬兰学者Tatu
Ylonen设计了SSH协议，将登录信息全部加密，成为互联网安全的一个基本解决方案，迅速在全世界获得推广，目前已经成为Linux系统的标准配置。

云服务器上安装的Ubuntu默认都是自带了OpenSSH服务端的，我们可以直接连接，如果你的Ubuntu服务器上没有安装OpenSSH服务器端，那么可以输入命令进行安装：

这里我们使用 WindTerm 来进行 SSH
登陆，官网：<https://github.com/kingToolbox/WindTerm>

查看 ip 信息

```sh
ifconfig
```

若提示找不到命令，则需安装 net-tools

```sh
sudo apt install net-tools
```

安装openssl

```sh
sudo apt-get install update
sudo apt-get install openssh-server # 输入后还需要你输入当前用户的密码才可以执行，至于为什么我们后面会说
```

启用root用户

```sh
sudo passwd root # 修改密码后就启用了
```

vim /etc/ssh/sshd_config

```sh
PermitRootLogin yes                   #允许root用户以任何认证方式登录（貌似也就两种认证方式：用户名密码认证，公钥认证）
PermitRootLogin without-password      #只允许root用public key认证方式登录
PermitRootLogin no                    #不允许root用户以任何认证方式登录
```

启动 ssh 服务

```sh
sudo service ssh start  或者  sudo /etc/init.d/ssh start
```
