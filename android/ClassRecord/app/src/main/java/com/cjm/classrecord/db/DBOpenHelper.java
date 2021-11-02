package com.cjm.classrecord.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBOpenHelper extends SQLiteOpenHelper {
    private final static String TAG = "DBOpenHelper";


    DBOpenHelper(@Nullable Context context, @Nullable String name,
                 @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "run the onCreate~");

        // 创建学生基本信息表
        String sql = "create table if not exists student_info(" +
                "student_id integer primary key autoincrement," +
                "student_name varchar(20)," +
                "grade varchar(16)," +
                "subject varchar(32));";
        db.execSQL(sql);

        Log.i(TAG, "table_1 create successful~");

        // 创建上课记录表
        sql = "CREATE TABLE" +
                " IF" +
                " NOT EXISTS class_record (" +
                " record_id INTEGER PRIMARY KEY autoincrement," +
                " student_id INTEGER," +
                " _month INTEGER," +
                " _day INTEGER," +
                " _week INTEGER," +
                " class_type BLOB," +
                " start_time VARCHAR ( 8 )," +
                " end_time VARCHAR ( 8 )," +
                " class_size DOUBLE," +
                " detail VARCHAR ( 1024 ));";
        db.execSQL(sql);
        Log.i(TAG, "table_2 create successful~");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
