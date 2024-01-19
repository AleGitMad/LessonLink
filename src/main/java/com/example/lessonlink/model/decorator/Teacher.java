package com.example.lessonlink.model.decorator;

public class Teacher extends Educator {

    private int teacherId;
    private String name;
    private int averageRating;

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

    public void setAverageRating(int averageRating) {
        this.averageRating = averageRating;
    }
    public int getAverageRating() {
        return averageRating;
    }

    @Override
    public int setAddFare() {
        return this.fare;
    }

    @Override
    public String setDecoration() { return this.decorations; }
}
