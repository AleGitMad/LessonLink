package com.example.lessonlink.controller;

import com.example.lessonlink.model.decorator.DecorateFare;
import com.example.lessonlink.model.decorator.Educator;
import com.example.lessonlink.model.decorator.Teacher;
import com.example.lessonlink.view1.bean.ProfileTeacherBean;

import java.util.Objects;

public class AddTeacherController {

    private Educator teacher;


    public void addTeacher(ProfileTeacherBean profileTeacherBean) {
        teacher = new Teacher();
        teacher.setSubject1(profileTeacherBean.getSubject1C());
        teacher.setSubject2(profileTeacherBean.getSubject2C());
        teacher.setSubject3(profileTeacherBean.getSubject3C());
        teacher.setQualification(profileTeacherBean.getQualificationC());
        teacher.setCity(profileTeacherBean.getCityC());
        teacher.setAvailableOnline(profileTeacherBean.getOnlineC());

        teacher = DecorateFare.addDecoration(profileTeacherBean.getDecorations(), (Teacher)teacher);
        teacher.setFare(teacher.setAddFare());
        profileTeacherBean.setFare(Integer.toString(teacher.getFare()) + "€/h");
    }

    public void useEvaluatedFare(ProfileTeacherBean profileTeacherBean){
        // TODO implement here
    }

    public void useOtherFare(ProfileTeacherBean profileTeacherBean){
        // TODO implement here
    }

    public void confirmTeacher() {
        // TODO implement here the savage to db
    }

    public void getEvaluatedFare(ProfileTeacherBean profileTeacherBean) {
    }
}
