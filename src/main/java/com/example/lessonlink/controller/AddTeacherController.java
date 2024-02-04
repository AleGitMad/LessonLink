package com.example.lessonlink.controller;

import com.example.lessonlink.exceptions.FailedInsertException;
import com.example.lessonlink.model.LoggedUser;
import com.example.lessonlink.model.dao.TeacherDao;
import com.example.lessonlink.model.decorator.DecorateFare;
import com.example.lessonlink.model.decorator.Educator;
import com.example.lessonlink.model.Teacher;
import com.example.lessonlink.view1.bean.AccountBean;
import com.example.lessonlink.view1.bean.ProfileTeacherBean;

import java.sql.SQLException;

public class AddTeacherController {
    private final Teacher teacher = new Teacher();

    public void addTeacher(ProfileTeacherBean profileTeacherBean) {
        Educator educator = new Teacher();
        educator.setSubject1(profileTeacherBean.getSubject1C());
        educator.setSubject2(profileTeacherBean.getSubject2C());
        educator.setSubject3(profileTeacherBean.getSubject3C());
        educator.setQualification(profileTeacherBean.getQualificationC());
        educator.setCity(profileTeacherBean.getCityC());
        educator.setAvailableOnline(profileTeacherBean.getOnlineC());

        educator = DecorateFare.addDecoration(profileTeacherBean.getDecorations(), (Teacher)educator);
        profileTeacherBean.setFare(Integer.toString(educator.getFare()));
        fillTeacher(teacher, profileTeacherBean);
    }


    public void confirmTeacher(ProfileTeacherBean profileTeacherBean) throws FailedInsertException {
        teacher.setFare(profileTeacherBean.getFareC());
        TeacherDao teacherDao = new TeacherDao();
        teacherDao.saveTeacher(teacher);
    }

    private void fillTeacher(Teacher teacher, ProfileTeacherBean profileTeacherBean){
        LoggedUser loggedUser = LoggedUser.getInstance();

        teacher.setAdminId(loggedUser.getAdmin().getUserId());

        teacher.setName(profileTeacherBean.name());
        teacher.setSubject1(profileTeacherBean.getSubject1C());
        teacher.setSubject2(profileTeacherBean.getSubject2C());
        teacher.setSubject3(profileTeacherBean.getSubject3C());
        teacher.setQualification(profileTeacherBean.getQualificationC());
        teacher.setCity(profileTeacherBean.getCityC());
        teacher.setAvailableOnline(profileTeacherBean.getOnlineC());
    }

    public AccountBean getAccountBean() {
        AccountBean accountBean = new AccountBean();
        accountBean.setName(LoggedUser.getInstance().getAdmin().getName());
        accountBean.setRole(LoggedUser.getInstance().getRole());
        return accountBean;
    }
}