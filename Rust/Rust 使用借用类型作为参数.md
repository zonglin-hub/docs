# 使用借用类型作为参数

编码时应该总是倾向于使用借用类型而不是借用所有类型。

## 对于`String`类型来说，应该倾向于使用使用`&str`，而不是`&String`

```rust
#[allow(unused)]
fn main() {
    let string: String = "hello".to_string();
    let borrow_string: &String = &string;
    let borrow_str: &str = string.as_str();
}
```

使用借用类型可以避免已经提供一层间接性的所有类型上的多层间接

`String`类型具有一层间接，因为`String`类型的本质是一个具有三个字段的胖指针分别是`ptr（指向在堆上的具体的内容）`、`cap（容量）`、`len`

`&String`具有两层引用，是因为是`String`的基础上，加了`&`，所以`borrow_string`指针`ptr`指向的是`string`，而不是堆上的内容；

`&str`类型也是一个胖指针，直接指向栈上的内容。`ptr`、`len`

## 对于`T`类型来说，应该倾向于 使用`&[T]`而不是`&Vec[T]`；应该倾向于使用`&T`而不是`&Box<T>`

如果函数希望读取某个类型的元素，那么它应该接收一个&[T]类型参数，而不是一个&Vec[T]类型参数。这样，调用方可以传递一个Vec[T]、数组等等。

```rust
#[allow(unused)]
fn print_elements(slice: &[i32]) {
    for &num in slice {
        println!("{}", num);
    }
}

#[allow(unused)]
fn main() {
    let vec = vec![1, 2, 3];
    let slice = &vec[..];
    
    print_elements(slice);
}
```

如果函数希望读取某个类型的可变元素，那么它应该接收一个&mut [T]类型参数，而不是一个&mut Vec[T]类型参数。这样，调用方可以传递一个Vec[T]、数组等等。

```rust
#[allow(unused)]
fn double_elements(slice: &mut [i32]) {
    for num in slice {
        *num *= 2;
    }
}

#[allow(unused)]
fn main() {
    let mut vec = vec![1, 2, 3];
    let slice = &mut vec[..];
    
    double_elements(slice);
    
    println!("{:?}", slice);
}
```

如果函数希望读取一个单独的元素，那么它应该接收一个&T类型参数，而不是一个&Box[T]类型参数。

```rust
#[allow(unused)]
fn print_element(num: &i32) {
    println!("{}", num);
}

#[allow(unused)]
fn main() {
    let num = Box::new(42);
    let b_num = &num;
    
    print_element(b_num);
}
```

使用借用类型有助于编写更具通用性的代码，因为它们允许调用方使用不同类型的值（例如Vec[T]、数组、Box[T]等）调用函数。这样可以提高代码的灵活性和可重用性。
