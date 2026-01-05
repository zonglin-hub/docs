
<strong>如果在运行 cargo 的时候：Blocking waiting for file lock on package cache</strong>

请产生 `.cargo` 文件夹下面的 `.package_cache` 文件：

```sh
rm ~/.cargo/.package-cache
```

<strong>原因：</strong>

<pre>
cargo 堵塞问题。
</pre>
