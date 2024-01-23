package com.example.lessonlink.model.decorator;

public class Teacher extends Educator {

    private int teacherId;
    private String name;
    private float averageRating;
    private boolean hasReviews;

    public Teacher(int teacherId, String name, String subject1, String subject2, String subject3, int fare, String city, String qualification, boolean availableOnline) {
        this.teacherId = teacherId;
        this.name = name;
        this.subject1 = subject1;
        this.subject2 = subject2;
        this.subject3 = subject3;
        this.fare = fare;
        this.city = city;
        this.qualification = qualification;
        this.availableOnline = availableOnline;
    }

    public Teacher() {
        this.teacherId = 0;
        this.name = "";
        this.subject1 = "";
        this.subject2 = "";
        this.subject3 = "";
        this.fare = 0;
        this.city = "";
        this.qualification = "";
        this.availableOnline = false;
    }

    @Override
    public int setAddFare() {
        return this.fare;
    }
    @Override
    public String setDecoration() { return this.decorations; }

    public int getTeacherId() {
        return teacherId;
    }
    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public float getAverageRating() {
        return averageRating;
    }
    public void setAverageRating(float averageRating) {
        this.averageRating = averageRating;
    }
    public boolean getHasReviews() {
        return hasReviews;
    }

    public void setHasReviews(boolean hasReviews) {
        this.hasReviews = hasReviews;
    }
}
