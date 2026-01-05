
__问题描述：__

```text
vscode 加载 web 视图，报错:“Error: Could not register serviceworkers: InvalidstateError: Failed to regist“
```

__解决办法：__

1. 关闭 vscode

2. 按WIN + R，输入cmd，打开终端，然后输入命令

    ```shell
    code --no-sandbox
    ```

3. 会重启 vscode，就可以正常使用了。
