package com.example.lessonlink.view1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import java.util.Objects;

public class HomeControllerG {
    @FXML
    private Button studentLoginButton;

    @FXML
    private Button adminLoginButton;

    @FXML
    void setLoginPage(ActionEvent event) {
        FXMLLoader loader = FxmlLoader.setPage("login");
        LoginControllerG loginControllerG = loader.getController();
        if (Objects.equals(((Button) event.getSource()).getId(), "studentLoginButton"))
            loginControllerG.setAccountType(0);
        else
            loginControllerG.setAccountType(1);
    }

}