
# `?` 简介

`?`是一个语法糖，用于在函数返回值类型为 `Result` 的情况下，简化错误处理的写法。当我们使用 `?` 语法糖来处理 `Result` 类型时，它会默认执行一次 `match` 语句，若返回了 `Err`，则直接将该 `Err` 作为整个函数的返回值。若返回了 `Ok`，则取出 `Ok` 中的值，并将其返回。这样，我们便可以省去不必要的 `match` 语句，节省了时间和代码量。但是需要注意的是，使用 `?` 只能在返回值类型为 `Result` 的函数中使用。

当函数返回值类型为`Result`的时候，使用`?`可以大大减少代码量，例如下面这个例子：

```rust
fn parse_input(input: &str) -> Result<i32, ParseIntError> {
    let parsed = input.parse::<i32>()?;
    Ok(parsed)
}
```

在这里，我们只需要在 `input.parse::<i32>()` 后面加上一个 `?`，如果出现了错误，它会直接返回错误值。另外，在这里，`ParseIntError` 类型是标准库 `std::num::ParseIntError` 的别名。

再看下一个例子：

```rust
fn read_file_content(file_path: &str) -> Result<String, std::io::Error> {
    let mut file = File::open(file_path)?;
    let mut content = String::new();
    file.read_to_string(&mut content)?;
    Ok(content)
}
```

这个例子中，我们打开一个文件，从中读取内容并返回它。在文件打开和读取时，都可以使用 `?` 来简化代码。

让我举例说明。

假设有一个函数`divide`，它接受两个`i32`参数并返回它们的商，除数不能为0，如果除数为0，则返回一个错误。

不使用`?`的代码如下：

```rust
fn divide(a: i32, b: i32) -> Result<i32, String> {
    if b == 0 { return Err("Cannot divide by zero".to_string()); }
    Ok(a / b)
}

fn main() {
    let result = divide(10, 0);
    match result {
        Ok(v) => println!("{}", v),
        Err(e) => println!("Error: {}", e),
    }
}
```

这里我们需要编写一个`match`语句来处理`Result`类型的返回值，其中`Ok`分支表示操作成功，`Err`分支表示出现错误。在`Err`分支中，我们需要编写错误处理代码。

使用`?`的代码如下：

```rust
fn divide(a: i32, b: i32) -> Result<i32, String> {
    if b == 0 { return Err("Cannot divide by zero".to_string()); }
    Ok(a / b)
}

fn main() -> Result<(), String> {
    let result = divide(10, 0)?;
    println!("{}", result);
    Ok(())
}
```

使用`?`可以使代码更加清晰简洁，只需要在函数调用的末尾加上一个`?`即可。如果函数返回一个`Err`，它会直接返回，否则它会返回包装在`Ok`中的值。我们还可以看到，我们不需要一个`match`语句来处理错误，而是可以使用`?`在函数调用之间进行链式调用。最后，我们需要在`main`函数中返回一个`Result`类型的值，这里我使用了一个简单的空元组`()`。

## `?` 和 `unwrap()` 区别

在 Rust 中，`?`和`unwrap()`都可以用来处理 `Result` 类型的值，但它们之间有着重要的区别。

- `?`：如果在函数中使用 `?`，如果函数返回一个 `Err`，那么这个错误会被返回给调用函数，并且它的类型会与当前函数的返回类型相同（通常是`Result`）。这意味着在使用 `?` 时，你必须声明当前函数的返回类型，以便编译器知道如何返回错误。

使用`?`的优点是它使代码更加简洁，对于错误处理代码的重复性更少。然而，它的缺点是它只适用于函数返回`Result`类型的情况。

- `unwrap()`：如果我们使用`unwrap()`，它会将`Result`类型的值解包并返回其中的`Ok`值，如果`Result`为`Err`，则会产生一个运行时错误并使程序崩溃。因此，我们通常只在我们非常确定`Result`是`Ok`时使用`unwrap()`。如果我们使用`unwrap()`并且`Result`实际上是一个`Err`，那么程序将会崩溃。所以，使用`unwrap()`需要非常小心。

总之，我们应当尽可能地使用`?`来处理错误，只有当我们非常确定`Result`是`Ok`时才使用`unwrap()`。这样可以使我们的代码更加健壮和稳定。

以下是一个使用?的示例代码：

```rust
use std::fs::File;
use std::io::{self, Read};

fn read_file_contents(file_path: &str) -> io::Result<String> {
    let mut file = File::open(file_path)?;
    let mut contents = String::new();
    file.read_to_string(&mut contents)?;
    Ok(contents)
}

fn main() {
    let file_path = "example.txt";
    match read_file_contents(file_path) {
        Ok(contents) => println!("{}", contents),
        Err(error) => println!("Failed to read {}: {}", file_path, error),
    }
}
```

在上述代码中，我们使用了?运算符来处理读取文件时的错误。如果在文件打开或读取期间发生错误，?会将错误返回给调用函数，并在main函数中打印出错误信息。

下面是一个使用unwrap()的示例代码：

```rust
fn divide(a: i32, b: i32) -> i32 {
    if b == 0 {
        panic!("Cannot divide by zero!");
    } else {
        a / b
    }
}

fn main() {
    let a = 10;
    let b = 2;
    let result = divide(a, b).unwrap();
    println!("{} / {} = {}", a, b, result);
}
```

在上述代码中，我们使用了 `unwrap()` 来解包函数 `divide` 返回的值。在这种情况下，我们确信 `b` 不会为零，所以我们可以用 `unwrap()` 来获取 `divide` 的返回值。然而，如果 `b` 为零，程序将会崩溃并打印 `"Cannot divide by zero!"`。因此，我们需要小心使用 `unwrap()`。
