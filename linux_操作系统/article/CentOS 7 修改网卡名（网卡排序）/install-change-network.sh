#!/bin/bash

# 安装并修改网卡名 #

# 删除原有的network
rm -rf /usr/local/las/program/network/

# 永久关闭网络管理命令
systemctl disable NetworkManager

# 安装dos2unix用于清理windows ^M 隐藏符号
rpm -ivh dos2unix-6.0.3-7.el7.x86_64.rpm

# 安装lspci指令
rpm -ivh pciutils-3.5.1-3.el7.x86_64.rpm

# 把network 移动到 program 目录下
mv -fb ./network /usr/local/las/program/ 
# tar -zxvf /usr/local/las/program/network.tar.gz

# 把自启脚本放入到sart_init文件当中。并把文件移动到/etc/init.d/目录下
mv -fb start_init /etc/init.d/

# 更新获取网卡名脚步
mv -fb getNetworkCard.sh /usr/local/las/program/shell/

# 清除原来的文件
rm -rf /etc/init.d/start_init~
rm -rf /usr/local/las/program/shell/getNetworkCard.sh~