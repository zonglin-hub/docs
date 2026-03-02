# Flatpak更换国内源安装Linux版微信和腾讯QQ

2025年3月28日 [教程](https://www.beizigen.com/category/tutorials/) [Linux](https://www.beizigen.com/tag/linux/)

原生Linux版微信和腾讯QQ可以直接在官网下载安装：

- [Linux版微信](https://linux.weixin.qq.com/)
- [Linux版QQ](https://im.qq.com/linuxqq/index.shtml)

如果要制作成一键安装脚本，同时需要下载最新版本，使用flatpak来安装要方便得多。

```
flatpak install com.tencent.WeChat
flatpak install com.qq.QQ
```

Flathub仓库在国内下载速度非常慢，国内有两个可用镜像源：

```
https://mirrors.ustc.edu.cn/flathub # 中国科学技术大学
https://mirror.sjtu.edu.cn/flathub	# 上海交通大学
```

实测中国科学技术大学的更稳定。将默认源更换为中科镜像源：

```
flatpak remote-modify flathub --url=https://mirrors.ustc.edu.cn/flathub
```

更新应用：

```
flatpak update
```

如果需要换回Flathub源：

```
flatpak remote-modify flathub --url=https://dl.flathub.org/repo/
```

如果卸载flatpak时删除了配置文件，可以重新添加源：

```
flatpak remote-add --if-not-exists flathub https://dl.flathub.org/repo/flathub.flatpakrepo
```

Zorin OS还需要添加操作系统官方源：

```
flatpak remote-add --no-gpg-verify zorinos https://flatpak.zorinos.com/repo/
```

查看所有镜像源：

```
flatpak remotes --show-details
```

删除指定镜像源（谨慎操作，已使用该源安装的软件会受到影响）：

```
flatpak remote-delete [源名称]
```

相关配置文件路径：

```
/var/lib/flatpak/repo/config
```

