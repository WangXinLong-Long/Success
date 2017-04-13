package com.silianchuangye.sumao.success.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by junjun on 2017/4/5.
 */
public class MyHepler extends SQLiteOpenHelper {
    //类没有实例化,是不能用作父类构造器的参数,必须声明为静态
    private static final String name = "sunmao"; //数据库名称
    private static final int version = 1; //数据库版本

    public MyHepler(Context context) {
        //第三个参数CursorFactory指定在执行查询时获得一个游标实例的工厂类,设置为null,代表使用系统默认的工厂类
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS info (name varchar(20)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("ALTER TABLE info ADD phone VARCHAR(12) NULL "); //往表中增加一列
        // DROP TABLE IF EXISTS person 删除表
    }
}
