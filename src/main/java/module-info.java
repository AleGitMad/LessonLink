module com.example.lessonlink {
        requires javafx.controls;
        requires javafx.fxml;
    requires java.sql;

    exports com.example.lessonlink.view1;
        opens com.example.lessonlink.controller;
        opens com.example.lessonlink.view1;
}