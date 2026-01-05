# redis Cluster（主从集群）

`redis 默认本身是主库`​​

## 查看当前库的信息

```sh
> info replication # 查看当前库的信息
```

## 编辑 redis.conf

```bash
# 配置第一个库
port 6379
daemonize yes
pidfile /var/run/redis_6379.pid
logfile "6379.log"
dbfilename dump6379.rdb

# 配置第二个库
port 6380
daemonize yes
pidfile /var/run/redis_6380.pid
logfile "6380.log"
dbfilename dump6380.rdb
......
```

## 重启各服务

```sh
ps -ef | grep redis
```

## 命令配置从机 6380 7381（redis 默认本身是主库）

> 配置从机 `slaveof 127.0.0.1 6379`

## 文件配置

```bash
# replicaof <masterip> <masterport>
# 主机ip：192.168.31.1
# 主机端口：6379

# 配置 6380
replicaof 192.168.31.1 6379
# 如果主机存在密码配置主机密码
# masterauth <master-password>

# 配置 6381
replicaof 192.168.31.1 6379
```

## 主机断了 `slaveo no one`​ 从换主

## 高可用之 Sentinel(哨兵模式)

单哨兵

> 配置哨兵模式

```bash
# 文件名相同
vim sentinel.conf

# 文件内容
# sentinel monitor 监控名称 端口 1
sentinel monitor myredis 127.0.0.1 6379 1
```

> 启动哨兵

```bash
redis-sentinel /usr/local/config/sentinel.conf
```

> 完整的哨兵模式配置文件 [sentinel.conf](sentinel.conf)

‍
