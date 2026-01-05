好的，在 Ubuntu 24.04 Noble Numbat 上安装 Fish shell 非常简单。Fish 是一个智能、用户友好的现代化 shell，以其开箱即用的强大功能（如自动补全、语法高亮）而闻名。

以下是几种安装方法，推荐使用第一种。

### 方法一：通过官方 APT 仓库安装（推荐）

这是最简单、最直接的方法，因为 Fish 已经包含在 Ubuntu 的主仓库中。

1.  **更新软件包列表**
    首先，打开终端（Terminal），运行以下命令以确保你的软件包列表是最新的：
    ```bash
    sudo apt update
    ```

2.  **安装 Fish**
    使用 `apt` 命令进行安装：
    ```bash
    sudo apt install fish
    ```

3.  **验证安装**
    安装完成后，你可以通过查看版本来确认安装成功：
    ```bash
    fish --version
    ```
    你会看到类似 `fish, version 3.7.0` 的输出。

4.  **启动 Fish**
    只需在终端中输入 `fish` 即可立即启动并体验它：
    ```bash
    fish
    ```
    你会注意到命令提示符变成了彩色的 `>`，并且开始支持智能补全和高亮。

---

### 方法二：通过 Fish 的官方 PPA 安装（获取最新版本）

如果 Ubuntu 仓库中的版本较旧，而你希望获取最新的稳定版，可以使用 Fish 维护的官方 PPA。

1.  **添加 Fish PPA**
    ```bash
    sudo add-apt-repository ppa:fish-shell/release-3
    ```

2.  **更新软件包列表**
    添加 PPA 后，需要再次更新列表：
    ```bash
    sudo apt update
    ```

3.  **安装（或升级） Fish**
    ```bash
    sudo apt install fish
    ```
    如果之前通过方法一安装过旧版本，此命令会将其升级到 PPA 中的最新版本。

---

### 将 Fish 设置为默认 Shell（重要提示）

安装完成后，你可能想将 Fish 设置为你每次打开终端时自动启动的默认 shell。**但在这样做之前，请务必了解：** 有些脚本（特别是为 Bash 编写的脚本）可能与 Fish 不兼容。你可以随时通过输入 `bash` 切换回 Bash。

**安全的方法：先不要设为默认，手动输入 `fish` 启动使用一段时间，确认没有问题后再进行设置。**

如果决定设置为默认 shell，请按以下步骤操作：

1.  **将 Fish 添加到 `/etc/shells` 文件**
    首先，你需要确保 Fish 的路径在合法的 shell 列表中。现代版本的 `apt` 通常会自动完成这一步，但手动确认一下总是好的。
    ```bash
    # 检查 /usr/bin/fish 是否已经在 /etc/shells 中
    grep /usr/bin/fish /etc/shells

    # 如果没有任何输出，说明需要手动添加
    echo /usr/bin/fish | sudo tee -a /etc/shells
    ```

2.  **更改当前用户的默认 shell**
    使用 `chsh` (change shell) 命令：
    ```bash
    chsh -s /usr/bin/fish
    ```
    系统会提示你输入用户密码。

3.  **生效更改**
    **重要：** 此更改不会立即在当前已打开的终端窗口中生效。你需要**完全注销并重新登录**，或者**重启计算机**，之后打开的任何新终端窗口都会默认使用 Fish。

---

### 初始设置与推荐配置

首次启动 Fish 时，它会提供一个友好的欢迎消息，并建议你进行一些初始设置。

1.  **安装 `fisher`（插件管理器）**
    Fish 有一个强大的插件生态系统。我们推荐使用 `fisher` 来管理它们。
    在 Fish shell 中运行以下命令来安装 `fisher`：
    ```bash
    curl -sL https://raw.githubusercontent.com/jorgebucaran/fisher/main/functions/fisher.fish | source && fisher install jorgebucaran/fisher
    ```

2.  **安装一些常用插件**
    例如，安装一个用于显示 Git 状态的主题（如 `Tide`）：
    ```bash
    fisher install IlanCosman/tide@v5
    ```
    安装完成后，运行 `tide configure` 可以进入交互式主题配置。

另一个非常流行的主题是 **`Starship`**，它是一个跨 shell 的提示符，功能极其强大。你需要先安装它：
    ```bash
    # 1. 安装 Starship 本身 (需要 curl)
    curl -sS https://starship.rs/install.sh | sh

    # 2. 在 Fish 配置中启用它
    echo "starship init fish | source" >> ~/.config/fish/config.fish
    ```

3.  **配置 Fish**
    Fish 的配置文件位于 `~/.config/fish/config.fish`。你可以在此文件中添加环境变量、别名和函数。
    -   **添加别名示例**：
        ```bash
        # 编辑配置文件
        nano ~/.config/fish/config.fish
        ```
        在文件中添加：
        ```fish
        # 通用别名
        alias ll "ls -alh"
        alias update "sudo apt update && sudo apt upgrade"

        # 设置 EDITOR 环境变量
        set -gx EDITOR nano
        ```
        保存文件后，运行 `source ~/.config/fish/config.fish` 或重启 Fish 使更改生效。

