---
标题: gnome-extensions 常用命令一览
创建时间: 2026-11-25
修改时间: 2026-11-25
标签:
  - Gnome
  - gnome-extensions
---

### 📋 常用子命令一览

`gnome-extensions` 是 GNOME 桌面环境中用于在命令行管理扩展的核心工具。它的基本语
法是 `gnome-extensions <子命令> [参数...]`，几乎所有的操作都围绕扩展的唯一标识符
__UUID__（例如 `dash-to-dock@micxgx.gmail.com`）来进行。


```bash
$ gnome-extensions
用法：
  gnome-extensions 命令 [参数…]

命令：
  help      打印帮助
  version   打印版本
  enable    启用扩展
  disable   禁用扩展
  reset     重置扩展
  uninstall 卸载扩展
  list      列出扩展
  info      显示扩展信息
  show      显示扩展信息
  prefs     打开扩展首选项
  create    创建扩展
  pack      打包扩展
  install   安装扩展包

使用“gnome-extensions help 命令”以获取详细帮助。
```


#### 🔍 查看与检查

__`list`__：列出扩展，配合选项可精确筛选。
*   `gnome-extensions list`：列出所有已安装的扩展（默认包含用户和系统级）。
*   `--enabled`：只显示已启用的。
*   `--disabled`：只显示已禁用的。
*   `--updates`：只显示有待更新版本的。
*   `--user` / `--system`：分别只显示用户级或系统级扩展。
*   `-d, --details`：显示更详细的信息。

__`info`__ 或 __`show`__：显示指定扩展的详细信息，包括名称、描述和当前状态（如
`ACTIVE`, `INACTIVE`）。
```bash
gnome-extensions info <UUID>
```

#### ⚙️ 启用与禁用

__`enable`__ / __`disable`__：启用或禁用扩展。如果扩展已是目标状态，命令不会做任
何事。
```bash
gnome-extensions enable <UUID>
gnome-extensions disable <UUID>
```
> __注意__：`enable` 命令不会检测扩展自身的错误。启用后，建议用 `info` 命令确认
> 其状态是否为 `ACTIVE`。

__`reset`__：将扩展重置为系统默认状态，通常用于修复因配置错误导致的问题。
```bash
gnome-extensions reset <UUID>
```

#### 📦 安装与卸载

__`install`__：从 `.zip` 包或编译好的 `PACK` 文件安装扩展。
```bash
gnome-extensions install /路径/到/extension.zip
```
*   `--force`：强制重新安装，覆盖已存在的版本。

__`uninstall`__：卸载指定的扩展。
```bash
gnome-extensions uninstall <UUID>
```

#### 🛠️ 配置与开发

__`prefs`__：打开指定扩展的图形化首选项/设置窗口（如果该扩展提供了的话）。
```bash
gnome-extensions prefs <UUID>
```

__`create`__：从模板创建一个新的扩展骨架，适合开发者。
*   `--interactive` 或 `-i`：进入交互模式，引导你输入名称、UUID 等元数据。

__`pack`__：将扩展的源代码目录打包成可分发或安装的 `.zip` 文件。
```bash
gnome-extensions pack /路径/到/扩展源码目录
```
