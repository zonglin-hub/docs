# Ubuntu 设置 SSH 连接

- ubuntu 查看 ip 信息

    ```sh
    ifconfig
    ```

- 若提示找不到命令，则需安装 net-tools

    ```sh
    sudo apt-get install net-tools
    ```

- 安装openssl

    ```sh
    sudo apt-get install update
    sudo apt-get install openssh-server
    ```

- 启动 ssh 服务

    ```sh
    sudo service ssh start  或者  sudo /etc/init.d/ssh start
    ```
