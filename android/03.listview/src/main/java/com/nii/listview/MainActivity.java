package com.nii.listview;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.nii.listview.adapter.ListViewAdapter;
import com.nii.listview.bean.StudentBean;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{

    /**
     * Context
     */
    private Context mContext;


    /**
     * listview
     */
    private ListView listView;

    /**
     * 适配器
     */
    private ListViewAdapter listViewAdapter;

    /**
     * 保存数据
     */
    private List<StudentBean> studentBeanList = new ArrayList<StudentBean>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mContext = this;

        //加载listview
        listView = (ListView) findViewById(R.id.listView);
        listViewAdapter = new ListViewAdapter(mContext,studentBeanList);
        listView.setAdapter(listViewAdapter);

        //save button的点击事件
        Button saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                saveStudentMessage();
            }
        });
    }



    /**
     * 保存学生的信息
     */
    private void saveStudentMessage()
    {
        EditText nameEditText = (EditText) findViewById(R.id.nameEditText);
        EditText descEditText = (EditText) findViewById(R.id.descEditText);

        if (StringUtils.isEmpty(nameEditText.getText().toString())
                || StringUtils.isEmpty(descEditText.getText().toString()))
        {
            Toast.makeText(mContext,"姓名和描述信息都不能为空",Toast.LENGTH_SHORT).show();
            return;
        }

        //判断该学生是否存在
        for (StudentBean studentBean : studentBeanList)
        {
            if (StringUtils.equals(studentBean.getName(),nameEditText.getText().toString()))
            {
                Toast.makeText(mContext,nameEditText.getText().toString() + "已经存在",Toast.LENGTH_SHORT).show();
                return;
            }
        }


        StudentBean studentBean = new StudentBean(nameEditText.getText().toString(),descEditText.getText().toString());
        studentBeanList.add(studentBean);

        listViewAdapter.notifyDataSetChanged();
    }

}
