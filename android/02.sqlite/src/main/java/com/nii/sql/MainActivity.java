package com.nii.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.nii.sql.util.SqliteHelper;

public class MainActivity extends AppCompatActivity
{
    /**
     * Context
     */
    private Context mContext;

    /**
     * Sqlite 帮助类
     */
    private SqliteHelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mContext = this;
        this.sqliteHelper = new SqliteHelper(mContext);

        //初始化创建一个数据库对象
        SQLiteDatabase sqLiteDatabase  = sqliteHelper.getReadableDatabase();

        //sqLiteDatabase.execSQL("Sql");

    }
}
