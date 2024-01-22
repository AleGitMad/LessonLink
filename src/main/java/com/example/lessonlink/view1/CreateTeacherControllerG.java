package com.example.lessonlink.view1;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

//This controller will control the CreateTeacher.fxml and EvaluatorPage.fxml files
public class CreateTeacherControllerG {
    @FXML
    private Label username;
    @FXML
    private ComboBox<String> subjectBox1;
    @FXML
    private ComboBox<String> subjectBox2;
    @FXML
    private ComboBox<String> subjectBox3;
    @FXML
    private ComboBox<String> cityBox;
    @FXML
    private ComboBox<String> qualificationBox;
    @FXML
    private ComboBox<String> onlineBox;
    @FXML
    private TextField nameField;


    @FXML
    public void initialize() {
        subjectBox1.getItems().addAll("Math", "History", "English", "Physics", "Geography");
        subjectBox2.getItems().addAll("Math", "History", "English", "Physics", "Geography");
        subjectBox3.getItems().addAll("Math", "History", "English", "Physics", "Geography");
        cityBox.getItems().addAll("Rome", "Milan", "Bergamo", "Tivoli");
        qualificationBox.getItems().addAll("High School", "Bachelor", "Master");
        onlineBox.getItems().addAll("Yes", "No");
    }

    @FXML
    void logout() {
        FxmlLoader.setPage("Home");
    }
//    @FXML
//    public void setUserName(String userName) {
//        username.setText(userName);
//    }
    @FXML
    void setHomePage(ActionEvent event) {
        FxmlLoader.setPage("AdminHomepage");
    }
    @FXML
    void goToEvaluatorPage(ActionEvent event) {
        FxmlLoader.setPage("EvaluatorPage");
    }
}
