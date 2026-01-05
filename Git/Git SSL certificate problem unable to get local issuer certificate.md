# Git SSL certificate problem unable to get local issuer certificate

> “git SSL certificate problem unable to get local issuer certificate”这句话的意思是在使用 git 时遇到了 SSL 证书问题，无法获取本地颁发者证书。
>
> 这通常表示在与远程仓库进行通信时，git 无法验证服务器的 SSL 证书的合法性。可能是因为系统缺少必要的根证书，或者证书的配置出现了问题。
>
> 出现这个问题可能会导致无法从远程仓库拉取或推送代码。解决这个问题的方法通常包括更新系统的根证书、配置 git 忽略证书验证（不推荐，因为这会降低安全性）或者手动添加服务器的证书到本地信任存储中。
>
> 这个问题是由于没有配置信任的服务器 HTTPS 验证。默认，cURL 被设为不信任任何 CAs，就是说，它不信任任何服务器验证。只需要执行下面命令就可以解决：

```sh
git config --global http.sslVerify false
```
