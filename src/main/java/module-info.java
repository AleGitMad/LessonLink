module com.example.lessonlink {
        requires javafx.controls;
        requires javafx.fxml;
    requires java.sql;

    exports com.example.lessonlink.view1;
        opens com.example.lessonlink.controller;
        opens com.example.lessonlink.view1;
    exports com.example.lessonlink.model;
    exports com.example.lessonlink.model.decorator;
    exports com.example.lessonlink.view1.bean;
    exports com.example.lessonlink.model.observer;
    exports com.example.lessonlink.view2 to javafx.graphics;
    exports com.example.lessonlink.controller;
    exports com.example.lessonlink.exceptions;

}