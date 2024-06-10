# Windows 安装 JDK

下载地址：[Java Downloads | Oracle]

[Java Downloads | Oracle]: https://www.oracle.com/java/technologies/downloads/

配置系统变量：__​​编辑系统环境变量__ -> __高级__ -> __环境变量​​​__

__变量：JAVA_HOME__

变量值：

```xml
C:\Users\liuzonglin\.jdks\corretto-17.0.6
```

---

__变量：CLASSPATH__

变量值：

```xml
.;%JAVA_HOME%\lib\dt.jar;%JAVA_HOME%\lib\tools.jar;
```

__变量：Path__

```sh
%JAVA_HOME%\bin
%JAVA_HOME%\jre\bin
```

---

__环境测试__

```sh
java  -version
javac -version
```
