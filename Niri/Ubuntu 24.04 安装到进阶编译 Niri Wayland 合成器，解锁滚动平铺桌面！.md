# debian+niri

[什么？Debian 也能用 niri？| Debian 13 安装到进阶编译 Niri Wayland 合成器，解锁滚动平铺桌面！_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV1iU49zYEYM/?pop_share=1&vd_source=dda6751b7d25c20fbf8655e8aed024cb)

# 安装依赖

```bash
sudo apt install rustup gcc clang libudev-dev libgbm-dev libxkbcommon-dev libegl1-mesa-dev libwayland-dev libinput-dev libdbus-1-dev libsystemd-dev libseat-dev libpipewire-0.3-dev libpango1.0-dev libdisplay-info-dev
```

# 设置rust

```
rustup default stable
cargo install cargo-deb
```

# 构建 niri

```
git clone https://github.com/YaLTeR/niri.git
cd niri
cargo deb
```

# 安装 niri

```
sudo dpkg -i target/debian/niri_25.8.0-1_amd64.deb
sudo apt install -f
```

# 参考

[https://yalter.github.io/niri/Getting-Started.html](https://yalter.github.io/niri/Getting-Started.html)