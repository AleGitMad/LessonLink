package com.example.lessonlink.view1;

import com.example.lessonlink.view1.bean.LessonBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;

import java.util.Comparator;
import java.util.List;

public class HistoryPageControllerG {

    @FXML
    private Label teacherNameLabel1;
    @FXML
    private Label teacherNameLabel2;
    @FXML
    private Label teacherNameLabel3;
    @FXML
    private Label teacherNameLabel4;
    @FXML
    private Label teacherNameLabel5;

    @FXML
    private Label lessonDate1;
    @FXML
    private Label lessonDate2;
    @FXML
    private Label lessonDate3;
    @FXML
    private Label lessonDate4;
    @FXML
    private Label lessonDate5;

    @FXML
    private Pane result1;
    @FXML
    private Pane result2;
    @FXML
    private Pane result3;
    @FXML
    private Pane result4;
    @FXML
    private Pane result5;

    @FXML
    private RadioButton sortByNameButton;
    @FXML
    private RadioButton sortByDateButton;
    private boolean sortByDate;

    @FXML
    private ComboBox<Integer> yourRating;

    List<LessonBean> lessonBeans;

    @FXML
    public void initialize() {

        yourRating.getItems().addAll(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        //setup togglegroup (only one button can be selected at a time)
        ToggleGroup sortingMethod = new ToggleGroup();
        sortByDateButton.setToggleGroup(sortingMethod);
        sortByNameButton.setToggleGroup(sortingMethod);

        //set default sorting method
        sortByDateButton.setSelected(true);
        sortByDate = true;
    }

    @FXML
    public void changeSortingMethod(ActionEvent actionEvent) {
        sortByDate = actionEvent.getSource().equals(sortByDateButton);
        setHistoryPage();
    }

    public void setLessonBeans(List<LessonBean> lessonBeans) {
        this.lessonBeans = lessonBeans;
    }

    public void setHistoryPage() {
        if (sortByDate) {
            lessonBeans.sort((LessonBean l1, LessonBean l2) -> l2.getLessonDateTime().compareTo(l1.getLessonDateTime()));
        } else {
            lessonBeans.sort(Comparator.comparing(LessonBean::getTeacherName));
        }

        int numberOfLessons = lessonBeans.size();

        // Create arrays of labels and panes
        Label[] teacherNameLabels = {teacherNameLabel1, teacherNameLabel2, teacherNameLabel3, teacherNameLabel4, teacherNameLabel5};
        Label[] lessonDateLabels = {lessonDate1, lessonDate2, lessonDate3, lessonDate4, lessonDate5};
        Pane[] resultPanes = {result1, result2, result3, result4, result5};

        // Set teacher details for each teacher
        for (int i = 0; i < numberOfLessons; i++) {
            setLessonDetails(lessonBeans.get(i), teacherNameLabels[i], lessonDateLabels[i]);
        }

        // Set visibility of result panes
        for (int i = 0; i < resultPanes.length; i++) {
            resultPanes[i].setVisible(i < numberOfLessons);
        }
    }

    private void setLessonDetails(LessonBean lessonBean, Label teacherNameLabel, Label lessonDateLabel) {
        teacherNameLabel.setText(lessonBean.getTeacherName());
        lessonDateLabel.setText(lessonBean.getLessonDateTime().toString());
    }

}
