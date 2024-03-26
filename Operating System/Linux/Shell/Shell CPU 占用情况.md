```sh
#!/bin/bash

# 基于 CentOS 7.5 编写

# 内存、cpu占用情况
echo "######################### 内存 #########################"

RESULT=$?

if [ ${RESULT} -eq 0 ]; then

    MEM_SUM_NUM=$(free -m | grep "Mem:" | awk -F" " '{print $2}')
    MEM_SURPLUS_NUM=$(free -m | grep "Mem:" | awk '{for(i=4;i<=NF;i++) print $i""FS;}' | awk '{a+=$1}END{print a}')
    MEM_SUM=$(free -m | grep "Mem:" | awk -F" " '{print $2"M"}')
    MEM_SURPLUS=$(free -m | grep "Mem:" | awk '{for(i=4;i<=NF;i++) print $i""FS;}' | awk '{a+=$1}END{print a"M"}')
    MEM_USED=$(echo $(($MEM_SUM_NUM - $MEM_SURPLUS_NUM)))
    PERCENT=$(printf "%d%%" $(($MEM_USED * 100 / $MEM_SUM_NUM)))
    PERCENT_NUM=$(echo $PERCENT | sed s/%//g)

    if [[ $PERCENT_NUM -lt 70 ]]; then
        MEM_STATUS=正常
    else
        MEM_STATUS=不正常
    fi

    echo "$MEM_STATUS(""总内存大小"$MEM_SUM,"剩余内存大小"$MEM_SURPLUS,"内存使用率"$PERCENT")"
else

    MEM_SUM_NUM7=$(free -m | grep "Mem:" | awk -F" " '{print $2}')
    MEM_SURPLUS_NUM7=$(free -m | grep "Mem:" | awk -F" " '{print $4}')
    MEM_SUM7=$(free -m | grep "Mem:" | awk -F" " '{print $2"M"}')
    MEM_SURPLUS7=$(free -m | grep "Mem:" | awk -F" " '{print $4"M"}')
    MEM_USED7=$(echo $(($MEM_SUM_NUM7 - $MEM_SURPLUS_NUM7)))
    PERCENT7=$(printf "%d%%" $(($MEM_USED7 * 100 / $MEM_SUM_NUM7)))
    PERCENT_NUM7=$(echo $PERCENT7 | sed s/%//g)

    if [[ $PERCENT_NUM7 -lt 70 ]]; then
        MEM_STATUS=正常
    else
        MEM_STATUS=不正常
    fi

    echo "$MEM_STATUS(""总内存大小: "$MEM_SUM7,"剩余内存大小: "$MEM_SURPLUS7,"内存使用率: "$PERCENT7")"
fi
```
