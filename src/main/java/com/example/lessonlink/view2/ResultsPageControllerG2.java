package com.example.lessonlink.view2;

import com.example.lessonlink.controller.BookLessonController;
import com.example.lessonlink.exceptions.FailedResearchException;
import com.example.lessonlink.view1.bean.LessonBean;
import com.example.lessonlink.view1.bean.TeacherBean;
import com.example.lessonlink.view2.utility.ErrorPrinter;
import com.example.lessonlink.view2.utility.LinePrinter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class ResultsPageControllerG2 {
    private List<TeacherBean> teacherBeans;
    private int selectedTeacherId;
    private boolean isOnline;
    BookLessonController bookLessonController = new BookLessonController();
    String toPrint;
    boolean sortByRating = true;

    public void setTeacherBeans(List<TeacherBean> teacherBeans) {
        this.teacherBeans = teacherBeans;
        isOnline = teacherBeans.getFirst().getIsOnline();
    }

    public void showResults() throws IOException, FailedResearchException {

        //sorting
        teacherBeans.sort((TeacherBean t1, TeacherBean t2) -> {
            if (sortByRating) {
                if (!t1.getTeacherHasReview()) return 1;
                if (!t2.getTeacherHasReview()) return -1;
                return Double.compare(t2.getTeacherAverageRating(), t1.getTeacherAverageRating());
            } else {
                return Double.compare(t1.getTeacherAverageRating(), t2.getTeacherAverageRating());
            }
        });

        //menu printing
        toPrint = "Results (press * to switch sorting by Rating/Fare, 0 to return to homepage):\n";
        LinePrinter.getInstance().print(toPrint);

        //teacher printing
        for (int i = 0; i < teacherBeans.size(); i++) {
            LinePrinter.getInstance().print((i + 1) + ": " + teacherBeans.get(i).getTeacherName() + ", "
                    + teacherBeans.get(i).getTeacherFare() + " €/h, "
                    + teacherBeans.get(i).getTeacherAverageRating() + "/10");
        }

        //choice logic
        LessonBean lessonBean = new LessonBean();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String role = "";
        try {
            role = reader.readLine();
        } catch (IOException e) {
            //not handled
        }
        switch(role){
            case"1":
                selectedTeacherId = teacherBeans.get(0).getTeacherId();
                selectDate(lessonBean);
                break;
            case"2":
                selectedTeacherId = teacherBeans.get(1).getTeacherId();
                selectDate(lessonBean);
                break;
            case"3":
                selectedTeacherId = teacherBeans.get(2).getTeacherId();
                selectDate(lessonBean);
                break;
            case"4":
                selectedTeacherId = teacherBeans.get(3).getTeacherId();
                selectDate(lessonBean);
                break;
            case"5":
                selectedTeacherId = teacherBeans.get(4).getTeacherId();
                selectDate(lessonBean);
                break;
            case"*":
                sortByRating = !sortByRating;
                showResults();
                break;
            case"0":
                StudentHomePageControllerG2 studentHomePageControllerG2 = new StudentHomePageControllerG2();
                studentHomePageControllerG2.setName(bookLessonController.getAccountBean().getName());
                break;
            default:
                toPrint = "The number you inserted is not valid";
                ErrorPrinter.getInstance().print(toPrint);
                showResults();
                break;
        }
    }

    private void selectDate(LessonBean lessonBean) {
        lessonBean.setTeacherId(selectedTeacherId);
        toPrint = "Insert date (yyyy-mm-dd): ";
        LinePrinter.getInstance().print(toPrint);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String dateString = "";
        try {
            dateString = reader.readLine();
            LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            //If date is successfully parsed, set in lessonBean
            lessonBean.setLessonDate(date);
            selectTime(lessonBean);
        } catch (IOException e) {
            //not handled
        } catch (DateTimeParseException e) {
            toPrint = "The date you inserted is not valid. Please use the format yyyy-mm-dd.";
            ErrorPrinter.getInstance().print(toPrint);
            selectDate(lessonBean);
        }
    }

    private void selectTime(LessonBean lessonBean) {
        toPrint = "Insert time (hh:mm): ";
        LinePrinter.getInstance().print(toPrint);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String timeString = "";
        try {
            timeString = reader.readLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime time = LocalTime.parse(timeString, formatter);
            //If time is successfully parsed, convert it to a string and set in lessonBean
            String formattedTime = formatter.format(time);
            lessonBean.setLessonTime(formattedTime);
            checkSlotAvailability(lessonBean);
        } catch (IOException e) {
            //not handled
        } catch (DateTimeParseException e) {
            toPrint = "The time you inserted is not valid. Please use the format hh:mm.";
            ErrorPrinter.getInstance().print(toPrint);
            selectTime(lessonBean);
        }
    }

    private void checkSlotAvailability(LessonBean lessonBean) {
        if (lessonBean.validate()){
            lessonBean.setLessonDateTimeFrom(java.sql.Date.valueOf(lessonBean.getLessonDate()), lessonBean.getLessonTime());
        } else {
            toPrint = "The date or time you inserted is not valid or it's not in the future. Please try again.";
            ErrorPrinter.getInstance().print(toPrint);
            selectDate(lessonBean);
        }
        try {
            if (bookLessonController.checkSlotAvailability(lessonBean)) {
                lessonBean.setIsOnline(isOnline);
                PaymentPageControllerG2 paymentPageControllerG2 = new PaymentPageControllerG2();
                paymentPageControllerG2.setLessonBean(lessonBean);
                paymentPageControllerG2.selectPayment();
            } else {
                toPrint = "The slot you selected is not available. Please select another slot.";
                ErrorPrinter.getInstance().print(toPrint);
                selectDate(lessonBean);
            }
        } catch (FailedResearchException e) {
            ErrorPrinter.getInstance().print(e.getMessage());
            selectDate(lessonBean);
        } catch (IOException e) {
            //not handled
        }
    }

}