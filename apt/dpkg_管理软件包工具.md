# Linux dpkg 命令 管理软件包工具

**dpkg 命令** 是 Debian/Ubuntu Linux 系统上安装、创建和管理软件包

目录

- [Linux dpkg 命令 管理软件包工具](#linux-dpkg-命令-管理软件包工具)
    - [常用选项](#常用选项)
    - [示例](#示例)
    - [dpkg 帮助文档](#dpkg-帮助文档)

## 常用选项

```shell
-i：安装软件包；
-r：删除软件包；
-P：删除软件包的同时删除其配置文件；
-L：显示于软件包关联的文件；
-l：显示已安装软件包列表；
--unpack：解开软件包；
-c：显示软件包内文件列表；
--confiugre：配置软件包。
```

## 示例

```shell
dpkg -i package.deb     # 安装包
dpkg -r package         # 删除包
dpkg -P package         # 删除包（包括配置文件）
dpkg -L package         # 列出与该包关联的文件
dpkg -l package         # 显示该包的版本
dpkg --unpack package.deb  # 解开deb包的内容
dpkg -S keyword            # 搜索所属的包内容
dpkg -l                    # 列出当前已安装的包
dpkg -c package.deb        # 列出deb包的内容
dpkg --configure package   # 配置包
```

## dpkg 帮助文档

```text
❯ dpkg --help
用法：dpkg [<选项>...] <命令>

命令：
  -i|--install       <.deb 文件名> ... | -R|--recursive <目录> ...
  --unpack           <.deb 文件名> ... | -R|--recursive <目录> ...
  -A|--record-avail  <.deb 文件名> ... | -R|--recursive <目录> ...
  --configure        <软件包名>    ... | -a|--pending
  --triggers-only    <软件包名>    ... | -a|--pending
  -r|--remove        <软件包名>    ... | -a|--pending
  -P|--purge         <软件包名>    ... | -a|--pending
  -V|--verify <软件包名> ...       检查包的完整性。
  --get-selections [<表达式> ...]  把已选中的软件包列表打印到标准输出。
  --set-selections                 从标准输入里读出要选择的软件。
  --clear-selections               取消选中所有非必需的软件包。
  --update-avail <软件包文件>      替换现有可安装的软件包信息。
  --merge-avail  <软件包文件>      把文件中的信息合并到系统中。
  --clear-avail                    清除现有的软件包信息。
  --forget-old-unavail             忘却已被卸载的不可安装的软件包。
  -s|--status      <软件包名> ...  显示指定软件包的详细状态。
  -p|--print-avail <软件包名> ...  显示可供安装的软件版本。
  -L|--listfiles   <软件包名> ...  列出属于指定软件包的文件。
  -l|--list  [<表达式> ...]        简明地列出软件包的状态。
  -S|--search <表达式> ...         搜索含有指定文件的软件包。
  -C|--audit [<表达式> ...]        检查是否有软件包残损。
  --yet-to-unpack                  列出标记为待解压的软件包。
  --predep-package                 列出待解压的预依赖。
  --add-architecture    <体系结构> 添加 <体系结构> 到体系结构列表。
  --remove-architecture <体系结构> 从体系结构列表中移除 <体系结构>。
  --print-architecture             显示 dpkg 体系结构。
  --print-foreign-architectures    显示已启用的异质体系结构。
  --assert-<特性>                  对指定特性启用断言支持。
  --validate-<属性> <字符串>       验证一个 <属性>的 <字符串>。
  --compare-versions <a> <关系> <b> 比较版本号 - 见下。
  --force-help                     显示本强制选项的帮助信息。
  -Dh|--debug=help                 显示有关出错调试的帮助信息。

  -?, --help                       显示本帮助信息。
      --version                    显示版本信息。

可验证的属性：pkgname, archname, trigname, version.

调用 dpkg 并带参数 -b, --build, -c, --contents, -e, --control, -I, --info,
  -f, --field, -x, --extract, -X, --vextract, --ctrl-tarfile, --fsys-tarfile
是针对归档文件的。 (输入 dpkg-deb --help 获取帮助)

Options:
  --admindir=<directory>     Use <directory> instead of /var/lib/dpkg.
  --root=<directory>         Install on a different root directory.
  --instdir=<directory>      Change installation dir without changing admin dir.
  --pre-invoke=<command>     Set a pre-invoke hook.
  --post-invoke=<command>    Set a post-invoke hook.
  --path-exclude=<pattern>   Do not install paths which match a shell pattern.
  --path-include=<pattern>   Re-include a pattern after a previous exclusion.
  -O|--selected-only         Skip packages not selected for install/upgrade.
  -E|--skip-same-version     Skip packages whose same version is installed.
  -G|--refuse-downgrade      Skip packages with earlier version than installed.
  -B|--auto-deconfigure      Install even if it would break some other package.
  --[no-]triggers            Skip or force consequential trigger processing.
  --verify-format=<format>   Verify output format (supported: 'rpm').
  --no-pager                 Disables the use of any pager.
  --no-debsig                Do not try to verify package signatures.
  --no-act|--dry-run|--simulate
                             Just say what we would do - don't do it.
  -D|--debug=<octal>         Enable debugging (see -Dhelp or --debug=help).
  --status-fd <n>            Send status change updates to file descriptor <n>.
  --status-logger=<command>  Send status change updates to <command>'s stdin.
  --log=<filename>           Log status changes and actions to <filename>.
  --ignore-depends=<package>[,...]
                             Ignore dependencies involving <package>.
  --force-<thing>[,...]      Override problems (see --force-help).
  --no-force-<thing>[,...]   Stop when problems encountered.
  --refuse-<thing>[,...]     Ditto.
  --abort-after <n>          Abort after encountering <n> errors.
  --robot                    Use machine-readable output on some commands.

可供--compare-versions 使用的比较运算符有：
 lt le eq ne ge gt        (如果版本号为空，那么就认为它先于任意版本号)；
 lt-nl le-nl ge-nl gt-nl  (如果版本号为空，那么就认为它后于任意版本号)；
 < << <= = >= >> >        (仅仅是为了与主控文件的语法兼容)。

'apt' 和 'aptitude' 提供了更为便利的软件包管理。
```

