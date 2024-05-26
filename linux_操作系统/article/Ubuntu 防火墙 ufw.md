# Ubuntu 防火墙 ufw

```sh
sudo apt-get install ufw
```

启用 ufw

```sh
sudo ufw enable
```

关闭 ufw

```sh
sudo ufw disable 
```

查看 ufw 状态

```sh
sudo ufw status 
```

开启/禁用相应端口或服务举例

```sh
sudo ufw allow 22               # 允许外部访问22端口
sudo ufw delete allow 22        # 禁止外部访问80 端口
sudo ufw allow from 192.168.1.1 # 允许此IP访问所有的本机端口
```
