```sh
#!/bin/bash

basepath=$(
  cd $(dirname $0)
  pwd
)
cd $basepath
mysql -u"用户" -p"密码" -f <./data_clean.sql
```

```sh
use fort;
select * from liuzonglin;
```
