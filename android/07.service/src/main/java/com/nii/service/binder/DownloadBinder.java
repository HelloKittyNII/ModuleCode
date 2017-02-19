package com.nii.service.binder;

import android.content.Context;
import android.os.Binder;
import android.widget.Toast;

/**
 * Created by wzj on 2017/2/19.
 */
public class DownloadBinder extends Binder
{
    /**
     * 模拟开始下载
     * @param context context
     */
    public void startDownload(Context context)
    {
        Toast.makeText(context,"开始下载",Toast.LENGTH_SHORT).show();
    }
}
