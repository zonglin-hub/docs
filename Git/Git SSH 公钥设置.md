[SSH 公钥设置](https://help.gitee.com/base/account/SSH%E5%85%AC%E9%92%A5%E8%AE%BE%E7%BD%AE)

每次 Git 提交都需要输入密码通常是因为使用了 **HTTPS 协议**克隆仓库，而非 SSH 协议。以下是解决方案：

---

### 方法 1：改用 SSH 协议（推荐）

1. **检查当前远程仓库地址**：

   ```bash
   git remote -v
   ```

   如果显示 `https://` 开头的 URL，说明是 HTTPS 协议。

2. **切换为 SSH 地址**：

   ```bash
   git remote set-url origin git@github.com:用户名/仓库名.git
   ```

3. **配置 SSH 密钥**（若未配置过）：
   - **生成 SSH 密钥**（按回车使用默认路径）：

     ```bash
     ssh-keygen -t ed25519 -C "你的邮箱"
     ```

   - **添加公钥到 Git 服务商**（如 GitHub）：
     1. 复制公钥内容：

        ```bash
        cat ~/.ssh/id_ed25519.pub
        ```

     2. 将输出内容粘贴到 GitHub 的 **Settings → SSH and GPG keys → New SSH Key**。

---

### 方法 2：启用 Git 凭据缓存（HTTPS 适用）

1. **缓存密码**（默认 15 分钟）：

   ```bash
   git config --global credential.helper cache
   ```

2. **自定义缓存时间**（例如 1 小时）：

   ```bash
   git config --global credential.helper 'cache --timeout=3600'
   ```

3. **永久存储密码**（明文保存，慎用）：

   ```bash
   git config --global credential.helper store
   ```

   **注意**：密码会保存在 `~/.git-credentials` 文件中。

---

### 方法 3：使用个人访问令牌（GitHub 适用）

如果启用了 **双重认证 (2FA)**，必须使用令牌替代密码：

1. **生成令牌**：
   - 进入 GitHub → **Settings → Developer Settings → Personal Access Tokens**，生成一个令牌（勾选 `repo` 权限）。

2. **提交时使用令牌**：
   - 输入密码时，替换为生成的令牌。

---

### 其他提示

- **检查 Git 配置**：

  ```bash
  git config --global --list
  ```

  确保 `credential.helper` 已正确设置。

- **更新 Git 客户端**：
  旧版本 Git 可能存在凭据管理问题，升级到最新版本。

---

通过以上方法，你可以避免每次提交都输入密码。推荐使用 **SSH 协议** 或 **凭据缓存** 提升效率。
