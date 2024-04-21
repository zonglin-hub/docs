# Windows 安装 VirtualBox

**参考**

- <http://www.javashuo.com/article/p-xnxjpcal-bm.html>
- <https://www.vagrantup.com/docs/vagrantfile>

---

- Windows 安装 VirtualBox（Vagrant是管理虚拟机的工具，依赖于VirtualBox）

VirtualBox 官网：<https://www.virtualbox.org/>

- 安装 vagrant
  
Vagrant 是一个基于 Ruby 的工具，用于创建和部署虚拟化开发环境。
它使用 Oracle 的开源 VirtualBox 虚拟化系统，使用 Chef 创建自动化虚拟环境。

vagrant 官网：<https://www.vagrantup.com/>

vagrant 官方镜像仓库：<https://app.vagrantup.com/boxes/search>

- vagrant 常用指令：

```sh
vagrant init       # 初始化
vagrant init <box> # 初始化一个指定box
vagrant up         # 启动虚拟机
vagrant halt       # 关闭虚拟机
vagrant reload     # 重启虚拟机
vagrant ssh        # SSH 至虚拟机
vagrant suspend    # 挂起虚拟机
vagrant resume     # 唤醒虚拟机
vagrant status     # 查看虚拟机运行状态
vagrant provision  #重新应用更改 vagrant 配置
vagrant destroy    # 销毁当前虚拟机

vagrant reload --provision  #按新配置重启
vagrant up --provision   #按新配置启动

vagrant box list    # 查看本地box列表
vagrant box add     # 添加box到列表
vagrant box remove  # 从box列表移除 

vagrant package --output local-centos.box # 将虚拟机打包为一个.box的文件
```

- VagrantFile

```sh
Vagrant.configure("2") do |config|
# 设置主机名称
config.vm.hostname="docker"
# 设置使用那个box,可以在https://vagrantcloud.com/search查找
config.vm.box = "centos-docker"
# 设置使用的版本
config.vm.box_version="1.0"
# Create a forwarded port mapping which allows access to a specific port
# within the machine from a port on the host machine. In the example below,
# accessing "localhost:8080" will access port 80 on the guest machine.
# NOTE: This will enable public access to the opened port
# config.vm.network "forwarded_port", guest: 80, host: 8080

# Create a forwarded port mapping which allows access to a specific port
# within the machine from a port on the host machine and only allow access
# via 127.0.0.1 to disable public access
# config.vm.network "forwarded_port", guest: 80, host: 8080, host_ip: "127.0.0.1"

# Create a private network, which allows host-only access to the machine
# using a specific IP.
config.vm.network "private_network", ip: "192.168.33.10"

# Create a public network, which generally matched to bridged network.
# Bridged networks make the machine appear as another physical device on
# your network.
config.vm.network "public_network"
# 文件同步
# config.vm.synced_folder "../data", "/vagrant_data"

# virtualbox 配置
config.vm.provider "virtualbox" do |vb|
# 内存设置
vb.memory = "2048"
# cpu
vb.cpus=2
# virtualbox显示的名称 
vb.name="centos-docker"
end
# 执行的shell脚本 
config.vm.provision "shell", inline: <<-SHELL
echo hello vagrant
SHELL
end
```
