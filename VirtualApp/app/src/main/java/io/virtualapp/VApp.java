package io.virtualapp;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import android.widget.Toast;

import com.github.moduth.blockcanary.BlockCanary;
import com.lody.virtual.client.core.InstallStrategy;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.stub.StubManifest;
import com.lody.virtual.helper.proto.InstallResult;
import com.lody.virtual.helper.utils.VLog;

import java.io.IOException;
import jonathanfinerty.once.Once;

/**
 * @author Lody
 */
public class VApp extends Application {

    private static final String[] GMS_PKG = {
            "com.android.vending",

            "com.google.android.gsf",
            "com.google.android.gsf.login",
            "com.google.android.gms",

            "com.google.android.backuptransport",
            "com.google.android.backup",
            "com.google.android.configupdater",
            "com.google.android.syncadapters.contacts",
            "com.google.android.feedback",
            "com.google.android.onetimeinitializer",
            "com.google.android.partnersetup",
            "com.google.android.setupwizard",
            "com.google.android.syncadapters.calendar",};

    private static VApp gDefault;

    public static VApp getApp() {
        return gDefault;
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        try {
            StubManifest.ENABLE_IO_REDIRECT = true;
            VirtualCore.get().startup(base);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate() {
        gDefault = this;
        super.onCreate();
        if (VirtualCore.get().isServerProcess()) {
            VirtualCore.get().setAppRequestListener(new VirtualCore.AppRequestListener() {
                @Override
                public void onRequestInstall(String path) {
                    Toast.makeText(VApp.this, "Installing: " + path, Toast.LENGTH_SHORT).show();
                    InstallResult res = VirtualCore.get().installApp(path, InstallStrategy.UPDATE_IF_EXIST);
                    if (res.isSuccess) {
                        try {
                            VirtualCore.get().preOpt(res.packageName);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (res.isUpdate) {
                            Toast.makeText(VApp.this, "Update: " + res.packageName + " success!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(VApp.this, "Install: " + res.packageName + " success!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(VApp.this, "Install failed: " + res.error, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onRequestUninstall(String pkg) {
                    Toast.makeText(VApp.this, "Uninstall: " + pkg, Toast.LENGTH_SHORT).show();

                }
            });
        }else if (VirtualCore.get().isMainProcess()) {
            Once.initialise(this);
        } else if (VirtualCore.get().isVAppProcess()) {
            BlockCanary.install(this, new AppBlockCanaryContext());
            VirtualCore.get().setComponentDelegate(new MyComponentDelegate());
            VirtualCore.get().setPhoneInfoDelegate(new MyPhoneInfoDelegate());
            VirtualCore.get().setTaskDescriptionDelegate(new MyTaskDescriptionDelegate());
        }
    }


    private void installGms() {
        VirtualCore virtualCore = VirtualCore.get();
        PackageManager pm = virtualCore.getUnHookPackageManager();
        for (String pkg : GMS_PKG) {
            if (virtualCore.isAppInstalled(pkg)) {
                continue;
            }
            try {
                ApplicationInfo appInfo = pm.getApplicationInfo(pkg, 0);
                String apkPath = appInfo.sourceDir;
                InstallResult res = VirtualCore.get().installApp(apkPath,
                        InstallStrategy.DEPEND_SYSTEM_IF_EXIST | InstallStrategy.TERMINATE_IF_EXIST);
                if (!res.isSuccess) {
                    VLog.e(getClass().getSimpleName(), "Warning: Unable to install app %s: %s.", appInfo.packageName, res.error);
                }
            } catch (Throwable e) {
                // Ignore
            }
        }
    }

}