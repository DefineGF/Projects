package com.cjm.classrecord.activity;

import android.content.Intent;
import android.os.Bundle;

import com.cjm.classrecord.R;
import com.cjm.classrecord.StudentAdapter;
import com.cjm.classrecord.bean.StudentInfo;
import com.cjm.classrecord.db.StudentInfoDBHelper;
import com.cjm.classrecord.interfaces.StudentInfoClickListener;
import com.cjm.classrecord.interfaces.StudentQueryCallback;
import com.cjm.classrecord.widget.MyProgressDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements StudentQueryCallback,
        StudentInfoClickListener, View.OnClickListener{
    private final static String TAG = "MainActivity";

    private List<StudentInfo> studentInfoList = new LinkedList<>();
    private StudentAdapter adapter;
    private MyProgressDialog progressDialog; // 为了减少 Activity 中代码
    private StudentInfoDBHelper studentInfoDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Log.i(TAG, "run the onCreate()");

        studentInfoDBHelper = new StudentInfoDBHelper(getApplicationContext());
        initView();

    }

    private void initView() {
        findViewById(R.id.test_activity).setOnClickListener(this);

        FloatingActionButton fab = findViewById(R.id.main_fab);
        fab.setOnClickListener(this);

        // 设置 RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rv_student);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new StudentAdapter(studentInfoList);
        adapter.setStudentInfoClickListener(this);
        recyclerView.setAdapter(adapter);

        // 加载数据源
        if (studentInfoList.size() == 0) {
            new Thread(new QueryRunnable(this)).start();
            progressDialog = new MyProgressDialog(this);
            progressDialog.open();
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // 数据库请求 接口回调
    @Override
    public void queryDataCallback(List<StudentInfo> students) {
        progressDialog.cancel();
        studentInfoList.clear();
        studentInfoList.addAll(students);
        adapter.notifyDataSetChanged();
    }

    // 点击 item 跳转详情
    @Override
    public void onStudentInfoClick(int student_id, String name) {
        Intent intent = new Intent(MainActivity.this,
                ClassRecordActivity.class);
        intent.putExtra("student_id", student_id);
        intent.putExtra("student_name", name);
        startActivity(intent);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.test_activity) {
            Intent intent = new Intent(MainActivity.this, TestActivity.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.main_fab) { // 点击 FloatingActionButton
            Toast.makeText(this, "添加记录", Toast.LENGTH_SHORT).show();
        }
    }


    private class QueryRunnable implements Runnable {
        private StudentQueryCallback callback;
        QueryRunnable(StudentQueryCallback callback) {
            this.callback = callback;
        }
        @Override
        public void run() {
            List<StudentInfo> students = studentInfoDBHelper.queryStudent();
            callback.queryDataCallback(students);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
