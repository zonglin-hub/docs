# Markdown 目录

在文章摘要或者文章简介之后，填写 `[TOC]` 以显示全文内容的目录结构。

TOC 是 Table of Contents 的简写。

## 目录

```markdown
- [Markdown 目录](#markdown-目录)
    - [目录](#目录)
    - [Span 标签](#span-标签)
```

<details> <summary>效果展示：</summary>

- [Markdown 目录](#markdown-目录)
    - [目录](#目录)
    - [Span 标签](#span-标签)

</details>

## Span 标签

```markdown
yum [[Options](#jump1)] [COMMAND](#jump2)

<span id="jump1"><strong>List of Commands: </strong></span>

    check

<span id="jump2"><strong>Options: </strong></span>

    -q, --quiet
```

<details> <summary>效果展示：</summary>

yum [[Options](#jump1)] [COMMAND](#jump2)

<span id="jump1"><strong>List of Commands: </strong></span>

    check

<span id="jump2"><strong>Options: </strong></span>

    -q, --quiet

</details>
