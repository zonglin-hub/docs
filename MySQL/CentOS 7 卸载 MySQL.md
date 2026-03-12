卸载 MySQL

```sh
sudo systemctl stop mysql    # 命令以停止 MySQL 服务
sudo apt-get remove --purge mysql-server mysql-client mysql-common    # 卸载 MySQL 及其依赖
sudo rm -rf /etc/mysql/    # 删除 MySQL 的配置文件
sudo rm -rf /var/lib/mysql/    # 删除 MySQL 的数据文件
sudo apt-get autoremove # 清理不必要的依赖项
```

完成以上步骤后，MySQL 就已经被卸载并且清除了相关的文件和依赖项。
