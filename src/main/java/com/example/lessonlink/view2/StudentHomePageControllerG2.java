package com.example.lessonlink.view2;

import com.example.lessonlink.controller.BookLessonController;
import com.example.lessonlink.exceptions.FailedResearchException;
import com.example.lessonlink.view1.bean.LessonBean;
import com.example.lessonlink.view2.utility.ErrorPrinter;
import com.example.lessonlink.view2.utility.LinePrinter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class StudentHomePageControllerG2 {

    String toPrint;
    List<LessonBean> lessonBeans;
    BookLessonController bookLessonController = new BookLessonController();

    public void setName(String name) throws IOException, FailedResearchException {
        toPrint = "Welcome " + name + " to your homepage.\nPress:\n0 to book a lesson\n1 to check your history\n2 to logout";
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
                try {
                    lessonBeans = bookLessonController.getLessons();
                    if (!lessonBeans.isEmpty()) {
                        //online boolean set in the first teacherBean of the list
                        setHistoryPage(lessonBeans);
                    } else {
                        toPrint = "No lessons found.\n";
                        LinePrinter.getInstance().print(toPrint);
                        setName(name);
                    }
                } catch (FailedResearchException e) {
                    ErrorPrinter.getInstance().print(e.getMessage());
                }
                break;
            case("2"):
                HomeControllerG2 homeControllerG2 = new HomeControllerG2();
                homeControllerG2.start();
                break;
            default:
                break;
        }
    }

    public void setHistoryPage(List<LessonBean> lessonBeans) throws IOException, FailedResearchException {
        HistoryPageControllerG2 historyPageControllerG2 = new HistoryPageControllerG2();
        historyPageControllerG2.setLessonBeans(lessonBeans);
        historyPageControllerG2.setBookLessonController(bookLessonController);
        historyPageControllerG2.showHistory();
    }
}
