package com.example.lessonlink.controller;

import com.example.lessonlink.model.Account;
import com.example.lessonlink.model.Admin;
import com.example.lessonlink.model.Student;
import com.example.lessonlink.model.dao.AccountDao;
import com.example.lessonlink.model.dao.LoginFSDao;
import com.example.lessonlink.model.factories.UserFactory;
import com.example.lessonlink.view1.bean.AccountHomepageBean;
import com.example.lessonlink.view1.bean.LoginBean;

import javax.security.auth.login.FailedLoginException;
import java.sql.SQLException;
import java.util.Objects;

public class LoginController {
    public AccountHomepageBean login(LoginBean loginBean) throws FailedLoginException, SQLException {

        Account account;

        Admin admin;

        Student student;

        //ResearchDAO researchDAO = new ResearchDAO();

        String fsAuth = System.getenv("FS_AUTH");

        String role;

        final String fs_on = "on";

        //if(fsAuth.equals(fs_on)){

            LoginFSDao loginDao = new LoginFSDao();

            role = loginDao.checkCredentials(loginBean.getEmail(), loginBean.getPassword());

        //}else {
            //TODO: loginDAO
            /*
            LoginDao loginDao = new LoginDao(); // creazione loginDao per trovare role

            role = loginDao.checkCredentials(loginBean.getEmail(), loginBean.getPassword());
            */
        //}


        UserFactory myFactory;

        // sezione di codice polimorfo per istanziare oggetti di tipo student/admin e i rispettivi Dao

        if (role.equals("Admin")) {
            myFactory = UserFactory.getFactory(UserFactory.ROLE_ADMIN);
            admin = (Admin) myFactory.createAccount();
            LoggedUser.getInstance().setAccount(admin);
            LoggedUser.getInstance().setRole(role);

            if(fsAuth.equals(fs_on)){
                AccountFileSystemDao accountFileSystemDao = new AccountFileSystemDao();
                accountFileSystemDao.setAccount(admin, loginBean.getEmail());
            }else {
                AccountDao accountDao = myFactory.createDAO();
                accountDao.setAccount(admin, loginBean.getEmail());
            }

            LoggedUser.getInstance().setSeller(admin);
            account = admin;

        } else {
            myFactory = UserFactory.getFactory(UserFactory.ROLE_STUDENT);
            student = (Student) myFactory.createAccount();
            LoggedUser.getInstance().setAccount(student);
            LoggedUser.getInstance().setRole(role);

            if(fsAuth.equals(fs_on)){
                AccountFileSystemDao accountFileSystemDao = new AccountFileSystemDao();
                accountFileSystemDao.setAccount(student, loginBean.getEmail());
            }else {
                AccountDao accountDao = myFactory.createDAO();
                accountDao.setAccount(student, loginBean.getEmail());
            }


            LoggedUser.getInstance().setBuyer(student);
            student.setFavourites(researchDAO.findFavoriteAds(LoggedUser.getInstance().getAccount().getIdAccount()));
            student.setOrders(researchDAO.findBuyerOrders(LoggedUser.getInstance().getAccount().getIdAccount()));
            account = student;
        }

        AccountHomepageBean accountHomepageBean = new AccountHomepageBean();
        accountHomepageBean.setFirstName(account.getFirstName());
        accountHomepageBean.setLastName(account.getLastName());
        accountHomepageBean.setRole(role);

        return accountHomepageBean;
    }
}
