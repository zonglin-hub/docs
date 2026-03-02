# systemd-analyze

**systemd-analyze** 是一个 Linux 系统自带的工具，用于分析系统启动性能。它可以显示系统启动过程中的性能统计数据、获取 systemd 系统管理器的状态与跟踪信息、校验单元文件的正确性。

使用 systemd-analyze 命令可以生成服务启动时间的SVG图表

```bash
❯ systemd-analyze plot > ~/boot.svg
❯ ls
boot.svg
```


使用浏览器打开查看即可


---

你的 `systemd-analyze plot` 输出（保存为 `boot.svg`）显示了系统启动的详细时间线。我来帮你解读一下这个启动性能报告：

---

### 📊 启动时间总览

从文件开头可以看到：

```
Startup finished in 4.444s (firmware) + 2.742s (loader) + 1.425s (kernel) + 7.774s (userspace) = 16.386s 
graphical.target reached after 7.677s in userspace.
```

- **总启动时间：16.386 秒**
- **Firmware (BIOS/UEFI)：4.444s**
- **Loader (GRUB/systemd-boot)：2.742s**
- **Kernel：1.425s**
- **Userspace (systemd 和服务)：7.774s**
- **图形界面就绪时间：7.677s**

---

### 📈 解读 SVG 图表

这个 SVG 是一个时间线图，横轴是时间（单位：秒），纵轴是各个 systemd 单元（服务、挂载点、设备等）。

- **颜色含义**：
  - `firmware`：灰色
  - `loader`：灰色
  - `kernel`：灰色
  - `active`（服务运行中）：浅红色
  - `activating`（启动中）：红色
  - `deactivating`（停止中）：深红色
  - `security`（安全相关）：浅绿色
  - `generators`：浅蓝色
  - `unitsload`：蓝色

---

### 🔍 关键服务启动时间

从图中可以看到很多服务的启动时间，例如：

- `systemd-journald.service`：82ms
- `systemd-udevd.service`：58ms
- `systemd-modules-load.service`：75ms
- `snap-*.mount`（Snap 包挂载）：约 30ms 每个
- `dev-loop*.device`（Loop 设备）：约 60-100ms 每个

---

### 🚀 优化建议

如果你的启动时间较长（16s 属于中等偏上），可以考虑：

1. **禁用不必要的服务**：
   ```bash
   sudo systemctl disable some-service.service
   ```

2. **使用 `systemd-analyze blame` 查看最耗时的服务**：
   ```bash
   systemd-analyze blame
   ```

3. **使用 `systemd-analyze critical-chain` 查看关键路径**：
   ```bash
   systemd-analyze critical-chain
   ```

4. **考虑使用 SSD**（如果你还在用机械硬盘）。

5. **减少 Snap 包的数量**（每个 Snap 都会挂载一个 loop 设备，拖慢启动）。

---

### 📁 如何再次生成该图

你可以再次运行：
```bash
systemd-analyze plot > boot.svg
```
然后用浏览器打开：
```bash
xdg-open boot.svg
```

---

如果你希望我帮你分析具体是哪个服务拖慢了启动，或者如何优化，我可以帮你进一步解读 `systemd-analyze blame` 或 `critical-chain` 的输出。