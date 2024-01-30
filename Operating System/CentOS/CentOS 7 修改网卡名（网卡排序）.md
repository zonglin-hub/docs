**1. lspci-0.sh**

```sh
#!/bin/bash

lspci |grep 'Ethernet controller' | awk '{print $1}' | cut -d ":" -f 1
```

**2. lspci-f.sh**

```sh
#!/bin/bash

lspci -D -n -v | grep "Device Serial Number" | awk '{print $6}'
```

**3. ip_a.sh**

```sh
#!/bin/bash

ip a | grep ether | awk '{print $2}'
```

**4. 安装并修改网卡名（install.sh）**

<details>
<summary>点击查看代码</summary>

```sh
#!/bin/bash

# 删除原有的network
rm -rf /usr/local/las/program/network/

# 永久关闭网络管理命令
systemctl disable NetworkManager

# 安装dos2unix用于清理windows ^M 隐藏符号
rpm -ivh dos2unix-6.0.3-7.el7.x86_64.rpm

# 安装lspci指令
rpm -ivh pciutils-3.5.1-3.el7.x86_64.rpm

# 把network 移动到 program 目录下
mv -fb ./network /usr/local/las/program/ 
# tar -zxvf /usr/local/las/program/network.tar.gz

# 把自启脚本放入到sart_init文件当中。并把文件移动到/etc/init.d/目录下
mv -fb start_init /etc/init.d/

# 更新获取网卡名脚步
mv -fb getNetworkCard.sh /usr/local/las/program/shell/

# 清除原来的文件
rm -rf /etc/init.d/start_init~
rm -rf /usr/local/las/program/shell/getNetworkCard.sh~
```

</details>

**5. 重启机器监控网卡是否变动（autoboot.sh）**

<details>
<summary>点击查看代码</summary>

```sh
#!/bin/bash

# 重新加载环境变量
source /etc/profile

# 判断 /usr/local/las/program/network/network.txt 与 /usr/local/las/program/network/@/network.txt 的MD5值

# 先执行jar包; 
java -jar /usr/local/las/program/network/networkmac.jar || exit 

# 清理network.txt文件中的隐藏符号 ^M
dos2unix /usr/local/las/program/network/network.txt

# 判断重启后判断md5值是否相同；如果md5不同配置网卡配置文件
N1=`md5sum /usr/local/las/program/network/network.txt | awk {'print $1'}`
N2=`md5sum /usr/local/las/program/network/contrast/network.txt | awk {'print $1'}`
if [[ "${N1}" == "${N2}" ]]; then
    echo "文件md5相同"
else
    echo '文件md5不同'
    # 把处理的数据写入到network.txt 文件当中
    /usr/local/las/program/network/insertName.sh
fi
```

</details>

**6. 更新网卡配置文件（insertName.sh）**

<details>
<summary>点击查看代码</summary>

```
#!/bin/bash

# 基于 CentOS 7.5 编写
# 写入对应的网卡配置文件

# 时时时间 
datetime=`date +%y%m%d%H%M%S`

function bond0(){
cat > /etc/sysconfig/network-scripts/ifcfg-${a[i]} << lzl
TYPE=Ethernet
BOOTPROTO=static
DEFROUTE=yes
PEERDNS=yes
PEERROUTES=yes
IPV4_FAILURE_FATAL=no
IPV6INIT=yes
IPV6_AUTOCONF=yes
IPV6_DEFROUTE=yes
IPV6_PEERDNS=yes
IPV6_PEERROUTES=yes
IPV6_FAILURE_FATAL=no
IPADDR=
NETMASK=
GATEWAY=
IPV6ADDR=
IPV6PREFIX=
IPV6_DEFAULTGW=
ONBOOT=yes
DNS1=
DNS2=
HWADDR=${b[i]}
DEVICE=${a[i]}
NAME=${a[i]}
lzl
}

# 移动文件之前先把原有的所有网卡配置文件清空，备
# 创建默认网卡配置文件备份目录
mkdir -p /etc/sysconfig/network-scripts/network_bak/network

# 把所有的ifcfg-enp* 全部的文件移动到指定目录下
mv /etc/sysconfig/network-scripts/ifcfg-e* /etc/sysconfig/network-scripts/network_bak/network/

# 修改文件夹名
mv /etc/sysconfig/network-scripts/network_bak/network /etc/sysconfig/network-scripts/network_bak/network_$datetime

# 获取现在 /etc/sysconfig/network-scripts 目录下所有的 网卡配置文件
# 读取最初版的网卡名；用户清空原始的网卡配置文件中的mac
c_3=0
for item_2 in `cat /usr/local/las/program/network/contrast/network.txt | grep -v '^$' | awk {'print $1'}`; do
    t[c_3]=$item_2
    let c_3++
done

# 读取网卡名称并写入到数组当中
c_1=0
for item in `cat /usr/local/las/program/network/network.txt | grep -v '^$' | awk {'print $1'}`; do
    a[c_1]=$item
    let c_1++
done

# 读取网卡名称的mac地址并写入到数组当中
c_2=0
for item_1 in `cat /usr/local/las/program/network/network.txt | grep -v '^$' | awk {'print $2'}`; do
    b[c_2]=$item_1
    let c_2++
done

# 定义一个空数组
q=()

# 替换板卡，删除多余网卡配置文件
function deleteFile() {
    # 布尔值定义
    flag=false
    # 定义q数组下标
    m=0
    # 循环遍历原版网卡配置文件
    for((i=0;i<${#t[*]};i++)); do
        # 判断文件是否存在路径
        # FILE_1=/etc/sysconfig/network-scripts/ifcfg-${t[i]}
        # echo "$FILE_1-----------------"
        flag=false
        for((j=0;j<${#a[*]};j++)); do
            if [[ "${t[i]}" == "${a[j]}" ]]; then
                # echo "老版本的网卡名称与新版的相同----------新版本中存在老版本的"
                # rm -rf /etc/sysconfig/network-scripts/ifcfg-${t[i]}
                flag=true
            fi
        done
       if [[ "$flag" == "false" ]]; then
           q[m]=${t[i]}
           let m++
       fi
    done
    # echo "${q[@]}"
    for((i=0;i<${#q[*]};i++)); do
        echo "/etc/sysconfig/network-scripts/ifcfg-${q[i]}"
        rm -rf /etc/sysconfig/network-scripts/ifcfg-${q[i]}
        # echo "${i}"
    done
    
}

# 创建网卡文件
function createFile() {

    # 循环遍历网卡文件个数
    for((i=0;i<${#a[*]};i++)); do

        # 判断文件是否存在路径
        FILE=/etc/sysconfig/network-scripts/ifcfg-${a[i]}

        # 如果文件存在；修改 mac 地址
        if [ -f "$FILE" ]; then
            echo "$FILE 文件存在"

            # HWADDR 指定 mac
            s="HWADDR=${b[i]}"

            # sed 修改以HWADDR的文件 路径：FILE
            sed -i "/^HWADDR/c$s" $FILE

        # 如果文件不存在就创建对应文件
        else 
            echo "$FILE 文件不存在"
            
            # 创建对应网卡配置文件 执行文件写入
            bond0
        fi
    done

    # 用于测试使用！
    # s_1="IPADDR=192.168.45.31"
    # sed -i "/^IPADDR/c$s_1" /etc/sysconfig/network-scripts/ifcfg-GE0-0
    # s_2="GATEWAY=192.168.1.1"
    # sed -i "/^GATEWAY/c$s_2" /etc/sysconfig/network-scripts/ifcfg-GE0-0
    # s_3="NETMASK=255.255.192.0"
    # sed -i "/^NETMASK/c$s_3" /etc/sysconfig/network-scripts/ifcfg-GE0-0

}


# 重新配置grub配置并更新内核
function grub () {

# 备份原有的 grub 文件;
mv /etc/default/grub /etc/default/grub.$datetime

cat > /etc/default/grub << lzl
GRUB_TIMEOUT=5
GRUB_DISTRIBUTOR="$(sed 's, release .*$,,g' /etc/system-release)"
GRUB_DEFAULT=saved
GRUB_DISABLE_SUBMENU=true
GRUB_TERMINAL_OUTPUT="console"
GRUB_CMDLINE_LINUX="crashkernel=auto rd.lvm.lv=centos/root "net.ifnames=0 biosdevname=0" rd.lvm.lv=centos/swap rhgb quiet"
GRUB_DISABLE_RECOVERY="true"
lzl

# 清除grub备份文件
rm -rf grub.*

# sed -i 's/root rd.lvm.lv/root "net.ifnames=0 biosdevname=0" rd.lvm.lv/g' /etc/default/grub
grub2-mkconfig -o /boot/grub2/grub.cfg

# 删除过期版的网卡名、mac 地址文件
rm -rf /usr/local/las/program/network/contrast/*

# 把生成的网卡名、mac 地址文件，备份到指定目录下，用户对比文件的 md5 值
cp -f /usr/local/las/program/network/network.txt /usr/local/las/program/network/contrast/

# 重启系统
echo `shutdown -r now`
# echo `reboot`
}

# 删除多余文件；创建缺失文件；在重启
deleteFile && createFile && grub

```

</details>

**7. 网卡名排序**
<details>
<summary>点击查看代码</summary>

```java
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * 依据 mac 地址规律进行网络名排序
 */
public class Mac {
    static String s1;
    static String s2;
    static String s3;

    public static void main(String[] args) {
        try {
            /*
            linux 指令获取到mac值！！！
            04
            05
            06
            07
            08
            09
            lspci |grep 'Ethernet controller' | awk '{print $1}' | cut -d ":" -f 1
            */
            s1 = execCmd("/usr/local/las/program/network/lspci-0.sh", null);

            /*
            8c-1c-da-ff-ff-43-11-97
            8c-1c-da-ff-ff-43-11-98
            8c-1c-da-ff-ff-43-11-99
            8c-1c-da-ff-ff-43-11-9a
            8c-1c-da-ff-ff-43-11-9b
            8c-1c-da-ff-ff-43-11-9c
            lspci -D -n -v | grep "Device Serial Number" | awk '{print $6}'
            */
            s2 = execCmd("/usr/local/las/program/network/lspci-f.sh", null);

            /*
            nmcli device show | grep "GENERAL.HWADDR" | awk {'print $2'}
            s3 = execCmd("/usr/local/las/program/network/nmcli.sh", null);

            8c:1c:da:43:11:97
            8c:1c:da:43:11:98
            8c:1c:da:43:11:99
            8c:1c:da:43:11:9a
            8c:1c:da:43:11:9b
            8c:1c:da:43:11:9c
            ip a | grep ether | awk '{print $2}'
            */
            s3 = execCmd("/usr/local/las/program/network/ip_a.sh", null);

            // 数据写入路径
            File f = new File("/usr/local/las/program/network/network.txt");
            // 如果文件不存在创建新的文件
            f.createNewFile();
            // 创建文件输出流以写入指定文件
            FileOutputStream fileOutputStream = new FileOutputStream(f);
            // 创建一个新地打印流
            PrintStream printStream = new PrintStream(fileOutputStream);
            // 文件写入
            System.setOut(printStream);
            t4(s1, s2, s3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 网卡mac分组，排序命名；三条数据的长度一致
     *
     * @param s1 lspci-0.sh
     * @param s2 lspci-f.sh
     * @param s3 ip_a.sh
     */
    public static void t4(String s1, String s2, String s3) {
        Map<Integer, String> map = new HashMap<>();
        boolean result = true;
        // 以 \r\n 进行分割
        String[] s1List = s1.split("\r\n");
        //把 -ff-ff- 替换成 "-";把 "-" 统一替换为 ":"
        s2 = s2.replaceAll("-ff-ff-", "-").replaceAll("-", ":");
        String[] s2List = s2.split("\r\n");
        // 定义数组长度
        String[] s2ListNew = new String[s2List.length];
        // 把参数转成小写
        s3 = s3.toLowerCase();
        String[] s3List = s3.split("\r\n");
        Map<Long, String> s3map = new HashMap<>();
        for (String s : s3List) {
            s3map.put(Long.parseLong(s.replaceAll(":", ""), 16), s);
        }
        // 3条数据长度不相等 返回一个空；程序退出
        if (s1List.length != s2List.length || s2List.length != s3List.length) {
            return;
        }

        for (int i = 0; i < s2List.length; i++) {
            String s2s = s2List[i];
            if (s3.contains(s2s)) {
                s2ListNew[i] = s2s;
            } else {
                String[] s2s_zheng = s2s.split(":");
                StringBuilder s2s_fan = new StringBuilder();
                for (int j = s2s_zheng.length - 1; j > -1; j--) {
                    if ("".equals(s2s_fan.toString())) {
                        s2s_fan = new StringBuilder(s2s_zheng[j]);
                    } else {
                        s2s_fan.append(":").append(s2s_zheng[j]);
                    }
                }
                if (s3.contains(s2s_fan.toString())) {
                    s2ListNew[i] = s2s_fan.toString();
                } else {
                    result = false;
                }
            }
        }

        int mapStratKey = 0;
        StringBuilder id_jilu = new StringBuilder();
        int z = 1;
        for (int i = 0; i < s1List.length; i++) {
            String mac = s2ListNew[i];
            if (map.toString().contains(mac)) {
                mac = mac.replace(":", "");
                mac = s3map.get(Long.parseLong(mac, 16) + z);
                z++;
                if (mac == null) {
                    result = false;
                }
            } else {
                z = 1;
            }
            String now = s1List[i];
            int num = 0;
            for (String s : s1List) {
                if (s.equals(now)) {
                    num++;
                }
            }

            if (num > 1) {
                if (!id_jilu.toString().contains(now)) {
                    mapStratKey++;
                }
                if (map.get(mapStratKey) == null) {

                    map.put(mapStratKey, mac);
                } else {
                    map.put(mapStratKey, map.get(mapStratKey) + "+" + mac);
                }
                id_jilu.append(now);
            } else {
                if (map.get(0) == null) {
                    map.put(0, mac);
                } else {
                    map.put(0, map.get(0) + "+" + mac);
                }
            }
        }

        if (!result) {
            map.clear();
        }

        for (int i = 0; i < map.size(); i++) {
            String tmp = map.get(i);
            String[] macArray = tmp.split("\\+");
            for (int j = 0; j < macArray.length; j++) {
                System.out.println("GE" + i + "-" + j + "  " + macArray[j] + "\r");
            }
        }
    }

    @Deprecated
    public static Map<Integer, String> t1(String s) {
        Map<Integer, String> map = new HashMap<>();
        // nmcli device show | grep "GENERAL.HWADDR" | awk {'print $2'}
        s = s.replace("00:00:00:00:00:00", "ff:ff:ff:ff:ff:ff");
        String[] list = s.split("\r\n");
        Arrays.sort(list);
        int mapStratKey = 10;
        for (int i = 0; i < list.length - 1; i++) {
            if (map.get(mapStratKey) == null) {
                map.put(mapStratKey, list[i]);
            } else {
                map.put(mapStratKey, map.get(mapStratKey) + "+" + list[i]);
            }

            Long now = Long.parseLong(list[i].replaceAll(":", ""), 16);
            Long next = Long.parseLong(list[i + 1].replaceAll(":", ""), 16);
            if (Math.abs(next - now) > 1) {
                mapStratKey++;
            }
        }
        return map;
    }

    @Deprecated
    public static Map<Integer, String> t2(String s) {
        StringBuilder jilu = new StringBuilder();
        int map1StratKey = 1;
        Map<Integer, String> map = t1(s);
        Map<Integer, String> map1 = new HashMap<>();
        String[] list = s.split("\r\n");
        for (String mac : list) {
            if (jilu.toString().contains(mac)) {
                continue;
            }
            if (map.size() == map1.size()) {
                break;
            }
            for (int i = 0; i < map.size(); i++) {
                String zu = map.get(10 + i);
                if (zu.contains(mac)) {
                    map1.put(map1StratKey, zu);
                    jilu.append(map.get(10 + i));
                    map1StratKey++;
                    break;
                }
            }
        }
        return map1;
    }

    @Deprecated
    public static void t3(String s) {
        Map<Integer, String> map = t2(s);
        for (int i = 0; i < map.size(); i++) {
            String tmp = map.get(i + 1);
            String[] macArray = tmp.split("\\+");
            for (int j = 0; j < macArray.length; j++) {
                System.out.println("eht" + (i + 1) + "-" + j + "  " + macArray[j] + "\r\n");
            }
        }
    }

    public static String execCmd(String cmd, File dir) throws Exception {
        StringBuilder result = new StringBuilder();
        Process process = null;
        BufferedReader bufrIn = null;
        BufferedReader bufrError = null;
        try {
            // 执行命令, 返回一个子进程对象（命令在子进程中执行）
            process = Runtime.getRuntime().exec(cmd, null, dir);
            // 方法阻塞, 等待命令执行完成（成功会返回0）
            process.waitFor();
            // 获取命令执行结果, 有两个结果: 正常的输出 和 错误的输出（PS: 子进程的输出就是主进程的输入）
            bufrIn = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
            bufrError = new BufferedReader(new InputStreamReader(process.getErrorStream(), StandardCharsets.UTF_8));
            // 读取输出
            String line;
            while ((line = bufrIn.readLine()) != null) {
                result.append(line).append("\r\n");
            }
            while ((line = bufrError.readLine()) != null) {
                result.append(line).append("\r\n");
            }
        } finally {
            closeStream(bufrIn);
            closeStream(bufrError);
            // 销毁子进程
            if (process != null) {
                process.destroy();
            }
        }
        // 返回执行结果
        return result.toString();
    }

    private static void closeStream(Closeable stream) {
        if (stream != null) {
            try {
                stream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}


```

</details>
