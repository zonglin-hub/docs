# Markdown 换行与段首缩进

## 换行

Markdown 中，段落之间的换行是通过在段落之间留空行的方式来实现。

```markdown
段落一

段落二
```

但我们会有其他的换行需求，可以这样实现：

* 键入 HTML 语言换行标签：`<br>`（通用）
* 段落内换行使用换行符 `<br>`，或者 `两个空格` + `shift-Enter`。不推荐使用  `\` + `shift-Enter`。
* Typora 中，空行中使用四个空格（一个 Tab）可以快速增大段落之间的间距

为了演示效果，举例如下：

```markdown
春望<br>唐代：杜甫

国破山河在，城春草木深。  
感时花溅泪，恨别鸟惊心。  
烽火连三月，家书抵万金。  
白头搔更短，浑欲不胜簪。
```

春望<br>唐代：杜甫

国破山河在，城春草木深。  
感时花溅泪，恨别鸟惊心。  
烽火连三月，家书抵万金。  
白头搔更短，浑欲不胜簪。

（没有换行符，内容会在一行显示）

## 段首缩进

使用 Markdown 写文章不需要段首缩进。但如果你需要的话，可以在段落前面使用：

### 1）两个全角空格

因为一个全角空格（space）的宽度是整整一个汉字，输入两个全角空格正好是两个汉字的宽度。

全角空格的输入方法为：一般的中文输入法都是按 shift + space，可以切换到全角模式下，输完后再次按 shift + space 切换回正常输入状态。

### 2）使用特殊占位符

使用特殊占位符，不同占位符所占空白是不一样大的。

```markdown
&ensp; or &#8194;  表示一个半角的空格
&emsp; or &#8195;  表示一个全角的空格
&emsp;&emsp;       两个全角的空格（用的比较多）
&nbsp; or &#160;   不断行的空白格
```

&#8194;  表示一个半角的空格<br>&#8195;  表示一个全角的空格<br>&emsp;&emsp;  两个全角的空格（用的比较多）<br>&#160;  不断行的空白格

## See also

* [CSDN-markdown 首行缩进的快捷实现：全角空格配合](https://blog.csdn.net/thither_shore/article/details/52205748)
* [markdown 添加空格缩进[全]](https://blog.csdn.net/zdx1996/article/details/86590864)
