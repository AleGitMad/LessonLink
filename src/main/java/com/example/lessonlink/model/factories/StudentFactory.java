package com.example.lessonlink.model.factories;

import com.example.lessonlink.model.Student;
import com.example.lessonlink.model.dao.StudentDao;
import com.example.lessonlink.model.dao.UserDao;

public class StudentFactory extends UserFactory{
    @Override
    public Student createUser() {
        return new Student();
    }

    @Override
    public UserDao createDAO() {
        return new StudentDao();
    }
}
