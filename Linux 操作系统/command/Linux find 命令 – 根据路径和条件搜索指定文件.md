# Linux find 命令 – 根据路径和条件搜索指定文件

**语法格式：** find [路径] [参数]

**常用参数：**

| 参数  | 解释     |
| ------- | ---------- |
| -name | 匹配名称 |

**参考案例：**

```shell
find / -name "*.txt"

# 忽略 /root/H5_fort_install_v2.8.0.14 这目录
find /root -path '/root/H5_fort_install_v2.8.0.14' -prune -o -name "*.txt" -print
```
