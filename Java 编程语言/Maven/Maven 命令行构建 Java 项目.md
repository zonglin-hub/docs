# Maven 命令行构建 Java 项目

- [使用Maven构建SpringBoot项目](https://blog.csdn.net/m0_59420288/article/details/128177819)
- [xml - 在 Spring-Boot Intro 之后， &quot;Unable to find a suitable main class, please add a ' mainClass' 属性&quot;](https://www.coder.work/article/4058647)
- [Spring Boot 学习笔记 3 : mvn spring-boot:run](https://blog.csdn.net/SmileorSilence/article/details/79085867)
- [springboot命令行启动的方法详解](https://www.php1.cn/detail/springboot_MingL_818fc6bc.html)
- [(maven常用命令大全(附详细解释)_mvn命令详解](https://blog.csdn.net/good_good_xiu/article/details/116740333)

---

- 使用命令生成Maven工程

  ```shell
  mvn archetype:generate \
   -DgroupId=com.project \
   -DartifactId=example \
   -DarchetypeArtifactId=maven-archetype-quickstart \
   -DinteractiveMode=false
  ```

  参数说明：

  - -DgroupId=com.project：组 ID

  - -DartifactId=example：项目名称，maven会根据这个名称在当前目录下新建一个名为该名称的目录用于建立项目

  - -DarchetypeArtifactId：项目原型，使用哪种原型来创建项目的初始结构,在这里，我们先使用maven-archetype-quickstart创建一个简单的 Java 应用

  - -DinteractiveMode=false ：是否已交互模式进行，如果是false的话就会采用默认设置建立项目

- `mvn archetype:generate`​ 操作案例：

  ```shell
  $ mvn archetype:generate
  [INFO] Scanning for projects...
  [INFO]
  [INFO] ------------------< org.apache.maven:standalone-pom >-------------------
  [INFO] Building Maven Stub Project (No POM) 1
  [INFO] --------------------------------[ pom ]---------------------------------
  [INFO]
  [INFO] >>> maven-archetype-plugin:3.2.1:generate (default-cli) > generate-sources @ standalone-pom >>>
  [INFO]
  [INFO] <<< maven-archetype-plugin:3.2.1:generate (default-cli) < generate-sources @ standalone-pom <<<
  [INFO]
  [INFO]
  [INFO] --- maven-archetype-plugin:3.2.1:generate (default-cli) @ standalone-pom ---
  [INFO] Generating project in Interactive mode
  [WARNING] No archetype found in remote catalog. Defaulting to internal catalog
  [INFO] No archetype defined. Using maven-archetype-quickstart (org.apache.maven.archetypes:maven-archetype-quickstart:1.0)
  Choose archetype:
  1: internal -> org.apache.maven.archetypes:maven-archetype-archetype (An archetype which contains a sample archetype.)
  2: internal -> org.apache.maven.archetypes:maven-archetype-j2ee-simple (An archetype which contains a simplifed sample J2EE application.)
  3: internal -> org.apache.maven.archetypes:maven-archetype-plugin (An archetype which contains a sample Maven plugin.)
  4: internal -> org.apache.maven.archetypes:maven-archetype-plugin-site (An archetype which contains a sample Maven plugin site.
        This archetype can be layered upon an existing Maven plugin project.)
  5: internal -> org.apache.maven.archetypes:maven-archetype-portlet (An archetype which contains a sample JSR-268 Portlet.)
  6: internal -> org.apache.maven.archetypes:maven-archetype-profiles ()
  7: internal -> org.apache.maven.archetypes:maven-archetype-quickstart (An archetype which contains a sample Maven project.)
  8: internal -> org.apache.maven.archetypes:maven-archetype-site (An archetype which contains a sample Maven site which demonstrates
        some of the supported document types like APT, XDoc, and FML and demonstrates how
        to i18n your site. This archetype can be layered upon an existing Maven project.)
  9: internal -> org.apache.maven.archetypes:maven-archetype-site-simple (An archetype which contains a sample Maven site.)
  10: internal -> org.apache.maven.archetypes:maven-archetype-webapp (An archetype which contains a sample Maven Webapp project.)
  Choose a number or apply filter (format: [groupId:]artifactId, case sensitive contains): 7: # 直接回车，使用默认值。
  Define value for property 'groupId': com.atguigu.maven # 设置组 ID
  Define value for property 'artifactId': pro01-maven-java # 设置项目名称
  Define value for property 'version' 1.0-SNAPSHOT: : # 直接回车，使用默认值。
  Define value for property 'package' com.atguigu.maven: : # 直接回车，使用默认值。
  Confirm properties configuration:
  groupId: com.atguigu.maven
  artifactId: pro01-maven-java
  version: 1.0-SNAPSHOT
  package: com.atguigu.maven
   Y: : # 直接回车，表示确认。如果，前面输入错误，想要重新输入，则输入 N 在回车。
  [INFO] ----------------------------------------------------------------------------
  [INFO] Using following parameters for creating project from Old (1.x) Archetype: maven-archetype-quickstart:1.1
  [INFO] ----------------------------------------------------------------------------
  [INFO] Parameter: basedir, Value: D:\.github\.dome\maven
  [INFO] Parameter: package, Value: com.atguigu.maven
  [INFO] Parameter: groupId, Value: com.atguigu.maven
  [INFO] Parameter: artifactId, Value: pro01-maven-java
  [INFO] Parameter: packageName, Value: com.atguigu.maven
  [INFO] Parameter: version, Value: 1.0-SNAPSHOT
  [INFO] project created from Old (1.x) Archetype in dir: D:\.github\.dome\maven\pro01-maven-java
  [INFO] ------------------------------------------------------------------------
  [INFO] BUILD SUCCESS
  [INFO] ------------------------------------------------------------------------
  [INFO] Total time:  01:16 min
  [INFO] Finished at: 2023-01-08T14:15:53+08:00
  [INFO] ------------------------------------------------------------------------
  ```

- 常用打包命令 `mvnw`​或`mvn`​

  ```shell
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
