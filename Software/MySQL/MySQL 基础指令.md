# mysql 基础操作

## LIKE 子句

```sh
SELECT field1, field2,...fieldN 
FROM table_name
WHERE field1 LIKE condition1 [AND [OR]] filed2 = 'somevalue'
```

**参数**

- 你可以在 WHERE 子句中指定任何条件。
- 你可以在 WHERE 子句中使用LIKE子句。
- 你可以使用LIKE子句代替等号 =。
- LIKE 通常与 % 一同使用，类似于一个元字符的搜索。
- 你可以使用 AND 或者 OR 指定一个或多个条件。
- 你可以在 DELETE 或 UPDATE 命令中使用 WHERE...LIKE 子句来指定条件。

## UNION 操作符

```mysql
SELECT expression1, expression2, ... expression_n
FROM tables
[WHERE conditions]
UNION [ALL | DISTINCT]
SELECT expression1, expression2, ... expression_n
FROM tables
[WHERE conditions];
```

**参数**

- expression1, expression2, ... expression_n: 要检索的列。
- tables: 要检索的数据表。
- WHERE conditions: 可选， 检索条件。
- DISTINCT: 可选，删除结果集中重复的数据。默认情况下 UNION 操作符已经删除了重复数据，所以 DISTINCT 修饰符对结果没啥影响。
- ALL: 可选，返回所有结果集，包含重复数据。

## 排序(ORDER BY)

```sh
SELECT field1, field2,...fieldN FROM table_name1, table_name2...
ORDER BY field1 [ASC [DESC][默认 ASC]], [field2...] [ASC [DESC][默认 ASC]]
```

**参数**

- 你可以使用任何字段来作为排序的条件，从而返回排序后的查询结果。
- 你可以设定多个字段来排序。
- 你可以使用 ASC 或 DESC 关键字来设置查询结果是按升序或降序排列。 默认情况下，它是按升序排列。
- 你可以添加 WHERE...LIKE 子句来设置条件

## 常用指令

```sql
## 创建数据库
CREATE DATABASE test;

## 删除数据库
drop database test;

## 切换数据库
USE test;

## 创建表结构
CREATE TABLE IF NOT EXISTS `runoob_tbl`(
   `runoob_id` INT UNSIGNED AUTO_INCREMENT,
   `runoob_title` VARCHAR(100) NOT NULL,
   `runoob_author` VARCHAR(40) NOT NULL,
   `submission_date` DATE,
   PRIMARY KEY ( `runoob_id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

## 删除表
DROP TABLE runoob_tbl;

## 查询所有表
show tables;

## 查询表数据
select * from runoob_tbl;

## WHERE 子句
select * from runoob_tbl where runoob_title="学习 C++";

## LIKE 子句
select * from runoob_tbl  where runoob_author like '%COM';

## UNION 操作符
SELECT expression1, expression2, ... expression_n
FROM tables
[WHERE conditions]
UNION [ALL | DISTINCT]
SELECT expression1, expression2, ... expression_n
FROM tables
[WHERE conditions];

## 排序
SELECT * from runoob_tbl ORDER BY submission_date ASC;
SELECT * from runoob_tbl ORDER BY submission_date DESC;

## 插入(insert)数据
insert into runoob_tbl (runoob_title, runoob_author, submission_date) values 
("学习 redis", "菜鸟教程", NOW()), 
("学习 mongo", "菜鸟教程", NOW()), 
("JAVA 教程", "RUNOOB.COM", '2016-05-06');

## 更新(update)语句
update runoob_tbl set runoob_title="学习 C++" where runoob_id=3;

## 删除(delete)语句
delete from runoob_tbl where runoob_id=6;

## ---------实例演示----------
SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `employee_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `employee_tbl`;
CREATE TABLE `employee_tbl` (
  `id` int(11) NOT NULL,
  `name` char(10) NOT NULL DEFAULT '',
  `date` datetime NOT NULL,
  `signin` tinyint(4) NOT NULL DEFAULT '0' COMMENT '登录次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `employee_tbl`
-- ----------------------------
BEGIN;
INSERT INTO `employee_tbl` VALUES 
('1', '小明', '2016-04-22 15:25:33', '1'), 
('2', '小王', '2016-04-20 15:25:47', '3'), 
('3', '小丽', '2016-04-19 15:26:02', '2'), 
('4', '小王', '2016-04-07 15:26:14', '4'), 
('5', '小明', '2016-04-11 15:26:40', '4'), 
('6', '小明', '2016-04-04 15:26:54', '2');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
SELECT * FROM employee_tbl;

## 统计 name 出现的次数并排序
SELECT name, COUNT(*) FROM employee_tbl GROUP BY name;

# ROLLUP(分组)
## 名字进行分组，再统计每个人登录的次数
SELECT name, SUM(signin) as signin_count FROM  employee_tbl GROUP BY name WITH ROLLUP;
SELECT coalesce(name, '总数'), SUM(signin) as signin_count FROM  employee_tbl GROUP BY name WITH ROLLUP;
## -------------------

CREATE TABLE `tcount_tbl` (
  `runoob_author` VARCHAR(10) NOT NULL,
  `runoob_count` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
SELECT * FROM tcount_tbl;
SELECT * from runoob_tbl;
INSERT INTO `tcount_tbl` VALUES ("菜鸟教程", 10), ("RUNOOB.COM", 20), ("Google", 22);
delete from runoob_tbl where runoob_id>5;
update runoob_tbl set submission_date="2017-04-12" where runoob_id=1;
update runoob_tbl set submission_date="2017-04-12" where runoob_id=2;
update runoob_tbl set submission_date="2015-05-01", runoob_title="学习 Java" where runoob_id=3;
update runoob_tbl set submission_date="2016-03-06", runoob_title="学习 Python" where runoob_id=4;
update runoob_tbl set submission_date="2017-04-05", runoob_title="学习 C", runoob_author="FK" where runoob_id=5;

## INNER JOIN 或 WHERE 连表查询 自会查询（a.runoob_author = b.runoob_author）相等的数据
SELECT a.runoob_id, a.runoob_author, b.runoob_count FROM runoob_tbl a INNER JOIN tcount_tbl b ON a.runoob_author = b.runoob_author;
SELECT a.runoob_id, a.runoob_author, b.runoob_count FROM runoob_tbl a, tcount_tbl b WHERE a.runoob_author = b.runoob_author;


## RIGHT JOIN（会读取右边数据表的全部数据，即使左边边表无对应数据）
SELECT a.runoob_id, a.runoob_author, b.runoob_count FROM runoob_tbl a RIGHT JOIN tcount_tbl b ON a.runoob_author = b.runoob_author;

## LEFT JOIN（会读取左边数据表的全部数据，即使右边边表无对应数据）
SELECT a.runoob_id, a.runoob_author, b.runoob_count FROM runoob_tbl a LEFT JOIN tcount_tbl b ON a.runoob_author = b.runoob_author;

## 正则表达式

### 查看以菜开头的数据
SELECT `runoob_author` FROM runoob_tbl WHERE `runoob_author` REGEXP '^菜';
### 查询以K结尾的数据
SELECT `runoob_author` FROM runoob_tbl WHERE `runoob_author` REGEXP 'K$';
### 包含COM字符串的所有数据
SELECT `runoob_author` FROM runoob_tbl WHERE `runoob_author` REGEXP 'COM';
## 查询包含B、K或以'COM'字符串结尾的所有数据
SELECT `runoob_author` FROM runoob_tbl WHERE `runoob_author` REGEXP '^[BK]|COM$';


# 事务

## 开始事务
begin;
## 提交事务
commit;
## 回滚
rollback;   

# ALTER(修改)命令
create table `testalter_tbl` (
 i INT,
 c CHAR(1)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
SELECT * FROM testalter_tbl;
## 显示列信息
SHOW COLUMNS FROM testalter_tbl;
## 删除 表字段 'i'
ALTER TABLE testalter_tbl DROP i;
## ADD 子句来向数据表中添加列
ALTER TABLE testalter_tbl ADD i INT;
##  FIRST (设定位第一列)
ALTER TABLE testalter_tbl ADD i INT FIRST;
## AFTER 字段名（设定位于某个字段之后）
ALTER TABLE testalter_tbl ADD i INT AFTER c;
## MODIFY（修改字段类型）
ALTER TABLE testalter_tbl MODIFY c CHAR(10);
## CHANGE（ CHANGE 关键字之后，紧跟着的是你要修改的字段名，然后指定新字段名及类型）
ALTER TABLE testalter_tbl CHANGE i j BIGINT;
ALTER TABLE testalter_tbl CHANGE j j INT;
## 指定字段 j 为 NOT NULL 且默认值为100 
ALTER TABLE testalter_tbl MODIFY j BIGINT NOT NULL DEFAULT 100;
## 修改默认值
ALTER TABLE testalter_tbl ALTER j SET DEFAULT 1000;
## DROP子句来删除字段的默认值
ALTER TABLE testalter_tbl ALTER j DROP DEFAULT;
## 修改表类型
ALTER TABLE testalter_tbl ENGINE = MYISAM;
## 查看数据表类型可以使用 SHOW TABLE STATUS 语句
SHOW TABLE STATUS LIKE 'testalter_tbl';
SHOW TABLE STATUS LIKE 'testalter_tbl'\G;
## 修改表名
ALTER TABLE testalter_tbl RENAME TO alter_tbl;

# 显示索引信息
SHOW INDEX FROM alter_tbl\G;
## 创建临时表
CREATE TEMPORARY TABLE SalesSummary (
 product_name VARCHAR(50) NOT NULL, 
 total_sales DECIMAL(12,2) NOT NULL DEFAULT 0.00,
 avg_unit_price DECIMAL(7,2) NOT NULL DEFAULT 0.00, 
 total_units_sold INT UNSIGNED NOT NULL DEFAULT 0
);
## 删除临时表
DROP TABLE SalesSummary;

# 复制表
SHOW CREATE TABLE runoob_tbl\G;
## 修改表
CREATE TABLE `runoobtbl` (
  `runoob_id` int unsigned NOT NULL AUTO_INCREMENT,
  `runoob_title` varchar(100) NOT NULL,
  `runoob_author` varchar(40) NOT NULL,
  `submission_date` date DEFAULT NULL,
  PRIMARY KEY (`runoob_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;
## 拷贝数据runoob_tbl表数据到runoobtbl中
INSERT INTO runoobtbl (runoob_id, runoob_title, runoob_author, submission_date) SELECT runoob_id, runoob_title, runoob_author, submission_date FROM runoob_tbl;
SELECT * FROM runoobtbl;

# 元数据
## 服务器版本信息
SELECT VERSION();
## 当前数据库名 (或者返回空)
SELECT DATABASE();
## 当前用户名
SELECT USER(); 
## 服务器状态
SHOW STATUS 
## 服务器配置变量
SHOW VARIABLES 


# 设置序列的开始值
CREATE TABLE insect(
 id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (id),
  name VARCHAR(30) NOT NULL, 
  date DATE NOT NULL,
  origin VARCHAR(30) NOT NULL
)engine=innodb auto_increment=100 charset=utf8; # 初始序列100

# 初始序列100
ALTER TABLE t AUTO_INCREMENT = 100;

# 统计重复数据
SELECT COUNT(*) as repetitions, last_name, first_name
 FROM person_tbl
  GROUP BY last_name, first_name
  HAVING repetitions > 1; # HAVING子句设置重复数大于1。
# DISTINCT（过滤重复数据）
SELECT DISTINCT last_name, first_nameFROM person_tbl;

source /home/abc/abc.sql  # 导入备份数据库

create database abc;      # 创建数据库
use abc;                  # 使用已创建的数据库 
set names utf8;           # 设置编码
source /home/abc/abc.sql  # 导入备份数据库

##--------------------查询练习-----------------------
# Write your MySQL query statement below

# CREATE TABLE IF NOT EXISTS Person ( Id INT UNSIGNED AUTO_INCREMENT, Email VARCHAR ( 100 ) NOT NULL, PRIMARY KEY ( Id ) ) ENGINE = INNODB DEFAULT CHARSET = utf8;

# INSERT INTO Person ( Email ) VALUES ("a@b.com"), ("c@d.com"), ("a@b.com");

SELECT Email FROM ( SELECT Email, COUNT( Email ) AS num FROM Person GROUP BY Email ) as s WHERE num > 1;
 
SELECT Email FROM Person GROUP BY Email HAVING COUNT(Email) > 1;
##--------------------查询练习-----------------------
DROP TABLE Person;

create table if not exists `Person`(
 `PersonId` int not null PRIMARY KEY AUTO_INCREMENT,
 `LastName` varchar(256) not null,
 `FirstName` varchar(256) not null
)engine=innodb auto_increment=1 charset=utf8;

SELECT * FROM Person;

insert into `Person` (`LastName`, `FirstName`) values ('Wang', 'Allen'), ('Alice', 'Bob');

CREATE TABLE if not exists `Address`(
 `AddressId` INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `PersonId` INT NOT NULL, 
  `City` VARCHAR(30) NOT NULL,
  `State` VARCHAR(30) NOT NULL
)engine=innodb auto_increment=1 charset=utf8;

SELECT * FROM Address;

insert into `Address` (`PersonId`, `City`, `State`) values (2, 'New York City', 'New York'), (3, 'Leetcode', 'California');

SELECT FirstName, LastName, City, State FROM Person p LEFT JOIN Address a on p.PersonId = a.PersonId;

##---------------------查询练习----------------------
DROP TABLE brand;
SELECT * FROM brand;
CREATE TABLE if not exists `brand`(
 `id` INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `code` INT(100), 
  `name` VARCHAR(30) NOT NULL,
  `edit_date` VARCHAR(30) NOT NULL
)engine=innodb auto_increment=1 charset=utf8;
insert into `Address` (`code`, `name`, `edit_date`) values 
(101, '1苏三1', Now()), 
(102, '苏三1', Now()), 
(103, '苏三2', Now()), 
(104, '苏三', Now()), 
(105, '苏三11', Now()), 
(106, '1苏三', Now()), 
(107, '11苏三', Now()), 
(108, '苏三说技术', Now()), 
(109, '苏三说技术666', Now()), 
(110, '2苏三', Now()),
(111, '苏三2', Now()),
(112, '21苏三', Now()),
(113, '22苏三', Now());

SELECT * FROM `brand` WHERE `name` LIKE '%苏三%' ORDER BY `edit_date` DESC LIMIT 5;
SELECT * FROM `brand` WHERE `name` LIKE '%苏三%' ORDER BY `edit_date` DESC LIMIT 5,5;
SELECT * FROM `brand` WHERE `name` LIKE '%苏三%' ORDER BY `id` DESC LIMIT 5;
SELECT * FROM `brand` WHERE `name` LIKE '%苏三%' ORDER BY `name` DESC LIMIT 5;
SELECT * FROM `brand` WHERE `name` LIKE '%苏三%' ORDER BY CHAR_LENGTH(`name`) ASC LIMIT 5;
SELECT * FROM `brand` WHERE `name` LIKE '%苏三%' ORDER BY CHAR_LENGTH(`name`) ASC LIMIT 5,5;
SELECT * FROM `brand` WHERE `name` LIKE '%苏三%' ORDER BY CHAR_LENGTH(`name`) ASC, LOCATE('苏三',`name`) ASC LIMIT 5,5;


##---------------------查询练习----------------------
-- 摸板表
CREATE TABLE IF NOT EXISTS `request_record_info` (
 `id`               BIGINT AUTO_INCREMENT                                                    COMMENT '主键id',
 `request_no`       VARCHAR(128)                           NOT NULL                          COMMENT '请求记录编号',
 `status`         INT(2)       DEFAULT 0                 NOT NULL                          COMMENT '请求状态：0-请求中，1-请求成功，2-请求失败',
 `company_name`     VARCHAR(300) DEFAULT ''                NOT NULL                          COMMENT '企业名称',
 `request_message`  TEXT                                   NULL                              COMMENT '请求参数',
  `response_message` TEXT                                   NULL                              COMMENT '返回参数',
  `hidden_display`   INT(2)       DEFAULT 1                 NOT NULL                          COMMENT '隐藏显示：0-隐藏，1-显示',
  `create_name`      VARCHAR(32)                            NULL                              COMMENT '创建人名字',
 `update_name`      VARCHAR(32)                            NULL                              COMMENT '更新人名字',
  `create_id`        BIGINT                                 NULL                              COMMENT '创建人ID',
  `update_id`        BIGINT                                 NULL                              COMMENT '更新人ID',
 `create_time`      DATETIME     DEFAULT CURRENT_TIMESTAMP NULL                              COMMENT '创建时间',
  `update_time`      DATETIME     DEFAULT CURRENT_TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP  COMMENT '更新时间',
 PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COMMENT '摸板表';

CREATE INDEX idx_request_no ON request_record_info ( request_no );
##---------------------
CREATE TABLE IF NOT EXISTS `Employee` (
 `id`        INT AUTO_INCREMENT                                       COMMENT '主键id',
 `name`      VARCHAR(30)            NOT NULL                          COMMENT '姓名',
 `salary`    INT                    NOT NULL                          COMMENT '工资',
 `managerId` INT                                                      COMMENT '经理的ID',
 PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;


INSERT INTO `Employee` (`name`, `salary`, `managerId`) VALUES
('Joe', 70000, 3), 
('Henry', 80000, 4), 
('Sam', 60000, NULL), 
('Max', 90000, NULL);

SELECT * FROM Employee;

SELECT a.`name` AS 'Employee' FROM `Employee` AS `a` JOIN `Employee` AS `b` ON a.ManagerId = b.Id AND a.Salary > b.Salary;
SELECT a.`name` AS 'Employee' FROM `Employee` AS `a`, Employee AS b WHERE a.ManagerId = b.Id AND a.Salary > b.Salary;
SELECT a.name Employee FROM Employee a, Employee b WHERE a.ManagerId = b.id AND a.salary> b.salary;

##---------------------查询练习----------------------
DROP TABLE Employee;
CREATE TABLE IF NOT EXISTS `Employee` (
 `id`               INT AUTO_INCREMENT                                                    COMMENT '主键id',
 `salary`       INT                          NOT NULL                          COMMENT '请求记录编号',
 PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT INTO Employee (salary) VALUES
(100),(200),(300);

SELECT * FROM Employee;
## DISTINCT(去重)
## LIMIT 1 OFFSET 1(表示取1后面第1条条数据, LIMIT(参数个数) OFFSET(从第几位开始))
## 降序(DESC)
SELECT DISTINCT salary as SecondHighestSalary FROM Employee ORDER BY salary DESC LIMIT 1 OFFSET 1;
SELECT (SELECT DISTINCT salary as SecondHighestSalary FROM Employee ORDER BY salary DESC LIMIT 1 OFFSET 1) as SecondHighestSalary;

## IFNULL(1, null) 如果没有返回值设置为null
SELECT IFNULL((SELECT DISTINCT salary as SecondHighestSalary FROM Employee ORDER BY salary DESC LIMIT 1 OFFSET 1), NULL) as SecondHighestSalary;

select ifnull((select DISTINCT salary from Employee order by salary DESC limit 1 OFFSET 1), null) as SecondHighestSalary;

## 创建了一个函数(getNthHighestSalary) 参数类型 int N 参数个数，返回值为 int 
CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
## 开始
BEGIN
## 声明参数
declare m INT;
## 条件两个参数，所以长度一定为大于1的，不然返回为空所以m值为N-1
SET m = N-1;
  RETURN (
      ## Write your MySQL query statement below.
   ## DISTINCT(去重)
   ## LIMIT 1 OFFSET 1(表示取1后面第1条条数据, LIMIT(参数个数) OFFSET(从第几位开始))
   ## 降序(DESC)
   ## IFNULL(expr1（N长度为1）,expr2（返回null）) 
      select IFNULL((select distinct salary from Employee ORDER BY salary DESC LIMIT 1 OFFSET m), null));
## 结束
END

##---------------------查询练习----------------------
# OR(或)
SELECT `name`, `population`, `area` FROM `World` WHERE `population` >= 3000000 OR `area` >= 25000000;
# UNION 连表查询
SELECT `name`, `population`, `area` FROM `World` WHERE `population` >= 3000000 UNION SELECT `name`, `population`, `area` FROM `World` WHERE `area` >= 25000000;
# AND(与)
SELECT `product_id` FROM `Products` WHERE `low_fats` = 'Y' AND `recyclable` = 'Y';
# `referee_id` != 2 referee_id <> 2 效果一致
SELECT `name` FROM `customer` WHERE `referee_id` != 2 OR `referee_id` IS NULL;
# in（子查询）
select customers.name as 'Customers' from customers where customers.id not in(select customerid from orders);

##---------------------查询练习----------------------
# IF(expr1（判断条件）, expr2（true 返回值）, expr3（false 返回值）)
# MOD(N,M)（N M 都为整数类型等同与N/M）MOD(`employee_id` ,2) != 0（偶数）MOD(`employee_id` ,2) != 1 （奇数）
# LEFT(str,len)（从str从字符串第len个字符开始截取）
select employee_id, IF(MOD(employee_id,2) != 0 AND LEFT(`name`, 1) != 'M', salary, 0) as bonus from Employees order by employee_id;
UPDATE salary SET sex = IF(sex=f,m,f);
delete p1 from Person p1, Person p2  where p1.email = p2.email && p1.id > p2.id;
##---------------------查询练习----------------------
# 合并字符串：
SELECT CONCAT("SQL ", "Tutorial ", "is ", "fun!") AS ConcatenatedString; 
# 将文本转换为大写：
SELECT UPPER("SQL Tutorial is FUN!"); 
# 从字符串中提取3个字符（从左侧开始）：
SELECT LEFT("SQL Tutorial", 3) AS ExtractString; 
# 将文本转换为小写：
SELECT LOWER("SQL Tutorial is FUN!"); 
# 从字符串中提取4个字符（从右侧开始）：
SELECT RIGHT("SQL Tutorial is cool", 4) AS ExtractString;
# 返回字符串的长度，以字节为单位：
SELECT LENGTH("SQL Tutorial") AS LengthOfString;

SELECT user_id, CONCAT(UPPER(LEFT(`name`, 1)), LOWER(RIGHT(`name`, LENGTH(`name`) - 1))) as `name` FROM Users ORDER BY user_id;


# GROUP_CONCAT(name) name1, name2, name3 拼接字符串以逗号分隔
select `sell_date`,count(distinct `product`) as num_sold,group_concat(distinct `product`) as products from activities order by sell_date;

# REGEXP（正则表达式）
# '^DIAB1|\\sDIAB1'（"^"以什么开头， 或以 \s 空格）
SELECT * FROM `Patients` WHERE `conditions` REGEXP '^DIAB1|\\sDIAB1'
SELECT employee_id FROM Employees WHERE employee_id NOT IN (
SELECT employee_id FROM Salaries) UNION
SELECT employee_id FROM Salaries WHERE employee_id NOT IN (
SELECT employee_id FROM Employees) ORDER BY employee_id ASC;
```

## 参考文档

- [MySQL官网](https://www.mysql.com/)
- [MySQL:: MySQL 8.0参考手册](https://dev.mysql.com/doc/refman/8.0/en/)
- [数据结构可视化](https://www.cs.usfca.edu/~galles/visualization/about.html)
