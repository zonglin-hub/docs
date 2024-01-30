# DTO与VO的区别

- [DTO与VO的区别 - 剑轩的专栏](http://www.tnblog.net/aojiancc2/article/details/2396)
- [一篇文章讲清楚VO，BO，PO，DO，DTO的区别](https://www.jianshu.com/p/072304c3dfb7)

---

## 概念

- VO（View Object）：视图对象，用于展示层，它的作用是把某个指定页面（或组件）的所有数据封装起来。
- DTO（Data Transfer Object）：数据传输对象，泛指用于展示层与服务层之间的数据传输对象。
- PO（Persistent Object）：持久化对象,就是和数据库保持一致的对象
- BO（ Business Object）：业务对象。 由Service层输出的封装业务逻辑的对象。
- DO（ Data Object）：与数据库表结构一一对应，通过DAO层向上传输数据源对象。 == PO  == Entity
- DTO（ Data Transfer Object）：数据传输对象，Service或Manager向外传输的对象。
- AO（ Application Object）：应用对象。 在Web层与Service层之间抽象的复用对象模型，极为贴近展示层，复用度不高。
- POJO（ Plain Ordinary Java Object）：在本手册中， POJO专指只有setter/getter/toString的简单类，包括DO/DTO/BO/VO等。
- Query：数据查询对象，各层接收上层的查询请求。 注意超过2个参数的查询封装，禁止使用Map类来传输。
