# `impl Trait` 的生命周期省略规则

- [`RFC 1951 expanded impl trait`][RFC 1951]
    - 作为返回值，只捕捉类型参数，不捕捉参数生命周期

- [`RFC 2394 async await`][RFC 2394]
    - `async fn` 返回匿名 `impl Future` + 参数生命周期

[RFC 1951]: https://rust-lang.github.io/rfcs/1951-expand-impl-trait.html
[RFC 2394]: https://rust-lang.github.io/rfcs/2394-async_await.html

- 示例：

    ```rust
    fn main() {}

    trait Foo {}

    impl Foo for &'_ str {}

    // impl Foo 捕获泛型中类型参数
    // impl 只捕获 T 类型，不捕获生命周期
    // fn f1<T: Foo>(t: T) -> Box<impl Foo> {
    //     Box::new(t)
    // }

    // dyn Foo 需要满足 自身 生命周期
    // dyn Foo 约束 必须满足 ’static ,所以 T 需要添加约束
    // dyn 不会捕获 T 但生命周期又需要约束 T
    // fn f2<T: Foo>(t: T) -> Box<dyn Foo> {
    // fn f2<T: Foo + 'static>(t: T) -> Box<dyn Foo + 'static> {
    // fn f2<'a, T: Foo + 'a>(t: T) -> Box<dyn Foo + 'a> {
    //     Box::new(t)
    // }

    // impl 只捕获 T 类型，不捕获生命周期
    // fn f3(t: &str) -> Box<impl Foo> {
    // fn f3(t: &'static str) -> Box<impl Foo> {
    // fn f3<'a>(t: &'a str) -> Box<impl Foo + 'a> {
    // fn f3(t: &str) -> Box<impl Foo + '_> {
    //     Box::new(t)
    // }

    // fn f4(t: &str) -> Box<dyn Foo> {
    // fn f4(t: &str) -> Box<dyn Foo + 'static> {
    fn f4(t: &str) -> Box<dyn Foo + '_> {
        Box::new(t)
    }
    ```

- 示例：

    ```rust
    use std::future::Future;

    fn main() {
        let future;
        {
            let s = String::from("any");
            // future = f1(&s);
            future = f1(&s);
        }
        let _another_future = future;
    }

    // impl Future<Output = ()> 不改变 T 的生命周期的，并不依赖 &s 的生命周期
    fn f1(s: &str) -> impl Future<Output = ()> {
        // 编译器理解返回值依赖了生命周期的，就会校验 s
        // fn f1(s: &str) -> impl Future<Output = ()> + '_ {
        async move {
            // 返回值，不依赖 s 的生命周期，这里确使用了 s
            // println!("{}", s);
            ()
        }
    }

    // async 会依赖参数生命周期
    // 对于 async 而言是生成了 impl Future<Output = ()> + 'a 捕获了 'a 生命周期，就会校验 s 的生命周期
    // async fn f2(_s: &str) -> () {
    async fn f2<'a, 'b>(s1: &'a str, s2: &'b str) -> () {
        ()
    }

    // 返回值依赖 ’a 但函数内部使用了 ‘b
    fn f3<'a, 'b>(s1: &'a str, s2: &'b str) -> impl Future<Output = ()> + 'a {
        println!("{}", s2);
        async move {
            println!("{}", s1);
            ()
        }
    }
    ```
