# Markdown 数学公式

当你需要在编辑器中插入数学公式时，可以使用两个美元符 `$$` 包裹 TeX 或 LaTeX 格式的数学公式来实现。如：

一个简单的数学公式，求圆的面积：

```latex
$$
S=\pi r^2
$$
```

$$
S=\pi r^2
$$

其他公式：

```latex
$$
x \href{why-equal.html}{=} y^2 + 1
$$
```

$$
x \href{why-equal.html}{=} y^2 + 1
$$

```
$$ 
x = {-b \pm \sqrt{b^2-4ac} \over 2a}. 
$$
```

$$
x = {-b \pm \sqrt{b^2-4ac} \over 2a}.
$$

```markdown
$$
\left [ – \frac{\hbar^2}{2 m} \frac{\partial^2}{\partial x^2} + V \right ] \Psi = i \hbar \frac{\partial}{\partial t} \Psi
$$
```

$$
\left [ – \frac{\hbar^2}{2 m} \frac{\partial^2}{\partial x^2} + V \right ] \Psi = i \hbar \frac{\partial}{\partial t} \Psi
$$

Alternatively, inline math can be written by wrapping the formula with only a single `$`:

```
This is inline: $\mathbf{y} = \mathbf{X}\boldsymbol\beta + \boldsymbol\varepsilon$
```

This is inline: $\mathbf{y} = \mathbf{X}\boldsymbol\beta + \boldsymbol\varepsilon$

Note that Markdown special characters need to be escaped with a backslash so they are treated as math rather than Markdown. For example, `*` and `_` become `\*` and `\_` respectively.

同时也支持 HTML 属性，如：

```
$$ 
(x+1)^2 = \class{hidden}{(x+1)(x+1)} 
$$

$$
(x+1)^2 = \cssId{step1}{\style{visibility:hidden}{(x+1)(x+1)}}
$$
```

$$
(x+1)^2 = \class{hidden}{(x+1)(x+1)}
$$

$$
(x+1)^2 = \cssId{step1}{\style{visibility:hidden}{(x+1)(x+1)}}
$$

​

## See also

* [Markdown 之表格 & MathJax](https://zsweety.blog.csdn.net/article/details/78341085)
