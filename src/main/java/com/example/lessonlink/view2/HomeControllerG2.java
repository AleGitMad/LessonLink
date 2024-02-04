package com.example.lessonlink.view2;

import com.example.lessonlink.view2.utility.ErrorPrinter;
import com.example.lessonlink.view2.utility.LinePrinter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HomeControllerG2 {
    String toPrint;
    public void start() {
        toPrint = "+++++++ LessonLink +++++++\nPress:\n0 to login\n1 if you forgot your credentials";
        LinePrinter.getInstance().print(toPrint);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String role = "";
        LoginControllerG2 loginControllerG2 = new LoginControllerG2();
        try {
            role = reader.readLine();
        } catch (IOException e) {
            //not handled
        }
        switch(role){
            case"0":
                loginControllerG2.login();
                break;
            case"1":
                loginControllerG2.resetCredentials();
                break;
            default:
                toPrint = "The number you inserted is not valid";
                ErrorPrinter.getInstance().print(toPrint);
                start();
                break;
        }
    }
}
