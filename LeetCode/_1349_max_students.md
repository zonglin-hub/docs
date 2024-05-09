# [1349. 参加考试的最大学生数][_1349]

<p>给你一个 <code>m * n</code> 的矩阵 <code>seats</code> 表示教室中的座位分布。如果座位是坏的（不可用），就用 <code>'#'</code> 表示；否则，用 <code>'.'</code> 表示。</p>

<p>学生可以看到左侧、右侧、左上、右上这四个方向上紧邻他的学生的答卷，但是看不到直接坐在他前面或者后面的学生的答卷。请你计算并返回该考场可以容纳的同时参加考试且无法作弊的 最大 学生人数。</p>

<p>学生必须坐在状况良好的座位上。</p>

<p>&nbsp;</p>

<strong>示例 1：</strong>

<pre>
<strong>输入：</strong> seats = [["#",".","#","#",".","#"],
                [".","#","#","#","#","."],
              ["#",".","#","#",".","#"]]
<strong>输出：</strong> 4
<strong>解释：</strong> 教师可以让 4 个学生坐在可用的座位上，这样他们就无法在考试中作弊。
</pre>

<strong>示例 2：</strong>

<pre>
<strong>输入：</strong> seats = [[".","#"],
              ["#","#"],
              ["#","."],
              ["#","#"],
              [".","#"]]
<strong>输出：</strong> 3
<strong>解释：</strong> 让所有学生坐在可用的座位上。
</pre>

<strong>示例 3：</strong>

<pre>
<strong>输入：</strong> seats = [["#",".",".",".","#"],
              [".","#",".","#","."],
              [".",".","#",".","."],
              [".","#",".","#","."],
              ["#",".",".",".","#"]]
<strong>输出：</strong> 10
<strong>解释：</strong> 让学生坐在第 1、3 和 5 列的可用座位上。
</pre>

<p>&nbsp;</p>

<strong>提示：</strong>

- <code>seats</code> 只包含字符 <code>'.'</code> 和 <code>'#'</code>
- <code>m == seats.length</code>
- <code>n == seats[i].length</code>
- <code>1 <= m <= 8</code>
- <code>1 <= n <= 8</code>

<p>&nbsp;</p>

## 方法一：记忆化搜索 + 状态压缩

<strong>思路</strong>

学生在选择座位时，必须满足四个指定的位置都没有人坐，而这四个位置，要不位于当前排，要不位于前一排。因此，某一排的座位上，学生可以选择的座位取决于上一排的落座情况。这提醒我们可以以排为单位来进行动态规划。同时，每一个座位，学生可以选择坐或者不坐，我们可以用一个长为 $n$ 的二进制数字来表示某一排的落座情况，从低到高的第 $j$ 位，如果为 $1$ 则表示这一排的第 $j$ 个位置有人落座，为 $0$ 则表示无人落座。

构造函数 $dp(row,status)$，用来表示当第 $row$ 排学生落座情况为 $status$ 时，第 $row$ 排及其之前所有座位能够容纳最多的学生数。首先判断第 $row$ 排的落座情况是否可能为 $status$ 时，我们可以构造一个函数 $isSingleRowCompliant$ 来辅助判断，主要是判断是否有学生坐了坏的位置和是否有两个学生挨着坐。如果第 $row$ 排的落座情况不可能为 $status$，返回一个极小的负值。接下来需要对前一排的落座情况进行遍历，即求出所有的 $dp(row−1,upperRowStatus)$，并且在这相邻两排的落座情况不会产生作弊的情况下，求出最大的学生数后进行返回。

最后我们调用 $dp$，求出最后一排所有状态下的最大学生数量。因为求解过程中会多次求解同一个状态，所以对动态规划进行记忆化的处理来降低时间复杂度。

<details><summary><strong>Code (Rust)</strong></summary>

```rust
use std::collections::HashMap;

impl Solution {
    pub fn max_students(seats: Vec<Vec<char>>) -> i32 {
        fn is_single_row_compliant(
            seats: &[Vec<char>],
            status: usize,
            n: usize,
            row: usize,
        ) -> bool {
            // for j in 0..n {
            //     if (status >> j) & 1 == 1 {
            //         if seats[row][j] == '#' {
            //             return false;
            //         }
            //         if j > 0 && (status >> (j - 1)) & 1 == 1 {
            //             return false;
            //         }
            //     }
            // }
            // true 等同实现逻辑
            (0..n).all(|j| {
                if (status & (1 << j)) == 0 {
                    true
                } else {
                    seats[row][j] != '#' && (j == 0 || (status & (1 << (j - 1))) == 0)
                }
            })
        }

        fn is_cross_rows_compliant(status: usize, upper_row_status: usize, n: usize) -> bool {
            // for j in 0..n {
            //     if (status >> j) & 1 == 1 {
            //         if j > 0 && (upper_row_status >> (j - 1)) & 1 == 1 {
            //             return false;
            //         }
            //         if j < n - 1 && (upper_row_status >> (j + 1)) & 1 == 1 {
            //             return false;
            //         }
            //     }
            // }
            // true 等同实现逻辑
            (0..n).all(|j| {
                if (status & (1 << j)) == 0 {
                    true
                } else {
                    // 检查当前行的第 j 个座位是否被占用
                    // 如果被占用，检查上一行的第 j-1 个座位是否未被占用（对于 j > 0 的情况）
                    // 或者检查下一行的第 j+1 个座位是否未被占用（对于 j < n - 1 的情况）
                    (j == 0 || (upper_row_status & (1 << (j - 1))) == 0)
                        && (j == n - 1 || (upper_row_status & (1 << (j + 1))) == 0)
                }
            })
        }

        fn bit_count(mut bits: usize) -> i32 {
            bits = bits - ((bits >> 1) & 0x55555555);
            bits = (bits & 0x33333333) + ((bits >> 2) & 0x33333333);
            bits = (bits + (bits >> 4)) & 0x0f0f0f0f;
            bits = (bits + (bits >> 8)) & 0x00ff00ff;
            bits = (bits + (bits >> 16)) & 0x0000ffff;
            bits as i32
        }

        fn dp(
            seats: &Vec<Vec<char>>,
            row: usize,
            status: usize,
            n: usize,
            memo: &mut HashMap<i64, i32>,
        ) -> i32 {
            // let key = ((row << n) + status) as i64; 等同实现逻辑
            let key = ((row << n) | status) as i64;

            if !memo.contains_key(&key) {
                if !is_single_row_compliant(seats, status, n, row) {
                    memo.insert(key, i32::MIN);
                    return i32::MIN;
                }

                let students = bit_count(status);

                if row == 0 {
                    memo.insert(key, students);
                    return students;
                }

                let mut mx = 0;
                for upper_row_status in 0..(1 << n) {
                    if is_cross_rows_compliant(status, upper_row_status, n) {
                        mx = mx.max(dp(seats, row - 1, upper_row_status, n, memo));
                    }
                }
                memo.insert(key, students + mx);
            }
            *memo.get(&key).unwrap()
        }

        let mut mx = 0;
        let m = seats.len();
        let n = seats[0].len();
        let mut memo = HashMap::<i64, i32>::new();
        (0..(1 << n)).for_each(|i| mx = mx.max(dp(&seats, m - 1, i, n, &mut memo)));
        mx
    }
}

# [cfg(test)]
mod tests {
    use crate::leet_code::Solution;

    #[test]
    fn test_max_students() {
        assert_eq!(
            Solution::max_students(vec![
                vec!['#', '.', '#', '#', '.', '#'],
                vec!['.', '#', '#', '#', '#', '.'],
                vec!['#', '.', '#', '#', '.', '#']
            ]),
            4
        );
        assert_eq!(
            Solution::max_students(vec![
                vec!['.', '#'],
                vec!['#', '#'],
                vec!['#', '.'],
                vec!['#', '#'],
                vec!['.', '#']
            ]),
            3
        );
        assert_eq!(
            Solution::max_students(vec![
                vec!['#', '.', '.', '.', '#'],
                vec!['.', '#', '.', '#', '.'],
                vec!['.', '.', '#', '.', '.'],
                vec!['.', '#', '.', '#', '.'],
                vec!['#', '.', '.', '.', '#']
            ]),
            10
        );
    }
}
```

</details>

<strong>复杂度分析</strong>

- 时间复杂度：$O(m \times n \times 2^{2n})$, 状态数量共有 $m \times 2^n$ 种，计算一个状态需要消耗 $O(n \times 2^n)$ 的时间。可以通过预计算所有 $isCrossRowsCompliant$ 的结果来降低时间复杂度到 $O((m + n) \times 2^n)$。
- 空间复杂度：$O(n \times 2^n)$。

[_1349]: https://leetcode.cn/problems/maximum-students-taking-exam/description/
