```sh
#!/bin/bash

mkdir -p /root/liuzonglin
new=/root/num.txt

for i in /home/ssh_log/* /home/rdp_log/*; do
    # echo $i
    md5sum $i >>$new
done

# 定义总行数
IN_ALL=$(wc -l $new | awk {'print $1'})

# 如果不操过总行数
for ((i = 1; i <= IN_ALL; i++)); do
    # 标准文件路径
    path=$(head -$i $new | tail -1 | awk {'print $2'})

    for i in 2021-06 2021-05 2021-04 2021-03 2021-02 2021-01; do
        stat $path | grep $i

        # 0为真 1位否
        if [ $? -eq 0 ]; then
          mv $path /root/liuzonglin/
          # rm -rf $path
        else
          echo "2022年文件"
        fi
      
    done

done
```
