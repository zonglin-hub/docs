## 使用 curl 下载到指定目录

`curl` 是另一个常用的命令行下载工具，常用选项：
- `-o <file>`：将输出保存到指定文件（与 wget 的 `-O` 类似）。
- `-O`：使用 URL 末尾的文件名作为本地文件名（需要 URL 指向明确文件名）。
- `-L`：跟随重定向（GitHub 的下载链接通常有重定向，必须加 `-L`）。
- `--output-dir <dir>`：指定输出目录（curl 7.73.0+ 支持），常与 `-O` 搭配。

### 等价 curl 命令
```bash
# 方法1：直接指定完整路径（推荐，明确文件名）
curl -L -o /home/zonglin/Programs/scrcpy.tar.gz https://github.com/Genymobile/scrcpy/releases/download/v3.3.4/scrcpy-linux-x86_64-v3.3.4.tar.gz

# 方法2：使用 --output-dir 和 -O（需确保目录存在，且自动使用远程文件名）
curl -L --output-dir /home/zonglin/Programs -O https://github.com/Genymobile/scrcpy/releases/download/v3.3.4/scrcpy-linux-x86_64-v3.3.4.tar.gz
```

注意：
- `-L` 必须加，否则可能只下载一个重定向页面。
- 如果目录不存在，curl 也会报错。请先创建目录：`mkdir -p /home/zonglin/Programs`。

---

## 3. 下载到指定目录的通用方法

无论用 `wget` 还是 `curl`，都需要确保目标目录已存在，并且你有写入权限。

### 使用 wget
- 进入目标目录再下载（无需 `-O`）：
  ```bash
  cd /home/zonglin/Programs
  wget -q https://github.com/.../scrcpy-linux-x86_64-v3.3.4.tar.gz
  ```
- 或直接用 `-O` 指定完整路径：
  ```bash
  wget -qO /home/zonglin/Programs/scrcpy.tar.gz <URL>
  ```

### 使用 curl
- 进入目录下载：
  ```bash
  cd /home/zonglin/Programs && curl -L -O <URL>
  ```
- 或使用 `-o` 指定路径：
  ```bash
  curl -L -o /home/zonglin/Programs/scrcpy.tar.gz <URL>
  ```

---

## 4. 关于 sudo 的建议

- 只有当你需要写入系统目录（如 `/usr/local/bin`）时才用 `sudo`。
- 下载到自己的家目录（如 `/home/zonglin` 下）**不要用 sudo**，否则文件属主会变成 root，后续操作可能遇到权限问题。
- 如果确实需要用 sudo，建议下载后使用 `chown` 将文件归属改回你的用户。

---

## 5. 后续操作：解压和使用 scrcpy

下载的 `.tar.gz` 文件需要解压：
```bash
tar -xzf /home/zonglin/Programs/scrcpy.tar.gz -C /home/zonglin/Programs/
```
这会创建一个类似 `scrcpy-linux-x86_64-v3.3.4` 的目录，里面包含可执行文件。你可以将可执行文件加入 PATH 或直接运行。

---

## 总结
- 你的命令将文件保存为无扩展名的 `scrcpy`，可能造成混淆，建议保留 `.tar.gz` 后缀。
- 使用 curl 时加上 `-L` 处理重定向，用 `-o` 或 `-O` 指定输出。
- 下载前确保目标目录存在且权限正确，避免滥用 `sudo`。

希望这些解释对你有帮助！如果还有其他疑问，欢迎继续提问。