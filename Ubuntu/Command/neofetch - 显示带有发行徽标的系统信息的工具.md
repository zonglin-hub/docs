__Neofetch__ 是一款开源的命令行工具，主要用于在终端中 __直观地显示系统信息__，并以美观的 ASCII 或图像形式展示操作系统徽标（如 Linux 发行版 Logo）。它用简洁的方式汇总硬件、软件和系统配置信息，适合快速查看或分享系统状态。

---

### 主要功能与特点

1. __系统信息展示__  
   - 显示操作系统、内核版本、运行时间、终端类型等。
   - 硬件信息：CPU、GPU、内存、磁盘使用情况等。
   - 软件信息：Shell、桌面环境、主题、图标等。

2. __高度可定制化__  
   - 通过配置文件（`~/.config/neofetch/config.conf`）调整显示内容和样式。
   - 支持自定义 ASCII 艺术、颜色主题，或直接使用系统 Logo 的图像（需终端支持）。

3. __跨平台支持__  
   - 支持 Linux、macOS、Windows（通过 WSL 或 Cygwin）、BSD 等系统。
   - 适配超过 150 种操作系统和发行版的专属 ASCII 徽标。

4. __低资源占用__  
   - 纯 Bash 脚本编写，无需复杂依赖，启动速度快。

---

### 使用场景

- __快速查看系统信息__：运行 `neofetch` 命令即可生成概览。
- __个性化终端__：用户常将其添加到 Shell 配置文件（如 `.bashrc`），在启动终端时自动显示。
- __社区分享__：截图展示自己的系统配置时，增强视觉效果。

---

### 安装方法（示例）

- __Debian/Ubuntu__：  

  ```bash
  sudo apt install neofetch
  ```

- __Arch Linux__：  

  ```bash
  sudo pacman -S neofetch
  ```

- __macOS（Homebrew）__：  

  ```bash
  brew install neofetch
  ```

---

### 同类工具对比__

- __ScreenFetch__：功能类似，但 Neofetch 维护更活跃，定制选项更丰富。
- __LinuxLogo__：专注生成系统 Logo，信息显示较少。

---

### 示例命令

```bash
# 显示默认信息+ASCII Logo
neofetch

# 使用系统LOGO图像（需终端支持）
neofetch --w3m /path/to/logo.png

# 指定显示Ubuntu的ASCII样式（即使当前系统非Ubuntu）
neofetch --ascii_distro ubuntu
```

如果需要进一步配置，可参考其 GitHub 仓库：[https://github.com/dylanaraps/neofetch](https://github.com/dylanaraps/neofetch)
