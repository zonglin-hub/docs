# Docker 设置开机自启

参考文档：

- [Docker设置容器开机自启](https://blog.csdn.net/qq_41054313/article/details/104297746)

---

```bash
[root@localhost init.d]# systemctl list-unit-files # 查看开机自启项列表
UNIT FILE                                     STATE   
proc-sys-fs-binfmt_misc.automount             static  
dev-hugepages.mount                           static  
dev-mqueue.mount                              static  
proc-sys-fs-binfmt_misc.mount                 static  
run-vmblock\x2dfuse.mount                     disabled
sys-fs-fuse-connections.mount                 static  
...
[root@localhost init.d]# systemctl list-unit-files | grep docker # 查询状态筛选服务
docker.service                                disabled
docker.socket                                 disabled
[root@localhost init.d]# systemctl enable docker # 设置docker服务开机启动
Created symlink from /etc/systemd/system/multi-user.target.wants/docker.service to /usr/lib/systemd/system/docker.service.
[root@localhost init.d]# systemctl list-unit-files | grep docker
docker.service                                enabled 
docker.socket                                 disabled
[root@localhost init.d]# init 6 # 重启系统
Connection closing...Socket close.

Connection closed by foreign host.

Disconnected from remote host(192.168.1.100) at 18:21:48.
[root@localhost ~]#
[root@localhost ~]#  systemctl status docker # 查看 docker 状态
● docker.service - Docker Application Container Engine
   Loaded: loaded (/usr/lib/systemd/system/docker.service; enabled; vendor preset: disabled)
   Active: active (running) since 二 2022-10-04 18:22:16 CST; 53s ago
     Docs: https://docs.docker.com
 Main PID: 1319 (dockerd)
    Tasks: 11
   Memory: 96.2M
   CGroup: /system.slice/docker.service
           └─1319 /usr/bin/dockerd -H fd:// --containerd=/run/containerd/containerd.sock

10月 04 18:22:15 localhost.localdomain dockerd[1319]: time="2022-10-04T18:22:15.702633344..."
10月 04 18:22:15 localhost.localdomain dockerd[1319]: time="2022-10-04T18:22:15.745525782..."
10月 04 18:22:16 localhost.localdomain dockerd[1319]: time="2022-10-04T18:22:16.055185788..."
10月 04 18:22:16 localhost.localdomain dockerd[1319]: time="2022-10-04T18:22:16.203268252..."
10月 04 18:22:16 localhost.localdomain dockerd[1319]: time="2022-10-04T18:22:16.291487816..."
10月 04 18:22:16 localhost.localdomain dockerd[1319]: time="2022-10-04T18:22:16.378593826..."
10月 04 18:22:16 localhost.localdomain dockerd[1319]: time="2022-10-04T18:22:16.441026971...8
10月 04 18:22:16 localhost.localdomain dockerd[1319]: time="2022-10-04T18:22:16.441153515..."
10月 04 18:22:16 localhost.localdomain systemd[1]: Started Docker Application Container ...e.
10月 04 18:22:16 localhost.localdomain dockerd[1319]: time="2022-10-04T18:22:16.461010934..."
Hint: Some lines were ellipsized, use -l to show in full.
```

参数描述：

- UNIT FILE : 服务名称
- STATE : 状态
    - enabled : 开机启动
    - disabled : 开机不启动

## 设置 Docker 容器开机自启

### 开启自启

在docker启动容器可以增加参数来达到，当docker 服务重启之后 自动启动容器，命令如下：

```sh
# 在使用 docker run 启动容器时，使用 --restart 参数来设置
docker run –restart=always
```

当然如果你的容器已经启动,可以通过update命令进行修改,命令如下：

```sh
# 如果创建时未指定 --restart=always，可通过 update 命令
docker update –restart=always <CONTAINER ID>
```

### 关闭自启

对某一个容器关闭自启动：

```sh
docker update --restart=no <CONTAINER ID>
```

取消所有自启动，命令如下：

```sh
docker update --restart=no $(docker ps -q)
```

参数说明

`--restart` 具体参数值详细信息：

- `no` - 容器退出时，不重启容器
- `on-failure` - 只有在非0状态退出时才从新启动容器
- `always` - 无论退出状态是如何，都重启容器
- `on - failure` - 指定 Docker 将尝试重新启动容器的最大次数。

  默认情况下，Docker将尝试永远重新启动容器 `docker run --restart=on-failure:10 imagesName`
