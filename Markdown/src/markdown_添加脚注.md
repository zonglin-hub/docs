# Markdown 脚注

## 语法

```markdown
在这段文字后添加一个脚注[^footnote].

[^footnote]:这里是脚注的内容.
```

效果展示：

在这段文字后添加一个脚注[^footnote].

> [!NOTE]
> `footnote` 可以是任意英文字符；脚注的内容可以放在文章的任意位置（一般放最后）。

## 举例

```Markdown
使用 Markdown[^1] 可以效率的书写文档，直接转换成 HTML[^2], 你可以使用 Typora[^T] 软件。

[^1]:Markdown 是一种纯文本标记语言。
[^2]:HyperText Markup Language 超文本标记语言。
[^T]:Typora 官网 <https://typora.io/>
```

效果展示：

使用 Markdown[^1] 可以效率的书写文档，直接转换成 HTML[^2]，你可以使用 Typora[^T] 软件。

[^1]:Markdown 是一种纯文本标记语言。
[^2]:HyperText Markup Language 超文本标记语言。
[^T]:Typora 官网 <https://typora.io/>

Note: 脚注显示在文章末尾；脚注后方的链接可以直接跳转回到加注的地方。
