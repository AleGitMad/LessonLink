package com.example.lessonlink.model.decorator;

public abstract class EducatorDecorator extends Educator {
    private Educator educator;

    protected EducatorDecorator(Educator educator){
        this.educator = educator;
    }

    @Override
    public int setAddFare(){
        return this.educator.setAddFare();
    }

    @Override
    public String setDecoration(){
        return this.educator.setDecoration();
    }
}
