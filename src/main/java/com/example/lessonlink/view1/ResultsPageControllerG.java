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
                reviewScoreLabel1.setText(String.valueOf(teachers.get(0).getAverageRating()) + "/10");
                fareInt1.setText(String.valueOf(teachers.get(0).getFare()));
                result2.setVisible(false);
                result3.setVisible(false);
                result4.setVisible(false);
                break;
            case 2:
                teacherNameLabel1.setText(teachers.get(0).getName());
                teacherNameLabel2.setText(teachers.get(1).getName());
                reviewScoreLabel1.setText(String.valueOf(teachers.get(0).getAverageRating()) + "/10");
                reviewScoreLabel2.setText(String.valueOf(teachers.get(1).getAverageRating()) + "/10");
                fareInt1.setText(String.valueOf(teachers.get(0).getFare()));
                fareInt2.setText(String.valueOf(teachers.get(1).getFare()));
                result3.setVisible(false);
                result4.setVisible(false);
                break;
            case 3:
                teacherNameLabel1.setText(teachers.get(0).getName());
                teacherNameLabel2.setText(teachers.get(1).getName());
                teacherNameLabel3.setText(teachers.get(2).getName());
                reviewScoreLabel1.setText(String.valueOf(teachers.get(0).getAverageRating()) + "/10");
                reviewScoreLabel2.setText(String.valueOf(teachers.get(1).getAverageRating()) + "/10");
                reviewScoreLabel3.setText(String.valueOf(teachers.get(2).getAverageRating()) + "/10");
                fareInt1.setText(String.valueOf(teachers.get(0).getFare()));
                fareInt2.setText(String.valueOf(teachers.get(1).getFare()));
                fareInt3.setText(String.valueOf(teachers.get(2).getFare()));
                result4.setVisible(false);
                break;
        }
        if (numberOfTeachers==4 || numberOfTeachers>4) {
            teacherNameLabel1.setText(teachers.get(0).getName());
            teacherNameLabel2.setText(teachers.get(1).getName());
            teacherNameLabel3.setText(teachers.get(2).getName());
            teacherNameLabel4.setText(teachers.get(3).getName());
            reviewScoreLabel1.setText(String.valueOf(teachers.get(0).getAverageRating()) + "/10");
            reviewScoreLabel2.setText(String.valueOf(teachers.get(1).getAverageRating()) + "/10");
            reviewScoreLabel3.setText(String.valueOf(teachers.get(2).getAverageRating()) + "/10");
            reviewScoreLabel4.setText(String.valueOf(teachers.get(3).getAverageRating()) + "/10");
            /*
            fareInt1 = teachers.get(0).getFare();
            fareInt2 = teachers.get(1).getFare();
            fareInt3 = teachers.get(2).getFare();
            fareInt4 = teachers.get(3).getFare();
            */
            fareInt1.setText(String.valueOf(teachers.get(0).getFare()));
            fareInt2.setText(String.valueOf(teachers.get(1).getFare()));
            fareInt3.setText(String.valueOf(teachers.get(2).getFare()));
            fareInt4.setText(String.valueOf(teachers.get(3).getFare()));
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
