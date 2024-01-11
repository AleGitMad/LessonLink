package com.example.lessonlink.view1.bean;

//TODO: capire se usare questo o il bean di AccountHomepageBean
public class AccountBean {
    private String email;
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
