package com.nii.dialog.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.nii.dialog.R;

/**
 * Created by wzj on 2017/2/19.
 */
public class LoadingDialog extends Dialog
{
    private TextView tv;

    public LoadingDialog(Context context)
    {
        super(context,R.style.loadingDialogStyle);
    }

    private LoadingDialog(Context context, int theme)
    {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
        tv = (TextView) this.findViewById(R.id.tv);
        tv.setText("正在上传...");
        LinearLayout linearLayout = (LinearLayout) this.findViewById(R.id.LinearLayout);
        linearLayout.getBackground().setAlpha(210);
    }
}
