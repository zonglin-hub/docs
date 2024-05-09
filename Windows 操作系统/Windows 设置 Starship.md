# Windows 设置 Starship

参考文档：

- [Starship 集成 Windows Terminal 使用指南](https://zhuanlan.zhihu.com/p/467581369)

配置 PowerShell 使用 Starship

```powershell
if (!(Test-Path -Path $PROFILE)) { New-Item -ItemType File -Path $PROFILE -Force }
```

注：命令作用是判断下不存在$PROFILE文件，就创建。
默认位置：`C:\Users\admin\Documents\WindowsPowerShell`
