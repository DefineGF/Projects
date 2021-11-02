package com.cjm.classrecord.widget;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.cjm.classrecord.R;

public class MyProgressDialog extends ProgressDialog {
    private static final String TAG = "MyProgressDialog";

    public MyProgressDialog(Context context) {
        super(context);
        initDialog();
    }

    private void initDialog() {
        setIcon(R.mipmap.ic_launcher);
        setTitle("数据库加载");
        setMessage("请稍候...");
        setIndeterminate(true);// 是否形成一个加载动画  true表示不明确加载进度形成转圈动画  false 表示明确加载进度
        setCancelable(false);   //点击返回键或者dialog四周是否关闭dialog  true表示可以关闭 false表示不可关闭
    }

    public void open() {
        show();
        Log.i(TAG, "dialog show");
    }

    public void close() {
        cancel();
        Log.i(TAG, "dialog cancel");
    }
}
