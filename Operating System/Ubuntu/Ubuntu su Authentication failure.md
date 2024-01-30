# su: Authentication failure

```shell
zonglin@zonglin-virtual-machine:~/Desktop$ su
Password: 
su: Authentication failure
zonglin@zonglin-virtual-machine:~/Desktop$ sudo passwd root
New password: 
BAD PASSWORD: The password is shorter than 8 characters
Retype new password: 
passwd: password updated successfully
zonglin@zonglin-virtual-machine:~/Desktop$ su root
Password: 
su: Authentication failure
zonglin@zonglin-virtual-machine:~/Desktop$ su
Password: 
root@zonglin-virtual-machine:/home/zonglin/Desktop# ls
root@zonglin-virtual-machine:/home/zonglin/Desktop# 
root@zonglin-virtual-machine:/home/zonglin/Desktop# 
root@zonglin-virtual-machine:/home/zonglin/Desktop# 
```

使用passwd修改密码

```shell
sudo passwd root # 修改root 密码
```

使用 su 切换用户

**参考资料：**

[Ubuntu下postgresql出现Authentication failure（认证失败）](https://www.cnblogs.com/liuyanerfly/p/13427361.html)
