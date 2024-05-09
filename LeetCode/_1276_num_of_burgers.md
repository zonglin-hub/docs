# 1276. [不浪费原料的汉堡制作方案][_1276]

圣诞活动预热开始啦，汉堡店推出了全新的汉堡套餐。为了避免浪费原料，请你帮他们制定合适的制作计划。

给你两个整数 `tomatoSlices` 和 `cheeseSlices`，分别表示番茄片和奶酪片的数目。不同汉堡的原料搭配如下：

- <strong>巨无霸汉堡：</strong> 4 片番茄和 1 片奶酪
- <strong>小皇堡：</strong> 2 片番茄和 1 片奶酪

请你以「total_jumbo, total_small 巨无霸汉堡总数，小皇堡总数」的格式返回恰当的制作方案，使得剩下的番茄片 `tomatoSlices` 和奶酪片 `cheeseSlices` 的数量都是 `0`。

如果无法使剩下的番茄片 `tomatoSlices` 和奶酪片 `cheeseSlices` 的数量为 `0`，就请返回 `[]`。

<p>&nbsp;</p>

<strong>示例 1：</strong>

<pre>
<strong>输入：</strong> tomatoSlices = 16, cheeseSlices = 7
<strong>输出：</strong> [1,6]
<strong>解释：</strong> 制作 1 个巨无霸汉堡和 6 个小皇堡需要 4*1 + 2*6 = 16 片番茄和 1 + 6 = 7 片奶酪。不会剩下原料。
</pre>

<strong>示例 2：</strong>

<pre>
<strong>输入：</strong> tomatoSlices = 17, cheeseSlices = 4
<strong>输出：</strong> []
<strong>解释：</strong> 只制作小皇堡和巨无霸汉堡无法用光全部原料。
</pre>

<strong>示例 3：</strong>

<pre>
<strong>输入：</strong> tomatoSlices = 4, cheeseSlices = 17
<strong>输出：</strong> []
<strong>解释：</strong> 制作 1 个巨无霸汉堡会剩下 16 片奶酪，制作 2 个小皇堡会剩下 15 片奶酪。
</pre>

<strong>示例 4：</strong>

<pre>
<strong>输入：</strong> tomatoSlices = 0, cheeseSlices = 0
<strong>输出：</strong> [0,0]
</pre>

<strong>示例 5：</strong>

<pre>
<strong>输入：</strong> tomatoSlices = 2, cheeseSlices = 1
<strong>输出：</strong> [0,1]
</pre>

<p>&nbsp;</p>

<strong>提示：</strong>

<ul>
    <li><code>0 &lt;= tomatoSlices &lt;= 10<sup>7</sup></code></li>
    <li><code>0 &lt;= tomatoSlices &lt;= 10<sup>7</sup></code></li>
</ul>

## 方法一：数学

设巨无霸汉堡有 $x$ 个，皇堡有 $y$ 个，由于所有的材料都需要用完，因此我们可以得到二元一次方程组：

$$
\begin{cases}
4x + 2y = tomatoSlices \\
x + y = cheeseSlices
\end{cases}
$$

<strong>解得</strong>

<b>让我们重新解这个方程组，并找到 x 和 y 的值。</b>

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

若不满足，则无解。

```rust
impl Solution {
    pub fn num_of_burgers(tomato_slices: i32, cheese_slices: i32) -> Vec<i32> {
        if tomato_slices % 2 != 0
            || tomato_slices < cheese_slices * 2
            || cheese_slices * 4 < tomato_slices
        {
            return vec![];
        }

        vec![
            tomato_slices / 2 - cheese_slices,
            cheese_slices * 2 - tomato_slices / 2,
        ]
    }
}
```

<strong>复杂度分析</strong>

- 时间复杂度： $O(1)$

- 空间复杂度： $O(1)$

[_1276]: https://leetcode.cn/problems/number-of-burgers-with-no-waste-of-ingredients/description/
