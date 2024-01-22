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
    TeacherBean teacherBean;

    @FXML
    public void initialize() {
        subjectBox.getItems().addAll("Math", "History", "English", "Physics", "Geography");
        whereBox.getItems().addAll("Rome", "Milan", "Bergamo", "Tivoli", "Online");
    }

    @FXML
    void search() {
        ResearchBean researchBean = new ResearchBean(subjectBox.getValue());
        if (researchBean.validate()) {
            if (whereBox.getValue().equals("Online")) {
                researchBean.setIsOnline();
            } else {
                researchBean.setWhere(whereBox.getValue());
            }
                try {
                    teacherBean = bookLessonController.search(researchBean);
                    if (!teacherBean.isEmpty()) {
                        setResultsPage(teacherBean);
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

    private void setResultsPage(TeacherBean teacherBean) {
        FXMLLoader loader = FxmlLoader.setPage("ResultsPage");
        ResultsPageControllerG resultsPageControllerG = loader.getController();
        resultsPageControllerG.setResultsPage(teacherBean);
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