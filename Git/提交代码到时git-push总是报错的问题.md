# Git 提交代码到 GitHub 时 git push 总是报错的问题

GitHub 在 2021 年 8 月 13 日的时候修改了认证方式，不能使用账户登录的方式去 push 代码等等操作，
而是使用生成仓库的个人密钥去 push 代码等操作。所以如果使用的是 gitee 的话，通过账户密码登录的方式是可以正常提交的，
而如果是使用 GitHub 的话，那么在最后一步的 `git push` 时是会报错的。

- 生成密钥步骤如下：

  1. 先登录 GitHub 生成一个个人访问令牌。

  2. 点击用户头像。

  3. 找到 Settings 选项。
  
  4. 滑到页面最下方找到 Developer settings。
  
  5. 选择 Personal access tokens。
  
  6. 选择 Token (classic）并点击旁边的 Generate a personal access token 或者直接访问 [tokens (classic)] 越过之前的步骤。

        > [!NOTE]
        > 为令牌取名，可以随便写。Expiration：过期时间，看个人选择，我选的是 No expiration 永不过期

  7. 勾选 repo (一定要勾，否则在 push 时会报权限错误，其他的权限看个人选择）

  8. 最后拉到底部点击 Generate token

        > [!NOTE]
        > 一定要点击复制并保存到你的记事本中，因为刷新页面以后就看不到，如果想再用只能删除重新生成

最后再使用 `git push` 进行同步就完成了，`git push` 后会让你输出你 GitHub 上的用户名和密码来进行验证你的身份，
用户名就写你 GitHub 上的名字就好了，密码就填我们步骤一最后生成的密匙，然后就完成了。

[tokens (classic)]: https://github.com/settings/tokens

每次 git pull 或者 git push 的时候，都要提示你输入账号和密码，很麻烦。
于是在网上找到了解决办法。这j里记录下来，以便以后解决此类问题。

首先进入你的git 工作目录下。
在终端执行一下命令：`git config --global credential.helper store`
然后再重新执行一次git pull,这次它还是提示你输入账号和密码：

示例：

```shell
❯ git pull
Username for 'https://isource-sh.dajiahao.com': s00558157
Password for 'https://stxgs123@isource-sh.dajiahao.com':*******
```

这次之后，它就会记录下账号和密码。以后再 pull 或者 push 的时候，
再也不用输入账户和密码了，节省时间，提高效率！！
