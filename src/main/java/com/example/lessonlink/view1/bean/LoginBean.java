package com.example.lessonlink.view1.bean;

public class LoginBean {
    protected String email;
    protected String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean validation(){
        return (email.length()>6 && email.contains("@"));
    }
}
