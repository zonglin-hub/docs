# POW(工作量证明)共识机制

POW（Proof of Work）是一种区块链共识机制技术，它是最早被使用的共识机制之一。POW机制的核心思想是通过计算来验证交易和生成新的区块。在POW机制中，区块链网络的参与者需要通过完成一定的难题，证明自己对于生成新区块的贡献，并获得一定的奖励。这个难题通常是一个哈希运算的过程，参与者需要不断尝试不同的输入，直到找到符合要求的输出。

POW机制存在的问题是算力竞争太过激烈，导致能源消耗过多，矿工垄断等问题。也因此，随着区块链技术的发展，越来越多的共识机制被提出和使用，如POS（Proof of Stake）、DPoS（Delegated Proof of Stake）等。

下面是一个使用Rust语言实现的POW机制的简单示例代码：

该示例代码中，我们使用Sha256哈希算法计算“Hello, world!”加上一个随机数（nonce）的哈希值，并且判断该哈希值的前3位是否都为0（即目标前缀），如果符合要求，则停止计算，并输出找到的nonce和哈希值。

[Examples](./examples/hello_sha256.rs)

下面是一个更复杂的示例代码，实现了一个简单的区块链，其中包括POW机制、交易记录等。

该示例代码中，我们实现了一个简单的区块链，其中包括了交易记录、POW机制、区块哈希值等。我们可以通过调用`add_transaction`方法添加交易记录，然后通过调用`mine_block`方法来挖矿产生新的区块。在`mine_block`方法中，我们先计算上一个区块的哈希值（如果是第一个区块，则哈希值为32个字节的0），然后创建一个新的区块，使用`find_hash`方法查找合法哈希值，并将合法哈希值及相应的nonce赋值给区块。在`find_hash`方法中，我们不断增加nonce，计算区块哈希值，并检查哈希值的前缀是否为目标前缀，如果符合要求，则返回相应的哈希值和nonce值。最后，我们通过序列化和反序列化方式将区块链和区块等保存为字节数组和结构体，并用println!宏输出区块链的信息。

[Examples](./examples/demo_transaction.rs)

## err

```rust
error[E0382]: borrow of moved value: `block`
  --> src\main.rs:67:46
   |
55 |         let mut block = Block {
   |             --------- move occurs because `block` has type `Block`, which does not implement the `Copy` trait
...
64 |         self.blocks.push(block);
   |                          ----- value moved here
...
67 |         println!("Mined block {} in {:?}\n", block.index, elapsed);
   |                                              ^^^^^^^^^^^ value borrowed here after move
   |
   = note: this error originates in the macro `$crate::format_args_nl` which comes from the expansion of the macro `println` (in Nightly builds, run with -Z macro-backtrace for more info)

For more information about this error, try `rustc --explain E0382`.
error: could not compile `pow` (bin "pow") due to previous error
```

这个错误是因为变量 `block` 被移动后被推入了 `self.blocks` 向量中，然后在后面被 `println!` 借用。由于移动使原始变量在内存中无效，因此借用失败。

要修复此错误，您可以在将其推入向量之前克隆 `Block`，如下所示：

```rust
self.blocks.push(block.clone());
```

这样，向量中添加了 `Block` 的新副本，原始副本仍然有效，因此稍后仍然可以打印它。

或者，您可以修改 `Block` 结构以实现 `Copy` trait，这允许复制其数据而不是移动。为此，在 `Block` 结构的定义上面添加 `#[derive(Copy, Clone)]`：

```rust
#[derive(Debug, PartialEq, Eq, Clone, Copy)]
struct Block {
    // ...
}
```
