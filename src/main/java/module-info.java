module com.example.lessonlink {
        requires javafx.controls;
        requires javafx.fxml;

        opens com.example.lessonlink.view1 to javafx.fxml;
        exports com.example.lessonlink.view1;
}