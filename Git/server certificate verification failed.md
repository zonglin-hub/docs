git clone报错 server certificate verification failed. CAfile: none CRLfile: none

当使用命令 git pull 出现错误信息如下: server certificate verification failed. CAfile: none CRLfile: none 解决方案:

```bash
git config --global http.sslverify false
git config --global https.sslverify false
```

git clone报错：“server certificate verification failed. CAfile: /etc/ssl/certs/ca-certificates.crt CRLfile: none” I can push by clone project using ssh, but it doesn’t work when I clone project with https. it shows message error as below.

server certificate verification failed. CAfile: /etc/ssl/certs/ca-certificates.crt CRLfile: none

解决方案： Open your terminal and run following command:

```bash
export GIT_SSL_NO_VERIFY=1
```