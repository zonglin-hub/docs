# VSCode 连接远程 Linux 开发

`Remote - SSH` 使用 SSH 打开远程机器上的任何文件夹，并利用 VSCode 的完整特性集。

配置本地 `C:\Users\zonglin\.ssh\config`

阅读更多关于SSH配置文件的信息：<https://linux.die.net/man/5/ssh_config>

`Host` 服务地址 `HostName` 服务名  `User` 服务器用户

```config
Host 192.168.1.100
    HostName 192.168.1.100
    User root
```
