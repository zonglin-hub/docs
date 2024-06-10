# Windows 安装 Gradle

**参考资料：**

- [【尚硅谷】Gradle教程入门到进阶（从gradle安装到项目实战）_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV1yT41137Y7/?spm_id_from=333.999.0.0&vd_source=9bfc54d2ed901f1eab04708cc346c2f5)

---

官网地址：[Gradle | Releases](https://gradle.org/releases/)下载并解压到指定目录

```sh
变量：GRADLE_HOME
变量值：D:\program\gradle-all\gradle-8.0.2

# GRALE_USER_HOME 相当于配置 Gradle 本地仓库位置和 Gradle Wrapper 缓存目录。
变量：GRADLE_USER_HOME
变量值：D:\.github\.m2\repository

变量：Path
变量值：%GRADLE_HOME%\bin

# 检测是否成功
gradle -v
gradle --version
```

配置远程仓库地址 `gradle-8.0.2/init.d/init.gradle`

```gradle
allprojects {
    repositories {
        mavenLocal()
        maven { name "Alibaba" ; url "https://maven.aliyun.com/repository/public" }
        maven { name "Bstek" ; url "https://nexus.bsdn.org/content/groups/public/" }
        mavenCentral()
    }
    buildscript {
        repositories {
            maven { name "Alibaba" ; url 'https://maven.aliyun.com/repository/public' }
            maven { name "Bstek" ; url 'https://nexus.bsdn.org/content/groups/public/' }
            maven { name "M2" ; url 'https://plugins.gradle.org/m2/' }
        }
    }
}
```
