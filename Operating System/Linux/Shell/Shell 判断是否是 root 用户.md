```sh
#!/bin/bash

# 检查是否是root用户
if [ $(id -u) != "0" ] then
    # 不是root用户!尝试使用sudo命令!
    echo "Not the root user! Try using sudo command!"
    # 退出脚本
    exit 1
fi
```
