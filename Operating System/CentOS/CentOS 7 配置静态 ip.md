修改启动协议 BOOTPROTO=static 根据大家自己的环境，ip地址可能略有不同。

接下来重启网络服务 systemctl restart network 重启之后 xshell可能无响应，这是因为ip变了，修改xshell配置中的ip重新连接即可。

```sh
[root@root]# vim /etc/sysconfig/network-scripts/ifcfg-ens32  # 修改网卡配置信息
TYPE=Ethernet
BOOTPROTO=dhcp            # 设置 static
DEFROUTE=yes
PEERDNS=yes
PEERROUTES=yes
IPV4_FAILURE_FATAL=no
IPV6INIT=yes
IPV6_AUTOCONF=yes
IPV6_DEFROUTE=yes
IPV6_PEERDNS=yes
IPV6_PEERROUTES=yes
IPV6_FAILURE_FATAL=no
IPADDR=192.168.31.124     # 设置 ip
NETMASK=255.255.192.0     # 设置子网掩码
GATEWAY=192.168.1.1       # 设置网关
IPV6ADDR=
IPV6PREFIX=
IPV6_DEFAULTGW=
ONBOOT=yes                # 修改 ONBOOT=yes
DNS1=114.114.114.114      # 设置 DNS
DNS2=
HWADDR=8c:1c:da:43:34:e3
DEVICE=GE0-0
NAME=GE0-0
```

重启网络服务

```sh
systemctl restart network
```

* **问题：**

  linux 本身浏览器可以上网但 ping 不通

  ```sh
  echo "nameserver 114.114.114.114" >> /etc/resolv.conf    # 电信的DNS
  echo "nameserver 8.8.8.8" >> /etc/resolv.conf    # googel的DNS
  ```

  如果还是无法 ping 外网 `netstat -rn` 查看路由中的网卡设置

  `route add  default gw 192.168.1.1` 添加网络路由效果如下如：
