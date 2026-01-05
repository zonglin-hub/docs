# 内存溢出问题该如何解决

- [Java面试题：内存溢出怎么产生的？如何解决的](https://zhuanlan.zhihu.com/p/413237280)
- [Linux jar包 后台运行](https://blog.csdn.net/qq_30739519/article/details/51115075)

**第一步：** 修改JVM启动参数，直接增加内存。(-Xms，-Xmx参数一定不要忘记加。)  
**第二步：** 检查错误日志，查看 OutOfMemory（内存不足）错误前是否有其它异常或错误。  
**第三步：** 对代码进行走查和分析，找出可能发生内存溢出的位置。

```shell
nohup java -jar xxxx.jar & # 我们经常使用nohup command &命令形式来启动一些后台程序，比如一些java服务：
nohup java -jar xxxx.jar >/dev/null 2>&1 & # 为了不让一些执行信息输出到前台（控制台），我们还会加上刚才提到的>/dev/null 2>&1命令来丢弃所有的输出：
nohup java -jar -Xms512m -Xmx512m -Xss256k -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC -Dspring.profiles.active=docker,quartz web-ssh-1.0-SNAPSHOT.jar >/dev/null 2>&1 &
```

| 参数                            | 说明                                                                                                                                                                      |
| ------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| --spring.profiles.active=daily  | 这个可以在spring-boot启动中指定系统变量，多环境(测试、预发、线上配置)的区分在排查jar包冲突时，可以指定启动的-verbose:class 打印出启动的应用实际加载类的路径，来排查来源。 |
| -XX:+HeapDumpOnOutOfMemoryError | 在堆溢出时保存快照                                                                                                                                                        |
| -Xmixed                         | 混合模式执行 (默认)                                                                                                                                                       |
| -Xint                           | 仅解释模式执行                                                                                                                                                            |
| -Xbootclasspath:                | 用 : 分隔的目录和 zip/jar 文件>                                                                                                                                           |
| -Xdiag                          | 显示附加诊断消息                                                                                                                                                          |
| -Xnoclassgc                     | 禁用类垃圾收集                                                                                                                                                            |
| -Xincgc                         | 启用增量垃圾收集                                                                                                                                                          |
| -Xloggc:                        | 将 GC 状态记录在文件中 (带时间戳)                                                                                                                                         |
| -Xbatch                         | 禁用后台编译                                                                                                                                                              |
| -Xms                            | 设置初始 Java 堆大小                                                                                                                                                      |
| -Xmx                            | 设置最大 Java 堆大小                                                                                                                                                      |
| -Xss                            | 设置 Java 线程堆栈大小                                                                                                                                                    |
| -Xprof                          | 输出 cpu 配置文件数据                                                                                                                                                     |
| -Xfuture                        | 启用最严格的检查, 预期将来的默认值                                                                                                                                        |
| -Xrs                            | 减少 Java/VM 对操作系统信号的使用 (请参阅文档)                                                                                                                            |
| -Xcheck:jni                     | 对 JNI 函数执行其他检查                                                                                                                                                   |
| -Xshare:off                     | 不尝试使用共享类数据                                                                                                                                                      |
| -Xshare:auto                    | 在可能的情况下使用共享类数据 (默认)                                                                                                                                       |
| -Xshare:on                      | 要求使用共享类数据, 否则将失败                                                                                                                                            |
| -XshowSettings                  | 显示所有设置并继续                                                                                                                                                        |
| -XshowSettings:all              | 显示所有设置并继续                                                                                                                                                        |
| -XshowSettings:vm               | 显示所有与 vm 相关的设置并继续                                                                                                                                            |
| -XshowSettings:properties       | 显示所有属性设置并继续                                                                                                                                                    |
| -XshowSettings:locale           | 显示所有与区域设置相关的设置并继续                                                                                                                                        |
