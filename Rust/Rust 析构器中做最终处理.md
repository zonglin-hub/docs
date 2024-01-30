# 析构器中做最终处理

Rust中，通常在析构函数中运行退出前必须运行的代码。

```rust
#[derive(Debug)]
pub struct A(u8);

impl Drop for A {
    fn drop(&mut self) {
        println!("A exit")
    }
}

#[derive(Debug)]
pub struct B(u8);

impl Drop for B {
    fn drop(&mut self) {
        println!("B exit");
        // panic!("err");   // drop 函数内部恐慌程序终止
        // println!("....")
    }
}

mod tests {
    #[allow(unused_imports)]
    use super::*;
    // 执行函数结束后（变量生命周期结束后） drop 函数才执行
    #[test]
    fn test() {
        let a = A(1);
        {
            let b = B(1);
            println!("A: {:?}", a);
            println!("B: {:?}", b);
        }
        panic!("err");
    }
}
```

执行函数结束后（变量生命周期结束后） drop 函数才执行

```rust
A: A(1)
B: B(1)
B exit
thread 'collections_as_smart_pointers::tests::test' panicked at 'err', 
A exit
```
