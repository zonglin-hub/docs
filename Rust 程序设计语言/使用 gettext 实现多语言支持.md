  

# 使用 gettext 实现多语言支持

⁠`gettext` 是一个用于国际化（i18n）和本地化（l10n）的工具，主要用于管理软件中的文本翻译。

### 0. **项目结构**：

```sh
c-gettext-example
├── src
│   └── main.c
├── po
│   ├── c-gettext-example.pot  # 翻译模板
│   ├── en_US.po   # 翻译文件
│   ├── zh_CN.po   # 翻译文件
│   └── LINGUAS    # 支持语言文件
└── locale         # 存储翻译产物
	├── en_US
	│   └── LC_MESSAGES
	│       └── c-gettext-example.mo
	└── zh_CN
		└── LC_MESSAGES
			└── c-gettext-example.mo
```

### 1. **标记可翻译字符串**：

在源代码中，使用 `gettext` 函数标记需要翻译的字符串：

`src/main.c`:

```c
#include <libintl.h>
#include <locale.h>
#include <stdio.h>

int main() {
	setlocale(LC_ALL, "");                // 设置语言环境（使用环境变量）
	bindtextdomain("c-gettext-example", "./locale");  // 绑定消息域
	textdomain("c-gettext-example");                  // 设置消息域
	printf(gettext("Hello, World!"));
}
```

> 在实际使用时不要包含非 ASCII 注释

或者，使用缩写形式 `_`（通常需要定义宏）：

```c
printf(_("Hello, World!"));
```
   
### 2. **提取翻译字符串**：
   
使用 `xgettext` 工具从源码中提取标记的字符串，生成 `.pot`（Portable Object Template）文件。这个文件包含了所有需要翻译的原始字符串。

```sh
mkdir -p po;
xgettext -o po/c-gettext-example.pot src/main.c
```

`.pot` 是翻译的模板文件。接下来我们要用它来生成翻译目标文件。

### 3. **创建翻译文件**：
   
为每种目标语言创建一个 `.po` 文件：

```sh
msginit -i po/c-gettext-example.pot -o po/zh_CN.po -l zh_CN.UTF-8 --no-translator
```

### 4. **翻译字符串**：

使用文本编辑器或专用的翻译工具（如 Poedit）打开 `.po` 文件，填写每个原始字符串的翻译。

```po
# Chinese translations for PACKAGE package.
# Copyright (C) 2025 THE PACKAGE'S COPYRIGHT HOLDER
# This file is distributed under the same license as the PACKAGE package.
# Automatically generated, 2025.
#
msgid ""
msgstr ""
"Project-Id-Version: PACKAGE VERSION\n"
"Report-Msgid-Bugs-To: \n"
"POT-Creation-Date: 2025-05-16 23:50+0800\n"
"PO-Revision-Date: 2025-05-16 23:50+0800\n"
"Last-Translator: Automatically generated\n"
"Language-Team: none\n"
"Language: zh\n"
"MIME-Version: 1.0\n"
"Content-Type: text/plain; charset=ASCII\n"
"Content-Transfer-Encoding: 8bit\n"

#: main.c:9
#, c-format
msgid "Hello, World!"
msgstr "你好，世界！"
```
   
### 5. **编译翻译文件**：
   
使用 `msgfmt` 将 `.po` 文件编译为机器可读的 `.mo` 文件。

```sh
mkdir -p locale/zh_CN/LC_MESSAGES
msgfmt -o locale/zh_CN/LC_MESSAGES/c-gettext-example.mo po/zh_CN.po
```
   
### 6. **编译程序**：
   
```sh
gcc -o c-gettext-example -I/opt/gettext/include -L/opt/gettext/lib src/main.c
```

### 7. **运行**：

```sh
$ LC_MESSAGES="en_US.UTF-8" ./c-gettext-example
Hello, World!
$ LC_MESSAGES="zh_CN.UTF-8" ./c-gettext-example
你好，世界！
```


参见：[使用 GNU gettext 来翻译软件 | Weblate 文档](https://docs.weblate.org/zh-cn/latest/devel/gettext.html)
