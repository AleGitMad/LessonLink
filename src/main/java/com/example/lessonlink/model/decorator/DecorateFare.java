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
        Result result;

        for (int i = 0; i < decorationsArray.length; i++) {
            if(decorationsArray[i]){
                result = swithFunction(i, educator);
                educator = result.getEducator();
                addFare = result.getAddFare();
                decoration = result.getDecoration();
            }
        }

        educator.setFare(addFare);
        educator.setDecoration(decoration);

        return educator;
    }

    private static Result swithFunction(int i, Educator educator){
        Result result = new Result();
        switch (i) {
        case MATH_IDX:
            Math math = new Math(educator);
            result.setAddFare(math.setAddFare());
            result.setDecoration(math.setDecoration());
            result.setEducator(math);
            break;
        case PHYSICS_IDX:
            Physics physics = new Physics(educator);
            result.setAddFare(physics.setAddFare());
            result.setDecoration(physics.setDecoration());
            result.setEducator(physics);
            break;
        case ENGLISH_IDX:
            English english = new English(educator);
            result.setAddFare(english.setAddFare());
            result.setDecoration(english.setDecoration());
            result.setEducator(english);
            break;
        case HISTORY_IDX:
            History history = new History(educator);
            result.setAddFare(history.setAddFare());
            result.setDecoration(history.setDecoration());
            result.setEducator(history);
            break;
        case GEOGRAPHY_IDX:
            Geography geography = new Geography(educator);
            result.setAddFare(geography.setAddFare());
            result.setDecoration(geography.setDecoration());
            result.setEducator(geography);
            break;
        case ROME_IDX:
            Rome rome = new Rome(educator);
            result.setAddFare(rome.setAddFare());
            result.setDecoration(rome.setDecoration());
            result.setEducator(rome);
            break;
        case MILAN_IDX:
            Milan milan = new Milan(educator);
            result.setAddFare(milan.setAddFare());
            result.setDecoration(milan.setDecoration());
            result.setEducator(milan);
            break;
        case BERGAMO_IDX:
            Bergamo bergamo = new Bergamo(educator);
            result.setAddFare(bergamo.setAddFare());
            result.setDecoration(bergamo.setDecoration());
            result.setEducator(bergamo);
            break;
        case TIVOLI_IDX:
            Tivoli tivoli = new Tivoli(educator);
            result.setAddFare(tivoli.setAddFare());
            result.setDecoration(tivoli.setDecoration());
            result.setEducator(tivoli);
            break;
        case HIGH_SCHOOL_IDX:
            HighSchool highSchool = new HighSchool(educator);
            result.setAddFare(highSchool.setAddFare());
            result.setDecoration(highSchool.setDecoration());
            result.setEducator(highSchool);
            break;
        case BACHELOR_IDX:
            Bachelor bachelor = new Bachelor(educator);
            result.setAddFare(bachelor.setAddFare());
            result.setDecoration(bachelor.setDecoration());
            result.setEducator(bachelor);
            break;
        case MASTER_IDX:
            Master master = new Master(educator);
            result.setAddFare(master.setAddFare());
            result.setDecoration(master.setDecoration());
            result.setEducator(master);
            break;
        case ONLINE_IDX:
            Online online = new Online(educator);
            result.setAddFare(online.setAddFare());
            result.setDecoration(online.setDecoration());
            result.setEducator(online);
            break;
        default:
            break;
    }
    return result;
    }

}






