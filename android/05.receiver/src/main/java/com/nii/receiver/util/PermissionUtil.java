package com.nii.receiver.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by wzj on 2017/2/19.
 */
public abstract class PermissionUtil
{
    /**
     * 请求权限
     * @param mContext mContext
     * @param permission  permission
     * @param requestCode requestCode
     */
    public static void requestPermission(Context mContext,String permission,int requestCode)
    {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
        {
            return;
        }

        int checkCallPhonePermission = ContextCompat.checkSelfPermission(mContext,permission);

        if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions((Activity) mContext, new String[]{permission},requestCode);
        }
    }
}
