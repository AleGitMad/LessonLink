package com.example.lessonlink.model;

import java.util.List;

public class Student extends User {
    private List<Lesson> lessons;

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

}
