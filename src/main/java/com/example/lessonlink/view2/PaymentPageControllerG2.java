package com.example.lessonlink.view2;

import com.example.lessonlink.controller.BookLessonController;
import com.example.lessonlink.view1.bean.LessonBean;
import com.example.lessonlink.view2.utility.LinePrinter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PaymentPageControllerG2 {
    String toPrint;
    BookLessonController bookLessonController = new BookLessonController();
    private LessonBean lessonBean;

    public void setLessonBean(LessonBean lessonBean) {
        this.lessonBean = lessonBean;
    }

    public void selectPayment() throws IOException{
        toPrint = "Press:\n1 to pay now (PayPal)\n2 to pay later (cash)\n0 to go back to search page\n";
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
                SearchPageControllerG2 searchPageControllerG2 = new SearchPageControllerG2();
                searchPageControllerG2.search();
                break;
            case("1"):
                lessonBean.setIsPaid(true);
                bookLessonController.insertLesson(lessonBean);
                goBackToHomePage();
                break;
            case("2"):
                lessonBean.setIsPaid(false);
                bookLessonController.insertLesson(lessonBean);
                goBackToHomePage();
                break;
            default:
                break;
        }
    }

    private void goBackToHomePage() throws IOException{
        toPrint = "Lesson booked successfully!\nPress:\n0 to go back to homepage\n";
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
                StudentHomePageControllerG2 studentHomePageControllerG2 = new StudentHomePageControllerG2();
                studentHomePageControllerG2.setName(bookLessonController.getAccountBean().getName());
                break;
            default:
                break;
        }
    }

}
