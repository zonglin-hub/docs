# 集合当成智能指针

通过为集合实现 `Deref trait`，提供其拥有和借用的数据视图。

Vec是一个拥有T的集合，然后通过实现Deref完成&Vec到&[T]的隐式解引用，从而提供借用T的集合（即&[T]）

```rust
#[stable(feature = "rust1", since = "1.0.0")]
unsafe impl<#[may_dangle] T, A: Allocator> Drop for Vec<T, A> {
    fn drop(&mut self) {
        unsafe {
            // 对 [T] 使用 drop，使用原始切片将 vector 的元素称为最弱必要类型；
            //
            // 在某些情况下可以避免有效性问题
            ptr::drop_in_place(ptr::slice_from_raw_parts_mut(self.as_mut_ptr(), self.len))
        }
        // RawVec 处理重新分配
    }
}
```

`Vec` 提供拥有T的集合，`&[T]` 提供借用`T`的集合。大部分情况下，只需要借用视图，提供两种方式，让用户在使用时在借用和拥有之间做出选择。

在 Rust 中，集合类型（如Vec[T]、String等）被视为智能指针。它们可以自动管理在堆上分配的内存，并在其所有权范围结束时自动释放该内存。下面是一个示例，展示了Vec[T]集合类型如何在其所有权范围结束时释放其内存。

```rust
struct Person {
    name: String,
    age: i32,
}

impl Person {
    fn new(name: String, age: i32) -> Self {
        Self { name, age }
    }
}

fn main() {
    let people = vec![
        Person::new("Alice".to_string(), 32),
        Person::new("Bob".to_string(), 28),
        Person::new("Charlie".to_string(), 75),
    ];
    
    for person in people.iter() {
        println!("{} is {} years old.", person.name, person.age);
    }
    
    println!("Done!");
} // 所有权范围结束，people集合释放其内存
```

在这个例子中，我们创建了一个包含三个Person的Vec[Person]集合。然后，我们遍历这个集合，并打印每个人的姓名和年龄。最后，我们结束了main函数，这导致people集合的所有权范围结束，并释放其在堆上分配的内存。Rust 借助所有权系统和集合类型的智能指针功能，确保我们不会遗漏释放内存的情况。

需要注意的是，这种智能指针功能仅在集合类型的所有权范围结束时起作用。如果我们意外地在集合之外保留了一个元素的引用，那么该元素的内存将不会被释放，这可能会导致内存泄漏。因此，在编写 Rust 代码时，需要注意集合元素的所有权范围，并确保本应释放的内存被正确释放。
