```properties
# 每次文件上传大小
spring.servlet.multipart.max-file-size=10MB
# 一次请求上传可上传文件大小
spring.servlet.multipart.max-request-size=10MB
# 设置 classpath 目录下的 WEB-INF 文件夹内容修改不重启
spring.devtools.restart.exclude=static/**
# 自定义静态文件访问地址
spring.devtools.restart.exclude=static/images/**
# 自定义静态目录 classpath: 类路径
spring.web.resources.static-locations=classpath:/css/
```
