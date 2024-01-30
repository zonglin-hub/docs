# Docker 网络设置

参考文档

- <https://mp.weixin.qq.com/s/nnV7tsn5dd1mtjB0GObLzA>
- <https://blog.csdn.net/weixin_43841155/article/details/123821258>

---

自定义网络

```sh
# 查看所有网桥
# docker network ls

# 查看网桥详细信息
# docker network inspect <网桥名>

# 创建网桥
# docker network create --driver bridge --subnet 192.168.0.0/16 --gateway 192.168.0.1 <网桥名>

# 运行容器使用自定义网桥
# docker run -d  -p 8081:8080 --name tomcat-net-01 --net <网桥名> tomcat

# 容器连接指定网络
# docker network connect <网桥名> <容器名>

# Ubuntu系统 安装 bridge-utils
# apt install bridge-utils

# 查看网桥状态
# brctl show

# 关闭此网桥
# ifconfig <bridge name> down

# 删除网桥
# brctl delbr <bridge name>

# 删除 docker 网桥
# docker network rm <NETWORK ID>
```
