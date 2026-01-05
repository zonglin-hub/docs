# Elasticsearch 集群搭建

elasticsearch.yml

```yml
# 集群使用描述性名称:
cluster.name: bigdata

# 使用节点的描述性名称:
node.name: node-1

# 存放数据的目录的路径(多个位置用逗号分隔)
path.data: /usr/local/las/data/elasticsearch

# 日志文件路径
path.logs: /usr/local/las/log/elasticsearch
bootstrap.memory_lock: false
bootstrap.system_call_filter: false

# 默认情况下Elasticsearch只能在本地主机上访问。
# 在这里设置一个不同的地址来公开网络上的这个节点
network.host: 0.0.0.0
network.publish_host: 172.100.19.155

# 默认 http 端口 9200
http.port: 9200
http.max_content_length: 100mb

# 配置跨域
http.cors.enabled: true
discovery.seed_hosts: ["172.100.19.155","172.100.19.156"]
cluster.initial_master_nodes: ["172.100.19.155","172.100.19.156"]
node.master: true
node.data: true
transport.tcp.port: 9300
transport.tcp.compress: true
indices.fielddata.cache.size: 10%
path.repo: ["/usr/local/las/data/backup/es"]
indices.query.bool.max_clause_count: 10240
```

## 参考文档

- [Linux搭建es集群详细教程（最终版）](https://blog.csdn.net/qq_50227688/article/details/115379121)
