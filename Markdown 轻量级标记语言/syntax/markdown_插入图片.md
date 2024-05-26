# Markdown 图片

图片的语法和链接很像。
alt text 是替换文本；alt 属性规定在图像无法显示时的替代文本。虽然 alt text 可以不填，但推荐还是填上。
Title 是可选项。

## 行内形式

```markdown
![alt text](/path/to/img.jpg "Title")
```

## 参考形式

```markdown
![alt text][id]

[id]: /path/to/img.jpg "Title"
```

## 插入互联网上图片

(右键 copy 图片地址)

```php
我在我的笔记本上装了 openSUSE Leap: 
![opensuse-laptop](https://www.opensuse.org/build/images/opensuse-laptop.gif)
```

## 控制图片的大小 对齐方式

MarkDown 中显示图片的语法是 `![图片描述](图片地址)` 。但是有时候我们需要更改图片大小和位置。

**通过 img 标签控制宽高**

```markdown
<img src="http://pic15.photophoto.cn/20100615/0006019058815826_b.jpg"  height="330" width="495">
```

## 图片剧中

```markdown
<div align="center">
  <picture>
    <img alt="systemd 的架构"
         src="./img/learning-to-love-systemd/085112xl9ukqlulkugszo5.png"
         width="80%">
  </picture>

  图 1：systemd 的架构，作者 Shmuel Csaba Otto Traian (CC BY-SA 3.0)
</div>
```

## 其他属性

根据 MarkDown 编辑器解析语法的方式不同，有些编辑器还支持别的属性

比如在 img 标签中增加 `style="margin-left:45px"` ，在 atom 中配合 GitHub MarkDown 有效。

<font color=red>甚至相同的 html 属性在不同的编辑器内展示效果也不一样。如果想传递一个 md 文件给别人的话， 尽量不要加别的 html 属性进去，最好是测试满意后转换为 pdf 格式发给对方。</font>

## See also

* [Markdown 中控制图片的大小 对齐方式](https://blog.csdn.net/sunsteam/article/details/73112787)
* [Markdown-图片设置（大小，居中）](https://blog.csdn.net/qq_35451572/article/details/79443467)
