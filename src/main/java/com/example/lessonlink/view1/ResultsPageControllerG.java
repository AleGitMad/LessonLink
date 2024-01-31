package com.example.lessonlink.view1;

import com.example.lessonlink.controller.BookLessonController;
import com.example.lessonlink.exceptions.FailedResearchException;
import com.example.lessonlink.view1.bean.LessonBean;
import com.example.lessonlink.view1.bean.TeacherBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
    @FXML
    private ComboBox<String> lessonTime;

    @FXML
    private Pane fullPane;
    @FXML
    private Pane errorPane;
    @FXML
    private Label errorLabel;

    @FXML
    private Label userNameLabel;

    private int selectedTeacherId;
    private boolean isOnline;

    private List<TeacherBean> teacherBeans;

    BookLessonController bookLessonController = new BookLessonController();

    public void setTeacherBeans(List<TeacherBean> teacherBeans) {
        this.teacherBeans = teacherBeans;
        isOnline = teacherBeans.getFirst().getIsOnline();
    }

    @FXML
    public void initialize() {

        lessonTime.getItems().addAll("08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00");

        //setup togglegroup (only one button can be selected at a time)
        ToggleGroup sortingMethod = new ToggleGroup();
        sortByRatingButton.setToggleGroup(sortingMethod);
        sortByFareButton.setToggleGroup(sortingMethod);

        //set default sorting method
        sortByRatingButton.setSelected(true);
        sortByRating = true;

        userNameLabel.setText(bookLessonController.getAccountBean().getName());
    }

    public void changeSortingMethod(ActionEvent actionEvent) {
        disableHighlights();
        teacherConfirmImage.setVisible(false);
        teacherConfirmLabel.setVisible(false);
        fareIntConfirm.setVisible(false);
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
    void showConfirmPanel(ActionEvent event) {
        switch (((Button) event.getSource()).getId()) {
            case "bookLessonButton1":
                disableHighlights();
                resultHighlight1.setVisible(true);
                teacherConfirmImage.setVisible(true);
                teacherConfirmLabel.setText(teacherBeans.getFirst().getTeacherName());
                teacherConfirmLabel.setVisible(true);
                fareIntConfirm.setText(teacherBeans.getFirst().getTeacherFare() + "€/h");
                fareIntConfirm.setVisible(true);
                selectedTeacherId = teacherBeans.getFirst().getTeacherId();
                break;
            case "bookLessonButton2":
                disableHighlights();
                resultHighlight2.setVisible(true);
                teacherConfirmImage.setVisible(true);
                teacherConfirmLabel.setText(teacherBeans.get(1).getTeacherName());
                teacherConfirmLabel.setVisible(true);
                fareIntConfirm.setText(teacherBeans.get(1).getTeacherFare() + "€/h");
                fareIntConfirm.setVisible(true);
                selectedTeacherId = teacherBeans.get(1).getTeacherId();
                break;
            case "bookLessonButton3":
                disableHighlights();
                resultHighlight3.setVisible(true);
                teacherConfirmImage.setVisible(true);
                teacherConfirmLabel.setText(teacherBeans.get(2).getTeacherName());
                teacherConfirmLabel.setVisible(true);
                fareIntConfirm.setText(teacherBeans.get(2).getTeacherFare() + "€/h");
                fareIntConfirm.setVisible(true);
                selectedTeacherId = teacherBeans.get(2).getTeacherId();
                break;
            case "bookLessonButton4":
                disableHighlights();
                resultHighlight4.setVisible(true);
                teacherConfirmImage.setVisible(true);
                teacherConfirmLabel.setText(teacherBeans.get(3).getTeacherName());
                teacherConfirmLabel.setVisible(true);
                fareIntConfirm.setText(teacherBeans.get(3).getTeacherFare() + "€/h");
                fareIntConfirm.setVisible(true);
                selectedTeacherId = teacherBeans.get(3).getTeacherId();
                break;
            default:
                break;
        }
    }

    void disableHighlights() {
        resultHighlight1.setVisible(false);
        resultHighlight2.setVisible(false);
        resultHighlight3.setVisible(false);
        resultHighlight4.setVisible(false);
    }

    @FXML
    void logout() {
        FxmlLoader.setPage("Home");
    }

    @FXML
    void checkSlotAvailability(ActionEvent event) {
        LessonBean lessonBean = new LessonBean();
        lessonBean.setTeacherId(selectedTeacherId);
        lessonBean.setLessonDate(lessonDate.getValue());
        lessonBean.setLessonTime(lessonTime.getValue());
        if (lessonBean.validate()) {
            lessonBean.setLessonDateTimeFrom(java.sql.Date.valueOf(lessonBean.getLessonDate()), lessonBean.getLessonTime());
            try {
                if (bookLessonController.checkSlotAvailability(lessonBean)) {
                    lessonBean.setIsOnline(isOnline);
                    setPaymentPage(lessonBean);
                } else {
                    fullPane.setVisible(true);
                }
            } catch (FailedResearchException e) {
                errorPane.setVisible(true);
                errorLabel.setText(e.getMessage());
            }
        } else {
            errorPane.setVisible(true);
            errorLabel.setText("Please select all the fields, making sure to select a date in the future");
        }
    }

    void setPaymentPage(LessonBean lessonBean) {
        FXMLLoader loader = FxmlLoader.setPage("PaymentPage");
        PaymentPageControllerG paymentPageControllerG = loader.getController();
        //pass teacherBeans to make resultsPage results available when going back
        paymentPageControllerG.setTeacherBeans(teacherBeans);
        paymentPageControllerG.setLessonBean(lessonBean);
    }

    @FXML
    void closeFullPane(ActionEvent event) {
        fullPane.setVisible(false);
    }
    @FXML
    void closeErrorPane(ActionEvent event) {
        errorPane.setVisible(false);
    }

}
