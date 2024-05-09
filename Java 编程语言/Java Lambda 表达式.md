# Lambda 表达式

参考文档：

- [Lambda学会这几种即可](https://www.bilibili.com/video/BV1f34119771/)
- [【IT老齐289】Java语法中的方法引用：：是什么？](https://www.bilibili.com/video/BV1je4y1c79s/)
- [Java8新特性之二：方法引用](https://www.cnblogs.com/wuhenzhidu/p/10727065.html)

## Lambda

使用场景 Lambda 只能使用在函数式接口，​

**什么是函数式接口：** ​<u>就是一个</u>​<u>有且仅有一个抽象方法</u>​<u>，但是可以有</u>​<u>多个非抽象方法的接口</u><u>。函数式接口可以被隐式转换为Lambda表达式</u>。Lambda表达式和方法引用（实际上也可以认为是Lambda表达式）

```java
interface MyInterface { // 函数式接口： 一个接口里面只有一个方法（并且这个方法是抽象的）
    int show(int i, int k);
}
```

### 旧的接口实现

```java
MyInterface m = new MyInterface() {
    @Override
    public int show(int i, int k) {
        return 0;
    }
};
m.show(1, 1);
```

### Lambda接口实现

```java
MyInterface m = (k, v) -> {
    return k + v;
};
m.show(1, 1);
```

## “::”方法引用  

**方法引用：**方法引用可以理解为Lambda表达式的另外一种表现形式。

案例准备

```java
public class Person {

    private String name;
    private String age;

    public Person() {
    }

    public Person(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

    public static int compare(Person a, Person b) {
        int i = a.getAge().compareTo(b.getAge());
        if (i != 0) {
            return i;
        } else {
            return a.getName().compareTo(b.getName());
        }
    }
    public static int compare(Person a, Person b, Person c) {
        return 0;
    }
 // static 属于 class 不属于 对象

    public Person concat(Person b) {
        this.setName(this.getName() + "," + b.getName());
        System.out.println(this);
        return this;
    }

}
```

### 五种实现方式

#### 实现一：方法引用写法，调用 static 静态方法，参数类型动态推断

```java
@Test
public void test() {

    List<Person> list = new ArrayList<>();
    list.add(new Person("liu", "1"));
    list.add(new Person("zong", "2"));
    list.add(new Person("lin", "3"));

    // 传统写法
    list.sort(new Comparator<Person>() {
        @Override
        public int compare(Person o1, Person o2) {
            return 0;
        }
    });
    // lambda
    list.sort((a, b) -> Person.compare(a, b));

    // 方法引用写法，调用 static 静态方法，参数类型动态推断
    list.sort(Person::compare);
    System.out.println(list);
}
```

#### 实现二：stream 留处理

```java
@Test
public void test1() {
    List<Person> list = new ArrayList<>();
    list.add(new Person("liu", "1"));
    list.add(new Person("zong", "2"));
    list.add(new Person("lin", "3"));
    list.stream().sorted(Person::compare).forEach(person -> System.out.println(person));
    list.stream().sorted(Person::compare).forEach(System.out::println);
}
```

#### 实现三：调用对象方法

```java
@Test
public void test2() {
    List<Person> list = new ArrayList<>();
    list.add(new Person("liu", "1"));
    list.add(new Person("zong", "2"));
    list.add(new Person("lin", "3"));
    Person a = new Person("liuzonglin", "1");
    list.stream().sorted(Person::compare).forEach(a::concat);
}
```

#### 实现四：`:: new 实例化对象`​

```java
@Test
public void test3() {
    // stream 留处理

    List<Person> list = new ArrayList<>();
    list.add(new Person("liu", "1"));
    list.add(new Person("zong", "2"));
    list.add(new Person("lin", "3"));
    Person a = new Person("liuzonglin", "1");
    list.stream().sorted(Person::compare).collect(Collectors.toList());
    list.stream().sorted(Person::compare).collect(Collectors.toCollection(ArrayList::new));

}
```

#### 实现五：代特定实例

```java
@Test
public void test4() {
    String[] strings = {

      "liu", "zong", "lin"
    };
    // 传统写法
    Arrays.sort(strings, new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            return 0;
        }
    });
    // lambda
    Arrays.sort(strings, (a, b) -> a.compareToIgnoreCase(b));
    // :: 写法 String指代a
    Arrays.sort(strings, String::compareToIgnoreCase);

}
```
