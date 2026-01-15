```bash
zonglin@huawei-crem-wxx9:~/文档/Github/mako$ meson build
The Meson build system
Version: 1.10.0
Source dir: /home/zonglin/文档/Github/mako
Build dir: /home/zonglin/文档/Github/mako/build
Build type: native build
Project name: mako
Project version: 1.10.0
C compiler for the host machine: cc (gcc 13.3.0 "cc (Ubuntu 13.3.0-6ubuntu2~24.04) 13.3.0")
C linker for the host machine: cc ld.bfd 2.42
Host machine cpu family: x86_64
Host machine cpu: x86_64
Found pkg-config: YES (/usr/bin/pkg-config) 1.8.1
Run-time dependency cairo found: YES 1.18.0
Run-time dependency pango found: YES 1.52.1
Run-time dependency pangocairo found: YES 1.52.1
Run-time dependency glib-2.0 found: YES 2.80.0
Run-time dependency gobject-2.0 found: YES 2.80.0
Library rt found: YES
Run-time dependency wayland-client found: YES 1.22.0
Found CMake: /usr/bin/cmake (3.28.3)
Run-time dependency wayland-protocols found: NO (tried pkgconfig and cmake)

meson.build:34:17: ERROR: Dependency "wayland-protocols" not found, tried pkgconfig and cmake

A full log can be found at /home/zonglin/文档/Github/mako/build/meson-logs/meson-log.txt
WARNING: Running the setup command as `meson [options]` instead of `meson setup [options]` is ambiguous and deprecated.
```


**安装依赖包（Ubuntu/Debian）**

```bash
sudo apt update
sudo apt install wayland-protocols
```

```bash
zonglin@huawei-crem-wxx9:/etc$ meson --version
1.10.0
zonglin@huawei-crem-wxx9:~/helloworld$ ninja --version
找不到命令 “ninja”，但可以通过以下软件包安装它：
sudo apt install ninja-build
zonglin@huawei-crem-wxx9:~/helloworld$ sudo apt install ninja-build
正在读取软件包列表... 完成
正在分析软件包的依赖关系树... 完成
正在读取状态信息... 完成                 
下列【新】软件包将被安装：
  ninja-build
升级了 0 个软件包，新安装了 1 个软件包，要卸载 0 个软件包，有 0 个软件包未被升级。
需要下载 129 kB 的归档。
解压缩后会消耗 364 kB 的额外空间。
获取:1 http://mirrors.aliyun.com/ubuntu noble/universe amd64 ninja-build amd64 1.11.1-2 [129 kB]
已下载 129 kB，耗时 0秒 (329 kB/s)  
正在选中未选择的软件包 ninja-build。
(正在读取数据库 ... 系统当前共安装有 245253 个文件和目录。)
准备解压 .../ninja-build_1.11.1-2_amd64.deb  ...
正在解压 ninja-build (1.11.1-2) ...
正在设置 ninja-build (1.11.1-2) ...
正在处理用于 man-db (2.12.0-4build2) 的触发器 ...
zonglin@huawei-crem-wxx9:~/helloworld$ meson setup build
The Meson build system
Version: 1.10.0
Source dir: /home/zonglin/helloworld
Build dir: /home/zonglin/helloworld/build
Build type: native build
Project name: hello
Project version: undefined
C compiler for the host machine: cc (gcc 13.3.0 "cc (Ubuntu 13.3.0-6ubuntu2~24.04) 13.3.0")
C linker for the host machine: cc ld.bfd 2.42
Host machine cpu family: x86_64
Host machine cpu: x86_64
Build targets in project: 1

Found ninja-1.11.1 at /usr/bin/ninja
zonglin@huawei-crem-wxx9:~$ 
zonglin@huawei-crem-wxx9:~$ cd 文档/Github/mako/
zonglin@huawei-crem-wxx9:~/文档/Github/mako$ ls
cairo-pixbuf.c  dbus                         icon.c   makoctl.c          notification.c  render.c       wayland.c
config.c        doc                          include  meson.build        pool-buffer.c   string-util.c
contrib         event-loop.c                 LICENSE  meson_options.txt  protocol        surface.c
criteria.c      fr.emersion.mako.service.in  main.c   mode.c             README.md       types.c
zonglin@huawei-crem-wxx9:~/文档/Github/mako$ meson build
The Meson build system
Version: 1.10.0
Source dir: /home/zonglin/文档/Github/mako
Build dir: /home/zonglin/文档/Github/mako/build
Build type: native build
Project name: mako
Project version: 1.10.0
C compiler for the host machine: cc (gcc 13.3.0 "cc (Ubuntu 13.3.0-6ubuntu2~24.04) 13.3.0")
C linker for the host machine: cc ld.bfd 2.42
Host machine cpu family: x86_64
Host machine cpu: x86_64
Found pkg-config: YES (/usr/bin/pkg-config) 1.8.1
Run-time dependency cairo found: YES 1.18.0
Run-time dependency pango found: YES 1.52.1
Run-time dependency pangocairo found: YES 1.52.1
Run-time dependency glib-2.0 found: YES 2.80.0
Run-time dependency gobject-2.0 found: YES 2.80.0
Library rt found: YES
Run-time dependency wayland-client found: YES 1.22.0
Did not find CMake 'cmake'
Found CMake: NO
Run-time dependency wayland-protocols found: NO (tried pkgconfig)

meson.build:34:17: ERROR: Dependency "wayland-protocols" not found, tried pkgconfig

A full log can be found at /home/zonglin/文档/Github/mako/build/meson-logs/meson-log.txt
WARNING: Running the setup command as `meson [options]` instead of `meson setup [options]` is ambiguous and deprecated.
zonglin@huawei-crem-wxx9:~/文档/Github/mako$ cmake --version
找不到命令 “cmake”，但可以通过以下软件包安装它：
sudo snap install cmake  # version 4.2.1, or
sudo apt  install cmake  # version 3.27.8-1build1
输入 “snap info cmake” 以查看更多版本。
zonglin@huawei-crem-wxx9:~/文档/Github/mako$ sudo apt install cmake
正在读取软件包列表... 完成
正在分析软件包的依赖关系树... 完成
正在读取状态信息... 完成                 
将会同时安装下列软件：
  cmake-data librhash0
建议安装：
  cmake-doc cmake-format elpa-cmake-mode
下列【新】软件包将被安装：
  cmake cmake-data librhash0
升级了 0 个软件包，新安装了 3 个软件包，要卸载 0 个软件包，有 0 个软件包未被升级。
需要下载 13.5 MB 的归档。
解压缩后会消耗 48.9 MB 的额外空间。
您希望继续执行吗？ [Y/n] y
获取:1 http://mirrors.aliyun.com/ubuntu noble/main amd64 librhash0 amd64 1.4.3-3build1 [129 kB]
获取:2 http://mirrors.aliyun.com/ubuntu noble/main amd64 cmake-data all 3.28.3-1build7 [2,155 kB]
获取:3 http://mirrors.aliyun.com/ubuntu noble/main amd64 cmake amd64 3.28.3-1build7 [11.2 MB]
已下载 13.5 MB，耗时 5秒 (2,601 kB/s)
正在选中未选择的软件包 librhash0:amd64。
(正在读取数据库 ... 系统当前共安装有 245264 个文件和目录。)
准备解压 .../librhash0_1.4.3-3build1_amd64.deb  ...
正在解压 librhash0:amd64 (1.4.3-3build1) ...
正在选中未选择的软件包 cmake-data。
准备解压 .../cmake-data_3.28.3-1build7_all.deb  ...
正在解压 cmake-data (3.28.3-1build7) ...
正在选中未选择的软件包 cmake。
准备解压 .../cmake_3.28.3-1build7_amd64.deb  ...
正在解压 cmake (3.28.3-1build7) ...
正在设置 librhash0:amd64 (1.4.3-3build1) ...
正在设置 cmake-data (3.28.3-1build7) ...
正在设置 cmake (3.28.3-1build7) ...
正在处理用于 man-db (2.12.0-4build2) 的触发器 ...
正在处理用于 libc-bin (2.39-0ubuntu8.6) 的触发器 ...
zonglin@huawei-crem-wxx9:~/文档/Github/mako$ cmake --version
cmake version 3.28.3

CMake suite maintained and supported by Kitware (kitware.com/cmake).
zonglin@huawei-crem-wxx9:~/文档/Github/mako$ sudo apt install libwayland-client0
正在读取软件包列表... 完成
正在分析软件包的依赖关系树... 完成
正在读取状态信息... 完成                 
libwayland-client0 已经是最新版 (1.22.0-2.1build1)。
libwayland-client0 已设置为手动安装。
升级了 0 个软件包，新安装了 0 个软件包，要卸载 0 个软件包，有 0 个软件包未被升级。

zonglin@huawei-crem-wxx9:~/文档/Github/mako$ sudo apt install libwayland-dev 
正在读取软件包列表... 完成
正在分析软件包的依赖关系树... 完成
正在读取状态信息... 完成                 
libwayland-dev 已经是最新版 (1.22.0-2.1build1)。
升级了 0 个软件包，新安装了 0 个软件包，要卸载 0 个软件包，有 0 个软件包未被升级。

zonglin@huawei-crem-wxx9:~/文档/Github/mako$ sudo apt install wayland-protocols
正在读取软件包列表... 完成
正在分析软件包的依赖关系树... 完成
正在读取状态信息... 完成                 
下列【新】软件包将被安装：
  wayland-protocols
升级了 0 个软件包，新安装了 1 个软件包，要卸载 0 个软件包，有 0 个软件包未被升级。
需要下载 85.2 kB 的归档。
解压缩后会消耗 673 kB 的额外空间。
获取:1 http://mirrors.aliyun.com/ubuntu noble/main amd64 wayland-protocols all 1.34-1 [85.2 kB]
已下载 85.2 kB，耗时 1秒 (126 kB/s)          
正在选中未选择的软件包 wayland-protocols。
(正在读取数据库 ... 系统当前共安装有 248666 个文件和目录。)
准备解压 .../wayland-protocols_1.34-1_all.deb  ...
正在解压 wayland-protocols (1.34-1) ...
正在设置 wayland-protocols (1.34-1) ...
zonglin@huawei-crem-wxx9:~/文档/Github/mako$ meson build
The Meson build system
Version: 1.10.0
Source dir: /home/zonglin/文档/Github/mako
Build dir: /home/zonglin/文档/Github/mako/build
Build type: native build
Project name: mako
Project version: 1.10.0
C compiler for the host machine: cc (gcc 13.3.0 "cc (Ubuntu 13.3.0-6ubuntu2~24.04) 13.3.0")
C linker for the host machine: cc ld.bfd 2.42
Host machine cpu family: x86_64
Host machine cpu: x86_64
Found pkg-config: YES (/usr/bin/pkg-config) 1.8.1
Run-time dependency cairo found: YES 1.18.0
Run-time dependency pango found: YES 1.52.1
Run-time dependency pangocairo found: YES 1.52.1
Run-time dependency glib-2.0 found: YES 2.80.0
Run-time dependency gobject-2.0 found: YES 2.80.0
Library rt found: YES
Run-time dependency wayland-client found: YES 1.22.0
Run-time dependency wayland-protocols found: YES 1.34
Run-time dependency wayland-cursor found: YES 1.22.0
Checking for function "timerfd_create" : YES 
Checking for function "signalfd" : YES 
Run-time dependency libsystemd found: YES 255
Found CMake: /usr/bin/cmake (3.28.3)
Run-time dependency gdk-pixbuf-2.0 found: NO (tried pkgconfig and cmake)
Build-time dependency wayland-scanner found: YES 1.22.0
Program /usr/bin/wayland-scanner found: YES (/usr/bin/wayland-scanner)
Configuring fr.emersion.mako.service using configuration
Found CMake: /usr/bin/cmake (3.28.3)
Build-time dependency scdoc found: NO (tried pkgconfig and cmake)
Build targets in project: 2

mako 1.10.0

    sd-bus provider: libsystemd
    Icons          : NO
    Man pages      : NO

Found ninja-1.11.1 at /usr/bin/ninja
WARNING: Running the setup command as `meson [options]` instead of `meson setup [options]` is ambiguous and deprecated.
zonglin@huawei-crem-wxx9:~/文档/Github/mako$ sudo apt install libgdk-pixbuf2.0-dev
正在读取软件包列表... 完成
正在分析软件包的依赖关系树... 完成
正在读取状态信息... 完成                 
有一些软件包无法被安装。如果您用的是 unstable 发行版，这也许是
因为系统无法达到您要求的状态造成的。该版本中可能会有一些您需要的软件
包尚未被创建或是它们已被从新到(Incoming)目录移出。
下列信息可能会对解决问题有所帮助：

下列软件包有未满足的依赖关系：
 libgdk-pixbuf-2.0-dev : 依赖: gir1.2-gdkpixbuf-2.0 (= 2.42.10+dfsg-3ubuntu3) 但是 2.42.10+dfsg-3ubuntu3.2 正要被安装
                         依赖: libgdk-pixbuf-2.0-0 (= 2.42.10+dfsg-3ubuntu3) 但是 2.42.10+dfsg-3ubuntu3.2 正要被安装
                         依赖: libgdk-pixbuf2.0-bin (= 2.42.10+dfsg-3ubuntu3)
                         依赖: libtiff-dev 但是它将不会被安装
E: 无法修正错误，因为您要求某些软件包保持现状，就是它们破坏了软件包间的依赖关系。

zonglin@huawei-crem-wxx9:~/文档/Github/mako$ 
zonglin@huawei-crem-wxx9:~/文档/Github/mako$ rm -rf build/
zonglin@huawei-crem-wxx9:~/文档/Github/mako$ meson setup build
The Meson build system
Version: 1.10.0
Source dir: /home/zonglin/文档/Github/mako
Build dir: /home/zonglin/文档/Github/mako/build
Build type: native build
Project name: mako
Project version: 1.10.0
C compiler for the host machine: cc (gcc 13.3.0 "cc (Ubuntu 13.3.0-6ubuntu2~24.04) 13.3.0")
C linker for the host machine: cc ld.bfd 2.42
Host machine cpu family: x86_64
Host machine cpu: x86_64
Found pkg-config: YES (/usr/bin/pkg-config) 1.8.1
Run-time dependency cairo found: YES 1.18.0
Run-time dependency pango found: YES 1.52.1
Run-time dependency pangocairo found: YES 1.52.1
Run-time dependency glib-2.0 found: YES 2.80.0
Run-time dependency gobject-2.0 found: YES 2.80.0
Library rt found: YES
Run-time dependency wayland-client found: YES 1.22.0
Run-time dependency wayland-protocols found: YES 1.34
Run-time dependency wayland-cursor found: YES 1.22.0
Checking for function "timerfd_create" : YES 
Checking for function "signalfd" : YES 
Run-time dependency libsystemd found: YES 255
Found CMake: /usr/bin/cmake (3.28.3)
Run-time dependency gdk-pixbuf-2.0 found: NO (tried pkgconfig and cmake)
Build-time dependency wayland-scanner found: YES 1.22.0
Program /usr/bin/wayland-scanner found: YES (/usr/bin/wayland-scanner)
Configuring fr.emersion.mako.service using configuration
Found CMake: /usr/bin/cmake (3.28.3)
Build-time dependency scdoc found: NO (tried pkgconfig and cmake)
Build targets in project: 2

mako 1.10.0

    sd-bus provider: libsystemd
    Icons          : NO
    Man pages      : NO

Found ninja-1.11.1 at /usr/bin/ninja
zonglin@huawei-crem-wxx9:~/文档/Github/mako$ cd build/

zonglin@huawei-crem-wxx9:~/文档/Github/mako$ sudo apt install scdoc
正在读取软件包列表... 完成
正在分析软件包的依赖关系树... 完成
正在读取状态信息... 完成                 
下列【新】软件包将被安装：
  scdoc
升级了 0 个软件包，新安装了 1 个软件包，要卸载 0 个软件包，有 0 个软件包未被升级。
需要下载 15.4 kB 的归档。
解压缩后会消耗 48.1 kB 的额外空间。
获取:1 http://mirrors.aliyun.com/ubuntu noble/universe amd64 scdoc amd64 1.11.2-1 [15.4 kB]
已下载 15.4 kB，耗时 0秒 (35.4 kB/s)
正在选中未选择的软件包 scdoc。
(正在读取数据库 ... 系统当前共安装有 248754 个文件和目录。)
准备解压 .../scdoc_1.11.2-1_amd64.deb  ...
正在解压 scdoc (1.11.2-1) ...
正在设置 scdoc (1.11.2-1) ...
正在处理用于 man-db (2.12.0-4build2) 的触发器 ...

zonglin@huawei-crem-wxx9:~/文档/Github/mako$ meson build
The Meson build system
Version: 1.10.0
Source dir: /home/zonglin/文档/Github/mako
Build dir: /home/zonglin/文档/Github/mako/build
Build type: native build
Project name: mako
Project version: 1.10.0
C compiler for the host machine: cc (gcc 13.3.0 "cc (Ubuntu 13.3.0-6ubuntu2~24.04) 13.3.0")
C linker for the host machine: cc ld.bfd 2.42
Host machine cpu family: x86_64
Host machine cpu: x86_64
Found pkg-config: YES (/usr/bin/pkg-config) 1.8.1
Run-time dependency cairo found: YES 1.18.0
Run-time dependency pango found: YES 1.52.1
Run-time dependency pangocairo found: YES 1.52.1
Run-time dependency glib-2.0 found: YES 2.80.0
Run-time dependency gobject-2.0 found: YES 2.80.0
Library rt found: YES
Run-time dependency wayland-client found: YES 1.22.0
Run-time dependency wayland-protocols found: YES 1.34
Run-time dependency wayland-cursor found: YES 1.22.0
Checking for function "timerfd_create" : YES 
Checking for function "signalfd" : YES 
Run-time dependency libsystemd found: YES 255
Found CMake: /usr/bin/cmake (3.28.3)
Run-time dependency gdk-pixbuf-2.0 found: NO (tried pkgconfig and cmake)
Build-time dependency wayland-scanner found: YES 1.22.0
Program /usr/bin/wayland-scanner found: YES (/usr/bin/wayland-scanner)
Configuring fr.emersion.mako.service using configuration
Build-time dependency scdoc found: YES 1.11.2
Build targets in project: 5

mako 1.10.0

    sd-bus provider: libsystemd
    Icons          : NO
    Man pages      : YES

Found ninja-1.11.1 at /usr/bin/ninja
WARNING: Running the setup command as `meson [options]` instead of `meson setup [options]` is ambiguous and deprecated.

```

### 降级

```bash
zonglin@huawei-crem-wxx9:~/文档/Github/mako$ sudo apt install gir1.2-gdkpixbuf-2.0=2.42.10+dfsg-3ubuntu3 libgdk-pixbuf-2.0-0=2.42.10+dfsg-3ubuntu3
正在读取软件包列表... 完成
正在分析软件包的依赖关系树... 完成
正在读取状态信息... 完成                 
下列软件包将被【降级】：
  gir1.2-gdkpixbuf-2.0 libgdk-pixbuf-2.0-0
升级了 0 个软件包，新安装了 0 个软件包，降级了 2 个软件包，要卸载 0 个软件包，有 0 个软件包未被升级。
需要下载 157 kB 的归档。
解压缩后将会空出 2,048 B 的空间。
您希望继续执行吗？ [Y/n] y
获取:1 http://mirrors.aliyun.com/ubuntu noble/main amd64 libgdk-pixbuf-2.0-0 amd64 2.42.10+dfsg-3ubuntu3 [147 kB]
获取:2 http://mirrors.aliyun.com/ubuntu noble/main amd64 gir1.2-gdkpixbuf-2.0 amd64 2.42.10+dfsg-3ubuntu3 [9,472 B]
已下载 157 kB，耗时 1秒 (135 kB/s)               
dpkg: 警告: 即将把 libgdk-pixbuf-2.0-0:amd64 从 2.42.10+dfsg-3ubuntu3.2 降级到 2.42.10+dfsg-3ubuntu3
(正在读取数据库 ... 系统当前共安装有 248761 个文件和目录。)
准备解压 .../libgdk-pixbuf-2.0-0_2.42.10+dfsg-3ubuntu3_amd64.deb  ...
正在解压 libgdk-pixbuf-2.0-0:amd64 (2.42.10+dfsg-3ubuntu3) 并覆盖 (2.42.10+dfsg-3ubuntu3.2) ...
dpkg: 警告: 即将把 gir1.2-gdkpixbuf-2.0:amd64 从 2.42.10+dfsg-3ubuntu3.2 降级到 2.42.10+dfsg-3ubuntu
3
准备解压 .../gir1.2-gdkpixbuf-2.0_2.42.10+dfsg-3ubuntu3_amd64.deb  ...
正在解压 gir1.2-gdkpixbuf-2.0:amd64 (2.42.10+dfsg-3ubuntu3) 并覆盖 (2.42.10+dfsg-3ubuntu3.2) ...
正在设置 libgdk-pixbuf-2.0-0:amd64 (2.42.10+dfsg-3ubuntu3) ...
正在设置 gir1.2-gdkpixbuf-2.0:amd64 (2.42.10+dfsg-3ubuntu3) ...
正在处理用于 libc-bin (2.39-0ubuntu8.6) 的触发器 ...

zonglin@huawei-crem-wxx9:~/文档/Github/mako/build$ sudo apt install --reinstall gir1.2-gdkpixbuf-2.0 libgdk-pixbuf-2.0-0 libgdk-pixbuf2.0-bin
正在读取软件包列表... 完成
正在分析软件包的依赖关系树... 完成
正在读取状态信息... 完成                 
不能重新安装 libgdk-pixbuf2.0-bin，因为无法下载它。
升级了 0 个软件包，新安装了 0 个软件包，重新安装了 2 个软件包，要卸载 0 个软件包，有 0 个软件包未被升级。
需要下载 157 kB 的归档。
解压缩后会消耗 0 B 的额外空间。
您希望继续执行吗？ [Y/n] y
获取:1 http://mirrors.aliyun.com/ubuntu noble/main amd64 gir1.2-gdkpixbuf-2.0 amd64 2.42.10+dfsg-3ubuntu3 [9,472 B]
获取:2 http://mirrors.aliyun.com/ubuntu noble/main amd64 libgdk-pixbuf-2.0-0 amd64 2.42.10+dfsg-3ubuntu3 [147 kB]
已下载 157 kB，耗时 1秒 (190 kB/s)            
(正在读取数据库 ... 系统当前共安装有 248761 个文件和目录。)
准备解压 .../gir1.2-gdkpixbuf-2.0_2.42.10+dfsg-3ubuntu3_amd64.deb  ...
正在解压 gir1.2-gdkpixbuf-2.0:amd64 (2.42.10+dfsg-3ubuntu3) 并覆盖 (2.42.10+dfsg-3ubuntu3) ...
准备解压 .../libgdk-pixbuf-2.0-0_2.42.10+dfsg-3ubuntu3_amd64.deb  ...
正在解压 libgdk-pixbuf-2.0-0:amd64 (2.42.10+dfsg-3ubuntu3) 并覆盖 (2.42.10+dfsg-3ubuntu3) ...
正在设置 libgdk-pixbuf-2.0-0:amd64 (2.42.10+dfsg-3ubuntu3) ...
正在设置 gir1.2-gdkpixbuf-2.0:amd64 (2.42.10+dfsg-3ubuntu3) ...
正在处理用于 libc-bin (2.39-0ubuntu8.6) 的触发器 ...

```

```bash
zonglin@huawei-crem-wxx9:~/文档/Github/mako$ sudo apt install wayland-protocols
正在读取软件包列表... 完成
正在分析软件包的依赖关系树... 完成
正在读取状态信息... 完成                 
wayland-protocols 已经是最新版 (1.34-1)。
升级了 0 个软件包，新安装了 0 个软件包，要卸载 0 个软件包，有 0 个软件包未被升级。

zonglin@huawei-crem-wxx9:~/文档/Github/mako$ meson build
The Meson build system
Version: 1.10.0
Source dir: /home/zonglin/文档/Github/mako
Build dir: /home/zonglin/文档/Github/mako/build
Build type: native build
Project name: mako
Project version: 1.10.0
C compiler for the host machine: cc (gcc 13.3.0 "cc (Ubuntu 13.3.0-6ubuntu2~24.04) 13.3.0")
C linker for the host machine: cc ld.bfd 2.42
Host machine cpu family: x86_64
Host machine cpu: x86_64
Found pkg-config: YES (/usr/bin/pkg-config) 1.8.1
Run-time dependency cairo found: YES 1.18.0
Run-time dependency pango found: YES 1.52.1
Run-time dependency pangocairo found: YES 1.52.1
Run-time dependency glib-2.0 found: YES 2.80.0
Run-time dependency gobject-2.0 found: YES 2.80.0
Library rt found: YES
Run-time dependency wayland-client found: YES 1.22.0
Run-time dependency wayland-protocols found: YES 1.34
Run-time dependency wayland-cursor found: YES 1.22.0
Checking for function "timerfd_create" : YES 
Checking for function "signalfd" : YES 
Run-time dependency libsystemd found: YES 255
Found CMake: /usr/bin/cmake (3.28.3)
Run-time dependency gdk-pixbuf-2.0 found: NO (tried pkgconfig and cmake)
Build-time dependency wayland-scanner found: YES 1.22.0
Program /usr/bin/wayland-scanner found: YES (/usr/bin/wayland-scanner)
Configuring fr.emersion.mako.service using configuration
Build-time dependency scdoc found: YES 1.11.2
Build targets in project: 5

mako 1.10.0

    sd-bus provider: libsystemd
    Icons          : NO
    Man pages      : YES

Found ninja-1.11.1 at /usr/bin/ninja
WARNING: Running the setup command as `meson [options]` instead of `meson setup [options]` is ambiguous and deprecated.
zonglin@huawei-crem-wxx9:~/文档/Github/mako$ ninja --version 
1.11.1


zonglin@huawei-crem-wxx9:~/文档/Github/mako$ sudo apt install aptitude
正在读取软件包列表... 完成
正在分析软件包的依赖关系树... 完成
正在读取状态信息... 完成                 
将会同时安装下列软件：
  aptitude-common libcwidget4
建议安装：
  apt-xapian-index aptitude-doc-en | aptitude-doc debtags tasksel libcwidget-dev
下列【新】软件包将被安装：
  aptitude aptitude-common libcwidget4
升级了 0 个软件包，新安装了 3 个软件包，要卸载 0 个软件包，有 0 个软件包未被升级。
需要下载 3,270 kB 的归档。
解压缩后会消耗 14.8 MB 的额外空间。
您希望继续执行吗？ [Y/n] y
获取:1 http://mirrors.aliyun.com/ubuntu noble/universe amd64 aptitude-common all 0.8.13-5ubuntu5 [1,800 kB]
获取:2 http://mirrors.aliyun.com/ubuntu noble/universe amd64 libcwidget4 amd64 0.5.18-6build1 [241 kB]
获取:3 http://mirrors.aliyun.com/ubuntu noble/universe amd64 aptitude amd64 0.8.13-5ubuntu5 [1,229 kB]
已下载 3,270 kB，耗时 2秒 (1,489 kB/s)
正在选中未选择的软件包 aptitude-common。
(正在读取数据库 ... 系统当前共安装有 248761 个文件和目录。)
准备解压 .../aptitude-common_0.8.13-5ubuntu5_all.deb  ...
正在解压 aptitude-common (0.8.13-5ubuntu5) ...
正在选中未选择的软件包 libcwidget4:amd64。
准备解压 .../libcwidget4_0.5.18-6build1_amd64.deb  ...
正在解压 libcwidget4:amd64 (0.5.18-6build1) ...
正在选中未选择的软件包 aptitude。
准备解压 .../aptitude_0.8.13-5ubuntu5_amd64.deb  ...
正在解压 aptitude (0.8.13-5ubuntu5) ...
正在设置 libcwidget4:amd64 (0.5.18-6build1) ...
正在设置 aptitude-common (0.8.13-5ubuntu5) ...
正在设置 aptitude (0.8.13-5ubuntu5) ...
update-alternatives: 使用 /usr/bin/aptitude-curses 来在自动模式中提供 /usr/bin/aptitude (aptitude)
正在处理用于 man-db (2.12.0-4build2) 的触发器 ...
正在处理用于 libc-bin (2.39-0ubuntu8.6) 的触发器 ...

zonglin@huawei-crem-wxx9:~/文档/Github/mako$ sudo apt install aptitude
正在读取软件包列表... 完成
正在分析软件包的依赖关系树... 完成
正在读取状态信息... 完成                 
aptitude 已经是最新版 (0.8.13-5ubuntu5)。
升级了 0 个软件包，新安装了 0 个软件包，要卸载 0 个软件包，有 0 个软件包未被升级。


zonglin@huawei-crem-wxx9:~/文档/Github/mako$ meson setup build
The Meson build system
Version: 1.10.0
Source dir: /home/zonglin/文档/Github/mako
Build dir: /home/zonglin/文档/Github/mako/build
Build type: native build
Project name: mako
Project version: 1.10.0
C compiler for the host machine: cc (gcc 13.3.0 "cc (Ubuntu 13.3.0-6ubuntu2~24.04) 13.3.0")
C linker for the host machine: cc ld.bfd 2.42
Host machine cpu family: x86_64
Host machine cpu: x86_64
Found pkg-config: YES (/usr/bin/pkg-config) 1.8.1
Run-time dependency cairo found: YES 1.18.0
Run-time dependency pango found: YES 1.52.1
Run-time dependency pangocairo found: YES 1.52.1
Run-time dependency glib-2.0 found: YES 2.80.0
Run-time dependency gobject-2.0 found: YES 2.80.0
Library rt found: YES
Run-time dependency wayland-client found: YES 1.22.0
Run-time dependency wayland-protocols found: YES 1.34
Run-time dependency wayland-cursor found: YES 1.22.0
Checking for function "timerfd_create" : YES 
Checking for function "signalfd" : YES 
Run-time dependency libsystemd found: YES 255
Found CMake: /usr/bin/cmake (3.28.3)
Run-time dependency gdk-pixbuf-2.0 found: NO (tried pkgconfig and cmake)
Build-time dependency wayland-scanner found: YES 1.22.0
Program /usr/bin/wayland-scanner found: YES (/usr/bin/wayland-scanner)
Configuring fr.emersion.mako.service using configuration
Build-time dependency scdoc found: YES 1.11.2
Build targets in project: 5

mako 1.10.0

    sd-bus provider: libsystemd
    Icons          : NO
    Man pages      : YES

Found ninja-1.11.1 at /usr/bin/ninja
zonglin@huawei-crem-wxx9:~/文档/Github/mako$ ninja -C build
ninja: Entering directory `build'
[37/37] Linking target mako
zonglin@huawei-crem-wxx9:~/文档/Github/mako$ ./build/mako
^Czonglin@huawei-crem-wxx9:~/文档/Github/mako$ sudo ninja -C build install
ninja: Entering directory `build'
[0/1] Installing files
Installing mako to /usr/local/bin
Installing makoctl to /usr/local/bin
Installing doc/mako.1 to /usr/local/share/man/man1
Installing doc/mako.5 to /usr/local/share/man/man5
Installing doc/makoctl.1 to /usr/local/share/man/man1
Installing /home/zonglin/文档/Github/mako/build/fr.emersion.mako.service to /usr/local/share/dbus-1/services
zonglin@huawei-crem-wxx9:~/文档/Github/mako$ 
zonglin@huawei-crem-wxx9:~/文档/Github/mako$ 
zonglin@huawei-crem-wxx9:~/文档/Github/mako$ 
zonglin@huawei-crem-wxx9:~/文档/Github/mako$ which mako
/usr/local/bin/mako
zonglin@huawei-crem-wxx9:~/文档/Github/mako$ 
notify-send "测试标题" "这是一个来自 Mako 的测试通知！"
notify-send -u low "低优先级" "这是一条不太重要的通知"
notify-send -u normal "普通优先级" "这是一条普通通知"
notify-send -u critical "紧急优先级" "这是一条需要你立即注意的通知！"
zonglin@huawei-crem-wxx9:~/文档/Github/mako$ 

```

安装完成后，你可以通过以下步骤测试 `mako` 是否正常工作。

### 1. 启动 Mako 服务
首先，你需要手动启动 `mako` 服务进程。它会在后台运行，监听通知。
```bash
# 前台启动（方便观察日志和测试）
mako
```
**注意**：上面的命令会占用你的终端。如果你想在后台启动，可以这样：
```bash
# 后台启动
mako &
# 或者使用 nohup 保持运行
nohup mako > /tmp/mako.log 2>&1 &
```

### 2. 发送测试通知
打开**另一个终端窗口**，使用 `notify-send` 命令发送一条测试通知：
```bash
notify-send "测试标题" "这是一个来自 Mako 的测试通知！"
```
如果一切正常，你应该会在屏幕的某个角落（通常是右上角）看到一个弹出的通知框。

### 3. 进阶测试与配置
- **测试不同级别的通知**：
  ```bash
  notify-send -u low "低优先级" "这是一条不太重要的通知"
  notify-send -u normal "普通优先级" "这是一条普通通知"
  notify-send -u critical "紧急优先级" "这是一条需要你立即注意的通知！"
  ```
- **发送带图标的通知**（如果你编译时启用了图标支持）：
  ```bash
  notify-send -i dialog-information "带图标的通知" "这条通知应该有一个图标"
  ```
- **列出当前所有通知**（在运行 `mako` 的终端按 `Ctrl+Shift+l` 或配置的快捷键）。
- **关闭所有通知**（在运行 `mako` 的终端按 `Ctrl+Shift+c`）。

### 4. 验证与状态检查
- **检查进程是否运行**：
  ```bash
  pgrep -a mako
  ```
- **查看帮助和配置选项**：
  ```bash
  mako --help
  ```
- **运行模式测试**（退出当前 `mako` 进程后测试）：
  ```bash
  # 以“替换模式”运行，确保只有一个实例
  mako --replace &
  ```

### 5. 停止 Mako
测试完毕后，如果想停止 `mako` 服务：
```bash
# 如果你在前台启动，直接按 Ctrl+C
# 如果在后台，使用 pkill
pkill mako
```

### 后续配置建议
`mako` 默认配置通常就可用。如果你想自定义样式、位置或行为，可以创建配置文件：
```bash
# 编辑配置文件（默认位于 ~/.config/mako/config）
mkdir -p ~/.config/mako
nano ~/.config/mako/config
```
常见配置如修改背景色、位置、超时时间等，可以参考 `man mako` 或[官方文档](https://github.com/emersion/mako)。

现在，请先尝试启动 `mako` 并发送第一条测试通知吧！如果遇到问题，请告诉我具体的错误信息。

### 预期结果与故障排查
- **如果通知成功显示**：恭喜！说明 `mako` 安装和运行成功。
- **如果通知没有弹出**：
  1. 确保 `mako` 进程正在运行（用 `pgrep` 检查）。
  2. 检查你是否运行在 **Wayland 会话** 中（`mako` 是原生的 Wayland 通知守护进程）。在终端运行：
     ```bash
     echo $XDG_SESSION_TYPE
     ```
     如果输出是 `x11`，你需要切换到 Wayland 登录会话（通常在登录管理器选择）。
  3. 查看 `mako` 的输出日志（如果你用 `nohup` 启动，查看 `/tmp/mako.log`）。
- **如果命令未找到**：尝试注销并重新登录，或者重启系统，确保安装路径（通常是 `/usr/local/bin`）被加入你的 `PATH` 环境变量。

