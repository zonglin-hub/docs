@REM 将当前目录更改为脚本所在的目录。
@REM `%~dp0`是一个批处理文件参数，它扩展为批处理文件所在的完全限定路径。
pushd "%~dp0"

@REM 列出`%SystemRoot%\servicing\Packages\`目录下所有以`Hyper-V`开头的`.mum`文件的名称，并将输出重定向到`hyper-v.txt`文件中。
@REM `/b`参数表示只显示文件名，不显示其他详细信息。
dir /b %SystemRoot%\servicing\Packages\*Hyper-V*.mum >hyper-v.txt

@REM 遍历`hyper-v.txt`文件中的每一行（每个`.mum`文件），并使用`dism`命令在线添加每个包。
@REM `/online`参数表示在当前正在运行的操作系统上操作，`/norestart`表示添加包后不重启计算机。
@REM `findstr /i . hyper-v.txt 2^>nul`用于忽略错误输出。
for /f %%i in ('findstr /i . hyper-v.txt 2^>nul') do dism /online /norestart /add-package:"%SystemRoot%\servicing\Packages\%%i"

@REM 这条命令删除之前创建的`hyper-v.txt`文件，因为不再需要它了。
del hyper-v.txt

@REM 启用Hyper-V的所有功能。
@REM `/online`参数表示在当前操作系统上操作，
@REM `/enable-feature`表示启用一个功能，
@REM `/featurename`后面跟着要启用的功能名称，
@REM `/LimitAccess`限制对Hyper-V虚拟机的访问，
@REM `/ALL`表示启用所有子功能。
Dism /online /enable-feature /featurename:Microsoft-Hyper-V-All /LimitAccess /ALL