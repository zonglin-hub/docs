# 单例设计模式

什么是单例：永远只能够创建一个对象

单例设计模式书写方式总共是有两种：饿汉式、懒汉式

书写单例设计模式的语法规则：

- 类中的构造方法私有
- 需要提供一个公共的获取对象的方法

饿汉式单例设计模式：

```java
// 饿汉式单例设计模式
public class Dog {
    private static final Dog dog = new Dog();

    private Dog() { System.out.println("Dog对象被创建了"); }

    public static Dog getInstance() { return dog; } // 获取实例
}
```

懒汉式单例设计模式：

```java
// 懒汉式单例设计模式 -> 会出现线程安全问题
public class Cat {      
    private static Cat cat = null;
    
    private Cat() { System.out.println("cat对象被创建了"); }
    
    public static Cat getInstance() {
        if (cat == null) { cat = new Cat(); }
        return cat;
    }
}
```
