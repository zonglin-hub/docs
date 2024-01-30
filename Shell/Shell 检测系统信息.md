```sh
#!/bin/bash

# 基于 CentOS 7.5 编写

echo "######################### 系统信息 #########################"

OS_TYPE=$(uname)
OS_VER=$(cat /etc/redhat-release)
OS_KER=$(uname -a | awk '{print $3}')
OS_TIME=$(date +%F_%T)
OS_RUN_TIME=$(uptime | awk '{print $3}' | awk -F, '{print $1}')
OS_LAST_REBOOT_TIME=$(who -b | awk '{print $2,$3}')
OS_HOSTNAME=$(hostname)

echo "    系统类型：$OS_TYPE"
echo "    系统版本：$OS_VER"
echo "    系统内核：$OS_KER"
echo "    当前时间：$OS_TIME"
echo "    运行时间：$OS_RUN_TIME"
echo "最后重启时间：$OS_LAST_REBOOT_TIME"
echo "    本机名称：$OS_HOSTNAME"
```
