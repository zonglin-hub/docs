---
标题: 查看 GNOME 扩展兼容性，并手动手动安装
创建时间: 2026-11-25
修改时间: 2026-11-25
标签:
  - Gnome
  - gnome-extensions
---

## 🐧 查看 GNOME 扩展兼容性，并手动手动安装

### 确定你的 GNOME Shell 版本

首先，你需要知道自己系统上运行的 GNOME Shell 具体版本。这是因为 GNOME 扩展通常针
对特定的 Shell 版本进行开发。

- __打开终端，输入以下命令：__：


    ```bash
    $ gnome-shell --version
    GNOME Shell 50.1
    ```

    命令会返回类似 `GNOME Shell ~` 这样的信息。

### 检查扩展的兼容性信息

#### 在 GNOME Extensions 网站上

GNOME Extensions 网站 (https://extensions.gnome.org) 是查找和安装扩展的首选平
台，它提供了详细的兼容性信息。

1.  __访问扩展页面__：在网站上找到你感兴趣的扩展。
2.  __查看“About”或兼容性部分__：扩展详情页面通常会明确列出其支持的 __GNOME
    Shell 版本范围__（例如，`GNOME 45 ~ 46`）。


#### 检查已安装扩展的元数据 (Metadata)

GNOME 扩展的安装路径主要分为 __用户级__ 和 __系统级__ 两种，每个扩展都以它的唯一
标识符 `<uuid>` 作为文件夹名称。

##### 📁 用户级安装路径（推荐）

这是最常见的方式，通过浏览器或 `gnome-extensions` 命令安装的扩展通常存放在这里。

```bash
~/.local/share/gnome-shell/extensions/<uuid>/
```

这个路径位于你的家目录下，`~` 代表当前用户的主目录（例如`/home/yourusername`）。
用户级安装 __不需要管理员权限__ ，且只对当前登录用户生效。

##### 🖥️ 系统级安装路径（全局）

如果你希望扩展对系统上的 __所有用户__ 都可用，或者通过发行版的包管理器（如
`apt`、`dnf`）安装，它们通常位于：

```bash
/usr/share/gnome-shell/extensions/<uuid>/
```

此目录下的文件通常需要 `sudo` 权限。

1.  __解压扩展包__。
2.  找到并打开解压文件夹中的 __`metadata.json`__ 文件。
3.  查看 __`shell-version`__ 字段。这个字段会明确列出该扩展设计支持的 GNOME
    Shell 版本号。

    ```json
    {
    "name": "Example Extension",
    "description": "An example GNOME Shell extension",
    "shell-version": ["45", "46"]
    }
    ```

如果 __你的 GNOME Shell 版本不在这个列表__ 中，那么该扩展很可能不兼容。



### 📦 手动安装扩展步骤

请严格按照以下步骤操作：

1.  __解压扩展到用户目录__ 手动安装扩展 `~/.local/share/gnome-shell/extensions/`

    > [!NOTE]
    >
    > 请确认扩展文件夹的名称必须与 _metadata.json_ __uuid__ 的值完全一致。如果不
    > 一致，请将文件夹重命名为 __uuid__ 的值

2. 使用 `gnome-extensions` 命令安装
3. 重启 GNOME Shell：__注销当前用户会话再重新登录__。

### 🔧 安装后问题排查

如果你在 __启用扩展__ 这一步没有找到它，或者在启用后效果异常，可以尝试以下方法：

-   __查看扩展错误信息__：

    重启 GNOME Shell 后，在终端里运行以下命令可以查看扩展相关的错误日志，这有助
    于诊断问题：

    ```bash
    journalctl -f -o cat /usr/bin/gnome-shell
    ```

-   __检查版本兼容性__：

    访问该扩展在 [extensions.gnome.org](https://extensions.gnome.org/) 上的官方
    页面（如果存在），查看其支持的 GNOME Shell 版本。你也可以在那里寻找更新的、
    兼容你当前系统的版本。

-   __寻求更新版本__：

    考虑到兼容性问题，强烈建议尝试寻找支持更新 GNOME Shell 版本的魔灯效果扩展。
    例如，在 [extensions.gnome.org](https://extensions.gnome.org/) 上搜索 "Magic
    Lamp" 或 "Genie Effect"，可能会找到其他维护更积极的替代品。