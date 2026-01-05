# MybatisPlus 代码生成器

## 旧版

[Gradle + SpringBoot + Mybatis-plus + 代码生成器 + 多数据源配置 | 码农家园 (codenong.com)](https://www.codenong.com/cs106384267/)

配置 `application.yml`​

```gradle
server:
    port: 8080
spring:
    application:
        name: lcloud
    datasource:
        driver-class-name: com.mysql.cj.jdbc.Driver
        url: jdbc:mysql://192.168.1.102:3306/lcloud?serverTimezone=UTC
        username: root
        password: root
```

## 依赖引入

* maven

  ```xml
  <dependency>
      <groupId>com.baomidou</groupId>
      <artifactId>mybatis-plus-generator</artifactId>
      <version>3.3.1</version>
  </dependency>

  <dependency>
      <groupId>org.apache.velocity</groupId>
      <artifactId>velocity-engine-core</artifactId>
      <version>2.0</version>
  </dependency>
  ```

* gradle `build.gradle`​

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

## 实例代码

```java
.......

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class CodeGet {

    public static void main(String[] args) {

        // 1、创建代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 2、全局配置
        GlobalConfig gc = new GlobalConfig();
        // String projectPath = System.getProperty("user.dir");
        // gc.setOutputDir(projectPath + "/src/main/java");
        gc.setOutputDir("/src/main/java");

        //去掉Service接口的首字母I
        gc.setServiceName("%sService");
        gc.setAuthor("atguigu");
        gc.setOpen(false);
        mpg.setGlobalConfig(gc);

        // 3、数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://192.168.1.102:3306/glkt_vod?characterEncoding=utf-8&useSSL=false");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 4、包配置
        PackageConfig pc = new PackageConfig();
        // com.atguigu.ggkt.live.
        //模块名
        pc.setModuleName("live");
        pc.setParent("com.atguigu.ggkt");

        // 基础代码包
        pc.setController("controller");
        pc.setEntity("entity");
        pc.setService("service");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);

        // 5、策略配置
        StrategyConfig strategy = new StrategyConfig();

        // 配置 数据库表名
        strategy.setInclude("teacher");

        // 数据库表映射到实体的命名策略
        strategy.setNaming(NamingStrategy.underline_to_camel);

        // 数据库表字段映射到实体的命名策略
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);

        // lombok 模型 @Accessors(chain = true) setter链式操作
        strategy.setEntityLombokModel(true);

        // restful api风格控制器
        strategy.setRestControllerStyle(true);

        // url中驼峰转连字符
        strategy.setControllerMappingHyphenStyle(true);
        mpg.setStrategy(strategy);

        // 6、执行
        mpg.execute();
    }
}
```

## 常见问题

### 数据库驱动名称

```cmd
23:06:24.204 [main] DEBUG com.baomidou.mybatisplus.generator.AutoGenerator - ==========================准备生成文件...==========================
Exception in thread "main" java.lang.RuntimeException: java.lang.ClassNotFoundException: com.mysql.cj.jdbc.Driver
 at com.baomidou.mybatisplus.generator.config.DataSourceConfig.getConn(DataSourceConfig.java:170)
 at com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder.<init>(ConfigBuilder.java:110)
 at com.baomidou.mybatisplus.generator.AutoGenerator.execute(AutoGenerator.java:96)
 at com.atguigu.CodeGet.main(CodeGet.java:77)
Caused by: java.lang.ClassNotFoundException: com.mysql.cj.jdbc.Driver
 at java.net.URLClassLoader.findClass(URLClassLoader.java:381)
 at java.lang.ClassLoader.loadClass(ClassLoader.java:424)
 at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:338)
 at java.lang.ClassLoader.loadClass(ClassLoader.java:357)
 at java.lang.Class.forName0(Native Method)
 at java.lang.Class.forName(Class.java:264)
 at com.baomidou.mybatisplus.generator.config.DataSourceConfig.getConn(DataSourceConfig.java:167)
 ... 3 more
```

### slf4j 依赖缺失

```cmd
SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
Exception in thread "main" java.lang.RuntimeException: java.lang.ClassNotFoundException: com.mysql.jdbc.Driver
 at com.baomidou.mybatisplus.generator.config.DataSourceConfig.getConn(DataSourceConfig.java:170)
 at com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder.<init>(ConfigBuilder.java:110)
 at com.baomidou.mybatisplus.generator.AutoGenerator.execute(AutoGenerator.java:96)
 at CodeGet.main(CodeGet.java:76)
Caused by: java.lang.ClassNotFoundException: com.mysql.jdbc.Driver
 at java.net.URLClassLoader.findClass(URLClassLoader.java:387)
 at java.lang.ClassLoader.loadClass(ClassLoader.java:418)
 at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:352)
 at java.lang.ClassLoader.loadClass(ClassLoader.java:351)
 at java.lang.Class.forName0(Native Method)
 at java.lang.Class.forName(Class.java:264)
 at com.baomidou.mybatisplus.generator.config.DataSourceConfig.getConn(DataSourceConfig.java:167)
 ... 3 more
```

## 新版

* [代码生成器（新） | MyBatis-Plus](https://baomidou.com/pages/779a6e/#%E5%AE%89%E8%A3%85)
* [Maven Repository: Search/Browse/Explore](https://mvnrepository.com/)

* 依赖导入

```gradle
plugins {
    id 'java'
    id 'org.springframework.boot' version '3.0.2'
    id 'io.spring.dependency-management' version '1.1.0'
}

group = 'com.zonglin'
version = '0.0.1-SNAPSHOT'
sourceCompatibility = '17'

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
    implementation 'com.baomidou:mybatis-plus-boot-starter:3.5.2'

    /* 代码生成器依赖 */
    implementation 'com.baomidou:mybatis-plus-generator:3.5.2'
    /* freemarker 引擎依赖 */
//    implementation group: 'org.freemarker', name: 'freemarker', version: '2.3.30'

    // https://mvnrepository.com/artifact/com.github.xiaoymin/knife4j-spring-ui
    implementation group: 'com.github.xiaoymin', name: 'knife4j-spring-ui', version: '3.0.3'
    // https://mvnrepository.com/artifact/io.springfox/springfox-swagger2
    implementation group: 'io.springfox', name: 'springfox-swagger2', version: '3.0.0'
    implementation 'org.freemarker:freemarker'
}

tasks.named('test') {
    useJUnitPlatform()
}

tasks.withType(JavaCompile).configureEach {
    options.encoding = "utf-8"
}
tasks.withType(Javadoc).configureEach {
    options.encoding = "utf-8"
}
```

* 生成方法

```gradle
package com.zonglin.lcloud.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class Gen {

    public static void main(String[] args) {

        FastAutoGenerator.create("jdbc:mysql://192.168.1.102:3306/lcloud",
                        "root", "root")
                .globalConfig(builder -> {
                    builder.author("zonglin") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
//                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D:\\.github\\zonglin\\lcloud\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.zonglin.lcloud") // 设置父包名
                            .moduleName("sys") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "D:\\.github\\zonglin\\lcloud\\src\\main\\resources\\mapper\\sys")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("sys_role", "sys_user") // 设置需要生成的表名
                            .addTablePrefix("sys_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

```
