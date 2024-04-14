# Git

<strong>Git 全局设置：</strong>

```sh
git config --global user.name <user_name>      # 设置用户签名
git config --global user.email <user_email>     # 设置用户邮箱
```

<pre>
说明：
    签名的作用是区分不同操作者身份。
    用户的签名信息在每一个版本的提交信息中能够看 到，以此确认本次提交是谁做的。
    Git 首次安装必须设置一下用户签名，否则无法提交代码。

注意：
    这里设置用户签名和将来登录 GitHub（或其他代码托管中心）的账号没有任 何关系。
</pre>

<pre>
$ open C:\Users\liuzonglin\.gitconfig
[user]
        name = user_name
        email = user_email
[http]
        sslVerify = false
[core]
        symlinks = true
[credential "https://code.aliyun.com"]
        provider = generic
</pre>

<strong>创建 git 仓库：</strong>

```sh
mkdir <repository_name>
cd <repository_name>
git init
touch README.md
git add README.md
git commit -m "first commit"
git remote add origin <remote_repository_address>
git push -u origin "main"
```

<strong>已有仓库：</strong>

```sh
cd <repository_name>
git remote add origin <remote_repository_address>
git push -u origin "main"
```

## 创建版本库

<pre>
$ git clone <remote_repository_address> # 克隆远程版本库

$ git init # 初始化本地版本库
Initialized empty Git repository in D:/.github/.dome/df/.git/

$ cat .git/config # 每个git库都会有一个配置信息文件
[core]
        repositoryformatversion = 0
        filemode = false
        bare = false
        logallrefupdates = true
        symlinks = false
        ignorecase = true
[remote "origin"]
        url = https://gitee.com/liuzonglin1/df.git
        fetch = +refs/heads/*:refs/remotes/origin/*
[branch "main"]
        remote = origin
        merge = refs/heads/main
</pre>

## 修改和提交

```sh
git status                            # 查看本地库状态
git diff                              # 查看变更内容
git add <file>                        # 跟踪指定的文件
git add .                             # 跟踪所有改动过的文件
git mv <old> <new>                    # 文件改名
git rm <file>                         # 删除文件
git rm --cached <file>                # 停止跟踪文件但不删除
git commit -m "日志信息"               # 提交所有更新过的文件
git commit -m "日志信息" <文件名>       # 将暂存区的文件提交到本地库
git commit --amend                   # 修改最后一次提交
```

## 查看提交历史记录

```sh
git log               # 查看提交历史
git log -p <file>     # 查看指定文件的提交历史
git blame <file>      # 以列表方式查看指定文件的提交历史
git reflog            # 列出引用日志的条目
```

## 撤销

```sh
git reset --hard HEAD         # 撤销工作目录中所有未提交文件的修改内容
git checkout HEAD <file>      # 撤销指定的未提交文件的修改内容
git revert <commit>           # 撤销指定的提交
```

## Git 分支与标签

```sh
git branch                    # 显示所有本地分支
git branch <new_branch>       # 创建新分支
git checkout <branch_name/tag> # 切换到指定分支标签
git branch -d <branch_name>   # 删除本地分支
git tag                       # 列出所有本地标签
git tag <tag_name>            # 基于最新提交创建标签
git tag -d <tag_name>         # 删除标签
```

## 合并与衍合

```sh
git merge <branch_name>  # 合并指定分支到当前分支
git rebase <branch_name> # 衍和指定分支到当前分支
```

## Git 远程仓库操作

```sh
git remote -v                 # 查看远程版本库信息
git remote show <remote_name> # 查看指定远程版本库信息
git remote add <remote_name> <url> # 添加远程版本库
git fetch <remote_name>            # 从远程库获取代码
git pull <remote_name> <branch_name> # 下载代码及快速合并
git push <remote_name> <branch_name> # 上传代码及快速合并
git push origin --delete branch_name # 删除远程分支或标签
git push --tags # 上传所有标签
```

其中，origin是远程仓库的名称，branch_name是要删除的分支的名称。

### git 切换 Commit

```sh
git switch -c <new_branch_name>
```

- 如果您想要创建一个新分支以保留您创建的提交，您可以使用 -c 开关与切换命令一起使用。

<p>&nbsp;</p>

## 参考文档

- [Git 命令总结](https://blog.51cto.com/qiangsh/1769754)
- [Git 进行本地代码版本管理流程图](https://blog.csdn.net/solomonlangrui/article/details/47052679)
- [Git 的下载安装和初始设置](https://blog.csdn.net/m0_59751822/article/details/125940620)
- [git push后出现错误 ![rejected] master -&gt; master(non-fast-forward)](https://www.cnblogs.com/qingheshiguang/p/14777557.html)
- [git commit 时报错HEAD detached from 85e119d nothing to commit, working tree clean问题解决](https://www.jianshu.com/p/07786e5af9fd)
- [Git Book](https://git-scm.com/book/zh/v2/%E8%B5%B7%E6%AD%A5-%E5%AE%89%E8%A3%85-Git)
