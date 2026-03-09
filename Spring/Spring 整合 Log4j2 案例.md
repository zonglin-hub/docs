# Spring整合Log4j2案例

* 引入 Log4j2 依赖

  ```shell
  <!--log4j2的依赖-->
  <dependency>
      <groupId>org.apache.logging.log4j</groupId>
      <artifactId>log4j-core</artifactId>
      <version>2.19.0</version>
  </dependency>
  <dependency>
      <groupId>org.apache.logging.log4j</groupId>
      <artifactId>log4j-slf4j2-impl</artifactId>
      <version>2.19.0</version>
  </dependency>
  ```

* 加入日志配置文件 在类的根路径下提供 <u>log4j2.xml</u> 配置文件（文件名固定为：log4j2.xml，文件必须放到类根路径下。）

  ```shell
  <?xml version="1.0" encoding="UTF-8"?>
  <configuration>
      <loggers>
          <!--
              level指定日志级别，从低到高的优先级：
                  TRACE < DEBUG < INFO < WARN < ERROR < FATAL
                  trace：追踪，是最低的日志级别，相当于追踪程序的执行
                  debug：调试，一般在开发中，都将其设置为最低的日志级别
                  info：信息，输出重要的信息，使用较多
                  warn：警告，输出警告的信息
                  error：错误，输出错误信息
                  fatal：严重错误
          -->
          <root level="DEBUG">
              <appender-ref ref="spring6log"/>
              <appender-ref ref="RollingFile"/>
              <appender-ref ref="log"/>
          </root>
      </loggers>

      <appenders>
          <!--输出日志信息到控制台-->
          <console name="spring6log" target="SYSTEM_OUT">
              <!--控制日志输出的格式-->
              <PatternLayout pattern="%d{yyyy-MM-dd HH:mm:ss SSS} [%t] %-3level %logger{1024} - %msg%n"/>
          </console>

          <!--文件会打印出所有信息，这个log每次运行程序会自动清空，由append属性决定，适合临时测试用--> 
   <!--D:\.github\.dome（开发示例）\spring6-dome-->
          <File name="log" fileName="d:/.github/.dome（开发示例）/spring6-dome/spring6_log/test.log" append="false">
              <PatternLayout pattern="%d{HH:mm:ss.SSS} %-5level %class{36} %L %M - %msg%xEx%n"/>
          </File>

          <!-- 这个会打印出所有的信息，
              每次大小超过size，
              则这size大小的日志会自动存入按年份-月份建立的文件夹下面并进行压缩，
              作为存档-->
          <RollingFile name="RollingFile" fileName="d:/.github/.dome（开发示例）/spring6-dome/spring6_log/app.log"
                       filePattern="log/$${date:yyyy-MM}/app-%d{MM-dd-yyyy}-%i.log.gz">
              <PatternLayout pattern="%d{yyyy-MM-dd 'at' HH:mm:ss z} %-5level %class{36} %L %M - %msg%xEx%n"/>
              <SizeBasedTriggeringPolicy size="50MB"/>
              <!-- DefaultRolloverStrategy属性如不设置，
              则默认为最多同一文件夹下7个文件，这里设置了20 -->
              <DefaultRolloverStrategy max="20"/>
          </RollingFile>
      </appenders>
  </configuration>
  ```

* 测试

  运行原测试程序，多了spring打印日志

* Java 测试

  ```java
  public class HelloWorldTest {

      private Logger logger = LoggerFactory.getLogger(HelloWorldTest.class);

      @Test
      public void testHelloWorld(){
          ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
          HelloWorld helloworld = (HelloWorld) ac.getBean("helloWorld");
          helloworld.sayHello();
          logger.info("执行成功");
      }
  }
  ```

* Kotlin 测试

  ```kotlin
  package org.example
  
  import org.junit.jupiter.api.Test
  import org.slf4j.Logger
  import org.slf4j.LoggerFactory
  import org.springframework.context.ApplicationContext
  import org.springframework.context.support.ClassPathXmlApplicationContext


  class HelloWorldTest {

      private val logger: Logger = LoggerFactory.getLogger(HelloWorldTest::class.java)
    
      @Test
      fun testHelloWorld() {
          val ac: ApplicationContext = ClassPathXmlApplicationContext("bean.xml")
          val bean = ac.getBean("helloWorld") as HelloWorld
          bean.sayHello()
          logger.info("执行成功")
      }
  }

  ```
  