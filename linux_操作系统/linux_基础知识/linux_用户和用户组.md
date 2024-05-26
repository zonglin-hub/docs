# 用户和用户组

我们整个 Linux 阶段的学习主要是以实操为主，大量的命令需要大量的使用才能记得更牢固。

Linux 系统是一个多用户多任务的分时操作系统，任何一个要使用系统的用户，都必须申请一个账号，然后以这个账号的身份进入系统。比如我们之前就是使用我们在创建服务器时申请的初始用户 test，通过输入用户名和密码登录到系统中，之后才能使用各种命令进行操作。其实用户机制和我们的 Windows 比较类似。一般的普通用户只能做一些比较基本的操作，并且只能在自己的目录，如 `/home/test` 中进行文件的创建和删除操作。

我们可以看到，当前状态信息分为三段：

```sh
test@ubuntu-server:~$
```

格式为：用户名@服务器名称:当前所处的目录$，其中~代表用户目录，如果不是用户目录，会显示当前的绝对路径地址。我们也可以使用`pwd`命令来直接查看当前所处的目录。

在Linux中默认存在一个超级用户root，而此用户拥有最高执行权限，它能够修改任何的内容，甚至可以删除整个Linux内核，正常情况下不会使用root用户进行登陆，只有在特殊情况下才会使用root用户来进行一些操作，root用户非常危险，哪怕一个小小的命令都能够毁掉整个Linux系统，比如`rm -rf /*`，感兴趣的话我们可以放在最后来演示（在以前老是听说安卓手机root，实际上就是获取安卓系统底层Linux系统的root权限，以实现修改系统文件的目的）

我们可以使用`sudo -s`并输入当前用户的密码切换到root用户，可以看到出现了一些变化：

```shell
test@ubuntu-server:~$

root@ubuntu-server:/home/test#
```

我们发现`$`符号变成了`#`符号，注意此符号表示当前的用户权限等级，并且test也变为了root，在此用户下，我们可以随意修改test用户文件夹以外的内容，而test用户下则无法修改。如果需要退出root用户，直接输入`exit`即可。

接着我们来看一下，如何进行用户的管理操作，进行用户管理，包括添加用户和删除用户都需要root权限才可以执行，但是现在我们是test用户，我们可以在命令前面添加`sudo`来暂时以管理员身份执行此命令，比如说我们现在想要添加一个新的用户：

```shell
sudo useradd study
```

其中`study`就是我们想要创建的新用户，`useradd`命令就是创建新用户的命令，同样的，删除用户：

```sh
sudo userdel study
```

Linux中的命令一般都可以携带一些参数来以更多特地的方式执行，我们可以在创建用户时，添加一些额外的参数来进行更多高级操作：

- -d<登录目录> 　指定用户登录时的起始目录。
- -g<群组> 　指定用户所属的群组。
- -G<群组> 　指定用户所属的附加群组。
- -m 　自动建立用户的登入目录。
- -M 　不要自动建立用户的登入目录。
- -s  指定Shell，一般指定为/bin/bash

如果还想查看更多命令，可以直接使用`man`来查看命令的详细参数列表，比如：

```shell
man useradd
```

比如我们现在需要在用户创建时顺便创建用户的文件夹，并指定shell（任意一种命令解释程序，用于处理我们输入的命令）为bash：

```shell
sudo useradd study -m -s /bin/bash
```

可以看到已经自动在home目录下创建了study文件夹（这里..表示上一级目录，.表示当前目录）：

```shell
test@ubuntu-server:~$ ls ..
study  test
```

用户创建完成之后，我们可以为此用户设定密码（如果不指定用户，那么会设置当前用户的密码）：

```shell
sudo passwd study
```

输入密码之后，我们可以使用命令来切换用户：

```shell
test@ubuntu-server:~$ su - study
Password: 
study@ubuntu-server:~$ 
```

可以看到，切换用户后名称已经修改为study了，我们使用`exit`即可退出当前用户回到test。

输入`who`可以查看当前登录账号（注意是登录的账号）输入`whoami`可以查看当前的操作账号：

```shell
test@ubuntu-server:~$ su study
Password: 
study@ubuntu-server:/home/test$ cd ~
study@ubuntu-server:~$ who
test     pts/0        2022-01-24 03:57 (192.168.10.3)
study@ubuntu-server:~$ whoami
study
study@ubuntu-server:~$ 
```

接着我们来看用户组，每个用户都有一个用户组，系统可以对一个用户组中的所有用户进行集中管理。我们可以输入`groups`来查看当前用户所有的用户组：

```sh
test@ubuntu-server:~$ groups
test adm cdrom sudo dip plugdev lxd
```

我们可以输入`id`来查看用户所属的用户相关信息：

```sh
test@ubuntu-server:~$ id
uid=1000(test) gid=1000(test) groups=1000(test),4(adm),24(cdrom),27(sudo),30(dip),46(plugdev),116(lxd)
```

我们可以看到test用户默认情况下主要用户组为同名的test用户组，并且还属于一些其他的用户组，其中sudo用户组就表示可以执行`sudo`命令，我们发现我们创建的study用户没有sudo的执行权限：

```sh
study@ubuntu-server:~$ sudo -s
[sudo] password for study: 
study is not in the sudoers file.  This incident will be reported.
```

正是因为没有加入到sudo用户组，这里我们来尝试将其添加到sudo用户组：

```sh
test@ubuntu-server:~$ id study
uid=1001(study) gid=1001(study) groups=1001(study)
```

使用`usermod`命令来对用户的相关设置进行修改，参数与useradd大致相同：

```sh
test@ubuntu-server:~$ sudo usermod study -G sudo
test@ubuntu-server:~$ id study
uid=1001(study) gid=1001(study) groups=1001(study),27(sudo)
```

接着切换到study用户就可以使用sudo命令了：

```sh
To run a command as administrator (user "root"), use "sudo <command>".
See "man sudo_root" for details.

study@ubuntu-server:/home/test$ sudo -s
[sudo] password for study: 
root@ubuntu-server:/home/test# 
```

实际上，我们的用户信息是存储在配置文件中的，我们之前说了，配置文件一般都放在etc目录下，而用户和用户组相关的配置文件，存放在`/etc/passwd`和`/etc/group`中，我们可以使用cat命令将文件内容打印到控制台：

```bash
test@ubuntu-server:~$ cat /etc/passwd
root:x:0:0:root:/root:/bin/bash
daemon:x:1:1:daemon:/usr/sbin:/usr/sbin/nologin
bin:x:2:2:bin:/bin:/usr/sbin/nologin
sys:x:3:3:sys:/dev:/usr/sbin/nologin
sync:x:4:65534:sync:/bin:/bin/sync
```

格式为：`注册名:口令:用户标识号:组标识号:用户名:用户主目录:命令解释程序 ​`，而我们的密码则存放在`/etc/shadow`中，是以加密形式存储的，并且需要root权限才能查看。
