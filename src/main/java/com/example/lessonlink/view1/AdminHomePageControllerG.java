package com.example.lessonlink.view1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AdminHomePageControllerG {
    @FXML
    private Label Username;

    public void setUsername(String username) {
        Username.setText(username);
    }

    @FXML
    void logout() {
        FxmlLoader.setPage("Home");
    }
    @FXML
    void addTeacher(ActionEvent event) {
        FxmlLoader.setPage("CreateTeacherProfile");
    }
    @FXML
    void activeBookings(ActionEvent event) {
        FxmlLoader.setPage("Home");
    }
}
