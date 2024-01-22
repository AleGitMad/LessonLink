package com.example.lessonlink.model.decorator;

public abstract class EducatorDecorator extends Educator implements DecorateFare {
    protected Educator educator;

    protected EducatorDecorator(Educator educator){
        this.educator = educator;
    }

    @Override
    public int setAddFare(){
        return educator.setAddFare();
    }

    @Override
    public String setDecoration(){
        return this.educator.setDecoration();
    }
}
