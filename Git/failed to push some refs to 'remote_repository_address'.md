# failed to push some refs to 'remote_repository_address'

>“failed to push some refs to 'remote_repository_address'” 这句话的意思是：向“remote_repository_address”（远程仓库地址）推送一些引用（refs，通常指分支等）失败了。
>
>可能是网络问题导致无法连接到远程仓库地址，从而推送失败。也可能是远程仓库中有未合并的提交，与本地的提交产生冲突，使得推送失败。
>
>例如，在使用 Git 进行版本控制时，如果在本地进行了一些提交，然后尝试推送到远程仓库，但是远程仓库中已经有其他人提交了新的代码，并且与本地的提交产生了冲突，就会出现这样的错误提示。此时需要先拉取远程仓库的代码，解决冲突后再进行推送。
>
>如果不确定本地版本是否是最新，最好先 `git pull`
>
>Git仓库中已经有一部分代码，所以它不允许你直接把你的代码覆盖上去。远程仓库和本地仓库存在差异。
>
>一般都是因为你在码云创建的仓库有ReadMe文件，而本地没有，造成本地和远程的不同步，

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
