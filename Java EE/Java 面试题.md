# Java 基础

## Java 语言的三种技术架构

JavaEE：企业版、jsp、servlet等。

JavaME：小型版本，是为开发电子消费产品和嵌入式设备提供的解决方案。手机

JavaSE：Java标准版，集合、多线程、面向对象啊等。

## 谈一谈面向对象思想

面向对象是相对于面向过程而言的，面向过程强调的是功能，面向对象强调的是将功能进行封装。

面向对象的 3 大基本特征：封装(private)、继承(extends)、多态

## 面向对象的三个基本特征？

继承： 继承就是子类继承父类的属性和方法。

封装：隐藏部分对象的属性和实现细节，对数据的访问只能通过外公开的接口。

多态：对于同一个行为，不同的子类对象具有不同的表现形式。

多态存在的3个条件：

- 继承；

- 重写；
- 父类引用指向子类对象。

```java
父类类型 对象 = new 子类对象(); // 改变子类对象
对象.get\set();
```

## 说说jvm、jre、jdk的区别？

Jvm： java 虚拟机，用于保证 java 的跨平台特性

Jre： java 的运行环境，包含 jvm+lib(jar包)

Jdk：java 的开发工具，包含 jre+开发工具

## 什么是编译时异常？什么是运行时异常？

```mermaid
graph LR;
a[.java 源文件] -- javac编译源文件成字节码文件时出现异常 --> b[.class 字节码文件] -- jvm --> c[执行]

```

## 为什么Java代码可以实现一次编写、到处运行？

1. JVM（Java虚拟机）是跨平台的关键
2. Java 源码（.java) -> 编译成字节码文件（.class） -> JVM 把特定的字节码文件翻译成特定平台下的机器嘛并运行

## Java虚拟机中有哪些类加载器？

启动类加载器（Bootstrap ClassLoader）：

这个类加载器负责将存放在<JAVA_HOME>\lib目录中的，或者被-Xbootclasspath参数所指定的路径中的，并且是虚拟机识别的（仅按照文件名识别，如rt.jar，名字不符合的类库即使放在lib目录中也不会被加载）类库加载到虚拟机内存中。

扩展类加载器（Extension ClassLoader）：

这个加载器由sun.misc.Launcher$ExtClassLoader实现，它负责加载<JAVA_HOME>\lib\ext目录中的，或者被java.ext.dirs系统变量所指定的路径中的所有类库，开发者可以直接使用扩展类加载器。

应用程序类加载器（Application ClassLoader）：

这个类加载器由sun.misc.Launcher$AppClassLoader实现。由于这个类加载器是ClassLoader中的getSystemClassLoader()方法的返回值，所以一般也称它为系统类加载器。它负责加载用户类路径（ClassPath）上所指定的类库，开发者可以直接使用这个类加载器，如果应用程序中没有自定义过自己的类加载器，一般情况下这个就是程序中默认的类加载器。

自定义类加载器：

用户自定义的类加载器。

## 一个Java文件里可以有多个类吗（不含内部类）？

1. 一个java文件内可以有多个类，但最多只有一个被 public 修饰的类
2. 这个文件包含public 修饰的类，这个类名必须与java文件名一致

## == 和 equals 的区别是什么？

==：运算符，用于比较基础类型变量和引用类型变量。栈中的值

equals 比较：

```java
    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof String) {
            String anotherString = (String)anObject;
            int n = value.length;
            if (n == anotherString.value.length) {
                char v1[] = value;
                char v2[] = anotherString.value;
                int i = 0;
                while (n-- != 0) {
                    if (v1[i] != v2[i])
                        return false;
                    i++;
                }
                return true;
            }
        }
        return false;
    }
```

```java
    public static void main(String[] args) {
        // 常量池中分配内存
        String str1 = "hello";
        // 堆中分配内存
        String str2 = new String("hello");
        // 引用地址
        String str3 = str2;
        System.out.println(str1 == str2); // false
        System.out.println(str1 == str3); // false
        System.out.println(str2 == str3); // true
        System.out.println(str1.equals(str2)); // true
        System.out.println(str1.equals(str3)); // true
        System.out.println(str2.equals(str3)); // true
    }
```

对于基础类型变量，比较的变量保存的值是否相同，类型不一定要相同。

```java
    public static void main(String[] args) {
        short s1 = 1;
        long l1 = 1;
        System.out.println(s1 == l1);        // 结果：true。类型不同，但是值相同
    }
```

对于引用类型变量，比较的是两个对象的地址是否相同。

```java
    public static void main(String[] args) {
        Integer i1 = new Integer(1);
        Integer i2 = new Integer(1);
        System.out.println(i1 == i2);        // 结果：false。通过new创建，在内存中指向两个不同的对象
        System.out.println(i1.equals(i2));  // 结果：true。两个不同的对象，但是具有相同的值
    }
```

equals：Object 类中定义的方法，通常用于比较**两个对象的值是否相等**。

equals 在 Object 方法中其实等同于 ==，但是在实际的使用中，equals 通常被重写用于比较两个对象的值是否相同。

```java
// Integer的equals重写方法
public boolean equals(Object obj) {
    if (obj instanceof Integer) {
        // 比较对象中保存的值是否相同
        return value == ((Integer)obj).intValue();
    }
    return false;
}
```

## 两个对象的 hashCode() 相同，则 equals() 也一定为 true，对吗？

不对。hashCode() 和 equals() 之间的关系如下：

当有 a.equals(b) == true 时，则 a.hashCode() == b.hashCode() 必然成立，

反过来，当 a.hashCode() == b.hashCode() 时，a.equals(b) 不一定为 true。

## int和Integer有什么区别？

Java是一个近乎纯洁的面向对象编程语言，但是为了编程的方便还是引入了基本数据类型，但是为了能够将这些基本数据类型当成对象操作，Java为每一个基本数据类型都引入了对应的包装类型（wrapper class），int的包装类就是Integer，从Java 5开始引入了自动装箱/拆箱机制，使得二者可以相互转换。

Java 为每个原始类型提供了包装类型：

- 原始类型: boolean，char，byte，short，int，long，float，double
- 包装类型：Boolean，Character，Byte，Short，Integer，Long，Float，Double

如：

```java
public static void main(String[] args) {
    Integer a = new Integer(3);
    Integer b = 3;                  // 将3自动装箱成Integer类型
    int c = 3;
    System.out.println(a == b);     // false 两个引用没有引用同一对象
    System.out.println(a == c);     // true a自动拆箱成int类型再和c比较
}
```

## Set里的元素是不能重复的，那么用什么方法来区分重复与否呢？是用==还是equals()?它们有何区别

set元素是不能重复的，可以用`equals()`来判断！

第二个问题：主要看你对`==`和`equals`的了解。

## 介绍一下Java的数据类型

引用数据类型、基本数据类型8个

- 整数类型 byte, short, int, long
- 浮点类型 float double
- 字符类型 char
- 布尔类型 boolean

## int类型的数据范围是多少？

int类型占4个字节（32位）数据范围 -2^31 ~ 2^31-1

引用数据类型

## String 是 Java 基本数据类型吗？

答：不是。`Java` 中的基本数据类型只有8个：

```mermaid
graph TD;
b[基本数据类型] --> c[数值型] -->e[整数类型: byte, short, int, long]
c[数值型] -->f[浮点数: float, double]
b[基本数据类型] --> a[字符型: char]
b[基本数据类型] --> d[布尔型: boolean]
```

基本数据类型：数据直接存储在栈上

引用数据类型：数据存储在堆上，栈上只存储引用地址

## String 类可以继承吗？

不行。`String` 类使用 `final` 修饰，无法被继承。

## String s = new String("xyz"); 创建了几个字符串对象？

如果**字符串常量池**已经有“xyz”，则是一个；否则会创建两个字符串对象。

当字符创常量池没有 “xyz”，此时会创建如下两个对象：

一个是字符串字面量 "xyz" 所对应的、驻留（intern）在一个全局共享的字符串常量池中的实例，此时该实例也是在堆中，字符串常量池

只放引用。

另一个是通过 new String() 创建并初始化的，内容与"xyz"相同的实例，也是在堆中。

## String s = "xyz"` 和 `String s = new String("xyz") 区别？

两个语句都会先去字符串常量池中检查是否已经存在 “xyz”，如果有则直接使用，如果没有则会在常量池中创建 “xyz” 对象。

另外，String s = new String("xyz") 还会通过 new String() 在堆里创建一个内容与 "xyz" 相同的对象实例。

所以前者其实理解为被后者的所包含。

## abstract class 和 interface 有什么区别？

abstract class 和 interface 都不能实例化。

abstract class 拥有自己的成员变量，interface 默认 static final 修饰

abstract class 方法可以私有化，非抽象方法，必须实现；interface 不能有私有的，默认是 public abstract

实现方式不同：abstract class 需要 extends，interface 要用 implement

## abstract 的 method 是否可同时是static？是否可同时是native？是否可同时是synchronized？

abstract 的方法可以同时是 static ！

abstract 的方法不能同时是native的！

native本身就和abstract冲突，他们都是方法的声明，只是一个把方法实现移交给子类，另一个是移交给本地操作系统。如果同时出现，就相当于既把实现移交给子类，又把实现移交给本地操作系统，那到底谁来实现具体方法呢！

abstract 的 方法可同时是 synchronized

## 接口是否可继承接口？抽象类是否可实现(implements)接口?抽象类是否可继承实体类(concrete class)

**接口：** 接口是一种约束形式，其中只包括成员定义，不包含成员的实现内容。

接口可以继承（extends）接口。

抽象类可以实现（implements）接口。

抽象类可继承（extends）实体类，(但前提是实体类必须有明确的构造函数)。

## 普通类、抽象类、接口之间相关面试题

普通类：没有抽象的方法

抽象类：抽象类中可以有抽象方法，也可以一个抽象方法都没有

接口：

1. 接口中所有的方法都为抽象方法(`public abstrac`t修饰)
2. 接口中所有的变量必须被`public static`

final 修饰

1. 普通类是否可以继承抽象类：可以
2. 普通类是否可以实现接口：可以
3. 抽象类是否可以继承普通类：可以
4. 抽象类是否可以继承抽象类：可以
5. 抽象类是否可以继承接口：可以
6. 接口是否可以继承普通类：不可以
7. 接口是否可以继承抽象类：不可以
8. 接口是否可以继承接口：可以

总结：接口不可以继承/实现任何类

## 抽象类（abstract class）和接口（interface）有什么区别？

抽象类只能单继承，接口可以多实现。

抽象类可以有构造方法，接口中不能有构造方法。

抽象类中可以有成员变量，接口中没有成员变量，只能有常量（默认就是 public static final）

抽象类中可以包含非抽象的方法，在 Java 7 之前接口中的所有方法都是抽象的，在 Java 8 之后，接口支持非抽象方法：default 方法、

静态方法等。Java 9 支持私有方法、私有静态方法。

抽象类中的抽象方法类型可以是任意修饰符，Java 8 之前接口中的方法只能是 public 类型，Java 9 支持 private 类型。

设计思想的区别：

接口是自上而下的抽象过程，接口规范了某些行为，是对某一行为的抽象。我需要这个行为，我就去实现某个接口，但是具体这个行为怎么实现，完全由自己决定。

抽象类是自下而上的抽象过程，抽象类提供了通用实现，是对某一类事物的抽象。我们在写实现类的时候，发现某些实现类具有几乎相同的实现，因此我们将这些相同的实现抽取出来成为抽象类，然后如果有一些差异点，则可以提供抽象方法来支持自定义实现。

## 说一说你对Java访问权限的了解

修饰变量、方法、类

1. private 可以被该类的内部成员访问
2. protected 可以被该类的内部成员访问，可以被同一个包下其他类访问，可以被它的子类访问
3. public 任意成员、包访问
4. default 可以被同一个包下其他类访问

## Java 中的 final 关键字有哪些用法？

修饰类：该类不能再派生出新的子类，不能作为父类被继承。因此，一个类不能同时被声明为abstract 和 final。

修饰方法：该方法不能被子类重写。

修饰变量：该变量必须在声明时给定初值，而在以后只能读取，不可修改。 如果变量是对象，则指的是引用不可修改，但是对象的属性

还是可以修改的。

## 阐述 final、finally、finalize(了解) 的区别

finally：finally 是对 Java 异常处理机制的最佳补充，通常配合 try、catch 使用，用于存放那些无论是否出现异常都一定会执行的代码。在

实际使用中，==通常用于释放锁、数据库连接等资源，把资源释放方法放到 finally 中，可以大大降低程序出错的几率==。

finalize：Object 中的方法，在垃圾收集器将对象从内存中清除出去之前做必要的清理工作。finalize()方法仅作为了解即可，在 Java 9 中

该方法已经被标记为废弃，并添加新的 java.lang.ref.Cleaner，提供了更灵活和有效的方法来释放资源。这也侧面说明了，这个方法的设

计是失败的，因此更加不能去使用它。

## try、catch、finally 考察，请指出下面程序的运行结果

```java
public class TryDemo {
    public static void main(String[] args) {
        System.out.println(test());  // 31
    }
    public static int test() {
        try {
            return 1;
        } catch (Exception e) {
            return 2;
        } finally {
            System.out.print("3");
        }
    }
}
```

try、catch。finally 的基础用法，在 return 前会先执行 finally 语句块，所以是先输出 finally 里的 3，再输出 return 的 1。

```java
public class TryDemo {
    public static void main(String[] args) {
        System.out.println(test1());  // 3
    }
    public static int test1() {
        try {
            return 2;
        } finally {
            return 3;
        }
    }
}
```

try 返回前先执行 finally，结果 finally 里不按套路出牌，直接 return 了，自然也就走不到 try 里面的 return 了。

finally 里面使用 return 仅存在于面试题中，实际开发中千万不要这么用。

```java
public class TryDemo {
    public static void main(String[] args) {
        System.out.println(test1());  // 2
    }
    public static int test1() {
        int i = 0;
        try {
            i = 2;
            return i;
        } finally {
            i = 3;
        }
    }
}
```

在执行 finally 之前，JVM 会先将 i 的结果暂存起来，然后 finally 执行完毕后，会返回之前暂存的结果，而不是返回 i，所以即使这边 i 已经被修改为 3，最终返回的还是之前暂存起来的结果 2。

这边其实根据字节码可以很容易看出来，在进入 finally 之前，JVM 会使用 iload、istore 两个指令，将结果暂存，在最终返回时在通过 iload、ireturn 指令返回暂存的结果。

## 介绍下 HashMap 的底层数据结构

我们现在用的都是 JDK 1.8，底层是由“数组+链表+红黑树”组成，而在 JDK 1.8 之前是由“数组+链表”组成。

## 为什么使用“数组+链表”？

使用 “数组+链表” 是为了解决 hash 冲突的问题。cc

数组和链表有如下特点：

数组：查找容易，通过 index 快速定位；插入和删除困难，插入某一个节点，这个节点的数据都会向后移动；删除也是，删除这个节点，数组整个会向前移动。

链表：查找困难，需要从头结点或尾节点开始遍历，直到寻找到目标节点；插入和删除容易，只需修改目标节点前后节点的 next 或 prev 属性即可；

HashMap 巧妙的将数组和链表结合在一起，发挥两者各自的优势，使用一种叫做 “拉链法” 的方式来解决哈希冲突。

首先通过 index 快速定位到索引位置，这边利用了数组的优点；然后遍历链表找到节点，进行节点的新增/修改/删除操作，这边利用了链表的优点。

## 为什么要改成“数组+链表+红黑树”？

主要是为了提升在 hash 冲突严重时（链表过长）的查找性能，使用链表的查找性能是 O(n)，而使用红黑树是 O(logn)。

## 那在什么时候用链表？什么时候用红黑树？

对于插入，默认情况下是使用链表节点。当同一个索引位置的节点在新增后超过8个（阈值8）：如果此时数组长度大于等于 64，则会触

发链表节点转红黑树节点（treeifyBin）；而如果数组长度小于64，则不会触发链表转红黑树，而是会进行扩容，因为此时的数据量还比

较小。

对于移除，当同一个索引位置的节点在移除后达到 6 个，并且该索引位置的节点为红黑树节点，会触发红黑树节点转链表节点（untreeify）。

## 为什么链表转红黑树的阈值是10？

我们平时在进行方案设计时，必须考虑的两个很重要的因素是：==时间和空间==。对于 HashMap 也是同样的道理，简单来说，阈值为8是在时间和空间上权衡的结果。已2倍的空间换效率，没有达到相应的效果。

红黑树节点大小约为链表节点的1.5倍，在节点太少时，红黑树的查找性能优势并不明显，付出2倍空间的代价作者觉得不值得。

理想情况下，使用随机的哈希码，节点分布在 hash 桶中的频率遵循泊松分布，按照泊松分布的公式计算，链表中节点个数为8时的概率为 0.00000006（就我们这QPS不到10的系统，根本不可能遇到嘛），这个概率足够低了，并且到8个节点时，红黑树的性能优势也会开始展现出来，因此8是一个较合理的数字。

## 那为什么转回链表节点是用的6而不是复用8？

如果我们设置节点多于8个转红黑树，少于8个就马上转链表，当节点个数在8徘徊时，就会频繁进行红黑树和链表的转换，造成性能的损耗。

## HashMap 的默认初始容量是多少？HashMap 的容量有什么限制吗？

默认初始容量是16。HashMap 的容量必须是2的N次方，HashMap 会根据我们传入的节点，进行容量计算一个大于等于该容量的最小的2的N次方，例如传 16，容量为16；传17，容量为32。

## HashMap 和Hashtable 的区别?

HashMap 允许 key 和 value 为 null，Hashtable 不允许。

HashMap 的默认初始容量为 16，Hashtable 为 11。

HashMap 的扩容为原来的 2 倍，Hashtable 的扩容为原来的 2 倍加 1。

HashMap 是非线程安全的，Hashtable是线程安全的。

## switch都可以支持哪些数据类型

JDK1.5： 在switch循环中支持==枚举类与byte short char int的包装类==,对四个包装类的支持是因为java编译器在底层手动进行拆箱,而对枚举类的支持是因为枚举类有一个ordinal方法,该方法实际上是一个int类型的数值。

jdk1.7： 开始支持String类型,但实际上String类型有一个hashCode算法,结果也是int类型.而byte short char类型可以在不损失精度的情况下向上转型成int类型.所以总的来说,可以认为switch中只支持int。

## switch中可以有null吗？

在switch语句中，表达式的值不能是null，否则会在运行时抛出NullPointerException。在case子句中也不能使用null，否则会出现编译错误。

## switch支持的类型有？

Java 7 中加入了对String类型的支持。所以支持的有：char、byte、short、int 和 Character、Byte、Short、Integer 和 String

## case字句可以重复吗？

case字句的值是不能重复的。对于字符串类型的也一样，但是字符串中可以包含Unicode转义字符。重复值的检查是在Java编译器对Java

源代码进行相关的词法转换之后才进行的。也就是说，有些case字句的值虽然在源代码中看起来是不同的，但是经词法转换之后是一样

的，就会在成编译错误。比如：“男”和“\u7537”就是一个意思。

## swtich是否能作用在byte上，是否能作用在long上，是否能作用在String上

switch和case语句的参数是int、short、char或者byte。

注意，对于精度比int大的类型，比如long、float、double，不会自动转换为int，如果想使用，就必须强转为int，如(int)float。

jdk1.7后，整型，枚举类型，boolean，字符串都可以。jdk1.7并没有新的指令俩处理switch String，而是通过调用switch中的String.hashCode，将String转为int从而进行判断。

## 给我一个你最常见到的RuntimeException

这种题就需要背了，没啥可理解的。常写代码谁能记住，我反正是记不住。就跟你手写代码一样，不是一般人那。

空指针异常 NullPointerException
数组越界异常 IndexOutOfBoundsException
类转换异常 ClassCaseException
向数组中存放与声明类型不兼容对象异常 ArrayStoreException
Io操作异常 BufferOverFlowException

## 编译时异常需要添加方法签名 throws 抛出异常运行时不需要

## error 和 exception 有什么区别

Exception 和 Error 都是继承了 Throwable 类，在 Java 中只有 Throwable 类型得实例才可以被抛出 throw 捕获 try-catch 。

Exception 是程序中正常运行中，可以预料的异常情况，可以被捕获，进行处理的异常。

Error 程序导致程序处于非正常的不可恢复状态。不便于捕获。

## List, Set,Map是否继承自 Collection 接口？

List 与 Set 继承 Collection 接口；Map 没有继承自 Collection 接口；

## try{} 里有一个return语句，那么紧跟在这个try后的finally{}里的code会不会被执行，什么时候被执行，在 return前还是后

Try{}里有一个return语句，那么紧跟在这个try后面的finally{}里的code会执行的。

finally 语句总会执行，除非遇到一些特殊情况，如System.exit(0)

return语句并不一定就是结束一段程序，当它和finally一起使用但finally语句中无return时会先等finally语句执行完成后再返回值。

当finally语句中有return语句时会直接返回finally中return的语句

## 用最有效率的方法算出2乘以8等於几

2 <<3

```java
System.out.println(2 << 3);  //16 2*2^3
System.out.println(1 << 2);  //4  1*2^2
```

---

## 两个对象值相同(x.equals(y)== true)，但却可有不同的hash code，这句话对不对

不对，如果两个对象x 和 y 满足 x.equals(y) == true，它们的哈希码（hashCode）应当相同。

## 当一个对象被当作参数传递到一个方法后，此方法可改变这个对象的属性，并可返回变化后的结果，那么这里到底是值传递还是引用传递

我先不思考题，首先 java 就属于值传递

## 重载（Overload）和重写（Override）的区别？

方法的重载和重写都是==实现多态==的方式，区别在于前者实现的是**编译时的多态性**，而后者实现的是**运行时的多态性** 。

重载：一个类中有多个同名的方法，但是具有有不同的参数列表（参数类型不同、参数个数不同或者二者都不同）。

重写：发生在子类与父类之间，子类对父类的方法进行重写，参数都不能改变，返回值类型可以不相同，但是必须是父类返回值的派生

类。

## Overload 和 Override 的区别；Overloaded 的方法是否可以改变返回值的类型

Overload 是重载，Override 是重写。很基础的知识点必会。

Overloaded 的方法的参数列表不一样，它们的返回类型`不一定`不一样。

如果两个方法的参数列表完全一样，是否可以让它们的返回值不同来实现重载Overload？ 这是不行的。

---

## 讲讲重载

- 需要方法返回值一个数时，添加返回值类型，不需要写void
- 方法名必须相同
- 返回值可以是一个或多个
- 跟返回值类型无关
- 重载方法中返回值参数类型不同也可以

## 为什么不能根据返回类型来区分重载？

如果我们有两个方法如下，当我们调用：test(1) 时，编译器无法确认要调用的是哪个。

```java
// 方法1
int test(int a);
// 方法2
long test(int a);
```

方法的返回值只是作为方法运行之后的一个“状态”，但是并不是所有调用都关注返回值，所以不能将返回值作为重载的唯一区分条件。

## 构造器 Constructor 是否可被重写(Override)

构造器Constructor不能被继承，因此不能被重写(Override)，在本类当中可以被重载（Overload）

如果父类自定义了有参构造函数，则子类无论定义无参，有参构造函数，都会报错。正确的做法是在子类的构造方法中添上super（参数），以表明子类构造之前先构造父类，而这句话必须放在第一句，否则报"Constructor call must be the first statement in a constructor"的错误。

## List、Set、Map三者的区别?

List（对付顺序的好帮手）： List 接口存储一组不唯一（可以有多个元素引用相同的对象）、有序的对象。

Set（注重独一无二的性质）：不允许重复的集合，不会有多个元素引用相同的对象。

Map（用Key来搜索的专业户）: 使用键值对存储。Map 会维护与 Key 有关联的值。两个 Key可以引用相同的对象，但 Key 不能重复，典型的 Key 是String类型，但也可以是任何对象。

## 讲讲 ArrayList 和 LinkedList 区别

- 首先,他们的底层数据结构不同,`ArrayList`底层是基于数组实现的,`LinkedList`底层是基于链表实现的
- 由于底层数据结构不同,他们所适用的场景也不同,`ArrayList`更适合随机查找,`LinkedList`更适合删除和添加,查询,删除的时间复杂度不同
- 另外`ArrayList`和`LinkedList`都实现了`List`接口,但是`LinkedList`还额外实现了`Deque`接口,所有`LinkedList`还可以当做队列来适用

## ArrayList 和 Vector 的区别？

Vector 和 ArrayList 几乎一致，唯一的区别是 Vector 在方法上使用了 synchronized 来保证线程安全，因此在性能上 ArrayList 具有更好的表现。

有类似关系的还有：StringBuilder 和 StringBuffer、HashMap 和 Hashtable。

## java 中的集合包括 ArrayList、LinkedList、HashMap

`ArrayList`和`LinkedList`均实现了List接口。

`ArrayList`的访问速度比`LinkedList`快。

`HashMap`实现Map接口，它允许任何类型的键和值对象，并允许将null用作键或值。

## ArrayList 是不是线程安全的？

**回答：** 不是

怎么实现他的线程安全？

**回答：** 使用`synchronized`在他的add方法上面这种适用于，在单线程下面没有任何的问题。但是在多线程可能发生一个线程的值覆盖掉另外的线程添加的值，所以要实现线程安全就得使用`Collections.synchronizedList()`

这样会不会影响到他的效率？

**回答：** 肯定会受到印象，提高安全的情况下还想提高效率，比如一个人可以做两个人的工作，为了安全只能先做一个人的工作。效率肯定会下降的。

那又要怎么去保证他的效率呢？

**回答：** 如果既要保证线程安全又要保证效率，那就不能使用`Collections.synchronizedList()`，换为写时复制容器，就是在多线程的情况之下，每一个线程进行添加的时候都会复制一个新的容器给这个线程，保证一个容器只有一个线程，这样就保证了他的线程安全，还不影响他的效率，但是内存的占用却是大大的增加了

**主要核心思想：** 空间换时间

## 数据库的三大范式？

**第** **1** 范式(1NF)：字段具有原子性，不可再分。所有关系型数据库系统都满足第一范式。

**第** **2** 范式(2NF)：是建立在第1范式的基础上，要求数据库表中每个实例或行必须可以被唯一的区分。

**第** **3** 范式(3NF)：要求一个数据库表中不包含已在其他表中包含的非主键信息。

---

## 说说union和union all 有什么不同？

**union all**：对重复的数据不会去重

**union**：对重复的数据会去重

---

## mysql 如何分页

通过limit关键字

```mysql
select * from users Limit 0,10;
```

---

## group by …… having的作用？

group by 作用是将某一张表中的某一个字段进行筛选，只保留一个唯一的。

当擦讯语句中有group by的时候，条件只能通过having进行。

---

## 排序与模糊查询

```mysql
-- 排序
-- 降序：desc
-- 升序：asc 默认
select * from goods order by price desc;
select * from goods order by price asc;

-- 模糊查询
# 查询googds表中goodName字段以"xxx"开头
select * from goods where goodName like 'xxx%';
# 查询googds表中goodName字段以"xxx"结尾
select * from goods where goodName like '%xxx';
# 查询googds表中goodName字段中包含"xxx"的所有数据
select * from goods where goodName like '%xxx%';
```

---

## 内连接与外连接的区别？

inner join(内连接)特点：筛选出两张表同时满足条件的数据

left join 与 right join(外连接)

左：以 left join 左边的为主，左边数据需要全部显示

左：以 right join 右边的为主，右边数据需要全部显示

---

## JDBC 中的 PreparedStatement 相比 Statement 的好处

1. PreparedStatement **可以防止** **SQL** **注入，但是** **Statement** **不能够防止** **SQL** **注入**
2. PreparedStatement **可以将** **SQL** **语句记性预编译，因此执行的速度跟快。**

---

## 为什么要用ORM？和JDBC有何不同？

**ORM**：对象关系映射

**常见的** ORM框架：Hibernate 框架、**DBUtil**、Mybatis

**orm** **是一种思想，就是把** **object** **转变成数据库中的记录，或者把数据库中的记录转变成** **object** **对象，我们可以使用** **JDBC** **来实现这种思想，其实，如果我们的项目是严格按照** **java** **中的** **oop** **思想，则已经实现了** **orm** **的思想。**

---

## truncate table 与 delete from table 的区别？

 **truncate   table** 表名  delete from **表名** 都可以删除某一张表中的所有数据记录，不同的是：truncate 删除数据时，会清空主键自增；delete from 删除数据库，自增的记录继续存在。在删除大量数据时，**truncate** 的效率更高

---

## 索引的作用

1、通过创建唯一索引，可以保证数据记录的唯一性

2、可以大大的加快数据检索的速度

3、可以加速表与表之间的连接，这一点在实现数据的参照完整性方面有特别的意义

4、在使用 order by 和 group by 子句中进行检索数据时，可以显著的减少查询中的分组和排序的时间

5、在使用索引可以在检索数据时使用、优化隐藏器，提高系统性能

---

## MySQL 如何备份数据与还原数据

cmd--->mysqldump -u **账号** **-p** **数据库名**>**备份的路径**

在 **mysql** 环境中，source **数据库备份文件**

---

## 什么是事务？

原子性 **(Atomic)**：多个操作不可以被分割

一致 **(consistent)**：操作前后的状态一致 **(**例如转账前后金额一致)

**隔离性**(isolation)：多个事务之间相互隔离

持久性 **(durable)**：数据一旦进入到库中，则需要永久存在，除非刻意删除

---

## 常见的非关系型数据库 :nosql(不仅仅只是 sql)

Redis：键值对的数据库，主要将数据保存到内存中去，因此读取、写入速度是很快，主要用来做缓存。

MongoDB：文档数据库，是一种最像关系型数据库的非关系型数据库。可以将评论保存到 MongoDB 中

---

## synchronized 和 Lock 的区别

1）Lock 是一个接口；synchronized 是 Java 中的关键字，synchronized 是内置的语言实现；

2）Lock 在发生异常时，如果没有主动通过 unLock() 去释放锁，很可能会造成死锁现象，因此使用 Lock 时需要在 finally 块中释放锁；synchronized 不需要手动获取锁和释放锁，在发生异常时，会自动释放锁，因此不会导致死锁现象发生；

3）Lock 的使用更加灵活，可以有响应中断、有超时时间等；而 synchronized 却不行，使用 synchronized 时，等待的线程会一直等待下去，直到获取到锁；

4）在性能上，随着近些年 synchronized 的不断优化，Lock 和 synchronized 在性能上已经没有很明显的差距了，所以性能不应该成为我们选择两者的主要原因。官方推荐尽量使用 synchronized，除非 synchronized 无法满足需求时，则可以使用 Lock。

## synchronized 各种加锁场景的作用范围

1.作用于非静态方法，锁住的是对象实例（this），每一个对象实例有一个锁。

`public synchronized void method() {}`

2.作用于静态方法，锁住的是类的Class对象，因为Class的相关数据存储在永久代元空间，元空间是全局共享的，因此静态方法锁相当于类的一个全局锁，会锁所有调用该方法的线程。

`public static synchronized void method() {}

3.作用于 Lock.class，锁住的是 Lock 的Class对象，也是全局只有一个。

`synchronized (Lock.class) {}

4.作用于 this，锁住的是对象实例，每一个对象实例有一个锁。

`synchronized (this) {}`

5.作用于静态成员变量，锁住的是该静态成员变量对象，由于是静态变量，因此全局只有一个。

`public static Object monitor = new Object(); synchronized (monitor) {}`

## 怎么预防死锁？

预防死锁的方式就是打破四个必要条件中的任意一个即可。

1）打破互斥条件：在系统里取消互斥。若资源不被一个进程独占使用，那么死锁是肯定不会发生的。但一般来说在所列的四个条件

中，“互斥”条件是无法破坏的。因此，在死锁预防里主要是破坏其他几个必要条件，而不去涉及破坏“互斥”条件。。

2）打破请求和保持条件：1）采用资源预先分配策略，即进程运行前申请全部资源，满足则运行，不然就等待。 2）每个进程提出新的资

源申请前，必须先释放它先前所占有的资源。

3）打破不可剥夺条件：当进程占有某些资源后又进一步申请其他资源而无法满足，则该进程必须释放它原来占有的资源。

4）打破环路等待条件：实现资源有序分配策略，将系统的所有资源统一编号，所有进程只能采用按序号递增的形式申请资源。

## wait() 和 sleep() 方法的区别（我没太放心上）

来源不同：sleep() 来自 Thread 类，wait() 来自 Object 类。

对于同步锁的影响不同：sleep() 不会该表同步锁的行为，如果当前线程持有同步锁，那么 sleep 是不会让线程释放同步锁的。wait() 会释

放同步锁，让其他线程进入 synchronized 代码块执行。

使用范围不同：sleep() 可以在任何地方使用。wait() 只能在同步控制方法或者同步控制块里面使用，否则会抛

`IllegalMonitorStateException`。

恢复方式不同：两者会暂停当前线程，但是在恢复上不太一样。sleep() 在时间到了之后会重新恢复；wait() 则需要其他线程调用同一对象

的 notify()/nofityAll() 才能重新恢复。

## 线程的 sleep() 方法和 yield() 方法有什么区别？

线程执行 sleep() 方法后进入超时等待（TIMED_WAITING）状态，而执行 yield() 方法后进入就绪（READY）状态。

sleep() 方法给其他线程运行机会时不考虑线程的优先级，因此会给低优先级的线程运行的机会；yield() 方法只会给相同优先级或更高优

先级的线程以运行的机会。

## 线程的 join() 方法是干啥用的？

用于等待当前线程终止。如果一个线程A执行了 threadB.join() 语句，其含义是：当前线程A等待 threadB 线程终止之后才从 threadB.join() 返回继续往下执行自己的代码。

## 编写多线程程序有几种实现方式？

通常来说，可以认为有三种方式：1）继承 Thread 类；2）实现 Runnable 接口；3）实现 Callable 接口。

其中，Thread 其实也是实现了 Runable 接口。Runnable 和 Callable 的主要区别在于是否有返回值。

## Thread 调用 start() 方法和调用 run() 方法的区别？

run()：普通的方法调用，在主线程中执行，不会新建一个线程来执行。

start()：新启动一个线程，这时此线程处于就绪（可运行）状态，并没有运行，一旦得到 CPU 时间片，就开始执行 run() 方法。

## 为什么要使用线程池？直接new个线程不是很舒服？

如果我们在方法中~~直接new一个线程来处理，当这个方法被调用频繁时就会创建很多线程~~，不仅会消耗系统资源，还会降低系统的稳定

性，一不小心把系统搞崩了，就可以直接去财务那结帐了。

如果我们合理的使用线程池，则可以避免把系统搞崩的窘境。总得来说，使用线程池可以带来以下几个好处：

- 降低资源消耗。通过重复利用已创建的线程，降低线程创建和销毁造成的消耗。

- 提高响应速度。当任务到达时，任务可以不需要等到线程创建就能立即执行。

- 增加线程的可管理型。线程是稀缺资源，使用线程池可以进行统一分配，调优和监控。

## 线程池的核心属性有哪些？

threadFactory（线程工厂）：用于创建工作线程的工厂。

corePoolSize（核心线程数）：当线程池运行的线程少于 corePoolSize 时，将创建一个新线程来处理请求，即使其他工作线程处于空闲状态。

workQueue（队列）：用于保留任务并移交给工作线程的阻塞队列。

maximumPoolSize（最大线程数）：线程池允许开启的最大线程数。

handler（拒绝策略）：往线程池添加任务时，将在下面两种情况触发拒绝策略：1）线程池运行状态不是 RUNNING；2）线程池已经达到最大线程数，并且阻塞队列已满时。

keepAliveTime（保持存活时间）：如果线程池当前线程数超过 corePoolSize，则多余的线程空闲时间超过 keepAliveTime 时会被终止。

## 线程池有哪些拒绝策略？

AbortPolicy：中止策略。默认的拒绝策略，直接抛出 RejectedExecutionException。调用者可以捕获这个异常，然后根据需求编写自己的处理代码。

DiscardPolicy：抛弃策略。什么都不做，直接抛弃被拒绝的任务。

DiscardOldestPolicy：抛弃最老策略。抛弃阻塞队列中最老的任务，相当于就是队列中下一个将要被执行的任务，然后重新提交被拒绝的任务。如果阻塞队列是一个优先队列，那么“抛弃最旧的”策略将导致抛弃优先级最高的任务，因此最好不要将该策略和优先级队列放在一起使用。

CallerRunsPolicy：调用者运行策略。在调用者线程中执行该任务。该策略实现了一种调节机制，该策略既不会抛弃任务，也不会抛出异常，而是将任务回退到调用者（调用线程池执行任务的主线程），由于执行任务需要一定时间，因此主线程至少在一段时间内不能提交任务，从而使得线程池有时间来处理完正在执行的任务。

## 网络编程协议？

1. TCP协议，它的特点是3次握手（第一次：我问你在么？ 第二次：你回答 第三次：我反馈我知道你在）。
2. UDP协议，它是将数据源和目的封装成数据包，不需要建立连接。
3. HTTP协议，超文本传输协议。

## 聊聊你对xml的了解？

xml目前主要用来做配置文件（springMVC、Spring、Mybatis、Hibernate、Struts2 等）

## 解析xml文件的几种方式？分别特点是什么？

- dom解析(占用内存，前后反复遍历)

- sax解析(节省内存，只能向后遍历数据)

## 什么是序列化?

对象最开始是不能够直接保存到磁盘中，如果需要将对象保存到磁盘中时，需要让对象实现序列化。

## 如何让对象实现序列化?

1. 让对象实现 Serializable 接口  
2. 生成序列号

## 字节流与字符流区别?

1. 当读取纯文本文件(只有文字字符)时，使用字符流比较方便
2. 当读取图片、视频、音频时需要使用字节流

常见的字节流

FileInputStream、BufferedInputStream、ObjectInputStream

FileOutputStream、BuuferedInputStream、ObjectOutputStream

常见的字符流

FileReader、BufferedReader

FileWriter、BufferedWriter

## 可变参数

```java
public class Demo1 {
    public void aaa(int...attr) {
        for (int i : attr) { System.out.println("i = " + i); }
    }
    public static void main(String[] args) { new Demo1().aaa(1,2,3,4); }
}
```

## instanceOf关键字

作用：用于判断某个对象是否是某个类型

语法：对象名`instanceOf`子类(实现)名，返回值为`boolean`

```java
    public void test() {
        ArrayList<String> aList = new ArrayList<>();
        boolean b = aList instanceof List;
        System.out.println("b = " + b);
    }
```

## Arrays、Math、Random等常用工具类

Arrays工具类：主要用来操作跟数组相关的

```java
 public void test1() {  // attrl由小到大排序
        int[] attrl = {10,-1,2,30,9,12};
        Arrays.sort(attrl);
        for (int temp : attrl) {
            System.out.println(temp);
        }
    }

    public void test() {
        List<String> aList = new ArrayList<>();
        aList.add("中国");
        aList.add("美国");
        aList.add("日本");
        List<String> bList = Arrays.asList("中国", "美国", "日本");
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        //bList.add("朝鲜");      // 返回集合是固定长度的，后期不能够添加数据
    }
```

Math工具类：主要是操作跟数学相关

常见的方法：abs(参数)、pow(2,3)

```java
    public void test() {
        int abs = Math.abs(-20);
        System.out.println("abs = " + abs);
        double pow = Math.pow(2, 3);
        System.out.println("pow = " + pow);
    }
```

Random工具类：随机数

```java
    public void test() {
        Random random = new Random();
        int i = random.nextInt();
        int i1 = (int) (Math.random() * 10000);
        System.out.println("i1 = " + i1);
    }
```

## Properties 类

Properties类主要用来操作 `.properties` 配置文件

```java
 public static void main(String[] args) throws IOException {
        Properties props = new Properties();
        InputStream ins = Demo1.class.getClassLoader().getResourceAsStream("jdbc.properties");
        props.load(ins);
        String driver = props.getProperty("driver");
        System.out.println("driver=" + driver);
        String aaa = props.getProperty("aaa", "我是默认值");
        System.out.println(aaa);
    }
```

---

## this关键字与 static 关键字

this 关键字：==代表的是本类对象的一个引用，谁调用 this所在的方法，this 就代表的是谁==

```java
    public void test1() { System.out.println(this); }
    public static void main(String[] args) { new Demo1().test1(); }
```

Static 关键字：用来修饰成员变量与成员方法

静态的特点：

1. 随着类的加载而加载
2. 优先与对象存在
3. 对所有的对象共享
4. 可以被类名直接调用

## 什么是匿名对象？匿名对象的应用场景？

什么是匿名对象：没有名字的对象，是对象的一种简写形式

```java
public class Demo1 {
    public static void print(List<String> aList) { aList.forEach((temp) -> System.out.println(temp)); }
    public static void main(String[] args) { Demo1.print(new ArrayList<String>()); }
}
```

匿名对象的应用场景：

1. 可以作为实际参数在方法传递中使用
2. 只调用类中的一次方法

## 静态变量和成员变量的区别

1. 调用方式：静态变量也称之为类变量，可以直接通过类名来调用。也可以通过对象名来调用

2. 存储位置：静态变量存储在方法区中的静态区域；成员变量存储在堆内存中

3. 生命周期：静态变量随着类的加载而存在，随着类的消失而消失，生命周期很长；

   成员变量随着对象的创建而存在，当对象消失时成员变量随之消失

4. 与对象的相关性：静态变量是所有对象共享的数据；成员变量是每个对象特有的

## 成员变量与局部变量的区别？

成员变量：直接放置在类中的变量称之为成员变量，与方法是平级关系。

局部变量：在方法体中声明的变量

1. 作用域：成员变量针对整个类有效；局部变量在某个范围内有效(方法、循环体)
2. 存储位置：成员变量随着对象的创建而存在，对象消失时成员变量就会消失，存储在堆内存中；局部变量在方法被调用，或者语句被执行的时候存在，存储在栈内存中。
3. 初始值：成员变量有默认的初始值(类类型的初始值为 Null，基本数据类型有各自特有的默认值)

## Lambda表达式遍历List 集合、Map集合

```java
 public void test01() {  // Lambda遍历List集合
        List<String> aList = Arrays.asList("张三", "李四", "王二麻子");
        aList.forEach((temp) -> System.out.println(temp));
    }

 public void test02() {  // Lambda遍历Map集合
        HashMap<String, Object> aMap = new HashMap<>();
        aMap.put("id", 1L);
        aMap.put("userName", "张三");
        aMap.put("gender", "男");
        aMap.forEach((k, v) -> System.out.println(k + " = " + v));
    }
```

## 内存结构

栈内存：用于存储局部变量，当数据使用完，所占用的空间会自动释放

堆内存：数组和对象，通过 new 建立的实例都存放在堆内存中

方法区：静态成员、构造函数、常量池、线程池

本地方法区：window 系统占用

## Java 中的数据类型

```mermaid
graph LR;
a[java的数据类型] --> b[基本数据类型: byte, boolean, char, short, int, long, float, double]
a --> c[自定义数据类型] --> 普通类Person
c --> 接口
c --> 数组
```

## 标识符命名规则

可以由数字、字母、下划线（_)、$进行组合，不能够以数字作为开头合法的命名：a1、_abc(不推荐)、abc$

## 常见的加密算法有哪些，详细解释其中一种？

常见的算法：DES、AES、RSA、Base64、MD5

 ```java
 public void testMd5() throws Exception {
        String[] attrs = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
        // MD5：单向加密算法，任何明文经过加密都是变成一个长度为32位的字符串
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        // 明文密码
        String pwd = "1";
        // 字节的长度永远为16
        byte[] digest = md5.digest(pwd.getBytes("UTF-8"));
        // 用来记录明文加密后的密文字符串
        String miWen = "";
        for (byte b : digest) {
            int temp = b;
            if (temp < 0) {
                temp += 256;
            }
            int index1 = temp / 16;
            int index2 = temp % 16;
            miWen += attrs[index1] + attrs[index2];

        }
        System.out.println("加密后的密文字符串为：" + miWen);
        System.out.println("密文的字符个数为：" + miWen.length());

    }
 ```

## 单例设计模式

什么是单例：永远只能够创建一个对象

单例设计模式书写方式总共是有两种：饿汉式、懒汉式

书写单例设计模式的语法规则：

1、类中的构造方法私有

2、需要提供一个公共的获取对象的方法

饿汉式单例设计模式：

```java
public class Dog {      // 饿汉式单例设计模式
    private static final Dog dog = new Dog();
    private Dog() { System.out.println("Dog对象被创建了"); }
    public static Dog getInstance() { return dog; }     // 获取实例
}
```

懒汉式单例设计模式：

```java
public class Cat {      // 懒汉式单例设计模式 --》会出现线程安全问题
    private static Cat cat = null;
    private Cat() { System.out.println("cat对象被创建了"); }
    public static Cat getInstance() {
        if (cat == null) { cat = new Cat(); }
        return cat;
    }
}
```

## 什么是强制类型转换、隐式类型转换？

将大的数据类型赋值小的数据类型称之为强制类型转换，会出现精度的损失

## 什么是反射?

反射是指在运行状态中，对于任意一个类都能够知道这个类所有的属性和方法；并且对于任意一个对象，都能够调用它的任意一个方法；

这种动态获取信息以及动态调用对象方法的功能称为反射机制。

Class类是反射的根基，Class对象代表的是某一个类编译后的字节码文件（.class 文件）

## 那你说说创建Class类对象的三种方式？

1. `Class.forName(“类的全路径名”)；`
2. `类名.class;`
3. `对象名.getClass();`

## native 修饰符是什么意思？

native 代表 java 访问其他语言编写的代码。

## Java 内存结构（运行时数据区）

程序计数器：线程私有。一块较小的内存空间，可以看作当前线程所执行的字节码的行号指示器。如果线程正在执行的是一个Java方法，这个计数器记录的是正在执行的虚拟机字节码指令的地址；如果正在执行的是Native方法，这个计数器值则为空。

Java虚拟机栈：线程私有。它的生命周期与线程相同。虚拟机栈描述的是Java方法执行的内存模型：每个方法在执行的同时都会创建一个栈帧用于存储局部变量表、操作数栈、动态链接、方法出口等信息。每一个方法从调用直至执行完成的过程，就对应着一个栈帧在虚拟机栈中入栈到出栈的过程。

本地方法栈：线程私有。本地方法栈与虚拟机栈所发挥的作用是非常相似的，它们之间的区别不过是虚拟机栈为虚拟机执行Java方法（也就是字节码）服务，而本地方法栈则为虚拟机使用到的Native方法服务。

Java堆：线程共享。对大多数应用来说，Java堆是Java虚拟机所管理的内存中最大的一块。Java堆是被所有线程共享的一块内存区域，在虚拟机启动时创建。此内存区域的唯一目的就是存放对象实例，几乎所有的对象实例都在这里分配内存。

方法区：与Java堆一样，是各个线程共享的内存区域，它用于存储已被虚拟机加载的类信息（构造方法、接口定义）、常量、静态变量、即时编译器编译后的代码（字节码）等数据。方法区是JVM规范中定义的一个概念，具体放在哪里，不同的实现可以放在不同的地方。

运行时常量池：运行时常量池是方法区的一部分。Class文件中除了有类的版本、字段、方法、接口等描述信息外，还有一项信息是常量池，用于存放编译期生成的各种字面量和符号引用，这部分内容将在类加载后进入方法区的运行时常量池中存放。

`String str = new String("hello");`
上面的语句中变量 str 放在栈上，用 new 创建出来的字符串对象放在堆上，而"hello"这个字面量是放在堆中。

## 什么是双亲委派模型？

如果一个类加载器收到了类加载的请求，它首先不会自己去尝试加载这个类，而是把这个请求委派给父类加载器去完成，每一个层次的类

加载器都是如此，因此所有的加载请求最终都应该传送到顶层的启动类加载器中，只有当父加载器反馈自己无法完成这个加载请求（它的

搜索范围中没有找到所需的类）时，子加载器才会尝试自己去加载。

## 类加载的过程

类加载的过程包括：加载、验证、准备、解析、初始化，其中验证、准备、解析统称为连接。

加载：通过一个类的全限定名来获取定义此类的二进制字节流，在内存中生成一个代表这个类的java.lang.Class对象。

验证：确保Class文件的字节流中包含的信息符合当前虚拟机的要求，并且不会危害虚拟机自身的安全。

准备：为静态变量分配内存并设置静态变量初始值，这里所说的初始值“通常情况”下是数据类型的零值。

解析：将常量池内的符号引用替换为直接引用。

初始化：到了初始化阶段，才真正开始执行类中定义的 Java 初始化程序代码。主要是静态变量赋值动作和静态语句块（static{}）中的语句。

## 介绍下垃圾收集机制（在什么时候，对什么，做了什么）？

在什么时候？

在触发GC的时候，具体如下，这里只说常见的 Young GC 和 Full GC。

触发Young GC：当新生代中的 Eden 区没有足够空间进行分配时会触发Young GC。

触发Full GC：

- 当准备要触发一次Young GC时，如果发现统计数据说之前Young GC的平均晋升大小比目前老年代剩余的空间大，则不会触发Young GC而是转为触发Full GC。（通常情况）
- 如果有永久代的话，在永久代需要分配空间但已经没有足够空间时，也要触发一次Full GC。
- System.gc()默认也是触发Full GC。
- heap dump带GC默认也是触发Full GC。
- CMS GC时出现Concurrent Mode Failure会导致一次Full GC的产生。

对什么？

对那些JVM认为已经“死掉”的对象。即从GC Root开始搜索，搜索不到的，并且经过一次筛选标记没有复活的对象。

做了什么？

对这些JVM认为已经“死掉”的对象进行垃圾收集，新生代使用复制算法，老年代使用标记-清除和标记-整理算法。

## GC Root有哪些?

在Java语言中，可作为GC Roots的对象包括下面几种：

- 虚拟机栈（栈帧中的本地变量表）中引用的对象。
- 方法区中类静态属性引用的对象。
- 方法区中常量引用的对象。
- 本地方法栈中JNI（即一般说的Native方法）引用的对象。

## 垃圾收集有哪些算法，各自的特点？

标记 - 清除算法

首先标记出所有需要回收的对象，在标记完成后统一回收所有被标记的对象。它的主要不足有两个：一个是效率问题，标记和清除两个过程的效率都不高；另一个是空间问题，标记清除之后会产生大量不连续的内存碎片，空间碎片太多可能会导致以后在程序运行过程中需要分配较大对象时，无法找到足够的连续内存而不得不提前触发另一次垃圾收集动作。

复制算法

为了解决效率问题，一种称为“复制”（Copying）的收集算法出现了，它将可用内存按容量划分为大小相等的两块，每次只使用其中的一块。当这一块的内存用完了，就将还存活着的对象复制到另外一块上面，然后再把已使用过的内存空间一次清理掉。这样使得每次都是对整个半区进行内存回收，内存分配时也就不用考虑内存碎片等复杂情况，只要移动堆顶指针，按顺序分配内存即可，实现简单，运行高效。只是这种算法的代价是将内存缩小为了原来的一半，未免太高了一点。

标记 - 整理算法

复制收集算法在对象存活率较高时就要进行较多的复制操作，效率将会变低。更关键的是，如果不想浪费50%的空间，就需要有额外的空间进行分配担保，以应对被使用的内存中所有对象都100%存活的极端情况，所以在老年代一般不能直接选用这种算法。

根据老年代的特点，有人提出了另外一种“标记-整理”（Mark-Compact）算法，标记过程仍然与“标记-清除”算法一样，但后续步骤不是直接对可回收对象进行清理，而是让所有存活的对象都向一端移动，然后直接清理掉端边界以外的内存。

分代收集算法

当前商业虚拟机的垃圾收集都采用“分代收集”（Generational Collection）算法，这种算法并没有什么新的思想，只是根据对象存活周期的不同将内存划分为几块。

一般是把Java堆分为新生代和老年代，这样就可以根据各个年代的特点采用最适当的收集算法。

在新生代中，每次垃圾收集时都发现有大批对象死去，只有少量存活，那就选用复制算法，只需要付出少量存活对象的复制成本就可以完成收集。

在老年代中因为对象存活率高、没有额外空间对它进行分配担保，就必须使用标记—清理或者标记—整理算法来进行回收。

## JDK1.8之后有哪些新特性？(看看就行了，你还想真记住！)

接口默认方法：Java 8允许我们给接口添加一个非抽象的方法实现，只需要使用 default关键字即可

Lambda 表达式和函数式接口：Lambda 表达式本质上是一段匿名内部类，也可以是一段可以传递的代码。Lambda 允许把函数作为一个方法的参数（函数作为参数传递到方法中），使用 Lambda 表达式使代码更加简洁，但是也不要滥用，否则会有可读性等问题，《Effective Java》作者 Josh Bloch 建议使用 Lambda 表达式最好不要超过3行。

Stream API：用函数式编程方式在集合类上进行复杂操作的工具，配合Lambda表达式可以方便的对集合进行处理。Java8 中处理集合的关键抽象概念，它可以指定你希望对集合进行的操作，可以执行非常复杂的查找、过滤和映射数据等操作。使用Stream API 对集合数据进行操作，就类似于使用 SQL 执行的数据库查询。也可以使用 Stream API 来并行执行操作。简而言之，Stream API 提供了一种高效且易于使用的处理数据的方式。

方法引用：方法引用提供了非常有用的语法，可以直接引用已有Java类或对象（实例）的方法或构造器。与lambda联合使用，方法引用可以使语言的构造更紧凑简洁，减少冗余代码。

日期时间API：Java 8 引入了新的日期时间API改进了日期时间的管理。

Optional 类：著名的 NullPointerException 是引起系统失败最常见的原因。很久以前 Google Guava 项目引入了 Optional 作为解决空指针异常的一种方式，不赞成代码被 null 检查的代码污染，期望程序员写整洁的代码。受Google Guava的鼓励，Optional 现在是Java 8库的一部分。

新工具：新的编译工具，如：Nashorn引擎 jjs、 类依赖分析器 jdeps。

## 关于依赖注入（DI）

依赖注入的主要目的是解耦合。

常用的依赖注入方式有`Setter`和构造方法。

## 线程

- Java通过synchronized进行访问的同步，synchronized作用非静态成员方法和静态成员方法上同步的目标是不同的

- 线程通过使用synchronized关键字可获得对象的互斥锁定。
- 线程调度算法是平台独立的

## Web Service 的描述

`Web Service`采用了soap协议（简单对象协议）进行通信。

`Web Service`是跨平台，跨语言的远程调用技术。

`WSDL`是用于描述Web Services 以及如何对它们进行访问。

## java 中的数组

数组是一个连续的存储结构。

数组是一个对象，不同类型的数组具有不同的类。

Java中不存在int *a这样的东西做数组的形参。

可以二维数组，且可以有多维数组，都是`java`中合法的。

```java
    public static void main(String[] args) {
        int[][] ints = {{1, 3, 3}, {5, 3, 2}, {7, 3, 5}, {7, 7, 0}};
        for (int[] a : ints) {
            for (int b : a) {
                System.out.print(b);
            }
        }
    }
```

## jdk 1.7 中

`java`中所有的抽象方法都必须在类内定义

## java 实例变量,类变量和 final 变量的说法

实例变量：类中定义的变量，即成员变量，如果没有初始化，会有默认值。

类变量：是用static修饰的属性。

final变量：是用final修饰的变量。

## java 中的 ClassLoader

`ClassLoader`的父子结构中，默认装载采用了父优先。

类装载器需要保证类装载过程的线程安全。

默认情况下，Java应用启动过程涉及三个`ClassLoader`：`Boostrap`，Extension，System

## 装箱、拆箱操作发生在

引用类型与值类型之间。

## 访问修饰符public，private，protected，以及不写时的区别？

|  修饰符   | 当前类 | 同包 | 子类 | 其他包 |
| :-------: | :----: | :--: | :--: | :----: |
|  public   |   √    |  √   |  √   |   √    |
| protected |   √    |  √   |  √   |   ×    |
|   不写    |   √    |  √   |  ×   |   ×    |
|  private  |   √    |  ×   |  ×   |   ×    |

## 以下代码能否执行？

```java
short s1 = 1; s1 = s1 + 1;  // 编译报错，原因：类型不兼容，int转short可能会出现损失。 
short s1 = 1; s1 += 1;  // 正常编译执行，s1 += 1 相当于 s1 = (short)(s1 + 1).
```

## 判断值是否相等为什么？

```java
public static void main(String[] args) {
    Integer a = 128, b = 128, c = 127, d = 127;
    System.out.println(a == b);  // false
    System.out.println(c == d);  // true
}
```

默认情况下范围为：-128~127

c 和 d 是相同对象，而 128 则没有命中，所以 a 和 b 是不同对象。

## 用最有效率的方法计算2乘以8？

2 << 3

## & 和 && 的区别？

&&：逻辑与运算符。

当运算符左右两边的表达式都为 true，才返回 true。同时具有短路性，如果第一个表达式为 false，则直接返回 false。

&：逻辑与运算符、按位与运算符。

**按位与运算符：** 用于二进制的计算，只有对应的两个二进位均为1时，结果位才为1 ，否则为0。

逻辑与运算符：& 在用于逻辑与时，和 && 的区别是不具有短路性。所在通常使用逻辑与运算符都会使用 &&，而 & 更多的适用于位运算。

## 深拷贝和浅拷贝区别是什么？

数据分为基本数据类型和引用数据类型。基本数据类型：数据直接存储在栈中；引用数据类型：存储在栈中的是对象的引用地址，真实的

对象数据存放在堆内存里。

浅拷贝：对于基础数据类型：直接复制数据值；对于引用数据类型：只是复制了对象的引用地址，新旧对象指向同一个内存地址，修改其

中一个对象的值，另一个对象的值随之改变。

深拷贝：对于基础数据类型：直接复制数据值；对于引用数据类型：开辟新的内存空间，在新的内存空间里复制一个一模一样的对象，新

老对象不共享内存，修改其中一个对象的值，不会影响另一个对象。

深拷贝相比于浅拷贝速度较慢并且花销较大。

## 并发和并行有什么区别？

并发：两个或多个事件在同一时间间隔发生。

并行：两个或者多个事件在同一时刻发生。

并行是真正意义上，同一时刻做多件事情，而并发在同一时刻只会做一件事件，只是可以将时间切碎，交替做多件事情。

## 当一个对象被当作参数传递到一个方法后，此方法可改变这个对象的属性，并可返回变化后的结果，那么这里到底是值传递还是引用传递？

值传递。Java 中只有值传递，对于对象参数，值的内容是对象的引用。

## Error 和 Exception 有什么区别？

Error 和 Exception 都是 Throwable 的子类，用于表示程序出现了不正常的情况。区别在于：

Error 表示系统级的错误和程序不必处理的异常，是恢复不是不可能但很困难的情况下的一种严重问题，比如内存溢出，不可能指望程序

能处理这样的情况。

Exception 表示需要捕捉或者需要程序进行处理的异常，是一种设计或实现问题，也就是说，它表示如果程序运行正常，从不会发生的情况。

## Java 静态变量和成员变量的区别

```java
public class Demo {
    public static String STATIC_VARIABLE = "静态变量";  // 静态变量：又称类变量，static修饰
    public String INSTANCE_VARIABLE = "实例变量";  // 实例变量：又称成员变量，没有static修饰
}
```

成员变量存在于堆内存中。静态变量存在于方法区中。

成员变量与对象共存亡，随着对象创建而存在，随着对象被回收而释放。静态变量与类共存亡，随着类的加载而存在，随着类的消失而消失。

成员变量所属于对象，所以也称为实例变量。静态变量所属于类，所以也称为类变量。

成员变量只能被对象所调用 。静态变量可以被对象调用，也可以被类名调用。

## 是否可以从一个静态（static）方法内部发出对非静态（non-static）方法的调用？

1）没有显示创建对象实例：不可以发起调用，==非静态方法只能被对象所调用==，==静态方法可以通过对象调用，也可以通过类名调用==。

```java
public class Demo {
    public static void staticMethod() {
        instanceMethod();  // 直接调用非静态方法：编译报错
    }
    public void instanceMethod() { System.out.println("非静态方法"); }
}
```

2）显示==创建对象实例==：可以发起调用，在静态方法中显示的创建对象实例，则可以正常的调用。

```java
public class Demo {
    public static void staticMethod() {
        instanceMethod01();
        Demo.instanceMethod01();        // 可以通过类直接调用静态方法！
        Demo demo = new Demo();  // 静态方法调用非静态方法首先创建实例对象!
        demo.instanceMethod();      // 再调用非静态方法：成功执行!
        demo.instanceMethod01();        // 可以通过对象调用静态方法！
    }
    public void instanceMethod() { System.out.println("非静态方法"); }
    public static void instanceMethod01() { System.out.println("静态方法"); }
}
```

static注意点：

1）静态变量只会初始化（执行）一次。

2）当有父类时，完整的初始化顺序为：父类静态变量（静态代码块）-> 子类静态变量（静态代码块）-> 父类非静态变量（非静态代码块）-> 父类构造器 -> 子类非静态变量（非静态代码块）-> 子类构造器 。

## 静态变量、静态代码块、静态方法、构造方法的执行顺序

执行顺序是：

```mermaid
graph LR;
a[静态变量] --> b[静态代码块] --> c[静态方法] --> d[构造方法]
```

```java
public class Students {

    static {
        System.out.println("静态代码块");
    }
    public static void main(String[] args) {  // 静态代码块
    }

}
```

## 如何修改tomcat端口号？

tomcat 的`server.xml`文件

```xml
<!-- apache-tomcat-10.0.6\conf下的 server.xml -->
<Connector port="8080" protocol="HTTP/1.1"
               connectionTimeout="20000"
               redirectPort="8443" />
```

## 重定向与转发的区别？

1. 重定向时地址栏会发生改变；转发时地址栏不会发生改变；
2. 重定向请求服务器2次；转发只请求服务器一次；
3. 重定向调用的是response对象中的方法；转发调用的是request对象中的方法；

## get 与 post 请求的区别？

1. get 请求：数据会在地址栏上显示出来，因此不要传递敏感数据。
2. get 请求：传递的数据量有限，因此对于有文件上传的数据时不要使用 get 请求。
3. post 请求：传递的参数不会在地址栏中显示出来，因此可以传递敏感数据。
4. post 请求：传递的数据可以有很多，因此文件上传是可以使用 post 请求。

## 文件存放在 WEB-INF 与 WebContent(WebRoot)下的区别？

1. 文件存放 WebContent 文件夹下可以直接访问；但是存放到 WEB-INF 的文件不能够直接访问，一般需要通过转发来访问。
2. 文件保存到 WEB-INF 下更加安全。

## 如何解决 get 请求与 post 请求中文乱码问题？

1. POST 请求中文乱码问题：

   request.setCharacterEncoding(“UTF-8”);

   response.setCharachterEncoding(“UTF-8”);

   response.setContentType(“text/html;charset=UTF-8”);

2. GET 请求中文乱码问题：修改 tomcat 的编码(ISO8859-1)--->UTF-8

## java 中的有哪些域对象？

java 中有 4 大域对象，按照生命周期由小到大分别为：page 域 --> request --> session --> application

域对象的特点：

1. 域对象由服务器创建，不由程序员 New
2. 域对象可以存取数据：域对象.setAttribute(String,Object)、域对象.getAttribute(String)
3. 域对象都有范围

## session 与 cookie 的区别？

session 将数据保存到服务器端，cookie 将数据保存到用户浏览器中

session 底层是基于 cookie 的。

cookie 可以用来做 xxx 小时免登陆，session 主要存放用户登录成功的标记

## getParameter 与 getAttribute 方法有什么区别？

getParameter：从 request 对象中获取表单页面或者其他方式传递过来的参数，当获取的参数信息不存在时返回值为 null

getAttribute(String key):获取保存在域对象(page、requestsession、application)中的数据区别：

getParameter 是从 request 对象中获取参数；getAttribute 是从域对象中获取之前保存好的数据

getParameter 返回值类型为 String，getAttribute 返回值类型为 Object

## 什么是 IOC

IOC：控制反转，将对象的创建、初始化、销毁等一系列的生命周期过程交给 spring 容器来管理 <bean id="唯一标识符" class="指定类的具体路径"> @Component、@Service、@Repository

## 什么是 DI?

DI：依赖注入，就是给对象的属性赋值

实现 DI：(1)、set 修改器方法 (2)、构造器方法

```xml
Set：
<bean id=”” class=””>
<property name=”对象中的属性名” value=”属性值” /></bean>

构造器：
<bean id=”” class=””>
<contructor-arg index=”构造方法中形参的下标” type=” ” value=””/></bean>
```

## 什么是 AOP?

AOP：是面向对象的一种横向补充。

## 列举几种常见的设计模式?

什么是设计模式：解决某些问题的固定思路

单例设计模式(饿汉式、懒汉式)、MVC 设计模式(M：模型，V：视图，C：控制)

装饰模式、静态工厂模式、享元模式等

## 过滤器与拦截器的区别

过滤器是实现 Filter 接口;拦截器实现 HandlerInterceptor

过滤器是 web.xml 文件中配置过滤的路径；拦截器在 springmvc.xml 文件中配置拦截路径

过滤器通过在 doFilter 方法中的  `chain.doFilter(request, response);` 实现放行；拦截器是通过

## SpringMVC 框架中如何解决 get/post 乱码问题

在 SpringMVC 框架中为我们提供了一个解决乱码问题的过滤器(CharacterEncodingFilter)，需要在 web.xml文件中进行配置。

```xml
<!-- 配置乱码的过滤器 -->
<filter>
 <filter-name>CharaterEncodingFilter</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <init-param>
     <param-name>encoding</param-name>
        <param-value>UTF-8</param-value>  <!--当前工程下所有的文件采用UTF-8的编码-->
    </init-param>
</filter>
```

```java
public class EncodingFilter implements Filter {  // 自定义处理乱码的工具类
    
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        // 1.进行强转
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse request = (HttpServletResponse) resp;
        // 2.修改request、response的编码--默认为ISO-8859-1
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        // 3.放行
        chain.doFilter(request,response);
    }
}
```

## SpringMVC 中如何开启注解处理器与适配器

需要在 springmvc.xml 文件中通过`<mvc:annotation-driven></mvc:annotation-driven>`

```xml
<!-- 配置包扫描 -->
<context:component-scan base-package="cn.java.controller.*,cn.java.service.impl"></context:component-scan>

<!-- 开启mvc注解驱动 -->
<mvc:annotation-driven></mvc:annotation-driven>
```

## 什么是 Spring Bean？Spring Bean 的生命周期？在 Spring 中如何注入一个集合？

Spring Bean: `<bean id="smallDog" class="cn.java.entity.Dog" scope="prototype" lazy="true/false/default">`

Bean 的生命周期：

`<bean id="smallDog" class="cn.java.entity.Dog" init-method="init" destroy-method="destroy">`

1. 在 applicationContext.xml 文件中指定何种类对象被 spring 容器管理
2. spring 容器帮助我们创建一个 Dog 对象，底层还是调用构造方法
3. 执行 init 初始化方法
4. 执行 Dog 对象中的其他方法
5. 当销毁 Dog 对象时，执行 Dog 对象中的 destroy

Spring 注入集合：通过 list、map、set、props 这些标签来实现的

```xml
<!-- 通过set修改器实现di -->
<bean id="dS" class="cn.java.di_set6.DataSource">
 <property name="driverClassName" value="com.mysql.jdbc.Driver"></property>
    <property name="port" value="3306"></property>
    
    <!-- 绘list绘赋值 -->
    <property name="list">
     <list>
         <value>liuzonglin</value>
            <value>18</value>
            <ref bean="oldMa"/>
        </list>
    </property>
    <!-- 绘set绘赋值 -->
    <property name="set">
     <set>
         <value>liuzonglin</value>
            <value>18</value>
            <ref bean="oldMa"/>
        </set>
    </property>
    <!-- 绘map绘赋值 -->
    <property name="set">
     <map>
         <entry key="username" value="小红"></entry>
            <entry key-ref="oldMa" value-ref="oldMa"></entry>                                 
        </map>
    </property>
    <!-- 绘Properties类型属性赋值 -->
    <property name="set">
     <props>
         <prop key="username">老马</prop>
            <prop key="uName">哈希值</prop>
            <prop key="usName">liuzonglin</prop>
        </props>
    </property>
</bean>
```
