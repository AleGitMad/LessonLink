package com.example.lessonlink.view2.utility;

public class LinePrinter implements Printer {

    private static LinePrinter instance = null;

    public static LinePrinter getInstance(){
        if(instance == null){
            instance = new LinePrinter();
        }
        return instance;
    }
    private LinePrinter(){}

    @Override
    public void print(String toPrint){
        System.out.println(toPrint);
    }

    public void clearConsole(){
        for (int i = 0; i < 50; ++i){
            print("\n");
        }
    }

}
