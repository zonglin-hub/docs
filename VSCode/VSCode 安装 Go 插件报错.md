# VSCode 安装 Go 插件报错

__错误消息：__

```log
The "go-outline" command is not available. Run "go install -v github.com/ramya-rao-a/go-outline@latest" to install.
```

__解决办法：__

使用 win+r 打开 cmd 输入一下命令

```cmd
# 开启代理设置
go env -w GO111MODULE=on
 
# 设置代理源 
go env -w GOPROXY=https://goproxy.cn,direct
```

输入完成后 重启 VSCode，然后再次点击 lnstall all
