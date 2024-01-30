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

### 强制覆盖本地文件

git pull 强制覆盖本地的代码方式，下面是正确的方法：

```sh
git fetch --all
```

然后，你有两个选择：

```sh
git reset --hard origin/main
```

或者如果你在其他分支上：

```sh
git reset --hard origin/<branch_name>
```

说明：

`git fetch` 从远程下载最新的，而不尝试合并或 `rebase` 任何东西。

然后 `git reset` 将主分支重置为您刚刚获取的内容。 `--hard` 选项更改工作树中的所有文件以匹配`origin/main`中的文件。

### git 切换 Commit

```sh
git switch -c <new_branch_name>
```

- 如果您想要创建一个新分支以保留您创建的提交，您可以使用 -c 开关与切换命令一起使用。

## error

<strong>error: failed to push some refs to 'remote_repository_address'：</strong>

<details><summary>点击查看错误示例</summary>

如果不确定本地版本是否是最新，最好先 `git pull`

Git仓库中已经有一部分代码，所以它不允许你直接把你的代码覆盖上去。远程仓库和本地仓库存在差异。

一般都是因为你在码云创建的仓库有ReadMe文件，而本地没有，造成本地和远程的不同步，

<strong>解决方法：</strong>

- 方法一、同步

```sql
1、git pull origin master --allow-unrelated-histories //把远程仓库和本地同步，消除差异
2、重新add和commit相应文件
3、git push origin master
4、此时就能够上传成功了
```

如果只是因为本地没有ReadMe文件，那么就在本地生成一个

```scss
git pull --rebase origin master  //本地生成ReadMe文件
git push origin master
```

- 方法二：强推

即利用强覆盖方式用你本地的代码替代git仓库内的内容

```perl
git push -f origin master
```

该命令会强制上传覆盖远程文件，慎用
方法三、

先把git的东西fetch到你本地然后merge后再push

```sql
git fetch
git merge
```

</details>

<p>&nbsp;</p>

<strong>error: git SSL certificate problem unable to get local issuer certificate：</strong>

<details><summary>点击查看错误示例</summary>

这个问题是由于没有配置信任的服务器HTTPS验证。默认，cURL被设为不信任任何CAs，就是说，它不信任任何服务器验证。只需要执行下面命令就可以解决：

```sh
git config --global http.sslVerify false
```

</details>

<p>&nbsp;</p>

<strong>git 中文文件名乱码</strong>

<details><summary>点击查看错误示例</summary>

<pre>
$ git status
On branch master
Your branch is up to date with 'origin/master'.

Changes not staged for commit:
  (use "git add/rm <file>..." to update what will be committed)
  (use "git restore <file>..." to discard changes in working directory)
        deleted:    "../Git/Windows Git \344\271\261\347\240\201.md"

Untracked files:
  (use "git add <file>..." to include in what will be committed)
        "../Git/Git \344\271\261\347\240\201.md"

no changes added to commit (use "git add" and/or "git commit -a")

liuzonglin@LAPTOP-CGO0UV3J MINGW64 /d/.github/.doc/Maven (master)
$ git config --global core.quotepath false
liuzonglin@LAPTOP-CGO0UV3J MINGW64 /d/.github/.doc/Maven (master)
$ git status
On branch master
Your branch is up to date with 'origin/master'.

Changes not staged for commit:
  (use "git add/rm <file>..." to update what will be committed)
  (use "git restore <file>..." to discard changes in working directory)
        deleted:    ../Git/Windows Git 乱码.md

Untracked files:
  (use "git add <file>..." to include in what will be committed)
        ../Git/Git 乱码.md

no changes added to commit (use "git add" and/or "git commit -a")

liuzonglin@LAPTOP-CGO0UV3J MINGW64 /d/.github/.doc/Maven (master)
</pre>

</details>

<p>&nbsp;</p>

<strong>HEAD detached from origin/master</strong>

<details><summary>点击查看错误示例</summary>

<pre>
liuzonglin@LAPTOP-CGO0UV3J MINGW64 /d/.github/java-dome ((a0734b5...))
$ git commit -m "日常更新"
HEAD detached from origin/master
nothing to commit, working tree clean

liuzonglin@LAPTOP-CGO0UV3J MINGW64 /d/.github/java-dome ((a0734b5...))
$ ^C

liuzonglin@LAPTOP-CGO0UV3J MINGW64 /d/.github/java-dome ((a0734b5...))
$ git branch v

liuzonglin@LAPTOP-CGO0UV3J MINGW64 /d/.github/java-dome ((a0734b5...))
$ git branch -v
* (HEAD detached from origin/master) a0734b5 日常更新
  master                             89ecd4a new file dubbo-api-task  deleted springboot-rabbitmq
  v                                  a0734b5 日常更新
liuzonglin@LAPTOP-CGO0UV3J MINGW64 /d/.github/java-dome ((a0734b5...))
$ git checkout master
Previous HEAD position was a0734b5 日常更新
Switched to branch 'master'
Your branch is up to date with 'origin/master'.

liuzonglin@LAPTOP-CGO0UV3J MINGW64 /d/.github/java-dome (master)
$ git merge a0734b5
Updating 89ecd4a..a0734b5
Fast-forward
 .gitignore                                         |   3 +-
 data-structure/README.md                           |   5 +
 data-structure/build.gradle                        |  19 ++
 data-structure/gradle/wrapper/gradle-wrapper.jar   | Bin 0 -> 59821 bytes
 .../gradle/wrapper/gradle-wrapper.properties       |   5 +
 data-structure/gradlew                             | 234 +++++++++++++++++++++
 data-structure/gradlew.bat                         |  89 ++++++++
 data-structure/settings.gradle                     |   2 +
 .../src/main/java/org/example/common/Utils.java    |  61 ++++++
 .../main/java/org/example/heap/HeapShiftUp.java    |  65 ++++++
 .../src/main/java/org/example/heap/MaxHeap.java    |  37 ++++
 .../main/java/org/example/sort/InsertionSort.java  |  36 ++++
 .../main/java/org/example/sort/SelectionSort.java  |  35 +++
 .../dubbo-spring-boot-demo-consumer/pom.xml        |  19 ++
 .../dubbo-spring-boot-demo-interface/pom.xml       |  19 ++
 .../dubbo-spring-boot-demo-provider/pom.xml        |  19 ++
 dubbo-spring-boot-demo/pom.xml                     |  67 ++++++
 .../example/sys/controller/LoginController.java    |   1 -
 thread/README.md                                   |   1 +
 thread/build.gradle                                |  19 ++
 thread/gradle/wrapper/gradle-wrapper.jar           | Bin 0 -> 59821 bytes
 thread/gradle/wrapper/gradle-wrapper.properties    |   5 +
 thread/gradlew                                     | 234 +++++++++++++++++++++
 thread/gradlew.bat                                 |  89 ++++++++
 thread/settings.gradle                             |   2 +
 .../java/org/example/Number/ThreadPoolTest.java    |  67 ++++++
 .../java/org/example/sync/SaleTicketDemo3.java     |  36 ++++
 .../java/org/example/sync/SaleTicketDemo4.java     |  31 +++
 .../java/org/example/sync/SaleTicketDemo5.java     |  61 ++++++
 .../main/java/org/example/thread/LifeCycle.java    |  43 ++++
 thread/src/main/java/org/example/thread/Main.java  |  17 ++
 .../main/java/org/example/thread/MyRunnable.java   |  20 ++
 .../src/main/java/org/example/thread/MyThread.java |  37 ++++
 .../java/org/example/thread/ThreadStateChange.java |  33 +++
 .../java/org/example/volatiles/volatileTest.java   |  49 +++++
 35 files changed, 1458 insertions(+), 2 deletions(-)
 create mode 100644 data-structure/README.md
 create mode 100644 data-structure/build.gradle
 create mode 100644 data-structure/gradle/wrapper/gradle-wrapper.jar
 create mode 100644 data-structure/gradle/wrapper/gradle-wrapper.properties
 create mode 100644 data-structure/gradlew
 create mode 100644 data-structure/gradlew.bat
 create mode 100644 data-structure/settings.gradle
 create mode 100644 data-structure/src/main/java/org/example/common/Utils.java
 create mode 100644 data-structure/src/main/java/org/example/heap/HeapShiftUp.java
 create mode 100644 data-structure/src/main/java/org/example/heap/MaxHeap.java
 create mode 100644 data-structure/src/main/java/org/example/sort/InsertionSort.java
 create mode 100644 data-structure/src/main/java/org/example/sort/SelectionSort.java
 create mode 100644 dubbo-spring-boot-demo/dubbo-spring-boot-demo-consumer/pom.xml
 create mode 100644 dubbo-spring-boot-demo/dubbo-spring-boot-demo-interface/pom.xml
 create mode 100644 dubbo-spring-boot-demo/dubbo-spring-boot-demo-provider/pom.xml
 create mode 100644 dubbo-spring-boot-demo/pom.xml
 create mode 100644 thread/README.md
 create mode 100644 thread/build.gradle
 create mode 100644 thread/gradle/wrapper/gradle-wrapper.jar
 create mode 100644 thread/gradle/wrapper/gradle-wrapper.properties
 create mode 100644 thread/gradlew
 create mode 100644 thread/gradlew.bat
 create mode 100644 thread/settings.gradle
 create mode 100644 thread/src/main/java/org/example/Number/ThreadPoolTest.java
 create mode 100644 thread/src/main/java/org/example/sync/SaleTicketDemo3.java
 create mode 100644 thread/src/main/java/org/example/sync/SaleTicketDemo4.java
 create mode 100644 thread/src/main/java/org/example/sync/SaleTicketDemo5.java
 create mode 100644 thread/src/main/java/org/example/thread/LifeCycle.java
 create mode 100644 thread/src/main/java/org/example/thread/Main.java
 create mode 100644 thread/src/main/java/org/example/thread/MyRunnable.java
 create mode 100644 thread/src/main/java/org/example/thread/MyThread.java
 create mode 100644 thread/src/main/java/org/example/thread/ThreadStateChange.java
 create mode 100644 thread/src/main/java/org/example/volatiles/volatileTest.java

liuzonglin@LAPTOP-CGO0UV3J MINGW64 /d/.github/java-dome (master)
$ git branch -v
* master a0734b5 [ahead 1] 日常更新
  v      a0734b5 日常更新

liuzonglin@LAPTOP-CGO0UV3J MINGW64 /d/.github/java-dome (master)
$ git status
On branch master
Your branch is ahead of 'origin/master' by 1 commit.
  (use "git push" to publish your local commits)

nothing to commit, working tree clean

liuzonglin@LAPTOP-CGO0UV3J MINGW64 /d/.github/java-dome (master)
$ git push
Enumerating objects: 76, done.
Counting objects: 100% (76/76), done.
Delta compression using up to 12 threads
Compressing objects: 100% (45/45), done.
Writing objects: 100% (65/65), 69.56 KiB | 7.73 MiB/s, done.
Total 65 (delta 9), reused 0 (delta 0), pack-reused 0
remote: Powered by GITEE.COM [GNK-6.4]
To https://gitee.com/liuzonglin1/java-dome.git
   89ecd4a..a0734b5  master -> master

liuzonglin@LAPTOP-CGO0UV3J MINGW64 /d/.github/java-dome (master)
</pre>

</details>

<p>&nbsp;</p>

## 参考文档

- [Git 命令总结](https://blog.51cto.com/qiangsh/1769754)
- [Git 进行本地代码版本管理流程图](https://blog.csdn.net/solomonlangrui/article/details/47052679)
- [Git 的下载安装和初始设置](https://blog.csdn.net/m0_59751822/article/details/125940620)
- [git push后出现错误 ![rejected] master -&gt; master(non-fast-forward)](https://www.cnblogs.com/qingheshiguang/p/14777557.html)
- [git commit 时报错HEAD detached from 85e119d nothing to commit, working tree clean问题解决](https://www.jianshu.com/p/07786e5af9fd)
- [Git Book](https://git-scm.com/book/zh/v2/%E8%B5%B7%E6%AD%A5-%E5%AE%89%E8%A3%85-Git)
