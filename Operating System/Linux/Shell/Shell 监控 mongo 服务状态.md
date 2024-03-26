```sh
#!/bin/bash

port=`netstat -nlpt | grep "\b27017\b" | wc -l`

for((;;)) do  # 死循环
 
    if [ $port -ne 1 ]; then # 检测是否有27017端口存在
        /etc/init.d/mongodb restart && sleep 10 # echo "状态异常"
    else
        echo "mongodb is rumming"
        exit   # 退出脚本
    fi
done
```
