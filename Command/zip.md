Linux 下操作 ZIP 文件主要依赖 `zip`（压缩/修改）和 `unzip`（解压/查看）两个命
令。ZIP 格式的最大优势是跨平台兼容性好，Windows、macOS 原生支持，适合在不同系统
之间传递文件。

---

### 安装

大多数 Linux 发行版默认没有预装，先根据你的系统安装：

- __Debian/Ubuntu__：

    ```bash
    sudo apt update
    sudo apt install zip unzip
    ```

安装后可用 `zip -v` 和 `unzip -v` 验证是否成功。

## `zip` 用法整理


```bash
# 压缩一个文件
zip archive.zip document.txt

# 压缩多个文件
zip archive.zip file1.txt file2.txt file3.txt

# 递归压缩目录
zip -r project.zip my_project/

# 只更新已变化或新增的文件
zip -u archive.zip changed_file.txt

# 从压缩包删除条目
zip -d archive.zip unwanted.txt

# 移入压缩包并删除原文件
zip -m archive.zip file1.txt

# 排除匹配名称
zip -r project.zip my_project/ -x "*.log"

# 仅包含指定名称
zip -r project.zip my_project/ -i "*.txt"

# 加密
zip -e secure.zip secret.txt

# 测试完整性
zip -T archive.zip
```

## `unzip` 用法整理


```bash
# 解压到当前目录
unzip archive.zip

# 解压到指定目录
unzip archive.zip -d target/

# 列出压缩包内容
unzip -l archive.zip

# 测试压缩包完整性
unzip -t archive.zip

# 不覆盖已存在文件
unzip -n archive.zip

# 强制覆盖，不询问
unzip -o archive.zip

# 排除文件
unzip archive.zip -x "*.log"

# 解压到管道并分页查看
unzip -p archive.zip file.txt | more

# 指定编码
unzip -O CHARSET archive.zip
unzip -I CHARSET archive.zip
```

---

## 五、最小速查表

| 场景 | 命令 |
|---|---|
| 压缩文件 | `zip archive.zip file1 file2` |
| 压缩目录 | `zip -r archive.zip dir/` |
| 更新压缩包 | `zip -u archive.zip file` |
| 删除压缩包内文件 | `zip -d archive.zip file` |
| 移动进压缩包 | `zip -m archive.zip file` |
| 加密压缩 | `zip -e archive.zip file` |
| 测试 zip 完整性 | `zip -T archive.zip` |
| 解压 | `unzip archive.zip` |
| 解压到目录 | `unzip archive.zip -d target/` |
| 列出内容 | `unzip -l archive.zip` |
| 测试 unzip 归档 | `unzip -t archive.zip` |
| 不覆盖 | `unzip -n archive.zip` |
| 强制覆盖 | `unzip -o archive.zip` |
| 排除文件 | `unzip archive.zip -x "*.log"` |