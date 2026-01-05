# 什么是 Sum, Product, 和 Pi 类型？

- 原文地址：<https://manishearth.github.io/blog/2017/03/04/what-are-sum-product-and-pi-types/>

---

你经常听到人们说 “语言 X 有求和类型” 或 “我希望语言 X[^1] 有求和类型”[^2] ，或者 “求和类型很酷”。

[^1]: Rust、Swift、Typescript，以及所有在它很酷之前就拥有它的函数式语言。

[^2]: 看着你，走吧。

就像 fezzes 和领结一样，sum 类型确实很酷。

这些天，我也看到有人问 “Pi 类型”，因为这个 [Rust RFC] 。

[Rust RFC]: https://github.com/ticki/rfcs/blob/pi-types-2/text/0000-pi-types.md

但是 “总和类型” 是什么意思？为什么叫这个名字？以理智的名义，什么是 Pi 类型？

在开始之前，我会提到，虽然我将介绍一些类型理论来解释 “总和” 和 “产品” 这两个名称，但你不需要理解这些名称来使用这些东西！人们经常难以理解语言中相对简单的概念，因为它们的名称令人困惑，数学背景令人困惑 3 。

在开始之前，我会提到，虽然我将介绍一些类型理论来解释“总和”和“产品”这两个名称，但你不需要理解这些名称来使用这些东西！人们经常难以理解语言中相对简单的概念，因为它们的名称令人困惑，数学背景令人困惑。

那么什么是求和类型呢？（无类型理论版本）
---

从本质上讲，求和类型基本上是 “或” 类型。让我们先看一下结构。

```rust
struct Foo {
    x: bool,
    y: String,
}
```

`Foo` `bool` 是一个 和 一个 `String` .你需要每一个来制作一个。这是一个 “and” 类型，或者一个 “product” 类型（我稍后会解释这个名字）。

那么 “或” 类型是什么呢？它将是一个值可以是 `bool`  或者的值。 `String` 您可以使用带有联合的 C++ 来实现这一点：

```rust
union Foo {
    bool x;
    string y;
}

foo.x = true; // set it to a bool
foo.y = "blah"; // set it to a string
```

但是，这并不完全正确，因为该值不存储它是哪个变体的信息。你可以存储 `false` ，读者不会知道你存储的是空 `string` 的还是 `false` `bool` .

C++ 中有一种称为 “标记联合”（或“歧视联合”）的模式可以弥合这一差距。

```c++
union FooUnion {
    bool x;
    string y;
}

enum FooTag {
    BOOL, STRING
}

struct Foo {
    FooUnion data;
    FooTag tag;
}

// set it to a bool
foo.data.x = true;
foo.tag = BOOL;

// set it to a string
foo.data.y = "blah";
foo.tag = STRING;
```

在这里，您在设置值时手动设置标签。C++ 还具有 `std::variant` （或 `boost::variant` ）用更好的 API 封装此模式。

虽然我在这里称这些为 “或” 类型，但此类类型的技术术语是 “sum” 类型。其他语言具有内置的求和类型。

Rust 拥有它们并称它们为 “枚举”。这些是您在其他语言中看到的枚举的更通用版本。

```rust
enum Foo {
    Str(String),
    Bool(bool)
}

let foo = Foo::Bool(true);

// "pattern matching"
match foo {
    Str(s) => /* do something with string `s` */,
    Bool(b) => /* do something with bool `b` */,
}
```

Swift 与此类似，也称它们为枚举

```swift
enum Foo {
    case str(String)
    case boolean(bool)
}

let foo = Foo.boolean(true);
switch foo {
    case .str(let s):
        // do something with string `s`
    case .boolean(let b):
        // do something with boolean `b`
}
```

您也可以在 Go 中使用接口伪造这些内容。Typescript 具有内置的联合，无需任何特殊工作即可进行类型检查，但您需要添加一个标签（如在 C++ 中）以匹配它们的模式。

当然， Haskell 有：

```haskell
data Foo = B Bool | S String

-- define a function
doThing :: Foo -> SomeReturnType
doThing (B b) = -- do something with boolean b
doThing (S s) = -- do something with string s

-- call it
doThing (S "blah")
doThing (B True)
```

具有 sum 类型的语言所做的非常常见的事情之一是将可空性表示为 sum 类型;

```rust
// an Option is either "something", containing a type, or "nothing"
enum Option<T> {
    Some(T),
    None
}

let x = Some("hello");
match x {
    Some(s) => println!("{}", s),
    None => println!("no string for you"),
}
```

一般来说，这些语言有“模式匹配”，这就像类固醇 `switch` 的声明。它可以让你匹配和解构各种东西，总和类型就是其中之一。通常，这些是“详尽的”，这意味着您被迫处理所有可能的情况。在 Rust 中，如果删除该 `None` 分支，程序将无法编译。所以你被迫以某种方式处理无情况。

一般来说，求和类型是一个非常简洁和强大的工具。内置它们的语言往往会大量使用它们，几乎与它们使用结构一样多。

为什么我们称它为求和类型？
---

这里是（类型论）[dragons][Compilers: Principles, Techniques, and Tools]

[Compilers: Principles, Techniques, and Tools]: https://suif.stanford.edu/dragonbook/

让我们退后一步，弄清楚什么是类型。

这实际上是对允许的值的限制。它可以有方法之类的东西，但在这里并不那么重要。

换句话说，它就像一个[^3] 集合。布尔值是集合
{true, false}。一个 8 位无符号整数（ `u8` 在 Rust 中）是集合
{0, 1, 2, 3, ... 254, 255} 。字符串是具有无限元素的集合，包含所有可能的有效字符串[^4]。

[^3]: 由于一些差异，类型并不完全是集合，但出于本文的目的，我们可以将它们视为集合。

[^4]: 尽管您可以争辩说字符串的长度通常受平台的指针大小限制，因此它仍然是一个有限的集合。↩

```rust
struct Foo {
    x: bool,
    y: u8,
}
```

的可能值集 `Foo` 是

$$\{(x, y): x ∈ bool, y ∈ u8 \}$$

（读作“所有 (x,y) where x is bool in 和 y is in u8 的集合”）

未完待续
---
