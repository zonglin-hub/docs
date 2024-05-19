# Linux rm 命令 用于删除给定的文件和目录

**rm 命令** 可以删除一个目录中的一个或多个文件或目录，也可以将某个目录及其下属的所有文件及其子目录均删除掉。对于链接文件，只是删除整个链接文件，而原有文件保持不变。

> [!NOTE] 注意
> 默认 rm 命令不支持在根目录上执行递归删除操作。然而，如果你非得完成这个操作，你需要使用 `--no-preserve-root` 选项。使用 rm 命令要格外小心。因为一旦删除了，就无法再恢复。

目录

- [Linux rm 命令 用于删除给定的文件和目录](#linux-rm-命令-用于删除给定的文件和目录)
    - [选项](#选项)
    - [参数](#参数)
    - [实例](#实例)

## 选项

<pre>
❯ rm --help
用法：rm [选项]... [文件]...
删除 (unlink) 一个或多个 <文件>。

  -f, --force           忽略不存在的文件和参数，且从不询问
  -i                    每次删除前询问
  -I                    在删除超过三个文件或者递归删除前询问一次；此选项比 -i
                          提示次数更少，但仍可以避免大多数错误的发生
      --interactive[=何时]  根据 <何时> 的值进行询问：never、once（同 -I）或者
                          always（同 -i）；如果省略 <何时>，则默认为 always
      --one-file-system  递归删除目录时，跳过所有和该目录所对应的命令行参
                          数不在同一个文件系统上的目录
      --no-preserve-root  不要对 "/" 特殊处理
      --preserve-root[=all]  不要删除 "/"（默认行为）；
                              如添加了 "all" 参数，将拒绝处理与其父目录位于
                              不同设备上的命令行参数
  -r, -R, --recursive   递归删除目录及其内容
  -d, --dir             删除空目录
  -v, --verbose         显示详细步骤
      --help            显示此帮助信息并退出
      --version         显示版本信息并退出

默认情况下，rm 不会删除目录。使用 --recursive（-r 或 -R）选项可删除每个给定
的目录，以及其中的全部内容。

要删除文件名第一个字符为 "-" 的文件（例如 "-foo"），请使用
以下命令之一：
  rm -- -foo

  rm ./-foo

请注意，如果使用rm 来删除文件，通常仍可以将该文件恢复原状。如果想保证
该文件的内容无法还原，请考虑使用shred。

GNU coreutils 在线帮助：<https://www.gnu.org/software/coreutils/>
请向 <http://translationproject.org/team/zh_CN.html> 报告任何翻译错误
完整文档 <https://www.gnu.org/software/coreutils/rm>
或者在本地使用：info '(coreutils) rm invocation'
</pre>

## 参数

文件：指定被删除的文件列表，如果参数中含有目录，则必须加上 `-r` 或者 `-R` 选项。

## 实例

交互式删除当前目录下的文件 test 和 example

```shell
rm -i test example
```

删除当前目录下除隐含文件外的所有文件和子目录

```shell
# rm -r *
```

应注意，这样做是非常危险的!

**删除当前目录下的 package-lock.json 文件**

```shell
find . -name "package-lock.json" -exec rm -rf {} \;
```

**查找 .html 结尾的文件并删除**

```shell
find ./docs -name "*.html" -exec rm -rf {} \;
```

**删除当前项目下 .html 结尾的文件**

```shell
rm -rf *.html
```

**删除当前目录下的 node_modules 目录**

```shell
find . -name 'node_modules' -type d -prune -exec rm -rf '{}' +
```

**删除文件**

```shell
rm test.txt file.txt
```

**删除目录**

```shell
rm -rf testdir
rm -r testdir
```

**删除操作前有确认提示**

> rm -i [文件/目录]

```shell
rm -r -i testdir
```

**批量删除 `icons` 文件夹中的子文件夹中的 data 文件夹**

```shell
rm -rf icons/**/data
```

**rm 忽略不存在的文件或目录**

```shell
rm -f [文件...]
```

**仅在某些场景下确认删除**

```shell
rm -I file1 file2 file3
```

**rm 显示当前删除操作的详情**

```shell
rm -v [文件/目录]
```
