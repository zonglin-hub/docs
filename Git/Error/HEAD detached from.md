# HEAD detached from origin/master

<pre>
liuzonglin@LAPTOP-CGO0UV3J MINGW64 /d/.github/java-dome ((a0734b5...))
$ git commit -m "日常更新"
HEAD detached from origin/master
nothing to commit, working tree clean

liuzonglin@LAPTOP-CGO0UV3J MINGW64 /d/.github/java-dome ((a0734b5...))
$ ^C

liuzonglin@LAPTOP-CGO0UV3J MINGW64 /d/.github/java-dome ((a0734b5...))
$ git branch v

liuzonglin@LAPTOP-CGO0UV3J MINGW64 /d/.github/java-dome ((a0734b5...))
$ git branch -v
* (HEAD detached from origin/master) a0734b5 日常更新
  master                             89ecd4a new file dubbo-api-task  deleted springboot-rabbitmq
  v                                  a0734b5 日常更新
liuzonglin@LAPTOP-CGO0UV3J MINGW64 /d/.github/java-dome ((a0734b5...))
$ git checkout master
Previous HEAD position was a0734b5 日常更新
Switched to branch 'master'
Your branch is up to date with 'origin/master'.

liuzonglin@LAPTOP-CGO0UV3J MINGW64 /d/.github/java-dome (master)
$ git merge a0734b5
Updating 89ecd4a..a0734b5
Fast-forward
 .gitignore                                         |   3 +-
 data-structure/README.md                           |   5 +
 data-structure/build.gradle                        |  19 ++
 data-structure/gradle/wrapper/gradle-wrapper.jar   | Bin 0 -> 59821 bytes
 .../gradle/wrapper/gradle-wrapper.properties       |   5 +
 data-structure/gradlew                             | 234 +++++++++++++++++++++
 data-structure/gradlew.bat                         |  89 ++++++++
 data-structure/settings.gradle                     |   2 +
 .../src/main/java/org/example/common/Utils.java    |  61 ++++++
 .../main/java/org/example/heap/HeapShiftUp.java    |  65 ++++++
 .../src/main/java/org/example/heap/MaxHeap.java    |  37 ++++
 .../main/java/org/example/sort/InsertionSort.java  |  36 ++++
 .../main/java/org/example/sort/SelectionSort.java  |  35 +++
 .../dubbo-spring-boot-demo-consumer/pom.xml        |  19 ++
 .../dubbo-spring-boot-demo-interface/pom.xml       |  19 ++
 .../dubbo-spring-boot-demo-provider/pom.xml        |  19 ++
 dubbo-spring-boot-demo/pom.xml                     |  67 ++++++
 .../example/sys/controller/LoginController.java    |   1 -
 thread/README.md                                   |   1 +
 thread/build.gradle                                |  19 ++
 thread/gradle/wrapper/gradle-wrapper.jar           | Bin 0 -> 59821 bytes
 thread/gradle/wrapper/gradle-wrapper.properties    |   5 +
 thread/gradlew                                     | 234 +++++++++++++++++++++
 thread/gradlew.bat                                 |  89 ++++++++
 thread/settings.gradle                             |   2 +
 .../java/org/example/Number/ThreadPoolTest.java    |  67 ++++++
 .../java/org/example/sync/SaleTicketDemo3.java     |  36 ++++
 .../java/org/example/sync/SaleTicketDemo4.java     |  31 +++
 .../java/org/example/sync/SaleTicketDemo5.java     |  61 ++++++
 .../main/java/org/example/thread/LifeCycle.java    |  43 ++++
 thread/src/main/java/org/example/thread/Main.java  |  17 ++
 .../main/java/org/example/thread/MyRunnable.java   |  20 ++
 .../src/main/java/org/example/thread/MyThread.java |  37 ++++
 .../java/org/example/thread/ThreadStateChange.java |  33 +++
 .../java/org/example/volatiles/volatileTest.java   |  49 +++++
 35 files changed, 1458 insertions(+), 2 deletions(-)
 create mode 100644 data-structure/README.md
 create mode 100644 data-structure/build.gradle
 create mode 100644 data-structure/gradle/wrapper/gradle-wrapper.jar
 create mode 100644 data-structure/gradle/wrapper/gradle-wrapper.properties
 create mode 100644 data-structure/gradlew
 create mode 100644 data-structure/gradlew.bat
 create mode 100644 data-structure/settings.gradle
 create mode 100644 data-structure/src/main/java/org/example/common/Utils.java
 create mode 100644 data-structure/src/main/java/org/example/heap/HeapShiftUp.java
 create mode 100644 data-structure/src/main/java/org/example/heap/MaxHeap.java
 create mode 100644 data-structure/src/main/java/org/example/sort/InsertionSort.java
 create mode 100644 data-structure/src/main/java/org/example/sort/SelectionSort.java
 create mode 100644 dubbo-spring-boot-demo/dubbo-spring-boot-demo-consumer/pom.xml
 create mode 100644 dubbo-spring-boot-demo/dubbo-spring-boot-demo-interface/pom.xml
 create mode 100644 dubbo-spring-boot-demo/dubbo-spring-boot-demo-provider/pom.xml
 create mode 100644 dubbo-spring-boot-demo/pom.xml
 create mode 100644 thread/README.md
 create mode 100644 thread/build.gradle
 create mode 100644 thread/gradle/wrapper/gradle-wrapper.jar
 create mode 100644 thread/gradle/wrapper/gradle-wrapper.properties
 create mode 100644 thread/gradlew
 create mode 100644 thread/gradlew.bat
 create mode 100644 thread/settings.gradle
 create mode 100644 thread/src/main/java/org/example/Number/ThreadPoolTest.java
 create mode 100644 thread/src/main/java/org/example/sync/SaleTicketDemo3.java
 create mode 100644 thread/src/main/java/org/example/sync/SaleTicketDemo4.java
 create mode 100644 thread/src/main/java/org/example/sync/SaleTicketDemo5.java
 create mode 100644 thread/src/main/java/org/example/thread/LifeCycle.java
 create mode 100644 thread/src/main/java/org/example/thread/Main.java
 create mode 100644 thread/src/main/java/org/example/thread/MyRunnable.java
 create mode 100644 thread/src/main/java/org/example/thread/MyThread.java
 create mode 100644 thread/src/main/java/org/example/thread/ThreadStateChange.java
 create mode 100644 thread/src/main/java/org/example/volatiles/volatileTest.java

liuzonglin@LAPTOP-CGO0UV3J MINGW64 /d/.github/java-dome (master)
$ git branch -v
* master a0734b5 [ahead 1] 日常更新
  v      a0734b5 日常更新

liuzonglin@LAPTOP-CGO0UV3J MINGW64 /d/.github/java-dome (master)
$ git status
On branch master
Your branch is ahead of 'origin/master' by 1 commit.
  (use "git push" to publish your local commits)

nothing to commit, working tree clean

liuzonglin@LAPTOP-CGO0UV3J MINGW64 /d/.github/java-dome (master)
$ git push
Enumerating objects: 76, done.
Counting objects: 100% (76/76), done.
Delta compression using up to 12 threads
Compressing objects: 100% (45/45), done.
Writing objects: 100% (65/65), 69.56 KiB | 7.73 MiB/s, done.
Total 65 (delta 9), reused 0 (delta 0), pack-reused 0
remote: Powered by GITEE.COM [GNK-6.4]
To https://gitee.com/liuzonglin1/java-dome.git
   89ecd4a..a0734b5  master -> master

liuzonglin@LAPTOP-CGO0UV3J MINGW64 /d/.github/java-dome (master)
</pre>
