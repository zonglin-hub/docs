```sh
#!/bin/bash
# 基于 debian 编写

PATH_=/root/disk.log
DiskTU=0
dis() {
  for i in $(cat $PATH_); do
    DiskU=0
    DiskTmp=0

    if [ ${i:0-1:1} == 'T' ]; then
        DiskTmp=$(echo $i | awk -F "T" '{print $1}')
        DiskU=$(printf "%.f" $(echo "scale=0; $DiskTmp*1024*1024*1024*1024" | bc))
    elif [ ${i:0-1:1} == 'G' ]; then
        DiskTmp=$(echo $i | awk -F "G" '{print $1}')
        DiskU=$(printf "%.f" $(echo "scale=0; $DiskTmp*1024*1024*1024" | bc))
    elif [ ${i:0-1:1} == 'M' ]; then
        DiskTmp=$(echo $i | awk -F "M" '{print $1}')
        DiskU=$(printf "%.f" $(echo "scale=0; $DiskTmp*1024*1024" | bc))
    elif [ ${i:0-1:1} == 'K' ]; then
        DiskTmp=$(echo $i | awk -F "K" '{print $1}')
        DiskU=$(printf "%.f" $(echo "scale=0; $DiskTmp*1024" | bc))
    fi

    DiskTU=$((DiskU + DiskTU))
  done
}

DISK_=$(df -hP | awk '{print $2}')
echo $DISK_ > $PATH_
dis
DISK=$DiskTU
echo "获取磁盘总量：$DISK"

DiskTU=0
DISK_=$(df -hP | awk '{print $3}')
echo $DISK_ > $PATH_
dis
DISI=$DiskTU
echo "磁盘使用总量：$DISI"

DF=$(printf "%.f" $(echo "scale=4; $DISI/$DISK*100" | bc))
echo $DF%
```
