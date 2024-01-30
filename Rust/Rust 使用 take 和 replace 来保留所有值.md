# Rust 使用 take 和 replace 来保留所有值

枚举类型

```rust
enum MyEnum {
    A { name: String, x: u32 },
    B { name: String },
}
```

使用 `std::mem::take()` 和 `std::mem::replace()` 在不克隆 `name` 的情况下修改 `name`

这种方式可以不用 `#[derive(Clone)]`，不存在内存分配。

```rust
#![allow(unused)]
use std::mem;

#[derive(Debug, Clone)]
enum MyEnum {
    A { name: String, x: u32 },
    B { name: String },
}

fn convert_a_to_b(x: &mut MyEnum) {
    // if let MyEnum::A { name, x: 0 } = x {
    //     *x = MyEnum::B {
    //         name: mem::take(name),
    //     }
    // }

    *x = match *x {
        MyEnum::A { ref mut name, x: 0 } => MyEnum::B {
            name: mem::take(name),
        },
        _ => return,
    }
}
#[allow(clippy::mem_replace_with_default)]
fn convert_a_to_b2(x: &mut MyEnum) {
    if let MyEnum::A { name, x: 0 } = x {
        *x = MyEnum::B {
            name: mem::replace(name, String::new()),
        }
    }
}

/// 假设我们有一个至少有两种变体的枚举&mut MyEnum 分别为
/// A { name: String, x: u8 }
/// B { name: String }
/// 现在我们想要当 `x = 0` 时，将A变为B，同时变量除变体类型变化外其他不变
#[test]
fn test() {
    let mut a = MyEnum::A {
        name: "A".to_owned(),
        x: 0,
    };
    println!("Before Convert, a is {:?}", a);

    convert_a_to_b(&mut a);
    println!("After Convert, a is {:?}", a);

    let mut a = MyEnum::A {
        name: "A".to_owned(),
        x: 0,
    };
    println!("Before Convert, a is {:?}", a);

    convert_a_to_b2(&mut a);
    println!("After Convert, a is {:?}", a);
}
```
