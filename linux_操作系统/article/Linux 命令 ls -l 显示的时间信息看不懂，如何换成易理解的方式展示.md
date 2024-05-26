# Linux 命令 ls -l 显示的时间信息看不懂，如何换成易理解的方式展示

`ls -l` 命令显示的时间信息是文件最后一次修改的时间，默认格式是按照当前系统的区域设置来显示的。
如果您觉得这个时间格式不够直观，可以通过环境变量 `TIME_STYLE` 来改变 `ls` 命令显示时间的格式。
以下是一些常用的 `TIME_STYLE` 值，您可以在命令行中设置这个环境变量，然后运行 `ls -l` 命令来查看效果：

1. __long-iso__：显示完整的 ISO 8601 格式的时间（包括年、月、日和小时、分钟）。

   ```bash
   export TIME_STYLE=long-iso
   ls -l
   ```

2. __iso__：显示简化的 ISO 8601 格式的时间（年、月、日）。

   ```bash
   export TIME_STYLE=iso
   ls -l
   ```

3. __locale__：根据您的系统区域设置显示时间。

   ```bash
   export TIME_STYLE=locale
   ls -l
   ```

4. __posix-locale__：显示 POSIX 区域设置格式的时间。

   ```bash
   export TIME_STYLE=posix-locale
   ls -l
   ```

5. __+FORMAT__：自定义时间格式，其中 `FORMAT` 是一个符合 `strftime` 函数格式的字符串。例如，如果您想要显示年月日和小时分钟秒，可以这样设置：

   ```bash
   export TIME_STYLE='+%Y-%m-%d %H:%M:%S'
   ls -l
   ```

> [!NOTE] 注意
> 这些设置只对当前的命令行会话有效。如果您希望永久改变 `ls` 命令的时间显示格式，可以将 `export` 命令添加到您的 shell 配置文件中，例如 `~/.bashrc`。
