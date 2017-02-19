package comnii.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import comnii.activity.type.RequestPermissionType;
import comnii.activity.util.DialogUtil;

public class MainActivity extends AppCompatActivity
{

    /**
     * context
     */
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mContext = this;
    }

    /**
     * 点击打电话的按钮响应事件
     *
     * @param view view
     */
    public void callButtonClickAction(View view)
    {

        //先new出一个监听器，设置好监听
        DialogInterface.OnClickListener dialogOnclicListener = new DialogInterface.OnClickListener()
        {

            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                switch (which)
                {
                    case Dialog.BUTTON_POSITIVE:
                        Toast.makeText(MainActivity.this, "Yes" + which, Toast.LENGTH_SHORT).show();
                        requestPermission();
                        break;
                    case Dialog.BUTTON_NEGATIVE:
                        Toast.makeText(MainActivity.this, "No" + which, Toast.LENGTH_SHORT).show();
                        break;
                    case Dialog.BUTTON_NEUTRAL:
                        Toast.makeText(MainActivity.this, "Cancel" + which, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };

        //弹窗让用户选择，是否允许申请权限
        DialogUtil.showConfirm(mContext, "申请权限", "是否允许获取打电话权限？", dialogOnclicListener, dialogOnclicListener);
    }

    /**
     * 申请权限
     */
    private void requestPermission()
    {
        //判断Android版本是否大于23
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE);

            if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE},
                        RequestPermissionType.REQUEST_CODE_ASK_CALL_PHONE);
                return;
            }
            else
            {
                callPhone();
            }
        }
        else
        {
            callPhone();
        }
    }

    /**
     * 注册权限申请回调
     * @param requestCode 申请码
     * @param permissions 申请的权限
     * @param grantResults 结果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        switch (requestCode)
        {
            case  RequestPermissionType.REQUEST_CODE_ASK_CALL_PHONE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    callPhone();
                }
                else
                {
                    // Permission Denied
                    Toast.makeText(MainActivity.this, "CALL_PHONE Denied", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    /**
     * 拨号方法
     */
    private void callPhone()
    {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:10086"));
        startActivity(intent);
    }
}
