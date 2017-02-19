package com.nii.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.widget.Toast;

/**
 * Created by wzj on 2017/2/19.
 */
public class StaticReceiver extends BroadcastReceiver
{

    /**
     * 接收广播
     * @param context context
     * @param intent intent
     */
    @Override
    public void onReceive(Context context, Intent intent)
    {
        String msg = intent.getStringExtra("msg");
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
