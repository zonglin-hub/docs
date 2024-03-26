1. lspci-0.sh

    ```sh
    #!/bin/bash

    lspci |grep 'Ethernet controller' | awk '{print $1}' | cut -d ":" -f 1
    ```

2. lspci-f.sh

    ```sh
    #!/bin/bash

    lspci -D -n -v | grep "Device Serial Number" | awk '{print $6}'
    ```

3. ip_a.sh

    ```sh
    #!/bin/bash

    ip a | grep ether | awk '{print $2}'
    ```

4. [安装并修改网卡名](./install-change-network.sh)

5. [重启机器监控网卡是否变动](./autoboot.sh)

6. [更新网卡配置文件](./insertName.sh)

7. [Java 实现网卡名排序](./Mac.java)
