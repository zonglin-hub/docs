# Markdown 设置字体、字号与颜色

Markdown 本身不支持修改字体、字号与颜色等，还好 Markdown 支持 HTML 语言，所以我们采用 HTML 语法格式即可。

## 设置字体、字号和颜色

编辑字体、字号和颜色的代码如下：

    <font face="黑体"> 我是黑体字 </font>
    <font face="微软雅黑"> 我是微软雅黑 </font>
    
    <font color=gray size=7> color=gray </font>
    <font color=#00ffff size=7> color=#00ffff </font>
    <font color=#0099ff size=7 face="黑体"> color=#0099ff size=7 face="黑体" </font>
    
    // Size：规定文本的尺寸大小。可能的值：从 1 到 7 的数字。浏览器默认值是 3。

font 是一对常规标签，font 标签内设置 `color="对应颜色值"` 即可设置对象内字体的颜色：

```markdown
<font color="red"> 我是红色字体 </font> 或者 <font color="#FF0000"> 我也是红色字体 </font> 
```

<font color="red">我是红色字体</font> 或者
<font color="#FF0000">我也是红色字体</font>

另外，还可以这样设置字体颜色：

```markdown
$\color{green}{绿色} $
```

$\color{green}{绿色} $

## 文字居中

对于标准的 Markdown 文本，默认左对齐，是不支持居中对齐的。我们采用 HTML 语法格式：

```markdown
<center>文字居中</center>
```

<center>文字居中</center>

## 添加背景色

Markdown 本身不支持背景色设置，需要借助 table、tr、td 等表格标签的 bgcolor 属性来实现背景色的功能。举例如下：

```markdown
<table><tr><td bgcolor=#FF4500>
    这里的背景色是：OrangeRed，十六进制颜色值：#FF4500，rgb(255, 69, 0)
</td></tr></table>
```

呈现效果如下：

<table><tr><td bgcolor=#FF4500>
    这里的背景色是：OrangeRed，十六进制颜色值：#FF4500，rgb(255, 69, 0)
</td></tr></table>
