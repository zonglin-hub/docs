# Windows 安装 Maven

环境变量

```sh
# 变量：JAVAHOME
变量值：D:\.github\.m2\settings.xml

# 变量：MAVEN_HOME
变量值：D:\program\apache-maven\apache-maven-3.9.0

# 变量：Path
变量值：%MAVEN_HOME%\bin

# 测试
mvn -v

## Maven 核心配置文件路径：
apache-maven/conf/settings.xml
```

配置依赖仓库

```xml
  <localRepository>D:\.github\.m2\repository</localRepository>
```

阿里云公共仓库是 central 仓和 jcenter 仓的聚合仓

```xml
<mirrors>
 <mirror>
  <id>aliyunmaven</id>
  <mirrorOf>*</mirrorOf>
  <name>阿里云公共仓库</name>
  <url>https://maven.aliyun.com/repository/public</url>
 </mirror>
 <mirror>
  <id>aliyunmaven</id>
  <mirrorOf>*</mirrorOf>
  <name>阿里云谷歌仓库</name>
  <url>https://maven.aliyun.com/repository/google</url>
 </mirror>
 <mirror>
  <id>aliyunmaven</id>
  <mirrorOf>*</mirrorOf>
  <name>阿里云阿帕奇仓库</name>
  <url>https://maven.aliyun.com/repository/apache-snapshots</url>
 </mirror>
 <mirror>
  <id>aliyunmaven</id>
  <mirrorOf>*</mirrorOf>
  <name>阿里云spring仓库</name>
  <url>https://maven.aliyun.com/repository/spring</url>
 </mirror>
 <mirror>
  <id>aliyunmaven</id>
  <mirrorOf>*</mirrorOf>
  <name>阿里云spring插件仓库</name>
  <url>https://maven.aliyun.com/repository/spring-plugin</url>
 </mirror>
 <mirror>
  <id>maven</id>
  <mirrorOf>*</mirrorOf>
  <name>maven仓库</name>
  <url>https://mvnrepository.com/</url>
 </mirror>
 <mirror>
  <id>nexus-aliyun</id>
  <mirrorOf>central</mirrorOf>
  <name>Nexus aliyun</name>
  <url>http://maven.aliyun.com/nexus/content/groups/public</url>
 </mirror>
</mirrors>
```

配置 Maven 工程的基础 JDK1.8 版本编译项目

```xml
<profiles>
 <profile>
  <id>JDK-1.8</id>
  <activation>
   <activeByDefault>true</activeByDefault>
   <jdk>1.8</jdk>
  </activation>
  <properties>
   <maven.compiler.source>1.8</maven.compiler.source>
   <maven.compiler.target>1.8</maven.compiler.target>
   <maven.compiler.compilerVersion>1.8</maven.compiler.compilerVersion>
  </properties>
 </profile>
</profiles>
```

- 常用打包命令 `mvnw`​或`mvn`​

  ```sh
  ./mvnw clean package -Dmaven.test.skip=true   # 跳过单测打包
  ./mvnw clean install -Dmaven.test.skip=true   # 跳过单测打包，并把打好的包上传到本地仓库
  ./mvnw clean deploy -Dmaven.test.skip=true   # 跳过单测打包，并把打好的包上传到远程仓库
  ./mvnw -v        # 查看版本
  ./mvnw archetype:create      # 创建 Maven 项目
  ./mvnw compile        # 编译源代码
  ./mvnw test-compile       # 编译测试代码
  ./mvnw test        # 运行应用程序中的单元测试
  ./mvnw site        # 生成项目相关信息的网站
  ./mvnw package        # 依据项目生成 jar 文件
  ./mvnw install        # 在本地 Repository 中安装 jar
  ./mvnw -Dmaven.test.skip=true      # 忽略测试文档编译
  ./mvnw clean        # 清除目标目录中的生成结果
  ./mvnw clean compile       # 将.java类编译为.class文件
  ./mvnw clean package       # 进行打包
  ./mvnw clean test       # 执行单元测试
  ./mvnw clean deploy       # 部署到版本仓库
  ./mvnw clean install       # 使其他项目使用这个jar,会安装到maven本地仓库中
  ./mvnw archetype:generate      # 创建项目架构
  ./mvnw dependency:list       # 查看已解析依赖
  ./mvnw dependency:tree com.xx.xxx     # 看到依赖树
  ./mvnw dependency:tree      # 命令解决jar包冲突
  ./mvnw dependency:analyze      # 查看依赖的工具
  ./mvnw help:system       # 从中央仓库下载文件至本地仓库
  ./mvnw help:active-profiles      # 查看当前激活的profiles
  ./mvnw help:all-profiles      # 查看所有profiles
  ./mvnw help:effective -pom      # 查看完整的pom信息
  ```

‍

参考文档

- 官网下载地址：[Maven – Download Apache Maven](https://maven.apache.org/download.cgi)
