# Java序列化与反序列化

- [java 序列化和反序列化](https://www.cnblogs.com/xbwang520/p/11524955.html)
- [【MySQL】如何判断索引是否生效？索引失效的情况?为什么Mysql用B+树做索引而不用B-树或红黑树_是菜鸟不是咸鱼的博客-CSDN博客_如何判断mysql中的索引有没有生效](https://blog.csdn.net/weixin_40335368/article/details/116593321)

什么是序列化和反序列化：序列化是将 Java 对象转换成与平台无关的二进制流，而反序列化则是将二进制流恢复成原来的 Java 对象，二进制流便于保存到磁盘上或者在网络上传输。

Java 序列化是为了保存各种对象在内存中的状态，并且可以把保存的对象状态再读出来。

以下情况需要使用 Java 序列化：

- 想把的内存中的对象状态保存到一个文件中或者数据库中时候；
- 想用套接字在网络上传送对象的时候；
- 想通过RMI（远程方法调用）传输对象的时候。
‍

```java
public class Person implements Serializable {
    private String name;

    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Person() {
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
```

```java
// 序列化
ObjectOutputStream output_stream = new ObjectOutputStream(
        new FileOutputStream("D:\\.github\\.dome（开发示例）\\spring\\ser.txt"));
Person person = new Person("liuzonglin", 25);
output_stream.writeObject(person);
output_stream.close();

// 反序列化
ObjectInputStream input_stream = new ObjectInputStream(
        new FileInputStream("D:\\.github\\.dome（开发示例）\\spring\\ser.txt"));
Person o = (Person)input_stream.readObject();
System.out.println(o);
input_stream.close();
```

## Serializable接口为什么需要定义serialVersionUID常量

serialVersionUID常量用于标明当前Serializable类的版本，以验证加载的类和序列化对象是否兼容。

在进行序列化时会将当前类的serialVersionUID写入到字节序列中，在反序列化时会将当前字节流中的serialVersionUID同本地对象中的serialVersionUID进行对比，如果相同则继续序列化，如果不同则失败报错。

serialVersionUID常量值默认为1L。

序列化运行时使用一个称为 serialVersionUID 的版本号与每个可序列化类相关联，该序列号在反序列化过程中<u>用于验证序列化对象的发送者和接收者是否为该对象加载了与序列化兼容的类</u>。如果接收者加载的该对象的类的 serialVersionUID 与对应的发送者的类的版本号不同，则反序列化将会导致 InvalidClassException
