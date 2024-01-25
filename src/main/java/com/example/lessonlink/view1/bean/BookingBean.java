package com.example.lessonlink.view1.bean;


public class BookingBean {
    private String dateTime;
    private boolean confirmed;
    private String teacher;
    private String student;


    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }
    public void setLessonId(String teacher) {
        this.teacher = teacher;
    }
    public void setStudentId(String student) {
        this.student = student;
    }
    public String getDateTime() {
        return dateTime;
    }
    public boolean getConfirmed() {
        return confirmed;
    }
    public String getLessonId() {
        return teacher;
    }
    public String getStudentId() {
        return student;
    }
}
