# Redis安装

我们这里还是使用Windows安装Redis服务器，但是官方指定是安装到Linux服务器上，我们后面学习了Linux之后，再来安装到Linux服务器上。由于官方并没有提供Windows版本的安装包，我们需要另外寻找：

* 官网地址：[https://redis.io](https://redis.io)
* GitHub Windows版本维护地址：[https://github.com/tporadowski/redis/releases](https://github.com/tporadowski/redis/releases)

## CentOS7 普通安装

[Redis官方网站](https://redis.io/)

### 环境安装

```sh
yum install -y gcc-C++ gcc wget vim
```

> 参数说明：
>
> * -y：自动选择 yes

### 下载安装包

```sh
wget https://download.redis.io/redis-stable.tar.gz
```

### 解压到指定路径

```sh
tar -xzvf redis-stable.tar.gz -C /usr/local/ && cd /usr/local/redis-stable # 解压到指定路径
```

参数说明：

* -x：--extract, --get 解压文件
* -z：--gzip, --ungzip 有 gz 属性的软件包
* -v：--verbose 详细显示处理的文件
* -f：--file [HOSTNAME:]F 指定存档或设备，后接文件名称
* -C：解压到指定路径

### 编译 并 安装

```sh
make && make install
```

### 修改 redis.conf 配置信息

```sh
# 设置后台启动
daemonize yes
# 默认端口
tls-port 6379
# 默认 16 个数据库
databases 16
```

### 配置启动脚本

启动脚本 `redis_init_script` 位于位于 Redis 的 `/utils/` 目录下

```sh
# 设置 init.d 启动脚步
cp /usr/local/redis-stable/utils/redis_init_script /etc/init.d/redis

# 修改服务配置
mkdir /etc/redis && cp redis.conf /etc/redis/6379.conf

# 设置开机自启
chkconfig redis on
```

### 设置 redis 6379 外网连接

```sh
# 把redis的端口放到防火墙计划中
/sbin/iptables -I INPUT -p tcp --dport 6379 -j ACCEPT
/etc/rc.d/init.d/redis stop
# 修改 redis.conf 以下内容
# 修改前
bind 127.0.0.1
protected-mode yes
# 修改后
# bind 127.0.0.1
protected-mode no
# 重启redis 服务
```

### 安装目录说明

查看默认安装目录：/usr/local/bin

* redis-benchmark：性能测试工具，可以在自己本子运行，看看自己本子性能如何
* redis-check-aof：修复有问题的AOF文件，rdb和aof后面讲
* redis-check-dump：修复有问题的dump.rdb文件
* redis-sentinel：Redis集群使用
* redis-server：Redis服务器启动命令
* redis-cli：客户端，操作入口
