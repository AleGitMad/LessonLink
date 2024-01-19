package com.example.lessonlink.model.factories;

import com.example.lessonlink.model.User;
import com.example.lessonlink.model.Admin;
import com.example.lessonlink.model.dao.AdminDao;
import com.example.lessonlink.model.dao.UserDao;

public class AdminFactory extends UserFactory{
    @Override
    public User createUser() {
        return new Admin();
    }

    @Override
    public UserDao createDAO() {
        return new AdminDao();
    }
}
