package com.example.lessonlink.view1;

import com.example.lessonlink.controller.LoginController;
import com.example.lessonlink.view1.bean.AccountHomepageBean;
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

    AccountHomepageBean accountHomepageBean;

    @FXML
    void setHomePage() {
        FxmlLoader.setPage("home");
    }

    @FXML
    void setStudentPage() {
        FxmlLoader.setPage("studentPage");
    }
    //TODO: set login function

    @FXML
    void login() {
        LoginBean loginBean = new LoginBean();              // settaggio parametri LoginBean
        loginBean.setEmail(emailField.getText());
        loginBean.setPassword(passwordField.getText());

        if(loginBean.validation()){

            try {
                accountHomepageBean = loginController.login(loginBean);

                if (accountHomepageBean.getRole().equals("Student")) {
                    StudentHomepageControllerG studentHomepageControllerG;
                    fxmlLoader = FxmlLoader.setPage("StudentHomepage");
                    studentHomepageControllerG = fxmlLoader.getController();
                    studentHomepageControllerG.setUsername(accountHomepageBean.getFirstName());

                }else if(accountHomepageBean.getRole().equals("Admin")) {
                    AdminHomePageControllerG adminHomepageControllerG;
                    fxmlLoader = FxmlLoader.setPage("AdminHomepage");
                    adminHomepageControllerG = fxmlLoader.getController();
                    adminHomepageControllerG.setUsername(accountHomepageBean.getFirstName());
                }

            } catch (FailedLoginException e) {
                showErrorMessage(e.getMessage());
            } catch (SQLException e) {
                //not handled
            }

        } else{
            showErrorMessage("Email or password format are not correct. Insert again");
        }
    }

    public void showErrorMessage(String message) {
        incorrectLabel.setText(message);
        incorrectLabel.setVisible(true);
    }

}