package com.cjm.classrecord.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.cjm.classrecord.R;

import java.util.Calendar;

public class TestActivity extends AppCompatActivity implements View.OnClickListener,
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private final static String TAG = "TestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();

    }

    private void initView() {
        findViewById(R.id.btn_date).setOnClickListener(this);
        findViewById(R.id.btn_time).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_date) {
            Calendar calendar = Calendar.getInstance();
            DatePickerDialog dialog = new DatePickerDialog(this,this,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MARCH),
                    calendar.get(Calendar.DAY_OF_MONTH));
            dialog.show();
        } else if (v.getId() == R.id.btn_time) {
            Calendar calendar = Calendar.getInstance();
            TimePickerDialog dialog = new TimePickerDialog(this,this,
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE),
                    true);
            dialog.show();
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        @SuppressLint("DefaultLocale")
        String desc = String.format("选中日期%d年%d月%d日",year,month + 1, dayOfMonth);
        Log.i(TAG, "获取日期为: " + desc);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        @SuppressLint("DefaultLocale")
        String desc = String.format("您选择%d时%d分",hourOfDay, minute);
        Log.i(TAG, "get the time is: " + desc);
    }
}
