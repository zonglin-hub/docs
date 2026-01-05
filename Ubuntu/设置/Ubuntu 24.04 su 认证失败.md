Ubuntu 22.04 su 认证失败

---

```bash
zonglin@huawei-crem-wxx9:~$ su # 使用 su 切换用户
Password: 
su: Authentication failure # 认证失败
zonglin@huawei-crem-wxx9:~$ sudo passwd root # 设置 root 密码
New password: 
BAD PASSWORD: The password is shorter than 8 characters
Retype new password: 
passwd: password updated successfully
zonglin@huawei-crem-wxx9:~$ su
Password: 
root@zonglin-virtual-machine:/home/zonglin# ls
```

使用 `passwd` 设置密码

```bash
sudo passwd root # 设置 root 密码
```
