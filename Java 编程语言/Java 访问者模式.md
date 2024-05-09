# 访问者模式（Visitor Pattern）

公园中存在多个景点，也存在多个游客，不同的游客对同一个景点的评价可能不同；

医院医生开的处方单中包含多种药元素，査看它的划价员和药房工作人员对它的处理方式也不同，划价员根据处方单上面的药品名和数量进行划价，药房工作人员根据处方单的内容进行抓药，相对于处方单来说，划价员和药房工作人员就是它的访问者，不过访问者的访问方式可能会不同。

在我们的Java程序中，也可能会出现这种情况，我们就可以通过访问者模式来进行设计。

比如我们日以继夜地努力，终于在某某比赛赢得了冠军，而不同的人对于这分荣誉，却有着不同的反应：

定义 JavaBean

```java
public class Prize { //奖
    String name;     // 比赛名称
    String level;    // 等级

    public Prize(String name, String level) {
        this.name = name;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public String getLevel() {
        return level;
    }
}
```

定义访问者接口：

```java
public interface Visitor {
    void visit(Prize prize);   //visit方法来访问我们的奖项
}
```

访问者相关的实现了：

```java
public class Teacher implements Visitor {   //指导老师作为一个访问者
    @Override
    public void visit(Prize prize) {   //它只关心你得了什么奖以及是几等奖，这也关乎老师的荣誉
        System.out.println("你得奖是什么奖？" + prize.name);
        System.out.println("你得了几等奖？" + prize.level);
    }
}
```

```java
public class Boss implements Visitor{    //你的公司老板作为一个访问者
    @Override
    public void visit(Prize prize) {   //你的老板只关心这些能不能为公司带来什么效益，奖本身并不重要
        System.out.println("你的奖项大么，能够为公司带来什么效益么？");
        System.out.println("还不如老老实实加班给我多干干，别去搞这些没用的");
    }
}
```

```java
public class Classmate implements Visitor{   //你的同学也可以作为一个访问者
    @Override
    public void visit(Prize prize) {   //你的同学也关心你得了什么奖，不过是因为你是他的奖学金竞争对手，他其实并不希望你得奖
        System.out.println("你得了" + prize.name + "奖啊，还可以");
        System.out.println("不过这个奖没什么含金量，下次别去了");
    }
}
```

```java
public class Family implements Visitor{    //你的家人也可以是一个访问者
    @Override
    public void visit(Prize prize) {   //你的家人并不是最关心你得了什么奖，而是先关心你自己然后才是奖项，他们才是真正希望你好的人。这个世界很残酷，可能你会被欺负得遍体鳞伤，可能你会觉得活着如此艰难，但是你的背后至少还有爱你的人，为了他们，怎能就此驻足。
        System.out.println("孩子，辛苦了，有没有好好照顾自己啊");
        System.out.println("你得了什么奖啊？" + prize.name + "，很不错，要继续加油啊！");
    }
}
```

可以看到，这里我们就设计了四种访问者，但是不同的访问者对于某一件事务的处理可能会不同。访问者模式把数据结构和作用于结构上的操作解耦，使得操作集合可相对自由地演化，我们上面就是将奖项本身的属性和对于奖项的不同操作进行了分离。

‍
