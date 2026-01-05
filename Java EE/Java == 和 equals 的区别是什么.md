# == 和 equals 的区别是什么？

* equals 代码示例：

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

## **「== 解读」**

对于基本类型和引用类型 == 的作用效果是不同的，如下所示：

* 基本类型：比较的是值是否相同；
* 引用类型：比较的是引用地址是否相同；

## **「equals 解读」**

equals 默认情况下是引用比较，只是很多类重新了 equals 方法，比如 String、Integer 等把它变成了值比较，所以一般情况下 equals 比较的是值是否相等。

```java
    public static void main(String[] args) {
        String x = "string";
        String y = "string";

        String z = new String("string");

        System.out.println(x == y);   // true 引用类型：比较的是引用地址是否相同
        System.out.println(x == z);   // false

        System.out.println(x.equals(y));  // true
        System.out.println(x.equals(z));  // true equals 默认情况下是引用比较，只是很多类重新了 equals 方法，比如 String、Integer 等把它变成了值比较，所以一般情况下 equals 比较的是值是否相等。
    }
```

---

**扩展**

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
        System.out.println(i1.equals(i2));   // 结果：true。两个不同的对象，但是具有相同的值
    }
```

**代码解读：**

因为 x 和 y 指向的是同一个引用，所以 == 也是 true，而 new String() 方法则重写开辟了内存空间，所以 == 结果为 false，而 equals 比较的一直是值，所以结果都为 true。

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

**总结：**

* == 对于基本类型来说是值比较，对于引用类型来说是比较的是引用；

* equals 默认情况下是引用比较，只是很多类重新了 equals 方法，比如 String、Integer 等把它变成了值比较，所以一般情况下 equals 比较的是值是否相等。

‍
