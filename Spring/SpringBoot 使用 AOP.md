## SpringBoot 使用 AOP

- [Springboot注解@Target用法](https://blog.csdn.net/xuxin132133/article/details/86981884)
- [SpringBoot使用AOP](https://blog.csdn.net/qq_33257527/article/details/82561635)

### 依赖注入

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
```

#### 一个切面类

```java
package com.dalaoyang.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.*;

@Aspect
@Component
public class LogAspect {
    @Pointcut("execution(public * com.dalaoyang.controller.*.*(..))")
    public void LogAspect(){}

    @Before("LogAspect()")
    public void doBefore(JoinPoint joinPoint){
        System.out.println("doBefore");
    }

    @After("LogAspect()")
    public void doAfter(JoinPoint joinPoint){
        System.out.println("doAfter");
    }

    @AfterReturning("LogAspect()")
    public void doAfterReturning(JoinPoint joinPoint){
        System.out.println("doAfterReturning");
    }

    @AfterThrowing("LogAspect()")
    public void deAfterThrowing(JoinPoint joinPoint){
        System.out.println("deAfterThrowing");
    }

    @Around("LogAspect()")
    public Object deAround(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("deAround");
        return joinPoint.proceed();
    }

}
```

参数说明：

- @Aspect 表明是一个切面类
- @Component 将当前类注入到Spring容器内
- @Pointcut 切入点，其中execution用于使用切面的连接点。使用方法：execution(方法修饰符(可选) 返回类型 方法名 参数 异常模式(可选)) ，可以使用通配符匹配字符，*可以匹配任意字符。
- @Before 在方法前执行
- @After 在方法后执行
- @AfterReturning 在方法执行后返回一个结果后执行
- @AfterThrowing 在方法执行过程中抛出异常的时候执行
- @Around 环绕通知，就是可以在执行前后都使用，这个方法参数必须为ProceedingJoinPoint，proceed()方法就是被切面的方法，上面四个方法可以使用JoinPoint，JoinPoint包含了类名，被切面的方法名，参数等信息。

### 自定义注解

```java
package com.atguigu.system.annotation;
​
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import com.atguigu.system.enums.BusinessType;
import com.atguigu.system.enums.OperatorType;
​

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
​
    public String title() default "";​
}
```

**参数说明：**

RetentionPolicy.RUNTIME：注解不仅被保存到class文件中，jvm加载class文件之后，仍然存在

@Document：是 java 在生成文档，是否显示注解的开关。

@Target：设定注解范围

- ElementType.PARAMETER：可用于参数上

- METHOD可用于描述方法上

### 使用方式

```java
@Log(title = "角色管理", businessType = BusinessType.INSERT)
@PreAuthorize("hasAuthority('bnt.sysRole.add')")
@PostMapping("/save")
public Result save(@RequestBody @Validated SysRole role) {
    sysRoleService.save(role);
    return Result.ok();
}
```
