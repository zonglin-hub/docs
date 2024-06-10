# Docker 部署 Redis

参考资料：

- [史上最详细Docker安装Redis](https://blog.csdn.net/weixin_45821811/article/details/116211724)

---

**拉取镜像**

```sh
docker pull redis:latest
```

**创建数据卷**

```sh
# 创建本地映射目录
mkdir -p /mydata/redis/data
# 创建本地映射文件
touch /mydata/redis/conf/redis.conf
```

参数说明：

- -p：多级目录创建

**启动容器**

```sh
docker run --restart=always --log-opt max-size=100m --log-opt max-file=2 \
    -p 6379:6379 --name lcloud-redis \
    -v /home/zonglin/redis/redis.conf:/etc/redis/redis.conf \
    -v /home/zonglin/redis/data:/data \
    -d redis:latest redis-server /etc/redis/redis.conf --appendonly yes
```

参数说明：

- -p 6379:6379：映射容器服务的 6379 端口到宿主机的 6379 端口。外部可以直接通过宿主机ip:6379 访问到 Redis 的服务
- --restart：开机自启
- --name：指定容器的hostname
- -v：映射数据卷，宿主机的目录地址，后者则是容器的目录地址
- -d：分离模式: 在后台运行

<details><summary><b>操作明细：</b></summary>

```sh
[root@localhost ~]# docker pull redis:latest # 最新版本的镜像
latest: Pulling from library/redis
Digest: sha256:db485f2e245b5b3329fdc7eff4eb00f913e09d8feb9ca720788059fdc2ed8339
Status: Image is up to date for redis:latest
docker.io/library/redis:latest
[root@localhost ~]# docker images # 查看本地镜像
REPOSITORY   TAG       IMAGE ID       CREATED         SIZE
redis        latest    7614ae9453d1   9 months ago    113MB
[root@localhost bin]# docker run -itd --name redis-test -p 6379:6379 redis # 运行容器
70463da02fbb2437c288aa2259760280b23a7c884d75509ee73535f3aa963732
[root@localhost bin]# docker ps # 查看运行容器
CONTAINER ID   IMAGE     COMMAND                  CREATED          STATUS          PORTS                                       NAMES
70463da02fbb   redis     "docker-entrypoint.s…"   12 seconds ago   Up 11 seconds   0.0.0.0:6379->6379/tcp, :::6379->6379/tcp   redis-test
[root@localhost bin]# docker exec -it redis-test /bin/bash # 连接测试使用 redis 服务
root@70463da02fbb:/data# redis-cli # 连接 redis 客户端
127.0.0.1:6379> ping
PONG
127.0.0.1:6379> set test 1
OK
127.0.0.1:6379> get test
"1"
127.0.0.1:6379> 
```

</details>
