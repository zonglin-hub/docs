# Markdown 流程图

## 横向流程图

```
graph LR;
A[Hard edge] -->|Label| B(Round edge)
B --> C{Decision}
C -->|One| D[Result one]
C -->|Two| E[Result two]
```

{{< diagram >}}
graph LR;
A[Hard edge] -->|Label| B(Round edge)
B --> C{Decision}
C -->|One| D[Result one]
C -->|Two| E[Result two]
{{< /diagram >}}

## 纵向流程图

```
graph TD;

A[christmas] -->B(Go shopping)

B --> C{Let me think}

C -->|One| D[Laptop]

C -->|Two| E[iPhone]

C -->|Three|F[Car]
```

{{< diagram >}}

graph TD;

A[christmas] -->B(Go shopping)

B --> C{Let me think}

C -->|One| D[Laptop]

C -->|Two| E[iPhone]

C -->|Three|F[Car]

{{< /diagram >}}

## 控制图

```markdown
st=>start: Start
op=>operation: Your Operation
cond=>condition: Yes or No?
e=>end

st->op->cond
cond(yes)->e
cond(no)->op
```

## 序列图

```
sequenceDiagram

loop every day

Alice->>John: Hello John, how are you?

John-->> Alice: Great!

end
```

## 甘特图

```
gantt

dateFormat YYYY-MM-DD

title 产品计划表

section 初期阶段

明确需求: 2016-03-01, 10d

section 中期阶段

跟进开发: 2016-03-11, 15d

section 后期阶段

走查测试: 2016-03-20, 9d
```

​

## See also

* [有道云笔记 - Markdown 模板](https://blog.csdn.net/lzuacm/article/details/81107109)
