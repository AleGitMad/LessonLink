package com.example.lessonlink.view1.bean;


import java.time.LocalDateTime;

public class BookingBean {
    private String teacher;
    private String student;
    private LocalDateTime dateTime;
    private int lessonId;
    private boolean isConfirmed;
    private int teacherId;
    private int studentId;

    public void setTeacherName(String teacher) {
        this.teacher = teacher;
    }
    public void setStudentName(String student) {
        this.student = student;
    }
    public void setDate(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }
    public void setIsConfirmed(boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
    }
    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    public String getDateTime() {
        String y = dateTime.toString().substring(0, 4);
        String m = dateTime.toString().substring(5, 7);
        String d = dateTime.toString().substring(8, 10);
        String h = dateTime.toString().substring(11, 13);
        String i = dateTime.toString().substring(14, 16);

        return d + "/" + m + "/" + y + " " + h + ":" + i;
    }
    public boolean getConfirmed() {
        return isConfirmed;
    }
    public String getTeacher() {
        return teacher;
    }
    public String getStudent() {
        return student;
    }
    public int getLessonId() {
        return lessonId;
    }
    public int getTeacherId() {
        return teacherId;
    }
    public int getStudentId() {
        return studentId;
    }
}
