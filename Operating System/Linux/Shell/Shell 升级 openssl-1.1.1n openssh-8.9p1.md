```sh
#!/bin/bash

# 基于CentOS 7.5 编写
# 功能实现升级openssl-1.1.1n openssh-8.9p1
# 检测基础环境是否安装

yum update openssh -y
yum install -y gcc gcc-c++ glibc make autoconf openssl openssl-devel pcre-devel pam-devel
yum install -y pam* zlib* # configure: error: PAM headers not found
# rpm -ivh pam-devel-1.1.8-23.el7.x86_64.rpm  --force --nodeps

# 更新openssl-1.1.1n
tar -zxvf openssl-1.1.1n.tar.gz && cd openssl-1.1.1n || exit
mv /usr/bin/openssl /usr/bin/openssl_bak
mv /usr/include/openssl /usr/include/openssl_bak
./config shared --prefix=/usr/local/ssl && make && make install
ln -s /usr/local/ssl/bin/openssl /usr/bin/openssl
ln -s /usr/local/ssl/include/openssl /usr/include/openssl

# 判断如果存在不添加，不存在添加
echo "/usr/local/ssl/lib" >>/etc/ld.so.conf
/sbin/ldconfig

# 更新openssh-8.9p1
cd ..
tar -zxvf openssh-8.9p1.tar.gz && cd openssh-8.9p1 || exit
pw=$(pwd)
chown -R root.root "$pw"
# chown -R root.root /root/liuzonglin/openssh-8.9p1
rm -rf /etc/ssh/*
./configure --prefix=/usr/ --sysconfdir=/etc/ssh --with-openssl-includes=/usr/local/ssl/include --with-ssl-dir=/usr/local/ssl --with-zlib --with-md5-passwords --with-pam && make && make install
# ./configure --prefix=/usr/ --sysconfdir=/etc/ssh.old --with-openssl-includes=/usr/local/ssl/include --with-ssl-dir=/usr/local/ssl --with-zlib --with-md5-passwords --with-pam  && make && make install

# 修改配置文件
s="PermitRootLogin yes"
sed -i "/^#PermitRootLogin/c$s" /etc/ssh/sshd_config
a="UseDNS no"
sed -i "/^#UseDNS/c$a" /etc/ssh/sshd_config
b="UsePAM yes"
sed -i "/^#UsePAM/c$b" /etc/ssh/sshd_config
cp -a contrib/redhat/sshd.init /etc/init.d/sshd
cp -a contrib/redhat/sshd.pam /etc/pam.d/sshd.pam
chmod +x /etc/init.d/sshd
chkconfig --add sshd
systemctl enable sshd
mv /usr/lib/systemd/system/sshd.service /root/
chkconfig sshd on
/etc/init.d/sshd restart
ssh -V
init 0
```
