package com.example.lessonlink.view1;

import com.example.lessonlink.controller.LoginController;
import com.example.lessonlink.exceptions.FailedFileAccessException;
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
    private int accountType = -1;   // 0 = student, 1 = admin

    public void setAccountType(int type) {
        accountType = type;
    }

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
                studentOrAdminLogin();
            } catch (FailedLoginException | FailedFileAccessException e) {
                showErrorMessage(e.getMessage());
            } catch (SQLException e) {
                //not handled
            }

        } else{
            showErrorMessage("Incorrect email or password. Try again");
        }
    }

    private void studentOrAdminLogin() {
        if (accountBean.getRole().equals("Student")) {
            if (accountType == 0) {
                fxmlLoader = FxmlLoader.setPage("StudentHomepage");
            } else {
                showErrorMessage("Please go to the admin login page.");
            }

        }else if(accountBean.getRole().equals("Admin")) {
            if (accountType == 1) {
                fxmlLoader = FxmlLoader.setPage("AdminHomepage");
            }
            showErrorMessage("Please go to the student login page.");
        }
    }

    private void showErrorMessage(String message) {
        incorrectLabel.setText(message);
        incorrectLabel.setVisible(true);
    }

    @FXML
    void forgotPassword() {
        incorrectLabel.setText("Not implemented yet, sorry.");
        incorrectLabel.setVisible(true);
    }

}