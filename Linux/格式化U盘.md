
第一步：`sudo fdisk -l` 这个命令主要是查看磁盘分区详细情况，同时确定U盘分区的，这里就假设要格式化的U盘是 `/dev/sdb`咯  

```bash
sudo fdisk -l
```


第二步：`sudo umount /dev/sdb` 这个命令主要是卸载U盘分区  

```bash
sudo umount /dev/sda1
```


第三步：`sudo mkfs.vfat /dev/sdb -I` （大写的i）进行格式化，格式化后的格式为FAT  

```bash
sudo mkfs.vfat /dev/sda1 -I
```

> [!NOTE]
> **补充：** 如果想格式化成其他格式，也简单，常见的一个是ntfs和ext4  
**_ntfs：** 可能需要先装一个库来支持 `sudo apt install ntfsprogs`，然后在进行格式化 `sudo mkfs.ntfs /dev/sdb`  
**_ext4：** 这个就简单了，直接 `sudo mkfs.ext4 /dev/sdb`，同样的还有`sudo mkfs.ext3 /dev/sdb` 和 `sudo mkfs.ext2 /dev/sdb`
