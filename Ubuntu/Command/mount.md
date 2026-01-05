---
标题:
创建时间:
修改时间:
标签:
---
```bash
sudo mount --bind ~/Downloads ~/.local/share/waydroid/data/media/0/Download 
```

要解除通过 `sudo mount --bind` 设置的绑定挂载，可以使用以下命令：

## 方法1：使用 umount 命令
```bash
sudo umount ~/.local/share/waydroid/data/media/0/Download
```

## 方法2：如果方法1失败，使用懒卸载
```bash
sudo umount -l ~/.local/share/waydroid/data/media/0/Download
```
`-l` 参数表示懒卸载，会在目录不再使用时立即卸载。

## 方法3：强制卸载（不推荐，仅在必要时使用）
```bash
sudo umount -f ~/.local/share/waydroid/data/media/0/Download
```

## 验证卸载是否成功
```bash
# 检查挂载点是否已卸载
mount | grep waydroid

# 或者查看具体目录
ls -la ~/.local/share/waydroid/data/media/0/Download
```

## 永久解除
如果这个绑定挂载是在系统启动时自动设置的，你还需要检查并清理相关的启动脚本，比如：
- `/etc/fstab`
- `/etc/rc.local`
- systemd 服务文件
- 用户的 crontab
- 其他启动脚本

## 注意事项
- 确保没有进程正在使用该目录，否则卸载可能会失败
- 使用 `lsof +D ~/.local/share/waydroid/data/media/0/Download` 可以查看是否有进程占用

推荐先尝试方法1，如果失败再使用方法2。

```txt
> mount --help

用法：
 mount [-lhV]
 mount -a [选项]
 mount [选项] [--source] <源> | [--target] <目录>
 mount [选项] <源> <目录>
 mount <操作> <挂载点> [<目标>]

挂载文件系统。

选项：
 -a, --all               mount all filesystems mentioned in fstab
 -c, --no-canonicalize   不对路径规范化
 -f, --fake              dry run; skip the mount(2) syscall
 -F, --fork              fork off for each device (use with -a)
 -T, --fstab <path>      alternative file to /etc/fstab
 -i, --internal-only     不调用 mount.<type> 辅助程序
 -l, --show-labels       也显示文件系统标签
 -m, --mkdir[=<mode>]    alias to '-o X-mount.mkdir[=<mode>]'
 -n, --no-mtab           不写 /etc/mtab
     --options-mode <mode>
                         what to do with options loaded from fstab
     --options-source <source>
                         mount options source
     --options-source-force
                         force use of options from fstab/mtab
     --onlyonce          check if filesystem is already mounted
 -o, --options <list>    comma-separated list of mount options
 -O, --test-opts <列表>  限制文件系统集合(和 -a 选项一起使用)
 -r, --read-only         mount the filesystem read-only (same as -o ro)
 -t, --types <列表>      限制文件系统集合
     --source <src>      explicitly specifies source (path, label, uuid)
     --target <target>   explicitly specifies mountpoint
     --target-prefix <path>
                         specifies path used for all mountpoints
 -v, --verbose           打印当前进行的操作
 -w, --rw, --read-write  以读写方式挂载文件系统(默认)
 -N, --namespace <ns>    perform mount in another namespace

 -h, --help              display this help
 -V, --version           display version

Source:
 -L, --label <label>     synonym for LABEL=<label>
 -U, --uuid <uuid>       synonym for UUID=<uuid>
 LABEL=<label>           specifies device by filesystem label
 UUID=<uuid>             specifies device by filesystem UUID
 PARTLABEL=<label>       specifies device by partition label
 PARTUUID=<uuid>         specifies device by partition UUID
 ID=<id>                 specifies device by udev hardware ID
 <device>                specifies device by path
 <directory>             mountpoint for bind mounts (see --bind/rbind)
 <file>                  regular file for loopdev setup

Operations:
 -B, --bind              mount a subtree somewhere else (same as -o bind)
 -M, --move              move a subtree to some other place
 -R, --rbind             mount a subtree and all submounts somewhere else
 --make-shared           mark a subtree as shared
 --make-slave            mark a subtree as slave
 --make-private          mark a subtree as private
 --make-unbindable       mark a subtree as unbindable
 --make-rshared          recursively mark a whole subtree as shared
 --make-rslave           recursively mark a whole subtree as slave
 --make-rprivate         recursively mark a whole subtree as private
 --make-runbindable      recursively mark a whole subtree as unbindable

更多信息请参阅 mount(8)。

```