package com.example.lessonlink.view1;

import com.example.lessonlink.model.Teacher;
import com.example.lessonlink.view1.bean.TeacherBean;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
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

    @FXML
    private RadioButton sortByRatingButton;
    @FXML
    private RadioButton sortByFareButton;
    private boolean sortByRating;

    @FXML
    private ImageView teacherConfirmImage;
    @FXML
    private Label teacherConfirmLabel;
    @FXML
    private Label fareIntConfirm;
    @FXML
    private DatePicker lessonDate;
    @FXML
    private Button confirmLessonButton;


    private List<TeacherBean> teacherBeans;
    public void setTeacherBeans(List<TeacherBean> teacherBeans) {
        this.teacherBeans = teacherBeans;
    }

    public void initialize() {
        //setup togglegroup (only one button can be selected at a time)
        ToggleGroup sortingMethod = new ToggleGroup();
        sortByRatingButton.setToggleGroup(sortingMethod);
        sortByFareButton.setToggleGroup(sortingMethod);

        //set default sorting method
        sortByRatingButton.setSelected(true);
        sortByRating = true;

    }

    public void changeSortingMethod(ActionEvent actionEvent) {
        sortByRating = actionEvent.getSource().equals(sortByRatingButton);
        setResultsPage();
    }

    public void setResultsPage() {

        teacherBeans.sort((TeacherBean t1, TeacherBean t2) -> {
            if (sortByRating) {
                if (!t1.getTeacherHasReview()) return 1;
                if (!t2.getTeacherHasReview()) return -1;
                return Double.compare(t2.getTeacherAverageRating(), t1.getTeacherAverageRating());
            } else {
                return Double.compare(t1.getTeacherAverageRating(), t2.getTeacherAverageRating());
            }
        });

        int numberOfTeachers = teacherBeans.size();

        // Create arrays of labels and panes
        Label[] teacherNameLabels = {teacherNameLabel1, teacherNameLabel2, teacherNameLabel3, teacherNameLabel4};
        Label[] reviewScoreLabels = {reviewScoreLabel1, reviewScoreLabel2, reviewScoreLabel3, reviewScoreLabel4};
        Label[] fareIntLabels = {fareInt1, fareInt2, fareInt3, fareInt4};
        Pane[] resultPanes = {result1, result2, result3, result4};

        // Set teacher details for each teacher
        for (int i = 0; i < numberOfTeachers; i++) {
            setTeacherDetails(teacherBeans.get(i), teacherNameLabels[i], reviewScoreLabels[i], fareIntLabels[i]);
        }

        // Set visibility of result panes
        for (int i = 0; i < resultPanes.length; i++) {
            resultPanes[i].setVisible(i < numberOfTeachers);
        }
    }

    private void setTeacherDetails(TeacherBean teacherBean, Label teacherNameLabel, Label reviewScoreLabel, Label fareIntLabel) {
        teacherNameLabel.setText(teacherBean.getTeacherName());
        if (teacherBean.getTeacherHasReview()) {
            reviewScoreLabel.setText(teacherBean.getTeacherAverageRating() + "/10");
        } else {
            reviewScoreLabel.setText("");
        }
        fareIntLabel.setText(teacherBean.getTeacherFare() + "€/h");
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
