package com.example.lessonlink.view2.utility;

public class ErrorPrinter implements Printer {

    private static ErrorPrinter instance = null;

    public static ErrorPrinter getInstance(){
        if(instance == null){
            instance = new ErrorPrinter();
        }
        return instance;
    }
    private ErrorPrinter(){}
    @Override
    public void print(String toPrint) {
        System.err.println(toPrint);
    }
}
