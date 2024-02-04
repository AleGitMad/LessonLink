package com.example.lessonlink.model;

public class User {
    protected int userId;
    protected String name;
    protected String email;


    public void setName(String name) {
        this.name = name;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public int getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }
}
