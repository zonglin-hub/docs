
Markdown 本身并不直接支持流程图的绘制，但通过一些扩展语法和工具，如 **Mermaid** 和 **PlantUML**，可以在 Markdown 文件中嵌入流程图和其他图形。以下是两种常用的流程图工具及其支持的图表类型：

---

### **Mermaid**

Mermaid 是一种基于文本的图表工具，支持多种图表类型，语法简单，适合嵌入 Markdown 文件中。以下是 Mermaid 支持的图表类型：

#### 流程图

`graph LR（横向）|TD（纵向）;`

```mermaid
graph LR;

A[christmas] -->B(Go shopping)
B --> C{Let me think}
C -->|One| D[Laptop]
C -->|Two| E[iPhone]
C -->|Three|F[Car]
```

#### 序列图

```mermaid
sequenceDiagram
    participant User
    participant Executor
    participant Future
    participant Async
    User ->> Executor: Block on Futrue
    Executor ->> Future: Poll
    
    loop Until completion
        Future ->> Executor: Pending
        Async ->> Executor: Wake up
        Executor ->> Future: Poll
    end

    Future ->> Executor: Ready
    Executor ->> User: Return value
    
    title 执行者执行 Future 的时序图
```

#### 甘特图

```mermaid
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

#### **类图（Class Diagram）**

```mermaid
classDiagram
    class User {
        +String name
        +int age
        +void login()
    }
    class Product {
        +String name
        +float price
    }
    User "1" -- "*" Product : 购买
```

#### **状态图（State Diagram）**

```mermaid
stateDiagram-v2
    [*] --> 状态1
    状态1 --> 状态2 : 事件1
    状态2 --> 状态3 : 事件2
    状态3 --> [*]
```
