以下是一个完整的 `gettext` 在 Rust 中的使用示例，包含项目设置、代码实现和构建配置。

## 🛠️ 项目设置

首先在 `Cargo.toml` 中添加依赖：

```toml
[package]
name = "gettext-example"
version = "0.1.0"
edition = "2024"

[dependencies]
gettext-rs = { version = "0.7", features = ["gettext-system"] }
```

## 📁 项目结构

```
> tree .
.
├── Cargo.lock
├── Cargo.toml
├── locale
│   ├── en_US
│   │   └── LC_MESSAGES
│   └── zh_CN
│       └── LC_MESSAGES
├── locales
├── po
│   └── c-gettext-example.pot
└── src
    └── main.rs

9 directories, 4 files
```

## 💻 代码实现

### `src/main.rs`

```rust
use gettextrs::{gettext, LocaleCategory};

fn main() {

	// 设置语言环境
	gettextrs::setlocale(LocaleCategory::LcAll, "zh_CN.UTF-8");
	gettextrs::bindtextdomain("myapp", "./locales").unwrap();
	gettextrs::textdomain("myapp").unwrap();
	
	// 翻译文本
	println!("{}", gettext("Hello, world!"));
	println!("{}", gettext("Welcome to my Rust project."));
}
```

## 🔧 构建脚本

### `build.rs`

```rust
```

## 📝 翻译文件

### `po/POTFILES.in`
```
src/main.rs
```

### `po/zh_CN.po`
```po
# SOME DESCRIPTIVE TITLE.
# Copyright (C) YEAR THE PACKAGE'S COPYRIGHT HOLDER
# This file is distributed under the same license as the PACKAGE package.
# FIRST AUTHOR <EMAIL@ADDRESS>, YEAR.
#
#, fuzzy
msgid ""
msgstr ""
"Project-Id-Version: PACKAGE VERSION\n"
"Report-Msgid-Bugs-To: \n"
"POT-Creation-Date: 2025-10-18 05:39+0000\n"
"PO-Revision-Date: YEAR-MO-DA HO:MI+ZONE\n"
"Last-Translator: FULL NAME <EMAIL@ADDRESS>\n"
"Language-Team: LANGUAGE <LL@li.org>\n"
"Language: \n"
"MIME-Version: 1.0\n"
"Content-Type: text/plain; charset=UTF-8\n"
"Content-Transfer-Encoding: 8bit\n"

#. 翻译文本
#: src/main.rs:10
msgid "Hello, world!"
msgstr ""

#: src/main.rs:11
msgid "Welcome to my Rust project."
msgstr ""

```

## 🚀 使用方法

### 1. 安装 gettext 工具

**Ubuntu/Debian:**
```bash
# sudo apt-get install gettext
cargo install xtr
```

### 2. 构建和运行

```bash
# 构建项目（会自动编译翻译文件）
cargo build

# 运行程序
cargo run
```

### 3. 设置语言环境

```bash
# 设置为中文环境
export LANG=zh_CN.UTF-8
cargo run

# 设置为英文环境
export LANG=en_US.UTF-8
cargo run
```

## 🎯 高级用法

### 使用 `ngettext` 处理复数形式

```rust
use gettext_rs::ngettext;

fn show_message_count(count: u32) {
    let msg = ngettext(
        "You have {} new message.",
        "You have {} new messages.",
        count
    );
    println!("{}", msg);
}
```

### 使用宏简化代码

```rust
macro_rules! t {
    ($msg:literal) => {
        gettext($msg)
    };
    ($ctx:literal, $msg:literal) => {
        pgettext($ctx, $msg)
    };
}

// 使用宏
println!("{}", t!("Hello"));
println!("{}", t!("menu", "Save"));
```

## 💡 工具链集成

### 提取翻译字符串

创建 `update_po.sh` 脚本：

```bash
#!/bin/bash

# 提取所有需要翻译的字符串
xgettext --keyword=gettext --keyword=pgettext:1c,2 \
         --keyword=ngettext:1,2 \
         --output=po/hello.pot \
         src/*.rs

# 更新中文翻译文件
msgmerge --update po/zh_CN.po po/hello.pot
```

这个示例展示了完整的 `gettext` 在 Rust 中的使用流程，包括项目配置、代码实现、翻译文件管理和构建集成。你可以根据这个基础模板为你的终端程序添加国际化支持。