```sh
#!/bin/bash

# 基于 CentOS 7.5 编写
# 作用：校验文件是否一致
# --------------------------------------------------------------------
# 7d7fe3fbc1bce90a2882b4a06c9439e5  /etc/init.d/arb
# 7d7fe3fbc1bce90a2882b4a06c9439e5
# --------------------------------------------------------------------

# 标准文件
H5=H5_fort_file_md5

# 执行路径
GETPATH=(
    "/etc/init.d/"
    "/usr/local/c_server/"
    "/usr/local/sbin/"
    "/usr/local/las/config/"
)

# 过略参数
OFF=(
    -not -name '.swp'
    -not -name 'oem.*'
    -not -name '*.log'
    -not -name '*.out'
    -not -name '*.css'
    -not -name '*.bak'
    -not -name '*.class'
    -not -name '*.zip'
    -not -name '*.md'
    -not -name '*.ico'
    -not -name '*.jar'
    -not -name '*.xlsx'
)

# 生成标准版本
standard() {
    >$H5

    for ((i = 0; i < ${#GETPATH[*]}; i++)); do
        find "${GETPATH[i]}" -type f "${OFF[@]}" | xargs md5sum >>$H5
    done
}

# 其他版本对比
md5_comparison() {
    >$H5.log

    # 定义总行数
    IN_ALL=$(wc -l $H5 | awk {'print $1'})

    # 如果不操过总行数
    for ((i = 1; i <= IN_ALL; i++)); do
    # 标准文件的MD5
    MD5=$(head -$i $H5 | tail -1 | awk {'print $1'})

    # 标准文件路径
    path=$(head -$i $H5 | tail -1 | awk {'print $2'})

    # 现在文件的MD5
    NOW=$(md5sum $path | awk {'print $1'})

    if [[ "$MD5" != "$NOW" ]]; then
        echo "$path" >>$H5.log
    fi

    done
}

# 备份
backup() {
    lu=$(pwd)
    IN_ALL1=$(wc -l $H5.log | awk {'print $1'})
    
    for ((i = 1; i <= IN_ALL1; i++)); do
        # 获得每一行的路径
        path=$(head -$i $H5.log | tail -1)
        cp --path $path $lu/

    done
}

case "$1" in
standard)
    standard
    ;;
md5_comparison)
    md5_comparison
    ;;
backup)
    backup
    ;;
*)

    echo "bash H5_fort_file_md5.sh { standard | md5_comparison | backup }" >&2
    exit 1
    ;;

esac
exit 0
```
