package com.example.lessonlink.view1;

import com.example.lessonlink.controller.AddTeacherController;
import com.example.lessonlink.view1.bean.ProfileTeacherBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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

    public void setController(AddTeacherController addTeacherController) {
        this.addTeacherController = addTeacherController;
    }
    public void setProfileTeacherBean(ProfileTeacherBean profileTeacherBean) {
        name.setText(profileTeacherBean.getName());
        subjects.setText(profileTeacherBean.getSubject1() + " " + profileTeacherBean.getSubject2() + " " + profileTeacherBean.getSubject3());
        city.setText(profileTeacherBean.getCity());
        qualification.setText(profileTeacherBean.getQualification());
        fare.setText(profileTeacherBean.getFare());
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
        FxmlLoader.setPage("EvaluatorPage");
    }



    public void createProfile() {
        addTeacherController.confirmTeacher();
        FxmlLoader.setPage("AdminHomepage");
    }
}
