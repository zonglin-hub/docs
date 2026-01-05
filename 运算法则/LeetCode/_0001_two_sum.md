
> _zonglin

```rust
pub fn two_sum(nums: Vec<i32>, target: i32) -> Vec<i32> {
    let mut hashtable = HashMap::new();
    for (i, num) in nums.iter().enumerate() {
        if let Some(&a) = hashtable.get(&(target - num)) {
            return vec![a, i as i32];
        }
        hashtable.insert(num, i as i32);
    }
    vec![]
}
```

生存代码流程图，ppt

> ChatGLM - AI

以下是您请求的代码流程图的图片链接，您可以将它插入到PPT中：

![代码流程图](474118a4-88de-4c0e-80bc-e5be4e049c34.png)

> _zonglin

能否解释下HashMap的用法

> ChatGLM - AI

当然可以。在Rust语言中，`HashMap` 是一种集合类型，它存储键值对（key-value pairs），并允许快速检索值（value）通过对应的键（key）。以下是 `HashMap` 的基本用法：

### 创建 `HashMap`

要使用 `HashMap`，首先需要引入 `std::collections::HashMap`。

```rust
use std::collections::HashMap;
```

然后，你可以创建一个新的空的 `HashMap`：

```rust
let mut map = HashMap::new();
```

### 插入键值对

使用 `insert` 方法来插入键值对：

```rust
map.insert(key, value);
```

这里的 `key` 和 `value` 必须是可哈希的（实现 `Hash` trait）和可比较的（实现 `Eq` trait）。

### 获取值

使用 `get` 方法来获取键对应的值：

```rust
let value = map.get(&key);
```

`get` 方法返回一个 `Option<&V>`，其中 `V` 是值的类型。如果键存在，则返回 `Some(&value)`，否则返回 `None`。

### 更新值

如果你想要更新一个键的值，你可以使用 `insert` 方法，它会覆盖旧的值。或者，你可以使用 `entry` 方法来更优雅地处理更新：

```rust
map.entry(key).or_insert(value);
```

这个方法会在键不存在时插入值，如果键已经存在，则不做任何操作。

### 遍历 `HashMap`

你可以使用 `for` 循环来遍历 `HashMap` 中的键值对：

```rust
for (key, value) in &map {
    println!("key: {}, value: {}", key, value);
}
```

### 示例

以下是一个简单的示例，展示了如何使用 `HashMap`：

```rust
use std::collections::HashMap;

fn main() {
    let mut scores = HashMap::new();
    scores.insert(String::from("Blue"), 10);
    scores.insert(String::from("Yellow"), 50);
    let team_name = String::from("Blue");
    let score = scores.get(&team_name).copied().unwrap_or(0);
    println!("Score for {}: {}", team_name, score);
    // 更新值
    scores.entry(String::from("Blue")).or_insert(50);
    scores.entry(String::from("Red")).or_insert(50);
    for (key, value) in &scores {
        println!("{}: {}", key, value);
    }
}
```

在这个例子中，我们创建了一个 `HashMap` 来存储队伍名称和分数，然后我们尝试获取“Blue”队伍的分数，如果不存在则默认为0。接着我们使用 `entry` 方法来更新或插入键值对。
记住，`HashMap` 的键必须是可哈希的，这意味着它们必须实现 `Hash` trait。同时，为了能够比较键是否相等，它们还必须实现 `Eq` trait。通常，如果你使用的是基本数据类型或者实现了这些 trait 的自定义类型，这些要求已经满足。
