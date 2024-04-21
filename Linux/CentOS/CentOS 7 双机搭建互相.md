## 两台机器互推文件

```sh
Connecting to 192.168.150.134:22...
Connection established.
To escape to local shell, press 'Ctrl+Alt+]'.

WARNING! The remote SSH server rejected X11 forwarding request.
Last login: Thu Feb 17 21:19:16 2022 from 192.168.150.134

 _____  ___   ____  _____ 
|  ___|/ _ \ |  _ \|_   _|
| |_  | | | || |_) | | |  
|  _| | |_| ||  _ <  | |  
|_|    \___/ |_| \_\ |_|  
                          

[root@Fort ~]# ssh-keygen -t rsa
Generating public/private rsa key pair.
Enter file in which to save the key (/root/.ssh/id_rsa): 
Created directory '/root/.ssh'.
Enter passphrase (empty for no passphrase): 
Enter same passphrase again: 
Your identification has been saved in /root/.ssh/id_rsa.
Your public key has been saved in /root/.ssh/id_rsa.pub.
The key fingerprint is:
SHA256:jdb+Oagpz+yf1Ewv6LIIq3xirPnuhJZ7i5bww9r4xt8 root@Fort
The key's randomart image is:
+---[RSA 2048]----+
|                 |
|                 |
|                 |
|         +       |
|        S o .    |
|...    . . = .   |
|oBo .     +.+ .  |
|.O%..+.+.+.o.o   |
|*%XB+ E+O=+ o.   |
+----[SHA256]-----+
[root@Fort ~]# rsync -auvz /root/.ssh/id_rsa.pub root@192.168.150.128:/root/.ssh/authorized_keys
The authenticity of host '192.168.150.128 (192.168.150.128)' can't be established.
ECDSA key fingerprint is SHA256:uLTfPtgfAO1fpKBJkgPvjnnmsTPPGf0pJ0ktcHSXql0.
ECDSA key fingerprint is MD5:db:2e:75:cb:d3:aa:10:21:e7:5d:91:1d:17:2c:ff:b7.
Are you sure you want to continue connecting (yes/no)? yes
Warning: Permanently added '192.168.150.128' (ECDSA) to the list of known hosts.
root@192.168.150.128's password: 
sending incremental file list
id_rsa.pub

sent 421 bytes  received 35 bytes  60.80 bytes/sec
total size is 391  speedup is 0.86
[root@Fort ~]# rsync -auvz /root/.ssh/id_rsa.pub root@192.168.150.128:/root/.ssh/authorized_keys
sending incremental file list

sent 49 bytes  received 12 bytes  122.00 bytes/sec
total size is 391  speedup is 6.41
您在 /var/spool/mail/root 中有邮件
[root@Fort ~]# rsync -auvz /root/.ssh/id_rsa.pub root@192.168.150.128:/root/.ssh/authorized_keys
sending incremental file list

sent 49 bytes  received 12 bytes  122.00 bytes/sec
total size is 391  speedup is 6.41
[root@Fort ~]# 
```

## 指令记录

```sh
ssh-keygen -t rsa  # 生成公私钥
rsync -auvz /root/.ssh/id_rsa.pub root@192.168.150.134:/root/.ssh/authorized_keys
rsync  -auvze 'ssh -p 220' /root/liuzonglin/ root@10.96.128.133:/root/liuzonglin/authorized_keys  # 同步公钥到指定机器并改名为authorized_keys

ssh -p 22 root@192.168.133.11

/etc/init.d/keepvlied stop
/etc/init.d/keepvlied start 
keep 做负载浮动IP keep在A B定时器检测Akeep是否正常，如果不正常 B就启动keep
```

## rsync 出错

<https://www.cnblogs.com/liuzonglin/p/16304128.html>
