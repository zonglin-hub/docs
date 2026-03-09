# MyBatis 如何编写一个自定义插件？

「自定义插件实现原理」

MyBatis 自定义插件针对 MyBatis 四大对象（Executor、StatementHandler、ParameterHandler、ResultSetHandler）进行拦截：

Executor：拦截内部执行器，它负责调用 StatementHandler 操作数据库，并把结果集通过 ResultSetHandler 进行自动映射，另外它还处理了二级缓存的操作；  
StatementHandler：拦截 SQL 语法构建的处理，它是 MyBatis 直接和数据库执行 SQL 脚本的对象，另外它也实现了 MyBatis 的一级缓存；  
ParameterHandler：拦截参数的处理；  
ResultSetHandler：拦截结果集的处理。  

「自定义插件实现关键」

MyBatis 插件要实现 Interceptor 接口，接口包含的方法，如下：

```java
public interfaceInterceptor{   
   Object intercept(Invocation invocation)throws Throwable;     
   Object plugin(Object target);  
   voidsetProperties(Properties properties);
}
```
  
setProperties 方法是在 MyBatis 进行配置插件的时候可以配置自定义相关属性，即：接口实现对象的参数配置；  
plugin 方法是插件用于封装目标对象的，通过该方法我们可以返回目标对象本身，也可以返回一个它的代理，可以决定是否要进行拦截进而决定要返回一个什么样的目标对象，官方提供了示例：return Plugin. wrap(target, this)；  
intercept 方法就是要进行拦截的时候要执行的方法。  
「自定义插件实现示例」

官方插件实现：

```java
@Intercepts({@Signature(type = Executor. class, method= "query",
        args = {MappedStatement. class, Object. class, RowBounds. class, ResultHandler. class})})
publicclassTestInterceptorimplementsInterceptor{
   public Object intercept(Invocation invocation)throws Throwable {
     Object target = invocation. getTarget(); //被代理对象
     Method method = invocation. getMethod(); //代理方法
     Object[] args = invocation. getArgs(); //方法参数
     // do something . . . . . .  方法拦截前执行代码块
     Object result = invocation. proceed();
     // do something . . . . . . . 方法拦截后执行代码块
     return result;
   }
   public Object plugin(Object target){
     return Plugin. wrap(target, this);
   }
}
```
