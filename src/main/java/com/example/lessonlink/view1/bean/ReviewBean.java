package com.example.lessonlink.view1.bean;

import java.time.LocalDate;
import java.util.Date;

public class ReviewBean {
    private float stars;
    private String comment;
    private int teacherId;
    private Date date;

    public float getStars() {
        return stars;
    }

    public void setStars(float stars) {
        this.stars = stars;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean validate() {
        return !(stars < 0) && !(stars > 10);
    }
}
