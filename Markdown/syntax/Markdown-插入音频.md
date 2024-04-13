# Markdown 音频

音频文件可以用 HTML 的方式嵌入到 Markdown 中。

## 使用 HTML5 `<audio>` 元素

下面是一个将音频嵌入到 HTML 文档的例子。

```html
<audio src="/test/audio.ogg">
  你的浏览器不支持 audio 标签。
</audio>
```

src 属性可以设置为一个音频文件的 URL 或者本地文件的路径。

```html
<audio src="audio.mp3" preload="none" controls loop>
  你的浏览器不支持 audio 标签。
</audio>
```

这个例子的代码中使用了 HTML 的“audio”元素的一些属性：

* ''controls'' : 为网页中的音频显示标准的 HTML5 控制器。
* ''loop'' : 使音频自动重复播放。
* ''preload'' : 属性用来缓冲 audio 元素的大文件，有三个属性值可供设置：
    * "none" 不缓冲文件
    * "auto" 缓冲音频文件
    * "metadata" 仅仅缓冲文件的元数据

> 参阅 [HTML5 `<audio>` 元素](https://developer.mozilla.org/zh-CN/docs/Web/HTML/Element/audio)，和 [使用 HTML5 音频和视频](https://developer.mozilla.org/zh-CN/docs/Web/Guide/HTML/Using_HTML5_audio_and_video)。

### 在线音乐

进入 [**网易云音乐**](https://music.163.com/#) 歌曲界面，点击光碟下方的生成外链播放器：

```html
<iframe
    frameborder="no"
    border="0"
    marginwidth="0"
    marginheight="0"
    width=330
    height=86
    src="//music.163.com/outchain/player?type=2&id=393697&auto=1&height=66">
</iframe>
```

<iframe
    frameborder="no"
    border="0"
    marginwidth="0"
    marginheight="0"
    width=330
    height=86
    src="//music.163.com/outchain/player?type=2&id=393697&auto=1&height=66">
</iframe>
