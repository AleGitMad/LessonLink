package com.example.lessonlink.view1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HomeControllerG {
    @FXML
    private Button studentloginButton;

    @FXML
    private Button adminloginButton;

    @FXML
    void setLoginPage() {
        FxmlLoader.setPage("login");
    }

}