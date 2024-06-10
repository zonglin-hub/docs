# Docker 安装可视化工具 Portainer 中文版

参考文档

- [安装中文版 portainer-ce](https://www.jianshu.com/p/818a00d189b5)
- [Docker可视化工具Portainer的安装和使用](https://blog.csdn.net/fly910905/article/details/113415899)
- [安装中文版 portainer-ce](https://www.jianshu.com/p/818a00d189b5)
- [Docker可视化工具Portainer的安装和使用](https://blog.csdn.net/fly910905/article/details/113415899)

---

## 拉取镜像

```sh
docker pull portainer/portainer-ce
docker pull 6053537/portainer-ce  # 直接用汉化版镜像
```

## 创建数据卷 portainer_data

```sh
docker volume create portainer_data # 首先创建一个数据卷portainer_data
```

## 运行容器

```sh
docker run -d -p 7000:9000 --restart=always \
    -v /var/run/docker.sock:/var/run/docker.sock \
    -v portainer_data:/usr/local/program/docker/portainer/data \
    --name lcloud-prtainer 6053537/portainer-ce
```

参数说明：

- -p 7000:9000 \ 9000 为 portainer 默认端口，7000 映射本地端口
- --restart=always \ 重启策略
- -v /var/run/docker.sock:/var/run/docker.sock \ 映射 docker 的管理权限
- -v portainer_data:/usr/local/program/docker/portainer/data \ 映射容器内部数据
- --name lcloud-prtainer \ 设置容器名称
- --net docker_default \ 设置容器使用那个网络
- 6053537/portainer-ce \ 指定容器

## 访问路径

- <http://192.168.1.102:7000/#!/2/docker/dashboard>
