参考文档：

- [2023版Spring6零基础视频教程，spring入门到精通](https://www.bilibili.com/video/BV1kR4y1b7Qc?p=44&vd_source=9bfc54d2ed901f1eab04708cc346c2f5)
- [Core Technologies (spring.io)](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#spring-core)

---

Java 反射实现 Spring IoC 案例：[gitee](https://gitee.com/liuzonglin1/java-dome/tree/master/spring-ioc)

[Spring loC容器和bean介绍](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-introduction)：Spring框架实现的控制反转(IoC)。 loC也称为依赖注入(DI)。它不是一门技术，而是一种设计思想，是一个重要的面向对象编程法则，能够指导我们如何设计出松耦合、更优良的程序。在这个过程中，对象仅通过构造函数参数、工厂方法的参数或在构造或从工厂方法返回对象实例后在对象实例上设置的属性来定义它们的依赖关系(也就是说，他们使用的其他对象)。然后容器在创建bean时注入这些依赖项。而这个过程基本上是bean本身的逆过程(因此得名，反转控制)，通过使用类的直接构造或服务定位器模式等机制来控制其依赖项的实例化或位置。

依赖注入实现方式：

- [基于setter的依赖注入](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-setter-injection)
- [基于构造函数的依赖注入](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-constructor-injection)

[Bean 的作用域](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-factory-scopes)：**​ ​**在创建bean定义时，您创建了用于创建由该bean定义定义的类的实际实例的配方。bean定义是一个配方的想法很重要，因为这意味着，与类一样，您可以从一个配方创建许多对象实例。

Spring 提供了IoC 容器的两种实现方式：

1. [BeanFactory](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-beanfactory)：这是 IoC 容器的基本实现，是 Spring 内部使用的接口。面向 Spring 本身，不提供给开发人员使用。

2. ApplicationContext：BeanFactory 的子接口，提供了更多高级特性。面向 Spring 的使用者，几乎所有场合都使用 ApplicationContext 而不是底层的 BeanFactory。

 | 类型名                          | 简介                                                                                                                          |
 | --------------------------------- | ------------------------------------------------------------------------------------------------------------------------------- |
 | ClassPathXmlApplicationContext  | 通过读取类路径下的 XML 格式的配置文件创建 IOC 容器对象                                                                        |
 | FileSystemXmlApplicationContext | 通过文件系统路径读取 XML 格式的配置文件创建 IOC 容器对象                                                                      |
 | ConfigurableApplicationContext  | ApplicationContext 的子接口，包含一些扩展方法 refresh() 和 close() ，让 ApplicationContext 具有启动、关闭和刷新上下文的能力。 |
 | WebApplicationContext           | 专门为 Web 应用准备，基于 Web 环境创建 IOC 容器对象，并将对象引入存入 ServletContext 域中。                                   |
