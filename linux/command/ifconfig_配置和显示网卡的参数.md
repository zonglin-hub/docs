# ifconfig_配置和显示网卡的参数

**ifconfig 命令** 被用于配置和显示 Linux 内核中网络接口的网络参数。
用 ifconfig 命令配置的网卡信息，在网卡重启后机器重启后，配置就不存在。
要想将上述的配置信息永远的存的电脑里，那就要修改网卡的配置文件了。

###  实例 

**显示网络设备信息（激活状态的）** 

<pre>
❯ ifconfig
eth0      Link encap:Ethernet  HWaddr 00:16:3E:00:1E:51  
          inet addr:10.160.7.81  Bcast:10.160.15.255  Mask:255.255.240.0
          UP BROADCAST RUNNING MULTICAST  MTU:1500  Metric:1
          RX packets:61430830 errors:0 dropped:0 overruns:0 frame:0
          TX packets:88534 errors:0 dropped:0 overruns:0 carrier:0
          collisions:0 txqueuelen:1000
          RX bytes:3607197869 (3.3 GiB)  TX bytes:6115042 (5.8 MiB)

lo        Link encap:Local Loopback  
          inet addr:127.0.0.1  Mask:255.0.0.0
          UP LOOPBACK RUNNING  MTU:16436  Metric:1
          RX packets:56103 errors:0 dropped:0 overruns:0 frame:0
          TX packets:56103 errors:0 dropped:0 overruns:0 carrier:0
          collisions:0 txqueuelen:0
          RX bytes:5079451 (4.8 MiB)  TX bytes:5079451 (4.8 MiB)
</pre>

说明：

**eth0** 表示第一块网卡，其中 HWaddr 表示网卡的物理地址，
可以看到目前这个网卡的物理地址MAC地址是 00:16:3E:00:1E:51。

**inet addr** 用来表示网卡的 IP 地址，此网卡的 IP 地址是 10.160.7.81，
广播地址 Bcast:10.160.15.255，掩码地址 Mask:255.255.240.0。

**lo** 是表示主机的回坏地址，这个一般是用来测试一个网络程序，
但又不想让局域网或外网的用户能够查看，只能在此台主机上运行和查看所用的网络接口。
比如把 httpd 服务器的指定到回坏地址，在浏览器输入 127.0.0.1 就能看到你所架 WEB 网站了。但只是您能看得到，局域网的其它主机或用户无从知道。

- 第一行：连接类型：Ethernet（以太网）HWaddr（硬件mac地址）。
- 第二行：网卡的IP地址、子网、掩码。
- 第三行：UP（代表网卡开启状态）RUNNING（代表网卡的网线被接上）MULTICAST（支持组播）MTU:1500（最大传输单元）：1500字节。
- 第四、五行：接收、发送数据包情况统计。
- 第七行：接收、发送数据字节数统计信息。

**启动关闭指定网卡：** 

```shell
ifconfig eth0 up
ifconfig eth0 down
```

`ifconfig eth0 up` 为启动网卡eth0，`ifconfig eth0 down` 为关闭网卡eth0。
ssh 登陆 linux 服务器操作要小心，关闭了就不能开启了，除非你有多网卡。

**为网卡配置和删除IPv6地址：** 

```shell
ifconfig eth0 add 33ffe:3240:800:1005::2/64    #为网卡eth0配置IPv6地址
ifconfig eth0 del 33ffe:3240:800:1005::2/64    #为网卡eth0删除IPv6地址
```

**用ifconfig修改MAC地址：** 

```shell
ifconfig eth0 hw ether 00:AA:BB:CC:dd:EE
```

**配置IP地址：** 

```shell
❯ ifconfig eth0 192.168.2.10
❯ ifconfig eth0 192.168.2.10 netmask 255.255.255.0
❯ ifconfig eth0 192.168.2.10 netmask 255.255.255.0 broadcast 192.168.2.255
```

**启用和关闭arp协议：** 

```shell
ifconfig eth0 arp    #开启网卡eth0 的arp协议
ifconfig eth0 -arp   #关闭网卡eth0 的arp协议
```

**设置最大传输单元：** 

```shell
ifconfig eth0 mtu 1500    #设置能通过的最大数据包大小为 1500 bytes
```

**其它实例**

```shell
ifconfig   #处于激活状态的网络接口
ifconfig -a  #所有配置的网络接口，不论其是否激活
ifconfig eth0  #显示eth0的网卡信息
```
