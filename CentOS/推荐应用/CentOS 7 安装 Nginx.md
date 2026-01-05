# CentOS 7 安装 Nginx

目录

- [CentOS 7 安装 Nginx](#centos-7-安装-nginx)
    - [安装环境](#安装环境)
    - [编译安装](#编译安装)
    - [安装成系统服务](#安装成系统服务)
    - [使用 systemctl](#使用-systemctl)
    - [关于防火墙](#关于防火墙)

## 安装环境

```sh
yum install -y gcc pcre pcre-devel zlib zlib-devel
```

> [!NOTE]  
> 如遗漏安装包会导致编译失败

## 编译安装

```sh
./configure --prefix=/usr/local/nginx && make && make install
```

## 安装成系统服务

```sh
# 创建服务脚本
vim /usr/lib/systemd/system/nginx.service
```

服务脚本 [nginx.service](nginx.service)

## 使用 systemctl

```sh
# 重新加载系统服务
systemctl daemon-reload

# 启动服务
systemctl start nginx.service

# 开机启动
systemctl enable nginx.service
```

## 关于防火墙

```sh
# 关闭防火墙
systemctl stop firewalld.service

# 禁止防火墙开机启动
systemctl disable firewalld.service

# 放行端口
firewall-cmd --zone=public --add-port=80/tcp --permanent

# 重启防火墙
firewall-cmd --reload
```
