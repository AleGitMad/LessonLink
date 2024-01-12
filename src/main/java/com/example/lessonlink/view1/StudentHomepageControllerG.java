package com.example.lessonlink.view1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StudentHomepageControllerG {
    @FXML
    private Label Username;

    public void setUsername(String username) {
        Username.setText(username);
    }

    @FXML
    void logout(ActionEvent event) {
        FxmlLoader.setPage("Homepage");
    }
    @FXML
    void history(ActionEvent event) {
        FxmlLoader.setPage("Homepage");
    }
}
