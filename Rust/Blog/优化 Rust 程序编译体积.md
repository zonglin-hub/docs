# 优化 Rust 程序编译体积

- 原文地址：<https://bertptrs.nl/2023/04/27/how-does-async-rust-work.html>

---

> Note: 本文最后更新于 November 22, 2019，文中内容可能已经过时。

体积狂魔の执念，记录一下以免忘记。

**2019-11-12 更新：** 自这篇文章发表后已经过了一年，Rust 发生了许多变化，比如不再自带 Jemalloc 作为内存分配器。
因此更新一下文章，参考了一个新项目 [min-sized-rust] 进行了更多的优化。

[min-sized-rust]: https://github.com/johnthagen/min-sized-rust

首先记录一下编译器版本

```bash
❯ rustc --version
rustc 1.41.0-nightly (5c5b8afd8 2019-11-16)
```

这次不再采用 Hello，world 作为演示了，因为本身就没多少代码参考意义不大，
本文的目的不是探究一个 Rust 程序能做到多小，而是一个**实际的** Rust 项目可以优化到多小。

翻了一下以前的项目，决定使用 [ren3] 作为优化对象：规模恰到好处，有足够的代码，又没有太多依赖。

[ren3]: https://github.com/Aloxaf/ren3/

那么，开始我们的 _Optimization_ 吧！

## 使用 Release 构建

```bash
cargo build --release
```

最基本的优化体积方式之一，大家都懂的。Cargo 默认的编译模式是 dev ，该下没有进行任何优化，
而且附带了大量调试信息。dev 模式下的程序别说几十兆，上百兆都是有可能的……

| 编译模式 | 体积  | 减少量 |
| -------- | ----- | ------ |
| dev      | 26.5M | 100%   |
| release  | 4.70M | 17.7%  |

可以看到 dev 和 release 两者差距非常大，没有特殊说明的话，后文就采用 release 模式下的 4.70M 作为基准来进行对比了

## 使用 strip

```bash
strip -s target/release/ren3
```

最基本的优化体积方式之二，可以去除（对正常运行）无用的符号信息

| 优化方式 | 体积  | 减少量 |
| -------- | ----- | ------ |
| NO       | 4.70M | 100%   |
| strip    | 2.11M | 44.9%  |

效果同样非常明显，直接减少了一半。

## 调整优化等级

[默认的 release 优化等级为 3]，这个等级下编译器会进行循环展开之类的操作以体积膨胀为代价提高程序运行速度。
不过这次我们在优化体积，我们不需要以体积为代价的优化。因为我们调整优化等级为 `z`，意为最小二进制体积：

[默认的 release 优化等级为 3]: https://doc.rust-lang.org/cargo/reference/manifest.html#the-profile-sections

```toml
[profile.release]
opt-level = 'z'
```

| 优化方式                  | 体积  | 减少量 |
| ------------------------- | ----- | ------ |
| NO                        | 4.70M | 100%   |
| strip                     | 2.11M | 44.9%  |
| `opt-level = 'z'`         | 4.84M | 103%   |
| `opt-level = 'z'` + strip | 1.96M | 41.7%  |

emmm 非常尴尬的是未 strip 时的体积竟然略有增加，不过 strip 后体积确实再次减少了一点点，聊胜于无吧。

## 开启 LTO

LTO（Link Time Optimization），意为链接时优化。可以消除大量冗余代码，减小二进制体积——代价是更长的链接时间

```toml
[profile.release]
lto = true
```

| 优化方式                        | 体积  | 减少量 |
| ------------------------------- | ----- | ------ |
| NO                              | 4.70M | 100%   |
| strip                           | 2.11M | 44.9%  |
| `opt-level = 'z'`               | 4.84M | 103%   |
| `opt-level = 'z'` + strip       | 1.96M | 41.7%  |
| `opt-level = 'z'` + LTO         | 2.79M | 59.4%  |
| `opt-level = 'z'` + LTO + strip | 1.56M | 33.2%  |

体积减小非常明显！

## 调整并行代码生成单元数量

Cargo 默认会启用 16 个并行代码生成单元，对编译速度有提升，但是会妨碍某些优化的进行。我们限制到 1

```toml
[profile.release]
codegen-units = 1
```

| 优化方式                                        | 体积  | 减少量 |
| ----------------------------------------------- | ----- | ------ |
| NO                                              | 4.70M | 100%   |
| strip                                           | 2.11M | 44.9%  |
| `opt-level = 'z'`                               | 4.84M | 103%   |
| `opt-level = 'z'` + strip                       | 1.96M | 41.7%  |
| `opt-level = 'z'` + `codegen-units = 1`         | 2.62M | 55.7%  |
| `opt-level = 'z'` + `codegen-units = 1` + strip | 1.49M | 31.7%  |

## Panic 时立刻终止

> 前面的优化对程序的行为都不会产生任何影响，然而这个优化会。

众所周知，Rust 程序在 panic 时会生成栈回溯，方便定位问题。而这个 flag 会禁用这种行为——请自行权衡使用。

```toml
[profile.release]
panic = 'abort'
```

| 优化方式                                      | 体积  | 减少量 |
| --------------------------------------------- | ----- | ------ |
| NO                                            | 4.70M | 100%   |
| strip                                         | 2.11M | 44.9%  |
| `opt-level = 'z'`                             | 293KB | 6.09%  |
| `opt-level = 'z'` + strip                     | 210KB | 4.36%  |
| `opt-level = 'z'` + `panic = 'abort'`         | 2.44M | 52.0%  |
| `opt-level = 'z'` + `panic = 'abort'` + strip | 1.40M | 29.8%  |

> Note: 编译方式 release

## 最小化依赖

> 上面的优化都是只用调整参数就能完成的优化，然而这个优化可能需要你改动源码 但是，如果做方法得当的话，这个可能会是效果最明显的方式

Rust 的中心化包管理系统用起来爽到不行，但是用外部库用起来太方便也带来了一个问题：一个小程序动不动就会带上上百个依赖……这体积怎么可能不大嘛

所以最小化你的依赖也是一个减小体积的重要方式

先看一下这个项目当前的依赖：

```toml
[dependencies]
regex = "1.3.1"
clap = { version = "2.33.0", features = ["yaml"] }
colored = "1.9.0"
```

很少，只有三个，但是仍然有优化空间。

### 去除不必要的依赖

首先使用命令 `cargo deps | dot -Tpng > dep.png`，可以将当前依赖关系绘制成一张图。需要用到 `cargo-deps` 和 `graphviz`

这里面看起来最 “庞大” 的是 clap。

不过这个图也未必准确，因为 Rust 拥有条件编译，这里列出的依赖并不一定会全部用到。
比如 `winconsole` 就只会在 Windows 上被编译。所以我们还要使用另一个工具 `cargo-bloat`（为了方便看出结果，这里没有启用前面的优化）

```bash
❯ cargo bloat --release --crates
...
    Finished release [optimized] target(s) in 1m 06s
    Analyzing /home/aloxaf/.cache/cargo-build/release/ren3

 File  .text     Size Crate
 7.3%  27.3% 351.7KiB clap
 4.8%  17.8% 229.2KiB regex
 4.7%  17.6% 226.4KiB regex_syntax
 4.1%  15.2% 195.3KiB std
 2.4%   9.1% 116.5KiB yaml_rust
 1.5%   5.5%  70.9KiB aho_corasick
 0.7%   2.8%  35.5KiB [Unknown]
 0.4%   1.4%  17.5KiB ren3
 0.2%   0.7%   8.8KiB colored
 0.1%   0.3%   3.6KiB thread_local
 0.1%   0.2%   3.2KiB ansi_term
 0.1%   0.2%   3.2KiB memchr
 0.0%   0.2%   2.4KiB strsim
 0.0%   0.1%   1.5KiB textwrap
 0.0%   0.0%      28B atty
26.7% 100.0%   1.3MiB .text section size, the file size is 4.7MiB

Note: numbers above are a result of guesswork. They are not 100% correct and never will be.
```

使用 cargo-bloat 查看各个 crate 所占体积，可以看到 clap 和 regex 加起来占了一半。

考虑到这个工具的命令行参数并不复杂，并不需要使用 clap 这种庞大的命令行参数解析库。所以要做的首先就是将 clap 去掉，换成更基础的 [getopts]。

[getopts]: https://crates.io/crates/getopts

换成 getopts 后再编译，体积再次小幅减小！

| 优化方式                            | 体积  | 减少量 |
| ----------------------------------- | ----- | ------ |
| NO                                  | 4.70M | 100%   |
| strip                               | 2.11M | 44.9%  |
| `opt-level = 'z'`                   | 293KB | 6.09%  |
| `opt-level = 'z'` + strip           | 210KB | 4.36%  |
| `opt-level = 'z'` + rm clap         | 1.96M | 41.8%  |
| `opt-level = 'z'` + rm clap + strip | 1.06M | 22.6%  |

> Note: 编译方式 release

### 禁用不必要的 feature

这是不少人都会忽略的一点，很多 crate 默认会启用不少 feature。有些功能可能你根本没有用到，却被引入了。（这也是 cargo-deps 存在的意义）

如果这个 crate 是项目的直接依赖还好，可以在 Cargo.toml 里禁用不需要的 feature。就怕这个 crate 并不是项目的直接依赖。

举一个例子，[imageproc#344]：imageproc 依赖 image，并且没有禁用默认 feature，而 image 默认启用了对所有图片格式的支持。
这就导致了任何使用了 imageproc 的项目都会附带一个开启了所有 feature 的 image。
即使你在自己的 Cargo.toml 中禁用这些 feature 也无济于事，因为 feature 是取**并集**的。
如果你也遇到了这种情况建议给直接库作者提 issue 或者 PR。

[imageproc#344]: https://github.com/image-rs/imageproc/issues/344

回到我们的项目中来，getopts 和 colored 都十分精简，没有多余的 feature，但 regex 还是有可优化之处的。
阅读 regex 的 [features 列表]，发现默认启用了 `["std", "perf", "unicode"]`

[features 列表]: https://github.com/rust-lang/regex/blob/master/Cargo.toml#L37-L105

- std，暂且不谈，而且看描述这和 feature 目前对于 regex 来说是必需的
- perf，提供性能优化
- unicode，顾名思义，提供完整的 Unicode 支持比如 `\p{Letter}`、`\p{Emoji}`

作为一个简单的文件夹改名工具，并不会用到复杂的正则，也不需要匹配奇怪的 Unicode 字符。所以我们可以禁用这两个 feature

```toml
[dependencies]
regex = { version = "1.3.1", default-features = false, features = ["std"] }
```

| 优化方式                                   | 体积  | 减少量 |
| ------------------------------------------ | ----- | ------ |
| NO                                         | 4.70M | 100%   |
| strip                                      | 2.11M | 44.9%  |
| `opt-level = 'z'`                          | 293KB | 6.09%  |
| `opt-level = 'z'` + strip                  | 210KB | 4.36%  |
| `opt-level = 'z'` + forbid feature         | 1.26M | 26.8%  |
| `opt-level = 'z'` + forbid feature + strip | 0.42M | 8.94%  |

> Note: 编译方式 release

巨大进步！strip 后的程序体积终于降低到了 KB 级别！！

## libstd 优化

> 终于要对 libstd 下手了

Rust 的工具链自带了预编译的标准库，这样开发者就不用在每次编译 Rust 程序的时候都编译一遍 libstd，而是直接把 libstd 静态链接进去就行。

好处是很明显的，然而坏处也是很明显的：

预编译的 libstd 着重优化速度而不是体积
即使是 LTO 也无法移除 libstd 中的某些我们用不到的代码
这时就轮到 [Xargo] 出场了——一个可以方便地为你的程序单独编译 libstd 的工具。

[Xargo]: https://github.com/japaric/xargo

在项目根目录下创建 `Xargo.toml`（不需要删掉原来的 `Cargo.toml`）

```toml
[dependencies]
std = { default-features = false }
```

然后编译（target 请自行调整）

```bash
xargo build --target x86_64-unknown-linux-gnu --release
```

| 优化方式                               | 体积  | 减少量 |
| -------------------------------------- | ----- | ------ |
| NO                                     | 4.70M | 100%   |
| strip                                  | 2.11M | 44.9%  |
| `opt-level = 'z'`                      | 293KB | 6.09%  |
| `opt-level = 'z'` + strip              | 210KB | 4.36%  |
| `opt-level = 'z'` + cutout std         | 378KB | 7.85%  |
| `opt-level = 'z'` + cutout std + strip | 266KB | 5.53%  |

> Note: 编译方式 release

### 移除 panic 相关字符串

即使已经在 `Cargo.toml` 指定了 `panic = 'abort'`，`rustc` 默认还是会生成相关的格式化字符串。
我们可以通过 feature `panic_immediate_abort` 来禁止这个行为

```toml
[dependencies]
std = { default-features = false, features=[ "panic_immediate_abort" ] }
```

| 优化方式                                    | 体积  | 减少量 |
| ------------------------------------------- | ----- | ------ |
| NO                                          | 4.70M | 100%   |
| strip                                       | 2.11M | 44.9%  |
| `opt-level = 'z'`                           | 293KB | 6.09%  |
| `opt-level = 'z'` + strip                   | 210KB | 4.36%  |
| `opt-level = 'z'` + `panic_immediate_abort` | 293KB | 6.09%  |
| `opt-level = 'z'` + `panic_immediate_abort` | 266KB | 4.36%  |

> Note: 编译方式 release

## UPX 压缩

```bash
upx -9 target/x86_64-unknown-linux-gnu/release/ren3
```

| 优化方式                  | 体积   | 减少量 |
| ------------------------- | ------ | ------ |
| NO                        | 4.70M  | 100%   |
| strip                     | 2.11M  | 44.9%  |
| `opt-level = 'z'`         | 293KB  | 6.09%  |
| `opt-level = 'z'` + strip | 210KB  | 4.36%  |
| `opt-level = 'z'` + upx   | 98.8KB | 2.06%  |

> Note: 编译方式 release

## 结尾

从 4.70MB 到 98.8 KB，非常可以了。我已经没有遗憾了（

虽然再往后还可以使用 `#![no_std]` 直接去除 libstd，不过这个限制太大，只有你使用的所有的 crate 都支持 nostd 时才能这样做，
而且限制了大量 feature。一般只有嵌入式项目或者 wasm 项目中才会用到，而我想要的是对与大部分 Rust 项目来说都能使用的技巧，所以就不谈这个了。

> 文章作者: Aloxaf \
> 上次更新: 2019-11-22 \
> 许可协议: [知识共享署名-非商业性使用 4.0 国际许可协议]

[知识共享署名-非商业性使用 4.0 国际许可协议]: https://creativecommons.org/licenses/by-nc-nd/4.0/
