package com.example.lessonlink.view2;

import com.example.lessonlink.controller.AddTeacherController;
import com.example.lessonlink.view1.bean.ProfileTeacherBean;
import com.example.lessonlink.view2.utility.LinePrinter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class CreateTeacherControllerG2 {
    private String toPrint;

    public void insertTeacher(){
        toPrint = "Inserting teacher";
        LinePrinter.getInstance().print(toPrint);

        ProfileTeacherBean profileTeacherBean = fillBean();

        if(!profileTeacherBean.validate()){
            toPrint = "Insertion failed . . . repeating steps";
            LinePrinter.getInstance().print(toPrint);
            insertTeacher();
        }
        else{
            toPrint = "Great! Now we will evaluate the fare for you";
            LinePrinter.getInstance().print(toPrint);
            profileTeacherBean.setDecorations();
        }

        AddTeacherController addTeacherController = new AddTeacherController();
        addTeacherController.addTeacher(profileTeacherBean);

        toPrint = "The fare evaluated is: " + profileTeacherBean.fare() + " €/h";
        LinePrinter.getInstance().print(toPrint);

        toPrint = "Would you like to use the evaluated fare? Press 0 if you do, 1 otherwise";
        LinePrinter.getInstance().print(toPrint);

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            if(reader.readLine().equals("0")) {
                profileTeacherBean.setFare(profileTeacherBean.fare());
                if (!profileTeacherBean.fareValidate()){
                    toPrint = "Insertion failed . . . repeating steps";
                    LinePrinter.getInstance().print(toPrint);
                    insertTeacher();
                }
            }else{
                toPrint = "It seems you didnt use our Evaluator :( , type in the price you want to use";
                LinePrinter.getInstance().print(toPrint);

                profileTeacherBean.setFare(reader.readLine());
                if (!profileTeacherBean.fareValidate()){
                    toPrint = "Insertion failed . . . repeating steps";
                    LinePrinter.getInstance().print(toPrint);
                    insertTeacher();
                }
            }

            addTeacherController.confirmTeacher(profileTeacherBean);
        }catch(IOException e){
            LinePrinter.getInstance().print("Something went wrong");
            insertTeacher();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }

    private ProfileTeacherBean fillBean(){
        ProfileTeacherBean profileTeacherBean = new ProfileTeacherBean();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String error = "The number you insered is not valid";
        String[] subjects = {"Math", "History", "English", "Physics", "Geography"};

        toPrint = "Insert name...";
        LinePrinter.getInstance().print(toPrint);

        try {
            profileTeacherBean.setName(reader.readLine());

            toPrint = "Insert city: 1-Rome, 2-Milan, 3-Bergamo, 4-Tivoli...";
            LinePrinter.getInstance().print(toPrint);
            switch (reader.readLine()){
                case "1":
                    profileTeacherBean.setCity("Rome");
                    break;
                case "2":
                    profileTeacherBean.setCity("Milan");
                    break;
                case "3":
                    profileTeacherBean.setCity("Bergamo");
                    break;
                case "4":
                    profileTeacherBean.setCity("Tivoli");
                    break;
                default:
                    toPrint = error;
                    LinePrinter.getInstance().print(toPrint);
                    fillBean();
                    break;
            }

            toPrint = "Insert qualification: 1-High School, 2-Bachelor, 3-Master...";
            LinePrinter.getInstance().print(toPrint);
            switch (reader.readLine()){
                case "1":
                    profileTeacherBean.setQualification("High School");
                    break;
                case "2":
                    profileTeacherBean.setQualification("Bachelor");
                    break;
                case "3":
                    profileTeacherBean.setQualification("Master");
                    break;
                default:
                    toPrint = error;
                    LinePrinter.getInstance().print(toPrint);
                    fillBean();
                    break;
            }

            toPrint = "Insert subject 1: 1-Math, 2-History, 3-English, 4-Physics, 5-Geography...";
            LinePrinter.getInstance().print(toPrint);
            switch (reader.readLine()){
                case "1":
                    profileTeacherBean.setSubject1(subjects[0]);
                    break;
                case "2":
                    profileTeacherBean.setSubject1(subjects[1]);
                    break;
                case "3":
                    profileTeacherBean.setSubject1(subjects[2]);
                    break;
                case "4":
                    profileTeacherBean.setSubject1(subjects[3]);
                    break;
                case "5":
                    profileTeacherBean.setSubject1(subjects[4]);
                    break;
                default:
                    toPrint = error;
                    LinePrinter.getInstance().print(toPrint);
                    fillBean();
                    break;
            }

            toPrint = "Insert subject 2: 1-Math, 2-History, 3-English, 4-Physics, 5-Geography or 6-None...";
            LinePrinter.getInstance().print(toPrint);
            switch (reader.readLine()){
                case "1":
                    profileTeacherBean.setSubject2(subjects[0]);
                    break;
                case "2":
                    profileTeacherBean.setSubject2(subjects[1]);
                    break;
                case "3":
                    profileTeacherBean.setSubject2(subjects[2]);
                    break;
                case "4":
                    profileTeacherBean.setSubject2(subjects[3]);
                    break;
                case "5":
                    profileTeacherBean.setSubject2(subjects[4]);
                    break;
                case "6":
                    break;
                default:
                    toPrint = error;
                    LinePrinter.getInstance().print(toPrint);
                    fillBean();
                    break;
            }

            toPrint = "Insert subject 3: 1-Math, 2-History, 3-English, 4-Physics, 5-Geography or 6-None...";
            LinePrinter.getInstance().print(toPrint);
            switch (reader.readLine()){
                case "1":
                    profileTeacherBean.setSubject3(subjects[0]);
                    break;
                case "2":
                    profileTeacherBean.setSubject3(subjects[1]);
                    break;
                case "3":
                    profileTeacherBean.setSubject3(subjects[2]);
                    break;
                case "4":
                    profileTeacherBean.setSubject3(subjects[3]);
                    break;
                case "5":
                    profileTeacherBean.setSubject3(subjects[4]);
                    break;
                case "6":
                    break;
                default:
                    toPrint = error;
                    LinePrinter.getInstance().print(toPrint);
                    fillBean();
                    break;
            }

            toPrint = "Insert online: 1-Yes, 2-No...";
            LinePrinter.getInstance().print(toPrint);
            switch (reader.readLine()){
                case "1":
                    profileTeacherBean.setOnline("Yes");
                    break;
                case "2":
                    profileTeacherBean.setOnline("No");
                    break;
                default:
                    toPrint = error;
                    LinePrinter.getInstance().print(toPrint);
                    fillBean();
                    break;
            }

        }catch (IOException e){
            LinePrinter.getInstance().print("Insertion failed . . . repeating steps");
            insertTeacher();
        }
        return profileTeacherBean;
    }


}
