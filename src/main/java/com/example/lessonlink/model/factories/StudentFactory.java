package com.example.lessonlink.model.factories;

import com.example.lessonlink.model.Student;
import com.example.lessonlink.model.dao.AccountDao;
import com.example.lessonlink.model.dao.StudentDao;

public class StudentFactory extends UserFactory{
    @Override
    public Student createAccount() {
        return new Student();
    }

    @Override
    public AccountDao createDAO() {
        return new StudentDao();
    }
}
