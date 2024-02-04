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
            toPrint = "No active bookings found";
            LinePrinter.getInstance().print(toPrint);
            return;
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
        String borderTop = "╔════════════════════════════════════════╗";
        String borderBottom = "╚════════════════════════════════════════╝";
        String dataSeparator = "╠════════════════════════════════════════╣";
        for (int i = 0; i < bookingBeanList.size(); i++) {
            BookingBean bookingBean = bookingBeanList.get(i);
            StringBuilder toPrint = new StringBuilder();
            toPrint.append(borderTop).append("\n");
            toPrint.append("║ Booking ").append(String.format("%-31s", (i+1))).append("║\n");
            toPrint.append(dataSeparator).append("\n");
            toPrint.append("║ Teacher: ").append(String.format("%-30s", bookingBean.getTeacher())).append("║\n");
            toPrint.append(dataSeparator).append("\n");
            toPrint.append("║ Student: ").append(String.format("%-30s", bookingBean.getStudent())).append("║\n");
            toPrint.append(dataSeparator).append("\n");
            toPrint.append("║ Date: ").append(String.format("%-33s", bookingBean.getDateTime())).append("║\n");
            toPrint.append(dataSeparator).append("\n");
            toPrint.append("║ Confirmed: ").append(String.format("%-28s", bookingBean.getConfirmed())).append("║\n");
            toPrint.append(borderBottom);
            LinePrinter.getInstance().print(toPrint.toString());
        }
    }
}
