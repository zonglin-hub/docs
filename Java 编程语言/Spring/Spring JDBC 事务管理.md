# Spring JDBC事务管理

[Spring JdbcTemplate（使用详解） (biancheng.net)](http://c.biancheng.net/spring/jdbc-template.html)

---

**JdbcTemplate 简介：** Spring 框架对 JDBC 进行封装，使用 JdbcTemplate 方便实现对数据库操作

**声明式事务概念：** 数据库事务( transaction)是访问并可能操作各种数据项的一个数据库操作序列，这些操作要么全部执行,要么全部不执行，是一个不可分割的工作单位。事务由事务开始与事务结束之间执行的全部数据库操作组成。

**事务的特性ACID：**

* **原子性(Atomicity)：** 一个事务(transaction)中的所有操作，要么全部完成，要么全部不完成，不会结束在中间某个环节。事务在执行过程中发生错误，会被回滚（Rollback）到事务开始前的状态，就像这个事务从来没有执行过一样。

* **一致性(Consistency)：** 事务的一致性指的是在一个事务执行之前和执行之后数据库都必须处于一致性状态。如果事务成功地完成，那么系统中所有变化将正确地应用，系统处于有效状态。如果在事务中出现错误，那么系统中的所有变化将自动地回滚，系统返回到原始状态。

* **隔离性(Isolation)：** 指的是在并发环境中，当不同的事务同时操纵相同的数据时，每个事务都有各自的完整数据空间。由并发事务所做的修改必须与任何其他并发事务所做的修改隔离。事务查看数据更新时，数据所处的状态要么是另一事务修改它之前的状态，要么是另一事务修改它之后的状态，事务不会查看到中间状态的数据。

* **持久性(Durability)：** 指的是只要事务成功结束，它对数据库所做的更新就必须保存下来。即使发生系统崩溃，重新启动数据库系统后，数据库还能恢复到事务成功结束时的状态。

**Spring 的事务管理：** Spring为事务管理提供了一致的模板，在高层次上建立了统一的事物抽象。

**Spring支持两种事务编程模型：**

* **编程式事务**

  编程式事务 Spring提供了TransactionTemplate模板，利用该模板我们可以通过编程的方式实现事务管理，而无需关注资源获取、复用、释放、事务同步及异常处理等操作。相对于声明式事务来说，这种方式相对麻烦一些，但是好在更为灵活，我们可以将事务管理的范围控制的更为精确。

  事务功能的相关操作全部通过自己编写代码来实现：

  编程式的实现方式存在缺陷：

  * 细节没有被屏蔽：具体操作过程中，所有细节都需要程序员自己来完成，比较繁琐。
  * 代码复用性不高：如果没有有效抽取出来，每次实现功能都需要自己编写代码，代码就没有得到复用。

* **声明式事务 ​**声明事务案例

  声明式事务 Spring事务管理的亮点在于声明式事务管理，它允许我们通过声明的方式，在IoC配置中指定事务的边界和事务属性，Spring会自动在指定的事务边界上应用事务属性。相对于编程式事务来说，这种方式十分的方便，只需要在需要做事务管理的方法上，增加@Transactional注解，以声明事务特征即可。 声明式事务管理是Spring事务管理的亮点，它允许我们通过声明的方式，在IoC配置中指定事务的边界和事务属性，Spring会自动在指定的事务边界上应用事务属性。相对于编程式事务来说，这种方式十分的方便，只需要在需要做事务管理的方法上，增加@Transactional注解，以声明事务特征即可。

  事务的打开、回滚和提交是由事务管理器来完成的，我们使用不同的数据库访问框架，就要使用与之对应的事务管理器。

  在Spring Boot中，当你添加了数据库访问框架的起步依赖时，它就会进行自动配置，即自动实例化正确的事务管理器。

  对于声明式事务，是使用@Transactional进行标注的。这个注解可以标注在类或者方法上。

  * 当它标注在类上时，代表这个类所有公共（public）非静态的方法都将启用事务功能。
  * 当它标注在方法上时，代表这个方法将启用事务功能。

  另外，在@Transactional注解上，我们可以使用 **[isolation](https://translate.volcengine.com/?category=&home_language=zh&source_language=detect&target_language=zh&text=isolation)**（隔离）属性声明事务的隔离级别，使用 **[propagation](https://translate.volcengine.com/?category=&home_language=zh&source_language=detect&target_language=zh&text=propagation)**（传播）属性声明事务的传播机制。

**隔离级别：** 数据库系统必须具有隔离并发运行各个事务的能力，使它们不会相互影响，避免各种并发问题。一个事务与其他事务隔离的程度称为隔离级别。SQL标准中规定了多种事务隔离级别，不同隔离级别对应不同的干扰程度，隔离级别越高，数据一致性就越好，但并发性越弱。

**隔离级别一共有四种：**

* 读未提交：`READ_UNCOMMITTED`​

  允许Transaction01读取Transaction02未提交的修改。
* 读已提交：`READ_COMMITTED`​

  要求Transaction01只能读取Transaction02已提交的修改。
* 可重复读：`REPEATABLE_READ`​

  确保Transaction01可以多次从一个字段中读取到相同的值，即Transaction01执行期间禁止其它事务对这个字段进行更新。
* 串行化：`SERIALIZABLE`​

  确保Transaction01可以多次从一个表中读取到相同的行，在Transaction01执行期间，禁止其它事务对这个表进行添加、更新、删除操作。可以避免任何并发问题，但性能十分低下。

**各个隔离级别解决并发问题的能力见下表：**

|隔离级别|脏读|不可重复读|幻读|
| ------------------| ------| ------------| ------|
|READ UNCOMMITTED|有|有|有|
|READ COMMITTED|无|有|有|
|REPEATABLE READ|无|无|有|
|SERIALIZABLE|无|无|无|

* 「脏读」

  表示一个事务能够读取另一个事务中还未提交的数据。比如，某个事务尝试插入记录 A，此时该事务还未提交，然后另一个事务尝试读取到了记录 A。
* 「不可重复读」

  是指在一个事务内，多次读同一数据。
* 「幻读」

  指同一个事务内多次查询返回的结果集不一样。比如同一个事务 A 第一次查询时候有 n 条记录，但是第二次同等条件下查询却有 n+1 条记录，这就好像产生了幻觉。发生幻读的原因也是另外一个事务新增或者删除或者修改了第一个事务结果集里面的数据，同一个记录的数据内容被修改了，所有数据行的记录就变多或者变少了。

**各种数据库产品对事务隔离级别的支持程度：**

|隔离级别|Oracle|MySQL|
| ------------------| ----------| ----------|
|READ UNCOMMITTED|×|√|
|READ COMMITTED|√(默认)|√|
|REPEATABLE READ|×|√(默认)|
|SERIALIZABLE|√|√|

**②使用方式**

```java
@Transactional(isolation = Isolation.DEFAULT)			// 使用数据库默认的隔离级别
@Transactional(isolation = Isolation.READ_UNCOMMITTED)		// 读未提交
@Transactional(isolation = Isolation.READ_COMMITTED)		// 读已提交
@Transactional(isolation = Isolation.REPEATABLE_READ)		// 可重复读
@Transactional(isolation = Isolation.SERIALIZABLE)		// 串行化
```

**传播行为：**在 service 类中有 a() 方法和 b() 方法，a()方法上有事务，b()方法上也有事务，当a()方法执行过程中调用了b()方法，事务是如何传递的？合并到一个事务里？还是开启一个新的事务？这就是事务传播行为。

**一共有七种传播行为：**

* [required](https://translate.volcengine.com/?category=&home_language=zh&source_language=detect&target_language=zh&text=REQUIRED)（必需的）：支持当前事务，如果不存在就新建一个(默认)**【没有就新建，有就加入】**
* [supports](https://translate.volcengine.com/?category=&home_language=zh&source_language=detect&target_language=zh&text=SUPPORTS)（支持）：支持当前事务，如果当前没有事务，就以非事务方式执行**【有就加入，没有就不管了】**
* [mandatory](https://translate.volcengine.com/?category=&home_language=zh&source_language=detect&target_language=zh&text=MANDATORY)（强制的）：必须运行在一个事务中，如果当前没有事务正在发生，将抛出一个异常**【有就加入，没有就抛异常】**
* ​`requires_new`​（需要新的）：开启一个新的事务，如果一个事务已经存在，则将这个存在的事务挂起**【不管有没有，直接开启一个新事务，开启的新事务和之前的事务不存在嵌套关系，之前事务被挂起】**
* ​`not_required`​：以非事务方式运行，如果有事务存在，挂起当前事务**【不支持事务，存在就挂起】**
* [never](https://translate.volcengine.com/?category=&home_language=zh&source_language=detect&target_language=zh&text=never)：以非事务方式运行，如果有事务存在，抛出异常**【不支持事务，存在就抛异常】**
* [nested](https://translate.volcengine.com/?category=&home_language=zh&source_language=detect&target_language=zh&text=NESTED)（嵌套的）：如果当前正有一个事务在进行中，则该方法应当运行在一个嵌套式事务中。被嵌套的事务可以独立于外层事务进行提交或回滚。如果外层事务不存在，行为就像REQUIRED一样。**【有事务的话，就在这个事务里再嵌套一个完全独立的事务，嵌套的事务可以独立的提交和回滚。没有事务就和REQUIRED一样。】**

‍
