#!/bin/bash


echo "######################### 系统信息 #########################"

OS_TYPE=$(uname)
OS_TIME=$(date +%F_%T)
OS_HOSTNAME=$(hostname)

# 获取系统版本信息 - 兼容多种Linux发行版
if [ -f /etc/redhat-release ]; then
    # CentOS/RHEL/Fedora
    OS_VER=$(cat /etc/redhat-release)
elif [ -f /etc/os-release ]; then
    # Ubuntu/Debian/Amazon Linux等
    . /etc/os-release
    OS_VER="$NAME $VERSION"
elif [ -f /etc/lsb-release ]; then
    # Ubuntu
    . /etc/lsb-release
    OS_VER="$DISTRIB_DESCRIPTION"
elif [ -f /etc/debian_version ]; then
    # Debian
    OS_VER="Debian $(cat /etc/debian_version)"
elif [ -f /etc/alpine-release ]; then
    # Alpine Linux
    OS_VER="Alpine Linux $(cat /etc/alpine-release)"
else
    OS_VER="Unknown (无法检测发行版)"
fi

# 获取内核版本
OS_KER=$(uname -r)

# 获取系统运行时间
if command -v uptime > /dev/null 2>&1; then
    OS_RUN_TIME=$(uptime | awk '{print $3}' | awk -F, '{print $1}')
    if [ -z "$OS_RUN_TIME" ]; then
        # 有些格式的运行时间可能在不同位置
        OS_RUN_TIME=$(uptime | grep -o 'up.*' | sed 's/up //' | awk '{print $1}')
    fi
else
    OS_RUN_TIME="无法获取"
fi

# 获取最后重启时间
if command -v who > /dev/null 2>&1; then
    OS_LAST_REBOOT_TIME=$(who -b 2>/dev/null | awk '{if(NF>=3) print $2,$3; else print "无法获取"}')
else
    OS_LAST_REBOOT_TIME="无法获取"
fi

# 备用方法：获取运行时间（通过/proc/uptime）
if [ "$OS_RUN_TIME" = "无法获取" ] || [ -z "$OS_RUN_TIME" ]; then
    if [ -f /proc/uptime ]; then
        uptime_sec=$(awk '{print $1}' /proc/uptime)
        days=$(echo "$uptime_sec / 86400" | bc)
        hours=$(echo "($uptime_sec % 86400) / 3600" | bc)
        OS_RUN_TIME="${days}天${hours}小时"
    fi
fi

# 输出信息
echo "    系统类型：$OS_TYPE"
echo "    系统版本：$OS_VER"
echo "    系统内核：$OS_KER"
echo "    当前时间：$OS_TIME"
echo "    运行时间：$OS_RUN_TIME"
echo "    重启时间：$OS_LAST_REBOOT_TIME"
echo "    本机名称：$OS_HOSTNAME"
