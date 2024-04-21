# git 中文文件名乱码

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
</pre>

```sh
git config --global core.quotepath false
```

参数说明：

- 将 git 配置文件 core.quotepath 项设置为 false
- quotepath 表示引用路径
- 加上 --global 表示全局配置

<pre>
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
</pre>

参考文档：<https://www.cnblogs.com/sdlz/p/13023342.html>
