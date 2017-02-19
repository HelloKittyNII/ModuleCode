package com.nii.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsMessage;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.Toast;
import com.nii.receiver.util.DialogUtil;

import java.util.Date;

/**
 * Created by wzj on 2017/2/19.
 */
public class SystemReceiver extends BroadcastReceiver
{

    /**
     * 接收系统静态广播
     *
     * @param context context
     * @param intent  intent
     */
    @Override
    public void onReceive(Context context, Intent intent)
    {
        if (intent.getAction().equals(Intent.ACTION_BATTERY_LOW))
        {
            Log.e("SystemReceiver", "电量低提示");
            Toast.makeText(context, "您的手机电量偏低，请及时充电", Toast.LENGTH_SHORT).show();
        }
        else if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED"))
        {
            if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED"))
            {
                Bundle bundle = intent.getExtras();
                if (bundle != null)
                {//如果数据不为空
                    //获得收到的短信数据
                    Object[] objArray = (Object[]) bundle.get("pdus");
                    //根据objArray的大小创建一个SmsMessage数组，用于封装短信内容
                    SmsMessage[] smsMessage = new SmsMessage[objArray.length];
                    StringBuffer sb = new StringBuffer();
                    sb.append("时间：" + new DateFormat().format("yyyy - MM - dd hh.mm.ss", new Date()));

                    //遍历smsMessage数组取出所有短信
                    for (int i = 0; i < smsMessage.length; i++)
                    {
                        //将每条字节类型的短信数据转换成SmsMessage对象
                        smsMessage[i] = SmsMessage.createFromPdu((byte[]) objArray[i]);
                        //获取短信发送地址
                        sb.append("发送者：" + smsMessage[i].getOriginatingAddress());
                        //获取短信内容
                        sb.append("短信内容：" + smsMessage[i].getDisplayMessageBody() + "\n");
                    }

                    DialogUtil.showPrompt(context,sb.toString());
                    Toast.makeText(context, sb.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }
        else if(intent.getAction().equals("android.hardware.usb.action.USB_STATE"))
        {
            if (intent.getExtras().getBoolean("connected"))
            {
                Toast.makeText(context, "USB插入", Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(context, "USB拔出", Toast.LENGTH_LONG).show();
            }
        }
    }
}
