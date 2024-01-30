# network

修改网卡名称

## 注意

- 问题一：windows 编码格式 ^M 会影响
- 问题二：网卡配置文件当中 static
- 问题三：多余网卡配置文件有影响
- 问题四：关闭 NetworkManager 服务

## 脚本示例

```sh
#!/bin/bash

# 基于 CentOS 7.5 编写
# 写入对应的网卡配置文件

# 时时时间 
datetime=`date +%y%m%d%H%M%S`

function bond0(){
cat > /etc/sysconfig/network-scripts/ifcfg-${a[i]} << lzl
TYPE=Ethernet
BOOTPROTO=static
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
IPADDR=
NETMASK=
GATEWAY=
IPV6ADDR=
IPV6PREFIX=
IPV6_DEFAULTGW=
ONBOOT=yes
DNS1=
DNS2=
HWADDR=${b[i]}
DEVICE=${a[i]}
NAME=${a[i]}
lzl
}

# 移动文件之前先把原有的所有网卡配置文件清空，备
# 创建默认网卡配置文件备份目录
mkdir -p /etc/sysconfig/network-scripts/network_bak/network

# 把所有的ifcfg-enp* 全部的文件移动到指定目录下
mv /etc/sysconfig/network-scripts/ifcfg-e* /etc/sysconfig/network-scripts/network_bak/network/

# 修改文件夹名
mv /etc/sysconfig/network-scripts/network_bak/network /etc/sysconfig/network-scripts/network_bak/network_$datetime

# 获取现在 /etc/sysconfig/network-scripts 目录下所有的 网卡配置文件
# 读取最初版的网卡名；用户清空原始的网卡配置文件中的mac
c_3=0
for item_2 in `cat /usr/local/las/program/network/contrast/network.txt | grep -v '^$' | awk {'print $1'}`; do
    t[c_3]=$item_2
    let c_3++
done

# 读取网卡名称并写入到数组当中
c_1=0
for item in `cat /usr/local/las/program/network/network.txt | grep -v '^$' | awk {'print $1'}`; do
    a[c_1]=$item
    let c_1++
done

# 读取网卡名称的mac地址并写入到数组当中
c_2=0
for item_1 in `cat /usr/local/las/program/network/network.txt | grep -v '^$' | awk {'print $2'}`; do
    b[c_2]=$item_1
    let c_2++
done

# 定义一个空数组
q=()

# 替换板卡，删除多余网卡配置文件
function deleteFile() {
    # 布尔值定义
    flag=false
    # 定义q数组下标
    m=0
    # 循环遍历原版网卡配置文件
    for((i=0;i<${#t[*]};i++)); do
        # 判断文件是否存在路径
        # FILE_1=/etc/sysconfig/network-scripts/ifcfg-${t[i]}
        # echo "$FILE_1-----------------"
        flag=false
        for((j=0;j<${#a[*]};j++)); do
            if [[ "${t[i]}" == "${a[j]}" ]]; then
                # echo "老版本的网卡名称与新版的相同----------新版本中存在老版本的"
                # rm -rf /etc/sysconfig/network-scripts/ifcfg-${t[i]}
                flag=true
            fi
        done
       if [[ "$flag" == "false" ]]; then
           q[m]=${t[i]}
           let m++
       fi
    done
    # echo "${q[@]}"
    for((i=0;i<${#q[*]};i++)); do
        echo "/etc/sysconfig/network-scripts/ifcfg-${q[i]}"
        rm -rf /etc/sysconfig/network-scripts/ifcfg-${q[i]}
        # echo "${i}"
    done
    
}

# 创建网卡文件
function createFile() {

    # 循环遍历网卡文件个数
    for((i=0;i<${#a[*]};i++)); do

        # 判断文件是否存在路径
        FILE=/etc/sysconfig/network-scripts/ifcfg-${a[i]}

        # 如果文件存在；修改 mac 地址
        if [ -f "$FILE" ]; then
            echo "$FILE 文件存在"

            # HWADDR 指定 mac
            s="HWADDR=${b[i]}"

            # sed 修改以HWADDR的文件 路径：FILE
            sed -i "/^HWADDR/c$s" $FILE

        # 如果文件不存在就创建对应文件
        else 
            echo "$FILE 文件不存在"
            
            # 创建对应网卡配置文件 执行文件写入
            bond0
        fi
    done

    # 用于测试使用！开放ip
    # s_1="IPADDR=192.168.45.31"
    # sed -i "/^IPADDR/c$s_1" /etc/sysconfig/network-scripts/ifcfg-GE0-0
    # s_2="GATEWAY=192.168.1.1"
    # sed -i "/^GATEWAY/c$s_2" /etc/sysconfig/network-scripts/ifcfg-GE0-0
    # s_3="NETMASK=255.255.192.0"
    # sed -i "/^NETMASK/c$s_3" /etc/sysconfig/network-scripts/ifcfg-GE0-0

}


# 重新配置grub配置并更新内核
function grub () {

# 备份原有的 grub 文件;
mv /etc/default/grub /etc/default/grub.$datetime

cat > /etc/default/grub << lzl
GRUB_TIMEOUT=5
GRUB_DISTRIBUTOR="$(sed 's, release .*$,,g' /etc/system-release)"
GRUB_DEFAULT=saved
GRUB_DISABLE_SUBMENU=true
GRUB_TERMINAL_OUTPUT="console"
GRUB_CMDLINE_LINUX="crashkernel=auto rd.lvm.lv=centos/root "net.ifnames=0 biosdevname=0" rd.lvm.lv=centos/swap rhgb quiet"
GRUB_DISABLE_RECOVERY="true"
lzl

# 清除grub备份文件
rm -rf grub.*

# sed -i 's/root rd.lvm.lv/root "net.ifnames=0 biosdevname=0" rd.lvm.lv/g' /etc/default/grub
grub2-mkconfig -o /boot/grub2/grub.cfg

# 删除过期版的网卡名、mac 地址文件
rm -rf /usr/local/las/program/network/contrast/*

# 把生成的网卡名、mac 地址文件，备份到指定目录下，用户对比文件的 md5 值
cp -f /usr/local/las/program/network/network.txt /usr/local/las/program/network/contrast/

# 重启系统
echo `shutdown -r now`
# echo `reboot`
}

# 删除多余文件；创建缺失文件；在重启
deleteFile && createFile && grub

```

```sh
#!/bin/bash

netName=$(ip addr|awk '{print $2}'|grep -E '^en|^enp|^eth|^ens|^eno|^GE')
c=0

for item in $netName; do
    a[c]=$item
    let c++
done

for((i=0;i<${#a[*]};i++)); do
    ifconfig ${a[i]} | grep ether | awk {'print $2'}
done
```

```sh
#!/bin/bash

lspci |grep 'Ethernet controller' | awk '{print $1}' | cut -d ":" -f 1
```

```sh
#!/bin/bash

lspci -D -n -v | grep "Device Serial Number" | awk '{print $6}'
```
