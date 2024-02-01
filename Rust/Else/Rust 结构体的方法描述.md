# Rust 结构体的方法描述

原文地址：<https://rustwiki.org/zh-CN/rust-by-example/fn/methods.html>

`Rust` 的 方法（method）是依附于对象的函数。这些方法通过关键字 `self` 来访问对象中的数据和其他。方法在 `impl` 代码块中定义。

## 静态方法（static method）

静态方法不需要实例来调用，把结构体看作 `Class`，静态方法则可以直接在 `Class` 上调用。得益于此特性，静态方法一般用作构造器（constructor），返回自己的实例。

## 实例方法（instance method）

实例方法需要依附于实例调用，因此一般的实例方法的第一个参数都是 self。常见的有：

- `self` 为 `self: Self` 的语法糖，这个方法会 “消耗” 调用者的资源
- `&self` 为 `self: &Self` 的语法糖（sugar），其中 `Self` 是方法调用者的
- `&mut self` 为 `self: &mut Self` 的语法糖
