package com.wzj.fuzzer.vo;

import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Created by wzj on 2017/10/14.
 */
public class AppInfo implements Serializable
{
    /**
     * app名字
     */
    private String appName;

    /**
     * 包名
     */
    private String packageName;

    /**
     * 图标
     */
    private Drawable appIcon;

    /**
     * apk的信息
     */
    private PackageInfo packageInfo;

    public String getAppName()
    {
        return appName;
    }

    public void setAppName(String appName)
    {
        this.appName = appName;
    }

    public String getPackageName()
    {
        return packageName;
    }

    public void setPackageName(String packageName)
    {
        this.packageName = packageName;
    }

    public Drawable getAppIcon()
    {
        return appIcon;
    }

    public void setAppIcon(Drawable appIcon)
    {
        this.appIcon = appIcon;
    }

    public PackageInfo getPackageInfo()
    {
        return packageInfo;
    }

    public void setPackageInfo(PackageInfo packageInfo)
    {
        this.packageInfo = packageInfo;
    }
}
