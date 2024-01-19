package com.example.lessonlink.controller;

import com.example.lessonlink.exceptions.FailedResearchException;
import com.example.lessonlink.model.User;
import com.example.lessonlink.model.Admin;
import com.example.lessonlink.model.LoggedUser;
import com.example.lessonlink.model.Student;
import com.example.lessonlink.model.dao.LessonDao;
import com.example.lessonlink.model.dao.UserFSDao;
import com.example.lessonlink.model.dao.UserDao;
import com.example.lessonlink.model.factories.UserFactory;
import com.example.lessonlink.view1.bean.AccountHomepageBean;
import com.example.lessonlink.view1.bean.LoginBean;

import javax.security.auth.login.FailedLoginException;
import java.sql.SQLException;

public class LoginController {
    public AccountHomepageBean login(LoginBean loginBean) throws FailedLoginException, SQLException, FailedResearchException {

        User user;
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
            admin = (Admin) myFactory.createUser();
            LoggedUser.getInstance().setUser(admin);
            LoggedUser.getInstance().setRole(role);

            if(fsAuth.equals(fs_on)){
                UserFSDao userDao = new UserFSDao();
                userDao.setUser(admin, loginBean.getEmail());
            }else {
                UserDao userDao = myFactory.createDAO();
                userDao.setUser(admin, loginBean.getEmail());
            }
            LoggedUser.getInstance().setAdmin(admin);
            user = admin;

        } else {
            myFactory = UserFactory.getFactory(UserFactory.ROLE_STUDENT);
            student = (Student) myFactory.createUser();
            LoggedUser.getInstance().setUser(student);
            LoggedUser.getInstance().setRole(role);

            if(fsAuth.equals(fs_on)){
                UserFSDao userDao = new UserFSDao();
                userDao.setUser(student, loginBean.getEmail());
            }else {
                UserDao userDao = myFactory.createDAO();
                userDao.setUser(student, loginBean.getEmail());
            }


            LoggedUser.getInstance().setStudent(student);
            LessonDao lessonDao = new LessonDao();
            student.setLessons(lessonDao.findStudentLessons(LoggedUser.getInstance().getUser().getUserId()));
            user = student;
        }

        AccountHomepageBean accountHomepageBean = new AccountHomepageBean();
        accountHomepageBean.setName(user.getName());
        accountHomepageBean.setRole(role);

        return accountHomepageBean;
    }
}
