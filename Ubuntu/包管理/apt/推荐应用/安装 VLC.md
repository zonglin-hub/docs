---
标题: Markdown 标题
创建时间: 2024-04-13
修改时间: 2025-10-23
标签:
  - Ubuntu
  - VLC
---
VLC 是一款自由、开源的跨平台多媒体播放器及框架，可播放大多数多媒体文件，以及 DVD、音频 CD、VCD 及各类流媒体协议。
## Ubuntu 24.04 安装 VLC

### ⚙️ 必备解码器支持
若遇 MP4 无法播放（尤其是 H.265/HEVC 编码），安装解码包：
```bash
sudo apt install ffmpeg # 核心多媒体处理工具
sudo apt install libx265-199 # H.265/HEVC编码器
sudo apt install libavcodec-extra # 额外的编解码器库
```


这些包组合在一起提供了一个完整的多媒体处理环境，支持现代视频格式的编解码和转换。

或完整多媒体支持包：
```bash
sudo apt install ubuntu-restricted-extras  # Ubuntu 开箱即用媒体增强包
```
> 注：安装过程中需手动接受 **[EULA 协议](https://www.minecraft.net/zh-hans/eula)**（按 Tab 键选中 "Yes" 回车）。


### 🎥 VLC 媒体播放器
   
   **特点**：开源全能播放器，支持几乎所有视频格式（包括 MP4），无需额外解码器。
   **安装命令**：
 ```bash
 sudo apt update # 更新系统软件包
 sudo apt install vlc # 安装 VLC
 ```
   **优势**：硬件加速、字幕同步、流媒体播放、视频滤镜等高级功能。


---

安装后右键点击 MP4 文件，选择“用其他应用程序打开”即可关联到新安装的播放器。