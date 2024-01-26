package com.example.lessonlink.model.decorator;

import com.example.lessonlink.model.observer.Observer;
import com.example.lessonlink.model.observer.Subject;

import java.util.ArrayList;
import java.util.List;

public abstract class Educator extends Subject {
    protected String subject1;
    protected String subject2;
    protected String subject3;
    protected int fare;
    protected String city;
    protected String qualification;
    protected boolean availableOnline;
    protected String decorations = "0".repeat(13);



    //observer pattern
    List<Observer> observers = new ArrayList<>();
    protected Educator() {
        super();
    }
    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }



    public abstract int setAddFare();
    public abstract String setDecoration();

    public void setDecoration(String decorations) {
        this.decorations = decorations;
    }
    public String getSubject1() {
        return subject1;
    }
    public void setSubject1(String subject1) {
        this.subject1 = subject1;
    }
    public String getSubject2() {
        return subject2;
    }
    public void setSubject2(String subject2) {
        this.subject2 = subject2;
    }
    public String getSubject3() {
        return subject3;
    }
    public void setSubject3(String subject3) {
        this.subject3 = subject3;
    }
    public int getFare() {
        return fare;
    }
    public void setFare(int fare) {
        this.fare = fare;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getQualification() {
        return qualification;
    }
    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
    public boolean isAvailableOnline() {
        return availableOnline;
    }
    public void setAvailableOnline(boolean availableOnline) {
        this.availableOnline = availableOnline;
    }
}
