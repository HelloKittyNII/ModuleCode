package com.wzj.fuzzer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;
import com.google.gson.Gson;
import com.wzj.fuzzer.adapter.AppInfoAdapter;
import com.wzj.fuzzer.type.ProgressBarStatus;
import com.wzj.fuzzer.util.AppUtil;
import com.wzj.fuzzer.vo.AppInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wzj on 2017/10/14.
 */
public class AppInfoActivity extends Activity
{
    /**
     * 上下文
     */
    private Context mContext = this;

    /**
     * 缓存app列表
     */
    private List<AppInfo> cacheAppInfoList;

    /**
     * app列表
     */
    private List<AppInfo> appInfoList;

    /**
     * 进度条
     */
    private ProgressBar progressBar = null;

    /**
     * 列表
     */
    private ListView listView = null;

    /**
     * 获取app列表信息线程
     */
    private Thread mGetPkgInfoThread = null;

    /**
     * 适配器
     */
    private AppInfoAdapter appInfoAdapter;

    /**
     * title
     */
    private TextView titleTextView;

    /**
     * 搜索框
     */
    private EditText searchAppEditText;

    /**
     * 通信
     */
    private Handler mHandler = new Handler()
    {
        /**
         * Subclasses must implement this to receive messages.
         *
         * @param msg
         */
        @Override
        public void handleMessage(Message msg)
        {
            if (msg.what == ProgressBarStatus.HIDE.getStatus())
            {
                progressBar.setVisibility(View.GONE);
                listView.setAdapter(appInfoAdapter);
            }
            else if (msg.what == ProgressBarStatus.VISIBLE.getStatus())
            {
                progressBar.setVisibility(View.VISIBLE);
            }
        }
    };

    private Runnable appInfoListRunner = new Runnable()
    {
        @Override
        public void run()
        {
            appInfoList = AppUtil.getPackageInfo(mContext);

            cacheAppInfoList = new ArrayList<AppInfo>(appInfoList);

            appInfoAdapter = new AppInfoAdapter(mContext,appInfoList);
            mHandler.obtainMessage(ProgressBarStatus.HIDE.getStatus()).sendToTarget();
        }
    };

    /**
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle contains the data it most
     *                           recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     * @see #onStart
     * @see #onSaveInstanceState
     * @see #onRestoreInstanceState
     * @see #onPostCreate
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.app_info_list);

        setProgressBarVisibility(true);

        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        listView = (ListView) findViewById(R.id.app_listview);
        searchAppEditText = (EditText) findViewById(R.id.searchAppEditText);

        progressBar.setIndeterminate(false);
        titleTextView = (TextView) findViewById(R.id.text_title);
        titleTextView.setText("Intent Fuzzer");

        mHandler.obtainMessage(ProgressBarStatus.VISIBLE.getStatus()).sendToTarget();

        if (mGetPkgInfoThread == null)
        {
            mGetPkgInfoThread = new Thread(appInfoListRunner);
            mGetPkgInfoThread.start();
        }

        setListViewClickAction();
        setSearchAppEditTextAction();
    }

    private void setSearchAppEditTextAction()
    {
        searchAppEditText.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                appInfoAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });
    }

    private void setListViewClickAction()
    {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                AppInfo appInfo = (AppInfo) appInfoAdapter.getItem(position);

                Intent intent = new Intent(mContext,IntentFuzzerActivity.class);

                intent.putExtra("packageName",appInfo.getPackageName());

                startActivity(intent);
            }
        });
    }
}
