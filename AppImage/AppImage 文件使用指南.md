# AppImage 文件使用指南

AppImage 是一种在 Linux 上流行的可执行文件格式，它包含了应用程序及其所有依赖。处
理 AppImage 文件主要有两种方式：**直接运行**或**解压提取文件**。

以下是具体方法：

## 一、直接运行（推荐方式）

AppImage 的设计初衷就是无需解压，直接作为独立可执行文件运行。

1. **授予执行权限**

   在终端中，进入 AppImage 文件所在目录，执行：

   ```bash
   chmod +x MyApp.AppImage
   ```

2. **运行程序**

   ```bash
   ./MyApp.AppImage
   ```

## 二、AppImage 运行环境补全

**检查是否已安装**

```bash
# 查看 FUSE 版本
fusermount --version

# 查看已安装的 FUSE 相关包
dpkg -l | grep fuse
```

确保系统已安装 FUSE（大多数现代 Linux 发行版默认已安装）。  

在某些系统中，包名可能不同：

```bash
# Ubuntu 22.04~23.04 及之前版本
sudo apt install libfuse2

# 安装完整 FUSE 工具集
sudo apt install fuse

# Ubuntu 24.04 版本使用
sudo apt install libfuse2t64
```

## 三、解压 AppImage（提取内部文件）

如果你需要查看或提取 AppImage 包内的文件，有以下方法：

### 方法 1：使用 `--appimage-extract` 参数（官方推荐）

大多数现代 AppImage 文件支持自解压。

1. **创建目录并解压**
   
   ```bash
   ./文件名.appimage --appimage-extract
   ```
   
   这会在当前目录创建一个名为 `squashfs-root` 的文件夹，里面就是所有应用程序文
   件。

2. **运行解压后的程序**  
   
   进入该文件夹，找到 `AppRun` 文件或可执行文件直接运行：
   
   ```bash
   cd squashfs-root
   ./AppRun
   ```

### 方法 2：使用 `unsquashfs` 解压 AppImage

AppImage 本质上是 SquashFS 格式的镜像。

1. **安装 squashfs-tools**
   
   - Ubuntu/Debian：
     ```bash
     sudo apt install squashfs-tools
     ```

   - Fedora：
     ```bash
     sudo dnf install squashfs-tools
     ```

   - Arch Linux：
     ```bash
     sudo pacman -S squashfs-tools
     ```

2. **解压文件**

   ```bash
   unsquashfs -f -d ./extracted MyApp.AppImage
   ```
