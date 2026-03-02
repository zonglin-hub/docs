Ubuntu 24.04 配置软件源

---

```bash
zonglin@huawei-crem-wxx9:~$ sudo cat /etc/apt/sources.list
[sudo] zonglin 的密码： 
# Ubuntu sources have moved to /etc/apt/sources.list.d/ubuntu.sources
zonglin@huawei-crem-wxx9:~$ sudo cat /etc/apt/sources.list.d/ubuntu.sources
Types: deb
URIs: https://repo.huaweicloud.com/ubuntu/
Suites: noble
Components: main restricted universe multiverse
Signed-By: /usr/share/keyrings/ubuntu-archive-keyring.gpg

Types: deb
URIs: http://security.ubuntu.com/ubuntu/
Suites: noble-security
Components: universe restricted main multiverse
Signed-By: /usr/share/keyrings/ubuntu-archive-keyring.gpg
zonglin@huawei-crem-wxx9:~$ 
zonglin@huawei-crem-wxx9:~$ sudo apt update && sudo apt upgrade -y

```

安装 Ubuntu 24.04 LTS 之后必须做的 20 件事

1. 更新系统与镜像源：选择最快镜像服务器，启用推荐软件源，执行 `sudo apt update && sudo apt upgrade -y`，重启系统。
2. 安装硬件驱动：通过"附加驱动程序"检查安装必要驱动，尤其是显卡驱动。
3. 安装基础软件：通过终端安装 VLC、GIMP、GParted、Synaptic，从官网安装 Chrome。
4. 启用 Preload：运行 `sudo apt install preload` 优化应用启动速度。
5. 配置 Dash 栏：个性化左侧启动栏，设置 Super 键+数字快捷启动。
6. 设置系统备份：安装 TimeShift，创建系统快照，建议存储在独立设备。
7. 启用深色主题：通过系统设置切换深色主题。
8. 优化 DNS：配置 Google DNS (8.8.8.8, 8.8.4.4) 提升网络访问速度。
9. 增强 Firefox：在 about:config 中启用硬件加速和渲染优化。
10. 安装多媒体支持：安装 Ubuntu Restricted Extras 获得编解码支持。
11. 配置夜间模式：启用自动调节屏幕色温功能。
12. 安装 GNOME Tweaks：安装系统调整工具，注意避免修改光标主题。
13. 添加 GNOME 扩展：访问 extensions.gnome.org 安装所需扩展。
14. 美化桌面：安装并应用额外的图标和 GTK 主题。
15. 优化全局搜索：自定义搜索范围和显示顺序。
16. 调整系统设置：配置通知、隐私和云服务选项。
17. 配置游戏支持：安装 Steam，启用 Proton 运行 Windows 游戏。
18. 设置显示缩放：针对高分屏启用分数缩放（如 125%）。
19. 启用防火墙：安装配置 Gufw 提供额外安全保护。
20. 系统清理：安装 Bleachbit，建议每六个月清理一次系统。
