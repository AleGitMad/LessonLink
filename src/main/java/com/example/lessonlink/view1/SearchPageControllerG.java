package com.example.lessonlink.view1;

import com.example.lessonlink.controller.BookLessonController;
import com.example.lessonlink.exceptions.FailedResearchException;
import com.example.lessonlink.view1.bean.ResearchBean;
import com.example.lessonlink.view1.bean.TeacherBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.util.List;

public class SearchPageControllerG {
    @FXML
    private ComboBox<String> subjectBox;
    @FXML
    private ComboBox<String> whereBox;
    @FXML
    private Button searchButton;
    @FXML
    private Pane notFoundPane;
    @FXML
    private Pane errorPane;
    @FXML
    private Label errorLabel;

    @FXML
    private Label userNameLabel;
    @FXML
    private Button newSearchButton;

    BookLessonController bookLessonController = new BookLessonController();

    List<TeacherBean> teacherBeans;
    TeacherBean teacherBean;

    @FXML
    public void initialize() {
        subjectBox.getItems().addAll("Math", "History", "English", "Physics", "Geography");
        whereBox.getItems().addAll("Rome", "Milan", "Bergamo", "Tivoli", "Online");
    }

    @FXML
    void search() {
        ResearchBean researchBean = new ResearchBean(subjectBox.getValue(), whereBox.getValue());
        if (researchBean.validate()) {
            if (whereBox.getValue().equals("Online")) {
                researchBean.setIsOnline();
            }
                try {
                    teacherBeans = bookLessonController.search(researchBean);
                    if (!teacherBeans.isEmpty()) {
                        setResultsPage(teacherBeans);
                    } else {
                        notFoundPane.setVisible(true);
                    }
                } catch (FailedResearchException e) {
                    errorPane.setVisible(true);
                    errorLabel.setText(e.getMessage());
                }
        }
        else {
            errorPane.setVisible(true);
            errorLabel.setText("Please select all the fields");
        }
    }

    private void setResultsPage(List<TeacherBean> teacherBeans) {
        FXMLLoader loader = FxmlLoader.setPage("ResultsPage");
        ResultsPageControllerG resultsPageControllerG = loader.getController();
        resultsPageControllerG.setTeacherBeans(teacherBeans);
        resultsPageControllerG.setResultsPage();
    }

    @FXML
    void newSearch(ActionEvent event) {
        notFoundPane.setVisible(false);
    }
    @FXML
    void newSearchE(ActionEvent event) {
        errorPane.setVisible(false);
    }
    @FXML
    void logout() {
        FxmlLoader.setPage("Home");
    }
    @FXML
    void setHomePage() {
        FxmlLoader.setPage("StudentHomepage");
    }
}