# Pinning in Rust

- 原文地址：<https://ghoulkingr.hashnode.dev/pinning-in-rust>

---

Pinning 是我在 Rust 编程时遇到的一个非常令人困惑的话题。我努力学习它，但我看到的指南、文章和视频很难理解。
它们通常涉及必须了解 Rust 中的另一个复杂概念，这让我在其他文章、视频和关于这些其他概念的指南之间来回切换。

在本文中，我将过滤掉所有其他概念，只关注 Pinning。阅读本文后，您将学习如何在代码中应用它，并了解它在其他代码中的用法。

## 首先，什么是 Pinning？

Pinning 是 Rust 中的一项基本功能。它是锁定对象在内存中位置的过程。Pinning 可防止代码在使用对象时移动对象。

在处理指向其他对象的代码时，Pinning 是一项重要功能。因为物体往往会改变它们在内存中的位置。这些更改会导致对前一个位置的任何引用变为空。

对象更改内存位置会影响数据结构，如链表和许多异步进程。空引用会导致不可预知的行为。

# 如何构建 Pinning 对象?

Pinning 对象很简单。Rust 提供了一个用于 Pinning 对象的 `Pin` 结构。该结构体是 Rust 标准库的一部分；您可以通过 `std::pin::Pin`。

请看这个例子：

```rust
struct MyStruct {
    value: u32
}

fn main() {
    let my_struct = MyStruct{ value: 10 };
    println!("{}", my_struct.value);
}
```

此示例包含一个结构体 `MyStruct` ，该 `main` 函数按以下方式使用该结构：

1. 创建 `my_struct` 的 `MyStruct` 实例。
2. 打印 `my_struct.value` 到终端。

要 Pinning `my_struct`，您需要做几件事：

1. 添加用于 `MyStruct` 存储 `PhantomPinned` 对象的 `_pin` 字段。
2. 传递 `my_struct` 到 `Box::pin` Pinning `my_struct` 。

```rust
use std::marker::PhantomPinned;

struct MyStruct {
    value: u32,
    _pin: PhantomPinned,
}

fn main() {
    let my_struct = MyStruct {
        value: 10,
        _pin: PhantomPinned,
    };

    let pinned_struct = Box::pin(my_struct);

    println!("{}", pinned_struct.value);
}
```

我还没有讨论 `Box` 过，所以让我们来研究一下。 `Box` 允许您在堆存储中手动分配内存。

Rust 有两种类型的内存，用于在代码中存储值：堆栈内存和堆内存。
如果 Rust 可以在编译时确定对象的大小，它会将该对象存储在堆栈内存中。
如果它无法确定对象的大小，则将数据存储在堆内存中。

之所以存在这种行为，是因为堆栈内存是刚性的，但速度非常快。虽然堆内存灵活且广泛，但与堆栈内存相比速度较慢。

您可以使用 `Box::pin()` 将对象存储在堆内存中并将其 Pinning 到位。

此列表包含有关 Pinning 对象的更多信息。

- 修改 Pinning 对象
- `_pin` 字段
- 使用 Pinning 对象的风险

让我们详细看看所有这些！

## 修改 Pinning 对象

Pinning 对象后，您会立即陷入困境的一件事是尝试修改其上的数据。这可能令人沮丧，但无需担心。
有一种方法可以做到这一点，但它涉及一些违反规则的过程。所以你必须在一个 `unsafe` 块中完成。

让我们重新审视上一节中的最后一个示例。为了继续学习，我将在下面粘贴它：

```rust
use std::marker::PhantomPinned;

struct MyStruct {
    value: u32,
    _pin: PhantomPinned,
}

fn main() {
    let my_struct = MyStruct {
        value: 10,
        _pin: PhantomPinned,
    };

    let pinned_struct = Box::pin(my_struct);

    println!("{}", pinned_struct.value);
}
```

假设我们想将 `my_struct.value` 的值更改为 32。如果我们只是尝试 `my_struct.value = 32`，
编译器将生成一条错误消息，告诉您更改值是违反规则的。

要更改 `my_struct.value` 的值，必须遵循以下几个步骤：

1. 首先，使用 `Pin::as_mut(&mut my_struct)` 收集对 Pinning 对象的可变引用。
2. 然后，使用该可变引用来引用存储在 `Pin` 中的 `Pin::get_unchecked_mut(mut_ref)` 对象。
3. 最后，使用对对象的引用来修改对象。

现在，您所做的任何修改都将反映在 Pinning 对象中。

让我们看看执行这些步骤后代码的外观。

```rust
use std::pin::Pin;

struct MyStruct {
    value: u32,
    _pin: PhantomPinned,
}

fn main() {
    let mut my_struct: Pin<Box<MyStruct>> = Box::pin(MyStruct {
        value: 10,
        _pin: PhantomPinned,
    });

    println!("{}", my_struct.value);

    unsafe {
        let mut_ref: Pin<&mut MyStruct> = Pin::as_mut(&mut my_struct);
        let mut_pinned: &mut MyStruct = Pin::get_unchecked_mut(mut_ref);
        mut_pinned.value = 32;
    }
    println!("{}", my_struct.value);
}
```

如果运行代码，则在修改代码之前和之后，它会在终端中显示 的 `my_struct.value` 值。

## `_pin` 字段（field）

如果您注意到，则必须在对代码的第一次修改中向结构添加一个 `_pin` 字段。
现在，你可能会问自己这个字段是什么，它做什么。这就是我们将在本节中介绍的内容。

`_pin` 是放置在要 Pinning 的结构中的字段。它告诉编译器应该 Pinning 结构。
将方法 `Box::pin()` 应用于没有 `_pin` 字段的结构时，方法调用可能会成功，
但不会将对象 Pinning 在内存中。您可以使用以下代码对此进行测试：

```rust
use std::pin::Pin;

struct MyStruct {
    value: u32,
}

fn main() {
    let mut my_struct: Pin<Box<MyStruct>> = Box::pin(MyStruct {
        value: 10,
    });

    println!("{}", my_struct.value);

    my_struct.value = 32;   // without `_pin`, this works without any issue
    println!("{}", my_struct.value);
}
```

当您将该 `_pin` 字段应用于结构体并使用 `PhantomPinned` 初始化它时，
第 14 行 （`my_struct.value = 32;`） 将变为无效。

```rust
use std::pin::Pin;

struct MyStruct {
    value: u32,
    _pin: PhantomPinned,
}

fn main() {
    let mut my_struct: Pin<Box<MyStruct>> = Box::pin(MyStruct {
        value: 10,
        _pin: PhantomPinned,
    });

    println!("{}", my_struct.value);

    my_struct.value = 32;   // This will cause a compilation error
    println!("{}", my_struct.value);
}
```

如果您查看该 `_pin` 字段，您可能还会问为什么需要使用 `PhantomPinned` 来初始化它。
答案很简单：`PhantomPinned` 是 Rust 用来在对象上强制执行 Pinning 规则的类型。
当您要 Pinning 结构体时，应始终将其应用于 `PhantomPinned` 该 `_pin` 结构体的字段。
`PhantomPinned` 在内存中不保留任何值，除了在应用它的结构上强制执行 Pinning 规则外，什么都不做。

## 使用 Pinning 对象有什么风险？

Pinning 对象的最大问题之一是安全性。不要误会我的意思，在特定应用程序中使用对象之前 Pinning 对象可以提高安全性。
现在你可能想知道：我刚才说他们的问题在于安全；这是怎么回事？Pinning 对象的安全问题在于使用它们。

您可能已经注意到，在修改 Pinning 对象时，`my_struct` 我们必须将进程包装在一个 `unsafe` 块中。
围绕这些表达 `unsafe` 是有原因的。事实证明，篡改 Pinning 对象时应小心。否则，可能会对代码的基本部分造成未定义的行为和其他问题。

我们的例子很简单，所以没有太大的风险。但是您仍然需要将其包装在一个 `unsafe` 块中，即使它是这样。

## 结论：那么，为什么要使用 Pinning 对象呢？

既然你已经经历了这一切，那么最终的问题仍然存在。为什么要为 Pinning 对象而烦恼？
这个问题是必不可少的，因为如果不回答对象，Pinning 对象将没有任何价值。

为了回答这些问题，我编制了一个简短的清单，其中列出了每个原因：

- 在 Rust 中 Pinning 对象可确保对象保留在内存中的 Pinning 位置（对于异步编程至关重要）。
- Pinning 有助于防止在多个进程访问同一数据时出现数据争用和其他并发问题。
- Pinning 还可以通过减少处理异步数据时代码的复制和移动来帮助提高性能。
- Pinning 可确保某些类型的数据在内存中始终可用，即使计算机交换了程序的其他部分也是如此。
