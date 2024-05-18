# 如何在 Ubuntu 22.04 上安装 fonts-cascadia-code

原文地址：<https://installati.one/install-fonts-cascadia-code-ubuntu-22-04/>

---

在本教程中，我们将学习如何在 Ubuntu 22.04 上安装 fonts-cascadia-code。fonts-cascadia-code 是等宽字体，旨在增强 Windows 终端的外观

Github 地址：<https://github.com/microsoft/cascadia-cde>

## 介绍

在本教程中，我们将学习如何在 Ubuntu 22.04 上安装 fonts-cascadia-code 。

## 什么是 fonts-cascadia-code

> Cascadia Code 是 Microsoft 提供的等宽字体。它包括编程连字，旨在用于终端应用程序以及文本编辑器。

有三种方法可以在 Ubuntu 22.04 上安装 fonts-cascadia-code 。我们可以使用 apt-get、apt 和 aptitude 。在以下各节中，我们将介绍每种方法。您可以选择其中之一。

## 使用 apt-get 安装 fonts-cascadia-code

apt-get 使用以下命令更新 apt 数据库。

```shell
sudo apt-get update
```

更新 apt 数据库后，我们可以通过运行以下命令来安装 fonts-cascadia-code use apt-get ：

```shell
sudo apt-get -y install fonts-cascadia-code
```

## 使用 apt 安装 fonts-cascadia-code

apt 使用以下命令更新 apt 数据库。

```shell
sudo apt update
```

更新 apt 数据库后，我们可以通过运行以下命令来安装 fonts-cascadia-code use apt ：

```shell
sudo apt -y install fonts-cascadia-code
```

## 安装 fonts-cascadia-code 使用 aptitude

如果你想遵循这个方法，你可能需要先安装aptitude，因为aptitude通常默认不安装在Ubuntu上。 aptitude 使用以下命令更新 apt 数据库。

```shell
sudo aptitude update
```

更新 apt 数据库后，我们可以通过运行以下命令来安装 fonts-cascadia-code use aptitude ：

```shell
sudo aptitude -y install fonts-cascadia-code
```

## 如何在 Ubuntu 上卸载 fonts-cascadia-code 22.04

要仅卸载软件包， fonts-cascadia-code 我们可以使用以下命令：

```shell
sudo apt-get remove fonts-cascadia-code
```

卸载 fonts-cascadia-code 及其依赖项

要卸载 fonts-cascadia-code Ubuntu 22.04 不再需要的依赖项及其依赖项，我们可以使用以下命令：

```shell
sudo apt-get -y autoremove fonts-cascadia-code
```

## 删除 fonts-cascadia-code 配置和数据

要从 Ubuntu 22.04 中删除 fonts-cascadia-code 配置和数据，我们可以使用以下命令：

```shell
sudo apt-get -y purge fonts-cascadia-code
```

## 删除 fonts-cascadia-code 配置、数据及其所有依赖项

我们可以使用以下命令来删除 fonts-cascadia-code 配置、数据及其所有依赖项，我们可以使用以下命令：

```shell
sudo apt-get -y autoremove --purge fonts-cascadia-code
```

## 总结

在本教程中，我们将学习如何使用不同的包管理工具在 Ubuntu 22.04 上安装 fonts-cascadia-code 包： apt 和 apt-get aptitude 。
