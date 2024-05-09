#!/bin/bash

# 重新加载环境变量
source /etc/profile

# 判断 /usr/local/las/program/network/network.txt 与 /usr/local/las/program/network/@/network.txt 的MD5值

# 先执行jar包; 
java -jar /usr/local/las/program/network/networkmac.jar || exit 

# 清理network.txt文件中的隐藏符号 ^M
dos2unix /usr/local/las/program/network/network.txt

# 判断重启后判断md5值是否相同；如果md5不同配置网卡配置文件
N1=`md5sum /usr/local/las/program/network/network.txt | awk {'print $1'}`
N2=`md5sum /usr/local/las/program/network/contrast/network.txt | awk {'print $1'}`
if [[ "${N1}" == "${N2}" ]]; then
    echo "文件md5相同"
else
    echo '文件md5不同'
    # 把处理的数据写入到network.txt 文件当中
    /usr/local/las/program/network/insertName.sh
fi