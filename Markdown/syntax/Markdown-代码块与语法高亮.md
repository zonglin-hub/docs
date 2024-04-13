# Markdown 代码块与语法高亮

插入程序代码的方式有两种：使用反引号 \`（~ 键）、使用缩进（Tab）。

- 插入行内代码，即插入一个单词或者一句代码的情况，使用 \`code\` 这样的形式插入。
- 插入多行代码，分别使用三个反引号（\```）包裹多行代码。或者使用缩进。

## 行内代码

在一般的段落文字中，可以使用反引号 \` 来标记或插入代码区段。

```text
C语言里的函数 `scanf()` 怎么使用？
```

C语言里的函数 `scanf()` 怎么使用？<br>

当然也可以标记 `文件名` 、`关键词` 等。

## 多行代码

- 在需要高亮的代码块的前一行及后一行使用三个反引号 ```（~ 键）
- 同时第一行反引号后面，输入代码块所使用的语言，实现代码高亮。

比如高亮 `python3` 代码块：

```python
#!/usr/bin/env python3
print("Hello, World!");
```

## 缩进式插入多行代码

**注意**：<br>

- 缩进式插入前方必须有空行；<br>
- 缩进 4 个空格或是 1 个制表符；<br>
- 一个代码区块会一直持续到没有缩进的那一行（或是文件结尾）。

代码：

```markdown

    #include  <stdio.h>`
    int main(void)`
    {
        printf("Hello world\n");
    }
```

显示效果：

    #include  <stdio.h>`
    int main(void)`
    {
        printf("Hello world\n");
    }

## 代码区块中的内容

代码区块中，一般的 Markdown 语法不会被转换，像是 * 便只是星号，这表示你可以很容易地以 Markdown 语法撰写 Markdown 语法相关的文件。

比如下面展示 Markdown 常用语法：

```markdown
Markdown 目录：
[TOC]

Markdown 标题：
# 这是 H1
## 这是 H2
### 这是 H3

Markdown 列表：
- 列表项目
1. 列表项目

*斜体*或_斜体_
**粗体**
***加粗斜体***
~~删除线~~

Markdown 插入链接：
[链接文字](链接网址 "标题")

Markdown 插入图片：
![alt text](/path/to/img.jpg "Title")

Markdown 插入代码块：

    ```python
    #!/usr/bin/python3
    print("Hello, World!");
    ```

Markdown 引用：
> 引用内容

Markdown 分割线：
---

Markdown 换行：
<br>

Markdown 段首缩进：
&ensp; or &#8194; 表示一个半角的空格
&emsp; or &#8195;  表示一个全角的空格
&emsp;&emsp; 两个全角的空格（用的比较多）
&nbsp; or &#160; 不断行的空白格

```
