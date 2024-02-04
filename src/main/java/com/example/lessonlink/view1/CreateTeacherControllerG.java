package com.example.lessonlink.view1;

import com.example.lessonlink.controller.AddTeacherController;
import com.example.lessonlink.view1.bean.ProfileTeacherBean;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.util.Arrays;
import java.util.List;

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

    private static final List<String> SUBJECTS = Arrays.asList("Math", "History", "English", "Physics", "Geography");
    private static final List<String> CITIES = Arrays.asList("Rome", "Milan", "Bergamo", "Tivoli");
    private static final List<String> QUALIFICATIONS = Arrays.asList("High School", "Bachelor", "Master");
    private static final List<String> ONLINE_OPTIONS = Arrays.asList("Yes", "No");


    @FXML
    public void initialize() {
        subjectBox1.getItems().addAll(SUBJECTS);
        subjectBox2.getItems().addAll(SUBJECTS);
        subjectBox3.getItems().addAll(SUBJECTS);
        cityBox.getItems().addAll(CITIES);
        qualificationBox.getItems().addAll(QUALIFICATIONS);
        onlineBox.getItems().addAll(ONLINE_OPTIONS);
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
    void goToEvaluatorPage() {
        ProfileTeacherBean profileTeacherBean = new ProfileTeacherBean();
        AddTeacherController addTeacherController = new AddTeacherController();

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
