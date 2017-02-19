package com.nii.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import com.nii.service.binder.DownloadBinder;
import com.nii.service.service.DownloadService;

public class MainActivity extends Activity implements View.OnClickListener
{

    /**
     * 启动Service button
     */
    private Button startServiceButton;


    /**
     * use binder Service button
     */
    private Button usebinderServiceButton;

    /**
     * 启动binder Service button
     */
    private Button binderServiceButton;

    /**
     * contect
     */
    private Context mContext;

    /**
     * binder
     */
    private DownloadBinder myBinder;


    private ServiceConnection connection = new ServiceConnection()
    {

        @Override
        public void onServiceDisconnected(ComponentName name)
        {
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service)
        {
            myBinder = (DownloadBinder) service;
            myBinder.startDownload(mContext);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mContext = this;

        this.startServiceButton = (Button) findViewById(R.id.startServiceButton);
        this.binderServiceButton = (Button) findViewById(R.id.binderServiceButton);
        this.usebinderServiceButton = (Button) findViewById(R.id.usebinderServiceButton);

        startServiceButton.setOnClickListener(this);
        binderServiceButton.setOnClickListener(this);
        usebinderServiceButton.setOnClickListener(this);
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
            case R.id.startServiceButton:
                Intent intent = new Intent(this, DownloadService.class);
                startService(intent);
                break;

            case R.id.binderServiceButton:
                Intent bindIntent = new Intent(this, DownloadService.class);
                bindService(bindIntent, connection, BIND_AUTO_CREATE);
                break;
            case R.id.usebinderServiceButton:
                myBinder.startDownload(mContext);
                break;
        }
    }
}
