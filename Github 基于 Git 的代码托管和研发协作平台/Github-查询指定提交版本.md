# Github 查询指定提交版本

但我们在 Github 查看某一次提交日志时。
你会看到类似的 <https://github.com/rust-lang/rust/commit/69ffc0d3a3c619009bcb27b8f61d810e27b12612> URL。
而这里的 `69ffc0` 就是你每次提交的日志 ID。

下载指定版本

1. 修改 url 路径后的 commit
2. 点击右上角的 <kbd>Browse files</kbd>，这里你就可以看到原 commit 的所有文件
3. 如果你想下此版本的代码，使用 <kbd>Download ZIP</kbd>，默认 `git clone` 是 default 版

> [!NOTE] 注意
> 如果你想知道官方更新了那些东西，可以把当前的版本创建一个 git，把最新版，扔进去，就可以查看到不同点了。
