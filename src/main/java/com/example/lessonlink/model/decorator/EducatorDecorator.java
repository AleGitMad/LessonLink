package com.example.lessonlink.model.decorator;

public abstract class EducatorDecorator extends Educator implements DecorateFare {
    protected Educator educator;

    protected EducatorDecorator(Educator educator){
        this.educator = educator;
    }

    public int setAddFare(){
        return educator.setAddFare();
    }
    public String setDecoration(){
        return educator.setDecoration();
    }
}
