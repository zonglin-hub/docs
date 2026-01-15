---
标题: 安装 Meson
创建时间: 2026-01-15
修改时间: 2026-01-15
标签:
  - Ubuntu24.04
---


在 Ubuntu 24.04 上安装 Meson

**Meson** 是一个现代开源构建系统，旨在快速、用户友好且高度便携。它主要用于编译和管理用 C、C++、Fortran、Rust 及其他语言编写的软件项目。

## 准备环境

确保你安装了 Ninja，因为它是 Meson 默认使用的后端。

你还需要必要的开发工具，如 gcc 或 g++ 编译器：

```bash
sudo apt install -y gcc g++
```
## 安装Meson


首先，获取最新的 Meson 版本号：`tag_name` 元素

```bash
https://api.github.com/repos/mesonbuild/meson/releases/latest
```
下载相应的档案：

```bash
wget -qO meson.tar.gz https://github.com/mesonbuild/meson/releases/latest/download/meson-${MESON_VERSION}.tar.gz
```

为介子创建一个目录并提取文件：

```bash
sudo mkdir /opt/meson
sudo tar xf meson.tar.gz --strip-components=1 -C /opt/meson
```

将主脚本移到更典型的可执行文件名：

```bash
sudo mv /opt/meson/meson.py /opt/meson/meson
```

Add Meson to your system PATH so it's available system-wide:  
将介子添加到你的系统 PATH 中，使其在整个系统范围内可用：

```bash
echo 'export PATH=$PATH:/opt/meson' | sudo tee -a /etc/profile.d/meson.sh
```

To apply the changes, you can either log out and log back in, or run the following command to activate them right away:  
要应用这些更改，你可以选择登出再重新登录，或者立即执行以下命令激活：

```bash
source /etc/profile
```

Verify the installation by checking Meson version:  
通过查看介子版本验证安装情况：

```bash
meson --version
```

Finally, clean up the downloaded archive:  
最后，清理下载的存档：

```bash
rm -rf meson.tar.gz
```

## 测试Meson

我们创建一个简单的项目来验证介子是否正常工作。为项目创建一个新目录并导航进入：

```bash
mkdir helloworld && cd helloworld
```

创建 C 源文件：

```bash
nano main.c
```

向 `main.c` 添加以下代码：

helloworld/main.c

```c
#include <stdio.h>

int main() {
    printf("Hello world\n");

    return 0;
}
```

接下来，创建 Meson 构建定义文件：

```bash
nano meson.build
```

将以下内容添加到 `meson.build`：

helloworld/meson.build

```build
project('hello', 'c')

executable('hello', 'main.c')
```

现在，在`构建`目录中使用 Meson 配置项目：

```bash
meson setup build
```

编译项目：

```bash
meson compile -C build
```

运行编译后的可执行文件：

```bash
./build/hello
```

## 卸载 Meson

要完全卸载 Meson，只需删除它的安装目录：

```bash
sudo rm -rf /opt/meson
```

移除用于配置环境变量的 `meson.sh` 文件：

```bash
sudo rm -rf /etc/profile.d/meson.sh
```