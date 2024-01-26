package com.example.lessonlink.view1;

import com.example.lessonlink.controller.BookLessonController;
import com.example.lessonlink.model.observer.Observer;
import com.example.lessonlink.view1.bean.LessonBean;
import com.example.lessonlink.view1.bean.ReviewBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.sql.Date;
import java.util.List;

public class HistoryPageControllerG implements Observer {

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

    @FXML
    private Label userNameLabel;

    @FXML
    private Button leaveReview1;
    @FXML
    private Button leaveReview2;
    @FXML
    private Button leaveReview3;
    @FXML
    private Button leaveReview4;
    @FXML
    private Button leaveReview5;

    @FXML
    private Rectangle resultHighlight1;
    @FXML
    private Rectangle resultHighlight2;
    @FXML
    private Rectangle resultHighlight3;
    @FXML
    private Rectangle resultHighlight4;
    @FXML
    private Rectangle resultHighlight5;

    @FXML
    private Label teacherConfirmLabel;
    @FXML
    private ImageView teacherConfirmImage;
    @FXML
    private Label teacherRatingLabel;
    @FXML
    private TextField yourComment;

    @FXML
    private Pane errorPane;

    @FXML
    private Button confirmReviewButton;


    List<LessonBean> lessonBeans;

    BookLessonController bookLessonController = new BookLessonController();

    private int selectedTeacherId;
    private int buttonPressed;

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

        userNameLabel.setText(bookLessonController.getAccountBean().getName());

        //observer pattern
        //bookLessonController.attachObserverToTeacher(this);
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
        Button[] leaveReviewButtons = {leaveReview1, leaveReview2, leaveReview3, leaveReview4, leaveReview5};
        Pane[] resultPanes = {result1, result2, result3, result4, result5};

        // Set teacher details for each teacher
        for (int i = 0; i < numberOfLessons; i++) {
            setLessonDetails(lessonBeans.get(i), teacherNameLabels[i], lessonDateLabels[i], leaveReviewButtons[i]);
        }

        // Set visibility of result panes
        for (int i = 0; i < resultPanes.length; i++) {
            resultPanes[i].setVisible(i < numberOfLessons);
        }
    }

    private void setLessonDetails(LessonBean lessonBean, Label teacherNameLabel, Label lessonDateLabel, Button leaveReviewButton) {
        teacherNameLabel.setText(lessonBean.getTeacherName());
        if (lessonBean.getLessonDateTime().isAfter(LocalDateTime.now())) {
            lessonDateLabel.setText("Next lesson on " + lessonBean.getLessonDate().toString());
            leaveReviewButton.setVisible(false);
            //mostra confermata o meno
        } else {
            lessonDateLabel.setText("Had a lesson on " + lessonBean.getLessonDate().toString());
        }
    }

    @FXML
    void showConfirmPanel(ActionEvent event) {
        switch (((Button) event.getSource()).getId()) {
            case "leaveReview1":
                disableHighlights();
                resultHighlight1.setVisible(true);
                teacherConfirmImage.setVisible(true);
                teacherConfirmLabel.setText(lessonBeans.getFirst().getTeacherName());
                teacherConfirmLabel.setVisible(true);
                teacherRatingLabel.setText("Actual rating: " + lessonBeans.getFirst().getAverageRating() + "/10");
                teacherRatingLabel.setVisible(true);
                yourComment.setEditable(true);
                selectedTeacherId = lessonBeans.getFirst().getTeacherId();
                buttonPressed = 1;
                break;
            case "leaveReview2":
                disableHighlights();
                resultHighlight2.setVisible(true);
                teacherConfirmImage.setVisible(true);
                teacherConfirmLabel.setText(lessonBeans.get(1).getTeacherName());
                teacherConfirmLabel.setVisible(true);
                teacherRatingLabel.setText("Actual rating: " + lessonBeans.get(1).getAverageRating() + "/10");
                teacherRatingLabel.setVisible(true);
                yourComment.setEditable(true);
                selectedTeacherId = lessonBeans.get(1).getTeacherId();
                buttonPressed = 2;
                break;
            case "leaveReview3":
                disableHighlights();
                resultHighlight3.setVisible(true);
                teacherConfirmImage.setVisible(true);
                teacherConfirmLabel.setText(lessonBeans.get(2).getTeacherName());
                teacherConfirmLabel.setVisible(true);
                teacherRatingLabel.setText("Actual rating: " + lessonBeans.get(2).getAverageRating() + "/10");
                teacherRatingLabel.setVisible(true);
                yourComment.setEditable(true);
                selectedTeacherId = lessonBeans.get(2).getTeacherId();
                buttonPressed = 3;
                break;
            case "leaveReview4":
                disableHighlights();
                resultHighlight4.setVisible(true);
                teacherConfirmImage.setVisible(true);
                teacherConfirmLabel.setText(lessonBeans.get(3).getTeacherName());
                teacherConfirmLabel.setVisible(true);
                teacherRatingLabel.setText("Actual rating: " + lessonBeans.get(3).getAverageRating() + "/10");
                teacherRatingLabel.setVisible(true);
                yourComment.setEditable(true);
                selectedTeacherId = lessonBeans.get(3).getTeacherId();
                buttonPressed = 4;
                break;
            case "leaveReview5":
                disableHighlights();
                resultHighlight5.setVisible(true);
                teacherConfirmImage.setVisible(true);
                teacherConfirmLabel.setText(lessonBeans.get(4).getTeacherName());
                teacherConfirmLabel.setVisible(true);
                teacherRatingLabel.setText("Actual rating: " + lessonBeans.get(4).getAverageRating() + "/10");
                teacherRatingLabel.setVisible(true);
                yourComment.setEditable(true);
                selectedTeacherId = lessonBeans.get(4).getTeacherId();
                buttonPressed = 5;
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
        resultHighlight5.setVisible(false);
        confirmReviewButton.setVisible(true);
    }


    @FXML
    void confirmReview() {
        ReviewBean reviewBean = new ReviewBean();
        reviewBean.setStars(yourRating.getValue());
        if (!yourComment.getText().isEmpty()) {
            reviewBean.setComment(yourComment.getText());
        }
        reviewBean.setTeacherId(selectedTeacherId);
        if (reviewBean.validate()) {

            reviewBean.setDate(Date.valueOf(LocalDate.now()));
            try {
                bookLessonController.insertReview(reviewBean);
                switch (buttonPressed) {
                    case 1:
                        leaveReview1.setVisible(false);
                        break;
                    case 2:
                        leaveReview2.setVisible(false);
                        break;
                    case 3:
                        leaveReview3.setVisible(false);
                        break;
                    case 4:
                        leaveReview4.setVisible(false);
                        break;
                    case 5:
                        leaveReview5.setVisible(false);
                        break;
                    default:
                        break;
                }
                confirmReviewButton.setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            errorPane.setVisible(true);
        }

    }


    @FXML
    void logout(ActionEvent event) {
        FxmlLoader.setPage("Home");
    }

    @FXML
    void setHomePage() {
        FxmlLoader.setPage("StudentHomePage");
    }

    @Override
    public void update() {
        //change average rating
    }

    @FXML
    void closeErrorPane() {
        errorPane.setVisible(false);
    }
}
