package com.example.lessonlink.view1;

import com.example.lessonlink.controller.BookingsController;
import com.example.lessonlink.exceptions.FailedResearchException;
import com.example.lessonlink.view1.bean.BookingBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;

import java.util.List;

public class AdminHomePageControllerG {
    @FXML
    private Label Username;

    public void setUsername(String username) {
        Username.setText(username);
    }

    @FXML
    void logout() {
        FxmlLoader.setPage("Home");
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
