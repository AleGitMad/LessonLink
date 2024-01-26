package com.example.lessonlink.view1;

import com.example.lessonlink.controller.AddTeacherController;
import com.example.lessonlink.controller.BookingsController;
import com.example.lessonlink.exceptions.FailedResearchException;
import com.example.lessonlink.view1.bean.BookingBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;

import java.util.List;

public class AdminHomePageControllerG {
    @FXML
    private Label userNameLabel;
    @FXML
    private Button modifyLabel;

    AddTeacherController addTeacherController = new AddTeacherController();

    @FXML
    public void initialize() {
        userNameLabel.setText(addTeacherController.getAccountBean().getName());
        //userNameLabel.setText("admin");
    }

    @FXML
    void logout() {
        FxmlLoader.setPage("Home");
    }
    @FXML
    void modify(){
        modifyLabel.setVisible(false);
    }
    @FXML
    void addTeacher(ActionEvent event) {
        FxmlLoader.setPage("CreateTeacherProfile");
    }

    @FXML
    void activeBookings(ActionEvent event) {
        BookingsController bookingsController = new BookingsController();
        FXMLLoader loader = FxmlLoader.setPage("ActiveBookings");
        ActiveBookingsControllerG activeBookingsControllerG = loader.getController();
        activeBookingsControllerG.setController(bookingsController);

        List<BookingBean> bookingBeanList = null;
        try {
            bookingBeanList = bookingsController.getActiveBookings();
        } catch (FailedResearchException e) {
            throw new RuntimeException(e);
        }
        activeBookingsControllerG.setData(bookingBeanList);
    }

}
