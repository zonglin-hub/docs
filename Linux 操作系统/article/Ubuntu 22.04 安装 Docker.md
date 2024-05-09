# Ubuntu(Ubuntu 22.04 操作系统) 安装 Docker

首先安装一些工具：

```sh
sudo apt-get install ca-certificates curl gnupg lsb-release
```

不过在Ubuntu22.04已经默认安装好了。接着安装官方的GPG key：

```sh
sudo mkdir -p /etc/apt/keyrings

curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /etc/apt/keyrings/docker.gpg
```

最后将Docker的库添加到apt资源列表中：

```sh
echo "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.gpg] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
```

接着我们更新一次apt：

```sh
 sudo apt update
```

最后安装Docker CE版本：

```sh
 sudo apt install docker-ce
```

等待安装完成就可以了：

最后我们将当前用户添加到docker用户组中，不然每次使用docker命令都需要sudo执行，很麻烦：

```sh
sudo usermod -aG docker <用户名>
```

配置好后，我们先退出SSH终端，然后重新连接就可以生效了。

## 参考

- [docker-compose权限不够](https://blog.csdn.net/qq_39162487/article/details/120597117)
- [Ubuntu安装docker-compose](https://blog.csdn.net/u012590718/article/details/125702606)
- [Ubuntu的docker详细安装教程](https://blog.csdn.net/weixin_50999155/article/details/119581698)
