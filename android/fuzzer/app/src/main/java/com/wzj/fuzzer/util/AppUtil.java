package com.wzj.fuzzer.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.wzj.fuzzer.vo.AppInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wzj on 2017/10/14.
 */
public abstract class AppUtil
{
    /**
     * 获取appinfo
     */
    private static List<AppInfo>  appInfoList = null;

    /**
     * 获取手机上已经按照的app
     * @param context 上下文
     * @return app列表
     */
    public static List<AppInfo> getPackageInfo(Context context)
    {
        List<AppInfo> pkgInfoList = new ArrayList<AppInfo>();

        List<PackageInfo> packages =
                context.getPackageManager().getInstalledPackages(PackageManager.GET_DISABLED_COMPONENTS
                | PackageManager.GET_ACTIVITIES | PackageManager.GET_RECEIVERS
                | PackageManager.GET_INSTRUMENTATION | PackageManager.GET_SERVICES);

        for (int i = 0; i < packages.size(); i++)
        {
            PackageInfo packageInfo = packages.get(i);
            pkgInfoList.add(fillAppInfo(packageInfo, context));

        }

        appInfoList = pkgInfoList;

        return pkgInfoList;

    }

    /**
     * 获取app列表信息
     * @return app列表
     */
    public static PackageInfo getPackageInfo(String packageName)
    {
        for (AppInfo appInfo : appInfoList)
        {
            if (appInfo.getPackageName().equals(packageName))
            {
                return appInfo.getPackageInfo();
            }
        }

        return null;
    }

    private static AppInfo fillAppInfo(PackageInfo packageInfo, Context context)
    {

        AppInfo appInfo = new AppInfo();
        appInfo.setPackageInfo(packageInfo);
        appInfo.setAppName(packageInfo.applicationInfo.loadLabel(context.getPackageManager()).toString());
        appInfo.setPackageName(packageInfo.packageName);
        appInfo.setAppIcon(packageInfo.applicationInfo.loadIcon(context.getPackageManager()));

        return appInfo;

    }
}
