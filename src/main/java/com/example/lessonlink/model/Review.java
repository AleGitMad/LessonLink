package com.example.lessonlink.model;

import java.util.Date;

public class Review {

    private int reviewId;
    private int stars;
    private Date date;
    private String comment;
    private int teacherId;

    public Review(int reviewId, int stars, Date date, String comment, int teacherId) {
        this.reviewId = reviewId;
        this.stars = stars;
        this.date = date;
        this.comment = comment;
        this.teacherId = teacherId;
    }

}
