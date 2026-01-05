# MySQL 如何实现分页查询

参考文档：

- [使用MySQL如何实现分页查询 - 路饭网 (45fan.com)](http://www.45fan.com/article.php?aid=1CZPZ0FXJQ6z8ohK)
- [(24条消息) 【MySQL】MySQL中如何实现分页操作_自牧君的博客-CSDN博客_mysql分页](https://blog.csdn.net/Sihang_Xie/article/details/125491969)

## 通过limit关键字

格式为：

```sql
select * from <库表名> Limit <位置偏移量>, <每页条目数>;
```

### 单参数用法

当指定一个参数时，默认省略了偏移量，即偏移量为`0`​，从第一行数据开始取，一共取`rows`​条。

```sql
/* 查询前5条数据 */
SELECT * FROM Student Limit 5;
```

### 双参数用法

当指定两个参数时，需要注意偏移量的取值是从0开始的，此时可以有两种写法：

```sql
/* 查询第1-10条数据 */
SELECT * FROM Student Limit 0,10;
/* 查询第11-20条数据 */
SELECT * FROM Student Limit 10 OFFSET 10;
```

## 分页公式

在进行分页之前，我们需要先根据数据总量来得出总页数，这需要用到COUNT函数和向上取整函数CEIL，SQL如下：

```sql
/* 获得数据总条数 */
SELECT COUNT(*) FROM Student;
/* 假设每页显示10条，则直接进行除法运算，然后向上取整 */
SELECT CEIL(COUNT(*) / 10) AS pageTotal FROM Student;
```
