# Windows 安装 JDK

**参考资料：**

- [https://cloud.tencent.com/developer/article/1835472](https://cloud.tencent.com/developer/article/1835472)
- [jdk17.0.4.1 镜像 - _ideal - 博客园 (cnblogs.com)](https://www.cnblogs.com/gkmin/p/16620528.html)

---

下载地址：[Java Downloads | Oracle](https://www.oracle.com/java/technologies/downloads/#java17)

配置系统变量：​​编辑系统环境变量 ->高级 -> 环境变量​​​

## 变量：JAVA_HOME

变量值：

```xml
C:\Users\liuzonglin\.jdks\corretto-17.0.6
```

---

### 变量：CLASSPATH

变量值：

```xml
.;%JAVA_HOME%\lib\dt.jar;%JAVA_HOME%\lib\tools.jar;
```

### 变量：Path

```sh
%JAVA_HOME%\bin
%JAVA_HOME%\jre\bin
```

### 测试

```sh
java  -version
javac -version
```
