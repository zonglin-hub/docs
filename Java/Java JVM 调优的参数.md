# JVM 调优的参数

* ​`-Xms2g`​：初始化推大小为 2g；
* ​`-Xmx2g`​：堆最大内存为 2g；
* ​`-XX:NewRatio=4`​：设置年轻的和老年代的内存比例为 1:4；
* ​`-XX:SurvivorRatio=8`​：设置新生代 Eden 和 Survivor 比例为 8:2；
* ​`–XX:+UseParNewGC`​：指定使用 ParNew + Serial Old 垃圾回收器组合；
* ​`-XX:+UseParallelOldGC`​：指定使用 ParNew + ParNew Old 垃圾回收器组合；
* ​`-XX:+UseConcMarkSweepGC`​：指定使用 CMS + Serial Old 垃圾回收器组合；
* ​`-XX:+PrintGC`​：开启打印 gc 信息；
* ​`-XX:+PrintGCDetails`​：打印 gc 详细信息。

‍
