package com.example.lessonlink.view1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StudentHomepageControllerG {
    @FXML
    private Label userNameLabel;

    public void setUserName(String userName) {
        userNameLabel.setText(userName);
    }

    @FXML
    void logout(ActionEvent event) {
        FxmlLoader.setPage("Home");
    }
    @FXML
    void history(ActionEvent event) {
        FxmlLoader.setPage("Home");
    }

    @FXML
    void bookLesson(ActionEvent event) {
        FxmlLoader.setPage("SearchPage");
    }
}
