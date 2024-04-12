# Linux MongoDB 常用指令

```sh
## 导出mongodb数据库
/usr/local/las/program/mongodb/bin/mongodump -d admin -u root -p "password" -o /root/sd 

## 导入mongodb数据库
/usr/local/las/program/mongodb/bin/mongorestore -d admin /root/sd/admin -u root -p "password" --drop

## 导出mongodb单个集合
/usr/local/las/program/mongodb/bin/mongoexport -d admin -c role -o /root/role.json -u root -p "password"

## 导入mongodb单个集合
/usr/local/las/program/mongodb/bin/mongoimport -u root -p "password" -d admin  -c role --type=json --file /root/role.json

# 登录mongo
/usr/local/las/program/mongodb/bin/mongo
use admin
db.auth("root","password");

# 查询用户表
db.getCollection('default_user').find({})

# 删除
db.default_user.drop()

# 再次查看数据库 mydb 中的集合
show collections

# 创建集合
db.createCollection("default_user")

# 这条指令是强制重置配置
rs.reconfig(cfg, { force: true});
cfg={_id:"fort",members:[{_id:0,host:"172.100.4.9:27017",priority:2},{_id:1,host:"172.100.4.25:27017",priority:1},{_id:2,host:"172.100.4.8:27018",arbiterOnly:true}]};

# 执行配置命令
rs.initiate(cfg)

# 查看集群状态
rs.status()
```
