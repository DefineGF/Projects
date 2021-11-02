package com.cjm.classrecord.interfaces;

import com.cjm.classrecord.bean.StudentInfo;

import java.util.List;

public interface StudentQueryCallback {
    public void queryDataCallback(List<StudentInfo> students);
}
