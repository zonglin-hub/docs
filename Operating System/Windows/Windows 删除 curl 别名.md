# Windows 删除 curl 别名

<pre>
D:/github/deno_core_live> cargo check
warning: version requirement `0.12.3+zstd.1.5.2` for dependency `zstd` includes semver metadata which will be ignored, removing the metadata is recommended to avoid confusion
warning: version requirement `0.12.3+zstd.1.5.2` for dependency `zstd` includes semver metadata which will be ignored, removing the metadata is recommended to avoid confusion
   Compiling v8 v0.73.0
error: failed to run custom build command for `v8 v0.73.0`

Caused by:
  process didn't exit successfully: `D:\github\deno_core_live\target\debug\build\v8-0f1318cc13004c3a\build-script-build` (exit code: 101)
  --- stdout
  cargo:rerun-if-changed=.gn
  cargo:rerun-if-changed=BUILD.gn
  cargo:rerun-if-changed=src/binding.cc
  cargo:rerun-if-env-changed=CCACHE
  cargo:rerun-if-env-changed=CLANG_BASE_PATH
  cargo:rerun-if-env-changed=DENO_TRYBUILD
  cargo:rerun-if-env-changed=DOCS_RS
  cargo:rerun-if-env-changed=GN
  cargo:rerun-if-env-changed=GN_ARGS
  cargo:rerun-if-env-changed=HOST
  cargo:rerun-if-env-changed=NINJA
  cargo:rerun-if-env-changed=OUT_DIR
  cargo:rerun-if-env-changed=RUSTY_V8_ARCHIVE
  cargo:rerun-if-env-changed=RUSTY_V8_MIRROR
  cargo:rerun-if-env-changed=SCCACHE
  cargo:rerun-if-env-changed=V8_FORCE_DEBUG
  cargo:rerun-if-env-changed=V8_FROM_SOURCE
  cargo:rerun-if-env-changed=PYTHON
  cargo:rerun-if-env-changed=DISABLE_CLANG
  cargo:rerun-if-env-changed=EXTRA_GN_ARGS
  cargo:rerun-if-env-changed=NO_PRINT_GN_ARGS
  cargo:rustc-link-lib=static=rusty_v8
  cargo:rustc-link-lib=dylib=winmm
  cargo:rustc-link-lib=dylib=dbghelp
  cargo:rustc-link-lib=dylib=msvcprt
  download lockfile: "D:\\github\\deno_core_live\\target\\debug\\build\\lib_download.fslock"
  static lib URL: https://github.com/denoland/rusty_v8/releases/download/v0.73.0/rusty_v8_release_x86_64-pc-windows-msvc.lib
  cargo:rustc-link-search=D:\github\deno_core_live\target\debug\gn_out\obj
  Downloading https://github.com/denoland/rusty_v8/releases/download/v0.73.0/rusty_v8_release_x86_64-pc-windows-msvc.lib
  Python downloader failed, trying with curl.

  --- stderr
  thread 'main' panicked at 'assertion failed: status.success()', C:\Users\liuzonglin\.cargo\registry\src\mirrors.ustc.edu.cn-61ef6e0cd06fb9b8\v8-0.73.0\build.rs:431:3
  note: run with `RUST_BACKTRACE=1` environment variable to display a backtrace
D:/github/deno_core_live>  
```

如果您不确定 curl 是否已经安装，可以尝试使用以下命令来检查 PowerShell 中可用的命令：

```powershell
PS D:\.github\docs> Get-Command curl

CommandType     Name                                               Version    Source
-----------     ----                                               -------    ------
Alias           curl -> Invoke-WebRequest
</pre>

如果没有任何输出，请尝试安装 curl 组件并重新运行上述命令。如果您已经安装了 curl 组件并且仍然无法找到它，请检查 `$env:Path` 是否正确设置，并尝试将 curl 组件所在的路径添加到 `$env:Path` 环境变量中。

在 Windows PowerShell 环境中，`curl` 别名是通过 PowerShell 的函数 `Invoke-WebRequest` 实现的。因此，要卸载 `curl` 别名，您可以使用以下命令：

```powershell
Remove-Item -Path Alias:curl
```

执行此命令后，`curl` 别名将被删除。

注意：如果您重新打开了一个 PowerShell 窗口或会话，`curl` 别名可能会重新出现。这是因为别名只在当前会话中有效，并且不会保存在您的计算机上。

如果您希望在每个 PowerShell 会话中都删除 `curl` 别名，您可以将上面的命令添加到 PowerShell 配置文件中。在 PowerShell 中，您可以使用 `profile` 文件来自定义您的 PowerShell 环境。要打开此文件，请在 PowerShell 窗口中执行以下命令：

```powershell
notepad $PROFILE
```

如果在执行`notepad $profile`命令时，提示无法找到文件，则表示 `$profile` 文件不存在。在这种情况下，您可以手动创建该文件。请按照以下步骤操作：

- 打开 PowerShell 控制台。
- 运行以下命令：

  ```powershell
  PS C:\Users\liuzonglin\Desktop> New-Item -ItemType File -Path $PROFILE -Force


      目录: C:\Users\liuzonglin\Documents\WindowsPowerShell


  Mode                 LastWriteTime         Length Name
  ----                 -------------         ------ ----
  -a----         2023/8/13     21:49              0 Microsoft.PowerShell_profile.ps1
  ```

- 使用文本编辑器（例如 Notepad）打开该文件：

  ```powershell
  notepad $PROFILE
  ```

- 在打开的文本编辑器中添加以下行：

  ```powershell
  Remove-Item -Path Alias:curl -ErrorAction SilentlyContinue
  ```

- 保存并关闭文件。

  现在，每次启动 PowerShell 时，该命令都会运行，并删除 `curl` 别名。请注意，如果您使用了其他 PowerShell 包或模块，它们可能会覆盖此文件，因此您可能需要在这些文件中添加相应的命令。
