package com.nii.fragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener
{


    /**
     * 中间的面板
     */
    private ContentFragment contentFragment;

    /**
     * chat 面板
     */
    private ChatFragment chatFragment;

    /**
     * 下方的微信按钮
     */
    private Button wenxinButton;

    /**
     * 下方的chat按钮
     */
    private Button chatButton;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);


        wenxinButton = (Button) findViewById(R.id.weinxin);
        chatButton = (Button) findViewById(R.id.chat);

        wenxinButton.setOnClickListener(this);
        chatButton.setOnClickListener(this);

        // 设置默认的Fragment
        setDefaultFragment();
    }

    private void setDefaultFragment()
    {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        contentFragment = new ContentFragment();
        transaction.replace(R.id.id_content, contentFragment);
        transaction.commit();
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v)
    {
        FragmentManager fm = getFragmentManager();

        // 开启Fragment事务
        FragmentTransaction transaction = fm.beginTransaction();

        switch (v.getId())
        {
            case R.id.weinxin:

                if (contentFragment == null)
                {
                    contentFragment = new ContentFragment();
                }
                // 使用当前Fragment的布局替代id_content的控件
                transaction.replace(R.id.id_content, contentFragment);

                break;

            case R.id.chat:

                if (chatFragment == null)
                {
                    chatFragment = new ChatFragment();

                }
                transaction.replace(R.id.id_content, chatFragment);
                break;
        }

        transaction.commit();
    }
}
