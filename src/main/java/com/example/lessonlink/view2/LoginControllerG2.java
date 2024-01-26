package com.example.lessonlink.view2;

import com.example.lessonlink.controller.LoginController;
import com.example.lessonlink.view2.utility.ErrorPrinter;
import com.example.lessonlink.view2.utility.LinePrinter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoginControllerG2 {
    private String toPrint;
    private LoginController loginController = new LoginController();

    public void start() {
        toPrint = "LOGINPAGE\nPress 0 if you want to login, 1 if you want to register: ";
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
                toPrint = "The number you insered is not valid";
                ErrorPrinter.getInstance().print(toPrint);
                start();
                break;
        }
    }


    private void registration() {

    }


    private void login() {

    }
}
