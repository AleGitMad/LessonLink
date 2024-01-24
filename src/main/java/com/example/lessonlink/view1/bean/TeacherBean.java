package com.example.lessonlink.view1.bean;

import com.example.lessonlink.model.decorator.Teacher;

import java.util.List;

public class TeacherBean {

    private String teacherName;
    private float teacherAverageRating;
    private int teacherFare;
    private boolean teacherHasReview;

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
