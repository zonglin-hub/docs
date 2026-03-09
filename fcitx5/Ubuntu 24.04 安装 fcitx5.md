
参考参考：

- Ubuntu 22.04 安装 Fcitx5 拼音输入法「Linux」「GNOME」「中文输入法」<https://www.bilibili.com/video/BV1tY411q7f1/>
- 视频对应文档地址：<https://muzing.top/posts/3fc249cf/>
- Fcitx5：<https://fcitx-im.org/wiki/Fcitx_5/zh-cn>
- 维基百科中文拼音词库：<https://github.com/felixonmars/fcitx5-pinyin-zhwiki>
- Input Method Panel 美化插件：<https://extensions.gnome.org/extension/261/kimpanel/>

---

## 检查系统中文环境

在 Ubuntu 设置中打开「区域与语言」—— 「管理已安装的语言」，然后会自动检查已安装
语言是否完整。若不完整，根据提示安装即可。

## 使用 apt 进行安装

```shell
sudo apt install fcitx5 fcitx5-chinese-addons fcitx5-frontend-gtk4 fcitx5-frontend-gtk3 fcitx5-frontend-gtk2 fcitx5-frontend-qt5
```

## 安装中文词库

在 GitHub 打开维基百科中文拼音词库的 Releases 界面，下载最新版的 `.dict` 文件。
按照 README 的指导，将其复制到 `~/.local/share/fcitx5/pinyin/dictionaries/` 文件
夹下即可。

```shell
# 下载词库文件
wget https://github.com/felixonmars/fcitx5-pinyin-zhwiki/releases/download/0.2.4/zhwiki-20220416.dict

# 创建存储目录
mkdir -p ~/.local/share/fcitx5/pinyin/dictionaries/

# 移动词库文件至该目录
mv zhwiki-20220416.dict ~/.local/share/fcitx5/pinyin/dictionaries/
```

## 设置为默认输入法

使用 `im-config` 工具可以配置首选输入法，根据弹出窗口的提示，将首选输入法设置为 Fcitx 5 即可。

## 环境变量

需要为桌面会话设置环境变量，使用 `vim ~/.bashrc` 命令写入配置文件以下内容

```shell
export XMODIFIERS=@im=fcitx
export GTK_IM_MODULE=fcitx
export QT_IM_MODULE=fcitx
```

> [!NOTE]
> 注意：如果不执行 `source ~/.bashrc` 命令，则需重启系统才能生效

## 开机自启动

安装 Fcitx 5 后并没有自动添加到开机自启动中，每次开机后需要手动在应用程序中找到
并启动，这里推荐使用 Tweaks 进行管理。你可以在终端执行 `sudo apt install
gnome-tweaks` 此条命令进行安装。并在 Tweaks 中将 Fcitx 5 添加到「开机启动程序」
列表中即可。


## 重置 fcitx5 

如果您在使用 `fcitx5` 或 `ibus` 时频繁遇到中文输入法异常，可以尝试以下步骤来解决问题：

### 1. __检查快捷键设置__：

   频繁的输入法切换可能是由于快捷键设置冲突。检查您的输入法快捷键设置，确保它们没有与其他应用程序或系统快捷键冲突。

### 2. __禁用不必要的输入法__：

   如果您安装了多个输入法，尝试只保留一个中文输入法，以减少切换可能引起的问题。

### 3. __重置输入法配置__：

   删除 `fcitx5` 或 `ibus` 的配置文件，让输入法框架重新生成默认配置。

     ```shell
     rm -rf ~/.config/fcitx5
     rm -rf ~/.config/ibus
     ```

   重启计算机或重新启动 `fcitx5` 或 `ibus` 服务。

### 4. __检查输入法进程__：

有时候输入法进程可能没有正确启动或卡住。使用以下命令检查输入法进程并杀死它们：

 ```shell
 ps -ef | grep fcitx
 killall fcitx5
 ```

然后重新启动输入法。


### 7. __查看日志文件__：

检查 `fcitx5` 或 `ibus` 的日志文件，以获取可能的错误信息：`fcitx5` 的日志通常在
`~/.local/share/fcitx5/log/` 目录下。

