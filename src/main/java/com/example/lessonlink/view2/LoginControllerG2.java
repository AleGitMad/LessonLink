package com.example.lessonlink.view2;

import com.example.lessonlink.controller.LoginController;
import com.example.lessonlink.view1.bean.AccountBean;
import com.example.lessonlink.view1.bean.LoginBean;
import com.example.lessonlink.view2.utility.ErrorPrinter;
import com.example.lessonlink.view2.utility.LinePrinter;

import javax.security.auth.login.FailedLoginException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoginControllerG2 {
    private String toPrint;
    private LoginController loginController = new LoginController();

    public void resetCredentials() {
        toPrint = "Not implemented yet, stay tuned!\n\n";
        LinePrinter.getInstance().print(toPrint);
        HomeControllerG2 homeControllerG2 = new HomeControllerG2();
        homeControllerG2.start();
    }

    public void login() {
        LoginBean loginBean = new LoginBean();              // settaggio parametri LoginBean
        toPrint = "Logging in. . . Insert your credentials.\nEmail:";
        LinePrinter.getInstance().print(toPrint);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            loginBean.setEmail(reader.readLine());
            toPrint = "Password: ";
            LinePrinter.getInstance().print(toPrint);
            loginBean.setPassword(reader.readLine());
        } catch (IOException e) {
            //not handled
        }

        if(loginBean.validation()){

            try {
                AccountBean accountBean = loginController.login(loginBean);
                LinePrinter.getInstance().clearConsole();

                if (accountBean.getRole().equals("Student")) {
                    StudentHomePageControllerG2 studentHomePageControllerG2 = new StudentHomePageControllerG2();
                    studentHomePageControllerG2.setName(accountBean.getName());

                }else if(accountBean.getRole().equals("Admin")) {
                    AdminHomePageControllerG2 adminHomePageControllerG2 = new AdminHomePageControllerG2();
                    adminHomePageControllerG2.setName(accountBean.getName());
                }

            } catch (FailedLoginException e) {
                ErrorPrinter.getInstance().print(e.getMessage());
            } catch (Exception e) {
                //not handled
            }

        } else{
            ErrorPrinter.getInstance().print("Incorrect email or password. Try again");
            login();
        }
    }
}
