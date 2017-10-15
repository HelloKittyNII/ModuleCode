package com.wzj.fuzzer;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.*;
import com.wzj.fuzzer.type.ComponentType;
import com.wzj.fuzzer.util.AppUtil;
import com.wzj.fuzzer.vo.AppInfo;
import com.wzj.fuzzer.vo.SerializableTest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wzj on 2017/10/14.
 */
public class IntentFuzzerActivity extends Activity
{

    /**
     * title
     */
    private TextView titleTextView;

    /**
     * PackageInfo
     */
    private PackageInfo packageInfo;

    /**
     * activity组件
     */
    private List<ComponentName> activityComponent = new ArrayList<ComponentName>();

    /**
     * receiver组件
     */
    private List<ComponentName> receiverComponent = new ArrayList<ComponentName>();

    /**
     * Service组件
     */
    private List<ComponentName> serviceComponent = new ArrayList<ComponentName>();

    /**
     * 适配器
     */
    private ArrayAdapter<String> componentAdapter;

    /**
     * 组件列表
     */
    private ListView componentListView;

    /**
     * 组件名字
     */
    private List<String> componentNames = new ArrayList<String>();


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
        setContentView(R.layout.intent_fuzzer);

        Intent intent = getIntent();
        String packageName = intent.getStringExtra("packageName");

        titleTextView = (TextView) findViewById(R.id.text_title);
        componentListView = (ListView) findViewById(R.id.component_listview);

        if (packageName == null)
        {
            titleTextView.setText("No Package Name");
            return;
        }

        packageInfo = AppUtil.getPackageInfo(packageName);
        if (packageInfo == null)
        {
            titleTextView.setText("No Package Info");
            return;
        }

        String appName = packageInfo.applicationInfo.loadLabel(this.getPackageManager()).toString();
        titleTextView.setText(appName);

        activityComponent = getComponents(ComponentType.ACTIVITY);
        receiverComponent = getComponents(ComponentType.RECEIVER);
        serviceComponent = getComponents(ComponentType.SERVICE);

        initListView();
        initListViewAction();
    }

    private void initListViewAction()
    {
        componentListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                ComponentName toSend = null;
                Intent intent = new Intent();
                String className = componentAdapter.getItem(position).toString();

                ComponentType componentType = null;
                if (componentType == null)
                {
                    for (ComponentName componentName : activityComponent)
                    {
                        if (componentName.getClassName().equals(className))
                        {
                            toSend = componentName;
                            componentType = ComponentType.ACTIVITY;
                            break;
                        }
                    }
                }

                if (componentType == null)
                {
                    for (ComponentName componentName : receiverComponent)
                    {
                        if (componentName.getClassName().equals(className))
                        {
                            toSend = componentName;
                            componentType = ComponentType.RECEIVER;
                            break;
                        }
                    }
                }

                if (componentType == null)
                {
                    for (ComponentName componentName : serviceComponent)
                    {
                        if (componentName.getClassName().equals(className))
                        {
                            toSend = componentName;
                            componentType = ComponentType.SERVICE;
                            break;
                        }
                    }
                }

                intent.setComponent(toSend);

                if (sendIntentByType(intent, componentType))
                {
                    Toast.makeText(IntentFuzzerActivity.this, "Sent Null " + intent, Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(IntentFuzzerActivity.this, "Send Null " + intent + " Failed!", Toast.LENGTH_LONG).show();
                }
            }

        });

        componentListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                ComponentName toSend = null;
                Intent intent = new Intent();
                String className =  componentAdapter.getItem(position).toString();




                ComponentType componentType = null;
                if (componentType == null)
                {
                    for (ComponentName componentName : activityComponent)
                    {
                        if (componentName.getClassName().equals(className))
                        {
                            toSend = componentName;
                            componentType = ComponentType.ACTIVITY;
                            break;
                        }
                    }
                }

                if (componentType == null)
                {
                    for (ComponentName componentName : receiverComponent)
                    {
                        if (componentName.getClassName().equals(className))
                        {
                            toSend = componentName;
                            componentType = ComponentType.RECEIVER;
                            break;
                        }
                    }
                }

                if (componentType == null)
                {
                    for (ComponentName componentName : serviceComponent)
                    {
                        if (componentName.getClassName().equals(className))
                        {
                            toSend = componentName;
                            componentType = ComponentType.SERVICE;
                            break;
                        }
                    }
                }

                intent.setComponent(toSend);
                intent.putExtra("test", new SerializableTest());

                if (sendIntentByType(intent, componentType)) {
                    Toast.makeText(IntentFuzzerActivity.this, "Sent Serializeable " + intent, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(IntentFuzzerActivity.this, "Send Serializeable " + intent + " Failed!", Toast.LENGTH_LONG).show();
                }
                return true;
            }

        });
    }



    private boolean sendIntentByType(Intent intent, ComponentType type)
    {
        try
        {
            switch (type)
            {
                case ACTIVITY:
                    startActivity(intent);
                    return true;
                case RECEIVER:
                    sendBroadcast(intent);
                    return true;
                case SERVICE:
                    startService(intent);
                    return true;
                default:
                    return true;
            }
        }
        catch (Exception e)
        {

            return false;
        }

    }


    private void initListView()
    {
        componentAdapter = new ArrayAdapter<String>(this, R.layout.component, componentNames);
        componentListView.setAdapter(componentAdapter);

        for (ComponentName componentName : activityComponent)
        {
            componentNames.add(componentName.getClassName());
        }

        for (ComponentName componentName : receiverComponent)
        {
            componentNames.add(componentName.getClassName());
        }

        for (ComponentName componentName : serviceComponent)
        {
            componentNames.add(componentName.getClassName());
        }
    }


    /**
     * 获取组件
     *
     * @param componentType 组件类型
     * @return 结果
     */
    private List<ComponentName> getComponents(ComponentType componentType)
    {
        PackageItemInfo items[] = null;
        ArrayList<ComponentName> cmpsFound = new ArrayList<ComponentName>();
        switch (componentType)
        {
            case ACTIVITY:
                items = packageInfo.activities;
                break;
            case RECEIVER:
                items = packageInfo.receivers;
                break;
            case SERVICE:
                items = packageInfo.services;
                break;
            default:
                items = packageInfo.activities;
                break;
        }

        if (items != null)
        {
            for (PackageItemInfo pkgItemInfo : items)
            {
                cmpsFound.add(new ComponentName(packageInfo.packageName, pkgItemInfo.name));
            }
        }

        return cmpsFound;
    }
}
