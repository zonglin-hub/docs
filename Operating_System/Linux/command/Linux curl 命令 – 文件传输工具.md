# Linux curl 命令 – 文件传输工具

**参考文档：**

- [curl命令 – 文件传输工具 – Linux命令大全(手册) (linuxcool.com)](https://www.linuxcool.com/curl)

---

**语法格式：** curl [参数] 网址

**常用参数：**

| 解释                         | 参数         |
| :----------------------------- | :------------- |
| 后跟请求数据                 | -d           |
| get 请求                     | -XGET        |
| post 请求                    | -XPOST       |
| 更新请求                     | -XPUT        |
| 删除数据                     | -XDELET      |
| 后跟首都信息                 | -H           |
| 获取浏览器所有首部           | -I           |
| 文件下载                     | -O           |
| 自定义文件并下载             | -o           |
| 限制下载速度（100k）         | --limit-rate |
| 跟随重定向                   | -L           |
| 显示连接信息                 | -v           |
| 用户密码                     | -u           |
| 上传文件                     | -T           |
| 允许连接到SSL站点没有证书(H) | -k           |

**参考案例：**

```sh
# curl默认是 get 请求
curl -XGET 'http://127.0.0.1:9200/_license' -k

# POST 请求
curl -XPOST http://127.0.0.1:9200/liuzonglin_jd1/_search?pretty \
    -H 'content-Type:application/json' \
    -d '{"query":{"match_all":{}},"from":0,"size":10}'

# put请求
curl -XPUT http://127.0.0.1:9200/liuzonglin/_doc/2?pretty \ 
    -H 'content-Type:application/json' \
    -H 'content-Type:application/json' \
    -d '{"name":"liuzonglin","age":"26"}'

# 删除请求
curl -XDELETE 'http://127.0.0.1:9200/las-e-*/' -k

# 获取浏览器所有首部
curl -I www.baidu.com

# curl 上传文件
curl -u <用户名>:<密码> -T <文件> <URL>

# curl 下载文件
curl -u <用户名>:<密码> -O <URL>
```
