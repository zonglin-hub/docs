
```bash

# !/bin/bash

# 获取随机 8 位字符串
echo $RANDOM | md5sum | cut -c 1-8

openssl rand -base64 4

cat /proc/sys/kernel/random/uuid | cut -c 1-8

# 获取随机 8 位数字
echo $RANDOM | cksum | cut -c 1-8

openssl rand -base64 4 | cksum | cut -c 1-8

date +%N | cut -c 1-8

```
