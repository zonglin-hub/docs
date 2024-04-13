# Markdown 折叠内容

HTML `<details>` 标签指定了用户可以根据需要打开和关闭的额外细节。

语法：

```markdown
<details> <summary>Title</summary>
contents ...
</details>
```

内容里面可以嵌套使用 Markdown 语法和 HTML 语法。

效果：

<details> <summary>Title</summary>
contents ...
</details>

在博客园的 Markdown 中，`<details>` 标签前面不能为空，要有字符或者空格：

```markdown
 <details> <summary>View Code</summary>
code ...
</details>
```

有的 Markdown 中，可能 `<summary>` 标签与正文间要空一行。比如：

```markdown
<details> <summary>View Code</summary>

code ...

</details>
```

当然，有空行的话比较方便阅读源码。
