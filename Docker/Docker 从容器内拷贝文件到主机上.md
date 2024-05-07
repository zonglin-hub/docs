# 从容器内拷贝文件到主机上

```bash
[root@localhost ~]# docker images
REPOSITORY   TAG       IMAGE ID       CREATED         SIZE
redis        latest    7614ae9453d1   9 months ago    113MB
mysql        latest    3218b38490ce   9 months ago    516MB
centos       latest    5d0da3dc9764   12 months ago   231MB
[root@localhost ~]# docker run -it centos /bin/bash
[root@30dde7df25fb /]# cd /root/
[root@30dde7df25fb ~]# ls
anaconda-ks.cfg  anaconda-post.log  original-ks.cfg
[root@30dde7df25fb ~]# > Test.java
[root@30dde7df25fb ~]# ls
Test.java  anaconda-ks.cfg  anaconda-post.log  original-ks.cfg
[root@30dde7df25fb ~]# exit
exit
[root@localhost ~]# docker images
REPOSITORY   TAG       IMAGE ID       CREATED         SIZE
redis        latest    7614ae9453d1   9 months ago    113MB
mysql        latest    3218b38490ce   9 months ago    516MB
centos       latest    5d0da3dc9764   12 months ago   231MB
[root@localhost ~]# docker ps
CONTAINER ID   IMAGE     COMMAND   CREATED   STATUS    PORTS     NAMES
[root@localhost ~]# docker ps -qa
30dde7df25fb
[root@localhost ~]# docker cp 30dde7df25fb:/root/Test.java /root/
[root@localhost ~]# ls
anaconda-ks.cfg  dump.rdb  Test.java
```
