# Markdown 视频

视频文件可以用 HTML 的方式嵌入到 Markdown 中。

## HTML5 `<video>` 元素

原生仅支持播放 `ogg/mp4/webm` 格式。不支持播放 FLV 格式视频。

| 类型       | 文件扩展名    |
| ---------- | ------------- |
| video/ogg  | ogg /ogv /ogm |
| video/webm | webm          |
| video/mp4  | mp4           |

在 HTML 中嵌入视频：

```markdown
<video src="http://v2v.cc/~j/theora_testsuite/320x240.ogg" controls>
  你的浏览器不支持 <code>video</code> 标签。
</video>
```

<video src="http://v2v.cc/~j/theora_testsuite/320x240.ogg" controls>
  你的浏览器不支持 <code>video</code> 标签。
</video>

这个例子展示了一个带有回放控制器的可播放视频，视频来源于 Theora 网站。

然而，浏览器并不是都支持相同的[视频格式](https://developer.mozilla.org/zh-CN/docs/Web/HTML/Supported_media_formats)，所以你可以在 `<source>` 元素里提供多个视频源，然后浏览器将会使用它所支持的第一个源。

```markdown
<video width="320" controls loop>
  <source src="myVideo.mp4" type="video/mp4">
  <source src="myVideo.webm" type="video/webm">
  <source src="myVideo.ogv" type="video/ogg" />
  <p>Your browser doesn't support HTML5 video. Here is
     a <a href="myVideo.mp4">link to the video</a> instead.</p>
</video>
```

属性：

* ''controls'' : 允许用户控制视频的播放，包括音量，跨帧，暂停/恢复播放。
* ''width'' : 视频显示区域的宽度，单位是CSS像素。
* ''height'' : 展示区域的高度，单位是CSS像素。
* ''loop'' : 布尔属性；指定后，会在视频结尾的地方，自动返回视频开始的地方。
* ''src'' : 要嵌到页面的视频的URL。可选；你也可以使用video块内的 `<source>` 元素来指定需要嵌到页面的视频。

> 参阅 [HTML5 `<video>` 元素](https://developer.mozilla.org/zh-CN/docs/Web/HTML/Element/video)， 和 [使用 HTML5 音频和视频](https://developer.mozilla.org/zh-CN/docs/Web/Guide/HTML/Using_HTML5_audio_and_video)。

## HTML 内联框架元素 `<iframe>`

`<iframe>` 能够将另一个 HTML 页面嵌入到当前页面中。

嵌入网络视频：

```markdown
<iframe 
    src="https://v.miaopai.com/iframe?scid=SvyHaHOczsp7B6ftW86oqMMz62-h5ai6~Fwp8A__"
    width="800" 
    height="450" 
    frameborder="0" 
    allowfullscreen>
</iframe>
```

<iframe
    src="https://v.miaopai.com/iframe?scid=SvyHaHOczsp7B6ftW86oqMMz62-h5ai6~Fwp8A__"
    width="800"
    height="450"
    frameborder="0"
    allowfullscreen>
</iframe>
