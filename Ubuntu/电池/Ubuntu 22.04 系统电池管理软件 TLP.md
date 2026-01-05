# Ubuntu 系统电池管理软件 TLP

在 Ubuntu 系统中，使用 TLP（Thinkpad Linux 电池优化器）来管理电池并设置充电阈值是一种有效的方法。TLP 是一款高级电源管理工具，专为延长 Linux 笔记本电脑的电池寿命而设计。它提供了许多功能，包括处理器频率调整、硬盘高级电源管理、无线网络省电模式等，以及专门为 ThinkPad 电脑提供的电池充电阈值设置功能。要在 ThinkPad 上设置电池充电阈值，可以使用以下命令：

```bash
sudo tlp setcharge [开始充电阈值] [结束充电阈值] [电池编号]
```

例如，要将电池 BAT0 的开始充电阈值设置为 70%，结束充电阈值设置为 90%，可以使用以下命令：

```bash
sudo tlp setcharge 70 90 BAT0
```

这样可以有效地控制电池的充电过程，延长电池的使用寿命。TLP 的所有设置都存储在 `/etc/default/tlp` 文件中。它的默认配置已经针对电池寿命进行了优化，但用户可以根据需要自定义设置。安装 TLP 非常简单。在基于 Debian/Ubuntu 的系统上，可以使用以下命令安装 TLP：

```bash
sudo apt-get install tlp
```

对于 ThinkPad，可能还需要安装一些额外的软件包，例如 `tp-smapi-dkms` 和 `acpi-call-dkms`。TLP 是一个纯命令行工具，不包含图形用户界面。它适用于各种品牌的笔记本电脑，但设置电池充电阈值的功能仅适用于 IBM/Lenovo ThinkPad 电脑。更多关于 TLP 的详细信息和设置选项，可以参考相关文档和在线资源。
