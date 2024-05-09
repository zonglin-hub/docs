# Rust Global

这里 Global 是什么意思?

```rust
leet_code::leet_code::_0225_my_stack
 // size = 32 (0x20), align = 0x8
pub struct MyStack {
    pub data: VecDeque<i32, Global>,
}
```

在Rust中，`Global` 通常是指 `std::alloc::Global`，这是 Rust 标准库中提供的全局内存分配器。
当你创建一个「双端队列 VecDeque」时，你可以指定其元素类型以及使用的分配器。
在大多数情况下，你不需要手动指定分配器，因为 `VecDeque` 默认使用全局分配器。
在这个结构体定义中：

```rust
pub struct MyStack {
    pub data: VecDeque<i32, Global>,
}
```

`Global` 是 `VecDeque` 的第二个类型参数，它告诉Rust编译器这个 `VecDeque` 应该使用全局分配器来管理其内存。
这意味着当 `VecDeque` 需要分配更多内存时（例如，当你向队列中添加更多元素时），它将使用Rust的默认全局内存分配器来处理这个请求。

如果你不熟悉Rust的内存分配器，可以这样简单理解：`Global` 是一个确保你的 `VecDeque` 可以动态地请求和释放内存的组件。
这是Rust中处理可变大小数据结构的标准方式。

在实际使用中，你通常不需要直接与 `Global` 交互，因为它是自动处理的。
然而，如果你想要使用自定义的分配器或者需要针对特定平台的优化，你可能会选择不同的分配器。
但在大多数情况下，使用 `Global` 就足够了。
