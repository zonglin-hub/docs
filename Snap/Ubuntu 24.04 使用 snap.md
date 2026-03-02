---
标题: Ubuntu 24.04 使用 snap
创建时间: 2025-10-24
修改时间: 2025-10-31
标签:
  - Snap
---

![[snapcraft-1.avif]]
Ubuntu Snap 是 Canonical 推出的软件包管理系统，旨在简化软件安装和管理。与传统 `apt` 不同，Snap 将软件及其依赖打包成一个独立的容器（称为 **Snap 包**），支持跨发行版和自动更新。

## 概述

- [Snapcraft 官网](https://snapcraft.io/)：查找更多 Snap 软件
- [官方文档](https://docs.snapcraft.io/)：深入学习 Snap 配置

## 安装与配置

### 前置条件

**确认 Snap 已安装**，Ubuntu 16.04+ 默认已集成 Snap，若未安装，运行：

```bash
sudo apt update && sudo apt install snapd
```

### 安装 Snap Store

```bash
sudo snap install snap-store
```

## 基础命令

### 搜索软件

```bash
snap find chromium  # 搜索 chromium 软件
```

### 安装软件

```bash
# --classic 表示解除严格沙盒限制
sudo snap install code --classic
```

### 列出已安装软件

```bash
snap list
```

### 更新软件

```bash
sudo snap refresh code    # 更新单个软件
sudo snap refresh        # 更新所有已安装的 Snap
```

### 卸载软件

```bash
sudo snap remove code
```

## Snap 的沙盒机制

Snap 默认采用 **严格沙盒隔离**（Strict Confinement），将软件运行在一个受限的容器中：

- **限制范围**：软件只能访问自身的数据和明确授权的资源（如用户主目录、网络、USB 设备等）
- **安全性优势**：防止软件意外或恶意修改系统文件、读取敏感数据

### 为什么需要 `--classic`？

某些软件需要广泛访问系统资源（如开发工具、系统级应用），但严格的沙盒会导致：

- **功能受限**：例如 IDE 需要访问 `/usr`、`/etc` 或其他目录中的编译工具
- **兼容性问题**：传统软件可能依赖全局配置文件或库，无法适应沙盒环境

通过 `--classic` 参数安装的 Snap 包会以 **经典模式（Classic Confinement）** 运行：

- **解除沙盒限制**：软件可以像传统 `.deb` 包一样自由读写系统文件
- **风险提示**：安装时会显示警告，需用户确认

## 运行软件

直接通过名称启动（部分软件需要重启终端或系统）：

```bash
<软件名>    # 例如 code 启动 VS Code
```

若找不到命令，检查 Snap 路径是否在 `PATH` 中（路径通常为 `/snap/bin`）。

## 依赖管理

在 Snap 包管理系统中，**删除某个 Snap 软件时，其依赖关系会一并被清理**。这是因为 Snap 的设计理念是将应用及其**所有依赖**打包成一个独立、自包含的容器。

### Snap 的依赖机制

- **自包含特性**：每个 Snap 包内部已包含运行所需的所有依赖（库、运行时环境等），**不依赖系统级的共享库**
- **隔离性**：依赖仅属于该 Snap 软件，不与其他 Snap 或系统共享

因此，当你删除一个 Snap 软件时：
- **依赖会被自动清除**，因为它们是该 Snap 的私有部分，无法被其他软件复用
- **不会残留无用文件**（除非其他 Snap 共享同一运行时环境）

### 特殊情况：共享的运行时（Runtime）

某些 Snap 软件可能依赖 **共享的运行时环境**（例如 `core18`、`core20`、`gnome-3-38-2004` 等）：

- **运行时的作用**：提供基础库和框架（如 Python、GTK 等），供多个 Snap 软件共用
- **删除逻辑**：
  - 如果删除了某个 Snap 软件，但其他 Snap 仍在使用同一运行时，则 **运行时不会被删除**
  - 当所有依赖该运行时的 Snap 均被删除后，系统会 **自动清理未使用的运行时**

### 验证依赖清理

**查看已安装的 Snap**：

```bash
snap list
```

如果某个运行时仍存在，说明有其他 Snap 依赖它。

**手动清理未使用的运行时**（可选）：

```bash
sudo snap remove --purge <runtime-name>
```

**示例**：假设你安装了 `vlc` 和 `chromium`，两者都依赖 `core20` 运行时：

```bash
sudo snap remove vlc
```

- `vlc` 的私有依赖会被清除
- `core20` 运行时仍保留（因为 `chromium` 还在使用）

## 高级操作

### 禁用自动更新

```bash
sudo snap set system refresh.hold="2030-01-01T00:00:00Z"  # 长期禁用
sudo snap unset system refresh.hold                       # 恢复自动更新
```

### 回滚到旧版本

```bash
sudo snap revert <软件名>    # 回滚到上一个版本
```

### 调整权限（沙盒环境）

某些 Snap 软件需要访问外部资源（如摄像头、USB 设备），需手动授权：

```bash
snap connect <软件名>:<接口> :<接口>    # 例如 snap connect vlc:removable-media
```
