

参考

- <https://blog.csdn.net/MJOY791270505/article/details/126400205>
- <https://blog.csdn.net/m0_64697285/article/details/127318141>

## nvm 是什么?

*<u>node.js version management</u>*，顾名思义是一个 nodejs 的版本管理工具。
为了解决 node 各种版本存在不兼容现象，nvm 是让你在同一台机器上安装和切换不同版本的 node 的工具，通过它可以安装和切换不同版本的 nodejs。

## Windows 下载安装 nvm

可在点此在github上下载最新版本,本次下载安装的是windows版本。

nvm-github下载地址：<https://github.com/coreybutler/nvm-windows/releases>

__1. 选择下载__

- __nvm-setup.zip：__ 安装版，__推荐使用。__
- __nvm-noinstall.zip：__ 绿色免安装版，但使用时需进行配置环境变量。

双击nvm-setup.exe文件安装

__2. 安装注意事项__

安装路径最好不要出现__中文和空格__
配置淘宝源 ...\nvm\settings.txt 无法修改下面的权限问题统一解决方式

```text
root: D:\program\nvm
path: D:\program\nodejs
node_mirror: https://npm.taobao.org/mirrors/node/
npm_mirror: https://npm.taobao.org/mirrors/npm/
```

安装后无法正常下载使用 nodejs 软件安装后没有操作权限，赋予NVM可执行权限

---


## nvm 指令

`nvm -v`，安装成功则显示版本号和列出了各种使用命令。

```sh
$ nvm -v
1.1.10
```

`nvm ls`列出所有已经安装的Node版本

```sh
$ nvm ls

* 14.21.1 (Currently using 64-bit executable)
14.19.3
10.16.3
```

安装最新版 Node

```sh
nvm install node
```

`nvm list available`列出所有可以安装的Node版本号

```sh
$ nvm list available

|   CURRENT    |     LTS      |  OLD STABLE  | OLD UNSTABLE |
|--------------|--------------|--------------|--------------|
|    19.6.1    |   18.14.1    |   0.12.18    |   0.11.16    |
|    19.6.0    |   18.14.0    |   0.12.17    |   0.11.15    |
|    19.5.0    |   18.13.0    |   0.12.16    |   0.11.14    |
|    19.4.0    |   18.12.1    |   0.12.15    |   0.11.13    |
|    19.3.0    |   18.12.0    |   0.12.14    |   0.11.12    |
|    19.2.0    |   16.19.1    |   0.12.13    |   0.11.11    |
|    19.1.0    |   16.19.0    |   0.12.12    |   0.11.10    |
|    19.0.1    |   16.18.1    |   0.12.11    |    0.11.9    |
|    19.0.0    |   16.18.0    |   0.12.10    |    0.11.8    |
|   18.11.0    |   16.17.1    |    0.12.9    |    0.11.7    |
|   18.10.0    |   16.17.0    |    0.12.8    |    0.11.6    |
|    18.9.1    |   16.16.0    |    0.12.7    |    0.11.5    |
|    18.9.0    |   16.15.1    |    0.12.6    |    0.11.4    |
|    18.8.0    |   16.15.0    |    0.12.5    |    0.11.3    |
|    18.7.0    |   16.14.2    |    0.12.4    |    0.11.2    |
|    18.6.0    |   16.14.1    |    0.12.3    |    0.11.1    |
|    18.5.0    |   16.14.0    |    0.12.2    |    0.11.0    |
|    18.4.0    |   16.13.2    |    0.12.1    |    0.9.12    |
|    18.3.0    |   16.13.1    |    0.12.0    |    0.9.11    |
|    18.2.0    |   16.13.0    |   0.10.48    |    0.9.10    |

This is a partial list. For a complete list, visit https://nodejs.org/en/download/releases
```

`nvm install <版本号>`安装指定版本号的Node

```sh
$ nvm install 18.14.1
Downloading node.js version 18.14.1 (64-bit)... 
Extracting node and npm...
Complete
npm v9.3.1 installed successfully.


Installation complete. If you want to use this version, type

nvm use 18.14.1
```

`nvm use <版本号>`使用特定版本的Node

```sh
$ nvm use 18.14.1
Now using node v18.14.1 (64-bit)
```

`nvm uninstall <版本号>`卸载版本号的Node

```sh
nvm uninstall 10.16.3
```

查看 node 版本

```gradle
$ node -v
v18.14.1
```
