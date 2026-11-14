---
标题:
创建时间: 2026-01-15
修改时间: 2026-01-15
标签:
---



在 Ubuntu 24.04 上安装 Ninja 构建系统

**Ninja** 是一个构建系统，允许从源代码构建二进制文件。Ninja 的诞生源于 Google Chrome 庞大的构建需求。面对成千上万个源文件，当时的构建工具（如 GNU Make）在解析和调度上成为瓶颈。因此，Ninja 的唯一最高目标就是 **“快”**

## 安装Ninja

从 GitHub 下载最新版本的 Ninja：

```bash
sudo wget -qO /usr/local/bin/ninja.gz https://github.com/ninja-build/ninja/releases/latest/download/ninja-linux.zip
```

从归档中解压可执行文件：

```bash
sudo gunzip /usr/local/bin/ninja.gz
```

接下来，设置执行权限：

```bash
sudo chmod a+x /usr/local/bin/ninja
```

现在，`ninja` 命令作为系统范围的通用命令对所有用户开放。

我们可以查看忍者版本：

```bash
ninja --version
```

## 测试Ninja

为了测试目的，我们将用 C 源代码构建一个可执行文件。确保你的系统安装了 GNU 编译器：

```bash
sudo apt install -y gcc
```

创建一个新目录来存储项目文件并导航到那里：

```bash
mkdir helloworld && cd helloworld
```

创建一个 `main.c` 文件，添加以下代码：

```bash
cat > helloworld/main.c << "EOF"
#include <stdio.h>

int main() {
    printf("Hello world\n");

    return 0;
}
EOF
```

默认情况下，Ninja 的配置文件名为 `build.ninja`。创建它：

```bash
vim build.ninja
```

添加以下代码：

helloworld/build.ninja

```ninja
rule compile
  command = gcc -Wall -c $in -o $out

rule link
  command = gcc $in -o $out

build hello.o: compile main.c
build hello: link hello.o
```

执行 `ninja` 命令来构建程序：

```bash
ninja
```

运行程序：

```bash
./hello
```

## 卸载 Ninja

如果不再需要 Ninja，请移除可执行文件：

```bash
sudo rm -rf /usr/local/bin/ninja
```

