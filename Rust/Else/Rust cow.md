# [cow](https://rustwiki.org/zh-CN/std/borrow/enum.Cow.html)

```rust
fn main() {
    let s = String::from("hello");
    let mut vec: Vec<std::borrow::Cow<String>> = Vec::new();
    // vec.push(std::borrow::Cow::Borrowed(&s));
    vec.push(std::borrow::Cow::Owned(s));
    println!("The string is: {}", vec[0]);
}
```

在这个修正后的示例中，我们使用 `std::borrow::Cow::Owned` 来创建一个新的 `String` 实例，并将其推送到 `vec` 矢量中。这样可以确保 `vec` 拥有自己的 `String` 实例，而不是借用 `s` 的生命周期。

在这个示例中，我们使用 &s 作为参数传递给 std::borrow::Cow::Borrowed 函数，这样编译器就知道我们要借用 s 的值。
