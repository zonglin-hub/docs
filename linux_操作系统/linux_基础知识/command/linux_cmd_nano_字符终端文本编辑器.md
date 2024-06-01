# Linux nano 字符终端文本编辑器

**nano** 是一个字符终端的文本编辑器，有点像 DOS 下的 editor 程序。
它比 vi/vim 要简单得多，比较适合 Linux 初学者使用。
某些 Linux 发行版的默认编辑器就是 nano 。

nano 命令可以打开指定文件进行编辑，默认情况下它会自动断行，即在一行中输入过长的内容时
自动拆分成几行，但用这种方式来处理某些文件可能会带来问题，比如 Linux 系统的
配置文件，自动断行就会使本来只能写在一行上的内容折断成多行了，有可能造成系统不灵了。
因此，如果你想避免这种情况出现，就加上 `-w` 选项吧。

##  语法

<pre>
❯ nano -h
用法: nano [选项] [[+行[,列]] 文件名]...

如需启动时将游标置放在文件的特定行上，请在文件名前使用“+”符号加上行号以
进行指定。如需同时指定特定列，可以在其后添加半角逗号和列号。
当文件名为“-”时，nano 从标准输入读取数据。

 选项           长选项                  意义
 -A             --smarthome             启用智能 HOME 键
 -B             --backup                储存既有文件的备份
 -C <目录>      --backupdir=<目录>      用以储存独一备份文件的目录
 -D             --boldtext              用粗体替代颜色反转
 -E             --tabstospaces          将已输入的制表符转换为空白
 -F             --multibuffer           默认从文件读入到一个新的缓冲区
 -G             --locking               使用（vim 风格）锁文件
 -H             --historylog            保存并重新加载搜索/替换字符串
 -I             --ignorercfiles         不要参考 nanorc 文件
 -J <数字>      --guidestripe=<数字>    在此栏显示一个导引条
 -K             --rawsequences          修正数字键区按键混淆问题
 -L             --nonewlines            不要自动添加换行符
 -M             --trimblanks            强制折行时移除末尾空白
 -N             --noconvert             不要从 DOS/Mac 格式转换
 -O             --bookstyle             以空白字符起始表示新的段落
 -P             --positionlog           保存和恢复光标位置
 -Q <正则表达式> --quotestr=<正则表达式> 匹配引用的正则表达式
 -R             --restricted            限制对文件系统的访问
 -S             --softwrap              以多行显示过长的行
 -T <数字>      --tabsize=<数字>        令制表符宽度为指定行数
 -U             --quickblank            在下一次按键后清除状态栏内容
 -V             --version               显示版本信息并离开
 -W             --wordbounds            更正确地侦测单字边界
 -X <字符串>    --wordchars=<字符串>    指定哪些其它特殊字符也是单词的一部分
 -Y <名称>      --syntax=<名称>         用于加亮的语法定义
 -Z             --zap                   让退格键和删除键清除选中的区域
 -a             --atblanks              软折行时在空白处进行
 -b             --breaklonglines        对过长的行自动强制换行
 -c             --constantshow          持续显示游标位置
 -d             --rebinddelete          修正退格键/删除键混淆问题
 -e             --emptyline             保持标题栏下面的行一直为空
 -f <文件>      --rcfile=<文件>         只使用这个文件配置 nano
 -g             --showcursor            在文件浏览器和帮助文本中显示游标
 -h             --help                  显示本帮助文本并退出
 -i             --autoindent            自动缩进新行
 -j             --jumpyscrolling        按半屏幕滚动文本，不按行
 -k             --cutfromcursor         从游标剪切至行尾
 -l             --linenumbers           在文本之前显示行号
 -m             --mouse                 启用鼠标功能
 -n             --noread                不要读取文件（仅写入）
 -o <目录>      --operatingdir=<目录>   设定操作目录
 -p             --preserve              保留XON (^Q) 和XOFF (^S) 按键
 -q             --indicator             显示位置+部分指示器
 -r <数字>      --fill=<数字>           设置强制换行宽度并进行重排
 -s <程序>      --speller=<程序>        使用此替代的拼写检查程序
 -t             --saveonexit            退出时自动保存修改，不要提示
 -u             --unix                  默认将文件保存为 Unix 格式
 -v             --view                  查看（只读）模式
 -w             --nowrap                不要为过长行强制折行 [默认]
 -x             --nohelp                不要显示辅助区
 -y             --afterends             使 Ctrl+Right 在单词末尾处停止
 -%             --stateflags            在标题栏显示某些状态
 -_             --minibar               在底部显示一个反馈条
 -0             --zero                  隐藏所有横栏，使用整个终端
</pre>

###  用法

**光标控制** 

* 移动光标：使用用方向键移动。
* 选择文字：按住鼠标左键拖到。

**复制、剪贴和粘贴** 

* 复制一整行：<kbd>Alt + 6</kbd>
* 剪贴一整行：<kbd>Ctrl + K</kbd>

**粘贴：<kbd>Ctrl+U</kbd>** 

如果需要复制／剪贴多行或者一行中的一部分，先将光标移动到需要复制／剪贴的文本的
开头，按 <kbd>Ctrl + 6</kbd>（或者<kbd>Alt + A</kbd>）做标记，然后移动光标到 待复制／剪贴的文本末尾。
这时选定的文本会反白，用<kbd>Alt + 6</kbd>来复制，<kbd>Ctrl + K</kbd>来剪贴。
若在选择文本过程中要取消，只需要再按一次 <kbd>Ctrl + 6</kbd>。

**搜索** 

按 <kbd>Ctrl + W</kbd> ，然后输入你要搜索的关键字，回车确定。
这将会定位到第一个匹配的文本，接着可以用 <kbd>Alt + W</kbd> 来定位到下一个匹配的文本。

**翻页** 

* `Ctrl+Y` 到上一页
* `Ctrl+V` 到下一页

**保存** 

使用<kbd>Ctrl + O</kbd> 来保存所做的修改

**退出** 

按 <kbd>Ctrl + X</kbd>

如果你修改了文件，下面会询问你是否需要保存修改。
输入 <kbd>Y</kbd> 确认保存，输入 <kbd>N</kbd> 不保存，按 <kbd>Ctrl + C</kbd> 取消返回。
如果输入了 <kbd>Y</kbd>，下一步会让你输入想要保存的文件名。
如果不需要修改文件名直接回车就行；若想要保存成别的名字（也就是另存为）则输入新名称然后确 定。
这个时候也可用 <kbd>Ctrl + C</kbd> 来取消返回。
