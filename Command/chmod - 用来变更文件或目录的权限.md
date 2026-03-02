# chmod

目录

- [Linux chmod 命令 用来变更文件或目录的权限](#linux-chmod-命令-用来变更文件或目录的权限)
    - [chmod 帮助文档](#chmod-帮助文档)
    - [例子](#例子)

> [!NOTE] 注意
>
> 1. 该命令是 `GNU coreutils` 包中的命令，相关的帮助信息请查看 `man chmod` 或 `info coreutils 'chmod invocation'`。
> 2. 符号连接的权限无法变更，如果用户对符号连接修改权限，其改变会作用在被连接的原始文件。
> 3. 使用 `-R` 选项一定要保留当前用户的执行和读取权限，否则会报错！

## chmod 帮助文档

<pre>
~> chmod --help
用法：chmod [选项]... 模式[,模式]... 文件...
　或：chmod [选项]... 八进制模式 文件...
　或：chmod [选项]... --reference=参考文件 文件...
将每个 <文件> 的模式变更为指定 <模式>。
使用 --reference 时，把每个 <文件> 的模式设置为与 <参考文件> 相同。

  -c, --changes          类似 verbose 选项，但仅在做出修改时进行报告
  -f, --silent, --quiet  不显示大多数错误消息
  -v, --verbose          为每个处理的文件输出一条诊断信息
      --no-preserve-root  不特殊对待 "/"（默认行为）
      --preserve-root    不允许在 "/" 上递归操作
      --reference=参考文件  使用 <参考文件> 的模式而非给定 <模式> 的值
  -R, --recursive        递归修改文件和目录
      --help        显示此帮助信息并退出
      --version        显示版本信息并退出

每个 <模式> 都应当符合此格式："[ugoa]*([-+=]([rwxXst]*|[ugo]))+|[-+=][0-7]+"。

GNU coreutils 在线帮助：<https://www.gnu.org/software/coreutils/>
请向 <http://translationproject.org/team/zh_CN.html> 报告任何翻译错误
完整文档 <https://www.gnu.org/software/coreutils/chmod>
或者在本地使用：info '(coreutils) chmod invocation'
</pre>

## 例子

> 参考`man chmod`文档的`DESCRIPTION`段落得知：
>
> - `u`符号代表当前用户。
> - `g`符号代表和当前用户在同一个组的用户，以下简称组用户。
> - `o`符号代表其他用户。
> - `a`符号代表所有用户。
> - `r`符号代表读权限以及八进制数`4`。
> - `w`符号代表写权限以及八进制数`2`。
> - `x`符号代表执行权限以及八进制数`1`。
> - `X`符号代表如果目标文件是可执行文件或目录，可给其设置可执行权限。
> - `s`符号代表设置权限suid和sgid，使用权限组合`u+s`设定文件的用户的ID位，`g+s`设置组用户ID位。
> - `t`符号代表只有目录或文件的所有者才可以删除目录下的文件。
> - `+`符号代表添加目标用户相应的权限。
> - `-`符号代表删除目标用户相应的权限。
> - `=`符号代表添加目标用户相应的权限，删除未提到的权限。

```shell
linux文件的用户权限说明：

# 查看当前目录（包含隐藏文件）的长格式。
ls -la
  -rw-r--r--   1 user  staff   651 Oct 12 12:53 .gitmodules

# 第1位如果是d则代表目录，是-则代表普通文件。
# 更多详情请参阅info coreutils 'ls invocation'（ls命令的info文档）的'-l'选项部分。
# 第2到4位代表当前用户的权限。
# 第5到7位代表组用户的权限。
# 第8到10位代表其他用户的权限。
```

```shell
# 添加组用户的写权限。
chmod g+w ./test.log

# 删除其他用户的所有权限。
chmod o= ./test.log

# 使得所有用户都没有写权限。
chmod a-w ./test.log

# 当前用户具有所有权限，组用户有读写权限，其他用户只有读权限。
chmod u=rwx, g=rw, o=r ./test.log

# 等价的八进制数表示：
chmod 764 ./test.log

# 将目录以及目录下的文件都设置为所有用户拥有读写权限。
# 注意，使用'-R'选项一定要保留当前用户的执行和读取权限，否则会报错！
chmod -R a=rw ./testdir/

# 根据其他文件的权限设置文件权限。
chmod --reference=./1.log  ./test.log
```
