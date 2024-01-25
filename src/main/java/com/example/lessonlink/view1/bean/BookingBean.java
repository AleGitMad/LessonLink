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
        String y = dateTime.substring(0, 4);
        String m = dateTime.substring(5, 7);
        String d = dateTime.substring(8, 10);
        String h = dateTime.substring(11, 13);
        String i = dateTime.substring(14, 16);

        return d + "/" + m + "/" + y + " " + h + ":" + i;
    }
    public boolean getConfirmed() {
        return confirmed;
    }
    public String getTeacher() {
        return teacher;
    }
    public String getStudent() {
        return student;
    }
}
