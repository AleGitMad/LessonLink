package com.example.lessonlink.view1;

import com.example.lessonlink.controller.BookLessonController;
import com.example.lessonlink.view1.bean.LessonBean;
import com.example.lessonlink.view1.bean.TeacherBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class PaymentPageControllerG {

    @FXML
    private Pane redirectPane;
    @FXML
    private Pane confirmPane;
    @FXML
    private Pane errorPane;
    @FXML
    private Label errorLabel;

    private List<TeacherBean> teacherBeans;
    private LessonBean lessonBean;

    BookLessonController bookLessonController = new BookLessonController();

    public void setTeacherBeans(List<TeacherBean> teacherBeans) {
        this.teacherBeans = teacherBeans;
    }

    public void setLessonBean(LessonBean lessonBean) {
        this.lessonBean = lessonBean;
    }

    @FXML
    void payNow(ActionEvent event) {
        redirectPane.setVisible(true);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                //cancel timer if user press the button
                timer.cancel();
                //start lesson insertion
                try {
                    lessonBean.setIsPaid(true);
                    bookLessonController.insertLesson(lessonBean);
                } catch (Exception e) {
                    errorPane.setVisible(true);
                    errorLabel.setText(e.getMessage());
                }
                showConfirmPane(event);
            }
        };
        // set timer to 5 seconds
        timer.schedule(task, 5000);
    }

    @FXML
    void payLater(ActionEvent event) {
        try {
            lessonBean.setIsPaid(false);
            bookLessonController.insertLesson(lessonBean);
        } catch (Exception e) {
            errorPane.setVisible(true);
            errorLabel.setText(e.getMessage());
        }
        confirmPane.setVisible(true);
    }

    @FXML
    void showConfirmPane(ActionEvent event) {
        redirectPane.setVisible(false);
        confirmPane.setVisible(true);
    }

    @FXML
    void goBack() {
        FXMLLoader loader = FxmlLoader.setPage("ResultsPage");
        ResultsPageControllerG resultsPageControllerG = loader.getController();
        resultsPageControllerG.setTeacherBeans(teacherBeans);
        resultsPageControllerG.setResultsPage();
    }

    @FXML
    void logout() {
        FxmlLoader.setPage("Home");
    }

    @FXML
    void setHomePage() {
        FxmlLoader.setPage("StudentHomePage");
    }
}
