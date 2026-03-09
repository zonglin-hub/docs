# SpringBoot 简介

Spring 中文文档：<https://springdoc.cn/>

SpringBoot 是一个 Java 后端框架，一种全新的编程规范，它的产生简化了框架的使用。所谓简化是指简化了 Spring 众多框架中所需的大量且繁琐的配置文件，所以 SpringBoot 是一个**服务于框架的框架**，服务范围是简化配置文件。

‍

## 1.1、约定大于配置

1. SpringBoot 的核心概念是：**约定大于配置**

    SpringBoot 是从 Spring 发展而来的，而开发一个 Spring 应用需要大量配置，这些配置多种多样。而 SpringBoot 要做的事就是**针对不同场景提供一个或多个starter(自启动依赖)**，我们引入这个 starter 就能使用 SpringBoot 默认的约定，加上属性文件，做大量自定义配置，简化开发。

2. SpringBoot 自动配置流程：

    导入 starter 场景启动器后，根据 SpringBoot 的默认规定，首先找到 **META-INF ​**包下的 **spring.factories ​**工厂。

    通过读取 `EnableAutoConfiguration(自动启动配置，下称自启动)`​ 属性的值获取启动时加载的类 ：`XXXAutoConfiguration(XXX自动配置类)`​。

    在自动配置类里，利用 `@Bean`​ 注解把场景下相关组件注册进容器中。

## 1.2、系统需求

|Build Tool|Version|
| ------------| --------------------|
|Maven|3.5+|
|Gradle|7.x (7.5 or later)|

参考文档：[https://docs.spring.io/spring-boot/docs/current/reference/html/getting-started.html#getting-started-system-requirements](https://docs.spring.io/spring-boot/docs/current/reference/html/getting-started.html#getting-started-system-requirements)

## 1.3、SpringBoot优**缺点**

* **优点**

  * **Create stand-alone Spring applications  # 创建独立Spring应用**
  * **Embed Tomcat, Jetty or Undertow directly (no need to deploy WAR files) # 内嵌web服务器**
  * **Provide opinionated 'starter' dependencies to simplify your build configuration # 自动starter依赖，简化构建配置**
  * **Automatically configure Spring and 3rd party libraries whenever possible # 自动配置Spring以及第三方功能**
  * **Provide production-ready features such as metrics, health checks, and externalized configuration # 提供生产级别的监控、健康检查及外部化配置**
  * **Absolutely no code generation and no requirement for XML configuration # 无代码生成、无需编写XML**

* **缺点**

  * **迭代快，需要时刻关注变化**
  * **封装太深，内部原理复杂，不容易精通**

## 1.4、微服务

[James Lewis and Martin Fowler (2014)](https://martinfowler.com/articles/microservices.html)  提出微服务完整概念。

[https://martinfowler.com/microservices/](https://martinfowler.com/microservices/)

In short, the microservice architectural style is an approach to developing a single application as a **suite of small services**, each **running in its own process** and communicating with lightweight mechanisms, often an HTTP resource API. These services are **built around business capabilities** and **independently deployable** by fully automated deployment machinery. There is a **bare minimum of centralized management** of these services, which may be written in different programming languages and use different data storage technologies.

-- [James Lewis and Martin Fowler (2014)](https://martinfowler.com/articles/microservices.html)

* **微服务是一种架构风格**
* **一个应用拆分为一组小型服务**
* **每个服务运行在自己的进程内，也就是可独立部署和升级**
* **服务之间使用轻量级HTTP交互**
* **服务围绕业务功能拆分**
* **可以由全自动部署机制独立部署**
* **去中心化，服务自治。服务可以使用不同的语言、不同的存储技术**
