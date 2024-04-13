# Markdown 列表

Markdown 支持 **有序列表** 和 **无序列表**。<br>

## 无序列表

使用星号 `*`、加号 `+` 或是减号 `-` 作为列表标记，标记符号后至少一个空格或制表符（Tab）。

```markdown
* Green （比较推荐）
- Red  （比较好用）
+   Blue
```

显示效果：

- Green （比较推荐）
- Red  （比较好用）
- Blue

无序列表标记符前每增加 2 个空格或是 4 个空格，增加一个层级：

```markdown
- 伟大始于渺小。
  - 说和做是迥然不同的两回事。
      - 行动比语言更响亮。
```

- 伟大始于渺小。
    - 说和做是迥然不同的两回事。
        - 行动比语言更响亮。

## 有序列表

则使用数字接着一个英文句点。标记符号后面也要接着至少一个空格或制表符（Tab）。

```markdown
1. First paragraph
2. Second paragraph
3. Third paragraph
```

1. First paragraph
2. Second paragraph
3. Third paragraph

在列表标记上使用的数字并不会影响输出的 HTML 结果，这意味着，你可以 Use lazy numbering for long lists：

```markdown
1.  Foo.
1.  Bar.
    1.  Foofoo.
    1.  Barbar.
1.  Baz.
```

1. Foo.
1. Bar.
    1. Foofoo.
    1. Barbar.
1. Baz.

有序列表标记符前每增加一个 Tab。

## 列表项目中的段落

列表项目可以包含多个段落，段落都必须缩进 4 个空格或是 1 个制表符（Tab）。

无序列表：

```markdown
- Every man is his own worst enemy. Wisdom in the mind is better than money in the hand. Every man is his own worst enemy. Wisdom in the mind is better than money in the hand.
    
    From small beginnings comes great things.(列表项目中的段落)

- Saying and doing are two different things. Actions speak louder than words. Saying and doing are two different things. Actions speak louder than words. 
```

- Every man is his own worst enemy. Wisdom in the mind is better than money in the hand. Every man is his own worst enemy. Wisdom in the mind is better than money in the hand.
  
    From small beginnings comes great things. (列表项目中的段落)

- Saying and doing are two different things. Actions speak louder than words. Saying and doing are two different things. Actions speak louder than words.

有序列表：

```markdown
1. Every man is his own worst enemy. Wisdom in the mind is better than money in the hand. Every man is his own worst enemy. Wisdom in the mind is better than money in the hand.
    
    From small beginnings comes great things. (列表项目中的段落)

2. Saying and doing are two different things. Actions speak louder than words. Saying and doing are two different things. Actions speak louder than words. 
```

1. Every man is his own worst enemy. Wisdom in the mind is better than money in the hand. Every man is his own worst enemy. Wisdom in the mind is better than money in the hand.

    From small beginnings comes great things. (列表项目中的段落)

2. Saying and doing are two different things. Actions speak louder than words. Saying and doing are two different things. Actions speak louder than words.

## 列表项目内引用

在引用标记符 `>` 前使用 2 / 4 个空格：

```markdown
- A list item with a blockquote:

    > This is a blockquote（上面空一行，标记符 > 前有 1 个 Tab）
    > inside a list item.

1. A list item with a blockquote:

    > This is a blockquote（上面空一行，标记符 > 前有 4 个空格或者 1 个 Tab）
    > inside a list item.
```

- A list item with a blockquote:

    > This is a blockquote
    > inside a list item.

1. A list item with a blockquote:

    > This is a blockquote
    > inside a list item.

## 列表项目内代码块

要放代码块的话，中间空一行，然后缩进两次，也就是 8 个空格或者 2 个制表符（Tab）：

```markdown
- 一列表项包含一个代码块：

        我是代码区块，我上方有一空行，前方有8个空格。
        
1. 一列表项包含一个代码块：

        我是代码区块，我上方有一空行，前方有8个空格。
```

- 一列表项包含一个代码块：

        我是代码区块，我上方有一空行，前方有8个空格。

1. 一列表项包含一个代码块：

        我是代码区块，我上方有一空行，前方有8个空格。

## 有序列表异常

当然，项目列表很可能会不小心产生，像是下面这样的写法：

```markdown
   1. Sweet osmanthus smells ten miles.
1986. What a great season.
```

1986 头怎么伸出去了？要避免这样的状况，你可以在**句点前面加上反斜杠**。

```markdown
1986\. What a great season.
```
