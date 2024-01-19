package com.example.lessonlink.model.decorator;

public abstract class Educator {
    protected String subject1;
    protected String subject2;
    protected String subject3;
    protected int fare;
    protected String city;
    protected String qualification;
    protected boolean availableOnline;
    protected String decorations = "0".repeat(26);
    public abstract int setAddFare();
    public abstract String setDecoration();
}
