package com.example.lessonlink.model;

public class Teacher {
    private String name;
    private String subject1;
    private String subject2;
    private String subject3;
    private int fare;
    private String city;
    private String qualification;
    private boolean availableOnline;

    public Teacher(String name, String subject1, String subject2, String subject3, int fare, String city, String qualification, boolean availableOnline) {
        this.name = name;
        this.subject1 = subject1;
        this.subject2 = subject2;
        this.subject3 = subject3;
        this.fare = fare;
        this.city = city;
        this.qualification = qualification;
        this.availableOnline = availableOnline;
    }


}
