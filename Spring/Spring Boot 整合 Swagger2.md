# Spring Boot 整合 Swagger2

参考文档

- [大白话讲解Spring的@bean注解](https://zhuanlan.zhihu.com/p/99870991)
- [SpringBoot集成Swagger2](https://blog.csdn.net/Oaklkm/article/details/125679021)
- [Swagger2常用注解说明](https://blog.csdn.net/ThinkWon/article/details/107477801)
- [Swagger2 | 05. 使用Swagger2调试时，部分接口不可传入参数](https://blog.csdn.net/xyxyxyxyxyxyx/article/details/115420853)

## 1、springfox-swagger-ui 使用

**概述：** 前后端分离开发模式中，api文档是最好的沟通方式。Swagger 是一个规范和完整的框架，用于生成、描述、调用和可视化 RESTful 风格的 Web 服务。及时性 (接口变更后，能够及时准确地通知相关前后端开发人员)规范性 (并且保证接口的规范性，如接口的地址，请求方式，参数及响应格式和错误信息)一致性 (接口信息一致，不会出现因开发人员拿到的文档版本不一致，而出现分歧)可测性 (直接在接口文档上进行测试，以方便理解业务)

**Swagger的主要功能如下：**

- 支持 API 自动生成同步的在线文档：使用 Swagger 后可以直接通过代码生成文档，不再需要自己手动编写接口文档了，对程序员来说非常方便，可以节约写文档的时间去学习新技术。
- 提供 Web 页面在线测试 API：光有文档还不够，Swagger 生成的文档还支持在线测试。参数和格式都定好了，直接在界面上输入参数对应的值即可在线测试接口。

结合Spring框架（Spring-fox），Swagger可以很轻松地利用注解以及扫描机制，来快速生成在线文档，以实现当我们项目启动之后，前端开发人员就可以打开Swagger提供的前端页面，查看和测试接口。

**SpringBoot 2.6以上版本修改了路径匹配规则，但是Swagger3还不支持，这里换回之前的，不然启动直接报错：**

```yaml
spring:
 mvc:
  pathmatch:
      matching-strategy: ant_path_matcher
```

项目启动后，我们可以直接打开：<http://localhost:8080/swagger-ui/index.html，这个页面（要是觉得丑，UI是可以换的，支持第三方）会显示所有的API文档，包括接口的路径、支持的方法、接口的描述等，并且我们可以直接对API接口进行测试，非常方便。>

我们可以创建一个配置类去配置页面的相关信息：

### 1.2、依赖引入

```xml
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
</dependency>
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
</dependency>
```

### 1.3、swagger2 配置类

```java
package com.atguigu.ggkt.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket webApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("ggkt")
                .apiInfo(webApiInfo())
                .select()
                // 只显示api路径下的页面
                // .paths(Predicates.and(PathSelectors.regex("/api/.*")))
                .build();
    }

    /**
     * web api信息
     */
    private ApiInfo webApiInfo(){
        return new ApiInfoBuilder()
                .title("网站-API文档")
                .description("本文档描述了网站微服务接口定义")
                .version("1.0")
                .contact(new Contact("atguigu", "http://atguigu.com", "atguigu.com"))
                .build();
    }
}
```

注解说明：

- @Configuration：该类标记为配置类
- @EnableSwagger2：开启 Swagger2
- @Bean：用于注册Bean的注解

### 1.4、配置启动类

```java
package com.atguigu.ggkt.vod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.atguigu")
public class ServiceVodApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceVodApplication.class, args);
    }
}
```

注解说明：

- @ComponentScan(basePackages = "com.atguigu") ：扫描 Swagger2 配置类

接着我们来看看如何为一个Controller编写API描述信息：

```java
@Api(tags = "账户验证接口", description = "包括用户登录、注册、验证码请求等操作。")
@RestController
@RequestMapping("/api/auth")
public class AuthApiController {
```

我们可以直接在类名称上面添加`@Api`​注解，并填写相关信息，来为当前的Controller设置描述信息。

接着我们可以为所有的请求映射配置描述信息：

```java
@ApiResponses({
        @ApiResponse(code = 200, message = "邮件发送成功"),  
        @ApiResponse(code = 500, message = "邮件发送失败")   //不同返回状态码描述
})
@ApiOperation("请求邮件验证码")   //接口描述
@GetMapping("/verify-code")
public RestBean<Void> verifyCode(@ApiParam("邮箱地址")   //请求参数的描述
                                 @RequestParam("email") String email){
```

```java
@ApiIgnore     //忽略此请求映射
@PostMapping("/login-success")
public RestBean<Void> loginSuccess(){
    return new RestBean<>(200, "登陆成功");
}
```

我们也可以为实体类配置相关的描述信息：

```java
@Data
@ApiModel(description = "响应实体封装类")
@AllArgsConstructor
public class RestBean<T> {

    @ApiModelProperty("状态码")
    int code;
    @ApiModelProperty("状态码描述")
    String reason;
    @ApiModelProperty("数据实体")
    T data;

    public RestBean(int code, String reason) {
        this.code = code;
        this.reason = reason;
    }
}
```

这样，我们就可以在文档中查看实体类简介以及各个属性的介绍了。

最后我们再配置一下多环境：

```xml
<profiles>
    <profile>
        <id>dev</id>
        <activation>
            <activeByDefault>true</activeByDefault>
        </activation>
        <properties>
            <environment>dev</environment>
        </properties>
    </profile>
    <profile>
        <id>prod</id>
        <activation>
            <activeByDefault>false</activeByDefault>
        </activation>
        <properties>
            <environment>prod</environment>
        </properties>
    </profile>
</profiles>
```

```xml
<resources>
    <resource>
        <directory>src/main/resources</directory>
        <excludes>
            <exclude>application*.yaml</exclude>
        </excludes>
    </resource>
    <resource>
        <directory>src/main/resources</directory>
        <filtering>true</filtering>
        <includes>
            <include>application.yaml</include>
            <include>application-${environment}.yaml</include>
        </includes>
    </resource>
</resources>
```

首先在Maven中添加两个环境，接着我们配置一下不同环境的配置文件：

```yaml
  jpa:
    show-sql: false
    hibernate:
      ddl-auto: update
springfox:
  documentation:
    enabled: false
```

在生产环境下，我们选择不开启Swagger文档以及JPA的数据库操作日志，这样我们就可以根据情况选择两套环境了。

### 1.5、Swagger2 常用注解

```java
@Api(tags = "讲师管理接口")
@RestController
@RequestMapping(value="/admin/vod/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @ApiOperation("查询所有讲师")
    @GetMapping("findAll")
    public Result<?> findAllTeacher() {
        List<Teacher> list = teacherService.list();
        return Result.ok(list).message("查询数据成功");
    }

    @ApiOperation("逻辑删除讲师")
    @DeleteMapping("remove/{id}")
    public Result removeTeacher(@ApiParam(name = "id", value = "ID", required = true) @PathVariable Long id) {
        boolean isSuccess = teacherService.removeById(id);
        if(isSuccess) {
            return Result.ok(null);
        } else {
            return Result.fail(null);
        }
    }
}
```

> 注解说明：
>
> - @Api(tags = "讲师管理接口")：定义类的说明
> - @ApiOperation("查询所有讲师")：定义接口的说明
> - @ApiParam(name = "id", value = "ID", required = true)：定义参数的说明

### 1.6、测试路径

[http://localhost:8080/swagger-ui.html#/](http://localhost:8080/swagger-ui.html#/)

​`默认路径服务路径 + /swagger-ui.html#/`​

## 2、knife4j-spring-ui 使用

### 2.1、依赖注入

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.2.1.RELEASE</version>
    <relativePath/> <!-- lookup parent from repository -->
</parent>

<properties>
    <springfox-swagger2>3.0.0</springfox-swagger2>
    <knife4j-spring-ui>3.0.3</knife4j-spring-ui>
</properties>

<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>${springfox-swagger2}</version>
        </dependency>
        <dependency>
            <groupId>com.github.xiaoymin</groupId>
            <artifactId>knife4j-spring-ui</artifactId>
            <version>${knife4j-spring-ui}</version>
        </dependency>
    </dependencies>
</dependencyManagement>
```

其余设置与springfox-swagger-ui 使用一致

### 2.2、访问路径

<http://localhost:port/doc.html#/home>

```java
@Api(tags = {"员工相关接口"})
@RestController
@RequestMapping("/enp")
public class EmployeeCtrl {

    @Autowired
    private EmployeeService employeeService;

    @ApiImplicitParams({@ApiImplicitParam(name = "param", value = "员工信息")})
    @ApiOperation(value = "新增员工", notes = "新增员工信息")
    @PostMapping("/save")
    public EmployeeDto saveEmploye(@RequestBody EmployeeDto param) {
        return employeeService.insertEmployee(param);
    }

    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "员工id")})
    @ApiOperation(value = "删除员工", notes = "删除员工信息")
    @PostMapping("/delete")
        public void deleteEmploye(@RequestBody String id) {
        employeeService.deleteEmployee(id);
    }

    @ApiImplicitParams({@ApiImplicitParam(name = "param", value = "员工信息")})
    @ApiOperation(value = "更新员工", notes = "更新员工信息")
    @PostMapping("/update")
    public void updateEmploye(@RequestBody EmployeeDto param) {
        employeeService.updateEmployee(param);
    }

    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "员工id")})
    @ApiOperation(value = "查询员工", notes = "查询员工信息")
    @PostMapping("/get")
    public EmployeeDto getEmploye(@RequestBody String id) {
        return employeeService.getEmployee(id);
    }
}
```

> 常用注解
>
> - @Api(tags = {“员工相关接口”}) 定义模块
> - @ApiImplicitParams({@ApiImplicitParam(name = “param”, value = “员工信息”)})定义参数
> - @ApiOperation(value = “新增员工”, notes = “新增员工信息”)定义接口名称以及描述
