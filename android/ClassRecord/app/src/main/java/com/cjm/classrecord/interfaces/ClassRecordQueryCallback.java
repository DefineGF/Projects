package com.cjm.classrecord.interfaces;

import com.cjm.classrecord.bean.ClassRecordInfo;

import java.util.List;

public interface ClassRecordQueryCallback {
    public void recordQueryCallback(List<ClassRecordInfo> classRecordInfoList);
}
