package com.example.lessonlink.view1.bean;

import com.example.lessonlink.model.decorator.Teacher;

import java.util.List;

public class TeacherBean {
    List<Teacher> teachers;
    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }
}
