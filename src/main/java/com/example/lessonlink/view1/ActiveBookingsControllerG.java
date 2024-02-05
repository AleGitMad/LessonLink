package com.example.lessonlink.view1;

import com.example.lessonlink.controller.BookingsController;
import com.example.lessonlink.exceptions.FailedUpdateException;
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
    @FXML
    public Button sendButton1;
    @FXML
    public Button sendButton2;
    @FXML
    public Button sendButton3;
    @FXML
    public Button sendButton4;
    @FXML
    public Pane errorPane;
    @FXML
    public Label errorLabel;

    private BookingsController bookingsController;
    private List<BookingBean> bookingBeans;


    @FXML
    void initialize() {
        result1.setVisible(false);
        result2.setVisible(false);
        result3.setVisible(false);
        result4.setVisible(false);
    }

    @FXML
    public void logout(MouseEvent mouseEvent) {
            FxmlLoader.setPage("Home");
    }
    @FXML
    public void setHomePage(MouseEvent mouseEvent) {
        FxmlLoader.setPage("AdminHomepage");
    }
    public void setController(BookingsController bookingsController) {
        this.bookingsController = bookingsController;
        userNameLabel.setText(bookingsController.getAccountBean().getName());
    }


    public void sendEmail(ActionEvent actionEvent) throws FailedUpdateException {
        Button button = (Button) actionEvent.getSource();
        String buttonId = button.getId();
        BookingBean bookingBean = null;

        switch (buttonId) {
            case "sendButton1":
                bookingBean = bookingBeans.getFirst();
                sendButton1.setVisible(false);
                break;
            case "sendButton2":
                bookingBean = bookingBeans.get(1);
                sendButton2.setVisible(false);
                break;
            case "sendButton3":
                bookingBean = bookingBeans.get(2);
                sendButton3.setVisible(false);
                break;
            case "sendButton4":
                bookingBean = bookingBeans.get(3);
                sendButton4.setVisible(false);
                break;
            default:
                break;
        }

        try {
            bookingsController.sendEmail(bookingBean);
        } catch (FailedUpdateException e) {
            errorPane.setVisible(true);
            errorLabel.setText(e.getMessage());
        }
    }


    public void setData(List<BookingBean> bookingBeanList) {
        bookingBeans = bookingBeanList;

        if (!bookingBeanList.isEmpty()) {
            result1.setVisible(true);
            teacherName1.setText(bookingBeanList.getFirst().getTeacher());
            studentName1.setText(bookingBeanList.getFirst().getStudent());
            date1.setText(bookingBeanList.getFirst().getDateTime());
            if(bookingBeanList.getFirst().getConfirmed()){
                sendButton1.setVisible(false);
            }
        }
        if (bookingBeanList.size() >= 2) {
            result2.setVisible(true);
            teacherName2.setText(bookingBeanList.get(1).getTeacher());
            studentName2.setText(bookingBeanList.get(1).getStudent());
            date2.setText(bookingBeanList.get(1).getDateTime());
            if(bookingBeanList.get(1).getConfirmed()){
                sendButton2.setVisible(false);
            }
        }
        if (bookingBeanList.size() >= 3) {
            result3.setVisible(true);
            teacherName3.setText(bookingBeanList.get(2).getTeacher());
            studentName3.setText(bookingBeanList.get(2).getStudent());
            date3.setText(bookingBeanList.get(2).getDateTime());
            if(bookingBeanList.get(2).getConfirmed()){
                sendButton3.setVisible(false);
            }
        }
        if (bookingBeanList.size() >= 4) {
            result4.setVisible(true);
            teacherName4.setText(bookingBeanList.get(3).getTeacher());
            studentName4.setText(bookingBeanList.get(3).getStudent());
            date4.setText(bookingBeanList.get(3).getDateTime());
            if(bookingBeanList.get(3).getConfirmed()){
                sendButton4.setVisible(false);
            }
        }
    }
    @FXML
    void newSearchE(ActionEvent event) {
        errorPane.setVisible(false);
    }
}
