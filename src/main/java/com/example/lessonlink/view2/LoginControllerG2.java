package com.example.lessonlink.view2;

import com.example.lessonlink.controller.LoginController;
import com.example.lessonlink.view1.FxmlLoader;
import com.example.lessonlink.view1.bean.AccountBean;
import com.example.lessonlink.view1.bean.LoginBean;
import com.example.lessonlink.view2.utility.ErrorPrinter;
import com.example.lessonlink.view2.utility.LinePrinter;

import javax.security.auth.login.FailedLoginException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class LoginControllerG2 {
    private String toPrint;
    private LoginController loginController = new LoginController();

    public void start() {
        toPrint = "+++++++ LessonLink +++++++\nPress:\n0 to login\n1 to register\n";
        LinePrinter.getInstance().print(toPrint);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String role = "";
        try {
            role = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch(role){
            case"0":
                login();
                break;
            case"1":
                registration();
                break;
            default:
                toPrint = "The number you inserted is not valid";
                ErrorPrinter.getInstance().print(toPrint);
                start();
                break;
        }
    }


    private void registration() {

    }


    private void login() {
        LoginBean loginBean = new LoginBean();              // settaggio parametri LoginBean
        toPrint = "Logging in. . . Insert your credentials.\n Email: ";
        LinePrinter.getInstance().print(toPrint);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            loginBean.setEmail(reader.readLine());
            toPrint = "Password: ";
            LinePrinter.getInstance().print(toPrint);
            loginBean.setPassword(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
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
            } catch (SQLException | IOException e) {
                //not handled
            }
            //TODO: exception handling

        } else{
            ErrorPrinter.getInstance().print("Incorrect email or password. Try again");
        }
    }
    //TODO: login retry logic to add
}
