package com.example.lessonlink.controller;

import com.example.lessonlink.exceptions.FailedResearchException;
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

        Admin admin;
        Student student;
        LoggedUser loggedUser = new LoggedUser();
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
        AccountHomepageBean accountHomepageBean = new AccountHomepageBean();

        // sezione di codice polimorfo per istanziare oggetti di tipo student/admin e i rispettivi Dao

        if (role.equals("Admin")) {
            myFactory = UserFactory.getFactory(UserFactory.ROLE_ADMIN);
            admin = (Admin) myFactory.createUser();
            loggedUser.setAdmin(admin);
            loggedUser.setRole(role);

            if(fsAuth.equals(fs_on)){
                UserFSDao userDao = new UserFSDao();
                userDao.setUser(admin, loginBean.getEmail());
            }else {
                UserDao userDao = myFactory.createDAO();
                userDao.setUser(admin, loginBean.getEmail());
            }
            accountHomepageBean.setName(admin.getName());
            accountHomepageBean.setRole(role);

        } else {
            myFactory = UserFactory.getFactory(UserFactory.ROLE_STUDENT);
            student = (Student) myFactory.createUser();
            loggedUser.setStudent(student);
            loggedUser.setRole(role);

            if(fsAuth.equals(fs_on)){
                UserFSDao userDao = new UserFSDao();
                userDao.setUser(student, loginBean.getEmail());
            }else {
                UserDao userDao = myFactory.createDAO();
                userDao.setUser(student, loginBean.getEmail());
            }
            //TODO: check se polimorfismo vuole nome classe variabile uguale
            LessonDao lessonDao = new LessonDao();
            student.setLessons(lessonDao.findStudentLessons(loggedUser.getStudent().getUserId()));
            accountHomepageBean.setName(student.getName());
            accountHomepageBean.setRole(role);
        }

        return accountHomepageBean;
    }
}
