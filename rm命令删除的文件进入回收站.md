在Linux中，想让rm命令删除的文件进入回收站，需要自己动手配置。核心思路就是 “偷梁换柱” ：用一个 safer 的命令（如mv或trash）来替换默认的rm命令。

我为你整理了两种主流且可靠的方案，你可以根据自己的需求选择：
---

方案一：使用 trash-cli 工具 (最推荐)

这是最规范、最省心的方式。trash-cli 是一套命令行工具，它模拟了图形化回收站的行为，删除的文件会进入 ~/.local/share/Trash 目录，并且可以用命令轻松恢复。

1. 安装 trash-cli
根据你的Linux发行版，运行对应的安装命令：

· Debian / Ubuntu / Deepin:
  ```bash
  sudo apt install trash-cli
  ```
  
· Red Hat / CentOS:
  ```bash
  sudo yum install trash-cli
  ```
  
· Fedora:
  ```bash
  sudo dnf install trash-cli
  ```
  

2. 常用命令
安装完成后，你就可以用以下命令来安全地管理文件了：

· 删除文件/文件夹：trash <文件或目录>
  例如：trash example.txt 或 trash my_folder/。
· 查看回收站：trash-list
  这会列出回收站里所有文件及其原始路径和删除时间。
· 恢复文件：trash-restore
  这是一个交互式命令，运行后会列出回收站内容并提示你输入编号来恢复文件。
· 清空回收站：trash-empty
  永久清空回收站中的所有文件。
  你也可以只清空超过指定天数的文件，例如 trash-empty --age 7 会清空7天前放入回收站的文件。

3. 如何用 rm 命令实现回收站效果？
安装trash-cli 后，你可以在 ~/.bashrc 文件中设置一个别名，让 rm 命令自动调用 trash。这样，当你习惯性地输入 rm 时，实际执行的是安全的 trash 命令。

```bash
echo "alias rm='trash'" >> ~/.bashrc
source ~/.bashrc
```

设置完成后，再使用 rm，文件就会被安全地送到回收站了。

方案二：自定义 rm 命令 (轻量级)

如果你不想安装额外软件，也可以通过修改配置文件，让 rm 变成一个“移动文件”的命令。

1. 创建回收站目录
首先，在你的用户主目录下创建一个隐藏文件夹来当回收站。

```bash
mkdir -p ~/.Trash
```



2. 修改 .bashrc 配置文件
编辑用户主目录下的.bashrc 文件：

```bash
vim ~/.bashrc
```

在文件末尾添加以下内容：

```bash
# 自定义回收站
alias rm='mv -t ~/.Trash'        # 将 rm 命令重定向为 mv 命令，目标指向回收站
alias rl='ls -l ~/.Trash'        # 查看回收站内容
alias ur='undelfile'             # 恢复文件的函数
undelfile() {
    mv -i ~/.Trash/$@ ./
}
alias ct='cleartrash'            # 清空回收站的函数
cleartrash() {
    read -p "确定要清空回收站吗? [y/N] " confirm
    [ $confirm == 'y' ] || [ $confirm == 'Y' ] && /bin/rm -rf ~/.Trash/*
}
```



3. 使配置生效

```bash
source ~/.bashrc
```



4. 如何使用

· “删除”文件：rm file.txt (实际移动到 ~/.Trash)
· 列出回收站：rl
· 恢复文件：ur file.txt
· 清空回收站：ct (执行时会再次确认)

💡 注意事项与小建议

1. 定期清理：无论是哪种方案，回收站都会占用磁盘空间。建议养成定期清理的习惯。可以设置一个cron定时任务来自动清空，例如每天凌晨2点清空7天前的文件：
   ```bash
   0 2 * * * /usr/bin/trash-empty --age 7d
   ```
2. 保留真正的rm：当你有时确实需要永久删除文件时，可以通过在命令前加反斜杠 \ 来使用原始的 rm 命令，例如 \rm -rf important_file。
3. 重要数据仍需备份：回收站只能防止误删，并不能替代数据备份。对于关键数据，请务必进行异地或云端备份。

这两种方法都能很好地解决你的问题。如果你想体验最接近Windows/Mac的回收站功能，方案一的trash-cli是你的最佳选择；如果你喜欢简洁、不喜欢装额外软件，方案二的自定义脚本则能完美满足你的需求。