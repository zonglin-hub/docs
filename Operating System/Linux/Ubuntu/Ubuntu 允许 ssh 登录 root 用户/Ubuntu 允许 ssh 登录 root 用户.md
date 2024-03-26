# Ubuntu 允许 ssh 登录 root 用户

设置 root密码
`sudo passwd root`
`sshd_config 的配置没有问题，还是连接不上，可能是root没有设置密码`

`vim /etc/ssh/sshd_config`

在行尾添加如下内容：

```sh
KexAlgorithms curve25519-sha256@libssh.org,ecdh-sha2-nistp256,ecdh-sha2-nistp384,ecdh-sha2-nistp521,diffie-hellman-group14-sha1
PermitRootLogin yes
PasswordAuthentication yes
```

`sudo systemctl restart sshd`
