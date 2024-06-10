# Ubuntu 不在 sudoers 文件中

```sh
[sudo] liuzonglin 的密码：
liuzonglin 不在 sudoers 文件中。此事将被报告。
```

这个错误提示说明您当前使用的用户不具备sudo权限，无法使用sudo命令。

如果您需要在当前用户下执行管理员权限的任务，可以尝试使用su命令换为root用户，如下所示：

```sh
su -
```

执行后，输入root用户的密码即可切换为root用户。

如果您需要为当前用户授予sudo权限，可以先切换为root用户，然后执行以下命令：

```sh
adduser liuzonglin sudo
```

其中，liuzonglin为您的用户名。执行完毕后，您需要重新登录才能让新的sudo权限生效。
