package com.example.lessonlink.view1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HomeControllerG {
    @FXML
    private Button studentLoginButton;

    @FXML
    private Button adminLoginButton;

    @FXML
    void setLoginPage() {
        FxmlLoader.setPage("login");
    }

    //TODO: distinguish between student and admin login

}