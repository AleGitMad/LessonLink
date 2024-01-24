package com.example.lessonlink.model.decorator;

import com.example.lessonlink.decorations.*;
import com.example.lessonlink.decorations.Math;
import com.example.lessonlink.model.Teacher;

public interface DecorateFare {
    int MATH_IDX = 0;
    int PHYSICS_IDX = 1;
    int ENGLISH_IDX = 2;
    int HISTORY_IDX = 3;
    int GEOGRAPHY_IDX = 4;
    int ROME_IDX = 5;
    int MILAN_IDX = 6;
    int BERGAMO_IDX = 7;
    int TIVOLI_IDX = 8;
    int HIGH_SCHOOL_IDX = 9;
    int BACHELOR_IDX = 10;
    int MASTER_IDX = 11;
    int ONLINE_IDX = 12;

    int MATH_FARE = 5;
    int PHYSICS_FARE = 5;
    int ENGLISH_FARE = 4;
    int HISTORY_FARE = 4;
    int GEOGRAPHY_FARE = 4;
    int ROME_FARE = 6;
    int MILAN_FARE = 6;
    int BERGAMO_FARE = 4;
    int TIVOLI_FARE = 2;
    int HIGH_SCHOOL_FARE = 5;
    int BACHELOR_FARE = 10;
    int MASTER_FARE = 20;
    int ONLINE_FARE = 2;

    static Educator addDecoration(boolean[] decorationsArray, Teacher teacher){
        Educator educator = teacher;
        String decoration = "0".repeat(13);
        int addFare = 0;

        for (int i = 0; i < decorationsArray.length; i++) {
            if(decorationsArray[i]){
                switch (i) {
                    case MATH_IDX:
                        Math math = new Math(educator);
                        addFare = math.setAddFare();
                        decoration = math.setDecoration();
                        educator = math;
                        break;
                    case PHYSICS_IDX:
                        Physics physics = new Physics(educator);
                        addFare = physics.setAddFare();
                        decoration = physics.setDecoration();
                        educator = physics;
                        break;
                    case ENGLISH_IDX:
                        English english = new English(educator);
                        addFare = english.setAddFare();
                        decoration = english.setDecoration();
                        educator = english;
                        break;
                    case HISTORY_IDX:
                        History history = new History(educator);
                        addFare = history.setAddFare();
                        decoration = history.setDecoration();
                        educator = history;
                        break;
                    case GEOGRAPHY_IDX:
                        Geography geography = new Geography(educator);
                        addFare = geography.setAddFare();
                        decoration = geography.setDecoration();
                        educator = geography;
                        break;
                    case ROME_IDX:
                        Rome rome = new Rome(educator);
                        addFare = rome.setAddFare();
                        decoration = rome.setDecoration();
                        educator = rome;
                        break;
                    case MILAN_IDX:
                        Milan milan = new Milan(educator);
                        addFare = milan.setAddFare();
                        decoration = milan.setDecoration();
                        educator = milan;
                        break;
                    case BERGAMO_IDX:
                        Bergamo bergamo = new Bergamo(educator);
                        addFare = bergamo.setAddFare();
                        decoration = bergamo.setDecoration();
                        educator = bergamo;
                        break;
                    case TIVOLI_IDX:
                        Tivoli tivoli = new Tivoli(educator);
                        addFare = tivoli.setAddFare();
                        decoration = tivoli.setDecoration();
                        educator = tivoli;
                        break;
                    case HIGH_SCHOOL_IDX:
                        HighSchool highSchool = new HighSchool(educator);
                        addFare = highSchool.setAddFare();
                        decoration = highSchool.setDecoration();
                        educator = highSchool;
                        break;
                    case BACHELOR_IDX:
                        Bachelor bachelor = new Bachelor(educator);
                        addFare = bachelor.setAddFare();
                        decoration = bachelor.setDecoration();
                        educator = bachelor;
                        break;
                    case MASTER_IDX:
                        Master master = new Master(educator);
                        addFare = master.setAddFare();
                        decoration = master.setDecoration();
                        educator = master;
                        break;
                    case ONLINE_IDX:
                        Online online = new Online(educator);
                        addFare = online.setAddFare();
                        decoration = online.setDecoration();
                        educator = online;
                        break;
                }
            }
        }

        educator.setFare(addFare);
        educator.setDecoration(decoration);

        return educator;
    }
}
