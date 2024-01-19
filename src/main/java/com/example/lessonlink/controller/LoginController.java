package com.example.lessonlink.controller;

import com.example.lessonlink.model.Account;
import com.example.lessonlink.model.Admin;
import com.example.lessonlink.model.LoggedUser;
import com.example.lessonlink.model.Student;
import com.example.lessonlink.model.dao.AccountFSDao;
import com.example.lessonlink.model.dao.LessonDao;
import com.example.lessonlink.model.dao.UserFSDao;
import com.example.lessonlink.model.dao.UserDao;
import com.example.lessonlink.model.factories.UserFactory;
import com.example.lessonlink.view1.bean.AccountHomepageBean;
import com.example.lessonlink.view1.bean.LoginBean;

import javax.security.auth.login.FailedLoginException;
import java.sql.SQLException;

public class LoginController {
    public AccountHomepageBean login(LoginBean loginBean) throws FailedLoginException, SQLException {

        Account account;
        Admin admin;
        Student student;
        String fsAuth = System.getenv("FS_AUTH");
        String role;
        final String fs_on = "on";

        if(fsAuth.equals(fs_on)){
            UserFSDao userDao = new UserFSDao();
            role = userDao.checkCredentials(loginBean.getEmail(), loginBean.getPassword());
        }else {
            UserDao userDao = new UserDao();
            role = userDao.checkCredentials(loginBean.getEmail(), loginBean.getPassword());
        }

        UserFactory myFactory;

        // sezione di codice polimorfo per istanziare oggetti di tipo student/admin e i rispettivi Dao

        if (role.equals("Admin")) {
            myFactory = UserFactory.getFactory(UserFactory.ROLE_ADMIN);
            admin = (Admin) myFactory.createAccount();
            LoggedUser.getInstance().setAccount(admin);
            LoggedUser.getInstance().setRole(role);

            if(fsAuth.equals(fs_on)){
                AccountFSDao accountFSDao = new AccountFSDao();
                accountFSDao.setAccount(admin, loginBean.getEmail());
            }else {
                AccountDao accountDao = myFactory.createDAO();
                accountDao.setAccount(admin, loginBean.getEmail());
            }
            LoggedUser.getInstance().setAdmin(admin);
            account = admin;

        } else {
            myFactory = UserFactory.getFactory(UserFactory.ROLE_STUDENT);
            student = (Student) myFactory.createAccount();
            LoggedUser.getInstance().setAccount(student);
            LoggedUser.getInstance().setRole(role);

            //if(fsAuth.equals(fs_on)){
                AccountFSDao accountFSDao = new AccountFSDao();
                accountFSDao.setAccount(student, loginBean.getEmail());
            /*
        }else {
                AccountDao accountDao = myFactory.createDAO();
                accountDao.setAccount(student, loginBean.getEmail());
            }
            */


            LoggedUser.getInstance().setStudent(student);
            student.setLessons(LessonDao.findBuyerOrders(LoggedUser.getInstance().getAccount().getUserId()));
            account = student;
        }

        AccountHomepageBean accountHomepageBean = new AccountHomepageBean();
        accountHomepageBean.setFirstName(account.getName());
        accountHomepageBean.setLastName(account.getLastName());
        accountHomepageBean.setRole(role);

        return accountHomepageBean;
    }
}
