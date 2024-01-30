# redis.conf 文件参数说明

```bash
# 不区分大小写
# units are case insensitive so 1GB 1Gb 1gB are all the same.

# include 组合多个配置问题
# include /path/to/local.conf
# include /path/to/other.conf
# include /path/to/fragments/*.conf

# 默认绑定 ip
bind 127.0.0.1 -::1

# 保护模式
protected-mode yes

# 端口
port 6379

# Specify the server verbosity level.
# This can be one of:
# debug (a lot of information, useful for development/testing)
# verbose (many rarely useful info, but not a mess like the debug level)
# notice (moderately verbose, what you want in production probably)
# warning (only very important / critical messages are logged)
# 默认日志输出级别 notice
loglevel notice

# 日志文件名
logfile ""

# 持久化错误是否继续工作
stop-writes-on-bgsave-error yes

# 是否压缩 *.rdp 文件
rdbcompression yes

# 是否校验 rdp 文件
rdbchecksum yes

# rdp 文件的默认保存目录（当前目录）
dir ./

# 设置密码（默认null）
# requirepass foobared

# 默认最大客户端数量
# maxclients 10000
# 默认最大内存限制
# maxmemory <bytes>


# maxmemory-policy 六种方式
# 1. volatile-lru：只对设置了过期时间的key进行LRU（默认值）
# 2. allkeys-lru ： 删除lru算法的key
# 3. volatile-random：随机删除即将过期key
# 4. allkeys-random：随机删除
# 5. volatile-ttl ： 删除即将过期的
# 6. noeviction ： 永不过期，返回错误
# 内存达到限制值的处理策略（redis 中的默认的过期策略是 volatile-lru ）
# maxmemory-policy noeviction

# 是否开启 AOF （默认不开启）
appendonly no
# 默认 AOF 文件名
appendfilename "appendonly.aof"
# 每次修改进行同步
# appendfsync always
# 每秒执行一次同步（数据同步策略）
appendfsync everysec
# 不进行同步 由操作系统进行同步 速度最快
# appendfsync no
```

## redis AOF

开启`appendonly yes`当 `appendonly.aof` 文件存在问题导致 redis 数据无法加载可以使用`redis-check-aof --fix appendonly.aof`恢复文件
