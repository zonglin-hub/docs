# int和Integer有什么区别

int类型的数据范围是多少

int类型占4个字节（32位）数据范围`-2^31 ~ 2^31-1`​​

引用数据类型

Java是一个近乎纯洁的面向对象编程语言，但是为了编程的方便还是引入了基本数据类型，但是为了能够将这些基本数据类型当成对象操作，Java为每一个基本数据类型都引入了对应的包装类型（wrapper class），int的包装类就是Integer，从Java 5开始引入了自动装箱/拆箱机制，使得二者可以相互转换。

Java 为每个原始类型提供了包装类型：

* 原始类型: boolean，char，byte，short，int，long，float，double
* 包装类型：Boolean，Character，Byte，Short，Integer，Long，Float，Double

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
