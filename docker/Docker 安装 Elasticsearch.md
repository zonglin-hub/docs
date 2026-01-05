# Docker 安装 Elasticsearch

## 拉取镜像

```bash
docker pull elasticsearch:8.6.2
```

## 创建容器挂载目录

```bash
mkdir -pv /home/zonglin/elasticsearch/config
mkdir -pv /home/zonglin/elasticsearch/data
mkdir -pv /home/zonglin/elasticsearch/plugins
```

`mkdir` 参数说明：

- -p, --parents     如果存在，则没有错误，根据需要创建父目录
- -v, --verbose     为每个创建的目录打印一条消息

## 配置文件

```bash
echo "http.host: 0.0.0.0" > /home/zonglin/elasticsearch/config/elasticsearch.yml
chmod -R 777 /home/zonglin/elasticsearch/
```

参数说明：

- ">"                          如果文件存在，清空文件内容并写入。如果文件不存在，创建新文件并写入。
- -R, --recursive        递归地更改文件和目录
- 777                         可读、可写、可执行权限

## 创建容器

```sh
docker run --name elasticsearch -p 9200:9200 -p 9300:9300 \
    --restart=always -e "discovery.type=single-node" -e ES_JAVA_OPTS="-Xms84m -Xmx512m" \
    -v /home/zonglin/elasticsearch/config/elasticsearch.yml:/usr/share/elasticsearch/config/elasticsearch.yml \
    -v /home/zonglin/elasticsearch/data:/usr/share/elasticsearch/data \
    -v /home/zonglin/elasticsearch/plugins:/usr/share/elasticsearch/plugins \
    -d elasticsearch:8.6.2
```

`docker run` 参数说明:

- --name string                    为容器分配一个名称
- -p, --publish list                  向主机发布容器的端口
- -P, --publish-all                    将所有公开的端口发布到随机端口
- -e discovery.type=single-node 单点模式启动
- -e ES_JAVA_OPTS="-Xms84m -Xmx512m"：设置启动占用的内存范围
- -v, --volume list                    绑定挂载卷
- -d, --detach                         在后台运行容器并打印容器ID

## 查看启动详情

```bash
docker ps  查看是否启动
docker logs elasticsearch  启动日志查询
docker restart elasticsearch   重启
docker exec -it elasticsearch bash 进入
```

## 安装 elasticsearch-ik 分词器

elasticsearch-ik 分词器版本和 elasticsearch 版本必须一致

```sh
# 拉取安装包
wget https://github.com/medcl/elasticsearch-analysis-ik/releases/download/v7.12.0/elasticsearch-analysis-ik-7.12.0.zip

# 创建 ik 目录 解压到ik目录中
mkdir -pv /opt/elasticsearch/plugins/ik/
unzip elasticsearch-analysis-ik-7.12.0.zip

# 重启服务
docker restart elasticsearch
```

## Elasticsearch 🔨 安装 kibana

### 可视化界面

[Elasticvue - Microsoft Edge Addons](https://microsoftedge.microsoft.com/addons/detail/elasticvue/geifniocjfnfilcbeloeidajlfmhdlgo)

```sh
# 安装 kibana 需要版本一致
wget https://artifacts.elastic.co/downloads/kibana/kibana-7.12.0-linux-x86_64.tar.gz
wget https://artifacts.elastic.co/downloads/elasticsearch/elasticsearch-7.12.0-linux-x86_64.tar.gz
```

### `kibana/config/kibana.yml` 配置

```sh
server.host: "192.168.1.102"
# 配置远程服务器地址
elasticsearch.hosts: ["http://192.168.1.102:9200"]
# 设置中文
i18n.locale: "zh-CN"
# zh-CN
/usr/local/kibana/x-pack/plugins/translations/translations/zh-CN.json
```

### 启动 kibana

```sh
# kibana 不支持root用户启动
./kibana/bin/kibana --allow-root &

# 关闭防火墙
systemctl stop firewalld.service
```

测试连接

`web访问：http://192.168.1.102:5601`
