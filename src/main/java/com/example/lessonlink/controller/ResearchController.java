package com.example.lessonlink.controller;

public class ResearchController {
    private static ResearchController instance = null;

    public static ResearchController getInstance() {
        if(instance == null){
            instance = new ResearchController();
        }
        return instance;
    }

}
