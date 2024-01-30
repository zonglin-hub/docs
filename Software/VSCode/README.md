# Visual Studio Code

## VSCode 常用配置

```yml
// User and Workspace Settings https://code.visualstudio.com/docs/getstarted/settings
{
    "editor.codeActionsOnSave": {},
    "editor.indentSize": "tabSize",
    // 在 VSCode 中启用连字字体需要用到两个选项 "editor.fontFamily", "editor.fontLigatures"
    "editor.fontFamily": "Fira Code Light, Consolas, Microsoft YaHei",
    "editor.fontLigatures": true,
    "editor.formatOnSave": true, // 自动保存
    "git.openRepositoryInParentFolders": "never",
    "explorer.confirmDelete": false,
    "debug.allowBreakpointsEverywhere": true,
    "files.autoSave": "afterDelay",
    "workbench.editor.showTabs": "single",
    "editor.minimap.enabled": false,
    "diffEditor.hideUnchangedRegions.enabled": true,
    "window.commandCenter": false,
    // markdownlint 配置：https://github.com/DavidAnson/markdownlint/blob/v0.32.1/doc/Rules.md
    "markdownlint.config": {
        "MD007": {
            "indent": 4, // 缩进空格
        },
        "MD010": {
            "spaces_per_tab": 4 // 每个硬制表符的空格数
        },
        "MD033": false, // 允许内联 HTML
        "MD036": false, // 使用强调代替标题
    },
    "[markdown]": {
        "editor.defaultFormatter": "DavidAnson.vscode-markdownlint" // markdownlint 为默认格式化
    },
    "workbench.iconTheme": "material-icon-theme",
}
```

## LeetCode 测试代码片段

```yml
{
    // 这里的格式可以通过以下链接进行格式化: https://snippet-generator.app/
    "LeetCode 测试代码片段": {
        "prefix": "v1",
        "body": [
          "#[cfg(test)]",
          "mod tests {",
          "    use crate::leet_code::Solution;",
          "",
          "    #[test]",
          "    fn test_two_sum() {",
          "        assert_eq!(Solution::);",
          "    }",
          "}"
        ],
        "description": "LeetCode 测试代码片段"
      }
}
```

## 扩展

### 公共插件

- Chinese (Simplified) (简体中文) Language Pack for Visual Studio Code
  
    中文语言包扩展(简体)

- Code Translate

    一款纯粹的 VSCode 滑词翻VSC

- Error Lens
  
    改进错误、警告和其他语言诊断的高亮显示。

- Git Graph
  
    查看仓库的 Git 图，并从图中执行 Git 操作。

- Image preview

    显示图像预览在阴沟和悬停

- Material Icon Theme

    Visual Studio Code 的 Material Design 图标

- Path Intellisense

    Visual Studio Code 插件，自动完成文件名

### markdown 模块插件

- Markdown All in One

    所有你需要写 Markdown (键盘快捷键，目录，自动预览等)

- Markdown Checkboxes

    为内置的 Markdown 预览添加复选框支持

- Markdown Emoji

    为 VSCode 内置的 Markdown 预览和 notebook 中的 Markdown 单元格添加了 emoji 语法支持

- Markdown Footnotes

    为 VSCode 内置的 Markdown 预览添加了 [^footnote] 语法支持

- Markdown Preview Enhanced

    Markdown 预览增强版移植到 VSCode

- Markdown Preview Mermaid Support

    在 VSCode 的内置 Markdown 预览中添加美人鱼图和流程图支持

- Markdown yaml Preamble

    在内置的 Markdown 预览中渲染 yaml 前端内容为一张表

- markdownlint

    VSCode 的 Markdown 检查和样式检查

### Rust 摸板插件

- Cargo
- CodeLLDB
- crates
- Even Better TOML
- rust-analyzer

### 扩展 yew

- HTML CSS Support
- rust-yew
