# 使用记录

***

## Syncing a fork

``` bash
- git fetch upstream
- git checkout master
- git merge upstream/master (git rebase upstream/master)
```

## 使用
- 初始化
在 *attachBaseContext()* 添加
``` java
ServiceManagerNative.SERVICE_CP_AUTH = BuildConfig.APPLICATION_ID + ".virtual.service.BinderProvider";
VirtualCore.get().startup(base);
```
- 安装
VirtualCore.get().installApp(app.path, InstallStrategy.COMPARE_VERSION);
- 是否安装
VirtualCore.get().isAppInstalled(pkg.packageName)
- 获取所有虚拟安装的 app
List<AppSetting> infos = VirtualCore.get().getAllApps();
- 查找
AppSetting appSetting = VirtualCore.get().findApp(appModel.packageName);
- 预装
VirtualCore.get().preOpt(appModel.packageName);
- 启动
VActivityManager.get().startActivity(intent, userId)
- 删除
VirtualCore.get().uninstallApp(app.packageName);



```
ServiceManagerNative.getService(ServiceManagerNative.APP_MANAGER)
```
## Service & Impl
- VirtualApp/lib/src/main/aidl/com/lody/virtual/service/ # com.lody.virtual.service
IAccountManager.aidl
IActivityManager.aidl
IAppManager.aidl
IPackageManager.aidl
IUserManager.aidl
- VirtualApp/lib/src/main/java/com/lody/virtual/service/pm/  #com.lody.virtual.service.pm
VAppManagerService.java
VUserManagerService.java
VPackageManagerService.java
../am
VActivityManagerService.java