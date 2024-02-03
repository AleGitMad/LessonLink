package com.example.lessonlink.view2;


import com.example.lessonlink.view2.utility.LinePrinter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AdminHomePageControllerG2 {
    public void setName(String name) throws IOException {
        boolean running = true;

        while (running) {
            String toPrint = "Welcome " + name + " to your homepage.\nPress:\n0 to add a teacher\n1 to check your active bookings\n2 to logout";
            LinePrinter.getInstance().print(toPrint);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String choice = "";
            try {
                choice = reader.readLine();
            } catch (IOException e) {
                //towrite
            }
            switch (choice) {
                case ("0"):
                    CreateTeacherControllerG2 createTeacherControllerG2 = new CreateTeacherControllerG2();
                    createTeacherControllerG2.insertTeacher();
                    break;
                case ("1"):
                    ActiveBookingsControllerG2 activeBookingsControllerG2 = new ActiveBookingsControllerG2();
                    activeBookingsControllerG2.showActiveBookings();
                    break;
                case ("2"):
                    running = false;
                    break;
                default:
                    break;
            }
        }

        HomeControllerG2 homeControllerG2 = new HomeControllerG2();
        homeControllerG2.start();
    }
}
