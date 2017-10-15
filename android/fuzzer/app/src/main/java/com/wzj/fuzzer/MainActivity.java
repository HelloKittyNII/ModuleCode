package com.wzj.fuzzer;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity
{
    /**
     * intent fuzzer按钮
     */
    private Button intentFuzzerButton;

    /**
     * 上下文
     */
    private Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intentFuzzerButton = (Button) findViewById(R.id.intentFuzzerButton);

        intentFuzzerButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(mContext,AppInfoActivity.class);
                startActivity(intent);
            }
        });
    }
}
