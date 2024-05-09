## Jul 日志引入

```java{6}
...
import java.util.logging.Logger;

public class JulMain {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger(JulMain.class.getName());
        logger.info("崇尚官方开发组：Jul");
    }
}
```

## log4j 日志引入

```xml
<!-- log4j 依赖 -->
<dependency>
 <groupId>log4j</groupId>
 <artifactId>log4j</artifactId>
 <version>1.2.17</version>
</dependency>
```

log4j.properties

```properties
# 日志级别
# trace < debug < info < warn < error < fatal
log4j.rootLogger=trace
log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=%d %p [%c] - %m%n
```

```java
...
import org.apache.log4j.Logger;

public class Log4jMain {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger(Log4jMain.class);
        logger.info("崇尚开源开发组：Jul");
    }
}
```

## log4j（实现） + slf4j（门面）

```xml
<!-- log4j 依赖 -->
<dependency>
 <groupId>log4j</groupId>
 <artifactId>log4j</artifactId>
 <version>1.2.17</version>
</dependency>
<!-- slf4j 依赖 -->
<dependency>
 <groupId>org.slf4j</groupId>
 <artifactId>slf4j-api</artifactId>
</dependency>
<!-- 添加 slf4j -> log4j 的桥接器 -->
<dependency>
 <groupId>org.slf4j</groupId>
 <artifactId>slf4j-log4j12</artifactId>
</dependency>
```

log4j.properties

```properties
# 日志级别
# trace < debug < info < warn < error < fatal
log4j.rootLogger=trace
log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=%d %p [%c] - %m%n
```

```java
...
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * log4j（实现） + slf4j（门面）
 */
public class Log4jMain {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Log4jMain.class);
        System.out.println(logger.getClass());
        logger.info("崇尚开源开发组：Jul");
    }
}
```

## Jul + JCL

commons-logging.properties

```properties
# 指定具体的日志实现
org.apache.commons.logging.log=org.apache.commons.logging.impl.Jdk14Logger
```

```xml
<!-- 引入 JCL门面依赖-->
<dependency>
 <groupId>commons-logging</groupId>
 <artifactId>commons-logging</artifactId>
 <version>1.2</version>
</dependency>
```

```java
...
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JulMain {
    public static void main(String[] args) {
        Log log = LogFactory.getLog(JulMain.class);
        System.out.println(log.getClass());
        log.info("崇尚官方开发组：Jul");
    }
}
```

## JCL slf4j 整合

```xml
<!-- 桥接器：将 JCL 转换 slf4j -->
<dependency>
 <groupId>org.slf4j</groupId>
 <artifactId>jcl-over-slf4j</artifactId>
</dependency>
```
