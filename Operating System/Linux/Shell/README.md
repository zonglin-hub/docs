# ShellScript

```bash
#!/bin/bash

# 命令可以检测刚才脚本文件的语法是否错误，如果没有回显结果就代表脚本文件没有语法错误
# bash -n hello.sh

# 执行文件
# /bin/bash /root/hello.sh
# bash ./hello.sh

# 显示声明数值变量
declare -i var=5;

# 隐式声明数值变量
num=2;

# 定义函数
test(){
    echo $var
}

# 执行函数
test

# 算术运算-------------------------------------------------------------------------------------------------
echo "算术运算------------------------------------------"

i=$((var + num))

# 使用$(())运算符执行算术表达式var+num
echo "var + num = $i"
echo "var - num = $((var - num))"
echo "var % num = $((var % num))"
echo "var * num = $((var * num))"

# 这里不需要加$，不是引用变量的值，而是修改变量的值
echo "var++: $((var++))"
echo "var--: $((var--))"
echo "++var: $((++var))"
echo "--var: $((--var))"

# (())运算符执行算术表达式自增，自减
((var++))
echo "(())运算 $var"

# 使用$(())执行算术表达式+=、-=、*=、/=、%=
val=$((var += num))
echo "var += num: $val"

val=$((var -= num))
echo "var -= num: $val"

val=$((var *= num))
echo "val *= num : $val"

val=$((var /= num))
echo "val /= num : $val"

val=$((var %= num))
echo "val %= num : $val"


# 比较运算-------------------------------------------------------------------------------------------------
echo "比较运算------------------------------------------"
# 使用[] or [[]] 比较运算
# 等于：-eq
# 不等于：-ne
# 小于等于：-le
# 大于等于：-ge
# 大于：-gt
# 小于：-lt
# 逻辑与：&&
# 逻辑非：!
# 逻辑或：||
# -eq：判断val变量的值是否等于5
# []运算符用来执行条件测试表达式，其执行结果要么为真，要么为假
# []运算符和条件测试表达式之间前后有空格
num=5
if [[ $num -eq 5 ]]; then
    echo "the value of val variable is $num"
fi

# -ne：判断num变量的值是否不等于5
# [[]]运算符用来执行条件测试表达式，其执行结果要么为真，要么为假
# [[]]运算符和条件测试表达式之间前后有空格
if [ $num -ne 4 ]; then
    echo "the value of num variable is not 5"
fi


# -le：判断num变量的值是否小于或等于val变量的值
# test命令关键字用来执行条件测试表达式，其执行结果要么为真，要么为假
if [ $num -le $val ]; then
    echo "the value of num variable is lower or equal than val variable"
fi

if test $num -le $val; then
    echo "the value of num variable is lower or equal than val variable"
fi

# -ge：判断val变量的值是否大于或等于num变量的值
# [[]]运算符用来执行条件测试表达式，其执行结果要么为真，要么为假
# [[]]运算符和条件测试表达式之间前后有空格
if [[ $val -ge $num ]]; then
    echo "the value of val variable is growth or equal than num variable"
fi

# -gt：判断val变量的值是否大于5
# []运算符用来执行条件测试表达式，其执行结果要么为真，要么为假
# []运算符和条件测试表达式之间前后有空格
if [[ $val -gt 2 ]];then
    echo "the value of val variable is growth than 2"
fi

# -lt：判断num变量的值是否小于5
# [[]]运算符用来执行条件测试表达式，其执行结果要么为真，要么为假
# [[]]运算符和条件测试表达式之间前后有空格
if [[ $num -lt 5 ]];then
    echo "the value of num variable is lower than 5"
fi

# 使用(()) 比较运算
# 等于：==
# 不等于：!=
# 小于等于：<=
# 大于等于：>=
# 大于：>
# 小于：<
# 逻辑与：&&
# 逻辑非：!
# 逻辑或：||
# ==判断变量i的值是否等于1
for ((i = 1; i == 1; i++));do
    echo $i
done

# !=判断变量i的值是否不等于3
for ((i = 1; i != 3; i++)); do
    echo $i
done

# <=判断变量i的值是否小于等于4
for ((i = 1; i <= 4; i++)); do
    echo $i
done

# >=判断变量i的值是否大于等于1
for ((i = 5; i >= 1; i--));do
    echo $i
done

# <判断变量i的值是否小于7
# >判断变量i的值是否大于0
# &&表示逻辑与
# ||表示逻辑或
# !表示逻辑非
# 非的优先级大于与，与的优先级大于或
for ((i = 1; i > 0 && i < 7; i++)); do
    echo $i
done

```

## 参考文档

- <https://shellscript.readthedocs.io/zh_CN/latest/index.html>
- <https://www.shellcheck.net/wiki/>
