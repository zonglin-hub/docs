# 生命周期 - HRTB & GAT

## 「高级特质约束 `higher-ranked trait bounds`」

- [RFC 387 higher ranked trait bounds][RFC 387]（`for<'a>...`）
    - `for<'a>` 表示 `for any lifetime` 都会成立
    - 目前 `fn*` 系列默认 `HRTB`
    - 区别 `lifetime` 的「早期绑定 `early_bound`」和「延迟绑定 `late_bound`」
        - 早期绑定：如果一个泛型函数的参数使用双引号 `('')` 包裹，那么这个参数就是早期绑定的。
        早期绑定参数的实际值在编译期间就会确定，而不是在函数调用时。
        - 延迟绑定：如果一个泛型函数的参数使用单引号 `( ')` 包裹，那么这个参数就是延迟绑定的。
        延迟绑定参数的实际值在函数调用时才会被确定，而不是在编译期间。

[RFC 387]: https://rust-lang.github.io/rfcs/0387-higher-ranked-trait-bounds.html

- 示例：

    ```rust
    use std::fmt::Debug;

    trait DoSomething<T> {
        fn do_sth(&self, value: T);
    }

    impl<'a, T: Debug> DoSomething<T> for &'a usize {
        fn do_sth(&self, value: T) {
            println!("{:?}", value);
        }
    }

    // fn foo1<'a>(b: Box<dyn DoSomething<&'a usize>>) {
    //     let s: usize = 10;
    //     b.do_sth(&s); // 报错，因为对于trait object，不会去检查具体类型do_sth实现
    // }

    fn foo2<'a>(b: Box<&'a usize>) {
        let s: usize = 10;
        b.do_sth(&s); // 此时编译器不会报错，因为会直接去检查 `do_sth` 实现
    }

    fn foo3(b: Box<dyn for<'a> DoSomething<&'a usize>>) {
        let s: usize = 10;
        b.do_sth(&s); // 通过，因为对于 `trait object`，标记了 `for<'f>` 会去检查具体类型 `do_sth` 实现
    }

    fn main() {
        let x = Box::new(&2usize);
        foo3(x);
    }
    ```

    在上面的例子中，

    ```rust
    fn foo<'a>(b: Box<dyn DoSomething<&'a usize>>)
    ```

    编译器看到 `'a` 是会实施早期绑定，也就是在编译时根据局部变量 `s` 的生命周期，判断会出现悬垂指针。
    然而根据语义，这里逻辑是没有问题的，所以需要使用：

    ```rust
    fn foo(b: Box<dyn for<'a> DoSomething<&'a usize>>)
    ```

    `for<'a>` 是高阶 `trait` 限定，提示编译器进行，延迟绑定，也就是检查了 `b` 调用 `do_sth` 函数的具体实现，再进行生命周期泛型参数的实例化。
    编译器发现 `b` 实现的 `do_sth` 函数没有返回引用，也就不需要进行生命周期的检查了，通过编译。

    ```rust
    impl<'a, T: Debug> DoSomething<T> for &'a usize {
        fn do_sth(&self, value: T) {
            println!("{:?}", value);
        }
    }
    ```

    编译器为什么实施早期绑定

    ```rust
    fn foo1<'a>(b: Box<dyn DoSomething<&'a usize>>) {
        let s: usize = 10;
        b.do_sth(&s); // 报错，因为对于 `trait object`，不会去检查具体类型 `do_sth` 实现
    }

    fn foo2<'a>(b: Box<&'a usize>) {
        let s: usize = 10;
        b.do_sth(&s); // 此时编译器不会报错，因为会直接去检查 `do_sth` 实现
    }

    fn foo3(b: Box<dyn for<'a> DoSomething<&'a usize>>) {
        let s: usize = 10;
        b.do_sth(&s); // 通过，因为对于 `trait object`，标记了 `for<'f>` 会去检查具体类型 `do_sth` 实现
    }
    ```

- 示例：

    ```rust
    fn main() {}

    // 函数出现 ‘a 那他的生命周期约束，一定要比 方法体 生命周期长的，但这里 `Fn(&'a i32)` 约束了 zero 的生命周期长度
    fn call_on_ref_zero<'a, F>(f: F)    // 这里 ’a 是从函数外部传递的。方法体，zero 使用是合规的。
    where
        F: Fn(&'a i32),
    {
        let zero = 0;
        f(&zero); // error[E0597]: `zero` does not live long enough
    }
    ```

    在上面的例子中，`'a` 会实施早期绑定

    ```rust
    fn call_on_ref_zero1<F>(f: F)
    where
        F: for<'a> Fn(&'a i32), // 对于任意的 ’a 函数 内部的标识都是满足要求的
    {
        let zero = 0;
        f(&zero);
    }

    fn call_on_ref_zero<F>(f: F)
    where
        F: Fn(&i32),    // Fn 本事就是高级 trait 绑定，所以 for<'a> 可以省略为 Fn(&i32)
    {
        let zero = 0;
        f(&zero);
    }

    fn call_on_ref_zero2<'a, F>(f: F)   // 'a 对函数生命周期不造成影响
    where
        F: Fn(&i32),
    {
        let zero = 0;
        f(&zero);
    }
    ```

- 示例：

    ```rust
    fn f<'a>() {}
    fn g<'a: 'a>() {}

    fn main() {
        // 如果存在延迟绑定的生命周期参数，则不能显式指定生命周期参数
        // let ff = f::<'static> as fn();

        // f 为延迟绑定不应该提前绑定 ‘static
        let ff = f as fn();

        // g 为 early bound
        let gg = g::<'static> as fn();
    }

    ```

## 「泛型关联类型 `Generic Associated Types`」

「泛型关联类型 Generic Associated Types」是一种与 C++ 中的 「模板元编程 Template Metaprogramming，(TMP)」类似的特性。
通过这种方式，我们可以在编译期间执行计算，从而实现一些高级的功能。
在 Rust 中，泛型关联类型主要通过 `std::associated_types` 库中的 `AssociatedType` 结构体来实现。
要使用泛型关联类型，首先需要导入 `std::associated_types` 库：

```rust  
use std::associated_types::{AssociatedType, Generic};  
```

然后，我们可以创建一个泛型类型，该类型使用一个泛型参数 `A`，并将其关联到一个特定的类型 `B`：

```rust  
struct Point<A> {  
   x: A,  
   y: A,  
}

impl<A: std::fmt::Display> Point<A> {  
   fn print(&self) {  
       println!("x: {}, y: {}", self.x, self.y);  
   }  
}
```

在上面的示例中，我们创建了一个名为 `Point` 的泛型结构体，它包含两个泛型参数 `A`。
然后，在 `impl` 块中，我们指定了 `A` 必须实现 `std::fmt::Display` trait 。
这使得我们可以在 `print` 方法中打印 `x` 和 `y` 值。

注意，泛型关联类型只能在某些情况下使用，例如在创建枚举、结构体、trait 等时。
在使用泛型关联类型时，需要遵循一定的语法规则和限制。

- [RFC 1598 generic associated types][RFC 1598]
    - 「关联 associated」type 中可以增加类型参数，当然也就包括 `lifetime`
    - [加强理解 GAT 的一篇文章][gat]

[RFC 1598]: https://rust-lang.github.io/rfcs/1598-generic_associated_types.html
[gat]: https://sabrinajewson.org/blog/the-better-alternative-to-lifetime-gats
