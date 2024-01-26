package com.example.lessonlink.view1;

import com.example.lessonlink.controller.LoginController;
import com.example.lessonlink.exceptions.FailedResearchException;
import com.example.lessonlink.view1.bean.AccountBean;
import com.example.lessonlink.view1.bean.LoginBean;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.security.auth.login.FailedLoginException;
import java.sql.SQLException;

public class LoginControllerG {
    @FXML
    private Button loginButton;
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
    @FXML
    private Label incorrectLabel;

    LoginController loginController = new LoginController(); // istanziamo il controller applicativo

    FXMLLoader fxmlLoader;

    AccountBean accountBean;

    @FXML
    void setHomePage() {
        FxmlLoader.setPage("home");
    }
    @FXML
    void login() {
        LoginBean loginBean = new LoginBean();              // settaggio parametri LoginBean
        loginBean.setEmail(emailField.getText());
        loginBean.setPassword(passwordField.getText());

        if(loginBean.validation()){

            try {
                accountBean = loginController.login(loginBean);

                if (accountBean.getRole().equals("Student")) {
                    fxmlLoader = FxmlLoader.setPage("StudentHomepage");

                }else if(accountBean.getRole().equals("Admin")) {
                    fxmlLoader = FxmlLoader.setPage("AdminHomepage");
                }

            } catch (FailedLoginException e) {
                showErrorMessage(e.getMessage());
            } catch (SQLException e) {
                //not handled
            } catch (FailedResearchException e) {
                throw new RuntimeException(e);
            }
            //TODO: exception handling

        } else{
            showErrorMessage("Incorrect email or password. Try again");
        }
    }

    public void showErrorMessage(String message) {
        incorrectLabel.setText(message);
        incorrectLabel.setVisible(true);
    }

}