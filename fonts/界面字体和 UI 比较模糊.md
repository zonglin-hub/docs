# 界面字体和 UI 比较模糊

很多朋友在使用 Hyprland/Wayland 的时候，可能会遇到这样一个问题，那就是
Chrome、VSCode 和 Obsidian 这类基于 chromium 的软件的界面字体和 UI 比较模糊，不
够 sharp 和清晰。那么，我这里简要介绍我常用的两种方法。

一种是像 VSCode/Chrome 这种可以直接加 flags 的，那么，我们直接在在配置目录中新建
一个 `code-flags.conf` 然后加上一些启动参数即可，

```conf
--ozone-platform-hint=wayland
--enable-wayland-ime
```

对于 Chrome，我们则需要写一个 `chrome-flags.conf`，

```conf
--enable-features=UseOzonePlatform 
--ozone-platform=wayland 
--enable-wayland-ime
```

## 通过 Desktop File 覆盖修改启动参数

对于不支持直接配置 flags 的应用程序如Obsidian，可通过自定义 desktop file 覆盖系
统默认配置。

### Obsidian 示例

1. 复制系统 desktop file 到本地目录：

    ```bash
    cp /usr/share/applications/obsidian.desktop ~/.local/share/applications/
    ```

2. 编辑本地 desktop file，修改 `Exec` 行：

    ```ini
    Exec=/usr/bin/obsidian %U --enable-features=UseOzonePlatform --ozone-platform=wayland --enable-wayland-ime
    ```

## ⚠️ 验证与故障排除

1. 确认 Wayland 会话正常运行：

    ```bash
    ⋈┈◎ echo $XDG_SESSION_TYPE
    wayland
    ```

2. 验证应用程序是否实际使用 Wayland：

    ```bash
    ⋈┈◎ env | grep WAYLAND # 💎 验证Wayland环境变量
    WAYLAND_DISPLAY=wayland-0
    ```

    如果输出中包含 `WAYLAND_DISPLAY` 等相关变量，说明当前环境是 Wayland。但这只能证
    明你的**桌面环境**是 Wayland，并不绝对保证 VS Code 一定以 Wayland 模式运行（因为
    它可能通过 XWayland 运行）。结合第1种方法判断更靠谱。

**🔧 注意**：应用程序对 Wayland 的支持持续改进，某些参数可能随版本更新而变化，建议
定期检查应用程序的官方文档以获取最新配置信息。

### 🔍 检查VS Code的启动参数（最直接）
查看 VS Code 是否真的带着你设置的参数启动：

*   打开一个终端，运行：
    
    ```bash
    ps aux | grep code
    ```

*   在输出的信息中，找到 VS Code 的主进程命令。如果配置生效，你应该能看到包含
    `--enable-features=UseOzonePlatform --ozone-platform=wayland
    --enable-wayland-ime` 等字样的参数。
