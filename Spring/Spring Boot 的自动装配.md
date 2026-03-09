# Spring Boot的自动装配

- 属性配置 [Common Application Properties (spring.io)](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html)
- 服务扩展 [Developing with Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/html/using.html)
- [SpringBoot 自动装配原理详解 | JavaGuide(Java面试+学习指南)](https://javaguide.cn/system-design/framework/spring/spring-boot-auto-assembly-principles.html)

---

**Spring Boot的自动装配的过程是：** Spring Boot 通过 @EnableAutoConfiguration 注解开启自动配置，加载 spring.factories 中注册的各种 AutoConfiguration 类，当某个 AutoConfiguration 类满足其注解 @Conditional 指定的生效条件时，实例化该AutoConfiguration 类中定义的 Bean（组件等），并注入 Spring 容器，就可以完成依赖框架的自动配置。@EnableAutoConfiguration 作用 从 classpath 中搜索所有 META-INF/spring.factories 配置文件然后，将其中`org.springframework.boot.autoconfigure.EnableAutoConfiguration`​ key对应的配置项加载到spring容器 只有spring.boot.enableautoconfiguration为true（默认为true）的时候，才启用自动配置 @EnableAutoConfiguration还可以根据class来排除（exclude），或是根据class name（excludeName）来排除 其内部实现的关键点有

- ImportSelector 该接口的方法的返回值都会被纳入到spring容器管理中

- SpringFactoriesLoader 该类可以从classpath中搜索所有META-INF/spring.factories配置文件，并读取配置

## 1、SpringBoot特点

### 依赖管理

- **父项目做依赖管理**

  ```xml
  <!--依赖管理-->
   <parent>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-parent</artifactId>
       <version>2.3.4.RELEASE</version>
   </parent>
  <!--他的父项目-->
    <parent>   
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-dependencies</artifactId>
        <version>2.3.4.RELEASE</version>
    </parent>
  ```

- **开发导入starter场景启动器**

  ```xml
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter</artifactId>
  </dependency>
  ```

- **可以修改默认版本号**

  ```xml
      <properties>
          <mysql.version>5.1.43</mysql.version>
      </properties>
  ```

### 自动配置

```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-tomcat</artifactId>
        <version>2.3.4.RELEASE</version>
        <scope>compile</scope>
    </dependency>
```

### @Configuration 配置标记

- **基本使用**

```java
#############################Configuration使用示例######################################################
/**
 * 1、配置类里面使用@Bean标注在方法上给容器注册组件，默认也是单实例的
 * 2、配置类本身也是组件
 * 3、proxyBeanMethods：代理bean的方法
 *      Full(proxyBeanMethods = true)、【保证每个@Bean方法被调用多少次返回的组件都是单实例的】
 *      Lite(proxyBeanMethods = false)【每个@Bean方法被调用多少次返回的组件都是新创建的】
 *      组件依赖必须使用Full模式默认。其他默认是否Lite模式
 *
 *
 *
 */
@Configuration(proxyBeanMethods = false) //告诉SpringBoot这是一个配置类 == 配置文件
class MyConfig {

    /**
     * Full:外部无论对配置类中的这个组件注册方法调用多少次获取的都是之前注册容器中的单实例对象
     * @return
     */
    @Bean //给容器中添加组件。以方法名作为组件的id。返回类型就是组件类型。返回的值，就是组件在容器中的实例
    public User user01(){
        User zhangsan = new User("zhangsan"， 18);
        //user组件依赖了Pet组件
        zhangsan.setPet(tomcatPet());
        return zhangsan;
    }
    @Bean("tom")
    public Pet tomcatPet(){
        return new Pet("tomcat");
    }
}

################################@Configuration测试代码如下########################################
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan("com.atguigu.boot")
class MainApplication {
    public static void main(String[] args) {
        //1、返回我们IOC容器
        ConfigurableApplicationContext run = SpringApplication.run(MainApplication.class， args);
        //2、查看容器里面的组件
        String[] names = run.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }
        //3、从容器中获取组件
        Pet tom01 = run.getBean("tom"， Pet.class);
        Pet tom02 = run.getBean("tom"， Pet.class);
        System.out.println("组件："+(tom01 == tom02));
        //4、com.atguigu.boot.config.MyConfig$$EnhancerBySpringCGLIB$$51f1e1ca@1654a892
        MyConfig bean = run.getBean(MyConfig.class);
        System.out.println(bean);
        //如果@Configuration(proxyBeanMethods = true)代理对象调用方法。SpringBoot总会检查这个组件是否在容器中有。
        //保持组件单实例
        User user = bean.user01();
        User user1 = bean.user01();
        System.out.println(user == user1);
        User user01 = run.getBean("user01"， User.class);
        Pet tom = run.getBean("tom"， Pet.class);
        System.out.println("用户的宠物："+(user01.getPet() == tom));
    }
}
```

### **@Bean、@Component、@Controller、@Service、@Repository @ComponentScan、@Import**

```java
 /* 4、@Import({User.class， DBHelper.class})
       给容器中自动创建出这两个类型的组件、默认组件的名字就是全类名*/
@Import({User.class， DBHelper.class})
@Configuration(proxyBeanMethods = false) // 告诉SpringBoot这是一个配置类 == 配置文件
public class MyConfig {
}
```

### **@Conditional**

**条件装配：满足Conditional指定的条件，则进行组件注入**

```java
=====================测试条件装配==========================
@Configuration(proxyBeanMethods = false) //告诉SpringBoot这是一个配置类 == 配置文件
//@ConditionalOnBean(name = "tom")
@ConditionalOnMissingBean(name = "tom")
public class MyConfig {


    /**
     * Full:外部无论对配置类中的这个组件注册方法调用多少次获取的都是之前注册容器中的单实例对象
     * @return
     */

    @Bean //给容器中添加组件。以方法名作为组件的id。返回类型就是组件类型。返回的值，就是组件在容器中的实例
    public User user01(){
        User zhangsan = new User("zhangsan"， 18);
        //user组件依赖了Pet组件
        zhangsan.setPet(tomcatPet());
        return zhangsan;
    }

    @Bean("tom22")
    public Pet tomcatPet(){
        return new Pet("tomcat");
    }
}

public static void main(String[] args) {
        //1、返回我们IOC容器
        ConfigurableApplicationContext run = SpringApplication.run(MainApplication.class， args);

        //2、查看容器里面的组件
        String[] names = run.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }

        boolean tom = run.containsBean("tom");
        System.out.println("容器中Tom组件："+tom);

        boolean user01 = run.containsBean("user01");
        System.out.println("容器中user01组件："+user01);

        boolean tom22 = run.containsBean("tom22");
        System.out.println("容器中tom22组件："+tom22);


    }
```

## **原生配置文件引入**

### **@ImportResource**

```xml
======================beans.xml=========================
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">

    <bean id="haha" class="com.atguigu.boot.bean.User">
        <property name="name" value="zhangsan"></property>
        <property name="age" value="18"></property>
    </bean>

    <bean id="hehe" class="com.atguigu.boot.bean.Pet">
        <property name="name" value="tomcat"></property>
    </bean>
</beans>
```

```java
@ImportResource("classpath:beans.xml")
public class MyConfig {}

======================测试=================
        boolean haha = run.containsBean("haha");
        boolean hehe = run.containsBean("hehe");
        System.out.println("haha："+haha);//true
        System.out.println("hehe："+hehe);//true
```

## 配置绑定

**如何使用Java读取到properties文件中的内容，并且把它封装到JavaBean中，以供随时使用；**

```java
public class getProperties {
     public static void main(String[] args) throws FileNotFoundException， IOException {
         Properties pps = new Properties();
         pps.load(new FileInputStream("a.properties"));
         Enumeration enum1 = pps.propertyNames();//得到配置文件的名字
         while(enum1.hasMoreElements()) {
             String strKey = (String) enum1.nextElement();
             String strValue = pps.getProperty(strKey);
             System.out.println(strKey + "=" + strValue);
             //封装到JavaBean。
         }
     }
 }
```

### **@ConfigurationProperties**

```java
/**
 * 只有在容器中的组件，才会拥有SpringBoot提供的强大功能
 */
@Component
@ConfigurationProperties(prefix = "mycar")
public class Car {

    private String brand;
    private Integer price;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                "， price=" + price +
                '}';
    }
}
```

### @EnableConfigurationProperties + **@ConfigurationProperties**

### **@Component + @ConfigurationProperties**

```java
@EnableConfigurationProperties(Car.class)
//1、开启Car配置绑定功能
//2、把这个Car这个组件自动注册到容器中
public class MyConfig {}
```

## **自动配置原理入门**

**引导加载自动配置类**

```java
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(excludeFilters = { @Filter(type = FilterType.CUSTOM， classes = TypeExcludeFilter.class)，
  @Filter(type = FilterType.CUSTOM， classes = AutoConfigurationExcludeFilter.class) })
public @interface SpringBootApplication{}
```

### **@SpringBootConfiguration**

**@Configuration。代表当前是一个配置类**

### @ComponentScan

**指定扫描哪些，Spring注解；**

### **@EnableAutoConfiguration**

```java
@AutoConfigurationPackage
@Import(AutoConfigurationImportSelector.class)
public @interface EnableAutoConfiguration {}
```

@AutoConfigurationPackage  
自动配置包？指定了默认的包规则

```java
@Import(AutoConfigurationPackages.Registrar.class)  //给容器中导入一个组件
public @interface AutoConfigurationPackage {}

//利用Registrar给容器中导入一系列组件
//将指定的一个包下的所有组件导入进来？MainApplication 所在包下。

```

**@Import(AutoConfigurationImportSelector.class)**

```java
1、利用getAutoConfigurationEntry(annotationMetadata);给容器中批量导入一些组件
2、调用List<String> configurations = getCandidateConfigurations(annotationMetadata， attributes)获取到所有需要导入到容器中的配置类
3、利用工厂加载 Map<String， List<String>> loadSpringFactories(@Nullable ClassLoader classLoader)；得到所有的组件
4、从META-INF/spring.factories位置来加载一个文件。
 默认扫描我们当前系统里面所有META-INF/spring.factories位置的文件
    spring-boot-autoconfigure-2.3.4.RELEASE.jar包里面也有META-INF/spring.factories
  
```

```java
文件里面写死了spring-boot一启动就要给容器中加载的所有配置类
spring-boot-autoconfigure-2.3.4.RELEASE.jar/META-INF/spring.factories
# Auto Configure
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration，\
org.springframework.boot.autoconfigure.aop.AopAutoConfiguration，\
org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration，\
org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration，\
org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration，\
org.springframework.boot.autoconfigure.cassandra.CassandraAutoConfiguration，\
org.springframework.boot.autoconfigure.context.ConfigurationPropertiesAutoConfiguration，\
org.springframework.boot.autoconfigure.context.LifecycleAutoConfiguration，\
org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration，\
org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration，\
org.springframework.boot.autoconfigure.couchbase.CouchbaseAutoConfiguration，\
org.springframework.boot.autoconfigure.dao.PersistenceExceptionTranslationAutoConfiguration，\
org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration，\
org.springframework.boot.autoconfigure.data.cassandra.CassandraReactiveDataAutoConfiguration，\
org.springframework.boot.autoconfigure.data.cassandra.CassandraReactiveRepositoriesAutoConfiguration，\
org.springframework.boot.autoconfigure.data.cassandra.CassandraRepositoriesAutoConfiguration，\
org.springframework.boot.autoconfigure.data.couchbase.CouchbaseDataAutoConfiguration，\
org.springframework.boot.autoconfigure.data.couchbase.CouchbaseReactiveDataAutoConfiguration，\
org.springframework.boot.autoconfigure.data.couchbase.CouchbaseReactiveRepositoriesAutoConfiguration，\
org.springframework.boot.autoconfigure.data.couchbase.CouchbaseRepositoriesAutoConfiguration，\
org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration，\
org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchRepositoriesAutoConfiguration，\
org.springframework.boot.autoconfigure.data.elasticsearch.ReactiveElasticsearchRepositoriesAutoConfiguration，\
org.springframework.boot.autoconfigure.data.elasticsearch.ReactiveElasticsearchRestClientAutoConfiguration，\
org.springframework.boot.autoconfigure.data.jdbc.JdbcRepositoriesAutoConfiguration，\
org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration，\
org.springframework.boot.autoconfigure.data.ldap.LdapRepositoriesAutoConfiguration，\
org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration，\
org.springframework.boot.autoconfigure.data.mongo.MongoReactiveDataAutoConfiguration，\
org.springframework.boot.autoconfigure.data.mongo.MongoReactiveRepositoriesAutoConfiguration，\
org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration，\
org.springframework.boot.autoconfigure.data.neo4j.Neo4jDataAutoConfiguration，\
org.springframework.boot.autoconfigure.data.neo4j.Neo4jRepositoriesAutoConfiguration，\
org.springframework.boot.autoconfigure.data.solr.SolrRepositoriesAutoConfiguration，\
org.springframework.boot.autoconfigure.data.r2dbc.R2dbcDataAutoConfiguration，\
org.springframework.boot.autoconfigure.data.r2dbc.R2dbcRepositoriesAutoConfiguration，\
org.springframework.boot.autoconfigure.data.r2dbc.R2dbcTransactionManagerAutoConfiguration，\
org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration，\
org.springframework.boot.autoconfigure.data.redis.RedisReactiveAutoConfiguration，\
org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration，\
org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration，\
org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration，\
org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchRestClientAutoConfiguration，\
org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration，\
org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration，\
org.springframework.boot.autoconfigure.groovy.template.GroovyTemplateAutoConfiguration，\
org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration，\
org.springframework.boot.autoconfigure.h2.H2ConsoleAutoConfiguration，\
org.springframework.boot.autoconfigure.hateoas.HypermediaAutoConfiguration，\
org.springframework.boot.autoconfigure.hazelcast.HazelcastAutoConfiguration，\
org.springframework.boot.autoconfigure.hazelcast.HazelcastJpaDependencyAutoConfiguration，\
org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration，\
org.springframework.boot.autoconfigure.http.codec.CodecsAutoConfiguration，\
org.springframework.boot.autoconfigure.influx.InfluxDbAutoConfiguration，\
org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration，\
org.springframework.boot.autoconfigure.integration.IntegrationAutoConfiguration，\
org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration，\
org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration，\
org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration，\
org.springframework.boot.autoconfigure.jdbc.JndiDataSourceAutoConfiguration，\
org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration，\
org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration，\
org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration，\
org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration，\
org.springframework.boot.autoconfigure.jms.JndiConnectionFactoryAutoConfiguration，\
org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration，\
org.springframework.boot.autoconfigure.jms.artemis.ArtemisAutoConfiguration，\
org.springframework.boot.autoconfigure.jersey.JerseyAutoConfiguration，\
org.springframework.boot.autoconfigure.jooq.JooqAutoConfiguration，\
org.springframework.boot.autoconfigure.jsonb.JsonbAutoConfiguration，\
org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration，\
org.springframework.boot.autoconfigure.availability.ApplicationAvailabilityAutoConfiguration，\
org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapAutoConfiguration，\
org.springframework.boot.autoconfigure.ldap.LdapAutoConfiguration，\
org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration，\
org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration，\
org.springframework.boot.autoconfigure.mail.MailSenderValidatorAutoConfiguration，\
org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration，\
org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration，\
org.springframework.boot.autoconfigure.mongo.MongoReactiveAutoConfiguration，\
org.springframework.boot.autoconfigure.mustache.MustacheAutoConfiguration，\
org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration，\
org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration，\
org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration，\
org.springframework.boot.autoconfigure.rsocket.RSocketMessagingAutoConfiguration，\
org.springframework.boot.autoconfigure.rsocket.RSocketRequesterAutoConfiguration，\
org.springframework.boot.autoconfigure.rsocket.RSocketServerAutoConfiguration，\
org.springframework.boot.autoconfigure.rsocket.RSocketStrategiesAutoConfiguration，\
org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration，\
org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration，\
org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration，\
org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration，\
org.springframework.boot.autoconfigure.security.reactive.ReactiveUserDetailsServiceAutoConfiguration，\
org.springframework.boot.autoconfigure.security.rsocket.RSocketSecurityAutoConfiguration，\
org.springframework.boot.autoconfigure.security.saml2.Saml2RelyingPartyAutoConfiguration，\
org.springframework.boot.autoconfigure.sendgrid.SendGridAutoConfiguration，\
org.springframework.boot.autoconfigure.session.SessionAutoConfiguration，\
org.springframework.boot.autoconfigure.security.oauth2.client.servlet.OAuth2ClientAutoConfiguration，\
org.springframework.boot.autoconfigure.security.oauth2.client.reactive.ReactiveOAuth2ClientAutoConfiguration，\
org.springframework.boot.autoconfigure.security.oauth2.resource.servlet.OAuth2ResourceServerAutoConfiguration，\
org.springframework.boot.autoconfigure.security.oauth2.resource.reactive.ReactiveOAuth2ResourceServerAutoConfiguration，\
org.springframework.boot.autoconfigure.solr.SolrAutoConfiguration，\
org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration，\
org.springframework.boot.autoconfigure.task.TaskSchedulingAutoConfiguration，\
org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration，\
org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration，\
org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration，\
org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration，\
org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration，\
org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration，\
org.springframework.boot.autoconfigure.web.reactive.HttpHandlerAutoConfiguration，\
org.springframework.boot.autoconfigure.web.reactive.ReactiveWebServerFactoryAutoConfiguration，\
org.springframework.boot.autoconfigure.web.reactive.WebFluxAutoConfiguration，\
org.springframework.boot.autoconfigure.web.reactive.error.ErrorWebFluxAutoConfiguration，\
org.springframework.boot.autoconfigure.web.reactive.function.client.ClientHttpConnectorAutoConfiguration，\
org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration，\
org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration，\
org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration，\
org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration，\
org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration，\
org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration，\
org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration，\
org.springframework.boot.autoconfigure.websocket.reactive.WebSocketReactiveAutoConfiguration，\
org.springframework.boot.autoconfigure.websocket.servlet.WebSocketServletAutoConfiguration，\
org.springframework.boot.autoconfigure.websocket.servlet.WebSocketMessagingAutoConfiguration，\
org.springframework.boot.autoconfigure.webservices.WebServicesAutoConfiguration，\
org.springframework.boot.autoconfigure.webservices.client.WebServiceTemplateAutoConfiguration

```

**按需开启自动配置项**

```java
虽然我们127个场景的所有自动配置启动的时候默认全部加载。xxxxAutoConfiguration
按照条件装配规则（@Conditional），最终会按需配置。

```

**修改默认配置**

```java
        @Bean
  @ConditionalOnBean(MultipartResolver.class)  //容器中有这个类型组件
  @ConditionalOnMissingBean(name = DispatcherServlet.MULTIPART_RESOLVER_BEAN_NAME) //容器中没有这个名字 multipartResolver 的组件
  public MultipartResolver multipartResolver(MultipartResolver resolver) {
            //给@Bean标注的方法传入了对象参数，这个参数的值就会从容器中找。
            //SpringMVC multipartResolver。防止有些用户配置的文件上传解析器不符合规范
   // Detect if the user has created a MultipartResolver but named it incorrectly
   return resolver;
  }
给容器中加入了文件上传解析器；

```

**SpringBoot默认会在底层配好所有的组件。但是如果用户自己配置了以用户的优先**

```java
    @Bean
    @ConditionalOnMissingBean
    public CharacterEncodingFilter characterEncodingFilter() {}
```

‍
