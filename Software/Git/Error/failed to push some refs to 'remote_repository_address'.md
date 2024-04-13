# failed to push some refs to 'remote_repository_address'

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
