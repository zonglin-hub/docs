`proot-distro` 是一个在Termux中使用的工具，它允许用户在 Android 设备上以非 root 用户身份运行各种 Linux 发行版。
这个工具利用 `proot`（一个用户空间虚拟化解决方案）来模拟一个完整的Linux文件系统环境，从而不需要root权限即可在Termux中运行Linux发行版。

`proot-distro` 的主要特点包括：

1. **无需root权限**：用户可以在没有root权限的情况下安装和使用Linux发行版。
2. **易于安装和使用**：`proot-distro` 提供了一系列的命令来简化Linux发行版的安装和管理过程。
3. **多种Linux发行版**：支持多种流行的Linux发行版，如Ubuntu、Debian、Fedora等。
4. **兼容性**：它可以在Termux环境中运行，并与Termux包管理器兼容。

使用`proot-distro`，用户可以在Termux中进行如下操作：

- 安装Linux发行版：

  ```sh
  proot-distro install <distro-name>
  ```

- 启动已安装的Linux发行版：

  ```sh
  proot-distro login <distro-name>
  ```

- 更新Linux发行版：

  ```sh
  proot-distro update <distro-name>
  ```

- 卸载Linux发行版：

  ```sh
  proot-distro remove <distro-name>
  ```
  
通过 `proot-distro` 用户能够在 Android 设备上获得类似于传统 Linux 桌面的体验，进行软件开发、学习或其他需要 Linux 环境的任务。
