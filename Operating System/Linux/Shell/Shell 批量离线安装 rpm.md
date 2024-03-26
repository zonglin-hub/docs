```sh
#!/bin/bash

download() {
    for((i=0;i<${#SETNAME[*]};i++)); do
        pwd=$do/${SETNAME[i]}
        mkdir -p $pwd    # c创建下载 rpm 对应的目录
        yum install --downloadonly --downloaddir=$pwd "${SETNAME[i]}"    
    done
}

case "$1" in
download)
    download
    ;;
*)
    echo "download" >&2
    exit 1
    
esac
exit 0
```
