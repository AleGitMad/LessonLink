package com.example.lessonlink.view1;

import com.example.lessonlink.controller.AddTeacherController;
import com.example.lessonlink.view1.bean.ProfileTeacherBean;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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

    @FXML
    private Pane errorPane;
    @FXML
    private Label errorLabel;

    private static final List<String> SUBJECTS = Arrays.asList("Math", "History", "English", "Physics", "Geography");
    private static final List<String> CITIES = Arrays.asList("Rome", "Milan", "Bergamo", "Tivoli");
    private static final List<String> QUALIFICATIONS = Arrays.asList("High School", "Bachelor", "Master");
    private static final List<String> ONLINE_OPTIONS = Arrays.asList("Yes", "No");

    private Timer timer;

    @FXML
    public void initialize() {
        subjectBox1.getItems().addAll(SUBJECTS);
        subjectBox2.getItems().addAll(SUBJECTS);
        subjectBox3.getItems().addAll(SUBJECTS);
        cityBox.getItems().addAll(CITIES);
        qualificationBox.getItems().addAll(QUALIFICATIONS);
        onlineBox.getItems().addAll(ONLINE_OPTIONS);
        AddTeacherController addTeacherController = new AddTeacherController();
        username.setText(addTeacherController.getAccountBean().getName());

        startTimer();
    }

    private void startTimer() {
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                errorPane.setVisible(true);
                errorLabel.setText("Error: Session expired. No data entered.");
            }
        };
        // Set the timer to 2 minutes
        timer.schedule(task, 2 * 60 * 1000);
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

        // Cancel the timer
        if (timer != null) {
            timer.cancel();
        }

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
