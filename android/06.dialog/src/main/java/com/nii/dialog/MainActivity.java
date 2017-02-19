package com.nii.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.nii.dialog.adapter.CustomAdapter;
import com.nii.dialog.bean.ItemBean;
import com.nii.dialog.dialog.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    /**
     * context
     */
    private Context mContext;


    /**
     * 加载转圈圈的按钮
     */
    private Button loadingDialogButton;

    /**
     * 简单的Dialog
     */
    private Button simpleDialogButton;

    /**
     * 简单列表的Dialog
     */
    private Button simpleListDialogButton;

    /**
     * 单选
     */
    private Button singleChioseDialogButton;

    /**
     * 多选
     */
    private Button multiChoiceDialogButton;

    /**
     * 自定义Adapter
     */
    private Button customAdapterDialog;

    /**
     * 自定义View
     */
    private Button customViewDialogButton;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mContext = this;

        //获取界面控件
        loadingDialogButton = (Button) findViewById(R.id.loadingDialogButton);
        simpleDialogButton = (Button) findViewById(R.id.simpleDialogButton);
        simpleListDialogButton = (Button) findViewById(R.id.simpleListDialogButton);
        singleChioseDialogButton = (Button) findViewById(R.id.singleChioseDialogButton);
        multiChoiceDialogButton = (Button) findViewById(R.id.multiChoiceDialogButton);
        customAdapterDialog = (Button) findViewById(R.id.customAdapterDialog);
        customViewDialogButton = (Button) findViewById(R.id.customViewDialogButton);

        loadingDialogButton.setOnClickListener(this);
        simpleDialogButton.setOnClickListener(this);
        simpleListDialogButton.setOnClickListener(this);
        singleChioseDialogButton.setOnClickListener(this);
        multiChoiceDialogButton.setOnClickListener(this);
        customAdapterDialog.setOnClickListener(this);
        customViewDialogButton.setOnClickListener(this);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.loadingDialogButton:
                loadingDialogButtonAction(v);
                break;
            case R.id.simpleDialogButton:
                simpleDialogButtonAction(v);
                break;
            case R.id.simpleListDialogButton:
                showSimpleListDialog(v);
                break;
            case R.id.singleChioseDialogButton:
                showSingleChoiceDialog(v);
                break;
            case R.id.multiChoiceDialogButton:
                showMultiChoiceDialog(v);
                break;
            case R.id.customAdapterDialog:
                showCustomAdapterDialog(v);
                break;
            case R.id.customViewDialogButton:
                showCustomViewDialog(v);
                break;
        }
    }

    /**
     * 模仿微信，加载转圈圈Dialog
     */
    private void loadingDialogButtonAction(View view)
    {
        LoadingDialog dialog = new LoadingDialog(mContext);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    /**
     * 简单对话框
     */
    private void simpleDialogButtonAction(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle(R.string.simple_dialog);
        builder.setMessage(R.string.dialog_message);

        //监听下方button点击事件
        builder.setPositiveButton(R.string.postive_button, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                Toast.makeText(getApplicationContext(), R.string.toast_postive, Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton(R.string.negative_button, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                Toast.makeText(getApplicationContext(), R.string.toast_negative, Toast.LENGTH_SHORT).show();
            }
        });

        //设置对话框是可取消的
        builder.setCancelable(true);
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    /**
     * 简单的list dialog
     *
     * @param view view
     */
    private void showSimpleListDialog(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle(R.string.simple_list_dialog);

        /**
         * 设置内容区域为简单列表项
         */
        final String[] Items = {"Items_one", "Items_two", "Items_three"};
        builder.setItems(Items, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                Toast.makeText(getApplicationContext(), "You clicked " + Items[i], Toast.LENGTH_SHORT).show();
            }
        });
        builder.setCancelable(true);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * 单选的Dialog
     *
     * @param view view
     */
    private void showSingleChoiceDialog(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle(R.string.single_choice_dialog);

        /**
         * 设置内容区域为单选列表项
         */
        final String[] items = {"Items_one", "Items_two", "Items_three"};
        builder.setSingleChoiceItems(items, 1, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                Toast.makeText(getApplicationContext(), "You clicked " + items[i], Toast.LENGTH_SHORT).show();
            }
        });

        builder.setCancelable(true);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * 多选 dialog
     *
     * @param view view
     */
    private void showMultiChoiceDialog(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle(R.string.simple_list_dialog);

        /**
         * 设置内容区域为多选列表项
         */
        final String[] items = {"Items_one", "Items_two", "Items_three"};
        builder.setMultiChoiceItems(items, new boolean[]{true, false, true}, new DialogInterface.OnMultiChoiceClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean b)
            {
                Toast.makeText(getApplicationContext(), "You clicked " + items[i] + " " + b, Toast.LENGTH_SHORT).show();
            }
        });


        builder.setCancelable(true);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * custom adapter
     *
     * @param view view
     */
    private void showCustomAdapterDialog(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle(R.string.custom_adapter_dialog);

        /**
         * 设置内容区域为自定义adapter
         */
        List<ItemBean> items = new ArrayList<>();
        items.add(new ItemBean(R.mipmap.stu2, "You can call me xiaoming"));
        items.add(new ItemBean(R.mipmap.ic_launcher, "I'm android xiao"));
        CustomAdapter adapter = new CustomAdapter(items, this);
        builder.setAdapter(adapter, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                Toast.makeText(getApplicationContext(), "You clicked" + i, Toast.LENGTH_SHORT).show();
            }
        });

        builder.setCancelable(true);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * show custom dialog
     * @param view
     */
    private void showCustomViewDialog(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle(R.string.custom_view_dialog);

        /**
         * 设置内容区域为自定义View
         */
        LinearLayout loginDialog = (LinearLayout) getLayoutInflater().inflate(R.layout.custom_view, null);
        builder.setView(loginDialog);

        builder.setCancelable(true);
        AlertDialog dialog = builder.create();
        dialog.show();
    }


}
