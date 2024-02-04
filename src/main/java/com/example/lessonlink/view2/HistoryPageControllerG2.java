package com.example.lessonlink.view2;

import com.example.lessonlink.controller.BookLessonController;
import com.example.lessonlink.exceptions.FailedResearchException;
import com.example.lessonlink.model.Teacher;
import com.example.lessonlink.model.observer.Observer;
import com.example.lessonlink.view1.bean.LessonBean;
import com.example.lessonlink.view1.bean.ReviewBean;
import com.example.lessonlink.view2.utility.ErrorPrinter;
import com.example.lessonlink.view2.utility.LinePrinter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

public class HistoryPageControllerG2 implements Observer {
    String toPrint;
    List<LessonBean> lessonBeans;
    private boolean sortByDate;
    BookLessonController bookLessonController;
    private int selectedTeacherIndex;
    Teacher teacherToObserv;

    public void setLessonBeans(List<LessonBean> lessonBeans) {
        this.lessonBeans = lessonBeans;
    }

    public void setBookLessonController(BookLessonController bookLessonController) {
        this.bookLessonController = bookLessonController;
    }

    public void showHistory() throws IOException {

        //sorting
        if (sortByDate) {
            lessonBeans.sort((LessonBean l1, LessonBean l2) -> l2.getLessonDateTime().compareTo(l1.getLessonDateTime()));
        } else {
            lessonBeans.sort(Comparator.comparing(LessonBean::getTeacherName));
        }

        int numberOfLessons = lessonBeans.size();


        //menu printing
        toPrint = "History:\n";
        LinePrinter.getInstance().print(toPrint);

        //lesson printing
        for (int i = 0; i < numberOfLessons; i++) {
            if (lessonBeans.get(i).getLessonDateTime().isAfter(LocalDateTime.now())) {
                LinePrinter.getInstance().print((i + 1) + ": " + lessonBeans.get(i).getTeacherName() + ", next lesson on: "
                    + lessonBeans.get(i).getLessonDate() + ". Has been confirmed: "
                    + lessonBeans.get(i).getIsConfirmed());
            } else {
                LinePrinter.getInstance().print((i + 1) + ": " + lessonBeans.get(i).getTeacherName() + ", had a lesson on: "
                    + lessonBeans.get(i).getLessonDate());
            }
        }

        //choice logic
        toPrint = "Press the number of the lesson to leave a review, * to switch sorting by Date/Name, 0 to return to homepage";
        LinePrinter.getInstance().print(toPrint);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String choice = "";
        try {
            choice = reader.readLine();
        } catch (IOException e) {
            //towrite
        }
        switch (choice) {
            case "1":
                selectedTeacherIndex = 0;
                leaveReview();
                break;
            case "2":
                selectedTeacherIndex = 1;
                leaveReview();
                break;
            case "3":
                selectedTeacherIndex = 2;
                leaveReview();
                break;
            case "4":
                selectedTeacherIndex = 3;
                leaveReview();
                break;
            case "5":
                selectedTeacherIndex = 4;
                leaveReview();
                break;
            case "*":
                sortByDate = !sortByDate;
                showHistory();
                break;
            case "0":
                StudentHomePageControllerG2 studentHomePageControllerG2 = new StudentHomePageControllerG2();
                try {
                    studentHomePageControllerG2.setName(bookLessonController.getAccountBean().getName());
                } catch (FailedResearchException e) {
                    e.printStackTrace();
                }
                break;
            default:
                int lessonIndex = Integer.parseInt(choice) - 1;
                if (lessonIndex >= 0 && lessonIndex < numberOfLessons) {
                    //to write
                }
                break;
        }

    }

    private void leaveReview() {
        toPrint = "Leaving a review for " + lessonBeans.get(selectedTeacherIndex).getTeacherName() + "\nActual rating: " +
            lessonBeans.get(selectedTeacherIndex).getAverageRating() + "/10\nInsert your rating (0-10): ";
        LinePrinter.getInstance().print(toPrint);
        ReviewBean reviewBean = new ReviewBean();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String rating = "";
        try {
            rating = reader.readLine();
        } catch (IOException e) {
            //not handled
        }
        reviewBean.setStars(Integer.parseInt(rating));
        toPrint = "Insert your comment (press enter to skip): ";
        LinePrinter.getInstance().print(toPrint);
        String comment = "";
        try {
            comment = reader.readLine();
        } catch (IOException e) {
            //not handled
        }
        if (!comment.isEmpty()) {
            reviewBean.setComment(comment);
        }
        reviewBean.setTeacherId(lessonBeans.get(selectedTeacherIndex).getTeacherId());
        if (reviewBean.validate()) {
            reviewBean.setDate(Date.valueOf(LocalDate.now()));
            try {
                //get Teacher
                teacherToObserv = bookLessonController.getTeacherById(reviewBean.getTeacherId());
                //attach
                teacherToObserv.attach(this);
                //insert review
                bookLessonController.insertReview(reviewBean, teacherToObserv);

                //end of review
                toPrint = "Review inserted!\n";
                LinePrinter.getInstance().print(toPrint);
                historyEnd();

            } catch (Exception e) {
                ErrorPrinter.getInstance().print(e.getMessage());
            }
        } else {
            toPrint = "The number you inserted is not valid";
            LinePrinter.getInstance().print(toPrint);
            leaveReview();
        }
    }

    private void historyEnd() throws IOException {
        toPrint = "Press 0 to return to homepage, anything else to close app\n";
        LinePrinter.getInstance().print(toPrint);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String choice = "";
        try {
            choice = reader.readLine();
        } catch (IOException e) {
            //not handled
        }
        if (choice.equals("0")) {
            StudentHomePageControllerG2 studentHomePageControllerG2 = new StudentHomePageControllerG2();
            try {
                studentHomePageControllerG2.setName(bookLessonController.getAccountBean().getName());
            } catch (FailedResearchException e) {
                e.printStackTrace();
            }
        } else {
            System.exit(0);
        }
    }

    @Override
    public void update() {
        //show new average rating (truncated)
        toPrint = "New teacher average rating is: " + (float) (Math.floor(teacherToObserv.getAverageRating() * 10) / 10) + "/10\n";
        LinePrinter.getInstance().print(toPrint);
    }
}
