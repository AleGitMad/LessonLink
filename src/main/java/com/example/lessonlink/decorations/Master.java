package com.example.lessonlink.decorations;

import com.example.lessonlink.model.decorator.DecorateFare;
import com.example.lessonlink.model.decorator.Educator;
import com.example.lessonlink.model.decorator.EducatorDecorator;

public class Master extends EducatorDecorator implements DecorateFare {
    private int myFare;
    private int idx;
    public Master(Educator educator){
        super(educator);
        this.setIdx(MASTER_IDX);
        this.setMyFare(MASTER_FARE);
    }

    public void setMyFare(int fare){
        this.myFare = fare;
    }
    public void setIdx(int idx){
        this.idx = idx;
    }

    private int applyFare(int input) {
        return this.myFare + input;
    }

    private String applyDecoration(String input) {
        return input.substring(0, idx) + "1" + input.substring(idx + 1);
    }

    @Override
    public int setAddFare(){
        int preliminaryFare = super.setAddFare();
        preliminaryFare = this.applyFare(preliminaryFare);
        return preliminaryFare;
    }

    @Override
    public String setDecoration(){
        String preliminaryDecoration = super.setDecoration();
        preliminaryDecoration = this.applyDecoration(preliminaryDecoration);
        return preliminaryDecoration;
    }
}
