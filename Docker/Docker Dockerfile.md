# Dockerfile

基础知识

1. 每个保留关键字（指令）都是**必须大写字母（约定俗成）**
2. 执行**从上到下顺序执行**
3. **&quot;#&quot; 表示注释**
4. **每个指令都会创建提交一个新的镜像层**，并提交！
5. Dockerfile 中<u>引用的所有文件一定要和Dockerfile文件在同一级父目录下</u>，可以为 Dockerfile 父目录的子目录
6. Dockerfile 中相对路径默认都是 Dockerfile 所在的目录

Dockerfile 指令介绍

```dockerfile
FROM                     # 基础镜像，一切从这里开始构建
MAINTAINER              # 镜像是谁写的， 姓名+邮箱
RUN                        # 镜像构建的时候需要运行的命令
ADD                        # 步骤， tomcat镜像， 这个tomcat压缩包！添加内容
WORKDIR                  # 镜像的工作目录
VOLUME                     # 挂载的目录
EXPOSE                  # 保留端口配置
CMD                       # 指定这个容器启动的时候要运行的命令，只有最后一个会生效可被替代
ENTRYPOINT              # 指定这个容器启动的时候要运行的命令， 可以追加命令
ONBUILD                    # 当构建一个被继承DockerFile 这个时候就会运行 ONBUILD 的指令，触发指令
COPY                      # 类似ADD, 将我们文件拷贝到镜像中
ENV                        # 构建的时候设置环境变量！
```

参考文档

- [制作 Docker jdk17 镜像](https://www.cnblogs.com/gkmin/p/16620528.html)
- [Dockerfile 详解超全](https://blog.csdn.net/AtlanSI/article/details/87892016)
