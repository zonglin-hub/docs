# 2660. 保龄球游戏的获胜者

给你两个下标从 `0` 开始的整数数组 `player1` 和 `player2` ，分别表示玩家 1 和玩家 2 击中的瓶数。

保龄球比赛由 `n` 轮组成，每轮的瓶数恰好为 `10` 。

假设玩家在第 `i` 轮中击中 $x_i$ 个瓶子。玩家第 `i` 轮的价值为：

- 如果玩家在该轮的前两轮的任何一轮中击中了 `10` 个瓶子，则为 <code>$2x_i$</code> 。
- 否则，为 <code>$x_i$</code> 。

玩家的得分是其 `n` 轮价值的总和。

返回

- 如果玩家 1 的得分高于玩家 2 的得分，则为 1 ；
- 如果玩家 2 的得分高于玩家 1 的得分，则为 2 ；
- 如果平局，则为 0 。

<p>&nbsp;</p>

<strong>示例 1：</strong>

<pre>
<strong>输入：</strong> player1 = [4,10,7,9], player2 = [6,5,2,3]
<strong>输出：</strong> 1
<strong>解释：</strong> player1 的得分是 4 + 10 + 2*7 + 2*9 = 46 。
player2 的得分是 6 + 5 + 2 + 3 = 16 。
player1 的得分高于 player2 的得分，所以 play1 在比赛中获胜，答案为 1 。
</pre>

<strong>示例 2：</strong>

<pre>
<strong>输入：</strong> player1 = [3,5,7,6], player2 = [8,10,10,2]
<strong>输出：</strong> 2
<strong>解释：</strong> player1 的得分是 3 + 5 + 7 + 6 = 21 。
player2 的得分是 8 + 10 + 2*10 + 2*2 = 42 。
player2 的得分高于 player1 的得分，所以 play2 在比赛中获胜，答案为 2 。
</pre>

<strong>示例 3：</strong>

<pre>
<strong>输入：</strong> player1 = [2,3], player2 = [4,1]
<strong>输出：</strong> 0
<strong>解释：</strong> player1 的得分是 2 + 3 = 5 。
player2 的得分是 4 + 1 = 5 。
player1 的得分等于 player2 的得分，所以这一场比赛平局，答案为 0 。
</pre>

<p>&nbsp;</p>

<strong>提示：</strong>

- <code>n == player1.length == player2.length</code>
- <code>1 &lt;= n &lt;= 1000</code>
- <code>0 &lt;= player1[i]，player2[i] &lt;= 10</code>

<p>&nbsp;</p>

## 方法一：模拟

<strong>思路与算法</strong>

根据题意可以知道，第 $i$ 轮中如果前两轮中存在任意一轮击中 $10$ 个瓶子，则得分为 $2x_i$，否则得分为 $x_i$ 。我们直接模拟即可，假设当前遍历到 第 $i4 轮，检测 $i$ 的前两轮是否击中 10 个瓶子，主要检测数组的第 $i−1,i−2$ 个元素中是否存在等于 10 的元素，如果存在则当前得分翻倍，否则不进行翻倍，累加每轮得分得到总得分别为 $s_1,s_2$，比较二者的大小，根据题意返回即可。

- 如果 $s_1 = s_2$，直接返回 $0$；
- 如果 $s_1 > s_2$，直接返回 $1$；
- 如果 $s_1 < s_2$，直接返回 $2$。

<strong>代码</strong>

```rust
use super::Solution;

use std::cmp::Ordering;

impl Solution {
    pub fn is_winner(player1: Vec<i32>, player2: Vec<i32>) -> i32 {
        fn score(player: Vec<i32>) -> i32 {
            let mut res = 0;

            for i in 0..player.len() {
                if (i > 0 && player[i - 1] == 10) || (i > 1 && player[i - 2] == 10) {
                    res += 2 * player[i];
                } else {
                    res += player[i];
                }
            }
            res
        }

        match score(player1).cmp(&score(player2)) {
            Ordering::Equal => 0,
            Ordering::Less => 2,
            Ordering::Greater => 1,
        }
    }
}

#[cfg(test)]
mod tests {
    use crate::leet_code::Solution;

    #[test]
    fn test_is_winner() {
        assert_eq!(Solution::is_winner(vec![4, 10, 7, 9], vec![6, 5, 2, 3]), 1);
        assert_eq!(Solution::is_winner(vec![3, 5, 7, 6], vec![8, 10, 10, 2]), 2);
        assert_eq!(Solution::is_winner(vec![2, 3], vec![4, 1]), 0);
    }
}
```

<strong>复杂度分析</strong>

- 时间复杂度：$O(n)$，其中 $n$ 表示给定数组的长度。模拟只需要遍历每个数组一遍即可求出每只球队的得分，需要的时间为 $O(n)$。
- 空间复杂度：$O(1)$ 。
