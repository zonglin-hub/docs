# Windows 创建一个 符号链接（.symlink） 类型的文件的命令是什么

在 Windows 操作系统中，创建符号链接（.symlink）可以使用 `mklink` 命令。
符号链接是一种指向其他文件或目录的特殊类型的文件，类似于Linux系统中的软链接。
`mklink` 命令可以在命令提示符 `cmd` 或 `PowerShell` 中执行。
以下是使用 `mklink` 命令创建不同类型符号链接的示例：

1. 创建文件符号链接：

    ```cmd
    mklink [链接名称] [目标路径]
    ```

    例如，创建一个名为 `link.txt` 的符号链接，指向 `C:\path\to\original\file.txt` ：

    ```cmd
    mklink link.txt C:\path\to\original\file.txt
    ```

2. 创建目录符号链接：

    ```cmd
    mklink /D [链接目录名称] [目标路径]
    ```

    例如，创建一个名为`link_dir`的目录符号链接，指向`C:\path\to\original\directory`：

    ```cmd
    mklink /D link_dir C:\path\to\original\directory
    ```

Note: 请注意，创建符号链接可能需要管理员权限。
如果当前用户没有足够的权限，请在命令提示符或 `PowerShell` 中以管理员身份运行。
此外，`.symlink` 扩展名并不是 Windows 系统创建符号链接时默认使用的扩展名。
通常情况下，符号链接的名称和扩展名应该与目标文件保持一致，以便于识别和管理。
