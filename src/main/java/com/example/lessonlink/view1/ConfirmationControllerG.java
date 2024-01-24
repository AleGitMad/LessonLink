package com.example.lessonlink.view1;

import com.example.lessonlink.controller.AddTeacherController;
import com.example.lessonlink.view1.bean.ProfileTeacherBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.sql.SQLException;

//This controller will control the CreateTeacher.fxml and EvaluatorPage.fxml files
public class ConfirmationControllerG {
    @FXML
    public Button useEvaluated;
    @FXML
    public Button useOther;
    @FXML
    public Label username;
    @FXML
    public Label fare;
    @FXML
    public Label name;
    @FXML
    public Label subjects;
    @FXML
    public Label city;
    @FXML
    public Label qualification;


    private AddTeacherController addTeacherController;
    private ProfileTeacherBean profileTeacherBean;

    public void setController(AddTeacherController addTeacherController) {
        this.addTeacherController = addTeacherController;
    }
    public void setProfileTeacherBean(ProfileTeacherBean profileTeacherBean) {
        name.setText(profileTeacherBean.getName());
        subjects.setText(profileTeacherBean.getSubject1() + " " + profileTeacherBean.getSubject2() + " " + profileTeacherBean.getSubject3());
        city.setText(profileTeacherBean.getCity());
        qualification.setText(profileTeacherBean.getQualification());
        fare.setText(profileTeacherBean.getFare());
        this.profileTeacherBean = profileTeacherBean;
    }

    @FXML
    public void logout() {
        FxmlLoader.setPage("Home");
    }
    @FXML
    public void setUserName(String userName) {
        username.setText(userName);
    }
    @FXML
    public void setHomePage() {
        FxmlLoader.setPage("AdminHomepage");
    }
    @FXML
    void back() {
        FXMLLoader fxmlLoader = FxmlLoader.setPage("EvaluatorPage");
        EvaluatorControllerG evaluatorControllerG = fxmlLoader.getController();
        evaluatorControllerG.setController(addTeacherController);
        evaluatorControllerG.setProfileTeacherBean(profileTeacherBean);
        evaluatorControllerG.setFareToScreen(profileTeacherBean.getFare());
    }



    public void createProfile() {
        try {
            addTeacherController.confirmTeacher(profileTeacherBean);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        FxmlLoader.setPage("AdminHomepage");
    }
}
