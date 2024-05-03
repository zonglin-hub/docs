# Markdown 链接

Markdown 支持两种形式的链接语法： **行内链接** 和 **参考链接**。

## 行内形式

链接文字都是用 `[方括号]` 来标记，使用 `(圆括号)` 来把文字转成链接。
还可以为链接文字配个 Title，当然 Title 属性是可选项，加不加看心情咯。

```markdown
This is an [example link](http://example.com/ "With a Title"). 
```

<details> <summary>显示效果：</summary>

This is an [example link](http://example.com/ "With a Title").

</details>

## 参考形式

为参考形式的链接定一个 `[name]` 方便我们在文章中多次引用

> 注意：name 可以用字母、数字和空格，且不分大小写。

```markdown
早饭后，我打开 [每日英语听力][1] 学习英语。遇到不懂的英语单词，我借助 [欧路在线词典][2] 
查看释义并加入生词本，方便使用 [客户端][3] 随时记忆单词。

[1]: https://dict.eudic.net/ting "每日英语听力"
[2]: https://dict.eudic.net/ "《欧路词典》在线版"
[3]: https://www.eudic.net/v4/en/app/eudic "《欧路词典》英语翻译软件"
```

<details> <summary>显示效果：</summary>

早饭后，我打开 [每日英语听力][1] 学习英语。遇到不懂的英语单词，我借助 [欧路在线词典][2]
查看释义并加入生词本，方便使用 [客户端][3] 随时记忆单词。

[1]: https://dict.eudic.net/ting "每日英语听力 - 欧路词典"
[2]: https://dict.eudic.net/ "《欧路词典》在线版"
[3]: https://www.eudic.net/v4/en/app/eudic "《欧路词典》英语翻译软件官方主页"

</details>

## 自动链接

Markdown 支持以比较简短的自动链接形式来处理网址和电子邮件信箱，
只需用 `< >` 包起来，Markdown 就会自动把它转成链接。
一般网址的链接文字就和链接地址一样，邮址的自动链接也很类似，例如：

```markdown
<http://example.com/>
<address@example.com>
```

<details> <summary>显示效果：</summary>

<http://example.com/><br>
<address@example.com>

</details>
