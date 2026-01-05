## 安装C++环境

`Rust` 底层是依赖 `C` 环境的连接器，所以需要先安装 `C/C++` 编译环境, 有两种选择:安装微软的 `msvc` 或者安装 `mingw/cygwin`。

如果使用 `msvc` 的 Visual Studio，只需要安装好 C/C++ 编译环境,然后一路默认就行了，缺点是体积比较大，下载安装都要好几个G，参见：安装 MSVC。

本文主要讲解 `mingw-64` 的环境下的安装操作，

看看下载页面

[https://www.mingw-w64.org/downloads/](https://www.mingw-w64.org/downloads/)

可以看到有很多种`mingw-64`的构建方式，支持`windows`的也不少，我本人目前用的是上图中圆圈标记的那个。

### Mingw-builds 的构建版本

地址为：[https://github.com/niXman/mingw-builds-binaries/releases](https://github.com/niXman/mingw-builds-binaries/releases)

以 [Release of 14.2.0-rt_v12-rev0](https://github.com/niXman/mingw-builds-binaries/releases/tag/14.2.0-rt_v12-rev0) 版本为例

- [i686-14.2.0-release-mcf-dwarf-ucrt-rt_v12-rev0.7z](https://github.com/niXman/mingw-builds-binaries/releases/download/14.2.0-rt_v12-rev0/i686-14.2.0-release-mcf-dwarf-ucrt-rt_v12-rev0.7z)88.6 MBSep 5
- [i686-14.2.0-release-posix-dwarf-msvcrt-rt_v12-rev0.7z](https://github.com/niXman/mingw-builds-binaries/releases/download/14.2.0-rt_v12-rev0/i686-14.2.0-release-posix-dwarf-msvcrt-rt_v12-rev0.7z)88.8 MBSep 5
- [i686-14.2.0-release-posix-dwarf-ucrt-rt_v12-rev0.7z](https://github.com/niXman/mingw-builds-binaries/releases/download/14.2.0-rt_v12-rev0/i686-14.2.0-release-posix-dwarf-ucrt-rt_v12-rev0.7z)88.5 MBSep 5
- [i686-14.2.0-release-win32-dwarf-msvcrt-rt_v12-rev0.7z](https://github.com/niXman/mingw-builds-binaries/releases/download/14.2.0-rt_v12-rev0/i686-14.2.0-release-win32-dwarf-msvcrt-rt_v12-rev0.7z)88.9 MBSep 5
- [i686-14.2.0-release-win32-dwarf-ucrt-rt_v12-rev0.7z](https://github.com/niXman/mingw-builds-binaries/releases/download/14.2.0-rt_v12-rev0/i686-14.2.0-release-win32-dwarf-ucrt-rt_v12-rev0.7z)88.6 MBSep 5
- [x86_64-14.2.0-release-mcf-seh-ucrt-rt_v12-rev0.7z](https://github.com/niXman/mingw-builds-binaries/releases/download/14.2.0-rt_v12-rev0/x86_64-14.2.0-release-mcf-seh-ucrt-rt_v12-rev0.7z)79.9 MBSep 5
- [x86_64-14.2.0-release-posix-seh-msvcrt-rt_v12-rev0.7z](https://github.com/niXman/mingw-builds-binaries/releases/download/14.2.0-rt_v12-rev0/x86_64-14.2.0-release-posix-seh-msvcrt-rt_v12-rev0.7z)80 MBSep 5
- [x86_64-14.2.0-release-posix-seh-ucrt-rt_v12-rev0.7z](https://github.com/niXman/mingw-builds-binaries/releases/download/14.2.0-rt_v12-rev0/x86_64-14.2.0-release-posix-seh-ucrt-rt_v12-rev0.7z)79.9 MBSep 5
- [x86_64-14.2.0-release-win32-seh-msvcrt-rt_v12-rev0.7z](https://github.com/niXman/mingw-builds-binaries/releases/download/14.2.0-rt_v12-rev0/x86_64-14.2.0-release-win32-seh-msvcrt-rt_v12-rev0.7z)80.1 MBSep 5
- [x86_64-14.2.0-release-win32-seh-ucrt-rt_v12-rev0.7z](https://github.com/niXman/mingw-builds-binaries/releases/download/14.2.0-rt_v12-rev0/x86_64-14.2.0-release-win32-seh-ucrt-rt_v12-rev0.7z)80 MBSep 5
- [Source code(zip)](https://github.com/niXman/mingw-builds-binaries/archive/refs/tags/14.2.0-rt_v12-rev0.zip)Feb 21
- [Source code(tar.gz)](https://github.com/niXman/mingw-builds-binaries/archive/refs/tags/14.2.0-rt_v12-rev0.tar.gz)Feb 21

### 如何选择版本

- x86_64
    - 64位系统，不必多说
- i686
    - 继 `i386`、`i486` 和 `i586`（如 Pentium）的升级版本，一种特定的 32 位 x86 架构。

- [mcf](https://github.com/niXman/mingw-builds-binaries/releases/download/14.2.0-rt_v12-rev0/x86_64-14.2.0-release-mcf-seh-ucrt-rt_v12-rev0.7z)
    - **意义**：`mcf`（Microsoft C Runtime）表示使用微软的C运行时库。
    - **适用性**：更适合与微软相关的工具和环境结合使用，通常在Windows环境中表现更好。
    - **异常处理**：支持微软结构化异常处理（SEH），这对于Windows开发来说是标准的异常处理机制。
    - **线程模型**：与微软相关的线程模型兼容性更好。
- posix
    - **意义**：`posix`（Portable Operating System Interface for uniX）表示POSIX标准兼容模式。
    - **适用性**：更适合跨平台开发和对POSIX标准有依赖的应用。如果你的开发需要支持多平台或者与POSIX标准的库和工具兼容，那么选择POSIX可能更合适。
    - **异常处理**：支持POSIX兼容的异常处理，适用于需要符合POSIX标准的应用程序。
    - **线程模型**：支持POSIX线程模型（pthread），更适合跨平台开发。
- win32
    - 用于对目标平台 win32 (32位) 的开发

- msvcrt-rt
    - 微软早期开发的C运行时库，随Windows操作系统和Visual Studio发布。
    - 已经存在很长时间，主要用于兼容旧版本的Windows和旧版应用程序。
    - 对于需要支持老版本Windows和老版本应用程序的开发者适用。
- ucrt-rt
    - 微软推出的新版C运行时库，意在统一C运行时库，使其更易于跨平台兼容。
    - 随Windows 10及更新版本发布，并作为Windows操作系统的一部分分发。
    - 对于需要现代C标准功能和更好兼容性的开发者适用。

以我为例，选择 x86_64-14.2.0-release-posix-seh-ucrt-rt_v12-rev0

然后下载到本地，把`bin`文件夹加到`Path`系统环境变量里面即可，这个适合老手，毕竟这个压缩包才70Mb左右，比安装`msvc`那一套快，又省空间。

至此，C/C++环境就搞定了，就是这么简单。

<pre>
Microsoft Windows [版本 10.0.26100.2605]
(c) Microsoft Corporation。保留所有权利。
 
C:\Users\zonglin>gcc -v
Using built-in specs.
COLLECT_GCC=gcc
COLLECT_LTO_WRAPPER=D:/Program/mingw64/bin/../libexec/gcc/x86_64-w64-mingw32/14.2.0/lto-wrapper.exe
Target: x86_64-w64-mingw32
Configured with: ../../../src/gcc-14.2.0/configure --host=x86_64-w64-mingw32 --build=x86_64-w64-mingw32 --target=x86_64-w64-mingw32 --prefix=/mingw64 --with-sysroot=/c/buildroot/x86_64-1420-win32-seh-ucrt-rt_v12-rev0/mingw64 --enable-host-shared --disable-multilib --enable-languages=c,c++,fortran,lto --enable-libstdcxx-time=yes --enable-threads=win32 --enable-libstdcxx-threads=yes --enable-libgomp --enable-libatomic --enable-lto --enable-graphite --enable-checking=release --enable-fully-dynamic-string --enable-version-specific-runtime-libs --enable-libstdcxx-filesystem-ts=yes --disable-libssp --disable-libstdcxx-pch --disable-libstdcxx-debug --enable-bootstrap --disable-rpath --disable-win32-registry --disable-nls --disable-werror --disable-symvers --with-gnu-as --with-gnu-ld --with-arch=nocona --with-tune=core2 --with-libiconv --with-system-zlib --with-gmp=/c/buildroot/prerequisites/x86_64-w64-mingw32-static --with-mpfr=/c/buildroot/prerequisites/x86_64-w64-mingw32-static --with-mpc=/c/buildroot/prerequisites/x86_64-w64-mingw32-static --with-isl=/c/buildroot/prerequisites/x86_64-w64-mingw32-static --with-pkgversion='x86_64-win32-seh-rev0, Built by MinGW-Builds project' --with-bugurl=https://github.com/niXman/mingw-builds LD_FOR_TARGET=/c/buildroot/x86_64-1420-win32-seh-ucrt-rt_v12-rev0/mingw64/bin/ld.exe --with-boot-ldflags='-pipe -fno-ident -L/c/buildroot/x86_64-1420-win32-seh-ucrt-rt_v12-rev0/mingw64/opt/lib -L/c/buildroot/prerequisites/x86_64-zlib-static/lib -L/c/buildroot/prerequisites/x86_64-w64-mingw32-static/lib  -Wl,--disable-dynamicbase -static-libstdc++ -static-libgcc'
Thread model: win32
Supported LTO compression algorithms: zlib
gcc version 14.2.0 (x86_64-win32-seh-rev0, Built by MinGW-Builds project)

C:\Users\zonglin>
</pre>

