# grep 强大的文本搜索工具

**grep**
（全面搜索正则表达式并把行打印出来，_global search regular expression(RE) and print out the line_）
是一种强大的文本搜索工具，它能使用正则表达式搜索文本，并把匹配的行打印出来。
用于过滤/搜索的特定字符。可使用正则表达式能配合多种命令使用，使用上十分灵活。

目录

- [grep 强大的文本搜索工具](#grep-强大的文本搜索工具)
  - [规则表达式](#规则表达式)
  - [示例](#示例)
  - [帮助文档](#帮助文档)

## 规则表达式

```sh
^           # 锚定行的开始 如：'^grep' 匹配所有以 grep 开头的行。    
$           # 锚定行的结束 如：'grep$' 匹配所有以 grep 结尾的行。
.           # 匹配一个非换行符的字符 如：'gr.p' 匹配 gr 后接一个任意字符，然后是 p。    
*           # 匹配零个或多个先前字符 如：'*grep' 匹配所有一个或多个空格后紧跟 grep 的行。    
.*          # 一起用代表任意字符。   
[]          # 匹配一个指定范围内的字符，如 '[Gg]rep' 匹配 Grep 和 grep。    
[^]         # 匹配一个不在指定范围内的字符，如：'[^A-Z]rep' 匹配不包含 A-Z 中的字母开头，紧跟 rep 的行
\(..\)      # 标记匹配字符，如 '\(love\)'，love 被标记为 1。    
\<          # 锚定单词的开始，如：'\<grep' 匹配包含以 grep 开头的单词的行。    
\>          # 锚定单词的结束，如：'grep\>' 匹配包含以 grep 结尾的单词的行。    
x\{m\}      # 重复字符 x，m 次，如：'0\{5\}' 匹配包含 5 个 o 的行。    
x\{m,\}     # 重复字符 x，至少 m 次，如：'o\{5,\}' 匹配至少有 5 个 o 的行。    
x\{m,n\}    # 重复字符 x，至少 m 次，不多于 n 次，如：'o\{5,10\}' 匹配 5~10 个 o 的行。   
\w          # 匹配文字和数字字符，也就是 [A-Za-z0-9]，如：'G\w*p' 匹配以 G 后跟零个或多个文字或数字字符，然后是 p。   
\W          # \w 的反置形式，匹配一个或多个非单词字符，如点号句号等。   
\b          # 单词锁定符，如: '\bgrep\b' 只匹配 grep。  
```

## 示例

在文件中搜索一个单词，命令会返回一个包含 `match_pattern` 的文本行：

```sh
grep match_pattern file_name

# 或

grep "match_pattern" file_name
```

在多个文件中查找：

```sh
grep "match_pattern" file_1 file_2 file_3 ...
```

输出除之外的所有行 `-v` 选项：

```sh
grep -v "match_pattern" file_name
```

标记匹配颜色 `--color=auto` 选项：

```sh
grep "match_pattern" file_name --color=auto
```

使用正则表达式 `-E` 选项：

```sh
grep -E "[1-9]+"

# 或

egrep "[1-9]+"
```

使用正则表达式 `-P` 选项：

```sh
grep -P "(\d{3}\-){2}\d{4}" file_name
```

只输出文件中匹配到的部分 `-o` 选项：

```sh
echo this is a test line. | grep -o -E "[a-z]+\."
line.

echo this is a test line. | egrep -o "[a-z]+\."
line.
```

统计文件或者文本中包含匹配字符串的行数 `-c` 选项：

```sh
grep -c "text" file_name
```

搜索命令行历史记录中 输入过 `git` 命令的记录：

```sh
history | grep git
```

输出包含匹配字符串的行数 `-n` 选项：

```sh
grep "text" -n file_name
# 或
cat file_name | grep "text" -n

#多个文件
grep "text" -n file_1 file_2
```

打印样式匹配所位于的字符或字节偏移：

```sh
echo gun is not unix | grep -b -o "not"
7:not
#一行中字符串的字符偏移是从该行的第一个字符开始计算，起始值为0。选项  **-b -o**  一般总是配合使用。
```

搜索多个文件并查找匹配文本在哪些文件中：

```sh
grep -l "text" file1 file2 file3...
```

在多级目录中对文本进行递归搜索：

```sh
grep "text" . -r -n
# .表示当前目录。
```

忽略匹配样式中的字符大小写：

```sh
echo "hello world" | grep -i "HELLO"
# hello
```

选项 `-e` 制动多个匹配样式：

```sh
echo this is a text line | grep -e "is" -e "line" -o
is
is
line

#也可以使用 **-f** 选项来匹配多个样式，在样式文件中逐行写出需要匹配的字符。
cat patfile
aaa
bbb

echo aaa bbb ccc ddd eee | grep -f patfile -o
```

在 grep 搜索结果中包括或者排除指定文件：

```sh
# 只在目录中所有的.php和.html文件中递归搜索字符"main()"
grep "main()" . -r --include *.{php,html}

# 在搜索结果中排除所有README文件
grep "main()" . -r --exclude "README"

# 在搜索结果中排除filelist文件列表里的文件
grep "main()" . -r --exclude-from filelist

```

使用0值字节后缀的 grep 与 xargs：

```sh
# 测试文件：
echo "aaa" > file1
echo "bbb" > file2
echo "aaa" > file3

grep "aaa" file* -lZ | xargs -0 rm

# 执行后会删除 file1 和 file3。
# grep 输出用 -Z 选项来指定以 0 值字节作为终结符文件名（\0）
# xargs -0 读取输入并用 0 值字节终结符分隔文件名，然后删除匹配文件
# -Z 通常和 -l 结合使用。
```

grep 静默输出：

```sh
grep -q "test" filename
# 不会输出任何信息，如果命令运行成功返回0，失败则返回非0值。一般用于条件测试。
```

打印出匹配文本之前或者之后的行：

```sh
# 显示匹配某个结果之后的3行，使用 -A 选项：
seq 10 | grep "5" -A 3
5
6
7
8

# 显示匹配某个结果之前的3行，使用 -B 选项：
seq 10 | grep "5" -B 3
2
3
4
5

# 显示匹配某个结果的前三行和后三行，使用 -C 选项：
seq 10 | grep "5" -C 3
2
3
4
5
6
7
8

# 如果匹配结果有多个，会用“--”作为各匹配结果之间的分隔符：
echo -e "a\nb\nc\na\nb\nc" | grep a -A 1
a
b
--
a
b
```

## 帮助文档

```text
$ grep --help
用法：grep [选项]... 模式 [文件]...
在每个 <文件> 中查找指定的 <模式>。
例如：grep -i 'hello world' menu.h main.c
<模式> 可以包含多个模式字符串，使用换行符进行分隔。

模式选择与解释：
  -E, --extended-regexp     <模式> 是扩展正则表达式
  -F, --fixed-strings       <模式> 是字符串
  -G, --basic-regexp        <模式> 是基本正则表达式
  -P, --perl-regexp         <模式> 是 Perl 正则表达式
  -e, --regexp=模式         使用指定的 <模式> 进行匹配
  -f, --file=文件           从指定的 <文件> 中获得 <模式>
  -i, --ignore-case         对于模式和数据，忽略大小写
      --no-ignore-case      不要忽略大小写（默认）
  -w, --word-regexp         仅匹配整个单词
  -x, --line-regexp         仅匹配整行
  -z, --null-data           数据行以 0 字节 (NUL) 结束，而非换行符

杂项：
  -s, --no-messages         不显示错误信息
  -v, --invert-match        选中不匹配的行
  -V, --version             显示版本信息并退出
      --help                显示此帮助信息并退出

输出控制：
  -m, --max-count=数值      选中 <数值> 行后停止执行
  -b, --byte-offset         输出的同时打印字节偏移量
  -n, --line-number         输出的同时打印行号
      --line-buffered       每行输出后排空输出缓冲区
  -H, --with-filename       输出的同时打印文件名
  -h, --no-filename         输出时不显示文件名前缀
      --label=标签          使用指定 <标签> 作为标准输入文件名前缀
  -o, --only-matching       只显示行中非空的匹配部分
  -q, --quiet, --silent     不显示所有常规输出
      --binary-files=类型   假定二进制文件是 <类型>；
                            <类型> 可以是 "binary"、"text" 或 "without-match"
  -a, --text                等价于 --binary-files=text
  -I                        等价于 --binary-files=without-match
  -d, --directories=动作    处理目录的方式；
                            <动作> 可以是 "read"、"recurse" 或 "skip"
  -D, --devices=动作        处理设备、FIFO 和套接字的方式；
                            <动作> 可以是 "read" 或 "skip"
  -r, --recursive           等价于 --directories=recurse
  -R, --dereference-recursive  同上，但跟随所有符号链接
      --include=GLOB        只查找匹配 GLOB（含通配符的文件模式）的文件
      --exclude=GLOB        跳过匹配 GLOB 的文件
      --exclude-from=文件   跳过匹配 <文件> 内容中任一文件模式的文件
      --exclude-dir=GLOB    跳过匹配 GLOB 的目录
  -L, --files-without-match  只打印没有被选中的行的 <文件> 的名称
  -l, --files-with-matches  只打印有被选中的行的 <文件> 的名称
  -c, --count               只打印每个 <文件> 的被选中的行的数量
  -T, --initial-tab         使制表符对齐（如有必要）
  -Z, --null                在 <文件> 名后打印 0 字节 (NUL)

文件控制：
  -B, --before-context=数值  打印前面 <数值> 行上下文
  -A, --after-context=数值  打印后面 <数值> 行上下文
  -C, --context=数值        打印前后 <数值> 行上下文
  -数值                     等价于 --context=数值
      --group-separator=分隔符  在带有上下文的匹配块之间打印 <分隔符>
      --no-group-separator  不要在带有上下文的匹配块之间打印分隔符
      --color[=何时],
      --colour[=何时]       使用标记高亮匹配的字符串；
                            <何时> 可以是 "always"、"never" 或 "auto"
  -U, --binary              不要清除行尾的 CR 字符 (MSDOS/Windows)

若 <文件> 为 "-"，则从标准输入读取。若没有指定 <文件>，则递归模式
下从 "." 读取，其他情况下从 "-" 读取。若指定的 <文件> 数量少于两个，
则默认启用 -h 选项。如果有任意行被选中，则退出状态为 0，否则
退出状态为 1；如果有错误发生，且未指定 -q 选项，则退出状态为 2。

请向 <bug-grep@gnu.org> 报告软件错误。
请向 <i18n-zh@googlegroups.com> 报告翻译错误。
GNU grep 的主页：<https://www.gnu.org/software/grep/>
GNU 软件一般性帮助：<https://www.gnu.org/gethelp/>
```
