# Spring Boot 如何在项目启动后自动执行某些方法呢

```java
package com.example.docker1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Component // 被 spring 容器管理
@Order(1) // 如果多个自定义的 ApplicationRunner  ，用来标明执行的顺序
public class BeanerApplicationRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("\n-----------------------------------------------------------\n\t" +
                        "项目启动成功！\n\t" +
                        "接口文档：\t{} \n\t" +
                        "接口文档：\t{} \n\t" +
                        "接口文档：\t{} \n" +
                        "-----------------------------------------------------------",
                "http://127.0.0.1:8080:/doc.html",
                "http://doc.bullall.top",
                "@liuzonglin"
        );
    }
}
```

## 参考

- [Spring Boot 如何在项目启动后自动执行某些方法呢](https://www.bilibili.com/video/BV1NR4y1U7em/?spm_id_from=333.999.0.0&vd_source=9bfc54d2ed901f1eab04708cc346c2f5)
- [SpringBoot启动后实现自动执行其它业务方法功能](https://blog.csdn.net/jike11231/article/details/118149202)
