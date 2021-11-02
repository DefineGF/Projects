package com.cjm.classrecord.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.cjm.classrecord.bean.ClassRecordInfo;
import com.cjm.classrecord.bean.Constant;


import java.util.LinkedList;
import java.util.List;

public class ClassRecordDBHelper {
    private final static String TAG = "ClassRecordDBHelper";
    private final static String TABLE_NAME = "class_record";

    private SQLiteDatabase sqLiteDatabase = null;

    public ClassRecordDBHelper(Context context) {
        DBOpenHelper dbOpenHelper = new DBOpenHelper(context,
                Constant.DATABASE_NAME, null, 1);
        sqLiteDatabase = dbOpenHelper.getWritableDatabase();
    }

    public List<ClassRecordInfo> queryClassRecordById(int studentId) {
        List<ClassRecordInfo> res = new LinkedList<>();
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME,
                new String[]{"_month", "_day", "_week", "class_type",
                        "subject", "start_time", "end_time", "class_size", "detail"},
                "student_id=?", new String[]{String.valueOf(studentId)},
                null, null, null);

        while(cursor.moveToNext()){
            int month = cursor.getInt(cursor.getColumnIndex("_month"));
            int day = cursor.getInt(cursor.getColumnIndex("_day"));
            int week = cursor.getInt(cursor.getColumnIndex("_week"));
            int class_type = cursor.getInt(cursor.getColumnIndex("class_type"));
            String subject = cursor.getString(cursor.getColumnIndex("subject"));
            String start_time = cursor.getString(cursor.getColumnIndex("start_time"));
            String end_time = cursor.getString(cursor.getColumnIndex("end_time"));
            double class_size = cursor.getDouble(cursor.getColumnIndex("class_size"));
            String detail = cursor.getString(cursor.getColumnIndex("detail"));

            ClassRecordInfo classRecordInfo = new ClassRecordInfo.ClassRecordInfoBuilder()
                    .studentID(studentId)
                    .day(month, day, week).time(start_time, end_time).classSize(class_size)
                    .subject(subject)
                    .detail(detail)
                    .singleToS((class_type == Constant.TEACH_TYPE_S_S))
                    .build();
            Log.i(TAG, "get the class_info is: " + classRecordInfo.toString());
            res.add(classRecordInfo);
        }
        Log.i(TAG, "get the list_size is: " + res.size() + " print end~");
        cursor.close();
        return res;
    }
}
