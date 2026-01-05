---
标题: APT 安装卸载示例
创建时间: 2025-11-13
修改时间: 2025-12-29
标签:
  - APT
---

## 新立得 (Synaptic)

```bash
sudo apt install synaptic # 新立得软件包管理器
```

## APT 安装 Vim

### 1. 更新软件包列表

安装前先更新系统软件包列表，确保获取最新版本：

```bash
sudo apt update
```

### 2. 安装 Vim

根据需求选择安装版本：

```bash
# vim：基础版 Vim（推荐大多数用户）
# vim-gtk3：支持 GUI 和高级功能（如剪贴板）
# vim-nox：无 GUI，但支持 Python/Lua 等脚本
# vim-tiny：极简版 Vim（仅基础功能）
sudo apt install vim
```

### 3. **验证安装**

检查是否安装成功：

```bash
vim --version  # 查看版本信息
which vim      # 查看安装路径
```

## 卸载 Vim 的步骤如下：



### 1. 使用 dpkg 查看已安装的 Vim

首先查看系统安装了哪些 Vim 相关的包：

```bash
dpkg -l | grep vim
```

这会列出所有名称包含 `vim` 的已安装包（例如 `vim`, `vim-common`, `vim-runtime`, `vim-tiny` 等）。

```bash
zonglin@huawei-crem-wxx9:~$ dpkg -l | grep vim
ii  vim                                           2:9.1.0016-1ubuntu7.8                    amd64        Vi IMproved - enhanced vi editor
ii  vim-common                                    2:9.1.0016-1ubuntu7.8                    all          Vi IMproved - Common files
ii  vim-runtime                                   2:9.1.0016-1ubuntu7.8                    all          Vi IMproved - Runtime files
ii  vim-tiny                                      2:9.1.0016-1ubuntu7.8                    amd64        Vi IMproved - enhanced vi editor - compact version
```

### 2. **卸载 Vim 主程序**

根据查询到的包名卸载 Vim。如果默认安装的是基础包，可直接运行：

```bash
# --auto-remove 会同时移除不再需要的依赖包。
sudo apt remove vim --auto-remove
```

### 3. **彻底清除配置文件和残留数据**

使用 `purge` 命令，删除配置文件

```bash
sudo apt purge vim vim-common vim-tiny vim-runtime # 替换为实际查到的包名
```

> [!NOTE]
> 使用 `sudo apt autoremove` 这个命令非常安全，主要用于清理系统更新或卸载软件后**残留的孤立依赖包**。

### 4. 验证是否卸载成功

运行以下命令检查 Vim 是否已移除：

```bash
which vim      # 应返回无结果
vim --version  # 应提示未找到命令
```


## 清理残留（状态为 `rc`）

```bash
zonglin@zonglin-CREM-WXX9:~$ dpkg -l | grep vim
rc  vim-runtime                                   2:9.1.0016-1ubuntu7.8                    all          Vi IMproved - Runtime files

```

1. **`rc` 状态的含义**  
   通过 `dpkg -l` 查看包状态时，`rc` 表示：  
   - **r** (desired action): 包已被标记为 `removed`（已卸载）  
   - **c** (current status): 但配置文件 (`config files`) 仍保留在系统中  

   这意味着 `vim-runtime` 已被卸载，但它的配置文件未被彻底清除。

2. **依赖关系**  
   `vim-runtime` 是 Vim 的核心依赖包，包含 Vim 的运行时文件（如语法高亮、插件支持等）。  
   当安装其他 Vim 变体（如 `vim-gtk3`、`vim-nox`）时，系统会自动安装 `vim-runtime`。  
   若卸载时未明确指定清除 `vim-runtime`，它可能因依赖关系或配置文件残留而保留 `rc` 状态。


### 彻底清除 `vim-runtime`？

1. 自动清理无用包

运行以下命令自动清理残留的依赖和配置文件：

```bash
sudo apt autoremove --purge
```

2. 使用 `purge` 彻底删除

直接清除 `vim-runtime` 的配置文件：

```bash
sudo apt purge vim-runtime
```

### 验证是否彻底清除

```bash
dpkg -l | grep vim
```

如果输出中不再包含 `vim-runtime`，则表示已完全删除。

### 为什么卸载 Vim 时未自动清除 `vim-runtime`？

- 若你通过 `apt remove vim` 卸载，系统默认**保留依赖包**（除非使用 `--auto-remove`）。  
- `vim-runtime` 可能被其他软件包依赖（即使 Vim 已卸载），系统会保留它以避免破坏依赖关系。  


## 💻 清理残留信息

#### 1. 使用 `apt autoremove`
这个命令非常安全，主要用于清理系统更新或卸载软件后残留的孤立依赖包。
```bash
sudo apt autoremove
```
系统会列出将被删除的包，确认无误后输入 `y` 并按回车即可。

#### 2. 使用 `apt remove --purge`
如果你想把某个软件及其所有配置痕迹都清除干净，可以使用这个命令。
```bash
sudo apt remove --purge <软件包名>
```
例如，要彻底清除一个名为`example-package`的软件，就执行：
```bash
sudo apt remove --purge example-package
```

#### 3. 使用 `apt clean` 与 `apt autoclean`
- **`sudo apt clean`**：会清空 `/var/cache/apt/archives/` 目录，删除所有已下载的.deb包。这些包主要用于离线安装或重装，删除后若需要再次安装，系统会重新下载。
- **`sudo apt autoclean`**：相比`clean`更温和，它只删除那些在软件源中已过时、不会再被使用的旧版本软件包缓存。


### 常见问题

- **无法安装**：检查网络连接或镜像源配置（`/etc/apt/sources.list`）。
- **依赖冲突**：使用 `sudo apt --fix-broken install` 修复依赖问题。