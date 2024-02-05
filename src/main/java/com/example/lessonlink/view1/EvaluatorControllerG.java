package com.example.lessonlink.view1;

import com.example.lessonlink.controller.AddTeacherController;
import com.example.lessonlink.view1.bean.ProfileTeacherBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EvaluatorControllerG {
    @FXML
    public Button useEvaluatedButton;
    @FXML
    public Button useOther;
    @FXML
    public Label username;
    @FXML
    public TextField fareField;
    @FXML
    public Label fareEvaluated;

    private AddTeacherController addTeacherController;
    private ProfileTeacherBean profileTeacherBean;

    public void setController(AddTeacherController addTeacherController) {
        this.addTeacherController = addTeacherController;
    }
    public void setProfileTeacherBean(ProfileTeacherBean profileTeacherBean) {
        this.profileTeacherBean = profileTeacherBean;
    }

    @FXML
    public void logout() {
        FxmlLoader.setPage("Home");
    }
    @FXML
    public void setHomePage() {
        FxmlLoader.setPage("AdminHomepage");
    }
    @FXML
    void back() {
        FXMLLoader fxmlLoader = FxmlLoader.setPage("CreateTeacherProfile");
        CreateTeacherControllerG createTeacherControllerG = fxmlLoader.getController();
        createTeacherControllerG.setData(profileTeacherBean);
    }


    @FXML
    public void useEvaluated(ActionEvent event) {
        profileTeacherBean.setFare(fareEvaluated.getText().substring(0, fareEvaluated.getText().length() - 4));
            FXMLLoader fxmlLoader = FxmlLoader.setPage("ConfirmationPage");
            ConfirmationControllerG confirmationControllerG = fxmlLoader.getController();
            confirmationControllerG.setController(addTeacherController);
            confirmationControllerG.setProfileTeacherBean(profileTeacherBean);
    }

    @FXML
    void useOther(ActionEvent event) {
        profileTeacherBean.setFare(fareField.getText());
        if(profileTeacherBean.fareValidate()){
            FXMLLoader fxmlLoader = FxmlLoader.setPage("ConfirmationPage");
            ConfirmationControllerG confirmationControllerG = fxmlLoader.getController();
            confirmationControllerG.setController(addTeacherController);
            confirmationControllerG.setProfileTeacherBean(profileTeacherBean);
        }
    }

    public void setFareToScreen(String fare) {
        username.setText(addTeacherController.getAccountBean().getName());
        fareEvaluated.setText(fare + " €/h");
    }
}
