package com.example.lessonlink.model;

public class LoggedUser {
    private Account account;
    private Student student;
    private Admin admin;

    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    private static LoggedUser instance = null;
    private LoggedUser() {}

    public static LoggedUser getInstance() {

        if(instance==null) {
            instance = new LoggedUser();
        }
        return instance;
    }

    @Override
    public String toString() {
        return "LoggedUser{" +
                "account=" + account +
                ", buyer=" + student +
                ", seller=" + admin +
                ", role='" + role + '\'' +
                '}';
    }
}
