package com.example.junit1;

public class Subject {
    public String subject;
    public double degree;
    public int semester;
    public int studentid;
    public String studentname;

    public Subject() {
    }

    public Subject(String subject, double degree, int semester, int studentid, String studentname) {
        this.subject = subject;
        this.degree = degree;
        this.semester = semester;
        this.studentid = studentid;
        this.studentname = studentname;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public double getDegree() {
        return degree;
    }

    public void setDegree(double degree) {
        this.degree = degree;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getStudentid() {
        return studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }
}
