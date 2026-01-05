# CentOS7 设置中文环境

- 查看当前字符集

```sh
locale
```

- 查看有没有zh_CN.utf8

```sh
locale -a |grep CN
```

- 安装 langpacks-zh_CN

```sh
yum install -y langpacks-zh_CN
```

- 配置

```sh
 > /etc/locale.conf; echo 'LANG="zh_CN.UTF-8"' >>/etc/locale.conf  
```

- 重启系统

```sh
shutdown -r now
```
