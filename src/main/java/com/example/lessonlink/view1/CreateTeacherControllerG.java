package com.example.lessonlink.view1;

import com.example.lessonlink.controller.AddTeacherController;
import com.example.lessonlink.view1.bean.ProfileTeacherBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

//This controller will control the CreateTeacher.fxml and EvaluatorPage.fxml files
public class CreateTeacherControllerG {
    @FXML
    private Label username;
    @FXML
    private ComboBox<String> subjectBox1;
    @FXML
    private ComboBox<String> subjectBox2;
    @FXML
    private ComboBox<String> subjectBox3;
    @FXML
    private ComboBox<String> cityBox;
    @FXML
    private ComboBox<String> qualificationBox;
    @FXML
    private ComboBox<String> onlineBox;
    @FXML
    private TextField nameField;

    private ProfileTeacherBean profileTeacherBean;
    private AddTeacherController addTeacherController;

    @FXML
    public void initialize() {
        subjectBox1.getItems().addAll("Math", "History", "English", "Physics", "Geography");
        subjectBox2.getItems().addAll("Math", "History", "English", "Physics", "Geography");
        subjectBox3.getItems().addAll("Math", "History", "English", "Physics", "Geography");
        cityBox.getItems().addAll("Rome", "Milan", "Bergamo", "Tivoli");
        qualificationBox.getItems().addAll("High School", "Bachelor", "Master");
        onlineBox.getItems().addAll("Yes", "No");
    }

    @FXML
    void logout() {
        FxmlLoader.setPage("Home");
    }

    @FXML
    void setHomePage() {
        FxmlLoader.setPage("AdminHomepage");
    }

    @FXML
    void goToEvaluatorPage(ActionEvent event) {
        profileTeacherBean = new ProfileTeacherBean();
        addTeacherController = new AddTeacherController();

        profileTeacherBean.setName(nameField.getText());
        profileTeacherBean.setCity(cityBox.getValue());
        profileTeacherBean.setQualification(qualificationBox.getValue());
        profileTeacherBean.setSubject1(subjectBox1.getValue());
        profileTeacherBean.setSubject2(subjectBox2.getValue());
        profileTeacherBean.setSubject3(subjectBox3.getValue());
        profileTeacherBean.setOnline(onlineBox.getValue());

        if (profileTeacherBean.validate()) {
            profileTeacherBean.setDecorations();
            addTeacherController.addTeacher(profileTeacherBean);
            //System.out.println(profileTeacherBean.getName());
            FXMLLoader fxmlLoader = FxmlLoader.setPage("EvaluatorPage");
            EvaluatorControllerG evaluatorControllerG = fxmlLoader.getController();
            evaluatorControllerG.setController(addTeacherController);
            evaluatorControllerG.setProfileTeacherBean(profileTeacherBean);
            evaluatorControllerG.setFareToScreen(profileTeacherBean.fare());
        }
    }

    public void setData(ProfileTeacherBean profileTeacherBean) {
        nameField.setText(profileTeacherBean.name());
        cityBox.setValue(profileTeacherBean.city());
        qualificationBox.setValue(profileTeacherBean.qualification());
        subjectBox1.setValue(profileTeacherBean.subject1());
        subjectBox2.setValue(profileTeacherBean.subject2());
        subjectBox3.setValue(profileTeacherBean.subject3());
        onlineBox.setValue(profileTeacherBean.online());
    }
}
