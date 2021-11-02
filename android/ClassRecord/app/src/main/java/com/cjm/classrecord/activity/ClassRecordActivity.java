package com.cjm.classrecord.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.cjm.classrecord.R;
import com.cjm.classrecord.bean.ClassRecordInfo;
import com.cjm.classrecord.db.ClassRecordDBHelper;
import com.cjm.classrecord.fragment.RecordDisplayFragment;
import com.cjm.classrecord.fragment.RecordInputFragment;
import com.cjm.classrecord.interfaces.ClassRecordQueryCallback;
import com.cjm.classrecord.widget.MyProgressDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class ClassRecordActivity extends AppCompatActivity implements ClassRecordQueryCallback,
        View.OnClickListener{
    private final static String TAG = "ClassRecordActivity";

    private String curName = "";  // 用于显示 toolbar
    private int studentId = -1;   // 学生 id

    private List<ClassRecordInfo> recordInfoList = new LinkedList<>();
    private ClassRecordDBHelper classRecordDBHelper = null;

    // 切换 fragment
    private boolean isDisplay = true;
    private RecordDisplayFragment recordDisplayFragment = null;
    private RecordInputFragment recordInputFragment = null;


    private MyProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_record);
        Toolbar toolbar = findViewById(R.id.toolbar_record);
        setSupportActionBar(toolbar);

        curName = getIntent().getStringExtra("student_name");
        studentId = getIntent().getIntExtra("student_id", -1);
        classRecordDBHelper = new ClassRecordDBHelper(this);
        initView();
    }

    private void initView() {
        Objects.requireNonNull(getSupportActionBar()).setTitle(curName);

        FloatingActionButton fab = findViewById(R.id.fab_record);
        fab.setOnClickListener(this);
        if (recordInfoList.size() == 0) {
            new Thread(new QueryRunnable(this)).start();
            progressDialog = new MyProgressDialog(this);
            progressDialog.open();
        }
    }


    public List<ClassRecordInfo> getRecordInfoList() {
        return recordInfoList;
    }

    @Override
    public void recordQueryCallback(List<ClassRecordInfo> classRecordInfoList) {
        this.recordInfoList = classRecordInfoList;
        Log.i(TAG, "get new list, the size is: " + recordInfoList.size());

        if (progressDialog != null)
                progressDialog.close();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab_record) {
            if (isDisplay) {
                toInputFragment();
            } else {
                toDisplayFragment();
            }
            isDisplay = !isDisplay;
        }
    }


    private void toDisplayFragment() {
        if (recordDisplayFragment == null) {
            recordDisplayFragment = RecordDisplayFragment.newInstance();
        }
        replaceFragment(recordDisplayFragment);
    }

    private void toInputFragment() {
        if (recordInputFragment == null) {
            recordInputFragment = RecordInputFragment.newInstance(studentId);
        }
        replaceFragment(recordInputFragment);
    }

    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    private class QueryRunnable implements Runnable {
        private ClassRecordQueryCallback callback;

        QueryRunnable(ClassRecordQueryCallback callback) {
            this.callback = callback;
        }

        @Override
        public void run() {
            List<ClassRecordInfo> classRecordInfoList =
                    classRecordDBHelper.queryClassRecordById(studentId);
            callback.recordQueryCallback(classRecordInfoList);
        }
    }
}
