# Elasticsearch 常用指令

```bash
root@root:~# curl "http://127.0.0.1:9200"
{
  "name" : "8f1930bdze131", # 主机名
  "cluster_name" : "elasticsearch", # 默认一个也是集群，默认集群名 elasticsearch
  "cluster_uuid" : "XjL5BIXbRrOY0VR4HfloEQ", # 集群 uuid
  "version" : {
    "number" : "7.12.0", # 当前版本
    "build_flavor" : "default",
    "build_type" : "docker",
    "build_hash" : "78722783c38caa25a70982b5b042074cde5d3b3a",
    "build_date" : "2021-03-18T06:17:15.410153305Z",
    "build_snapshot" : false,
    "lucene_version" : "8.8.0", # 基于 lucene 版本
    "minimum_wire_compatibility_version" : "6.8.0",
    "minimum_index_compatibility_version" : "6.0.0-beta1"
  },
  "tagline" : "You Know, for Search" # 标语：你知道了，为了收索
}
root@root:~#
root@root:~# curl 'http://127.0.0.1:9200/_cat/nodes' # 查询所有节点
192.168.31.127 49 61 0 2.16 2.11 2.03 dilmrt * node-1
root@root:~#
root@root:~# curl -u elastic:Z37ufZpU -k 'http://127.0.0.1:9200/_cluster/health?pretty' # 查询集群状态
{
  "cluster_name" : "bigdata",
  "status" : "green",
  "timed_out" : false,
  "number_of_nodes" : 1,
  "number_of_data_nodes" : 1,
  "active_primary_shards" : 13,
  "active_shards" : 13,
  "relocating_shards" : 0,
  "initializing_shards" : 0,
  "unassigned_shards" : 0,
  "delayed_unassigned_shards" : 0,
  "number_of_pending_tasks" : 0,
  "number_of_in_flight_fetch" : 0,
  "task_max_waiting_in_queue_millis" : 0,
  "active_shards_percent_as_number" : 100.0
}
root@root:~#
root@root:~# curl -XGET -u elastic:Z37ufZpU -k 'http://127.0.0.1:9200/_license' # ES 查询授权许可
{
  "license" : {
    "status" : "active",
    "uid" : "6db4d4ba-a409-43cc-9278-45e8043201ef",
    "type" : "basic",
    "issue_date" : "2022-05-18T12:33:36.765Z",
    "issue_date_in_millis" : 1652877216765,
    "max_nodes" : 1000,
    "issued_to" : "bigdata",
    "issuer" : "elasticsearch",
    "start_date_in_millis" : -1
  }
}
root@root:~#
root@root:~# curl -XGET -u elastic:Z37ufZpU -k 'http://127.0.0.1:9200/_xpack/license' # ES 查询授权许可
{
  "license" : {
    "status" : "active",
    "uid" : "6db4d4ba-a409-43cc-9278-45e8043201ef",
    "type" : "basic",
    "issue_date" : "2022-05-18T12:33:36.765Z",
    "issue_date_in_millis" : 1652877216765,
    "max_nodes" : 1000,
    "issued_to" : "bigdata",
    "issuer" : "elasticsearch",
    "start_date_in_millis" : -1
  }
}
root@root:~#
root@root:~# curl -u elastic:Z37ufZpU -k 'http://127.0.0.1:9200/_cat/indices' # ES查询索引
green open las-e-2022-08-01 P1hcLjCtRAmfumcUaTGnnA 1 0  57 0 134.1kb 134.1kb
green open las-e-2022-07-11 CHqhzA7ERzi5eBZveJlQVA 1 0  11 0 143.6kb 143.6kb
green open las-e-2022-07-22 sagFseupQBuSSvU-PUyIwQ 1 0  25 0 150.4kb 150.4kb
green open las-e-2022-07-21 S9uNdWdgTBSHWuvoybNqlg 1 0   4 0  40.4kb  40.4kb
green open las-e-2022-07-31 i1E9BltXQBaLT7VqWdFHbA 1 0   1 0  17.5kb  17.5kb
green open las-e-2022-07-09 vJfXM9ZdSsaPyDnHV_vQEA 1 0   2 0    19kb    19kb
green open las-e-2022-07-27 hoyAlYOLTw-yQJPhdyn0cg 1 0   3 0  39.6kb  39.6kb
green open las-e-2022-07-14 S9fXCk4uTDSglKFc3dYWzA 1 0  10 0  97.1kb  97.1kb
green open las-e-2022-07-13 njD_YAPASkmrGAQ6SsmLvQ 1 0   1 0  17.6kb  17.6kb
green open las-e-2022-08-04 IKXHOKgWSSSrDCcnny_S0A 1 0 182 0   170kb   170kb
green open las-e-2022-08-05 C5J7DQXnTFeerizk-Puz8g 1 0 416 0 201.3kb 201.3kb
green open las-e-2022-08-06 nkDGb_iASaSlf0VXWWaYHA 1 0 420 0 109.6kb 109.6kb
green open las-e-2022-08-07 JlMhcAOpTfSqeRrs1bT4kA 1 0 834 0 282.5kb 282.5kb
root@root:~#
root@root:~# curl -XGET "http://127.0.0.1:9200/_cat/indices?v&pretty"
health status index            uuid                   pri rep docs.count docs.deleted store.size pri.store.size
green  open   las-e-2022-08-01 P1hcLjCtRAmfumcUaTGnnA   1   0         57            0    134.1kb        134.1kb
green  open   las-e-2022-07-22 sagFseupQBuSSvU-PUyIwQ   1   0         25            0    150.4kb        150.4kb
green  open   las-e-2022-07-11 CHqhzA7ERzi5eBZveJlQVA   1   0         11            0    143.6kb        143.6kb
green  open   las-e-2022-07-21 S9uNdWdgTBSHWuvoybNqlg   1   0          4            0     40.4kb         40.4kb
green  open   las-e-2022-07-31 i1E9BltXQBaLT7VqWdFHbA   1   0          1            0     17.5kb         17.5kb
green  open   las-e-2022-07-09 vJfXM9ZdSsaPyDnHV_vQEA   1   0          2            0       19kb           19kb
green  open   las-e-2022-07-27 hoyAlYOLTw-yQJPhdyn0cg   1   0          3            0     39.6kb         39.6kb
green  open   las-e-2022-07-14 S9fXCk4uTDSglKFc3dYWzA   1   0         10            0     97.1kb         97.1kb
green  open   las-e-2022-07-13 njD_YAPASkmrGAQ6SsmLvQ   1   0          1            0     17.6kb         17.6kb
green  open   las-e-2022-08-04 IKXHOKgWSSSrDCcnny_S0A   1   0        182            0      170kb          170kb
green  open   las-e-2022-08-05 C5J7DQXnTFeerizk-Puz8g   1   0        416            0    201.3kb        201.3kb
green  open   las-e-2022-08-06 nkDGb_iASaSlf0VXWWaYHA   1   0        420            0    109.6kb        109.6kb
green  open   las-e-2022-08-07 JlMhcAOpTfSqeRrs1bT4kA   1   0        855            0    262.9kb        262.9kb
root@root:~#
root@root:~# curl -u elastic:Z37ufZpU -XDELETE 'http://127.0.0.1:9200/las-e-*/' -k {"acknowledged" : true} # ES删除索引
root@root:~#
root@root:~# curl -u elastic:Z37ufZpU 'http://127.0.0.1:9200/las-e-2022-08-07/_search?pretty' -k # ES查询指定索引内容
```
