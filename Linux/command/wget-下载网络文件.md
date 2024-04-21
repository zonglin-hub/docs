# wget 下载网络文件

wget 命令 用来从指定的URL下载文件。wget 非常稳定，它在带宽很窄的情况下和不稳定网络中有很强的适应性，
如果是由于网络的原因下载失败，wget 会不断的尝试，直到整个文件下载完毕。如果是服务器打断下载过程，
它会再次联到服务器上从停止的地方继续下载。这对从那些限定了链接时间的服务器上下载大文件非常有用。

wget支持HTTP，HTTPS和FTP协议，可以使用HTTP代理。所谓的自动下载是指，wget可以在用户退出系统的之后在后台执行。
这意味这你可以登录系统，启动一个wget下载任务，然后退出系统，wget将在后台执行直到任务完成，
相对于其它大部分浏览器在下载大量数据时需要用户一直的参与，这省去了极大的麻烦。

用于从网络上下载资源，没有指定目录，下载资源回默认为当前目录。wget虽然功能强大，但是使用起来还是比较简单：

1. __支持断点下传功能__ 这一点，也是网络蚂蚁和FlashGet当年最大的卖点，现在，Wget也可以使用此功能，那些网络不是太好的用户可以放心了；
2. __同时支持FTP和HTTP下载方式__ 尽管现在大部分软件可以使用HTTP方式下载，但是，有些时候，仍然需要使用FTP方式下载软件；
3. __支持代理服务器__ 对安全强度很高的系统而言，一般不会将自己的系统直接暴露在互联网上，所以，支持代理是下载软件必须有的功能；
4. __设置方便简单__ 可能，习惯图形界面的用户已经不是太习惯命令行了，但是，命令行在设置上其实有更多的优点，最少，鼠标可以少点很多次，也不要担心是否错点鼠标；
5. __程序小，完全免费__ 程序小可以考虑不计，因为现在的硬盘实在太大了；完全免费就不得不考虑了，即使网络上有很多所谓的免费软件，但是，这些软件的广告却不是我们喜欢的。

## 目录

- [wget 下载网络文件](#wget-下载网络文件)
    - [目录](#目录)
    - [示例](#示例)
    - [帮助手册](#帮助手册)

## 示例

__使用 wget 下载单个文件__

```sh
wget http://www.jsdig.com/testfile.zip
```

以下的例子是从网络下载一个文件并保存在当前目录，在下载的过程中会显示进度条，
包含（下载完成百分比，已经下载的字节，当前下载速度，剩余下载时间）。

__下载并以不同的文件名保存__

```sh
wget -O wordpress.zip http://www.jsdig.com/download.aspx?id=1080
```

wget 默认会以最后一个符合 `/` 的后面的字符来命令，对于动态链接的下载通常文件名会不正确。

错误：下面的例子会下载一个文件并以名称 `download.aspx?id=1080` 保存:

```sh
wget http://www.jsdig.com/download?id=1
```

即使下载的文件是 zip 格式，它仍然以 `download.php?id=1080` 命名。

正确：为了解决这个问题，我们可以使用参数 `-O` 来指定一个文件名：

```sh
wget -O wordpress.zip http://www.jsdig.com/download.aspx?id=1080
```

__wget 限速下载__

```sh
wget --limit-rate=300k http://www.jsdig.com/testfile.zip
```

当你执行wget的时候，它默认会占用全部可能的宽带下载。但是当你准备下载一个大文件，
而你还需要下载其它文件时就有必要限速了。

__使用 wget 断点续传__

```sh
wget -c http://www.jsdig.com/testfile.zip
```

使用 `wget -c` 重新启动下载中断的文件，对于我们下载大文件时突然由于网络等原因中断非常有帮助，
我们可以继续接着下载而不是重新下载一个文件。需要继续中断的下载时可以使用 `-c` 参数。

__使用wget后台下载__

```sh
wget -b http://www.jsdig.com/testfile.zip

Continuing in background, pid 1840.
Output will be written to 'wget-log'.
```

对于下载非常大的文件的时候，我们可以使用参数 `-b` 进行后台下载，你可以使用以下命令来察看下载进度：

```sh
tail -f wget-log
```

__伪装代理名称下载__

```sh
wget --user-agent="Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US) AppleWebKit/534.16 (KHTML, like Gecko) Chrome/10.0.648.204 Safari/534.16" http://www.jsdig.com/testfile.zip
```

有些网站能通过根据判断代理名称不是浏览器而拒绝你的下载请求。不过你可以通过 `--user-agent` 参数伪装。

__测试下载链接__

当你打算进行定时下载，你应该在预定时间测试下载链接是否有效。我们可以增加 `--spider` 参数进行检查。

```sh
wget --spider URL
```

如果下载链接正确，将会显示:

```sh
Spider mode enabled. Check if remote file exists.
HTTP request sent, awaiting response... 200 OK
Length: unspecified [text/html]
Remote file exists and could contain further links,
but recursion is disabled -- not retrieving.
```

这保证了下载能在预定的时间进行，但当你给错了一个链接，将会显示如下错误:

```sh
wget --spider url
Spider mode enabled. Check if remote file exists.
HTTP request sent, awaiting response... 404 Not Found
Remote file does not exist -- broken link!!!
```

你可以在以下几种情况下使用 `--spider` 参数：

- 定时下载之前进行检查
- 间隔检测网站是否可用
- 检查网站页面的死链接

__增加重试次数__

```sh
wget --tries=40 URL
```

如果网络有问题或下载一个大文件也有可能失败。wget默认重试20次连接下载文件。
如果需要，你可以使用 `--tries` 增加重试次数。

__下载多个文件__

```sh
wget -i filelist.txt
```

首先，保存一份下载链接文件：

```sh
cat > filelist.txt
url1
url2
url3
url4
```

接着使用这个文件和参数 `-i` 下载。

__镜像网站__

```sh
wget --mirror -p --convert-links -P ./LOCAL URL
```

下载整个网站到本地。

- `--miror` 开户镜像下载。
- `-p` 下载所有为了 html 页面显示正常的文件。
- `--convert-links` 下载后，转换成本地的链接。
- `-P ./LOCAL` 保存所有文件和目录到本地指定目录。

__过滤指定格式下载__

```sh
wget --reject=gif ur
```

下载一个网站，但你不希望下载图片，可以使用这条命令。

__把下载信息存入日志文件__

```sh
wget -o download.log URL
```

不希望下载信息直接显示在终端而是在一个日志文件，可以使用。

__限制总下载文件大小__

```sh
wget -Q5m -i filelist.txt
```

当你想要下载的文件超过5M而退出下载，你可以使用。
注意：这个参数对单个文件下载不起作用，只能递归下载时才有效。

__下载指定格式文件__

```sh
wget -r -A.pdf url
```

可以在以下情况使用该功能：

- 下载一个网站的所有图片。
- 下载一个网站的所有视频。
- 下载一个网站的所有PDF文件。

__FTP 下载__

```sh
wget ftp-url
wget --ftp-user=USERNAME --ftp-password=PASSWORD url
```

可以使用 wget 来完成 ftp 链接的下载。

使用 wget 匿名 ftp 下载：

```sh
wget ftp-url
```

使用 wget 用户名和密码认证的 ftp 下载：

```sh
wget --ftp-user=USERNAME --ftp-password=PASSWORD url
```

## 帮助手册

```text
$ wget --help
GNU Wget 1.21.2，非交互式的网络文件下载工具。
用法： wget [选项]... [URL]...

长选项所必须的参数在使用短选项时也是必须的。

启动：
  -V,  --version                   显示 Wget 的版本信息并退出
  -h,  --help                      打印此帮助
  -b,  --background                启动后转入后台
  -e,  --execute=命令              运行一个“.wgetrc”风格的命令

日志和输入文件：
  -o,  --output-file=文件          将日志信息写入 FILE
  -a,  --append-output=文件        将信息添加至 FILE
  -d,  --debug                     打印大量调试信息
  -q,  --quiet                     安静模式 (无信息输出)
  -v,  --verbose                   详尽的输出 (此为默认值)
  -nv, --no-verbose                关闭详尽输出，但不进入安静模式
       --report-speed=类型         以 <类型> 报告带宽。类型可以是 bits
  -i,  --input-file=文件           下载本地或外部 <文件> 中的 URL
  -F,  --force-html                把输入文件当成 HTML 文件
  -B,  --base=URL                  解析相对于 URL 的 HTML 输入文件链接 (-i -F)
       --config=文件               指定要使用的配置文件
       --no-cookies                不读取任何配置文件
       --rejected-log=文件         将拒绝 URL 的原因写入 <文件>。

下载：
  -t,  --tries=数字                设置重试次数为 <数字> (0 代表无限制)
       --retry-connrefused         即使拒绝连接也是重试
       --retry-on-http-error=ERRORS    提供以逗号分隔的列表，列出遇到时进行重试的 HTTP 错误
  -O,  --output-document=文件      将文档写入 FILE
  -nc, --no-clobber                不要下载已存在将被覆盖的文件
       --no-netrc                  不要尝试从 .netrc 获取凭据
  -c,  --continue                  断点续传下载文件
       --start-pos=偏移量          从由零计数的 <偏移量> 开始下载
       --progress=类型             选择进度条类型
       --show-progress             在任意啰嗦状态下都显示进度条
  -N,  --timestamping              只获取比本地文件新的文件
       --no-if-modified-since      不要在时间戳 (timestamping) 模式下使用
                                     if-modified-since get 条件请求
       --no-use-server-timestamps  不用服务器上的时间戳来设置本地文件
  -S,  --server-response           打印服务器响应
       --spider                    不下载任何文件
  -T,  --timeout=SECONDS           将所有超时设为 SECONDS 秒
       --dns-timeout=SECS          设置 DNS 查寻超时为 SECS 秒
       --connect-timeout=SECS      设置连接超时为 SECS 秒
       --read-timeout=SECS         设置读取超时为 SECS 秒
  -w,  --wait=SECONDS              等待间隔为 SECONDS 秒
                                     （在获取的 URL 多于 1 个时生效）
       --waitretry=SECONDS         在获取文件的重试期间等待 1..SECONDS 秒
                                     （在获取的 URL 多于 1 个时生效）
       --random-wait               获取多个文件时，每次随机等待间隔 (0.5~1.5)*WAIT 秒
                                     （在获取的 URL 多于 1 个时生效）
       --no-proxy                  禁止使用代理
  -Q,  --quota=数字                设置获取配额为 <数字> 字节
       --bind-address=ADDRESS      绑定至本地主机上的 ADDRESS (主机名或是 IP)
       --limit-rate=RATE           限制下载速率为 RATE
       --no-dns-cache              关闭 DNS 查询缓存
       --restrict-file-names=系统  限定文件名中的字符为 <系统> 允许的字符
       --ignore-case               匹配文件/目录时忽略大小写
  -4,  --inet4-only                仅连接至 IPv4 地址
  -6,  --inet6-only                仅连接至 IPv6 地址
       --prefer-family=地址族      首先连接至指定家族（IPv6，IPv4 或 none）的地址
       --user=用户                 将 ftp 和 http 的用户名均设置为 <用户>
       --password=密码             将 ftp 和 http 的密码均设置为 <密码>
       --ask-password              提示输入密码
       --use-askpass=命令          指定用于请求用户名和密码的凭据管理器。
                                     如果没有提供指定命令，程序将使用 WGET_ASKPASS 或
                                     SSH_ASKPASS 环境变量。
       --no-iri                    关闭 IRI 支持
       --local-encoding=ENC        使用 ENC 作为 IRI (国际化资源标识符) 的本地编码
       --remote-encoding=ENC       使用 ENC 作为默认远程编码
       --unlink                    覆盖前移除文件
       --xattr                     在文件的拓展属性中储存元数据

目录：
  -nd, --no-directories            不创建目录
  -x,  --force-directories         强制创建目录
  -nH, --no-host-directories       不要创建主 (host) 目录
       --protocol-directories      在目录中使用协议名称
  -P,  --directory-prefix=前缀     保存文件到 <前缀>/..
       --cut-dirs=数字             忽略远程目录中 <数字> 个目录层。

HTTP 选项：
       --http-user=用户            设置 http 用户名为 <用户>
       --http-password=密码        设置 http 密码为 <密码>
       --no-cache                  不使用服务器缓存的数据。
       --default-page=NAME         改变默认页 (通常是“index.html”)。
  -E,  --adjust-extension          以合适的扩展名保存 HTML/CSS 文档
       --ignore-length             忽略头部的‘Content-Length’区域
       --header=字符串             在头部插入 <字符串>
       --compression=类型          选择压缩类型，值可以为 auto、gzip 和 none。（默认：none）
       --max-redirect              每页所允许的最大重定向
       --proxy-user=用户           使用 <用户> 作为代理用户名
       --proxy-password=密码       使用 <密码> 作为代理密码
       --referer=URL               在 HTTP 请求头包含‘Referer: URL’
       --save-headers              将 HTTP 头保存至文件。
  -U,  --user-agent=代理           标识自己为 <代理> 而不是 Wget/VERSION。
       --no-http-keep-alive        禁用 HTTP keep-alive (持久连接)。
       --no-cookies                不使用 cookies。
       --load-cookies=文件         会话开始前从 <文件> 中载入 cookies。
       --save-cookies=文件         会话结束后保存 cookies 至 FILE。
       --keep-session-cookies      载入并保存会话 (非永久) cookies。
       --post-data=字符串          使用 POST 方式；把 <字串>作为数据发送。
       --post-file=文件            使用 POST 方式；发送 <文件> 内容。
       --method=HTTP方法           在请求中使用指定的 <HTTP 方法>。
       --post-data=字符串          把 <字串> 作为数据发送，必须设置 --method
       --post-file=文件            发送 <文件> 内容，必须设置 --method
       --content-disposition       当选择本地文件名时允许 Content-Disposition
                                   头部 (实验中)。
       --content-on-error          在服务器错误时输出接收到的内容
       --auth-no-challenge         不先等待服务器询问就发送基本 HTTP 验证信息。

HTTPS (SSL/TLS) 选项：
       --secure-protocol=PR         选择安全协议，值可以是 auto、SSLv2、
                                    SSLv3、TLSv1、TLSv1_1、TLSv1_2 或 PFS
       --https-only                 只跟随安全的 HTTPS 链接
       --no-check-certificate       不要验证服务器的证书。
       --certificate=文件           客户端证书文件。
       --certificate-type=类型      客户端证书类型，PEM 或 DER。
       --private-key=文件           私钥文件。
       --private-key-type=类型      私钥文件类型，PEM 或 DER。
       --ca-certificate=文件        带有一组 CA 证书的文件。
       --ca-directory=DIR           保存 CA 证书的哈希列表的目录。
       --ca-certificate=文件        带有一组 CA 证书的文件。
       --pinnedpubkey=文件/散列值  用于验证节点的公钥（PEM/DER）文件或
                                   任何数量的 sha256 散列值，以 base64 编码、
                                   “sha256//” 开头、用“;”间隔
       --random-file=文件           用于初始化 SSL 伪随机数生成器（PRNG）的文件，
                                      应含有随机数据

       --ciphers=STR           直接设置 priority string (GnuTLS) 或 cipher list string (OpenSSL)。
                                   请小心使用。该选项会覆盖 --secure-protocol。
                                   其具体格式和字符串语法取决于对应的 SSL/TLS 引擎。
HSTS 选项：
       --no-hsts                   禁用 HSTS
       --hsts-file                 HSTS 数据库路径（将覆盖默认值）

FTP 选项：
       --ftp-user=用户             设置 ftp 用户名为 <用户>。
       --ftp-password=密码         设置 ftp 密码为 <密码>
       --no-remove-listing         不要删除‘.listing’文件
       --no-glob                   不在 FTP 文件名中使用通配符展开
       --no-passive-ftp            禁用“passive”传输模式
       --preserve-permissions      保留远程文件的权限
       --retr-symlinks             递归目录时，获取链接的文件 (而非目录)

FTPS 选项：
       --ftps-implicit                 使用隐式 FTPS（默认端口 990）
       --ftps-resume-ssl               打开数据连接时继续控制连接中的 SSL/TLS 会话
       --ftps-clear-data-connection    只加密控制信道；数据传输使用明文
       --ftps-fallback-to-ftp          回落到 FTP，如果目标服务器不支持 FTPS
WARC 选项：
       --warc-file=文件名          在一个 .warc.gz 文件里保持请求/响应数据
       --warc-header=字符串        在头部插入 <字符串>
       --warc-max-size=数字        将 WARC 的最大尺寸设置为 <数字>
       --warc-cdx                  写入 CDX 索引文件
       --warc-dedup=文件名         不要记录列在此 CDX 文件内的记录
       --no-warc-compression       不要 GZIP 压缩 WARC 文件
       --no-warc-digests           不要计算 SHA1 摘要
       --no-warc-keep-log          不要在 WARC 记录中存储日志文件
       --warc-tempdir=目录         WARC 写入器的临时文件目录

递归下载：
  -r,  --recursive                 指定递归下载
  -l,  --level=数字                最大递归深度 (inf 或 0 代表无限制，即全部下载)。
       --delete-after              下载完成后删除本地文件
  -k,  --convert-links             让下载得到的 HTML 或 CSS 中的链接指向本地文件
       --convert-file-only         只转换 URL 的文件部分（一般叫做“基础名”/basename）
       --backups=N                 写入文件 X 前，轮换移动最多 N 个备份文件
  -K,  --backup-converted          在转换文件 X 前先将它备份为 X.orig。
  -m,  --mirror                    -N -r -l inf --no-remove-listing 的缩写形式。
  -p,  --page-requisites           下载所有用于显示 HTML 页面的图片之类的元素。
       --strict-comments           用严格方式 (SGML) 处理 HTML 注释。

递归接受/拒绝：
  -A,  --accept=列表               逗号分隔的可接受的扩展名列表
  -R,  --reject=列表               逗号分隔的要拒绝的扩展名列表
       --accept-regex=REGEX        匹配接受的 URL 的正则表达式
       --reject-regex=REGEX        匹配拒绝的 URL 的正则表达式
       --regex-type=类型           正则类型 (posix|pcre)
  -D,  --domains=列表              逗号分隔的可接受的域名列表
       --exclude-domains=列表      逗号分隔的要拒绝的域名列表
       --follow-ftp                跟踪 HTML 文档中的 FTP 链接
       --follow-tags=列表          逗号分隔的跟踪的 HTML 标识列表
       --ignore-tags=列表          逗号分隔的忽略的 HTML 标识列表
  -H,  --span-hosts                递归时转向外部主机
  -L,  --relative                  仅跟踪相对链接
  -I,  --include-directories=列表  允许目录的列表
       --trust-server-names        使用重定向 URL 的最后一段作为本地文件名
  -X,  --exclude-directories=列表  排除目录的列表
  -np, --no-parent                 不追溯至父目录

请将问题报告和讨论内容电子邮件发送至 <bug-wget@gnu.org>
和/或在 https://savannah.gnu.org/bugs/?func=additem&group=wget 开 issue 进行讨论。
```
