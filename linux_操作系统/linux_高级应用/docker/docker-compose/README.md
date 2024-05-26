# docker-compose

github 仓库地址：<https://github.com/docker/compose/releases>

## 安装 docker-compose

```shell
# 下载安装
sudo curl -L https://github.com/docker/compose/releases/download/1.24.1/docker-compose-`uname -s`-`uname -m` -o /usr/local/bin/docker-compose

# 文件授权
sudo chmod +x /usr/local/bin/docker-compose

# 查看版本
docker-compose -v
```

## 基础使用

```sh
# 启动yml文件定义的container
docker-compose up

# 后台运行
docker-compose up -d

# 查看up帮助
docker-compose up --help

# -f 指定yml文件
docker-compose -f docker-compose.yml up

# 停止
docker-compose stop
docker-compose start 

# 查看
docker-compose ls

# 停止删除
docker-compose down
docker-compose ps
docker-compose images
docker-compose exec {service_name} {bash}
```
