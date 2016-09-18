package com.lody.virtual.client.service;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;

import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.env.VirtualRuntime;
import com.lody.virtual.helper.compat.BundleCompat;
import com.lody.virtual.helper.utils.VLog;
import com.lody.virtual.server.ServiceCache;
import com.lody.virtual.service.interfaces.IServiceFetcher;

/**
 * @author Lody
 */
public class ServiceManagerNative {

	public static final String PACKAGE = "package";
	public static final String ACTIVITY = "activity";
	public static final String USER = "user";
	public static final String APP = "app";
	public static final String ACCOUNT = "account";
	public static final String JOB = "job";
	public static final String INTENT_FILTER = "intent_filter";
    public static final String INTERCEPTOR_SERVICE = "interceptor_service";
	private static final String TAG = ServiceManagerNative.class.getSimpleName();
//	public static final String SERVICE_CP_AUTH = "virtual.service.BinderProvider";
	public static String SERVICE_CP_AUTH = "";

	private static IServiceFetcher sFetcher;

	public synchronized static IServiceFetcher getServiceFetcher() {
		if (sFetcher == null) {
			Context context = VirtualCore.get().getContext();
			Bundle response = new ProviderCall.Builder(context, SERVICE_CP_AUTH).methodName("@").call();
			if (response != null) {
				IBinder binder = BundleCompat.getBinder(response, "_VA_|_binder_");
				linkBinderDied(binder);
				sFetcher = IServiceFetcher.Stub.asInterface(binder);
			}
		}
		return sFetcher;
	}

	private static void linkBinderDied(final IBinder binder) {
		IBinder.DeathRecipient deathRecipient = new IBinder.DeathRecipient() {
			@Override
			public void binderDied() {
				binder.unlinkToDeath(this, 0);
				VLog.e(TAG, "Ops, the server has crashed.");
				VirtualRuntime.exit();
			}
		};
		try {
			binder.linkToDeath(deathRecipient, 0);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public static IBinder getService(String name) {
		if (VirtualCore.get().isServiceProcess()) {
			return ServiceCache.getService(name);
		}
		IServiceFetcher fetcher = getServiceFetcher();
		if (fetcher != null) {
			try {
				return fetcher.getService(name);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		VLog.e(TAG, "GetService(%s) return null.", name);
		return null;
	}

	public static void addService(String name, IBinder service) {
		IServiceFetcher fetcher = getServiceFetcher();
		if (fetcher != null) {
			try {
				fetcher.addService(name, service);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}

	}

	public static void removeService(String name) {
		IServiceFetcher fetcher = getServiceFetcher();
		if (fetcher != null) {
			try {
				fetcher.removeService(name);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

}
