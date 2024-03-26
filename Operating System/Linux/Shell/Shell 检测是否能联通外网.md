```bash
#!/bin/bash

ping -c 1 www.baidu.com &>/dev/null

if [ $? -eq 0 ]; then
  ping_tong=能连接网络！
else
  ping_tong=不能连接网络！
fi

echo "------ 网络状态：$ping_tong   "
```
