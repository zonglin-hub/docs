### 🔍 第一步：查找运行中的容器实例

首先，确保目标应用（如 DeepSeek）正在运行。然后打开终端，执行：

```bash
ll-cli ps
```

这个命令会列出所有正在运行的玲珑应用，输出类似：

```bash
App                                            ContainerID      Pid
main:com.deepseek.uos/版本/x86_64              c3b5ce363172     539537
```

你需要记下 **`App` 列**的完整名称（例如 `main:com.deepseek.uos/版本/x86_64`），
这就是进入容器所需的**实例 ID**。

### 🚪 第二步：进入容器

获取实例 ID 后，使用 `ll-cli enter` 命令进入。根据你的玲珑版本，命令略有不同：

**新版（推荐，适用于 1.14.x 及以上）**：

```bash
ll-cli enter <实例ID> /bin/bash
```

将 `<实例ID>` 替换为第一步中查到的名称，例如：

```bash
ll-cli enter main:com.deepseek.uos/版本/x86_64 /bin/bash
```

- **退出容器**：在容器内输入 `exit` 即可返回宿主机终端。
