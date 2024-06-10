import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * 依据 mac 地址规律进行网络名排序
 * JDK 1.8
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