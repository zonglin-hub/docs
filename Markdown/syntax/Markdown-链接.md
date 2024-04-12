# Markdown 链接

Markdown 支持两种形式的链接语法： **行内链接** 和 **参考链接**。

## 行内形式

链接文字都是用 `[方括号]` 来标记，使用 `(圆括号)` 来把文字转成链接。还可以为链接文字配个Title，当然 Title 属性是可选项，加不加看心情咯。

```php
[链接文字](链接网址 "标题")
This is an [example link](http://example.com/ "With a Title"). 
```

This is an [example link](http://example.com/ "With a Title").

在 Typora 软件中，复制一个链接，选中文字按 <kbd>ctrl-K</kbd> 快速插入链接。

## 参考形式

为参考形式的链接定一个 `[name]` 方便我们在文章中多次引用（name 可以用字母、数字和空格，且不分大小写）。

```php
[链接文字][name]

[name]: link "Title"
```

举例：

早饭后，我打开 [每日英语听力][1] 学习英语。遇到不懂的英语单词，我借助 [欧路在线词典][2]
查看释义并加入生词本，方便使用 [客户端][3] 随时记忆单词。

[1]: https://dict.eudic.net/ting "每日英语听力 - 欧路词典"
[2]: https://dict.eudic.net/ "《欧路词典》在线版"
[3]: https://www.eudic.net/v4/en/app/eudic "《欧路词典》英语翻译软件官方主页"

```php
早饭后，我打开 [每日英语听力][1] 学习英语。遇到不懂的英语单词，我借助 [欧路在线词典][2] 
查看释义并加入生词本，方便使用 [客户端][3] 随时记忆单词。

[1]: https://dict.eudic.net/ting "每日英语听力"
[2]: https://dict.eudic.net/ "《欧路词典》在线版"
[3]: https://www.eudic.net/v4/en/app/eudic "《欧路词典》英语翻译软件"
```

一直用 [百度][bd]，当知道 [搜狗][s] 可以搜索微信和知乎时，我慢慢爱上了 [搜狗][s] 这个功能。学术搜索还是用 [Google][g]。平时也会用 [Bing][b]，毕竟各有所长。

[G]: https://www.google.com/ "Google"
[BD]: https://www.baidu.com/ "Baidu Search"
[S]: https://www.sogou.com/ "Sogou"
[B]: https://cn.bing.com/ "Bing Search"

```php
一直用 [百度][bd]，当知道 [搜狗][s] 可以搜索微信和知乎时，我慢慢爱上了 [搜狗][s] 这个功能。
学术搜索还是用 [Google][g]。平时也会用 [Bing][b]，毕竟各有所长。

[G]: https://www.google.com/ "Google"
[BD]: https://www.baidu.com/ "Baidu Search"
[S]: https://www.sogou.com/ "Sogou"
[B]: https://cn.bing.com/ "Bing Search"
```

## 自动链接

Markdown 支持以比较简短的自动链接形式来处理网址和电子邮件信箱，只需用 `< >` 包起来，Markdown 就会自动把它转成链接。一般网址的链接文字就和链接地址一样，邮址的自动链接也很类似，例如：

```markdown
<http://example.com/>
<address@example.com>
```

<http://example.com/><br>
<address@example.com>
