package com.example.lessonlink.view1;

import com.example.lessonlink.model.decorator.Teacher;
import com.example.lessonlink.view1.bean.TeacherBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.util.List;

public class ResultsPageControllerG {

    @FXML
    private Label teacherNameLabel1;
    @FXML
    private Label teacherNameLabel2;
    @FXML
    private Label teacherNameLabel3;
    @FXML
    private Label teacherNameLabel4;

    @FXML
    private Label reviewScoreLabel1;
    @FXML
    private Label reviewScoreLabel2;
    @FXML
    private Label reviewScoreLabel3;
    @FXML
    private Label reviewScoreLabel4;

    @FXML
    private Label fareInt1;
    @FXML
    private Label fareInt2;
    @FXML
    private Label fareInt3;
    @FXML
    private Label fareInt4;

    @FXML
    private Rectangle resultHighlight1;
    @FXML
    private Rectangle resultHighlight2;
    @FXML
    private Rectangle resultHighlight3;
    @FXML
    private Rectangle resultHighlight4;

    @FXML
    private Pane result1;
    @FXML
    private Pane result2;
    @FXML
    private Pane result3;
    @FXML
    private Pane result4;


    private List<Teacher> teachers;
    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public void setResultsPage(TeacherBean teacherBean) {
        teachers = teacherBean.getTeachers();
        int numberOfTeachers = teachers.size();
        switch (numberOfTeachers) {
            case 1:
                teacherNameLabel1.setText(teachers.get(0).getName());
                if (teachers.get(0).getHasReviews()) {
                    reviewScoreLabel1.setText(teachers.get(0).getAverageRating() + "/10");
                } else {
                    reviewScoreLabel1.setText("");
                }
                fareInt1.setText(teachers.get(0).getFare() + "€/h");
                result2.setVisible(false);
                result3.setVisible(false);
                result4.setVisible(false);
                break;
            case 2:
                teacherNameLabel1.setText(teachers.get(0).getName());
                teacherNameLabel2.setText(teachers.get(1).getName());
                if (teachers.get(0).getHasReviews()) {
                    reviewScoreLabel1.setText(teachers.get(0).getAverageRating() + "/10");
                } else {
                    reviewScoreLabel1.setText("");
                }
                if (teachers.get(1).getHasReviews()) {
                    reviewScoreLabel2.setText(teachers.get(1).getAverageRating() + "/10");
                } else {
                    reviewScoreLabel2.setText("");
                }
                fareInt1.setText(teachers.get(0).getFare() + "€/h");
                fareInt2.setText(teachers.get(1).getFare() + "€/h");
                result3.setVisible(false);
                result4.setVisible(false);
                break;
            case 3:
                teacherNameLabel1.setText(teachers.get(0).getName());
                teacherNameLabel2.setText(teachers.get(1).getName());
                teacherNameLabel3.setText(teachers.get(2).getName());
                if (teachers.get(0).getHasReviews()) {
                    reviewScoreLabel1.setText(teachers.get(0).getAverageRating() + "/10");
                } else {
                    reviewScoreLabel1.setText("");
                }
                if (teachers.get(1).getHasReviews()) {
                    reviewScoreLabel2.setText(teachers.get(1).getAverageRating() + "/10");
                } else {
                    reviewScoreLabel2.setText("");
                }
                if (teachers.get(2).getHasReviews()) {
                    reviewScoreLabel3.setText(teachers.get(2).getAverageRating() + "/10");
                } else {
                    reviewScoreLabel3.setText("");
                }
                fareInt1.setText(teachers.get(0).getFare() + "€/h");
                fareInt2.setText(teachers.get(1).getFare() + "€/h");
                fareInt3.setText(teachers.get(2).getFare() + "€/h");
                result4.setVisible(false);
                break;
        }
        if (numberOfTeachers==4 || numberOfTeachers>4) {
            teacherNameLabel1.setText(teachers.get(0).getName());
            teacherNameLabel2.setText(teachers.get(1).getName());
            teacherNameLabel3.setText(teachers.get(2).getName());
            teacherNameLabel4.setText(teachers.get(3).getName());
            if (teachers.get(0).getHasReviews()) {
                reviewScoreLabel1.setText(teachers.get(0).getAverageRating() + "/10");
            } else {
                reviewScoreLabel1.setText("");
            }
            if (teachers.get(1).getHasReviews()) {
                reviewScoreLabel2.setText(teachers.get(1).getAverageRating() + "/10");
            } else {
                reviewScoreLabel2.setText("");
            }
            if (teachers.get(2).getHasReviews()) {
                reviewScoreLabel3.setText(teachers.get(2).getAverageRating() + "/10");
            } else {
                reviewScoreLabel3.setText("");
            }
            if (teachers.get(3).getHasReviews()) {
                reviewScoreLabel4.setText(teachers.get(3).getAverageRating() + "/10");
            } else {
                reviewScoreLabel4.setText("");
            }
            fareInt1.setText(teachers.get(0).getFare() + "€/h");
            fareInt2.setText(teachers.get(1).getFare() + "€/h");
            fareInt3.setText(teachers.get(2).getFare() + "€/h");
            fareInt4.setText(teachers.get(3).getFare() + "€/h");
        }
    }

    @FXML
    void setHomePage() {
        FxmlLoader.setPage("StudentHomePage");
    }

    @FXML
    void logout() {
        FxmlLoader.setPage("Home");
    }


}
