package com.example.lessonlink.model;

public class Account {
    protected String userId;
    protected String name;
    protected String email;


    public void setName(String name) {
        this.name = name;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    /*
    public void changeCredentials(String credential, String newCredential){
        switch(credential){
            case "email":
                this.email = newCredential;
                break;
            case "firstname":
                this.firstName = newCredential;
                break;
            case "lastname":
                this.lastName = newCredential;
                break;
            default:
                break;
        }
        notifyObservers();
    }
    */  //TODO: implement change credentials???
}
