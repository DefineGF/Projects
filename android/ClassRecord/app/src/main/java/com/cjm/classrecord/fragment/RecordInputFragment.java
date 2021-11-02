package com.cjm.classrecord.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.cjm.classrecord.R;

import java.util.Calendar;
import java.util.Objects;

public class RecordInputFragment extends Fragment implements DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener, View.OnClickListener {

    private final static String ARG_STUDENT_ID = "student_id";
    private int studentId = -1;

    private Context mContext;
    private OnFragmentInteractionListener mListener;

//    private EditText et_date, et_week, et_subject, et_start_time, et_end_time, et_class_size, et_detail;

    private int[] etIds = {R.id.et_date, R.id.et_week,                 // 日期相关  0,1
            R.id.et_subject,                                           // 科目     2
            R.id.et_start_time, R.id.et_end_time, R.id.et_class_size,  // 时间相关  3,4,5
            R.id.et_detail};                                           // 课程描述  6

    private EditText[] editTexts = new EditText[etIds.length];

    private int turnFlag = -1;            // 0:start; 1:end
    private boolean isSingleToS = true;
    private int month, day, week = -1;
    private String startTime, endTime;



    public RecordInputFragment() {

    }


    public static RecordInputFragment newInstance(int studentId) {
        RecordInputFragment fragment = new RecordInputFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            studentId = getArguments().getInt(ARG_STUDENT_ID);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_record_input, container, false);
        initView(view);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    private void initView(View view) {
//        et_date = view.findViewById(R.id.et_date);
//        et_week = view.findViewById(R.id.et_week);
//        et_subject = view.findViewById(R.id.et_subject);
//        et_start_time = view.findViewById(R.id.et_start_time);
//        et_end_time = view.findViewById(R.id.et_end_time);
//        et_class_size = view.findViewById(R.id.et_class_size);
//        et_detail = view.findViewById(R.id.et_detail);
        for (int i = 0; i < etIds.length; i++) {
            editTexts[i] = view.findViewById(etIds[i]);
            if (i != 2 && i != 5 && i != 6) {
                editTexts[i].setOnClickListener(this);
            }

        }
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.et_date: // 请求日期
                Calendar dateCalendar = Calendar.getInstance();
                DatePickerDialog dialog = new DatePickerDialog(mContext,this,
                        dateCalendar.get(Calendar.YEAR),
                        dateCalendar.get(Calendar.MARCH),
                        dateCalendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
                break;
            case R.id.et_week:
                onWeekSet();
                break;

            case R.id.et_start_time:
                showTimePickerDialog();
                turnFlag = 0;
                break;
            case R.id.et_end_time:
                showTimePickerDialog();
                turnFlag = 1;
                break;

        }
    }

    private void showTimePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        TimePickerDialog dialog = new TimePickerDialog(mContext,this,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true);
        dialog.show();
    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        this.month = month + 1;
        this.day = dayOfMonth;

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        if (turnFlag == 0) {
            startTime = hourOfDay + ":" + minute;
        } else if (turnFlag == 1) {
            endTime = hourOfDay + ":" + minute;
        }
        turnFlag = -1;
    }

    private void onWeekSet() {
        final String[] items = {"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"};
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext)
                .setIcon(R.drawable.ic_launcher_background)
                .setTitle("选择周几")
                .setCancelable(false)
                .setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        week = i;
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (week != -1) {
                            Toast.makeText(mContext, "你选择了" + items[week], Toast.LENGTH_LONG).show();
                        }
                    }
                });
        builder.create().show();
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

}
