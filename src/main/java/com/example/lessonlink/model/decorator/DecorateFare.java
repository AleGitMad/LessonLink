package com.example.lessonlink.model.decorator;

import com.example.lessonlink.model.decorator.Educator;

public interface DecorateFare {
    int MATH_IDX = 1;
    int PHYSICS_IDX = 2;
    int ENGLISH_IDX = 3;
    int HISTORY_IDX = 4;
    int GEOGRAPHY_IDX = 5;
    int ROME_IDX = 6;
    int MILAN_IDX = 7;
    int BERGAMO_IDX = 8;
    int TIVOLI_IDX = 9;
    int DIPLOMA_IDX = 10;
    int DEGREE_IDX = 11;
    int MASTER_IDX = 12;
    int ONLINE_IDX = 13;

    int MATH_FARE = 5;
    int PHYSICS_FARE = 5;
    int ENGLISH_FARE = 4;
    int HISTORY_FARE = 4;
    int GEOGRAPHY_FARE = 4;
    int ROME_FARE = 6;
    int MILAN_FARE = 6;
    int BERGAMO_FARE = 4;
    int TIVOLI_FARE = 2;
    int DIPLOMA_FARE = 5;
    int DEGREE_FARE = 10;
    int MASTER_FARE = 20;
    int ONLINE_FARE = 5;

    static Educator addDecoration(boolean[] decorationsArray, Educator educator){
        return null;
    }
}
