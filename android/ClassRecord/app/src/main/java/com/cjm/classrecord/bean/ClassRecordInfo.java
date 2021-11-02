package com.cjm.classrecord.bean;


public class ClassRecordInfo {
    private boolean isSingleToS;
    private int student_id;
    private int month, day, week;
    private double class_size;
    private String subject;
    private String start_time, end_time;
    private String detail;

    private ClassRecordInfo(ClassRecordInfoBuilder builder) {
        this.isSingleToS = builder.isSingleToS;
        this.student_id = builder.student_id;
        this.month = builder.month;
        this.day = builder.day;
        this.week = builder.week;
        this.class_size = builder.class_size;
        this.subject = builder.subject;
        this.start_time = builder.start_time;
        this.end_time = builder.end_time;
        this.detail = builder.detail;
    }


    public static class ClassRecordInfoBuilder {
        boolean isSingleToS;
        int student_id;
        int month, day, week;
        double class_size;
        String subject;
        String start_time, end_time;
        String detail;

        public ClassRecordInfoBuilder singleToS(boolean isSingleToS) {
            this.isSingleToS = isSingleToS;
            return this;
        }

        public ClassRecordInfoBuilder studentID(int student_id) {
            this.student_id = student_id;
            return this;
        }

        public ClassRecordInfoBuilder day(int month, int day, int week) {
            this.month = month;
            this.day = day;
            this.week = week;
            return this;
        }

        public ClassRecordInfoBuilder classSize(double class_size) {
            this.class_size = class_size;
            return this;
        }

        public ClassRecordInfoBuilder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public ClassRecordInfoBuilder time(String start_time, String end_time) {
            this.start_time = start_time;
            this.end_time = end_time;
            return this;
        }

        public ClassRecordInfoBuilder detail(String detail) {
            this.detail = detail;
            return this;
        }

        public ClassRecordInfo build() {
            return new ClassRecordInfo(this);
        }
    }

    @Override
    public String toString() {
        return "ClassRecordInfo{" +
                "isSingleToS=" + isSingleToS +
                ", student_id=" + student_id +
                ", month=" + month +
                ", day=" + day +
                ", week=" + week +
                ", class_size=" + class_size +
                ", subject='" + subject + '\'' +
                ", start_time='" + start_time + '\'' +
                ", end_time='" + end_time + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public boolean isSingleToS() {
        return isSingleToS;
    }

    public void setSingleToS(boolean singleToS) {
        isSingleToS = singleToS;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public double getClass_size() {
        return class_size;
    }

    public void setClass_size(double class_size) {
        this.class_size = class_size;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }
}
