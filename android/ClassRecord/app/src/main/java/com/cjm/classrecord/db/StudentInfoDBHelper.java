package com.cjm.classrecord.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.cjm.classrecord.bean.Constant;
import com.cjm.classrecord.bean.StudentInfo;

import java.util.LinkedList;
import java.util.List;

public class StudentInfoDBHelper {
    private final static String TAG = "StudentInfoDBHelper";
    private final static String TABLE_NAME = "student_info";

    private SQLiteDatabase db;

    public StudentInfoDBHelper(Context context) {
        DBOpenHelper dbSqliteOpenHelper = new DBOpenHelper(context,
                Constant.DATABASE_NAME, null, 1);
        db = dbSqliteOpenHelper.getWritableDatabase();
    }

    public void insertStudent(StudentInfo studentInfo) {
        ContentValues values = new ContentValues();
        values.put("student_name", studentInfo.getName());
        values.put("grade", studentInfo.getGrade());
        values.put("subject", studentInfo.getSubject());
        assert db != null;
        db.insert(TABLE_NAME, null, values);
    }

    public void deleteStudent(int student_id) {
        db.delete(TABLE_NAME, "student_id=?", new String[]{String.valueOf(student_id)});
        Log.i(TAG, "delete student_id < " + student_id + " > successful~");
    }

//    public void updateStudent(int student_info, StudentInfo studentID) {
//        ContentValues values = new ContentValues();
//        values.put("grade", "8");
//        db.update("student_info", values, "student_name=?",
//                new String[]{"刘佳妍"});
//    }

    public List<StudentInfo> queryStudent() {
        List<StudentInfo> students = new LinkedList<>();

        Cursor cursor = db.query(TABLE_NAME,
                new String[]{"student_id", "student_name", "grade", "subject"},
                null, null,
                null, null, null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("student_id"));
            String name = cursor.getString(cursor.getColumnIndex("student_name"));
            String grade = cursor.getString(cursor.getColumnIndex("grade"));
            String subject = cursor.getString(cursor.getColumnIndex("subject"));
            Log.i(TAG, "id = " + id + " ;name = " + name + " ;grade = " + grade + " ;subject = " + subject);
            students.add(new StudentInfo(id, name, grade, subject));
        }
        Log.i(TAG, "print end~");
        cursor.close();  // 关闭游标，释放资源
        return students;
    }

}
