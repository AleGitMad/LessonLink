package com.example.lessonlink.view1.bean;

import com.example.lessonlink.model.decorator.Teacher;

import java.util.List;

public class ResultsBean {
    List<Teacher> teachers;
    public List<Teacher> getTeachers() {
        return teachers;
    }
    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public boolean isEmpty() {
        return teachers.isEmpty();
    }
}
