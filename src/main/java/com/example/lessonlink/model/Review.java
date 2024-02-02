package com.example.lessonlink.model;

import java.util.Date;

public class Review {

    private int reviewId;
    private int stars;
    private Date date;
    private String comment;
    private int teacherId;

    public int getReviewId() {
        return reviewId;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public Review() {}

    public Review(int reviewId, int stars, Date date, String comment, int teacherId) {
        this.reviewId = reviewId;
        this.stars = stars;
        this.date = date;
        this.comment = comment;
        this.teacherId = teacherId;
    }

}
