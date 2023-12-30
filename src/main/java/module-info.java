module com.example.lessonlink {
        requires javafx.controls;
        requires javafx.fxml;

        exports com.example.lessonlink.view1;
        opens com.example.lessonlink.controller;
        opens com.example.lessonlink.view1;
}