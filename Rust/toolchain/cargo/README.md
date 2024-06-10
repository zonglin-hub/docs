# cargo -h

```text
$ cargo
Rust 的包管理器

用法： cargo [+toolchain] [OPTIONS] [COMMAND]
      cargo [+toolchain] [OPTIONS] -Zscript <MANIFEST_RS> [ARGS]...

选项：
  -V, --version             打印版本信息并退出
      --list                列出已安装的命令
      --explain <CODE>      提供有关 rustc 错误消息的详细解释
  -v, --verbose...          使用详细输出 (-vv 非常详细/build.rs 输出)
  -q, --quiet               不打印 cargo 日志消息
      --color <WHEN>        色彩：自动，总是，从不
  -C <DIRECTORY>            在执行任何操作之前切换到 DIRECTORY (仅限夜间版)
      --frozen              要求 Cargo.lock 和缓存是最新的
      --locked              要求 Cargo.lock 是最新的
      --offline             不访问网络的情况下运行
      --config <KEY=VALUE>  覆盖一个配置值
  -Z <FLAG>                 不稳定的(仅限夜间版)标志到 Cargo，请参阅 'cargo -Z help' 以获取详细信息
  -h, --help                打印帮助

命令：
    build, b    编译当前包
    check, c    分析当前包并报告错误，但不编译目标文件
    clean       删除目标目录
    doc, d      构建此包及其依赖项的文档
    new         创建一个新的 cargo 包
    init        在现有目录中创建一个新的 cargo 包
    add         添加依赖到清单文件
    remove      从清单文件中删除依赖
    run, r      运行本地包的二进制文件或示例
    test, t     运行测试
    bench       运行基准测试
    update      更新 Cargo.lock 中列出的依赖关系
    search      在注册表中搜索 crates
    publish     将此包包装并上传到注册表
    install     安装 Rust 二进制文件。默认位置是 $HOME/.cargo/bin
    uninstall   卸载 Rust 二进制文件
    ...         使用 --list 查看所有命令

有关特定命令的更多信息，请参阅 'cargo help <命令>'。
```

<p>&nbsp;</p>

## cargo build -h

```text
$ cargo build -h
编译本地包及其所有依赖项

使用方法：cargo.exe build [OPTIONS]

选项：
      --ignore-rust-version     忽略包中的 `rust-version` 规格说明
      --future-incompat-report  在构建结束时输出将来的不兼容性报告
      --message-format <FMT>    错误格式
  -q, --quiet                   不打印 cargo 日志消息
  -v, --verbose...              使用详细输出（-vv 非常详细/build.rs 输出）
      --color <WHEN>            色彩：自动，始终，从不
      --config <KEY=VALUE>      覆盖一个配置值
  -Z <FLAG>                     不稳定的（仅限夜间）标志到 Cargo，详情请见 'cargo -Z help'
  -h, --help                    打印帮助信息

包选择：
  -p, --package [<SPEC>]  要构建的包（参见 `cargo help pkgid`）
      --workspace         构建工作区内的所有包
      --exclude <SPEC>    从构建中排除包
      --all               --workspace 的别名（已弃用）

目标选择：
      --lib               仅构建此包的库
      --bins              构建所有可执行文件
      --bin [<NAME>]      仅构建指定的可执行文件
      --examples          构建所有示例
      --example [<NAME>]  仅构建指定的示例
      --tests             构建所有测试目标
      --test [<NAME>]     仅构建指定的测试目标
      --benches           构建所有基准测试目标
      --bench [<NAME>]    仅构建指定的基准测试目标
      --all-targets       构建所有目标

特性选择：
  -F, --features <FEATURES>  空格或逗号分隔的激活特性列表
      --all-features         激活所有可用特性
      --no-default-features  不激活 `default` 特性

编译选项：
  -r, --release                 在发布模式下构建工件，并进行优化
      --profile <PROFILE-NAME>  使用指定配置文件构建工件
  -j, --jobs <N>                并行作业的数量，默认为 CPU 的数量。
      --keep-going              在出现错误时不立即终止构建
      --target [<TRIPLE>]       为目标三重奏构建
      --target-dir <DIRECTORY>  所有生成工件的目录
      --out-dir <PATH>          将最终工件复制到此目录（不稳定）
      --build-plan              以 JSON 格式输出构建计划（不稳定）
      --unit-graph              以 JSON 格式输出构建图（不稳定）
      --timings[=<FMTS>]        定时输出格式（不稳定）（逗号分隔）：html, json

清单选项：
      --manifest-path <PATH>  Cargo.toml 的路径
      --frozen                需要 Cargo.lock 和缓存是最新的
      --locked                需要 Cargo.lock 是最新的
      --offline               不访问网络运行

运行 `cargo help build` 以获取更详细的信息。
```

<p>&nbsp;</p>

## cargo check -h

```text
$ cargo check -h
检查本地包及其所有依赖项是否存在错误

使用方法：cargo.exe check [OPTIONS]

选项：
      --ignore-rust-version     忽略包中的 `rust-version` 规格说明
      --future-incompat-report  在构建结束时输出将来的不兼容性报告
      --message-format <FMT>    错误格式
  -q, --quiet                   不打印 cargo 日志消息
  -v, --verbose...              使用详细输出（-vv 非常详细/build.rs 输出）
      --color <WHEN>            色彩：自动，始终，从不
      --config <KEY=VALUE>      覆盖一个配置值
  -Z <FLAG>                     不稳定的（仅限夜间）标志到 Cargo，详情请见 'cargo -Z help'
  -h, --help                    打印帮助信息

包选择：
  -p, --package [<SPEC>]  要检查的包（s）
      --workspace         检查工作区内的所有包
      --exclude <SPEC>    从检查中排除包
      --all               --workspace 的别名（已弃用）

目标选择：
      --lib               仅检查此包的库
      --bins              检查所有可执行文件
      --bin [<NAME>]      仅检查指定的可执行文件
      --examples          检查所有示例
      --example [<NAME>]  仅检查指定的示例
      --tests             检查所有测试目标
      --test [<NAME>]     仅检查指定的测试目标
      --benches           检查所有基准测试目标
      --bench [<NAME>]    仅检查指定的基准测试目标
      --all-targets       检查所有目标

特性选择：
  -F, --features <FEATURES>  空格或逗号分隔的激活特性列表
      --all-features         激活所有可用特性
      --no-default-features  不激活 `default` 特性

编译选项：
  -j, --jobs <N>                并行作业的数量，默认为 CPU 的数量。
      --keep-going              在出现错误时不立即终止构建
  -r, --release                 在发布模式下检查工件，并进行优化
      --profile <PROFILE-NAME>  使用指定配置文件检查工件
      --target [<TRIPLE>]       为目标三重奏检查
      --target-dir <DIRECTORY>  所有生成工件的目录
      --unit-graph              以 JSON 格式输出构建图（不稳定）
      --timings[=<FMTS>]        定时输出格式（不稳定）（逗号分隔）：html, json

清单选项：
      --manifest-path <PATH>  Cargo.toml 的路径
      --frozen                需要 Cargo.lock 和缓存是最新的
      --locked                需要 Cargo.lock 是最新的
      --offline               不访问网络运行

运行 `cargo help check` 以获取更详细的信息。
```

<p>&nbsp;</p>

## cargo clean -h

```text
$ cargo clean -h
删除 cargo 过去生成的所有工件

使用方法：cargo.exe clean [OPTIONS]

选项：
      --doc                 是否仅清理文档目录
  -q, --quiet               不打印 cargo 日志消息
  -n, --dry-run             显示将被删除的内容，但实际不删除任何文件
  -v, --verbose...          使用详细输出（-vv 非常详细/build.rs 输出）
      --color <WHEN>        色彩：自动，始终，从不
      --config <KEY=VALUE>  覆盖一个配置值
  -Z <FLAG>                 不稳定的（仅限夜间）标志到 Cargo，详情请见 'cargo -Z help'
  -h, --help                打印帮助信息

包选择：
  -p, --package [<SPEC>]  要清理工件的包

编译选项：
  -r, --release                 是否清理发布工件
      --profile <PROFILE-NAME>  清理指定配置文件的工件
      --target [<TRIPLE>]       清理输出目标三重奏
      --target-dir <DIRECTORY>  所有生成工件的目录

清单选项：
      --manifest-path <PATH>  Cargo.toml 的路径
      --frozen                需要 Cargo.lock 和缓存是最新的
      --locked                需要 Cargo.lock 是最新的
      --offline               不访问网络运行

运行 `cargo help clean` 以获取更详细的信息。
```

<p>&nbsp;</p>

## cargo doc -h

```text
$ cargo doc -h
构建包的文档

使用方法：cargo.exe doc [OPTIONS]

选项：
      --open                    操作完成后在浏览器中打开文档
      --no-deps                 不为依赖项构建文档
      --document-private-items  文档私有项
      --ignore-rust-version     忽略包中的 `rust-version` 规格说明
      --message-format <FMT>    错误格式
  -q, --quiet                   不打印 cargo 日志消息
  -v, --verbose...              使用详细输出（-vv 非常详细/build.rs 输出）
      --color <WHEN>            色彩：自动，始终，从不
      --config <KEY=VALUE>      覆盖一个配置值
  -Z <FLAG>                     不稳定的（仅限夜间）标志到 Cargo，详情请见 'cargo -Z help'
  -h, --help                    打印帮助信息

包选择：
  -p, --package [<SPEC>]  要文档化的包
      --workspace         文档化工作区内的所有包
      --exclude <SPEC>    从构建中排除包
      --all               --workspace 的别名（已弃用）

特性选择：
  -F, --features <FEATURES>  空格或逗号分隔的激活特性列表
      --all-features         激活所有可用特性
      --no-default-features  不激活 `default` 特性

目标选择：
      --lib               仅文档化此包的库
      --bins              文档化所有可执行文件
      --bin [<NAME>]      仅文档化指定的可执行文件
      --examples          文档化所有示例
      --example [<NAME>]  仅文档化指定的示例

编译选项：
  -j, --jobs <N>                并行作业的数量，默认为 CPU 的数量。
      --keep-going              出现错误时不立即终止构建
  -r, --release                 在发布模式下构建工件，并进行优化
      --profile <PROFILE-NAME>  使用指定配置文件构建工件
      --target [<TRIPLE>]       为目标三重奏构建
      --target-dir <DIRECTORY>  所有生成工件的目录
      --unit-graph              以 JSON 格式输出构建图（不稳定）
      --timings[=<FMTS>]        定时输出格式（不稳定）（逗号分隔）：html, json

清单选项：
      --manifest-path <PATH>  Cargo.toml 的路径
      --frozen                需要 Cargo.lock 和缓存是最新的
      --locked                需要 Cargo.lock 是最新的
      --offline               不访问网络运行

运行 `cargo help doc` 以获取更详细的信息。
```

<p>&nbsp;</p>

## cargo new -h

```text
$ cargo new -h
在 <path> 创建一个新的 cargo 包

使用方法：cargo.exe new [OPTIONS] <PATH>

参数：
  <PATH>

选项：
      --vcs <VCS>            为给定的版本控制系统初始化一个新仓库，覆盖全局配置。[可能值：git, hg, pijul, fossil, none]
      --bin                  使用二进制（应用程序）模板 [默认]
      --lib                  使用库模板
      --edition <YEAR>       设置生成 crate 的版次 [可能值：2015, 2018, 2021, 2024]
      --name <NAME>          设置结果包的名称，默认为目录名称
      --registry <REGISTRY>  使用注册表
  -q, --quiet                不打印 cargo 日志消息
  -v, --verbose...           使用详细输出（-vv 非常详细/build.rs 输出）
      --color <WHEN>         色彩：自动，始终，从不
      --config <KEY=VALUE>   覆盖一个配置值
  -Z <FLAG>                  不稳定的（仅限夜间）标志到 Cargo，详情请见 'cargo -Z help'
  -h, --help                 打印帮助信息

清单选项：
      --frozen   需要 Cargo.lock 和缓存是最新的
      --locked   需要 Cargo.lock 是最新的
      --offline  不访问网络运行

运行 `cargo help new` 以获取更详细的信息。
```

<p>&nbsp;</p>

## cargo init -h

```text
$ cargo init -h
在现有目录中创建一个新的 cargo 包

使用方法：cargo.exe init [OPTIONS] [PATH]

参数：
  [PATH]  [默认：.]

选项：
      --vcs <VCS>            为给定的版本控制系统初始化一个新仓库，覆盖全局配置。[可能值：git, hg, pijul, fossil, none]
      --bin                  使用二进制（应用程序）模板 [默认]
      --lib                  使用库模板
      --edition <YEAR>       设置生成 crate 的版次 [可能值：2015, 2018, 2021, 2024]
      --name <NAME>          设置结果包的名称，默认为目录名称
      --registry <REGISTRY>  使用注册表
  -q, --quiet                不打印 cargo 日志消息
  -v, --verbose...           使用详细输出（-vv 非常详细/build.rs 输出）
      --color <WHEN>         色彩：自动，始终，从不
      --config <KEY=VALUE>   覆盖一个配置值
  -Z <FLAG>                  不稳定的（仅限夜间）标志到 Cargo，详情请见 'cargo -Z help'
  -h, --help                 打印帮助信息

清单选项：
      --frozen   需要 Cargo.lock 和缓存是最新的
      --locked   需要 Cargo.lock 是最新的
      --offline  不访问网络运行

运行 `cargo help init` 以获取更详细的信息。
```

<p>&nbsp;</p>

## cargo add -h

```text
$ cargo add -h
向 Cargo.toml 清单文件中添加依赖项

使用方法：cargo add [OPTIONS] <DEP>[@<VERSION>] ...
        cargo add [OPTIONS] --path <PATH> ...
        cargo add [OPTIONS] --git <URL> ...

参数：
  [DEP_ID]...  要作为依赖项添加的包的引用

选项：
      --no-default-features  禁用默认特性
      --default-features     重新启用默认特性
  -F, --features <FEATURES>  要激活的特性的空格或逗号分隔列表
      --optional             将依赖项标记为可选
      --no-optional          将依赖项标记为必需
      --rename <NAME>        重命名依赖项
      --ignore-rust-version  忽略包中的 `rust-version` 规格说明（不稳定）
  -n, --dry-run              实际不写入清单
  -q, --quiet                不打印 cargo 日志消息
  -v, --verbose...           使用详细输出（-vv 非常详细/build.rs 输出）
      --color <WHEN>         色彩：自动，始终，从不
      --config <KEY=VALUE>   覆盖一个配置值
  -Z <FLAG>                  不稳定的（仅限夜间）标志到 Cargo，详情请见 'cargo -Z help'
  -h, --help                 打印帮助信息（更多内容请使用 '--help'）

清单选项：
      --manifest-path <PATH>  Cargo.toml 的路径
      --frozen                需要 Cargo.lock 和缓存是最新的
      --locked                需要 Cargo.lock 是最新的
      --offline               不访问网络运行

包选择：
  -p, --package [<SPEC>]  要修改的包

来源：
      --path <PATH>      本地 crate 的文件系统路径
      --git <URI>        Git 仓库位置
      --branch <BRANCH>  从 Git 分支下载 crate
      --tag <TAG>        从 Git 标签下载 crate
      --rev <REV>        从 Git 引用下载 crate
      --registry <NAME>  此依赖项的包注册表

节：
      --dev              作为开发依赖项添加
      --build            作为构建依赖项添加
      --target <TARGET>  作为给定目标平台的依赖项添加

运行 `cargo help add` 以获取更详细的信息。
```

<p>&nbsp;</p>

## cargo remove -h

```text
$ cargo remove -h
从 Cargo.toml 清单文件中移除依赖项

使用方法：cargo.exe remove [OPTIONS] <DEP_ID>...

参数：
  <DEP_ID>...  要移除的依赖项

选项：
  -n, --dry-run             实际不写入清单
  -q, --quiet               不打印 cargo 日志消息
  -v, --verbose...          使用详细输出（-vv 非常详细/build.rs 输出）
      --color <WHEN>        色彩：自动，始终，从不
      --config <KEY=VALUE>  覆盖一个配置值
  -Z <FLAG>                 不稳定的（仅限夜间）标志到 Cargo，详情请见 'cargo -Z help'
  -h, --help                打印帮助信息

节：
      --dev              从 dev-dependencies 中移除
      --build            从 build-dependencies 中移除
      --target <TARGET>  从 target-dependencies 中移除

包选择：
  -p, --package [<SPEC>]  要从中移除的包

清单选项：
      --manifest-path <PATH>  Cargo.toml 的路径
      --frozen                需要 Cargo.lock 和缓存是最新的
      --locked                需要 Cargo.lock 是最新的
      --offline               不访问网络运行

运行 `cargo help remove` 以获取更详细的信息。
```

<p>&nbsp;</p>

## cargo run -h

```text
$ cargo run -h
运行本地包的二进制文件或示例

使用方法：cargo.exe run [OPTIONS] [ARGS]...

参数：
  [ARGS]...  要运行的二进制文件或示例的参数

选项：
      --ignore-rust-version   忽略包中的 `rust-version` 规格说明
      --message-format <FMT>  错误格式
  -q, --quiet                 不打印 cargo 日志消息
  -v, --verbose...            使用详细输出（-vv 非常详细/build.rs 输出）
      --color <WHEN>          色彩：自动，始终，从不
      --config <KEY=VALUE>    覆盖一个配置值
  -Z <FLAG>                   不稳定的（仅限夜间）标志到 Cargo，详情请见 'cargo -Z help'
  -h, --help                  打印帮助信息

包选择：
  -p, --package [<SPEC>]  要运行的包的目标

目标选择：
      --bin [<NAME>]      要运行的二进制目标的名称
      --example [<NAME>]  要运行的示例目标的名称

特性选择：
  -F, --features <FEATURES>  要激活的特征的空格或逗号分隔列表
      --all-features         激活所有可用特性
      --no-default-features  不激活 `default` 特性

编译选项：
  -j, --jobs <N>                并行作业的数量，默认为 CPU 的数量。
      --keep-going              在出现错误时不要立即终止构建
  -r, --release                 在发布模式下构建工件，并进行优化
      --profile <PROFILE-NAME>  使用指定配置文件构建工件
      --target [<TRIPLE>]       为目标三元组构建
      --target-dir <DIRECTORY>  所有生成工件的目录
      --unit-graph              以 JSON 格式输出构建图（不稳定）
      --timings[=<FMTS>]        时间输出格式（不稳定）（逗号分隔）：html, json

清单选项：
      --manifest-path <PATH>  Cargo.toml 的路径
      --frozen                需要 Cargo.lock 和缓存是最新的
      --locked                需要 Cargo.lock 是最新的
      --offline               不访问网络运行

运行 `cargo help run` 以获取更详细的信息。
```

<p>&nbsp;</p>

## cargo test -h

```text
$ cargo test -h
执行本地包的所有单元和集成测试，并构建示例

使用方法：cargo.exe test [OPTIONS] [TESTNAME] [-- [ARGS]...]

参数：
  [TESTNAME]  如果指定，只运行包含此字符串在其名称中的测试
  [ARGS]...   测试二进制文件的参数

选项：
      --doc                     仅测试此库的文档
      --no-run                  编译，但不运行测试
      --no-fail-fast            无论失败与否，运行所有测试
      --ignore-rust-version     忽略包中的 `rust-version` 规格说明
      --future-incompat-report  在构建结束时输出将来的不兼容性报告
      --message-format <FMT>    错误格式
  -q, --quiet                   每个测试显示一个字符，而不是一行
  -v, --verbose...              使用详细输出（-vv 非常详细/build.rs 输出）
      --color <WHEN>            色彩：自动，始终，从不
      --config <KEY=VALUE>      覆盖一个配置值
  -Z <FLAG>                     不稳定的（仅限夜间）标志到 Cargo，详情请见 'cargo -Z help'
  -h, --help                    打印帮助信息

包选择：
  -p, --package [<SPEC>]  要运行测试的包
      --workspace         测试工作区中的所有包
      --exclude <SPEC>    从测试中排除包
      --all               --workspace 的别名（已弃用）

目标选择：
      --lib               仅测试此包的库单元测试
      --bins              测试所有二进制文件
      --bin [<NAME>]      仅测试指定的二进制文件
      --examples          测试所有示例
      --example [<NAME>]  仅测试指定的示例
      --tests             测试所有测试目标
      --test [<NAME>]     仅测试指定的测试目标
      --benches           测试所有基准目标
      --bench [<NAME>]    仅测试指定的基准目标
      --all-targets       测试所有目标（不包括文档测试）

特性选择：
  -F, --features <FEATURES>  要激活的特征的空格或逗号分隔列表
      --all-features         激活所有可用特性
      --no-default-features  不激活 `default` 特性

编译选项：
  -j, --jobs <N>                并行作业的数量，默认为 CPU 的数量。
  -r, --release                 在发布模式下构建工件，并进行优化
      --profile <PROFILE-NAME>  使用指定配置文件构建工件
      --target [<TRIPLE>]       为目标三元组构建
      --target-dir <DIRECTORY>  所有生成工件的目录
      --unit-graph              以 JSON 格式输出构建图（不稳定）
      --timings[=<FMTS>]        时间输出格式（不稳定）（逗号分隔）：html, json

清单选项：
      --manifest-path <PATH>  Cargo.toml 的路径
      --frozen                需要 Cargo.lock 和缓存是最新的
      --locked                需要 Cargo.lock 是最新的
      --offline               不访问网络运行

运行 `cargo help test` 以获取更详细的信息。
运行 `cargo test -- --help` 以获取测试二进制选项。
```

<p>&nbsp;</p>

## cargo bench -h

```text
$ cargo bench -h
执行本地包的所有基准测试

使用方法：cargo.exe bench [OPTIONS] [BENCHNAME] [-- [ARGS]...]

参数：
  [BENCHNAME]  如果指定，只运行在其名称中包含此字符串的基准测试
  [ARGS]...    基准测试二进制文件的参数

选项：
      --no-run                编译，但不运行基准测试
      --no-fail-fast          无论失败与否，运行所有基准测试
      --ignore-rust-version   忽略包中的 `rust-version` 规格说明
      --message-format <FMT>  错误格式
  -q, --quiet                 不打印 cargo 日志消息
  -v, --verbose...            使用详细输出（-vv 非常详细/build.rs 输出）
      --color <WHEN>          色彩：自动，始终，从不
      --config <KEY=VALUE>    覆盖一个配置值
  -Z <FLAG>                   不稳定的（仅限夜间）标志到 Cargo，详情请见 'cargo -Z help'
  -h, --help                  打印帮助信息

包选择：
  -p, --package [<SPEC>]  要运行基准测试的包
      --workspace         在工作区中基准测试所有包
      --exclude <SPEC>    从基准测试中排除包
      --all               --workspace 的别名（已弃用）

目标选择：
      --lib               仅基准测试此包的库
      --bins              基准测试所有二进制文件
      --bin [<NAME>]      仅基准测试指定的二进制文件
      --examples          基准测试所有示例
      --example [<NAME>]  仅基准测试指定的示例
      --tests             基准测试所有测试目标
      --test [<NAME>]     仅基准测试指定的测试目标
      --benches           基准测试所有基准目标
      --bench [<NAME>]    仅基准测试指定的基准目标
      --all-targets       基准测试所有目标

特性选择：
  -F, --features <FEATURES>  要激活的特征的空格或逗号分隔列表
      --all-features         激活所有可用特性
      --no-default-features  不激活 `default` 特性

编译选项：
  -j, --jobs <N>                并行作业的数量，默认为 CPU 的数量。
      --profile <PROFILE-NAME>  使用指定配置文件构建工件
      --target [<TRIPLE>]       为目标三元组构建
      --target-dir <DIRECTORY>  所有生成工件的目录
      --unit-graph              以 JSON 格式输出构建图（不稳定）
      --timings[=<FMTS>]        时间输出格式（不稳定）（逗号分隔）：html, json

清单选项：
      --manifest-path <PATH>  Cargo.toml 的路径
      --frozen                需要 Cargo.lock 和缓存是最新的
      --locked                需要 Cargo.lock 是最新的
      --offline               不访问网络运行

运行 `cargo help bench` 以获取更详细的信息。
```

<p>&nbsp;</p>

## cargo update -h

```text
$ cargo update -h
更新本地锁文件中记录的依赖项

使用方法：cargo.exe update [OPTIONS] [SPEC]...

选项：
  -n, --dry-run             不实际写入锁文件
      --recursive           强制更新 [SPEC]... 以及所有依赖项
      --precise <PRECISE>   将 [SPEC] 更新为精确的 PRECISE
  -q, --quiet               不打印 cargo 日志消息
  -v, --verbose...          使用详细输出（-vv 非常详细/build.rs 输出）
      --color <WHEN>        色彩：自动，始终，从不
      --config <KEY=VALUE>  覆盖一个配置值
  -Z <FLAG>                 不稳定的（仅限夜间）标志到 Cargo，详情请见 'cargo -Z help'
  -h, --help                打印帮助信息

包选择：
  -w, --workspace  仅更新工作区包
  [SPEC]...    要更新的包

清单选项：
      --manifest-path <PATH>  Cargo.toml 的路径
      --frozen                需要 Cargo.lock 和缓存是最新的
      --locked                需要 Cargo.lock 是最新的
      --offline               不访问网络运行

运行 `cargo help update` 以获取更多详细信息。
```

<p>&nbsp;</p>

## cargo search -h

```text
$ cargo search -h
在 crates.io 中搜索包

使用方法：cargo.exe search [OPTIONS] [QUERY]...

参数：
  [QUERY]...

选项：
      --limit <LIMIT>        限制结果的数量（默认：10，最大：100）
      --index <INDEX>        注册表索引 URL，用于搜索包
      --registry <REGISTRY>  用于搜索包的注册表
  -q, --quiet                不打印 cargo 日志消息
  -v, --verbose...           使用详细输出（-vv 非常详细/build.rs 输出）
      --color <WHEN>         色彩：自动，始终，从不
      --config <KEY=VALUE>   覆盖一个配置值
  -Z <FLAG>                  不稳定的（仅限夜间）标志到 Cargo，详情请见 'cargo -Z help'
  -h, --help                 打印帮助信息

清单选项：
      --frozen   需要 Cargo.lock 和缓存是最新的
      --locked   需要 Cargo.lock 是最新的
      --offline  不访问网络运行

运行 `cargo help search` 以获取更多详细信息。
```

<p>&nbsp;</p>

## cargo publish -h

```text
$ cargo publish -h
将包上传到注册表

使用方法：cargo.exe publish [OPTIONS]

选项：
  -n, --dry-run              执行所有检查而不上传
      --index <INDEX>        上传包到的注册表索引 URL
      --registry <REGISTRY>  上传包到的注册表
      --token <TOKEN>        上传时使用的令牌
      --no-verify            不通过构建来验证内容
      --allow-dirty          允许脏工作目录被打包
  -q, --quiet                不打印 cargo 日志消息
  -v, --verbose...           使用详细输出（-vv 非常详细/build.rs 输出）
      --color <WHEN>         色彩：自动，始终，从不
      --config <KEY=VALUE>   覆盖一个配置值
  -Z <FLAG>                  不稳定的（仅限夜间）标志到 Cargo，详情请见 'cargo -Z help'
  -h, --help                 打印帮助信息

包选择：
  -p, --package [<SPEC>]  要发布的包

特性选择：
  -F, --features <FEATURES>  激活的空间或逗号分隔的特性列表
      --all-features         激活所有可用特性
      --no-default-features  不激活 `default` 特性

编译选项：
  -j, --jobs <N>                并行作业的数量，默认为 CPU 的数量。
      --keep-going              出现错误时不立即终止构建
      --target [<TRIPLE>]       为目标三元组构建
      --target-dir <DIRECTORY>  所有生成艺术品的目录

清单选项：
      --manifest-path <PATH>  Cargo.toml 的路径
      --frozen                需要 Cargo.lock 和缓存是最新的
      --locked                需要 Cargo.lock 是最新的
      --offline               不访问网络运行

运行 `cargo help publish` 以获取更多详细信息。
```

<p>&nbsp;</p>

## cargo install -h

```text
$ cargo install -h
安装 Rust 二进制文件。默认位置是 $HOME/.cargo/bin

使用方法：cargo.exe install [OPTIONS] [CRATE[@<VER>]]...

参数：
  [CRATE[@<VER>]]...  从给定源选择包

选项：
      --version <VERSION>     指定要安装的版本
      --index <INDEX>         要从中安装的注册表索引
      --registry <REGISTRY>   要使用的注册表
      --git <URL>             安装指定 crate 的 Git URL
      --branch <BRANCH>       从 git 安装时使用的分支
      --tag <TAG>             从 git 安装时使用的标签
      --rev <SHA>             从 git 安装时使用的特定提交
      --path <PATH>           要安装的本地 crate 的文件系统路径
      --root <DIR>            安装包的目录
  -f, --force                 强制覆盖现有 crate 或二进制文件
      --no-track              不保存跟踪信息
      --list                  列出所有已安装的包及其版本
      --ignore-rust-version   忽略包中的 `rust-version` 规格说明
      --message-format <FMT>  错误格式
  -q, --quiet                 不打印 cargo 日志消息
      --debug                 以 debug 模式（使用 'dev' 配置文件）而不是 release 模式构建
  -v, --verbose...            使用详细输出（-vv 非常详细/build.rs 输出）
      --color <WHEN>          色彩：自动，始终，从不
      --config <KEY=VALUE>    覆盖一个配置值
  -Z <FLAG>                   不稳定的（仅限夜间）标志到 Cargo，详情请见 'cargo -Z help'
  -h, --help                  打印帮助信息

目标选择：
      --bin [<NAME>]      仅安装指定的二进制文件
      --bins              安装所有二进制文件
      --example [<NAME>]  仅安装指定的示例
      --examples          安装所有示例

特性选择：
  -F, --features <FEATURES>  激活的空间或逗号分隔的特性列表
      --all-features         激活所有可用特性
      --no-default-features  不激活 `default` 特性

编译选项：
  -j, --jobs <N>                并行作业的数量，默认为 CPU 的数量。
      --keep-going              出现错误时不立即终止构建
      --profile <PROFILE-NAME>  使用指定配置文件安装工件
      --target [<TRIPLE>]       为目标三元组构建
      --target-dir <DIRECTORY>  所有生成艺术品的目录
      --timings[=<FMTS>]        时间输出格式（不稳定）：html, json（逗号分隔）

清单选项：
      --frozen   需要 Cargo.lock 和缓存是最新的
      --locked   需要 Cargo.lock 是最新的
      --offline  不访问网络运行

运行 `cargo help install` 以获取更多详细信息。
```

<p>&nbsp;</p>

## cargo uninstall -h

```text
$ cargo uninstall -h
移除 Rust 二进制文件

使用方法：cargo.exe uninstall [OPTIONS] [SPEC]...

参数：
  [SPEC]...

选项：
      --root <DIR>          要卸载包的目录
  -q, --quiet               不打印 cargo 日志消息
  -v, --verbose...          使用详细输出（-vv 非常详细/build.rs 输出）
      --color <WHEN>        色彩：自动，始终，从不
      --config <KEY=VALUE>  覆盖一个配置值
  -Z <FLAG>                 不稳定的（仅限夜间）标志到 Cargo，详情请见 'cargo -Z help'
  -h, --help                打印帮助信息

包选择：
  -p, --package [<SPEC>]  要卸载的包

目标选择：
      --bin <NAME>  仅卸载名为 NAME 的二进制文件

清单选项：
      --frozen   需要 Cargo.lock 和缓存是最新的
      --locked   需要 Cargo.lock 是最新的
      --offline  不访问网络运行

运行 `cargo help uninstall` 以获取更多详细信息。
```

<p>&nbsp;</p>

## cargo --list

```text
$ cargo --list
已安装的命令：
    add                  将依赖项添加到 Cargo.toml 清单文件中
    b                    别名：build
    bench                执行本地包的所有基准测试
    build                编译本地包及其所有依赖项
    c                    别名：check
    check                检查本地包及其所有依赖项是否有错误
    clean                删除 cargo 过去生成的所有工件
    clippy               检查包以捕获常见错误并提高您的 Rust 代码质量。
    config               检查配置值
    d                    别名：doc
    doc                  构建包的文档
    fetch                从网络获取包的依赖项
    fix                  自动修复 rustc 报告的 lint 警告
    fmt                  使用 rustfmt 格式化当前 crate 的所有 bin 和 lib 文件。
    generate-lockfile    为包生成 lockfile
    git-checkout         此命令已被移除
    help                 显示 cargo 子命令的帮助信息
    init                 在现有目录中创建一个新的 cargo 包
    install              安装 Rust 二进制文件。默认位置是 $HOME/.cargo/bin
    locate-project       打印 Cargo.toml 文件位置的 JSON 表示
    login                登录到注册表。
    logout               从本地注册表中移除 API 令牌
    metadata             以机器可读的格式输出包的解析依赖项，包括包括覆盖在内的具体使用的版本
    miri
    new                  在 <path> 处创建一个新的 cargo 包
    owner                在注册表中管理crate的所有者
    package              将本地包组装成可分发的 tarball
    pkgid                打印完全合格的包规范
    publish              将包上传到注册表
    r                    别名：run
    read-manifest        打印 Cargo.toml 清单的 JSON 表示。
    remove               从 Cargo.toml 清单文件中移除依赖项
    report               生成并显示各种报告
    rm                   别名：remove
    run                  运行本地包的二进制文件或示例
    rustc                编译包，并将额外选项传递给编译器
    rustdoc              使用指定的自定义标志构建包的文档。
    search               在 crates.io 中搜索包
    t                    别名：test
    test                 执行本地包的所有单元和集成测试并构建示例
    tree                 显示依赖关系图的树状可视化
    uninstall            移除 Rust 二进制文件
    update               更新本地锁文件中记录的依赖项
    vendor               在本地为项目提供所有依赖项
    verify-project       检查 crate 清单的正确性
    version              显示版本信息
    yank                 从索引中移除已推送的 crate
```
