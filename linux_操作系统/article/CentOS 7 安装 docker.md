# CentOS 7 安装卸载 docker

```sh
# 查看内核版本，需要3.10以上
uname -r

# 更新 yum 软件包索引
yum makecache fast

# 安装工具包
yum install -y yum-utils

# 设置镜像厂库
yum-config-manager \
    --add-repo \
    http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo

# 安装 docker Engine-Community
yum install docker-ce docker-ce-cli containerd.io docker-compose-plugin

# 启动 docker
systemctl start docker

# 判断是否装成功
docker version

# centos7 设置 docker 开机自启
systemctl enable docker

# 执行完毕需要重启系统
init 6
```

- 阿里云镜像加速

    - [Docker配置阿里云镜像加速](https://blog.csdn.net/m0_46665077/article/details/124248727)

## 卸载 docker

```sh
# 卸载旧版本 docker
yum remove docker \
    docker-client \
    docker-client-latest \
    docker-common \
    docker-latest \
    docker-latest-logrotate \
    docker-logrotate \
    docker-engine

# 卸载 docker 依赖
yum remove docker-ce docker-ce-cli containerd.io docker-compose-plugin

# 删除资源
rm -rf /var/lib/docker
```
