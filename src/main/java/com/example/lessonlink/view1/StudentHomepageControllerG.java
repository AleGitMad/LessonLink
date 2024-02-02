package com.example.lessonlink.view1;

import com.example.lessonlink.controller.BookLessonController;
import com.example.lessonlink.exceptions.FailedResearchException;
import com.example.lessonlink.view1.bean.LessonBean;
import com.example.lessonlink.view1.bean.ResearchBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.util.List;

public class StudentHomepageControllerG {

    BookLessonController bookLessonController = new BookLessonController();
    List<LessonBean> lessonBeans;
    @FXML
    private Label userNameLabel;

    @FXML
    private Pane notFoundPane;
    @FXML
    private Pane errorPane;
    @FXML
    private Label errorLabel;

    public void setUserName(String userName) {
        userNameLabel.setText(userName);
    }

    @FXML
    public void initialize() {
        userNameLabel.setText(bookLessonController.getAccountBean().getName());
    }

    @FXML
    void logout() {
        FxmlLoader.setPage("Home");
    }

    @FXML
    void history(ActionEvent event) {
        try {
            lessonBeans = bookLessonController.getLessons();
            if (!lessonBeans.isEmpty()) {
                //online boolean set in the first teacherBean of the list
                setHistoryPage(lessonBeans);
            } else {
                notFoundPane.setVisible(true);
            }
        } catch (FailedResearchException e) {
            errorPane.setVisible(true);
            errorLabel.setText(e.getMessage());
        }
    }
    @FXML
    void bookLesson(ActionEvent event) {
        FxmlLoader.setPage("SearchPage");
    }

    @FXML
    void disableErrorPane(ActionEvent event) {
        errorPane.setVisible(false);
    }
    @FXML
    void disableNotFoundPane(ActionEvent event) {
        notFoundPane.setVisible(false);
    }

    void setHistoryPage(List<LessonBean> lessonBeans) {
        FXMLLoader loader = FxmlLoader.setPage("HistoryPage");
        HistoryPageControllerG historyPageControllerG = loader.getController();
        historyPageControllerG.setLessonBeans(lessonBeans);
        historyPageControllerG.setBookLessonController(bookLessonController);
        historyPageControllerG.setHistoryPage();
    }
}
