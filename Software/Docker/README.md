# Docker 常用指令

参考文档

- [官网 docker 指令帮助文档](https://docs.docker.com/engine/reference/commandline/docker/ "官网 docker 指令帮助文档")
- [docker compose CLI 概述](https://docs.docker.com/compose/reference/)

---

| 指令                                     | 解释                                                            |
| --------------------------------------- | --------------------------------------------------------------- |
| docker version                          | 显示 docker 版本信息                                              |
| docker info                             | 显示 docker 系统信息                                              |
| docker images                           | 查看所有镜像id                                                    |
| docker images -aq                       | 查看所有镜像id                                                    |
| docker ps -a                            | 查看运行容器                                                      |
| docker rm -f $(docker ps -qa)           | 删除所有运行容器                                                   |
| docker search centos                    | 查找镜像                                                          |
| docker pull centos                      | 下载镜像                                                          |
| docker rmi -f feb5d9fea6a5              | 删除指定 id 镜像                                                  |
| docker {start\|kill} feb5d9fea6a5       | 启动                                                            |
| docker attach 3913ce5ec9fd              | 进入当前正在运行的容器                                             |
| docker exec -it 3913ce5ec9fd /bin/bash  | 进入一个新的终端                                                  |
| docker inspect 3913ce5ec9fd             | 查看容器元数据                                                    |
| docker logs -ft --tail 10  9970a5ec9fb5 | 查看容器运行日志                                                  |

## 参考案例

```bash
# 显示 docker 版本信息
docker version 

# 显示 docker 系统信息
docker info 

# 帮助命令
docker [命令] -help 

# 查看所有镜像id
docker images -aq

# 收索镜像
docker search mysql

# 下载镜像
docker pull mysql

# 删除指定id容器
docker rmi -f feb5d9fea6a5 

# 下载 centos 镜像
docker pull centos

# 新建容器并启动，启动镜像并进入容器
# -i 交互式操作 
# -t 终端 
# /bin/bash 交互式 Shell
docker run -it centos /bin/bash

# 停止并退出容器
exit

# 容器不停止退出
Ctrl + P + Q

# 查看运行容器
# -a 展示出所有运行过的容器
# -n=? 显示最近创建的容器
# -q 显示容器编号
docker ps -a -n=? -q

# 删除容器
docker rm feb5d9fea6a5

# 启动
docker start feb5d9fea6a5

# 重启
docker restart feb5d9fea6a5

# 停止
docker stop feb5d9fea6a5
docker kill feb5d9fea6a5

# 通过镜像启动容器，容器停止了
docker run -d centos

# 查看容器运行日志
docker logs -f -t --tail 10  9970a5ec9fb5

# 执行容器并执行shell
[root@localhost ~]# docker run -d centos /bin/bash -c "while true; do echo liuzonglin; sleep 1; done"
3913ce5ec9fd2ed91e93df0c2088485d5fbce5e75ed630d4f7b64aac357ac51f
[root@localhost ~]#
[root@localhost ~]# docker ps
CONTAINER ID   IMAGE     COMMAND                  CREATED          STATUS          PORTS     NAMES
3913ce5ec9fd   centos    "/bin/bash -c 'while…"   27 seconds ago   Up 27 seconds             admiring_haslett
9970a5ec9fb5   centos    "/bin/bash"              17 minutes ago   Up 17 minutes             clever_jennings
[root@localhost ~]# docker logs -ft --tail 10 3913ce5ec9fd
2022-10-01T15:18:18.647064020Z liuzonglin
2022-10-01T15:18:19.651083452Z liuzonglin
2022-10-01T15:18:20.655981698Z liuzonglin
2022-10-01T15:18:21.659626269Z liuzonglin
2022-10-01T15:18:22.665580566Z liuzonglin
[root@localhost ~]# 
[root@localhost ~]# docker top 3913ce5ec9fd # 查看容器进程信息
UID                 PID                 PPID                C                   STIME               TTY                 TIME                CMD
root                72577               72559               0                   23:17               ?                   00:00:00            /bin/bash -c while true; do echo liuzonglin; sleep 1; done
root                73010               72577               0                   23:23               ?                   00:00:00            /usr/bin/coreutils --coreutils-prog-shebang=sleep /usr/bin/sleep 1
[root@localhost ~]# 
[root@localhost ~]# docker inspect 3913ce5ec9fd # 查看容器元数据
[
    {
        "Id": "3913ce5ec9fd2ed91e93df0c2088485d5fbce5e75ed630d4f7b64aac357ac51f",
        "Created": "2022-10-01T15:17:48.112633999Z",
        "Path": "/bin/bash",
        "Args": [
            "-c",
            "while true; do echo liuzonglin; sleep 1; done"
        ],
        "State": {
            "Status": "running",
            "Running": true,
            "Paused": false,
            "Restarting": false,
            "OOMKilled": false,
            "Dead": false,
            "Pid": 72577,
            "ExitCode": 0,
......
[root@localhost ~]# 
[root@localhost ~]# docker ps
CONTAINER ID   IMAGE     COMMAND                  CREATED          STATUS          PORTS     NAMES
3913ce5ec9fd   centos    "/bin/bash -c 'while…"   11 minutes ago   Up 11 minutes             admiring_haslett
9970a5ec9fb5   centos    "/bin/bash"              29 minutes ago   Up 29 minutes             clever_jennings
[root@localhost ~]# 
[root@localhost ~]# 
[root@localhost ~]# 
[root@localhost ~]# 
[root@localhost ~]# docker exec -it 3913ce5ec9fd /bin/bash # 进入一个新的终端
[root@3913ce5ec9fd /]# 
[root@3913ce5ec9fd /]# 
[root@3913ce5ec9fd /]# 
[root@3913ce5ec9fd /]# exit;
exit
[root@localhost ~]# docker ps
CONTAINER ID   IMAGE     COMMAND                  CREATED          STATUS          PORTS     NAMES
3913ce5ec9fd   centos    "/bin/bash -c 'while…"   13 minutes ago   Up 13 minutes             admiring_haslett
9970a5ec9fb5   centos    "/bin/bash"              30 minutes ago   Up 30 minutes             clever_jennings
[root@localhost ~]# docker attach 3913ce5ec9fd # 进入当前正在运行的容器
liuzonglin
liuzonglin
[root@localhost ~]# docker rm -f $(docker ps -qa) # 清理所有运行容器
3913ce5ec9fd
9970a5ec9fb5
a23efb86ac89
8c620d0bc309
79487abc709b
1aca4d59732d
5d8c99947416
b81b70d4a84e
7ae37a790506
[root@localhost ~]# docker ps
CONTAINER ID   IMAGE     COMMAND   CREATED   STATUS    PORTS     NAMES
[root@localhost ~]# docker images
REPOSITORY   TAG       IMAGE ID       CREATED         SIZE
redis        latest    7614ae9453d1   9 months ago    113MB
mysql        latest    3218b38490ce   9 months ago    516MB
centos       latest    5d0da3dc9764   12 months ago   231MB
[root@localhost ~]# 
```
