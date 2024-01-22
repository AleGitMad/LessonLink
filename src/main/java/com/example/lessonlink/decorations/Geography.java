package com.example.lessonlink.decorations;

import com.example.lessonlink.model.decorator.DecorateFare;
import com.example.lessonlink.model.decorator.Educator;
import com.example.lessonlink.model.decorator.EducatorDecorator;

public class Geography extends EducatorDecorator implements DecorateFare {
    private int myFare;
    private int idx;
    public Geography(Educator educator){
        super(educator);
        this.setIdx(GEOGRAPHY_IDX);
        this.setFare(GEOGRAPHY_FARE);
    }

    public void setFare(int fare){
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
