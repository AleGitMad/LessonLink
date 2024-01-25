package com.example.lessonlink.view1.bean;


public class TeacherBean {

    private int teacherId;
    private String teacherName;
    private float teacherAverageRating;
    private int teacherFare;
    private boolean teacherHasReview;
    private boolean isOnline; //bool to check if lesson has to be online when registering in database

    public boolean getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(boolean online) {
        isOnline = online;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public float getTeacherAverageRating() {
        return teacherAverageRating;
    }

    public void setTeacherAverageRating(float teacherAverageRating) {
        this.teacherAverageRating = teacherAverageRating;
    }

    public int getTeacherFare() {
        return teacherFare;
    }

    public void setTeacherFare(int teacherFare) {
        this.teacherFare = teacherFare;
    }

    public boolean getTeacherHasReview() {
        return teacherHasReview;
    }

    public void setTeacherHasReview(boolean teacherHasReview) {
        this.teacherHasReview = teacherHasReview;
    }
}
