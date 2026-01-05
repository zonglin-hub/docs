# SpringBootServletInitializer

[springboot之SpringBootServletInitializer](https://cloud.tencent.com/developer/article/1749644)

**SpringBootServletInitializer 概述：** 对于Spring Boot应用，我们一般会打成jar包使用内置容器运行，但是有时候我们想要像使用传统springweb项目一样，将Spring Boot应用打成WAR包，然后部署到外部容器运行，那么我们传统的使用Main类启动的方式稍显蹩脚，因为外部容器无法识别到应用启动类，需要在应用中继承SpringBootServletInitializer类，然后重写config方法，将其指向应用启动类。我们将介绍SpringBootServletInitializer的原理和使用。它是WebApplicationInitializer的扩展，从部署在Web容器上的传统WAR文件运行SpringApplication。 此类将Servlet，Filter和ServletContextInitializer bean从应用程序上下文绑定到服务器。

```java
...
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.yami.shop")
public class WebApplication extends SpringBootServletInitializer {

 public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
 }

 @Override
 protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
  return builder.sources(WebApplication.class);
 }
}
```
