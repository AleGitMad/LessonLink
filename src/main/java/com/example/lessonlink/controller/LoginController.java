package com.example.lessonlink.controller;

import com.example.lessonlink.exceptions.FailedResearchException;
import com.example.lessonlink.model.Admin;
import com.example.lessonlink.model.LoggedUser;
import com.example.lessonlink.model.Student;
import com.example.lessonlink.model.dao.LessonDao;
import com.example.lessonlink.model.dao.UserFSDao;
import com.example.lessonlink.model.dao.UserDao;
import com.example.lessonlink.model.factories.UserFactory;
import com.example.lessonlink.view1.bean.AccountBean;
import com.example.lessonlink.view1.bean.LoginBean;

import javax.security.auth.login.FailedLoginException;
import java.sql.SQLException;

public class LoginController {
    public AccountBean login(LoginBean loginBean) throws FailedLoginException, SQLException {

        Admin admin;
        Student student;

        //usiamo una variabile d'ambiente per discriminare il dao da utilizzare per il login
        int fsAuth = Integer.parseInt(System.getenv("PROCESSOR_LEVEL"));
        String role;
        if(fsAuth < 12){
            UserFSDao userDao = new UserFSDao();
            role = userDao.checkCredentials(loginBean.getEmail(), loginBean.getPassword());
            System.out.println("sono in login controller");
        }else {
            UserDao userDao = new UserDao();
            role = userDao.checkCredentials(loginBean.getEmail(), loginBean.getPassword());
        }

        UserFactory myFactory;
        AccountBean accountBean = new AccountBean();

        // sezione di codice polimorfo per istanziare oggetti di tipo student/admin e i rispettivi Dao

        if (role.equals("Admin")) {
            myFactory = UserFactory.getFactory(UserFactory.ROLE_ADMIN);
            admin = (Admin) myFactory.createUser();
            LoggedUser.getInstance().setAdmin(admin);
            LoggedUser.getInstance().setRole(role);

            if(fsAuth < 12){
                UserFSDao userDao = new UserFSDao();
                userDao.setUser(admin, loginBean.getEmail());
            }else {
                UserDao userDao = myFactory.createDAO();
                userDao.setUser(admin, loginBean.getEmail());
            }
            accountBean.setName(admin.getName());
            accountBean.setRole(role);

        } else {
            myFactory = UserFactory.getFactory(UserFactory.ROLE_STUDENT);
            student = (Student) myFactory.createUser();
            LoggedUser.getInstance().setStudent(student);
            LoggedUser.getInstance().setRole(role);

            if(fsAuth < 12){
                UserFSDao userDao = new UserFSDao();
                userDao.setUser(student, loginBean.getEmail());
            }else {
                UserDao userDao = myFactory.createDAO();
                userDao.setUser(student, loginBean.getEmail());
            }
            //TODO: check se polimorfismo vuole nome classe variabile uguale
            LessonDao lessonDao = new LessonDao();
            accountBean.setName(student.getName());
            accountBean.setRole(role);
        }

        return accountBean;
    }
}
