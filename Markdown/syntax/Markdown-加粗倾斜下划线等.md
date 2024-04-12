# Markdown 加粗倾斜下划线等

## 加粗倾斜删除

```markdown
**粗体**

***加粗斜体***

*斜体*或_斜体_

~~删除线~~
```

强调也可以直接插在文字中间：

```
un**frigging**believable
```

un**frigging**believable

如果 * 和 _ 与强调文字之间有空格的话，它们就只会被当成普通的符号。比如：

```markdown
不是** 把学的东西用起来 **，
而是** 为了用而学**！
```

不是**把学的东西用起来 \**，<br>
而是** 为了用而学**！

## 上标 / 下标

```markdown
上标：O<sup>2</sup>
下标：H<sub>2</sub>O
```

上标：O<sup>2</sup><br>下标：H<sub>2</sub>O

## 下划线

```markdown
<u>下划线 underline</u>

用数学公式：
下划线 $\underline{X}$
上划线 $\overline{X}$
```

<u>下划线 underline</u>

用数学公式：<br>下划线 $\underline{X}$<br>上划线 $\overline{X}$

另外，如果想要个性化下划线，可以使用 html 的 span 标签、设置行内 CSS 的 border-bottom 属性来添加下划线。这种方式自定义程度最高。

语法：

```php+HTML
<span style="border-bottom: 2px dashed red;">下划虚线</span>
```

<span style="border-bottom: 2px dashed red;">下划虚线</span>

其他下划线格式：

```php+HTML
<span style="border-bottom: 1px solid red;">单下划线</span>

<span style="border-bottom: thick double #32a1ce;">双下划线</span>

<span style="border-bottom: 2px dotted red;">下划虚线</span>
```

<span style="border-bottom: 1px solid red;">单下划线</span>

<span style="border-bottom: thick double #32a1ce;">双下划线</span>

<span style="border-bottom: 2px dotted red;">下划虚线</span>

Markdown 无法表现中文的着重号 `．`，实在是没有办法的办法：

```php+HTML
<span style="border-bottom: 8px dotted red;">假冒着重号</span>
```

<span style="border-bottom: 8px dotted red;">假冒着重号</span>

> 更多 ``border-style`` 参阅 [border-bottom - CSS_ Cascading Style Sheets _ MDN](https://developer.mozilla.org/en-US/docs/Web/CSS/border-bottom)。

## 插入普通的符号

如果要在文字前后直接插入普通的星号或底线，你可以用反斜线：

```
\*this text is surrounded by literal asterisks\*
```

\*this text is surrounded by literal asterisks\*

Markdown 支持以下这些符号前面加上反斜杠来帮助插入普通的符号：

```html
\   反斜线
`   反引号
*   星号
_   底线
{}  花括号
[]  方括号
()  括弧
#   井字号
+   加号
-   减号
.   英文句点
!   惊叹号
```

## HTML `<kbd>` 标签

`<kbd>` 标签定义键盘文本。HTML 键盘输入元素（`<kbd>`）用于表示用户输入，它将产生一个行内元素，以浏览器的默认 monospace 字体显示。

示例：

```html
Please press 
<kbd>Ctrl</kbd> + <kbd>Shift</kbd> + <kbd>R</kbd> 
to re-render an MDN page.
```

输出：

<p>Please press <kbd>Ctrl</kbd> + <kbd>Shift</kbd> + <kbd>R</kbd> to re-render an MDN page.</p>

> 参见 [**kbd** The Keyboard Input element - HTML_ Hypertext Markup Language](https://developer.mozilla.org/zh-CN/docs/Web/HTML/Element/kbd)。

## 添加注音

示例：

```markdown
<ruby>
我<rp>（</rp><rt>wǒ</rt><rp>）</rp>
爱<rp>（</rp><rt>ài</rt><rp>）</rp>
你<rp>（</rp><rt>nǐ</rt><rp>）</rp>
中<rp>（</rp><rt>zhōng</rt><rp>）</rp>
国<rp>（</rp><rt>guó</rt><rp>）</rp>
</ruby>
```

输出：

<ruby>
我<rp>（</rp><rt>wǒ</rt><rp>）</rp>
爱<rp>（</rp><rt>ài</rt><rp>）</rp>
你<rp>（</rp><rt>nǐ</rt><rp>）</rp>
中<rp>（</rp><rt>zhōng</rt><rp>）</rp>
国<rp>（</rp><rt>guó</rt><rp>）</rp>
</ruby>

#### ruby 语法说明

- `<ruby>` — 用它将需要注释或注音标的文字内容包围住。
- `<rt>` — 这里面放置音标或注释，这个标记要跟在需要注释的文本后边。`rt` 里的文字，对于横向显示的文章，它会显示在上方。对于竖向显示的文字，它会显示到右边。
- `<rp>` — 这个标记是防备那些不支持 `ruby` 标记的浏览器，主要用来放置括弧。对于支持这个标记的浏览器，`rp` 标记的 CSS 样式是 `{display:none;}`，也就是不可见。

偷懒的写法：

<ruby>
我爱你中国<rt>wǒàinǐzhōngguó</rt>
</ruby>

```
<ruby>
我爱你中国<rt>wǒàinǐzhōngguó</rt>
</ruby>
```

其中带音标的字母可通过输入法中的特殊字符输入。也可查看 [符号大全-特殊符号](http://www.fhdq.net/) 中的汉语拼音部分。

> 参见 [HTML 拼音/音标注释标记 ruby 和它的子元素 rt/rp](https://blog.csdn.net/chs_jdmdr/article/details/51622360)
