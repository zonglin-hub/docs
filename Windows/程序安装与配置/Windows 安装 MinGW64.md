# Windows 安装 MinGW64

- 这里我们需要一个完整的 `gcc` 运行时环境。
  请在 [MinGW64 官网][MinGW64]，[进行下载 `x86_64-win32-seh`][MinGW64-download]。
  这里推荐 [GitHub 文件加速下载工具][Github-accelerated-download]

    [MinGW64]: https://www.mingw-w64.org
    [MinGW64-download]: https://www.mingw-w64.org/downloads/#mingw-w64-builds
    [Github-accelerated-download]: <https://github.akams.cn/>
  
  解压指定目录并设置环境变量，参照以下内容。

    - 并于系统变量 `Path` 中编辑环境变量，指定 `mingw64\bin` 目录。

      ```text
      D:\program\mingw64\bin
      ```

    - 使用 `gcc -v` 测试 gcc 环境是否正常。

        ```text
        PS C:\Windows\System32> gcc -v
        Using built-in specs.
        COLLECT_GCC=D:\program\mingw64\bin\gcc.exe
        COLLECT_LTO_WRAPPER=D:/program/mingw64/bin/../libexec/gcc/x86_64-w64-mingw32/14.2.0/lto-wrapper.exe
        Target: x86_64-w64-mingw32
        Configured with: ../../../src/gcc-14.2.0/configure --host=x86_64-w64-mingw32 ……
        Thread model: win32
        Supported LTO compression algorithms: zlib
        gcc version 14.2.0 (x86_64-win32-seh-rev0, Built by MinGW-Builds project)
        PS C:\Windows\System32>
        ```

- 使用 MinGW64 自带的 make

   `D:\program\mingw64\bin` 里面有 `mingw32-make.exe`，拷贝之后重命名为 `make.exe` 添加到环境变量 `PATH`

## 版本说明

这两个看起来像是编译器或者软件库的文件名，通常用于软件开发。下面是对这些文件名各部分的解释：

- `x86_64-14.2.0-release-win32-seh-msvcrt-rt_v12-rev0.7z`
- `x86_64-14.2.0-release-win32-seh-ucrt-rt_v12-rev0.7z`

1. `x86_64`:
   - 表示软件包是为64位x86架构编译的，这是最常见的个人电脑和服务器处理器架构。
2. `14.2.0`:
   - 表示软件的版本号，这里可能是主版本号、次版本号和修订版本号。
3. `release`:
   - 指示这是一个发行版本，相对于开发版或测试版。
4. `win32`:
   - 尽管名字中有“32”，但在这里它通常表示软件是为Windows操作系统编译的，而不一定意味着它是32位版本。如果是64位软件，这里的“win32”可能是指32位API兼容性。
5. `seh`:
   - 表示Structured Exception Handling（结构化异常处理），这是Windows操作系统提供的一种错误处理机制。
6. `msvcrt` 和 `ucrt`:
   - `msvcrt` 是 Microsoft Visual C++ Runtime 的缩写，表示这个软件包依赖于较旧版本的Microsoft C运行时库。
   - `ucrt` 是 Universal C Runtime 的缩写，表示这个软件包依赖于较新版本的通用C运行时库，这个库从Visual Studio 2015开始引入。
7. `rt_v12`:
   - 可能表示运行时库的版本号，这里“v12”可能是指特定的版本。
8. `rev0`:
   - 表示这是该版本的初始修订版。
9. `.7z`:
   - 表示文件使用了7z格式的压缩，这是一种高效的压缩格式。

总结：

- 第一个文件名 `x86_64-14.2.0-release-win32-seh-msvcrt-rt_v12-rev0.7z` 表示这是一个为64位Windows系统编译的版本14.2.0的软件包，使用MSVCRT运行时库，支持结构化异常处理，并且是发行版rev0，文件使用7z格式压缩。
- 第二个文件名 `x86_64-14.2.0-release-win32-seh-ucrt-rt_v12-rev0.7z` 与第一个类似，但是它使用的是UCRT运行时库而不是MSVCRT。
通常，这类文件名中的差异（如msvcrt和ucrt）表示了软件包依赖的运行时库的不同，可能会影响到软件的兼容性和性能。
