package com.example.lessonlink.view1;

import com.example.lessonlink.controller.AddTeacherController;
import com.example.lessonlink.exceptions.FailedInsertException;
import com.example.lessonlink.view1.bean.ProfileTeacherBean;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;


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
    @FXML
    public Pane errorPane;
    @FXML
    public Label errorLabel;


    private AddTeacherController addTeacherController;
    private ProfileTeacherBean profileTeacherBean;

    public void setController(AddTeacherController addTeacherController) {
        this.addTeacherController = addTeacherController;
    }
    public void setProfileTeacherBean(ProfileTeacherBean profileTeacherBean) {
        name.setText(profileTeacherBean.name());
        subjects.setText(profileTeacherBean.subject1() + " " + profileTeacherBean.subject2() + " " + profileTeacherBean.subject3());
        city.setText(profileTeacherBean.city());
        qualification.setText(profileTeacherBean.qualification());
        fare.setText(profileTeacherBean.fare() + " €/h");
        this.profileTeacherBean = profileTeacherBean;
        username.setText(addTeacherController.getAccountBean().getName());
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
        evaluatorControllerG.setFareToScreen(profileTeacherBean.fare());
    }



    public void createProfile() {
        try {
            addTeacherController.confirmTeacher(profileTeacherBean);
        } catch (FailedInsertException e) {
            errorPane.setVisible(true);
            errorLabel.setText(e.getMessage());
        }
        FxmlLoader.setPage("AdminHomepage");
    }

    @FXML
    void hidePanel() {
        errorPane.setVisible(false);
    }
}
