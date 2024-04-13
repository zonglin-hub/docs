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

```latex
$$ 
x = {-b \pm \sqrt{b^2-4ac} \over 2a}. 
$$
```

$$
x = {-b \pm \sqrt{b^2-4ac} \over 2a}.
$$

```latex
$$
\left [ – \frac{\hbar^2}{2 m} \frac{\partial^2}{\partial x^2} + V \right ] \Psi = i \hbar \frac{\partial}{\partial t} \Psi
$$
```

$$
\left [ – \frac{\hbar^2}{2 m} \frac{\partial^2}{\partial x^2} + V \right ] \Psi = i \hbar \frac{\partial}{\partial t} \Psi
$$

Alternatively, inline math can be written by wrapping the formula with only a single `$`:

```latex
This is inline: $\mathbf{y} = \mathbf{X}\boldsymbol\beta + \boldsymbol\varepsilon$
```

This is inline: $\mathbf{y} = \mathbf{X}\boldsymbol\beta + \boldsymbol\varepsilon$

Note that Markdown special characters need to be escaped with a backslash so they are treated as math rather than Markdown. For example, `*` and `_` become `\*` and `\_` respectively.

同时也支持 HTML 属性

# 示例

设巨无霸汉堡有 $x$ 个，皇堡有 $y$ 个，由于所有的材料都需要用完，因此我们可以得到二元一次方程组：

$$
\begin{cases}
4x + 2y = tomatoSlices \\
x + y = cheeseSlices
\end{cases}
$$

<strong>解得</strong>

让我们重新解这个方程组，并找到 x 和 y 的值。

我们有两个方程：

$$
\begin{cases}
4x + 2y = tomatoSlices \\
x + y = cheeseSlices
\end{cases}
$$

我们可以使用消元法来解这个方程组。首先，我们可以将第二个方程乘以2，以便我们可以从第一个方程中减去它，消去 $y$：

$$
\begin{cases}
4x + 2y = tomatoSlices \\
2x + 2y = 2 \times cheeseSlices
\end{cases}
$$

现在，我们从第一个方程中减去第二个方程：

$$
\begin{aligned}
(4x + 2y) - (2x + 2y) & = tomatoSlices - 2 \times cheeseSlices \\
2x & = tomatoSlices - 2 \times cheeseSlices \\
x & = \frac{tomatoSlices - 2 \times cheeseSlices}{2}
\end{aligned}
$$

接下来，我们将 $x$ 的值代入第二个原始方程中解出 $y$：

$$
\begin{aligned}
x + y & = cheeseSlices \\
\frac{tomatoSlices - 2 \times cheeseSlices}{2}  & = cheeseSlices
\end{aligned}
$$

现在我们解出 $y$：

$$
\begin{aligned}
y & = cheeseSlices - \frac{tomatoSlices - 2 \times cheeseSlices }{2} \\
y & = cheeseSlices - \frac{tomatoSlices}{2} + cheeseSlices \\
y & = 2 \times cheeseSlices - \frac{tomatoSlices}{2}
\end{aligned}
$$

所以，我们得到了 $x$ 和 $y$ 的值：

$$
\begin{aligned}
x & = \frac{tomatoSlices - 2 \times cheeseSlices }{2} \\
y & = 2 \times cheeseSlices - \frac{tomatoSlices}{2}
\end{aligned}
$$

$$
\begin{cases}
x = \frac{1}{2} \times tomatoSlices - cheeseSlices \\
y = 2 \times cheeseSlices - \frac{1}{2} \times tomatoSlices
\end{cases}
$$

根据题意，$x,y≥0$ 且 $x,y∈N$，因此需要满足：

$$
\begin{cases}
 tomatoSlices = 2k, \ \ \ k \in N \\
 tomatoSlices \ge 2 \times cheeseSlices \\
 4 \times cheeseSlices \ge tomatoSlices
\end{cases}
$$
