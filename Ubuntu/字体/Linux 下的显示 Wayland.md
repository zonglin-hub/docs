# 优化 Chrome/Chromium 与 JetBrains 软件在 Hyprland/Wayland 下的显示与输入法支持

在使用 Hyprland/Wayland 组合时，基于 Chromium 的应用程序（如 Chrome、VSCode、Obsidian）和 JetBrains 系列 IDE 可能会遇到界面缩放模糊或输入法无法正常工作的问题。以下提供两种可靠的解决方案。

## 解决方案一：为支持命令行参数的应用程序添加 Flags

对于允许通过配置文件添加启动参数的应用程序（如 Chrome、VSCode 等），可创建对应的 `.conf` 文件进行设置。

### VSCode 配置

创建或编辑 `~/.config/chrome-flags.conf`（适用于 Chromium 系浏览器）或 `~/.config/code-flags.conf`（适用于 VSCode），添加以下内容：

```conf
--enable-features=UseOzonePlatform
--ozone-platform=wayland
--enable-wayland-ime
```

### 注意事项
- 某些应用程序（如最新版本的 Chrome/Chromium）可能已默认启用 Wayland 支持，无需额外配置
- 若存在兼容性问题，可尝试将 `--ozone-platform=wayland` 替换为 `--ozone-platform-hint=auto`

## 解决方案二：通过 Desktop File 覆盖修改启动参数

对于不支持直接配置 flags 的应用程序（如 Obsidian 或 JetBrains 工具），可通过自定义 desktop file 覆盖系统默认配置。

### Obsidian 示例

1. 复制系统 desktop file 到本地目录：
```bash
cp /usr/share/applications/obsidian.desktop ~/.local/share/applications/
```

2. 编辑本地 desktop file，修改 `Exec` 行：
```ini
Exec=/usr/bin/obsidian %U --enable-features=UseOzonePlatform --ozone-platform=wayland --enable-wayland-ime
```

### JetBrains IDE 示例

对于 JetBrains 系列软件，建议使用以下参数：

```ini
Exec=/opt/intellij-idea-ultimate-edition/bin/idea.sh %u -Dawt.toolkit.name=WLToolkit
```

或者更完整的 Wayland 支持参数：
```ini
Exec=/opt/intellij-idea-ultimate-edition/bin/idea.sh %u -Dawt.useSystemAAFontSettings=lcd -Dswing.aatext=true -Dawt.toolkit.name=WLToolkit
```

## 验证与故障排除

1. 确认 Wayland 会话正常运行：
```bash
echo $XDG_SESSION_TYPE
```

2. 验证应用程序是否实际使用 Wayland：
```bash
env | grep WAYLAND
```

3. 如果修改后仍无效，可尝试通过终端直接使用参数启动应用程序，确认有效性后再修改配置文件

## 补充说明

- 部分应用程序可能需要额外设置缩放比例参数，如 `--force-device-scale-factor=1.5`
- 输入法支持需要确保 `--enable-wayland-ime` 参数正确传递
- 某些 JetBrains 软件版本可能需要更新至最新版才能获得完整的 Wayland 支持

以上方法在 Ubuntu 24.04 和最新 Hyprland 组合下测试有效，如遇问题可查阅各应用程序官方文档获取最新 Wayland 支持信息。

---
**注意**：应用程序对 Wayland 的支持持续改进，某些参数可能随版本更新而变化，建议定期检查应用程序的官方文档以获取最新配置信息。

# 在 Ubuntu 24.04 的 Hyprland/Wayland 环境下，你希望通过一些配置让 VS Code 更好地运行。要确认你的 `code-flags.conf` 配置是否生效，可以尝试以下方法。

### 🔧 先确认配置文件是否正确

首先，我们确保配置文件在正确的位置，并且格式正确。

*   **配置文件位置**：VS Code 的 flags 配置文件应该放在 `~/.config/codes-flags.conf` (对于 Arch Linux 社区的 `visual-studio-code-bin` 或 `code` 包)。请确认路径和文件名是否正确。
*   **配置文件格式**：`code-flags.conf` 文件内容应该**每行一个启动参数**，例如：
    ```conf
    --enable-features=UseOzonePlatform
    --ozone-platform=wayland
    --enable-wayland-ime
    ```
    **注意**：确保文件中没有多余的引号、逗号或其他格式错误。这些参数会直接传递给 Electron 框架。

### 🔍 检查配置是否生效

#### 1. 检查VS Code的启动参数（最直接）
查看 VS Code 是否真的带着你设置的参数启动：
*   打开一个终端，运行：
    ```bash
    ps aux | grep code
    ```
*   在输出的信息中，找到 VS Code 的主进程命令。如果配置生效，你应该能看到包含 `--enable-features=UseOzonePlatform --ozone-platform=wayland --enable-wayland-ime` 等字样的参数。

#### 2. 查看VS Code的“关于”窗口（图形化方式）
*   在 VS Code 中，点击左上角的 **帮助 (Help)** 菜单，然后选择 **关于 (About)**。
*   在某些版本的 VS Code 中，这里可能会显示其使用的图形平台（Ozone/Wayland 或 X11）。不过这个方式不一定所有版本都有效，更推荐第一种方法。

#### 3. 验证Wayland环境变量
Wayland 原生应用通常会设置特定的环境变量。你可以在终端中运行：
```bash
env | grep WAYLAND
```
如果输出中包含 `WAYLAND_DISPLAY` 等相关变量，说明当前环境是 Wayland。但这只能证明你的**桌面环境**是 Wayland，并不绝对保证 VS Code 一定以 Wayland 模式运行（因为它可能通过 XWayland 运行）。结合第1种方法判断更靠谱。

#### 4. 观察视觉差异（辅助判断）
Wayland 原生运行的应用程序，在其窗口的**外观和行为**上可能有一些细微的差异：
*   **窗口装饰**：Wayland 原生的窗口装饰通常**更简洁**，可能与系统主题融合得更好。而通过 XWayland 运行的应用程序，其窗口装饰可能看起来略有不同，或者带有传统的边框。
*   **缩放和清晰度**：在 Wayland 原生模式下，UI 缩放和字体渲染**可能更清晰、更平滑**，尤其是在高分屏上。如果你之前感觉模糊，生效后应该能观察到改善。
*   **输入法**：如果 `--enable-wayland-ime` 生效，你应该能正常使用 Wayland 原生输入法（如 IME）。

### ⚠️ 试试其他排查手段

如果发现配置未生效，可以看看以下几点：

*   **重启VS Code**：修改 `code-flags.conf` 后，**务必完全关闭并重新启动 VS Code**，新的启动参数才会生效。
*   **检查VS Code版本和来源**：你通过什么方式安装的 VS Code（例如，是从 Arch Linux 的 AUR 安装的 `visual-studio-code-bin` 或 `code`，还是其他发行版的包，或者是 Snap/Flatpak 包？）。不同的打包方式处理启动脚本和配置文件的位置**可能有所不同**。AUR 中的特定版本明确支持 `~/.config/code-flags.conf`。
*   **查看日志信息**：启动 VS Code 时，可以留意终端（如果你是从终端启动的）是否有任何与 Wayland 或 Ozone 相关的输出信息（包括错误或警告）。
*   **尝试其他应用**：为了确认是 VS Code 的配置问题还是系统环境问题，可以试试其他基于 Electron 且已知支持 Wayland 的应用程序（例如，Chrome/Chromium 浏览器，通过添加类似的启动参数），看它们能否正常以 Wayland 模式运行。

### 💎 总结

确认 `code-flags.conf` 生效，最**可靠**的方法是直接**检查 VS Code 进程的实际启动参数**（方法1）。观察视觉变化（方法4）可以作为辅助判断。

希望这些方法能帮你有效确认配置效果。