# Docker 安装 redis

## 拉取 redis 镜像

```sh
docker pull redis
```

## 创建本地映射文件目录/文件

```sh
mkdir -p /mydata/redis/conf
touch /mydata/redis/conf/redis.conf
```

## 运行容器并开机自启

```sh
docker run -p 6379:6379 --restart=always --name redis \
    -v /mydata/redis/data:/data \
    -v /mydata/redis/conf/redis.conf:/etc/redis/redis.conf \
    -d redis redis-server /etc/redis/redis.conf
```
