package com.nii.sql.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.nii.sql.type.CommonConstant;

/**
 * Created by wzj on 2017/2/12.
 */
public class SqliteHelper extends SQLiteOpenHelper
{
    /**
     * 数据库版本号
     */
    private static final int DB_VERSION = 2;

    /**
     * 数据库名字
     */
    private static final String DB_NAME = "my.db";

    /**
     * 构造函数
     */
    public SqliteHelper(Context context)
    {
        super(context,DB_NAME, null,DB_VERSION);
    }

    /**
     * 第一次创建数据库的时候调用，可以创建表结构
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        Log.d(CommonConstant.LOG_TAG,"Create db");
        db.execSQL("create table info (_id integer primary key autoincrement,name varchar(20))");
    }

    /**
     * 当数据库版本号改动的时候，执行这个函数，可以改变数据库的表结构
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     * <p>
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log.d(CommonConstant.LOG_TAG,"onUpgrade db");
    }
}
