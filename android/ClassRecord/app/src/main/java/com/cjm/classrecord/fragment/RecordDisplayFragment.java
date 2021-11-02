package com.cjm.classrecord.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cjm.classrecord.R;
import com.cjm.classrecord.activity.ClassRecordActivity;
import com.cjm.classrecord.bean.ClassRecordInfo;

import java.util.LinkedList;
import java.util.List;

public class RecordDisplayFragment extends Fragment {
    private final static String TAG = "ClassRecordActivity";

    private static final String ARG_STUDENT_ID = "student_id";
    private Context mContext = null;
    private List<ClassRecordInfo> data = new LinkedList<>();

    private OnFragmentInteractionListener mListener;

    public RecordDisplayFragment() {
        // Required empty public constructor
    }

    public static RecordDisplayFragment newInstance() {
        return new RecordDisplayFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Display: onCreate()");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "Display: onCreateView");
        View view = inflater.inflate(R.layout.fragment_record_display, container, false);
        RecyclerView recordRecyclerView = view.findViewById(R.id.rv_class_record);
        data = ((ClassRecordActivity)mContext).getRecordInfoList();
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
    public void onStart() {
        super.onStart();
        Log.i(TAG, "Display: onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "Display: onResume()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
