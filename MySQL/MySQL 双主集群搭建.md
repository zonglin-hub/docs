# MySQL 双主集群搭建

参考文档：

- [图文结合带你搞懂MySQL日志之relay log（中继日志）](https://www.cnblogs.com/greatsql/p/17052055.html)
- [一个月后，我们又从 MySQL 双主切换成了主 - 从](https://baijiahao.baidu.com/s?id=1738097788602997045&wfr=spider&for=pc)

## MySQL 简介

- MySQL是一个关系型数据库管理系统，由瑞典MySQL AB 公司开发，属于 Oracle 旗下产品。
- MySQL被广泛地应用在 Internet 上的中小型网站中。由于其体积小、速度快、总体拥有成本低，尤其是开放源码这一特点，使得很多公司都采用 MySQL 数据库以降低成本
- MySQL 是一款安全、跨平台、高效的，并与 PHP、Java、Python 等主流编程语言紧密结合的数据库系统
- MySQL 是最流行的关系型数据库管理系统之一

## MySQL 意义及风险

集群好处：

- 高可用性：故障检测及迁移，多节点备份。
- 可伸缩性：新增数据库节点便利，方便扩容。
- 负载均衡：切换某服务访问某节点，分摊单个节点的数据库压力。

集群存在风险：

- 网络分裂：群集还可能由于网络故障而拆分为多个部分，每部分内的节点相互连接，但各部分之间的节点失去连接
- 脑裂：导致数据库节点彼此独立运行的集群故障称为“脑裂”。这种情况可能导致数据不一致，并且无法修复，例如当两个数据库节点独立更新同一表上的同一行时
‍

## MySQL 集群方案

- 方案一：共享存储
    - 一般共享存储采用比较多的是 SAN/NAS 方案。
- 方案二：操作系统实时数据块复制
    - 这个方案的典型场景是 DRBD，DRBD架构(MySQL+DRBD+Heartbeat)
- 方案三：主从复制架构
    - 主从复制(一主多从)
    - MMM架构(双主多从)
    - MHA架构(多主多从)
- 方案四：数据库高可用架构
    - MGR(MySQL Group Replication)，MySQL官方开发的一个实现MySQL高可用集群的一个工具。第一个GA版本正式发布于MySQL5.7.17中
    - Galera
‍

## MySQL 复制

MySQL Replication是MySQL非常出色的一个功能，该功能将一个MySQL实例中的数据复制到另一个MySQL实例中。整个过程是异步进行的，但由于其高效的性能设计，复制的延时非常小。多数的集群方案都基于此功能进行设计。MySQL的复制（replication）是一个异步的复制，从一个MySQLinstace（称之为Master）复制到另一个MySQLinstance（称之Slave）。整个复制操作主要由三个进程完成的，其中两个进程在Slave（Sql进程和IO进程），另外一个进程在Master（IO进程）上

复制分成三步：

1. master 将改变记录到二进制日志(binary log)中（这些记录叫做二进制日志事件，binary log events）；
2. slave 将 master 的 binary log events 拷贝到它的中继日志(relay log)；
3. slave 重做中继日志中的事件，将改变反映它自己的数据。

![image](https://img2023.cnblogs.com/blog/2402369/202309/2402369-20230923154107873-1937175725.png)

‍

实现mysql主主模式，采用互为主从即可既是 master，又是另一台服务器的 slave

![image](https://img2023.cnblogs.com/blog/2402369/202309/2402369-20230923154118501-850163980.png)

​

- 修改配置文件

    修改 MySQL 的配置文件 my.cnf

  ![image](https://img2023.cnblogs.com/blog/2402369/202309/2402369-20230923154146298-618014855.png)

    server-id   可以为任意自然数，必须保证两台mysql主机不重复

    log_bin 启动mysql二进制日志，如果没有配置这个将无法远程链接

    replicate-do-db 指定要同步的数据库

    replicate-ignore-db 指定不同步的数据库

    auto_increment_offset 表示自增长字段从哪个数开始，指字段一次递增多少，他的取值范围是1 .. 65535

    auto_increment_increment 表示自增长字段每次递增的量，指自增字段的起始值，其默认值是1，取值范围是1 .. 65535

    为了避免两台服务器同时做更新时自增长字段的值之间发生冲突。一般在主主同步配置时，需要将两台服务器的auto_increment_increment增长量都配置为2，

    而要把auto_increment_offset分别配置为1和2.

    修改完配置文件之后需要重启mysql服务生效

    注：如果是复制的虚拟机，mysql data/auto.cnf 中server-uuid的值不能相同

    ‍
- salve节点创建复制账号

    192.168.56.103 数据库1：

    `grant replication slave, replication client on ​`​*`.`*​`​ to 'fort'@'192.168.56.105' identified by 'xxxxxxxx';`​

    `flush privileges;`​ // 刷新MySQL的系统权限相关表，否则会无法用账号链接

    192.168.56.105 数据库2：

    `grant replication slave, replication client on *.* to 'fort'@'192.168.56.103' identified by 'xxxxxxx';`​

    `flush privileges;`​ // 刷新MySQL的系统权限相关表，否则会无法用账号链接
    ‍
    每个 slave 使用标准的 MySQL 用户名和密码连接 master。进行复制操作的用户会授予 REPLICATION SLAVE 权限。用户名的密码都会存储在文本文件 `data/master.info`​ 中

- slave 连接 master

  在 master 上执行 `show master status;`​查看日志位置

  ![image](https://img2023.cnblogs.com/blog/2402369/202309/2402369-20230923154206021-1410661849.png)

    slave 使用 CHANGE MASTER TO 语句连接 master

    ```sql
    mysql> change master to
    ->master_host = '192.168.56.103',
    ->master_port = 3306,
    ->master_user = 'fort',
    ->master_password = 'xxxxxxx',
    ->master_log_file = 'binlog.000002',
    ->master_log_pos = 154;
    ```

- 启动同步并查看状态

  执行start slave; 启动启动slave服务

  执行show slave status\G 查看服务状态

  ![image](https://img2023.cnblogs.com/blog/2402369/202309/2402369-20230923154222629-771381995.png)

  主主模式下，两台机器做同样的操作即可实现双 MASTER

## 常见问题

1. 网络不通--保证两台数据库之间3306端口可达
2. 机器id相同--my.cnf中的server-id、auto.cnf中的server-uuid保证两台不一致
3. 主键冲突--my.cnf中auto_increment_increment步进值、auto_increment_offset起始值要设置好
4. pos错误——看看当前日志节点内容与你之前打的一样不，反正重新来一遍准没错
