在Git中撤销上一次提交有多种方法，具体取决于你的需求：

## 1. 撤销提交但保留更改（修改后重新提交）

```bash
git reset --soft HEAD~1
```

- 提交被撤销，更改保留在暂存区
- 适合修改提交信息或重新组织提交

## 2. 撤销提交并取消暂存更改

```bash
git reset HEAD~1
# 或
git reset --mixed HEAD~1  # 默认选项
```

- 提交被撤销，更改保留在工作目录（未暂存）
- 可以重新选择要提交的文件

## 3. 彻底撤销提交（丢弃更改）

```bash
git reset --hard HEAD~1
```

⚠️ **警告**：这会永久丢弃上次提交的所有更改！慎用！

## 4. 创建撤销提交（推荐用于已推送的提交）

```bash
git revert HEAD
```

- 创建一个新的提交来撤销上次提交的更改
- 不会改写历史，适合已推送到远程仓库的情况

## 5. 查看状态确认操作

```bash
git status
git log --oneline  # 查看提交历史
```

## 使用建议：

### 如果还没推送到远程：

- 使用 `git reset` 修改本地历史
- 示例：修改提交信息

  ```bash
  git reset --soft HEAD~1
  git commit -m "新的提交信息"
  ```

### 如果已经推送到远程：

- 使用 `git revert` 创建撤销提交
- 示例：

  ```bash
  git revert HEAD
  git push
  ```

### 如果误操作了：

```bash
# 查看引用日志，找到之前的提交
git reflog

# 恢复到某个状态
git reset --hard HEAD@{n}
```

## 示例场景：

**场景1**：刚提交但发现漏了文件

```bash
git reset --soft HEAD~1        # 撤销提交
git add 漏掉的文件.txt         # 添加漏掉的文件
git commit -m "完整的提交"      # 重新提交
```

**场景2**：想完全放弃上次提交

```bash
git reset --hard HEAD~1        # 彻底撤销（谨慎使用！）
```

**场景3**：已经推送到GitHub，需要撤销

```bash
git revert HEAD                # 创建撤销提交
git push                       # 推送到远程
```

选择哪种方法取决于你的具体需求和提交是否已推送。