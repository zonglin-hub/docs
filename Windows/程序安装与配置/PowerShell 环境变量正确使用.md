# PowerShell 环境变量正确使用

在 PowerShell 中，您应该可以直接使用环境变量来改变目录。
但是，如果出现错误，可能是因为环境变量没有被正确解析。

尝试以下步骤：

1. 首先，确保环境变量 `APPDATA` 已经被定义。在 PowerShell 中，您可以使用以下命令来查看所有环境变量：

    ```powershell
    Get-ChildItem Env:
    ```

    查看输出中是否有 `APPDATA` 这一项，并且其值应该是 `C:\Users\zonglin\AppData\Roaming`。

2. 如果 `APPDATA` 环境变量存在，尝试直接使用它来改变目录：

    ```powershell
    cd $env:APPDATA\nushell
    ```

    在这个命令中，`$env:APPDATA` 是 PowerShell 中获取环境变量值的语法。

3. 如果上述命令仍然不起作用，您可以尝试将环境变量的值展开为完整的路径，然后使用它：

```powershell
cd "$env:APPDATA\nushell"
```

请注意，如果您在 PowerShell 之外的其他环境中（比如 cmd 或 NuShell），环境变量的使用语法可能会有所不同。如果您在使用 NuShell，请确保您使用的是 NuShell 支持的语法。如果您在使用cmd，您应该可以使用 `%APPDATA%` 来引用环境变量，例如：

```cmd
cd %APPDATA%\nushell
```

如果问题仍然存在，请检查NuShell是否支持环境变量的展开，或者是否有其他特定的语法要求。
