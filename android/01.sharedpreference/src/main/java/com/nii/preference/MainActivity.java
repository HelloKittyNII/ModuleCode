package com.nii.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity
{

    /**
     * 用户名
     */
    private EditText usernameEditText;

    /**
     * 密码
     */
    private EditText passwordEditText;

    /**
     * 是否记住密码
     */
    private CheckBox remCheckbox;

    /**
     * 登录按钮
     */
    private Button loginBt;

    /**
     * 上下文
     */
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        remCheckbox = (CheckBox) findViewById(R.id.remCheckbox);
        loginBt = (Button) findViewById(R.id.loginBt);

        loginBt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                login();
            }
        });

        //获取用户名密码
        Map<String, String> map = getUserInfo();
        if (map != null)
        {
            String username = map.get("username");
            String password = map.get("password");
            usernameEditText.setText(username);//设置用户名
            passwordEditText.setText(password);
            remCheckbox.setChecked(true);//设置复选框选中状态
        }
    }

    private void login()
    {
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        boolean isrem = remCheckbox.isChecked();

        //d.判断用户名密码是否为空，不为空请求服务器（省略，默认请求成功）
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password))
        {
            Toast.makeText(mContext, "用户名密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }


        //e.判断是否记住密码，如果记住，将用户名密码保存本地
        if (isrem)
        {
            boolean result = saveUserInfo(username, password);
            if (result)
            {
                Toast.makeText(mContext, "用户名密码保存成功", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(mContext, "用户名密码保存失败", Toast.LENGTH_SHORT).show();
            }

        }
        else
        {
            Toast.makeText(mContext, "无需保存", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 保存用户名和密码
     *
     * @param username username
     * @param password password
     * @return 结果 true 保存成功 | false 保存失败
     */
    public boolean saveUserInfo(String username, String password)
    {

        try
        {
            SharedPreferences sharedPreferences = mContext.getSharedPreferences("userinfo.txt", Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putString("username", username);
            editor.putString("password", password);

            editor.commit();

            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 获取用户名信息
     *
     * @return 信息
     */
    public Map<String, String> getUserInfo()
    {

        try
        {

            //1.通过Context对象创建一个SharedPreference对象
            SharedPreferences sharedPreferences = mContext.getSharedPreferences("userinfo.txt", Context.MODE_PRIVATE);

            String username = sharedPreferences.getString("username", "");
            String password = sharedPreferences.getString("password", "");


            HashMap<String, String> hashMap = new HashMap<String, String>();
            hashMap.put("username", username);
            hashMap.put("password", password);
            return hashMap;

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
