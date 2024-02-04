package com.example.lessonlink.model;

import com.example.lessonlink.model.decorator.Educator;
import javafx.util.Builder;

public class Teacher extends Educator {

    private int teacherId;
    private int adminId;
    private String name;
    private float averageRating;
    private boolean hasReviews;
    private int totalReviews;

    //two methods to reduce the number of parameters in the constructor
    public void initTeacherInfo(int teacherId, String name, int fare, String city, String qualification, boolean availableOnline) {
        this.teacherId = teacherId;
        this.name = name;
        this.fare = fare;
        this.city = city;
        this.qualification = qualification;
        this.availableOnline = availableOnline;
    }
    public void initTeacherSubjects(String subject1, String subject2, String subject3) {
        this.subject1 = subject1;
        this.subject2 = subject2;
        this.subject3 = subject3;
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

    public int getTotalReviews() {
        return totalReviews;
    }

    public void setTotalReviews(int totalReviews) {
        this.totalReviews = totalReviews;
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
        notifyObservers();
    }
    public boolean getHasReviews() {
        return hasReviews;
    }
    public void setHasReviews(boolean hasReviews) {
        this.hasReviews = hasReviews;
    }
    public int getAdminId() { return adminId; }
    public void setAdminId(int adminId) { this.adminId = adminId; }
}
