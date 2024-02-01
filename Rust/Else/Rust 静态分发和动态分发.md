# [Rust 静态分发和动态分发](https://zhuanlan.zhihu.com/p/163650432)

首先定义两个结构体 Dog 和 Cat 分别实现 Animal trait

```rust
trait Animal {
    fn speak(&self);
}
struct Dog;
impl Animal for Dog {
    fn speak(&self) {
        println!("旺旺.....");
    }
}
struct Cat;
impl Animal for Cat {
    fn speak(&self) {
        println!("喵喵.....");
    }
}
```

通过泛型实现静态分发

```rust
fn animal_speak<T: Animal>(animal: T) {
    animal.speak();
}

fn main() {
    let dog = Dog;
    let cat = Cat;

    animal_speak(dog);
    animal_speak(cat);
}

// 输出：
// 旺旺.....
// 喵喵.....
```

这样相当于为每个Dog和Cat分别实现了一个animal_speak()方法

```rust
fn dog_speak(dog: dog) {
    dog.speak();
}

fn cat_speak(cat: Cat) {
    cat.speak();
}
```

目前只能看出来静态分发会导致编译后的二进制文件膨胀，至于好处还要和动态分发对比才能知道；

通过&或者Box智能指针来实现动态分发

```rust
fn animal_speak(animal: &dyn Animal) {
    animal.speak();
}

fn main() {
    let dog = Dog;
    let cat = Cat;

    animal_speak(&dog);
    animal_speak(&cat);
}

// 输出：
// 旺旺.....
// 喵喵.....
```

[探索Rust中的动态分派](https://alschwalm.com/blog/static/2017/03/07/exploring-dynamic-dispatch-in-rust/)
