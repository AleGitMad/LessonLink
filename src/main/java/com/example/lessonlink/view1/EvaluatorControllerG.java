package com.example.lessonlink.view1;

import com.example.lessonlink.controller.AddTeacherController;
import com.example.lessonlink.view1.bean.ProfileTeacherBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

//This controller will control the CreateTeacher.fxml and EvaluatorPage.fxml files
public class EvaluatorControllerG {
    @FXML
    public Button useEvaluated;
    @FXML
    public Button useOther;
    @FXML
    public Label username;
    @FXML
    public TextField FareField;
    @FXML
    public Label fareEvaluated;

    private AddTeacherController addTeacherController;
    private ProfileTeacherBean profileTeacherBean;

    public void setController(AddTeacherController addTeacherController) {
        this.addTeacherController = addTeacherController;
    }
    public void setProfileTeacherBean(ProfileTeacherBean profileTeacherBean) {
        this.profileTeacherBean = profileTeacherBean;
        //System.out.println(profileTeacherBean.getName());
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
        FxmlLoader.setPage("CreateTeacherProfile");
    }


    @FXML
    public void useEvaluated(ActionEvent event) {
        profileTeacherBean.setFare(fareEvaluated.getText());

            addTeacherController.useEvaluatedFare(profileTeacherBean);
            FXMLLoader fxmlLoader = FxmlLoader.setPage("ConfirmationPage");
            ConfirmationControllerG confirmationControllerG = fxmlLoader.getController();
            confirmationControllerG.setController(addTeacherController);
            confirmationControllerG.setProfileTeacherBean(profileTeacherBean);
    }

    @FXML
    void useOther(ActionEvent event) {
        profileTeacherBean.setFare(FareField.getText());
        if(profileTeacherBean.fareValidate()){
            addTeacherController.useOtherFare(profileTeacherBean);
            FXMLLoader fxmlLoader = FxmlLoader.setPage("ConfirmationPage");
            ConfirmationControllerG confirmationControllerG = fxmlLoader.getController();
            confirmationControllerG.setController(addTeacherController);
            confirmationControllerG.setProfileTeacherBean(profileTeacherBean);
        }
    }

    public void setFareToScreen(String fare) {
        fareEvaluated.setText(fare);
        System.out.println(fare);
    }
}
