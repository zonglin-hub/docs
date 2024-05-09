# MyBatisPlus 入门

- ‍MyBatis-Plus官网：<https://baomidou.com/>
- github仓库：<https://github.com/baomidou/mybatis-plus>
- MyBatis Plus 教程：<https://www.hxstrive.com/subject/mybatis_plus/260.htm>

## 数据库 Schema 脚本

```sql
CREATE DATABASE mybatis_plus;

USE mybatis_plus;

CREATE TABLE user
(
    id BIGINT(20) NOT NULL COMMENT '主键ID',
    name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
    age INT(11) NULL DEFAULT NULL COMMENT '年龄',
    email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
    PRIMARY KEY (id)
);

INSERT INTO user (id, name, age, email) VALUES
(1, 'Jone', 18, 'test1@baomidou.com'),
(2, 'Jack', 20, 'test2@baomidou.com'),
(3, 'Tom', 28, 'test3@baomidou.com'),
(4, 'Sandy', 21, 'test4@baomidou.com'),
(5, 'Billie', 24, 'test5@baomidou.com');
```

‍

## **初始化 Spring Boot 工程**

- [Spring Initializr](https://start.spring.io/)
- [Cloud Native App Initializer (aliyun.com)](https://start.aliyun.com/)

### **项目引入依赖**

- maven

  ```xml
  <dependencies>
      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter</artifactId>
      </dependency>

      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-test</artifactId>
          <scope>test</scope>
       </dependency>

      <!--mybatis-plus-->
      <dependency>
          <groupId>com.baomidou</groupId>
          <artifactId>mybatis-plus-boot-starter</artifactId>
          <version>3.3.1</version>
      </dependency>

      <!--mysql-->
      <dependency>
          <groupId>mysql</groupId>
          <artifactId>mysql-connector-java</artifactId>
      </dependency>

      <!--lombok用来简化实体类-->
      <dependency>
          <groupId>org.projectlombok</groupId>
          <artifactId>lombok</artifactId>
      </dependency>
  </dependencies>
  ```

- gradle

  ```gradle
  plugins {
      id 'org.springframework.boot' version '2.6.11'
      id 'io.spring.dependency-management' version '1.0.13.RELEASE'
      id 'java'
  }
  
  group = 'com.example'
  version = '0.0.1-SNAPSHOT'
  sourceCompatibility = '11'
  
  configurations {
      developmentOnly
      runtimeClasspath {
          extendsFrom developmentOnly
      }
      compileOnly {
          extendsFrom annotationProcessor
      }
  }
  
  repositories {
      mavenCentral()
  }
  
  dependencies {
      implementation 'org.springframework.boot:spring-boot-starter-web'
      compileOnly 'org.projectlombok:lombok'
      developmentOnly 'org.springframework.boot:spring-boot-devtools'
      runtimeOnly 'mysql:mysql-connector-java'
      annotationProcessor 'org.projectlombok:lombok'
      testImplementation 'org.springframework.boot:spring-boot-starter-test'
  
     /* mybatis-plus 启动依赖 */
      implementation group: 'com.baomidou', name: 'mybatis-plus-boot-starter', version: '3.3.2'
  
      /* 代码生成器依赖 */
      implementation group: 'com.baomidou', name: 'mybatis-plus-generator', version: '3.3.2'
      /* freemarker 引擎依赖 */
      implementation group: 'org.freemarker', name: 'freemarker', version: '2.3.30'
  
  }
  
  test {
      useJUnitPlatform()
  }
  
  tasks.withType(JavaCompile).configureEach {
      options.encoding = "utf-8"
  }
  tasks.withType(Javadoc).configureEach {
      options.encoding = "utf-8"
  }
  ```

### 创建配置文件

**mysql5**

```properties
#mysql数据库连接
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/mybatis_plus
spring.datasource.username=root
spring.datasource.password=123456
```

**mysql8以上（spring boot 2.1）注意：driver和url的变化**

```properties
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/mybatis_plus?serverTimezone=GMT%2B8
spring.datasource.username=root
spring.datasource.password=root
```

配置 `application.yml`​

```gradle
server:
    port: 8080
spring:
    application:
        name: demo-mybatis
    datasource:
        driver-class-name: com.mysql.cj.jdbc.Driver
        password: root
        url: jdbc:mysql://127.0.0.1:3306/mybatis_plus?serverTimezone=UTC
        username: root
```

> 扩展

1. 这里的 url 使用了 `?serverTimezone=GMT%2B8`​ 后缀，因为Spring Boot 2.1 集成了 8.0版本的jdbc驱动，这个版本的 jdbc 驱动需要添加这个后缀，否则运行测试用例报告如下错误：

    `java.sql.SQLException: The server time zone value 'ÖÐ¹ú±ê×¼Ê±¼ä' is unrecognized or represents more`​

2. `driver-class-name`​ 使用了 `com.mysql.cj.jdbc.Driver`​ ，在 jdbc 8 中 建议使用这个驱动，之前的 `com.mysql.jdbc.Driver`​ 已经被废弃，否则运行测试用例的时候会有 WARN 信息

### 编写代码

**（1）创建启动类**

在 Spring Boot 启动类中添加 `@MapperScan`​ 注解，扫描 Mapper 文件夹

```java
@SpringBootApplication
@MapperScan("com.atguigu.mybatisplus.mapper") // 在 Spring Boot 启动类中添加 `@MapperScan` 注解，扫描 Mapper 文件夹
public class MybatisPlusApplication {
    ......
}
```

**（2）创建实体类**

```java
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
```

**（3）创建Mapper**

```java
public interface UserMapper extends BaseMapper<User> {
  
}
```

**（4）功能测试-查询所有记录**

```java
public class MybatisPlusApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectList() {
        System.out.println(("----- selectAll method test ------"));
        //UserMapper 中的 selectList() 方法的参数为 MP 内置的条件封装器 Wrapper
        //所以不填写就是无任何条件
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }
}
```

**注意：**

IDEA在 userMapper 处报错，因为找不到注入的对象，因为类是动态创建的，但是程序可以正确的执行。

为了避免报错，可以在 dao 层 的接口上添加 `@Repository`​ 注解

#### MyBatisPlus实现CRUD操作

##### 插入操作

```java
public class CRUDTests {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void testInsert(){
        User user = new User();
        user.setName("mary");
        user.setAge(18);
        user.setEmail("atguigu@qq.com");
        int result = userMapper.insert(user);
        System.out.println(result); //影响的行数
        System.out.println(user); //id自动回填
    }
}
```

**查看sql输出日志**

```properties
#mybatis日志
mybatis-plus.configuration.log-impl=org.apache.ibatis.logging.stdout.StdOutImpl
```

##### 主键策略

**（1）ID_WORKER**

MyBatis-Plus默认的主键策略是：ID_WORKER  *全局唯一ID*

**（2）自增策略**

- 要想主键自增需要配置如下主键策略
-  

- 需要在创建数据表的时候设置主键自增
- 实体字段中配置 @TableId(type = IdType.AUTO)

```java
@TableId(type = IdType.AUTO)
private Long id;
```

其它主键策略：分析 IdType 源码可知

```java
public enum IdType {
     /**
     * 数据库ID自增
     */
    AUTO(0),
  
    /**
     * 该类型为未设置主键类型
     */
    NONE(1),
  
    /**
     * 用户输入ID
     * 该类型可以通过自己注册自动填充插件进行填充
     */  
    INPUT(2),
  
    /**
     * 全局唯一ID
     */  
    ASSIGN_ID(3),
  
    /**
     * 全局唯一ID (UUID)
     */
    ASSIGN_UUID(4),
  
    /** @deprecated */
    @Deprecated
    ID_WORKER(3),
    /** @deprecated */
    @Deprecated
    ID_WORKER_STR(3),
    /** @deprecated */
    @Deprecated
    UUID(4);
    private final int key;
    private IdType(int key) {
        this.key = key;
    }
    public int getKey() {
        return this.key;
    }
}
```

##### 根据Id更新操作

**注意：**update时生成的sql自动是动态sql：UPDATE user SET age=? WHERE id=?

```java
    @Test
    public void testUpdateById(){
        //1 根据id查询记录
        User user = userMapper.selectById(1L);
        //2 设置修改的值
        user.setAge(50);
        //3 调用方法修改
        int result = userMapper.updateById(user);
        System.out.println(result);
    }
```

##### 分页查询

MyBatis Plus自带分页插件，只要简单的配置即可实现分页功能

**（1）创建配置类**

```java
/**
 * 分页插件
 */
@Bean
public PaginationInterceptor paginationInterceptor() {
    return new PaginationInterceptor();
}
```

**（2）测试selectPage分页**

**测试：**最终通过page对象获取相关数据

```java
@Test
public void testSelectPage() {
    Page<User> page = new Page<>(1,5);
    userMapper.selectPage(page, null);
    page.getRecords().forEach(System.out::println);
    System.out.println(page.getCurrent());
    System.out.println(page.getPages());
    System.out.println(page.getSize());
    System.out.println(page.getTotal());
    System.out.println(page.hasNext());
    System.out.println(page.hasPrevious());
}
```

控制台sql语句打印：SELECT id,name,age,email,create_time,update_time FROM user LIMIT 0,5

##### 根据id删除记录

```java
@Test
public void testDeleteById(){
    int result = userMapper.deleteById(8L);
    System.out.println(result);
}
```

##### 批量删除

```java
    @Test
    public void testDeleteBatchIds() {
        int result = userMapper.deleteBatchIds(Arrays.asList(8, 9, 10));
        System.out.println(result);
    }
```

##### 逻辑删除

- 物理删除：真实删除，将对应数据从数据库中删除，之后查询不到此条被删除数据
- 逻辑删除：假删除，将对应数据中代表是否被删除字段状态修改为“被删除状态”，之后在数据库中仍旧能看到此条数据记录

**（1）数据库中添加 deleted字段**

```sql
ALTER TABLE `user` ADD COLUMN `deleted` boolean
```

**（2）实体类添加deleted字段**

并加上 @TableLogic 注解

```java
@TableLogic
private Integer deleted;
```

**（3）application.properties 加入配置**

此为默认值，如果你的默认值和mp默认的一样,该配置可无

```properties
mybatis-plus.global-config.db-config.logic-delete-value=1
mybatis-plus.global-config.db-config.logic-not-delete-value=0
```

**（5）测试逻辑删除**

- 测试后发现，数据并没有被删除，deleted字段的值由0变成了1
- 测试后分析打印的sql语句，是一条update
- **注意：**被删除数据的deleted 字段的值必须是 0，才能被选取出来执行逻辑删除的操作

```java
/**
 * 测试 逻辑删除
 */
@Test
public void testLogicDelete() {
    int result = userMapper.deleteById(1L);
    System.out.println(result);
}
```

**（7）测试逻辑删除后的查询**

MyBatis Plus中查询操作也会自动添加逻辑删除字段的判断

```java
/**
 * 测试 逻辑删除后的查询：
 * 不包括被逻辑删除的记录
 */
@Test
public void testLogicDeleteSelect() {
    User user = new User();
    List<User> users = userMapper.selectList(null); // SELECT id,name,age,email,create_time,update_time,deleted FROM user WHERE deleted=0;
    users.forEach(System.out::println);
}
```

‍

#### MyBatisPlus条件构造器

[条件构造器 | MyBatis-Plus (baomidou.com)](https://baomidou.com/pages/10c804/#abstractwrapper)

##### QueryWrapper 使用

**（1）ge、gt、le、lt**

```java
@Test
public void testSelect() {
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.ge("age", 28);
    List<User> users = userMapper.selectList(queryWrapper);
    System.out.println(users);
}
```

**（2）eq、ne**

**注意：** seletOne返回的是一条实体记录，当出现多条时会报错

```java
@Test
public void testSelectOne() {
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("name", "Tom");
    User user = userMapper.selectOne(queryWrapper); // SELECT id,name,age,email,create_time,update_time,deleted,version FROM user WHERE deleted=0 AND name = ?
    System.out.println(user);
}
```

‍

**（3）like、likeLeft、likeRight**

selectMaps返回Map集合列表

```java
@Test
public void testSelectMaps() {
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper
        .like("name", "e")
        .likeRight("email", "t");
    List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);//返回值是Map列表 SELECT id,name,age,email,create_time,update_time,deleted,version FROM user WHERE deleted=0 AND name LIKE ? AND email LIKE ?
    maps.forEach(System.out::println);
}
```

‍

**（4）orderByDesc、orderByAsc**

```java
@Test
public void testSelectListOrderBy() {
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.orderByDesc("id");
    List<User> users = userMapper.selectList(queryWrapper); // SELECT id,name,age,email,create_time,update_time,deleted,version FROM user WHERE deleted=0 ORDER BY id DESC
    users.forEach(System.out::println);
}
```

‍

##### LambdaQueryWrapper  使用

```java
@Test
public void testLambdaQuery() {
    LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(User::getAge,30);
    queryWrapper.like(User::getName,"张");
    List<User> list = userMapper.selectList(queryWrapper); // SELECT id,name,age,email,create_time,update_time,deleted,version FROM user WHERE deleted=0 AND age = ? AND name LIKE ?
    System.out.println(list);
}
```

‍

#### MyBatisPlus封装Service层

##### 创建service

```java
public interface UserService extends IService<User> {
  
}
```

##### 创建service实现类

```java
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
```

##### 方法调用测试

```java
@SpringBootTest
class TestApplicationTests {

    //注入service
    @Autowired
    private UserService userService;
  
    //查询表所有数据
    @Test
    public void findAll() {
        List<User> userList = userService.list();
        for (User user:userList) {
            System.out.println(user);
        }
    }
}
```

‍
