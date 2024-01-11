package com.example.lessonlink.model.factories;

import com.example.lessonlink.model.Account;
import com.example.lessonlink.model.dao.AccountDao;

public abstract class UserFactory {
    public static final String ROLE_ADMIN = "Admin";

    public static final String ROLE_STUDENT = "Student";

    public static UserFactory getFactory(String s) {
        if(s.equals(ROLE_ADMIN)){
            return new AdminFactory();

        }else {
            return new StudentFactory();
        }
    }

    public abstract Account createAccount();

    public abstract AccountDao createDAO();
}
