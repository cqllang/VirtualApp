# 使用记录

***

## Syncing a fork

``` bash
- git fetch upstream
- git checkout master
- git merge upstream/master (git rebase upstream/master)
```

## 在 *attachBaseContext()* 添加
``` java
ServiceManagerNative.SERVICE_CP_AUTH = BuildConfig.APPLICATION_ID + ".virtual.service.BinderProvider";
VirtualCore.get().startup(base);
```