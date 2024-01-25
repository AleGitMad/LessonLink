module com.example.lessonlink {
        requires javafx.controls;
        requires javafx.fxml;
    requires java.sql;

    exports com.example.lessonlink.view1;
        opens com.example.lessonlink.controller;
        opens com.example.lessonlink.view1;
    exports com.example.lessonlink.model;
    exports com.example.lessonlink.model.decorator;
}