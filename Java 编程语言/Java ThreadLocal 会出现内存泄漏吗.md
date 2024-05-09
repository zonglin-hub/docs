# ThreadLocal 会出现内存泄漏吗？

- [ThreadLocal可能引起的内存泄露](https://www.cnblogs.com/yifanSJ/p/16330808.html)
- [头条二面：你确定ThreadLocal真的会造成内存泄露？](https://baijiahao.baidu.com/s)
- [ThreadLocal的内存泄露？什么原因？如何避免？](https://blog.csdn.net/daobuxinzi/article/details/126766201)

---

ThreadLocal 里面使用了一个存在弱引用的 Map, 当释放掉 ThreadLocal 的强引用以后，Map里面的 value 却没有被回收，而这块 value 永远不会被访问到了。所以存在着内存泄露，**最好的做法是将调用 ThreadLocal 的 remove() 方法.**

在 ThreadLocal 的生命周期中，都存在这些引用看下图：

每个thread中都存在一个map, map的类型是`ThreadLocal.ThreadLocalMap`​​。

```java
static class ThreadLocalMap {
 
    static class Entry extends WeakReference<ThreadLocal<?>> {
        /** The value associated with this ThreadLocal. */
        Object value;
 
        Entry(ThreadLocal<?> k, Object v) {
            super(k);
            value = v;
        }
    }
    ...
}
```

## ThreadLocal 内存泄漏的原因

从上图中可以看出，ThreadLocalMap 使用 ThreadLocal 的弱引用作为 key，如果一个 ThreadLocal 不存在外部**强引用**时，Key(ThreadLocal) 势必会被 GC 回收，这样就会导致 ThreadLocalMap 中 key 为 null， 而 value 还存在着强引用，只有 Thread 线程退出以后, value 的强引用链条才会断掉。

但如果当前线程再迟迟不结束的话，这些key为null的Entry的value就会一直存在一条强引用链：

*<u>Thread Ref -&gt; Thread -&gt; ThreaLocalMap -&gt; Entry -&gt; value</u>*

永远无法回收，造成内存泄漏。

**Java为了最小化减少内存泄露的可能性和影响，在 ThreadLocal 的 get、set 的时候都会清除线程 Map 里所有 key 为 null 的 value。所以最怕的情况就是，ThreadLocal 对象设 null 了。**

## key 使用强引用

当 ThreadLocalMap 的 key 为强引用回收 ThreadLocal 时，因为 ThreadLocalMap 还持有 ThreadLocal 的强引用，如果没有手动删除，ThreadLocal 不会被回收，导致 Entry 内存泄漏。

## key 使用弱引用

当 ThreadLocalMap 的 key 为弱引用回收 ThreadLocal 时，由于 ThreadLocalMap 持有 ThreadLocal 的弱引用，即使没有手动删除，ThreadLocal 也会被回收。当 key 为 null，在下一次 ThreadLocalMap 调用 set()、get()，remove() 方法的时候会被清除 value 值。

```java
        /**
         * Remove the entry for key.
         */
        private void remove(ThreadLocal<?> key) {
     // 使用 hash 方式，计算当前 ThreadLocal 变量所在 table 数组位置
            Entry[] tab = table;
            int len = tab.length;
            int i = key.threadLocalHashCode & (len-1);
     // 再次循环判断是否在为 ThreadLocal 变量所在 table 数组位置
            for (Entry e = tab[i];
                 e != null;
                 e = tab[i = nextIndex(i, len)]) {
                if (e.refersTo(key)) {
      // 调用 WeakReference 的 clear 方法清除对ThreadLocal的弱引用
                    e.clear();
      // 清理key为null的元素
                    expungeStaleEntry(i);
                    return;
                }
            }
        }

        private int expungeStaleEntry(int staleSlot) {
            Entry[] tab = table;
            int len = tab.length;

            // expunge entry at staleSlot
            tab[staleSlot].value = null;
            tab[staleSlot] = null;
            size--;

            // Rehash until we encounter null
            Entry e;
            int i;
            for (i = nextIndex(staleSlot, len);
                 (e = tab[i]) != null;
                 i = nextIndex(i, len)) {
                ThreadLocal<?> k = e.get();
                if (k == null) {
                    e.value = null;
                    tab[i] = null;
                    size--;
                } else {
                    int h = k.threadLocalHashCode & (len - 1);
                    if (h != i) {
                        tab[i] = null;

                        // Unlike Knuth 6.4 Algorithm R, we must scan until
                        // null because multiple entries could have been stale.
                        while (tab[h] != null)
                            h = nextIndex(h, len);
                        tab[h] = e;
                    }
                }
            }
            return i;
        }
```

## 总结

- 由于 Thread 中包含变量 ThreadLocalMap，因此 ThreadLocalMap 与 Thread 的生命周期是一样长，如果都没有手动删除对应 key，都会导致内存泄漏。
- 但是使用**弱引用**可以多一层保障：弱引用 ThreadLocal 不会内存泄漏，对应的 value 在下一次 ThreadLocalMap 调用 set()，get()，remove() 的时候会被清除。
- 因此，ThreadLocal 内存泄漏的根源是：由于 ThreadLocalMap 的生命周期跟 Thread 一样长，如果没有手动删除对应 key 就会导致内存泄漏，而不是因为弱引用。

## ThreadLocal正确的使用方法

- 每次使用完 ThreadLocal 都调用它的 remove() 方法清除数据
- 将 ThreadLocal 变量定义成 private static，这样就一直存在 ThreadLocal 的强引用，也就能保证任何时候都能通过 ThreadLocal 的弱引用访问到 Entry 的 value 值，进而清除掉 。

**Java 的4种引用类型，主要是在垃圾回收时java虚拟机会根据不同的引用类型采取不同的措施。**

- **强引用：** java默认的引用类型，例如 `Object a = new Object();`​ 其中 a 为强引用，new Object() 为一个具体的对象。一个对象从根路径能找到强引用指向它，JVM 虚拟机就不会回收。
- **软引用(SoftReference)：** 进行**年轻代的垃圾回收**不会触发 SoftReference 所指向对象的回收；但如果触发 Full GC，那 SoftReference 所指向的对象将被回收。**备注：是除了软引用之外没有其他强引用引用的情况下**。
- **弱引用(WeakReference)：** 如果对象除了有弱引用指向它后没有其他强引用关联它，**当进行年轻代垃圾回收时，该引用指向的对象就会被垃圾回收器回收。**
- **虚引用(PhantomeReference)：** 该引用指向的对象，无法对垃圾收集器收集对象时产生任何影响，但在执行垃圾回收后垃圾收集器会通过注册在 PhantomeReference 上的队列来通知应用程序对象被回收。
