package com.example.lessonlink.view2;

import com.example.lessonlink.view2.utility.LinePrinter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StudentHomePageControllerG2 {

    String toPrint;

    public void setName(String name) throws IOException {
        toPrint = "Welcome " + name + " to your homepage.\nPress:\n0 to book a lesson\n1 to check your history\n2 to logout\n";
        LinePrinter.getInstance().print(toPrint);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String choice = "";
        try {
            choice = reader.readLine();
        } catch (IOException e) {
            //towrite
        }
        switch(choice){
            case("0"):
                break;
            case("1"):
                break;
            case("2"):
                break;
            default:
                break;
        }
    }
}
