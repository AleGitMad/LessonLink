package com.example.lessonlink.view1.bean;

import java.util.Objects;

public class ProfileTeacherBean {
    private boolean[] decorations;
    private String name;
    private String city;
    private String qualification;
    private String subject1;
    private String subject2;
    private String subject3;
    private String online;
    private String fare;

    public void setName(String text) {
        name = text;
    }
    public void setCity(String value) {
        city = value;
    }
    public void setQualification(String value) {
        qualification = value;
    }
    public void setSubject1(String value) {
        subject1 = value;
    }
    public void setSubject2(String value) {
        subject2 = value;
    }
    public void setSubject3(String value) {
        subject3 = value;
    }
    public void setOnline(String value) {
        online = value;
    }

    public String name() {
        return name;
    }
    public String city() {
        return city;
    }
    public String qualification() {
        return qualification;
    }
    public String subject1() {
        return subject1;
    }
    public String subject2() {
        return subject2;
    }
    public String subject3() {
        return subject3;
    }
    public String online() {
        return online;
    }
    public String fare() {
        return fare;
    }


    public String getCityC() {
        return city;
    }
    public String getQualificationC() {
        return qualification;
    }
    public String getSubject1C() {
        return subject1;
    }
    public String getSubject2C() {
        return subject2;
    }
    public String getSubject3C() {
        return subject3;
    }
    public Boolean getOnlineC() {
        return online.equals("Yes");
    }
    public int getFareC() {
        return Integer.parseInt(fare);
    }


    public boolean validate() {
        boolean basicCheck = Objects.nonNull(name) && !name.isEmpty() && name.length() < 45 && Objects.nonNull(city) && Objects.nonNull(qualification)
                && Objects.nonNull(subject1) && Objects.nonNull(online);

        boolean subjectsCheck = true;
        if (Objects.nonNull(subject1)) {
            if (Objects.nonNull(subject2)) {
                subjectsCheck = !subject1.equals(subject2);
            }
            if (Objects.nonNull(subject3)) {
                subjectsCheck = subjectsCheck && !subject1.equals(subject3);
            }
            if (Objects.nonNull(subject2) && Objects.nonNull(subject3)) {
                subjectsCheck = subjectsCheck && !subject2.equals(subject3);
            }
        }

        return basicCheck && subjectsCheck;
    }

    public void setFare(String text) {
        fare = text;
    }

    public boolean fareValidate() {
        return fare != null && fare.matches("\\d+") && fare.length() < 5;
    }

    public void setDecorations() {
        decorations = new boolean[13];
        if (Objects.equals(subject1, "Math")) decorations[0] = true;
        if (Objects.equals(subject1, "Physics")) decorations[1] = true;
        if (Objects.equals(subject1, "English")) decorations[2] = true;
        if (Objects.equals(subject1, "History")) decorations[3] = true;
        if (Objects.equals(subject1, "Geography")) decorations[4] = true;
        if (Objects.equals(city, "Rome")) decorations[5] = true;
        if (Objects.equals(city, "Milan")) decorations[6] = true;
        if (Objects.equals(city, "Bergamo")) decorations[7] = true;
        if (Objects.equals(city, "Tivoli")) decorations[8] = true;
        if (Objects.equals(qualification, "High School")) decorations[9] = true;
        if (Objects.equals(qualification, "Bachelor")) decorations[10] = true;
        if (Objects.equals(qualification, "Master")) decorations[11] = true;
        if (Objects.equals(online, "Yes")) decorations[12] = true;
    }

    public boolean[] getDecorations() {
        return decorations;
    }
}
