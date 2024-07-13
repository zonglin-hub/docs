# git SSL certificate problem unable to get local issuer certificate

这个问题是由于没有配置信任的服务器 HTTPS 验证。默认，cURL 被设为不信任任何 CAs，就是说，它不信任任何服务器验证。只需要执行下面命令就可以解决：

```sh
git config --global http.sslVerify false
```
