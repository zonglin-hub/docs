---
标题: 安装卸载 Docker
创建时间: 2026-02-18
修改时间: 2026-11-24
标签:
  - Docker
  - Ubuntu
---

# 安装卸载 Docker

- Docker 官方文档：[https://docs.docker.com/](https://docs.docker.com/)
- Docker 社区论坛：[https://forums.docker.com/](https://forums.docker.com/)

![Docker Logo](./imgs/docker-logo.avif)

## 在 Ubuntu 上安装 Docker

在 Ubuntu 24.04 LTS 上，虽然可以通过 Ubuntu 的官方仓库直接安装 Docker，但是这种
方法通常无法获取到最新的 Docker 版本，而且安全更新也可能延迟。

因此，推荐从 Docker 的官方仓库进行安装，确保可以用上最新版本并和自动更新。

### 更新软件包并安装必要软件

运行以下命令，更新软件包索引并安装添加 Docker 仓库所需的前置软件包：


```bash
sudo apt update
sudo apt install apt-transport-https curl
```

- `ca-certificates`：允许系统信任 HTTPS 证书，用于安全地访问网络源。
- `curl`：命令行工具，用于从网络下载文件（如 Docker 的 GPG 密钥）。

### 导入 Docker 官方 GPG 密钥

#### 创建用于存储 GPG 密钥的目录

```bash
sudo install -m 0755 -d /etc/apt/keyrings
```

- `install`：一个用于复制文件和创建目录的命令，比 `mkdir` 和 `chmod` 的组合更简
  洁。
- `-m 0755`：设置目录的权限为 `0755`（所有者可读、写、执行，其他用户可读和执
  行）。这是存储密钥目录的标准权限。
- `-d`：指定要创建的是目录而不是文件。
- `/etc/apt/keyrings`：APT 包管理器推荐的用于存储第三方软件仓库密钥的目录。

下载 Docker 官方的 GPG 密钥，并将其保存到上一步创建的目录中。

```bash
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /etc/apt/keyrings/docker.gpg -o /etc/apt/keyrings/docker.asc
```

**GPG 密钥用于验证 Docker 软件包的“身份”和“完整性”，确保你下载的 Docker 软件包来
自可信的源头，并且在传输过程中没有被任何人篡改或植入恶意代码。**

在官方中使用下载的地址是：https://download.docker.com/linux/ubuntu/gpg

#### 此处改为阿里云镜像地址，但下载下来的还是官方的 GPG 密钥。

```bash
sudo curl -fsSL http://mirrors.aliyun.com/docker-ce/linux/ubuntu/gpg -o /etc/apt/keyrings/docker.asc
```

- `-f` 或 `--fail`：让 curl 在服务器错误时静默失败（不输出 HTML 错误页面）。
- `-s` 或 `--silent`：静默模式，不显示进度条或错误信息。
- `-S` 或 `--show-error`：与 `-s` 一起使用时，如果失败会显示错误信息。
- `-L` 或 `--location`：如果请求的页面发生了重定向，则自动跟随重定向。
- `-o /etc/apt/keyrings/docker.asc`：将下载的内容输出到指定文件。文件扩展名
  `.asc` 表明这是一个 ASCII 格式的 GPG 密钥。

### 更改密钥文件的权限

为所有用户添加可读的权限，这样所有用户下载时都可以通过密钥进行验证。

```bash
sudo chmod a+r /etc/apt/keyrings/docker.asc
```

- `chmod a+r`：`a` 代表所有用户 (all)，`+r` 代表添加读取 (read) 权限。

### 添加 Docker 官方仓库

将 Docker 的官方仓库添加到 Ubuntu 24.04 LTS 的软件源列表：

### 添加 Docker 的软件源

将 Docker 的官方软件源添加到 Ubuntu 24.04 的软件源列表，`apt install` 时用于获取
官方源提供 Docker CE 的所有组件：`docker-ce`, `docker-ce-cli`, `containerd.io`,
`docker-buildx-plugin`, `docker-compose-plugin` 等。

此处地址为官方地址的阿里云镜像地址，Docker 官方地址为：
<https://download.docker.com/linux/ubuntu> 如需改成官方，修改url即可

```bash
echo "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.gpg] http://mirrors.aliyun.com/docker-ce/linux/ubuntu $(. /etc/os-release && echo "$VERSION_CODENAME") stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
```

1. **`echo "deb [arch=...] ..."`**：
    - 用于生成一个标准的 Debian/Ubuntu 软件源条目（sources.list entry）。
2. **`[arch=$(dpkg --print-architecture)
   signed-by=/etc/apt/keyrings/docker.asc]`**：
    - `arch=$(dpkg --print-architecture)`：这是一个**命令替换**。它会先执行
      `dpkg --print-architecture` 命令（该命令会输出您系统的架构，如 `amd64` 或
      `arm64`），然后用输出结果替换这部分内容。这确保了软件源地址与您的系统架构
      匹配。
    - `signed-by=/etc/apt/keyrings/docker.asc`：**极其重要**。它明确指定了用于验
      证此软件源中所有软件包的 GPG 密钥的路径。这是确保软件包来自 Docker 官方而
      非恶意镜像的安全关键步骤。
3. **`http://mirrors.aliyun.com/docker-ce/linux/ubuntu`**：
    - Docker 官方软件源的阿里云镜像地址。
4. **`$(. /etc/os-release && echo "${UBUNTU_CODENAME:-$VERSION_CODENAME}")`**：
    - 这是另一个**命令替换**和**参数扩展**的巧妙结合。
    - `. /etc/os-release`：这会执行（source）一个系统文件，该文件包含当前 Ubuntu
      版本的详细信息（如 `VERSION_ID="24.04"`, `VERSION_CODENAME=noble`,
      `UBUNTU_CODENAME=noble`）。
    - `&& echo "${UBUNTU_CODENAME:-$VERSION_CODENAME}"`：如果前一个命令成功，则
      执行 `echo`。`${UBUNTU_CODENAME:-$VERSION_CODENAME}` 是一个参数扩展，意思
      是“如果 `UBUNTU_CODENAME` 变量存在且不为空，则使用它的值；否则，使用
      `VERSION_CODENAME` 的值”。
    - 最终，这部分会被替换为您的 Ubuntu 系统代号，例如 `jammy` (22.04), `noble`
      (24.04) 等。这确保了您添加的软件源与您的系统版本完全匹配。
5. **`stable`**：
    - 指定要使用 Docker 的稳定版（stable）发布通道。而不是测试版（test）或夜间构
      建版（nightly）。
6. **`| sudo tee /etc/apt/sources.list.d/docker.list > /dev/null`**：  - `|`：管
   道符，将 `echo` 命令的输出传递给 `tee` 命令。
    - `tee`：一个同时输出到屏幕和文件的命令。这里与 `sudo` 一起使用，以便拥有向
      受保护目录写入的权限。
    - `/etc/apt/sources.list.d/docker.list`：将软件源条目写入到
      `/etc/apt/sources.list.d/` 目录下的一个新文件 `docker.list` 中。这是一种最
      佳实践，可以保持组织有序，并且更容易管理和删除单个软件的源。
    - `> /dev/null`：将 `tee` 命令输出到**标准输出（屏幕）** 的内容重定向到“空设
      备”，从而抑制任何输出，保持终端整洁。

### 更新软件包列表

刷新软件包列表，以便系统识别新添加的 Docker 仓库：

```bash
sudo apt update
```

### 安装 Docker

执行以下命令在 Ubuntu 24.04 LTS 上安装最新版本的 Docker，包括 Docker 引擎及其相
关组件：


```bash
sudo apt install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin
```

1. `docker-ce`
    - **全称**：Docker Community Edition
    - **作用**：这是 **Docker 引擎的核心**，也就是常说的 "Docker 守护进程"
      (`dockerd`)。
    - **功能**：它是一个持续运行的后台服务，负责管理 Docker 的方方面面：
        - 下载和存储镜像（Images）。
        - 创建、运行、停止和删除容器（Containers）。
        - 管理网络（Networks）和存储卷（Volumes）。
        - 暴露 REST API 供其他工具（如 CLI）调用。
    - **简言之**：它是 Docker 的“大脑”和“发动机”。
2. `docker-ce-cli`
    - **全称**：Docker Community Edition Command Line Interface
    - **作用**：这是 **Docker 的命令行工具**。你平时在终端里打的 `docker` 命令就
      来自于它。
    - **功能**：它本身不执行管理操作，而是作为一个客户端，接收你的命令（如
      `docker run`, `docker ps`），然后通过 API 与上面的 `docker-ce` 守护进程通
      信，并告诉你结果。
    - **简言之**：它是用户与 Docker 引擎交互的“遥控器”。
3. `containerd.io`
    - **作用**：这是一个**行业标准的容器运行时**。它是更底层、更核心的组件。
    - **功能**：它负责容器生命周期的最底层操作，例如：
        - 从镜像中拉取和存储容器文件系统层。
        - 管理容器的执行、暂停、恢复和销毁。
        - 管理容器网络和存储的低级细节。
    - **与 Docker 的关系**：从 Docker 的架构演变来看，`docker-ce`（守护进程）现
      在将大部分实际的容器运行时操作**委托**给了 `containerd`。这种解耦使得架构
      更清晰，`containerd` 也可以被其他系统（如 Kubernetes）直接使用。
    - **简言之**：它是真正“动手”拉取镜像和运行容器的“工程师”，而 `docker-ce` 是
      它的“项目经理”。
4. `docker-buildx-plugin`
    - **作用**：Docker 的**扩展构建插件**，提供了下一代强大的镜像构建功能。
    - **功能**：它是 `docker build` 命令的增强版，支持许多高级特性：
        - **多平台构建**：一次性为多种 CPU 架构（如 amd64, arm64, arm/v7）构建镜
          像，无需复杂的交叉编译环境。
        - **构建缓存管理**：更高效的缓存机制，加速构建过程。
        - **并行构建**：同时处理多个构建任务，充分利用多核CPU。
    - **注意**：在较新版本的 Docker 中，`buildx` 已成为默认的构建引擎。
5. `docker-compose-plugin`
    - **作用**：Docker 官方提供的 **Compose 功能**，用于定义和运行多容器应用。
    - **功能**：它提供了 `docker compose` 命令（注意没有横线），用来替代旧的、独
      立的 `docker-compose`（Python 编写）工具。
        - 通过一个 `docker-compose.yml` 文件来配置你的应用所需的所有服务、网络、
          卷。
        - 用一个命令（`docker compose up`）就能启动整个复杂的应用栈（例如一个包
          含 Web 前端、后端 API、数据库、缓存的应用）。
    - **为什么是插件形式**：Docker 公司为了更好的集成和发布周期，将 Compose 功能
      做成了 CLI 插件，而不是一个独立的二进制文件。

### 验证安装是否成功

```bash
docker -v
```

### Docker 设置开机自启（可选）

安装成功后，Docker 服务将会开机自动启动。

```bash
systemctl is-enabled docker
```

可以看到输出是 `enabled`，说明 Docker 已经设置为开机自动启动了

### 检查 Docker 服务状态

使用以下命令检查 Docker 的运行状态：

```bash
sudo systemctl is-active docker
```

## 替换 DockerHub 国内镜像源（推荐）

前拉取镜像时，是从 Docker Hub 中拉取的。但国内访问 Docker Hub 时，会无法访问，导
致无法拉取镜像。

对于这种情况，建议**添加国内镜像源地址**。

但请放心，国内镜像源本质上是**为了提升访问速度而建立的 Docker Hub 缓存代理**。它
会帮你从 Docker Hub 拉取镜像，并将这些镜像存储在自己的服务器上。你通过它们下载的
镜像，**源头依然是 Docker Hub**。你可以把它们理解为设立在身边的 Docker Hub “分
身”，旨在解决远距离访问速度慢的问题。

国内镜像源工作流程：

### 打开配置文件

### 添加镜像源

如果拉取 [DockerHub](https://hub.docker.com/) 镜像时遇到困难，可以替换为国内镜像：

1. **打开配置文件**：使用文本编辑器打开 Docker 的配置文件，如果没有就新建：

```bash
sudo vim /etc/docker/daemon.json
```

2. **编辑配置文件**：在配置文件中添加或修改registry-mirrors，指定国内镜像源的
   URL。以下是一些可用的国内镜像源地址，你可以根据需要选择使用：

当前一些可用的镜像源列表：

|镜像源名称|镜像加速地址|主要特点/备注|
|---|---|---|
|**DaoCloud 镜像站**|`https://docker.m.daocloud.io`|国内老牌服务商，稳定可靠|
|**网易云镜像加速**|`http://hub-mirror.c.163.com`|多节点覆盖|
|**中国科学技术大学**|`https://docker.mirrors.ustc.edu.cn`|教育网用户访问效果可能较好|
|**南京大学镜像站**|`https://docker.nju.edu.cn`|支持 Docker Hub、GCR、GHCR、Quay、NVCR 等|
|**Docker 中国官方镜像**|`https://registry.docker-cn.com`|官方认证，适合企业环境|

配置文件的内容示例如下：


```json
{
  "registry-mirrors": [
    "https://docker.m.daocloud.io",
    "https://mirror.baidubce.com",
    "http://hub-mirror.c.163.com"
  ]
}
```

3. **重启 Docker 服务**：保存配置文件并重启 Docker 服务以应用更改。


```bash
sudo systemctl daemon-reload
sudo systemctl restart docker
```

如果修改了服务配置（例如，修改了 Docker 的 `docker.service` 文件中的环境变量、启
动参数或依赖关系），`systemd`（Linux 的系统和服务管理器） 并不会自动感知到这个变
化。

`systemctl daemon-reload` 命令会**通知 `systemd` 去重新读取并加载所有服务单元文
件**的更改，使新的配置生效。

当然，它只是让 `systemd` 记住了新的配置，只有下次启动服务时才会应用。

我们当前修改的配置文件是 daemon.json，在重启服务前无需执行 `sudo systemctl
daemon-reload` 这个命令。

4. **验证配置**：重启 Docker 后，使用docker info命令来检查镜像源是否已经替换成
   功：

重启后，查看一下镜像源配置是否成功。可以看到相比最初，增加了 `Registry
Mirrors`。

```bash
sudo docker info
```

使用 docker info 命令检查镜像源

### 运行测试容器（可选）

运行 hello-world 测试容器，验证 Docker 是否安装成功并正常工作：

```bash
sudo docker run hello-world # 运行 hello-world 测试容器
```

## 允许非 root 用户运行 Docker 命令（可选）

### 添加当前用户到用户组

#### 权限说明

默认情况下，只有 root 用户和组名为 `docker` 的用户组用户才能执行 Docker 命令。

普通用户执行 Docker 命令时需要添加 `sudo`，如果觉得麻烦，可以将用户添加到 Docker
用户组。

> Docker 守护进程（`dockerd`）启动后，会创建一个用于通信的 Unix 套接字（Socket）
> 文件，默认路径是 `/var/run/docker.sock`。
> 
> 通过命令可以查看到这个文件的详细信息：
> 
> ```bash
> $ ls -l /var/run/docker.sock
> srw-rw---- 1 root docker 0 Sep 15 09:37 /var/run/docker.sock
> ```
> 
> 这里的关键信息是：
> 
> - **所有者 (Owner)**: `root`
> - **所属组 (Group)**: `docker`
> - **权限 (Permissions)**: `srw-rw----`（其中的 `rw-` 表示所属组具有读写权限）
> 
> 这意味着：
> 
> 1. **root 用户** 永远可以直接操作这个套接字（所以用 `sudo` 总能运行 Docker 命
>    令）。
> 2. 所有属于 **`docker` 组** 的用户也具有读写权限，因此可以无需 `sudo` 直接与
>    Docker 守护进程通信。
> 3. 其他用户则没有任何权限。
> 
> 虽然可以通过修改配置文件 `daemon.json` 的方式，修改默认组名，但不推荐。

#### 查看用户组

默认情况下，安装好 Docker 后，会自动创建一个用户组，通过以下命令可以查看
**`docker` 组** 信息。

```bash
grep docker /etc/group
```

`/etc/group` 文件存储了系统中所有**用户组（Group）** 的定义信息，其中的每一行都
代表一个用户组，其格式由冒号 `:` 分隔为四个字段。

|示例值|含义|
|---|---|
|`docker`|**用户组的名称**。这就是你查询的组。|
|`x`|**经过加密的组密码**。几乎总是显示为 `x`，表示密码实际上被存储在更安全的 `/etc/gshadow` 文件中。通常组密码很少使用。|
|`988`|**组ID (GID)**。这是系统用来识别这个组的唯一数字。**这个数字在不同系统上可能不同**（常见的有 999, 998 等），988 是完全正常的。|
|**(空)**|**属于该组的用户成员列表**。这个字段是空的，表示目前**没有用户被直接添加为这个组的成员**。|

如果没有任何返回，那么可以执行 `sudo groupadd docker` 命令去创建一个名为
`docker` 的用户组。

#### 添加当前用户

```bash
sudo usermod -aG docker $USER
```

- `usermod`：修改用户属性的命令
- `-aG`：追加到附加组 (Append to Supplementary Groups)
- `docker`：目标用户组的名称
- `$USER`：shell 环境变量，代表当前用户名

完成后，再次查看用户组，可以看到当前用户已经添加成功。


#### 切换到 `docker` 组

```bash
newgrp docker
```

`newgrp` 命令的主要目的是**临时性地将当前用户会话（terminal session）的主要组
（primary group）或附加组（supplementary group）的权限生效**。

当前用户已经添加到 `docker` 组，这个更改虽然被写入了 `/etc/group` 文件，但**不会
自动应用到你已经登录的当前会话中**。

要让新的组权限生效，有三种方式：

- **注销后重新登录**（最彻底的方式）。
- **打开一个新的终端窗口**（新的会话会读取新的组信息）。
- 使用 **`newgrp`** 命令（如果执行 `exit` 命令会退回到当前用户之前的权限）。

我们使用 FinalShell 连接，其实**打开一个新的终端窗口更方便**。

#### 测试

执行一个 Docker 命令不加 `sudo` ，比如 `docker images`，可以看到命令正常运行。


## 卸载 Docker

Ubuntu 卸载 Docker，都可以按照以下步骤操作：

使用以下命令卸载 Docker 及其相关组件：


```bash
sudo apt purge docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin docker-ce-rootless-extras
```

执行以下命令来删除 Docker 创建的目录：

```bash
sudo rm -rf /var/lib/docker
sudo rm -rf /var/lib/containerd
```
