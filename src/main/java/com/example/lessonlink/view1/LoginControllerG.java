package com.example.lessonlink.view1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class LoginControllerG {
    @FXML
    private Button loginButton;

    @FXML
    void setLoginPage() {
        FxmlLoader.setPage("studentPage");
    }

}