# Linux yum 命令 – 基于RPM的软件包管理器

**参考文档：**

- [Linux和Unix的软件包下载地址](https://pkgs.org/)
- [yum命令 – 基于RPM的软件包管理器 – Linux命令大全(手册) (linuxcool.com)](https://www.linuxcool.com/yum)

------

**yum** [[Options](#jump1)] [COMMAND](#jump2)

<span id="jump1"><strong>List of Commands: </strong></span>

```shell
check          检查 RPM 数据库问题
check-update   检查是否有可用的软件包更新
clean          删除缓存数据
deplist        列出软件包的依赖关系
distribution-synchronization 已同步软件包到最新可用版本
downgrade      降级软件包
erase          从系统中移除一个或多个软件包
groups         显示或使用、组信息
help           显示用法提示
history        显示或使用事务历史
info           显示关于软件包或组的详细信息
install        向系统中安装一个或多个软件包
list           列出一个或一组软件包
load-transaction 从文件名中加载一个已存事务
makecache      创建元数据缓存
provides       查找提供指定内容的软件包
reinstall      覆盖安装软件包
repo-pkgs      将一个源当作一个软件包组，这样我们就可以一次性安装/移除全部软件包。
repolist       显示已配置的源
search         在软件包详细信息中搜索指定字符串
update         更新系统中的一个或多个软件包
upgrade        更新软件包同时考虑软件包取代关系
```

<span id="jump2"><strong>Options: </strong></span>

```shell
  -q, --quiet           静默执行
  -v, --verbose         详尽的操作过程
  -y, --assumeyes       回答全部问题为是
  --assumeno            回答全部问题为否
  --version             显示 Yum 版本然后退出
  --installroot=[path]  设置安装根目录
  --enablerepo=[repo]   启用一个或多个软件源(支持通配符)
  --disablerepo=[repo]  禁用一个或多个软件源(支持通配符)
  --obsoletes           更新时处理软件包取代关系
  --noplugins           禁用 Yum 插件
  --nogpgcheck          禁用 GPG 签名检查
  --skip-broken         忽略存在依赖关系问题的软件包
  --color=COLOR         配置是否使用颜色
  --downloadonly        仅下载而不更新
  --downloaddir=DLDIR   指定一个其他文件夹用于保存软件包
  --setopt=SETOPTS      设置任意配置和源选项
```

**参考案例：**

```shell
pwd=`/home/rpm.`
yum install --downloadonly --downloaddir=$pwd "vim"  # 下载rpm到指定目录，
```
