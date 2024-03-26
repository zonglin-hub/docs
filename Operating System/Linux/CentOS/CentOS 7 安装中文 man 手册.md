# CentOS 7 安装中文 man 手册

```sh
# 更新yum列表
yum update

# 查询yuam列表中 中文man的软件包名称
yum list | grep man.*zh

# 安装上面出现的软件包
yum -y install man-pages-zh-CN.noarch

# 编辑当前用户的bash 配置文件，添加一个cman别名命令
vi .bashrc
alias cman='man -M /usr/share/man/zh_CN'

# 让当前bash从新载入下配置文件
source .bashrc

# 使用举例
cman ls 可查看中文的ls命令使用手册了。
```

---

参考文档：

- [Centos 7 安装中文man手册](https://blog.huati365.com/a710d7f4f788d04e)
