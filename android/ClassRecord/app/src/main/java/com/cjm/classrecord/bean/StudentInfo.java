package com.cjm.classrecord.bean;

public class StudentInfo {
    private int studentId;
    private String name;
    private String grade;
    private String subject;

    private String addition = "";

    public StudentInfo() {}

    public StudentInfo(int id, String name, String grade, String subject) {
        setId(id);
        setName(name);
        setGrade(grade);
        setSubject(subject);
    }

    public int getId() {
        return studentId;
    }

    public void setId(int id) {
        this.studentId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }


    public void setAddition(String addition) {this.addition = addition;}

    public String getAddition() {return addition; }
}
