package com.example.lessonlink.view2;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main2 extends Application {
    public static void main(String[] args){
        launch();
    }

    @Override
    public void start(Stage stage) {
        HomeControllerG2 homeControllerG2 = new HomeControllerG2();
        homeControllerG2.start();
    }
}
