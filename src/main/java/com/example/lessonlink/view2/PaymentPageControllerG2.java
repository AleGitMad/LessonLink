package com.example.lessonlink.view2;

import com.example.lessonlink.controller.BookLessonController;
import com.example.lessonlink.exceptions.FailedInsertException;
import com.example.lessonlink.exceptions.FailedResearchException;
import com.example.lessonlink.view1.bean.LessonBean;
import com.example.lessonlink.view2.utility.ErrorPrinter;
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

    public void selectPayment() throws IOException, FailedResearchException {
        toPrint = "Press:\n1 to pay now (PayPal)\n2 to pay later (cash)\n0 to go back to search page\n";
        LinePrinter.getInstance().print(toPrint);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String choice = "";
        try {
            choice = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch(choice){
            case("0"):
                SearchPageControllerG2 searchPageControllerG2 = new SearchPageControllerG2();
                searchPageControllerG2.search();
                break;
            case("1"):
                lessonBean.setIsPaid(true);
                try {
                    bookLessonController.insertLesson(lessonBean);
                } catch (FailedInsertException e) {
                    ErrorPrinter.getInstance().print(e.getMessage());
                }
                goBackToHomePage();
                break;
            case("2"):
                lessonBean.setIsPaid(false);
                try {
                    bookLessonController.insertLesson(lessonBean);
                } catch (FailedInsertException e) {
                    ErrorPrinter.getInstance().print(e.getMessage());
                }
                goBackToHomePage();
                break;
            default:
                break;
        }
    }

    private void goBackToHomePage() throws IOException, FailedResearchException {
        toPrint = "Lesson booked successfully!\nPress:\n0 to go back to homepage\nanything else to exit";
        LinePrinter.getInstance().print(toPrint);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String choice = "";
        try {
            choice = reader.readLine();
        } catch (IOException e) {
            //towrite
        }
        if (choice.equals("0")) {
            StudentHomePageControllerG2 studentHomePageControllerG2 = new StudentHomePageControllerG2();
            studentHomePageControllerG2.setName(bookLessonController.getAccountBean().getName());
        } else {
            System.exit(0);
        }
    }

}
