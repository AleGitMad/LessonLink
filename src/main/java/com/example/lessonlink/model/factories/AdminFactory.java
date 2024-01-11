package com.example.lessonlink.model.factories;

import com.example.lessonlink.model.Account;
import com.example.lessonlink.model.Admin;
import com.example.lessonlink.model.dao.AccountDao;
import com.example.lessonlink.model.dao.AdminDao;

public class AdminFactory extends UserFactory{
    @Override
    public Account createAccount() {
        return new Admin();
    }

    @Override
    public AccountDao createDAO() {
        return new AdminDao();
    }
}
