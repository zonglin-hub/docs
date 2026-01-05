---
标题:
创建时间: 2025-12-07
修改时间: 2025-12-27
标签:
  - Ubuntu24.04
---
> 参考：https://jishuzhan.net/article/1925725199803207681

推荐阅读：[如何在 Ubuntu 23.04 中启用 AppImage 支持](https://www.sysgeek.cn/ubuntu-23-04-appimage/)
### Snipaste F1快捷截图

打开[官网](https://www.snipaste.com/)下载Linux版本AppImage，然后找个目录存放在，拟定目录下，此时双击打不开的，终端执行 `./Snipaste.AppImage`也无法运行，需要安装以下依赖:

```bash
sudo apt install libfuse2t64 
```

随后可以`./Snipaste.AppImage`打开了，但是还不够方便，因此我在桌面创建了个`Snipaste.desktop`文件，内容如下：


```bash
cat > ~/.local/share/applications/Snipaste.desktop << "EOF"
[Desktop Entry]
Type=Application
Name=Snipaste
Comment=None
Exec=/home/zonglin/AppImage/Snipaste-2.11-x86_64.AppImage --no-sandbox
Icon=/home/zonglin/AppImage/logo/snipaste_logo.svg
Terminal=false
EOF
```

> 其中那个图片为我保存的一个Logo，可以自己找个Logo用一下，然后`--no-sandbox`为禁用沙箱打开，我之前测试过不禁用打不开，所以也加上了这个参数。

随后给`Snipaste.desktop`复制到`/usr/share/applications`或`~/.local/share/applications/`文件夹中，就可以在控制台找到了。
