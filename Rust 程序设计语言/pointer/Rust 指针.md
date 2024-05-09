# 指针

<strong>什么是指针：</strong>

- 指针是计算机引用无法立即直接访问的数据的一种方式（类比 书的目录）
- 数据在物理内存（RAM）中是分散的存储着
- 地址空间是检索系统
- 指针就被编码为内存地址，使用 usize 类型的整数表示。
    - 一个地址就会指向地址空间中的某个地方
- 地址空间的范围是 OS 和 CPU 提供的外观界面
    - 程序只知道有序的字节序列，不会考虑系统中实际 RAM 的数量

<strong>名词解释：</strong>

- 内存地址（地址），就是指代内存中单个字节的一个数
    - 内存地址是汇编语言提供的抽象
- 指针（有时扩展称为原始指针），就是指向某种类型的一个内存地址
    - 指针是高级语言提供的抽象
- 引用，就是指针。如果是动态大小的类型，就是指针和具有额外保证的一个整数
    - 引用是 Rust 提供的抽象

<strong>Rust 的引用：</strong>

- 引用始终引用的是有效数据
- 引用与 usize 的倍数对齐
- 引用可以为动态大小的类型提供上述保障

## Rust 引用 和 指针

```rust
static B: [u8; 10] = [99, 97, 114, 114, 121, 116, 111, 119, 101, 108];
static C: [u8; 11] = [116, 104, 97, 110, 107, 115, 102, 105, 115, 104, 0];

fn main() {
    let a = 42;
    let b = &B;
    let c = &C;

    println!("a: {}, b: {:p}, c: {:p}", a, b, c);
}

// a: 42, b: 0x1023dc660, c: 0x1023dc66a
```

- 一个更加逼真的例子
    - 使用更复杂的类型展示指针内部的区别

```rust
use std::mem::size_of;

static B: [u8; 10] = [99, 97, 114, 114, 121, 116, 111, 119, 101, 108];
static C: [u8; 11] = [116, 104, 97, 110, 107, 115, 102, 105, 115, 104, 0];

fn main() {
    let a: usize = 42;
    let b = Box::new(B);
    let c = &C;

    // {:p} 有两个意思 -> 1. 表示打印的是指针 2. 表示打印地址
    // println!("a: {}, b: {:p}, c: {:p}", a, b, c);

    println!("a (unsigned 整数):");
    println!(" 地址: {:p}", &a);
    println!(" 大小: {:?} bytes", size_of::<usize>());
    println!(" 值:   {:?}\n", a);

    println!("b (B 装在 Box 里):");
    println!(" 地址: {:p}", &b);
    println!(" 大小: {:?} bytes", size_of::<Box<[u8]>>());
    println!(" 值:   {:p}\n", b);

    println!("B (10 bytes 的数组):");
    println!(" 地址: {:p}", &B);
    println!(" 大小: {:?} bytes", size_of::<Box<[u8]>>());
    println!(" 值:   {:?}\n", B);

    println!("c (C 的引用):");
    println!(" 地址: {:p}", &c);
    println!(" 大小: {:?} bytes", size_of::<&[u8; 11]>());
    println!(" 值:   {:p}\n", c);

    println!("C (11 bytes 的数字):");
    println!(" 地址: {:p}", &C);
    println!(" 大小: {:?} bytes", size_of::<&[u8; 11]>());
    println!(" 值:   {:?}\n", C);
}

// a (unsigned 整数):
//  地址: 0x8309d4efb8
//  大小: 8 bytes
//  值:   42

// b (B 装在 Box 里):
//  地址: 0x8309d4efc0
//  大小: 16 bytes
//  值:   0x1e2768e4a50

// B (10 bytes 的数组):
//  地址: 0x7ff73778f390
//  大小: 16 bytes
//  值:   [99, 97, 114, 114, 121, 116, 111, 119, 101, 108]

// c (C 的引用):
//  地址: 0x8309d4efd8
//  大小: 8 bytes
//  值:   0x7ff73778f39a

// C (11 bytes 的数字):
//  地址: 0x7ff73778f39a
//  大小: 8 bytes
//  值:   [116, 104, 97, 110, 107, 115, 102, 105, 115, 104, 0]
```

- 对 B 和 C 中文本进行解码的例子
    - 它创建了一个与前图更加相似的内存地址布局

```rust
use std::borrow::Cow;
use std::ffi::CStr;
use std::os::raw::c_char;

static B: [u8; 10] = [99, 97, 114, 114, 121, 116, 111, 119, 101, 108];
static C: [u8; 11] = [116, 104, 97, 110, 107, 115, 102, 105, 115, 104, 0];

fn main() {
  let a = 42;
  let b: String;
  let c: Cow<str>;
  
  unsafe {
    let b_ptr = &B as * const u8 as *mut u8;
    b = String::from_raw_parts(b_ptr, 10, 10);
    
    let c_ptr = &C as *const u8 as *const c_char;
    c = CStr::from_ptr(c_ptr).to_string_lossy();
  }
  println!("a: {}, b: {}, c: {}", a, b, c);
}

// a: 42, b: carrytowel, c: thanksfish
```

## Raw Pointers（原始指针）

- Raw Pointer （原始指针）是没有 Rust 标准保障的内存地址。
    - 这些本质上是 unsafe 的
- 语法：
    - 不可变 Raw Pointer：*const T
    - 可变的 Raw Pointer：*mut T
    - 注意：*const T，这三个标记放在一起表示的是一个类型
    - 例子：*const String
- *const T 与*mut T 之间的差异很小，相互可以自由转换
- Rust 的引用（&mut T 和 &T）会编译为原始指针
    - 这意味着无需冒险进入 unsafe 块，就可以获得原始指针的性能
- 例子：把引用转为原始指针

```rust
fn main() {
  let a: i64 = 42;
  let a_ptr = &a as *const i64;
  
  println!("a: {} ({:p})", a, a_ptr);
}
```

- 解引用（dereference）：通过指针从 RAM 内存提取数据的过程叫做对指针进行解引用（dereferencing a pointer）
- 例子：把引用转为原始指针

```rust
fn main() {
  let a: i64 = 42;
  let a_ptr = &a as *const i64;
  let a_addr: usize = unsafe {std::mem::transmute(a_ptr)};
  
  println!("a: {} ({:p}...0x{:x})", a, a_ptr, a_addr + 7);
}
```

<strong>关于 Raw Pointer 的提醒：</strong>

- 在底层，引用（&T 和 &mutT）被实现为原始指针。但引用带有额外的保障，应该始终作为首选使用
- 访问 Raw Pointer 的值总是 unsafe 的
- Raw Pointer 不拥有值的所有权
    - 在访问时编译器不会检查数据的合法性
- 允许多个 Raw Pointer 指向同一数据
    - Rust 无法保证共享数据的合法性

<strong>使用 Raw Pointer 的情况：</strong>

- 不可避免
    - 某些 OS 或 第三方库需要使用，例如与C交互
- 共享对某些内容的访问至关重要，运行时性能要求高

<strong>Rust 指针生态：</strong>

- Raw Pointer 是 unsafe 的
- Smart Pointer（智能指针）倾向于包装原始指针，附加更多的能力
    - 不仅仅是对内存地址解引用

<strong>Rust 智能指针：</strong>

| 名称         | 简介                                                                                     | 强项                                                | 弱项                                     |
| ------------ | ---------------------------------------------------------------------------------------- | --------------------------------------------------- | ---------------------------------------- |
| Raw Pointers | `*mut T` 和 `*const T`，</br> 自由基，闪电般快，极其 Unsafe                              | 速度、与外界交互                                    | Unsafe                                   |
| `Box<T>`     | 可把任何东西放入 `Box` 里。可接受几乎任何类型的长期存储。</br>新的安全编程时代的主力军。 | 对值集合中存储在 `Heap`                             | 大小增加                                 |
| `Rc<T>`      | 单线程引用计数指针。Rc 代表引用计数。</br> 它知道谁借了什么，何时借了什么                | 对值的共享访问                                      | 大小增加；运行时成本增加；线程不安全     |
| `Arc<T>`     | 跨线程共享值，保证这些值不会相互干扰                                                     | 对值的共享访问；线程安全                            | 大小增加；运行时成本增加                 |
| `Cell<T>`    | 具有改变不可变值的能力                                                                   | 内部可变性                                          | 大小增加；性能                           |
| `RefCell<T>` | 对不可变引用执行改变，但有代价                                                           | 内部可变性；可与仅接受不可变的引用Rc、Arc嵌套使用   | 大小增加；运行时成本增加；缺乏编译时保障 |
| `Cow<T>`     | 封闭并提供对借用数据的不可变访问，并在需要</br>修改或所有权时延迟克隆数据                | 当只有只读访问时避免写入                            | 大小可能会增大                           |
| `String`     | 处理可变长度的文本，展示了如何构建安全的抽象                                             | 动态按需增长；在运行时保证正确编码                  | 过度分配内存大小                         |
| `Vec<T>`     | 用于存储可变长度的元素序列，并保持数据有序。                                             | 动态按需增长                                        | 过度分配内存大小                         |
| `RawVec<T>`  | 时`Vec<T>`和其它动态大小类型的基石；</br>知道如何按需给你的数据提供一个家                | 动态按需增长；与内存分配器一起配合寻找空间          | 不适合用于你的代码                       |
| `Unique<T>`  | 作为值的唯一所有者，可保证拥有完全控制权                                                 | 需要独占值的类型（如 String）的基础                 | 不适合直接用于应用程序代码               |
| `Shared<T>`  | 分享所有权很难，但它使生活更轻松                                                         | 共享所有权；可以将内存与T的宽度对齐，即使是空的时候 | 不适合直接用于应用程序代码               |

## deref

deref()方法是将一个智能指针转换为底层数据类型的引用。

```rust
fn main() {
    let a = vec![1, 2, 3];
    a.iter().for_each(|f| println!("{}", f));
    // deref 方法是将一个智能指针转换为底层数据类型的引用。
    let b = a.deref();
    b.iter().for_each(|f| println!("{}", f));
}
```

在上述代码中，Vec是一个智能指针，所以 `a.deref()` 返回的是一个指向底层数据的引用，可以通过这个引用访问到原始的数据。
因此，`a.iter().for_each()` 和`b.iter().for_each()` 打印的内容相同，都是1, 2, 3。

这种用法在许多情况下都非常有用，例如将智能指针转换为对底层数据的可变引用以进行修改。示例如下：

```rust
fn main() {
    let mut v = vec![1, 2, 3];
    {
        let v_mut = v.get_mut(..).unwrap();
        v_mut[0] = 4;
        v_mut[1] = 5;
        v_mut[2] = 6;
    }
    assert_eq!(v, vec![4, 5, 6]);
}
```

在上面的例子中，通过调用deref_mut方法，我们可以获得一个指向底层数据的可变引用，然后就可以对它进行修改。
