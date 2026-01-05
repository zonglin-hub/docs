---
标题: Ubuntu 上 kvm 虚拟化命令列表
创建时间: 2025-11-13
修改时间: 2025-11-13
标签:
  - KVM
---


# Ubuntu 上 kvm 虚拟化命令列表


## 检查虚拟化支持

```bash
# 检查虚拟化支持 系统语言英文
lscpu | grep Virtualization

# 检查虚拟化支持 系统语言中文
lscpu | grep 虚拟化 # zh-cn

# 检查虚拟化支持 grep -E "(AMD-V|VT-x)"
lscpu | grep -E "(AMD-V|VT-x)"
```

## 系统更新

```bash
sudo apt update && sudo apt upgrade -y
```

## 安装必要组件

**您的下一步**：您现在不需要再折腾 BIOS 了。虚拟化功能已经完全就绪，您可以开始安装和使用 **KVM**、**VirtualBox** 或 **VMware** 等虚拟机软件了。

例如，安装 KVM：
```bash
# qemu-system-x86       # KVM虚拟化的核心系统仿真器
# virt-manager          # 管理虚拟机的图形化界面
# libvirt-daemon-system # 管理虚拟化平台的守护进程和服务
# virtinst              # 创建虚拟机的命令行工具集
# libvirt-clients       # 连接libvirt守护进程的客户端工具
# bridge-utils          # 配置网络桥接的工具
# ovmf                  # 为虚拟机提供UEFI固件支持
# virt-viewer           # 连接虚拟机图形控制台的查看器
sudo apt install qemu-system-x86 virt-manager libvirt-daemon-system virtinst libvirt-clients bridge-utils ovmf virt-viewer
```
然后打开 `virt-manager` 就可以创建和管理虚拟机了。

## 验证安装

```bash
virt-manager --version
qemu-system-x86_64 --version
virsh --version
```

## 服务管理

```bash
# 启用并启动服务
sudo systemctl enable --now libvirtd

# 检查服务状态
sudo systemctl status libvirtd
```

## 用户权限配置

```bash
# 添加用户组
sudo adduser $USER libvirt
sudo adduser $USER kvm

# 立即生效
newgrp libvirt
newgrp kvm

# 验证组
groups
```

## 网络配置（可选）

```bash
# 启动默认网络
sudo virsh net-start default

# 设置开机自启
sudo virsh net-autostart default

# 检查网络状态
sudo virsh net-list --all
```
