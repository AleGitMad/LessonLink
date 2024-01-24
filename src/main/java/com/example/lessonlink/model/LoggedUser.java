package com.example.lessonlink.model;

public class LoggedUser {
    private Student student;
    private Admin admin;

    private String role;

    private LoggedUser() {}

    //singleton
    public static LoggedUser getInstance() {

        if(instance==null) {
            instance = new LoggedUser();
        }
        return instance;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    @Override
    public String toString() {
        return "LoggedUser{" +
                ", student=" + student +
                ", admin=" + admin +
                ", role='" + role + '\'' +
                '}';
    }
}
