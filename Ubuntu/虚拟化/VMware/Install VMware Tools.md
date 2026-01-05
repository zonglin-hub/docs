### 安装 VMware Tools

**推荐使用开源工具 `open-vm-tools`**（官方推荐且更简单）

### 为什么需要 `open-vm-tools-desktop`？
- `open-vm-tools` 是基础服务，提供虚拟机与宿主机之间的基本通信。VMware 与Windows主机之间复制粘贴就可以使用这个工具
- `open-vm-tools-desktop` 是桌面增强包，它包含与图形界面交互所需的组件（如拖放文件、剪贴板共享、自适应分辨率等）

### Install

**安装基础工具和桌面增强包**：
```bash
sudo apt update
sudo apt install open-vm-tools open-vm-tools-desktop
```


**重启虚拟机**（或仅重启相关服务）：
```bash
sudo systemctl reboot # 或 sudo systemctl restart vmtoolsd
```
