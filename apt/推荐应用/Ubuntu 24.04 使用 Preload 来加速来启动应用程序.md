
# Ubuntu 24.04 使用 Preload（预加载）来加速来启动应用程序

Preload 是一种智能工具，可以显著加快 Ubuntu 中的应用程序启动时间。本指南介绍了安装 Preload 的基础知识，对其进行配置以适应您的使用模式，以及了解它如何预取和缓存最常用的应用程序，从而缩短加载时间并提供更流畅的用户体验。

在当今快节奏的数字世界中，应用程序的启动速度会显著影响我们的工作效率和整体计算体验。对于 Linux 用户来说，有一个功能强大但经常未被充分利用的工具可以在这一领域产生明显的差异：预加载。Preload 是一个守护程序，可智能地分析您的应用程序使用模式，并将部分常用程序预加载到内存中，从而减少其加载时间。

在这篇博客中，我们深入探讨了 Preload 的复杂性，探讨了它的工作原理、它的安装和配置过程，以及它给您的系统带来的实际好处和限制。

## 什么是预加载？

Preload 是在 Linux 系统后台运行的守护程序。它分析用户行为并跟踪经常使用的应用程序。随着时间的推移，它会智能地预测您接下来可能会启动哪些应用程序，并将这些应用程序的一部分加载到内存中。此过程显著缩短了加载时间，使您的计算体验更流畅、更快速。

### 为什么使用 preload？

想象一下，点击您最喜欢的软件并几乎立即弹出它。这就是 preload 的魔力！对于硬盘驱动器速度较慢的系统或通常需要很长时间才能启动的应用程序，它特别有用。

## 预加载的工作原理

Preload 会监控您使用的应用程序，并智能地预测您接下来可能运行的应用程序。它不会将整个应用程序加载到内存中，而只是将经常需要的位加载到内存中。此方法可确保优化内存使用，而不会使系统负担过重。

### 我的经历

我记得使用的是一台较旧的笔记本电脑，它在加载时间方面遇到了困难。安装 preload 后，改进非常明显。像 GIMP 和 LibreOffice 这样的应用程序过去需要相当长的时间才能启动，现在只需很短的时间就可以准备好了。

### 分步指南

1. __打开您的终端__ ：您可以通过在应用程序菜单中搜索“终端”或使用快捷方式（通常为 `Ctrl + Alt + T`）来执行此作。
2. __安装预加载__ ：键入以下命令并按 Enter：

    ```bash
    sudo apt-get install preload # 在系统上安装 preload 守护程序。
    ```

3. __检查 preload 是否正在运行__ ：安装后，preload 会自动启动。您可以通过以下方式检查其状态：

    ```bash
    systemctl status preload # 检查 preload 服务的当前状态，显示它是否处于活动状态并正在运行。
    ```

    您应该会看到一个输出，指示 preload 处于活动状态且正在运行。

    ```bash
    ● preload.service - LSB: Adaptive readahead daemon
        Loaded: loaded (/etc/init.d/preload; generated)
        Active: active (running) since Mon 2025-05-05 14:45:14 CST; 3 days ago
        Docs: man:systemd-sysv-generator(8)
        Tasks: 1 (limit: 18215)
        Memory: 69.9M (peak: 74.5M)
            CPU: 1min 20.943s
        CGroup: /system.slice/preload.service
                └─945 /usr/sbin/preload -s /var/lib/preload/preload.state

    5月 05 14:45:15 zonglin-CREM-WXX9 preload[896]:  * Starting Adaptive readahead daemon preload
    5月 05 14:45:15 zonglin-CREM-WXX9 preload[896]:    ...done.
    5月 05 14:45:14 zonglin-CREM-WXX9 systemd[1]: Starting preload.service - LSB: Adaptive readahead daemon...
    5月 05 14:45:14 zonglin-CREM-WXX9 systemd[1]: Started preload.service - LSB: Adaptive readahead daemon.
    ```

### Configuring preload  配置预加载

Preload 是开箱即用的，但您可以调整其设置以获得更好的性能。

#### Sample configuration  示例配置

通过键入以下内容来编辑预加载配置文件：

```bash
sudo nano /etc/preload.conf # 打开预加载配置文件进行编辑。您可以在此处更改 `sortstrategy`、`minsize` 和 `memtotal` 等设置。
```

在这里，您可以调整 `sortstrategy`、`minsize` 和 `memtotal` 等设置。例如，将 `minsize` 更改为更高的值意味着 preload 将仅考虑较大的文件，从而可能加快较大的应用程序速度。

#### Original configuration (before)  原始配置 （之前）

以下是 `preload` 配置文件在默认状态下可能是什么样子的示例：

```bash
# /etc/preload.conf - original configuration

sortstrategy = 0
minsize = 20000
memtotal = 50
```

在此默认配置中：

- `sortStrategy` 设置为 `0`，这意味着 Preload 根据访问频率确定文件的优先级。
- `minsize` 设置为 `20000`（字节），因此 preload 将考虑大于 20 KB 的文件。
- `memtotal` 设置为 `50`，这意味着 preload 最多可以使用总 RAM 的 50%。

编辑后，保存文件并重新启动预加载：

```bash
sudo systemctl restart preload # 重新启动预加载服务以应用在配置文件中所做的任何更改。
```

### 监控预加载的性能

要查看 preload 如何影响您的系统，您可以检查其日志文件：

```bash
sudo tail -f /var/log/preload.log # 显示预加载日志文件，其中显示已预加载的文件以及其他作详细信息。
```

#### 输出示例

```bash
[Sat May  3 14:03:44 2025] saving state to /var/lib/preload/preload.state
[Sat May  3 14:04:06 2025] loading conf from /etc/preload.conf
[Sat May  3 14:04:06 2025] loading state from /var/lib/preload/preload.state
[Sat May  3 21:19:04 2025] saving state to /var/lib/preload/preload.state
[Sun May  4 23:43:32 2025] saving state to /var/lib/preload/preload.state
[Mon May  5 13:33:14 2025] exit requested
[Mon May  5 13:33:14 2025] saving state to /var/lib/preload/preload.state
[Mon May  5 14:29:36 2025] loading conf from /etc/preload.conf
[Mon May  5 14:29:36 2025] loading state from /var/lib/preload/preload.state
[Mon May  5 14:44:57 2025] exit requested
[Mon May  5 14:44:57 2025] saving state to /var/lib/preload/preload.state
[Mon May  5 14:45:14 2025] loading conf from /etc/preload.conf
[Mon May  5 14:45:14 2025] loading state from /var/lib/preload/preload.state
[Mon May  5 23:16:31 2025] saving state to /var/lib/preload/preload.state
[Thu May  8 23:55:53 2025] saving state to /var/lib/preload/preload.state
[Fri May  9 23:37:00 2025] exit requested
[Fri May  9 23:37:00 2025] saving state to /var/lib/preload/preload.state
[Fri May  9 23:37:00 2025] loading conf from /etc/preload.conf
[Fri May  9 23:37:00 2025] loading state from /var/lib/preload/preload.state
```

##### 输出详解

- __Timestamps__：每个条目都带有时间戳，显示作发生的时间。
- __Starting preload daemon__：指示预加载服务的启动时间。
- __Monitoring files and directories__ ：显示 preload 正在监控的文件和目录数量。此数字会随着 preload 适应您的使用模式而变化。
- __Re-scanning directories__ ：preload 会定期重新扫描目录，以根据最近的使用情况更新其文件列表。
- __Re-scanning done__ ：表示重新扫描完成，并显示正在监控的文件和目录的更新计数。

#### 这说明了什么

此日志提供了有关 preload 如何适应系统使用情况的见解。通过监控文件和目录数量的变化，您可以了解 preload 如何优化其作以更好地适应您的使用模式。定期重新扫描可确保预加载与文件访问习惯的最新更改保持同步。

### 优点和缺点

#### 优势

1. __提高 I/O 效率__ ：Preload 通过将常用应用程序的某些部分智能地预加载到 RAM 中，减少了应用程序启动时的磁盘 I/O。这对于具有机械硬盘驱动器的系统尤其有益，因为 I/O 作是一个重要的瓶颈。
2. __自适应学习算法__ ：Preload 使用复杂的算法来分析使用模式，以适应应用程序使用情况随时间的变化。这意味着您使用某些应用程序的次数越多，预加载在预测和加载必要文件方面就越好。
3. __缩短了应用程序启动时间__ ：通过在内存中加载了必要的文件，应用程序在启动后响应所需的时间显著减少，从而增强了用户体验。
4. __可定制的性能调整__ ：Preload 允许对各种参数（如 `minsize`、`memtotal` 和 `sortstrategy`）进行微调，从而根据个人需求和系统规格灵活地优化性能。
5. __低开销__ ：尽管在后台运行，但 preload 设计为轻量级。它不会消耗大量的 CPU 资源，因此甚至适用于功能较弱的系统。

#### 缺点

1. __内存使用量增加__ ：预加载需要系统的一部分 RAM 来存储预加载的文件。在内存有限的系统上，这可能会导致其他应用程序的可用 RAM 减少，从而可能影响整体系统性能。
2. __SSD 的回报递减__ ：配备固态硬盘 （SSD） 的系统从预加载中获得的好处可能较小，因为 SSD 已经提供了快速的读取速度。预加载的影响在具有传统硬盘驱动器的系统上更为明显。
3. __预测不准确的可能性__ ：虽然 preload 的算法通常有效，但并非万无一失。在某些情况下，它可能会预加载不需要的文件，或者无法预加载所需的文件，尤其是在用户行为快速变化时。
4. __需要时间学习__ ：Preload 需要时间来分析使用模式，以免其影响变得明显。新用户或新安装了预加载的系统不会立即看到性能提升。
    __仅限于 Linux 环境__ ： Preload 主要是一种基于 Linux 的工具，这意味着 Windows 或 macOS 等其他作系统的用户无法获得它的好处。

### 技术注意事项

- __平衡内存使用__ ：用户需要平衡分配给预加载（通过 `memtotal`）的内存与整体系统内存，确保有足够的 RAM 用于其他应用程序和系统进程。
- __文件大小注意事项__ ：`minsize` 参数应根据最常用的应用程序使用的典型文件大小进行配置。将其设置得太高或太低都会影响预加载的有效性。
- __系统监控__ ：用户应监控系统性能指标，如 RAM 使用情况、磁盘 I/O 和应用程序启动时间，以评估预加载的影响并根据需要调整配置。

### 结论

充分利用 preload 的潜力的关键在于深思熟虑的配置并了解它对系统资源的影响。调整 `sortstrategy`、`minsize` 和 `memtotal` 等设置允许用户根据其特定需求定制预加载的行为。但是，请务必平衡这些设置与整体系统性能，请记住，preload 的学习算法需要时间来适应使用模式。

在探索了预加载的深度之后，很明显，该工具为 Linux 计算体验提供了有价值的增强，特别是对于硬盘驱动器速度较慢的系统。通过根据用户行为智能地预加载应用程序的各个部分，预加载可以显著缩短应用程序启动时间，使您的工作流程更顺畅、更高效。虽然它在具有充足 RAM 和传统硬盘驱动器的环境中大放异彩，但它的有效性在内存有限或使用 SSD 的系统上可能会有所不同。
