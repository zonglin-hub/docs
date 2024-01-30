
# CentOS7 关闭防火墙或开放端口

## 关闭防火墙

```sh
systemctl status firewalld     # 查看防火墙的状态
systemctl start firewalld # 临时打开防火墙，立即生效。
systemctl stop firewalld      # 临时关闭防火墙，立即生效。
systemctl enable firewalld  # 永久开启防火墙，重启后生效。
systemctl disable firewalld # 永久关闭防火墙，重启后生效。
```

## 开放端口

查看开放的端口号

```sh
firewall-cmd --list-all
```

设置开放的端口号

```sh
firewall-cmd --add-service=http --permanent 
firewall-cmd --add-port=3306/tcp --permanent 
```

重启防火墙

```sh
firewall-cmd --reload
```
