# pip 使用文档

## 概述

pip（Python Package Manager）是 Python 的包管理工具，用于查找、下载、安装和卸载 Python 第三方库。它可以帮助我们更方便地管理 Python 项目中的依赖关系，提高开发效率。

## 安装与使用

- 安装如果您使用的是 Python 3.4 及以上版本，那么您已经自带了 pip。如果没有，可以通过以下方式安装：

    - 在命令行中输入 `easy_install pip`（适用于 Python 2.x 版本）；  
    - 或在 [Python 官方下载页面](https://www.python.org/downloads/) 下载最新版本的 Python 安装包，该包已自带 pip。

- 使用 pip 的使用主要通过命令行进行。以下是一些常用的 pip 命令及其用法：

    - **pip install：** 用于安装第三方库。格式为：`pip install <包名>`。例如，要安装 NumPy 库，只需输入`pip install numpy`。
    - **pip uninstall：** 用于卸载已经安装的包。格式为：`pip uninstall <包名>`。例如，要卸载 NumPy 库，只需输入`pip uninstall numpy`。
    - **pip list：** 列出已安装的所有包。
    - **pip freeze：** 查看已安装包的详细信息，包括版本、安装路径等。
    - **pip show：** 查看指定包的详细信息。格式为：`pip show <包名>`。例如，要查看 NumPy 库的详细信息，只需输入`pip show numpy`。
    - **pip search：** 搜索指定包。格式为：`pip search <关键字>`。例如，要搜索 NumPy 库，只需输入`pip search numpy`。

## 高级使用

- 使用 requirements.txt 文件

    在进行项目开发时，我们可以使用 requirements.txt 文件来记录项目所需的依赖库及其版本。只需在文件中逐行列出所需库的名称和版本，然后使用 `pip install -r requirements.txt` 命令进行安装。

- 使用约束文件

    当项目依赖于多个库时，可以使用约束文件（如 constraints.txt）来指定各个库的版本范围。在文件中逐行列出库的名称和版本范围，然后使用 `pip install -r constraints.txt` 命令进行安装。

- 从 Wheels 中安装

    Wheels 是 Python 的一种打包格式，可以方便地安装和管理第三方库。要使用 Wheels 文件进行安装，只需将 Wheels 文件拖放到命令行窗口，或使用 `pip install <Wheels 文件名>` 命令进行安装。

- 配置 pip

    通过编辑 pip 的配置文件（通常位于 `~/.pip/pip.conf` 或 `%APPDATA%\pip\pip.ini` ），可以定制 pip 的行为。

    例如，可以设置默认的镜像站点、启用或禁用缓存等。

    ```ini
    [global]
    index-url = https://pypi.tuna.tsinghua.edu.cn/simple
    [install]
    trusted-host = https://pypi.tuna.tsinghua.edu.cn
    ```

## 参考资料

- [Python pip 安装与使用](https://www.runoob.com/python/python-pip-install.html)
- [Python Pip 基础教程](https://www.liaoxuefeng.com/wiki/1016959663602400)
- [学 Python 还不会用 pip？看这里，详细的 pip 使用教程](https://juejin.cn/post/684490402793376525)
- [Python 零基础教程系列：pip 的安装和使用](https://www.zhihu.com/club/119972197629670784)
- [开发工具之四 Python 中的 pip 安装及使用详解原创](https://blog.csdn.net/qq_34126229/article/details/115173067)
