package com.example.lessonlink.view2;

import com.example.lessonlink.controller.BookingsController;
import com.example.lessonlink.exceptions.FailedResearchException;
import com.example.lessonlink.view1.bean.BookingBean;
import com.example.lessonlink.view2.utility.LinePrinter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class ActiveBookingsControllerG2 {
    public void showActiveBookings() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BookingsController bookingsController = new BookingsController();
        List<BookingBean> bookingBeanList;
        String toPrint;
        int choice = 0;

        try {
            bookingBeanList = bookingsController.getActiveBookings();
        } catch (FailedResearchException e) {
            throw new RuntimeException(e);
        }

        printList(bookingBeanList);
        toPrint = "Press: n to choose the booking to confirm, 0 to go back";
        LinePrinter.getInstance().print(toPrint);

        try {
            choice = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            //towrite
        } catch (NumberFormatException e) {
            toPrint = "The input you inserted is not a valid number";
            LinePrinter.getInstance().print(toPrint);
            showActiveBookings();
        }

        if (choice > bookingBeanList.size()) {
            toPrint = "The number you inserted is not valid";
            LinePrinter.getInstance().print(toPrint);
            showActiveBookings();
        }
        if (choice < 0) {
            toPrint = "The number you inserted is not valid";
            LinePrinter.getInstance().print(toPrint);
            showActiveBookings();
        }
        if (choice != 0 && bookingBeanList.get(choice - 1).getConfirmed()) {
            toPrint = "The booking is already confirmed";
            LinePrinter.getInstance().print(toPrint);
            showActiveBookings();
        }

        if (choice != 0 && (!bookingBeanList.get(choice - 1).getConfirmed())) {
                BookingBean bookingBean = bookingBeanList.get(choice - 1);
                bookingsController.sendEmail(bookingBean);
                toPrint = "Booking confirmed";
                LinePrinter.getInstance().print(toPrint);
                showActiveBookings();
        }
    }

    private void printList(List<BookingBean> bookingBeanList) {
        String toPrint;
        String borderTop = "╔════════════════════════════════════════╗";
        String borderBottom = "╚════════════════════════════════════════╝";
        String dataSeparator = "╠════════════════════════════════════════╣";
        for (int i = 0; i < bookingBeanList.size(); i++) {
            BookingBean bookingBean = bookingBeanList.get(i);
            toPrint = borderTop + "\n";
            toPrint += "║ Booking " + String.format("%-31s", (i+1)) + "║\n";
            toPrint += dataSeparator + "\n";
            toPrint += "║ Teacher: " + String.format("%-30s", bookingBean.getTeacher()) + "║\n";
            toPrint += dataSeparator + "\n";
            toPrint += "║ Student: " + String.format("%-30s", bookingBean.getStudent()) + "║\n";
            toPrint += dataSeparator + "\n";
            toPrint += "║ Date: " + String.format("%-33s", bookingBean.getDateTime()) + "║\n";
            toPrint += dataSeparator + "\n";
            toPrint += "║ Confirmed: " + String.format("%-28s", bookingBean.getConfirmed()) + "║\n";
            toPrint += borderBottom;
            LinePrinter.getInstance().print(toPrint);
        }
    }
}
