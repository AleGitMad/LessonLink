package com.example.lessonlink.view2;

import com.example.lessonlink.controller.BookLessonController;
import com.example.lessonlink.exceptions.FailedResearchException;
import com.example.lessonlink.view1.bean.ResearchBean;
import com.example.lessonlink.view1.bean.TeacherBean;
import com.example.lessonlink.view2.utility.ErrorPrinter;
import com.example.lessonlink.view2.utility.LinePrinter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class SearchPageControllerG2 {
    private String toPrint;
    BookLessonController bookLessonController = new BookLessonController();
    List<TeacherBean> teacherBeans;
    public void search() throws FailedResearchException, IOException {
        toPrint = "What do you want to study?\n";
        LinePrinter.getInstance().print(toPrint);
        ResearchBean researchBean = new ResearchBean();
        selectSubject(researchBean);
        selectWhere(researchBean);
        try {
            teacherBeans = bookLessonController.search(researchBean);
            if (!teacherBeans.isEmpty()) {
                teacherBeans.getFirst().setIsOnline(researchBean.getIsOnline());
                ResultsPageControllerG2 resultsPageControllerG2 = new ResultsPageControllerG2();
                resultsPageControllerG2.setTeacherBeans(teacherBeans);
                resultsPageControllerG2.showResults();
            } else {
                toPrint = "No results found\n";
                LinePrinter.getInstance().print(toPrint);
                search();
            }
        } catch (FailedResearchException e) {
            ErrorPrinter.getInstance().print(e.getMessage());
        }
    }

    private void selectSubject(ResearchBean researchBean) throws IOException, FailedResearchException {
        toPrint = "Select subject:\n1: Math\n2: History\n3: English\n4: Physics\n5: Geography\n0: Return to homepage";
        LinePrinter.getInstance().print(toPrint);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String role = "";
        try {
            role = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch(role){
            case"1":
                researchBean.setSubject("Math");
                break;
            case"2":
                researchBean.setSubject("History");
                break;
            case"3":
                researchBean.setSubject("English");
                break;
            case"4":
                researchBean.setSubject("Physics");
                break;
            case"5":
                researchBean.setSubject("Geography");
                break;
            case"0":
                StudentHomePageControllerG2 studentHomePageControllerG2 = new StudentHomePageControllerG2();
                studentHomePageControllerG2.setName(bookLessonController.getAccountBean().getName());
                break;
            default:
                toPrint = "The number you inserted is not valid";
                ErrorPrinter.getInstance().print(toPrint);
                selectSubject(researchBean);
                break;
        }
    }
    private void selectWhere(ResearchBean researchBean) {
        toPrint = "Select where the lesson will be:\n1: Rome\n2: Milan\n3: Bergamo\n4: Tivoli\n5: Online\n";
        LinePrinter.getInstance().print(toPrint);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String role = "";
        try {
            role = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (role) {
            case "1":
                researchBean.setWhere("Rome");
                break;
            case "2":
                researchBean.setWhere("Milan");
                break;
            case "3":
                researchBean.setWhere("Bergamo");
                break;
            case "4":
                researchBean.setWhere("Tivoli");
                break;
            case "5":
                researchBean.setIsOnline();
                break;
            default:
                toPrint = "The number you inserted is not valid";
                ErrorPrinter.getInstance().print(toPrint);
                selectWhere(researchBean);
                break;
        }
    }
}
