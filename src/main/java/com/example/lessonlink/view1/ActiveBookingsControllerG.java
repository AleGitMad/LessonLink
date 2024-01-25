package com.example.lessonlink.view1;

import com.example.lessonlink.controller.BookingsController;
import com.example.lessonlink.view1.bean.BookingBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.util.List;

public class ActiveBookingsControllerG {
    @FXML
    public Label userNameLabel;
    @FXML
    public Pane result1;
    @FXML
    public Label teacherName1;
    @FXML
    public Label studentName1;
    @FXML
    public Label date1;
    @FXML
    public Pane result2;
    @FXML
    public Label teacherName2;
    @FXML
    public Label studentName2;
    @FXML
    public Label date2;
    @FXML
    public Pane result3;
    @FXML
    public Label teacherName3;
    @FXML
    public Label studentName3;
    @FXML
    public Label date3;
    @FXML
    public Pane result4;
    @FXML
    public Label teacherName4;
    @FXML
    public Label studentName4;
    @FXML
    public Label date4;


    private BookingsController bookingsController;


    @FXML
    void initialize() {
    }

    @FXML
    public void logout(MouseEvent mouseEvent) {
            FxmlLoader.setPage("Home");
    }
    public void setHomePage(MouseEvent mouseEvent) {
        FxmlLoader.setPage("AdminHomepage");
    }

    public void sendEmail(ActionEvent actionEvent) {
    }

    public void setController(BookingsController bookingsController) {
        this.bookingsController = bookingsController;
    }

    public void setData(List<BookingBean> bookingBeanList) {
    }
}
