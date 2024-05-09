# 堆叠借用

根据对 Rust 内存模型的理解，对一个变量进行堆叠借用时，实际上是创建了一个新的引用对象，这个引用对象包含了指向原始变量的指针以及其他关于这个引用的元数据。因此，在你的代码中，虽然 r 堆叠借用指向的是 u，但它所指向的内存位置包含了一个指向 u 的指针以及其他元数据，因此它的地址与 u 的地址是不同的。

同样的道理，当你使用 core::slice::from_raw_parts 函数从原始类型的地址生成一个字节切片时，生成的字节切片所指向的地址也是一个新的地址，它包含了一个指向原始变量的指针以及其他元数据。因此，当你通过 &head 和 &tail 来获取堆叠借用的地址时，它们实际上是指向这个新的地址，而不是原始变量 u 的地址。

综上所述，Rust 中的堆叠借用可以看作是由引用对象（包含指针和其他元数据）组成的，它们的地址可能与原始变量的地址不同。

```rust
#![allow(unused)]
fn main() {
    let u: u64 = 7_u64;
    println!("u 地址: {:p}", &u);
    let r: &u64 = &u;
    println!("r 地址: {:p}", &r);
    let s: &[u8] = unsafe {
        core::slice::from_raw_parts(&u as *const u64 as *const u8, 8)
    };
    let (head, tail) = s.split_first().unwrap();
    
    println!("head 地址: {:p}", &head);
    println!("tail 地址: {:p}", &tail);
}

// u 地址: 0x53103cf620
// r 地址: 0x53103cf670
// head 地址: 0x53103cf6c0
// tail 地址: 0x53103cf6c8
```
