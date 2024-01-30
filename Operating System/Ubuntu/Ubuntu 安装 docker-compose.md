# Ubuntu 安装 docker-compose

docker-compose github 下载地址：[Releases · docker/compose (github.com)](https://github.com/docker/compose/releases)

下载安装

```sh
sudo curl -L https://github.com/docker/compose/releases/download/1.24.1/docker-compose-`uname -s`-`uname -m` -o /usr/local/bin/docker-compose
```

文件授权

```sh
sudo chmod +x /usr/local/bin/docker-compose
```

查看版本

```sh
docker-compose -v
```
