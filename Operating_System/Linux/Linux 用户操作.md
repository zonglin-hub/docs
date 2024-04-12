# 用户操作

## 创建用户

默认情况下创建一个用户账号，会创建一个家目录和一个用户邮箱（在/var/spool/mail目录以用户名命名）

```sh
[root@localhost ~]# useradd <username>
```

**useradd 参数说明：**

<details>
<summary>点击查看代码</summary>

```text
  -b, --base-dir BASE_DIR 新账户的主目录的基目录
  -c, --comment COMMENT         新账户的 GECOS 字段
  -d, --home-dir HOME_DIR       新账户的主目录
  -D, --defaults  显示或更改默认的 useradd 配置
  -e, --expiredate EXPIRE_DATE  新账户的过期日期
  -f, --inactive INACTIVE       新账户的密码不活动期
  -g, --gid GROUP  新账户主组的名称或 ID
  -G, --groups GROUPS 新账户的附加组列表
  -h, --help                    显示此帮助信息并推出
  -k, --skel SKEL_DIR 使用此目录作为骨架目录
  -K, --key KEY=VALUE           不使用 /etc/login.defs 中的默认值
  -l, --no-log-init 不要将此用户添加到最近登录和登录失败数据库
  -m, --create-home 创建用户的主目录
  -M, --no-create-home  不创建用户的主目录
  -N, --no-user-group 不创建同名的组
  -o, --non-unique  允许使用重复的 UID 创建用户
  -p, --password PASSWORD  加密后的新账户密码
  -r, --system                  创建一个系统账户
  -R, --root CHROOT_DIR         chroot 到的目录
  -s, --shell SHELL  新账户的登录 shell
  -u, --uid UID   新账户的用户 ID
  -U, --user-group  创建与用户同名的组
  -Z, --selinux-user SEUSER  为 SELinux 用户映射使用指定 SEUSER
```

</details>

## 设置密码

```sh
[root@localhost ~]# passwd <username>
```

## 删除用户

```sh
[root@localhost ~]# userdel -r <username>
```

**userdel 参数说明：**
<details>
<summary>点击查看代码</summary>

```text
  -f, --force                   force some actions that would fail otherwise
                                e.g. removal of user still logged in
                                or files, even if not owned by the user
  -h, --help                    显示此帮助信息并推出
  -r, --remove                  删除主目录和邮件池
  -R, --root CHROOT_DIR         chroot 到的目录
  -Z, --selinux-user            为用户删除所有的 SELinux 用户映射
```

</details>

## `userdel -r <username>` 删除用户信息文件

若使用`userdel -r name`命令删除该用户时，并不能删除该用户的所有信息，只是删除了`/etc/passwd shadow group gshadow`四个文件里的该账户和组的信息。

```sh
[root@localhost ~]# cat /etc/passwd | grep name
name:x:1000:1000::/home/name:/bin/bash
[root@localhost ~]# cat /etc/shadow | grep name
name:$6$K1D9Y6ZW$0xwctiPxi6r/qqKpEpFsaHDCkUZ2V.8m9Oeyf4It.thk8At/oFttb.a3taMMkOWCissFOxYgZJ9QKK5N4dOUN.:19272:0:99999:7:::
[root@localhost ~]# cat /etc/group | grep name
name:x:1000:
[root@localhost ~]# cat /etc/gshadow | grep name
name:!::
```
