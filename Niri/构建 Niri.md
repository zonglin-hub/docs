---
标题: 编译/安装 Niri Wayland 合成器，解锁滚动平铺桌面！
创建时间: 2026-03-01
修改时间: 2026-11-15
标签:
  - Ubuntu24.04
  - Ubuntu26.04
  - Debian 13
---

# debian+niri

- [Niri 入门文档](https://yalter.github.io/niri/Getting-Started.html)
- [Debian 13 编译/安装 Niri Wayland 合成器_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV1iU49zYEYM/?pop_share=1&vd_source=dda6751b7d25c20fbf8655e8aed024cb)

## 安装依赖

__Debian/Ubuntu__：

```bash
# Ubuntu24.04 install dev 
sudo apt install rustup gcc clang libudev-dev libgbm-dev libxkbcommon-dev libegl1-mesa-dev libwayland-dev libinput-dev libdbus-1-dev libsystemd-dev libseat-dev libpipewire-0.3-dev libpango1.0-dev libdisplay-info-dev
```

## 设置rust

```bash
rustup default stable
cargo install cargo-deb
```

## 构建 niri

```bash
git clone https://github.com/YaLTeR/niri.git
cd niri
cargo deb
```

## 安装 niri

```bash
sudo dpkg -i target/debian/niri_25.8.0-1_amd64.deb
sudo apt install -f
```