# 构造器和默认构造器

Rust中，通常使用一个关联函数new来创建一个对象，通过Default trait来支持默认构造器。

```rust
// #[derive(Default)]来实现Default，而不必显式的实现。
#[derive(Default)]
pub struct Person {
    name: String,
    age: u64,
}

impl Person {
    // new 构造函数创建对象
    pub fn new(name: String, age: u64) -> Self {
        Self { name, age }
    }

    pub fn print(&self) {
        println!("name: {}", self.name);
        println!("age: {}", self.age);
    }
}

// 为Person实现Default trait
// impl Default for Person {
//     fn default() -> Self {
//         Self {
//             name: "liuzonglin".to_owned(),
//             age: 27,
//         }
//     }
// }
```

```rust
#[allow(unused_imports)]
pub mod person;
use person::Person;

fn main() {
    let person = Person::new("liuzonglin".to_owned(), 27);
    person.print();

    // error[E0451]: field `name` of struct `Person` is private
    // 使用 new构建函数对类型来说具有更好的封装性
    // let person = Person { name: "liuzonglin".to_string(), age: 27, };
    // person.print();

    // default 实现 分两种：
    // 1. #[derive(Default)] 来实现Default，而不必显式的实现。
    // 2. impl Default for Person {...} 为Person实现Default trait
    let person = Person::default();
    person.print();
}
```
