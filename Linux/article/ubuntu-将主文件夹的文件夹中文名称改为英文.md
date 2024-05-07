# ubuntu 将主文件夹的文件夹中文名称改为英文

许多人在使用 ubuntu 时使用汉语的，但是主文件夹下的文件夹也随之变成了中文，
导致命令行下输入文件夹名称非常的困难，那么怎么简单的把名字改成英文的呢？
（也不知道哪个版本开始不出现下面这个图示提示了）

打开终端，在终端中输入命令:

```shell
export LANG=en_US
```

```shell
xdg-user-dirs-gtk-update
```

> [!NOTE]
> 此时会弹出界面，选择带有 “update” 字样的选项或者是 “更新名称” 跳出对话框询问是否将目录转化为英文路径，选择后提示成功！

如果想改回中文，在终端中输入命令:

```shell
export LANG=zh_CN
 
或者
 
export LANG=zh_CN.utf-8
```

```shell
xdg-user-dirs-gtk-update
```

> [!NOTE]
> 此时会再次弹出对话框询问，选择 oldupdate 或者保留旧名称，跳出对话框询问是否将目录转化为中文路径，选择后提示成功！
