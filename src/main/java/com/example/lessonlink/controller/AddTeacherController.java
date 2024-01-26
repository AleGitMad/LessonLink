package com.example.lessonlink.controller;

import com.example.lessonlink.model.LoggedUser;
import com.example.lessonlink.model.dao.TeacherDao;
import com.example.lessonlink.model.decorator.DecorateFare;
import com.example.lessonlink.model.decorator.Educator;
import com.example.lessonlink.model.Teacher;
import com.example.lessonlink.view1.bean.AccountBean;
import com.example.lessonlink.view1.bean.ProfileTeacherBean;

import java.sql.SQLException;

public class AddTeacherController {
    public void addTeacher(ProfileTeacherBean profileTeacherBean) {
        Educator teacher = new Teacher();
        teacher.setSubject1(profileTeacherBean.getSubject1C());
        teacher.setSubject2(profileTeacherBean.getSubject2C());
        teacher.setSubject3(profileTeacherBean.getSubject3C());
        teacher.setQualification(profileTeacherBean.getQualificationC());
        teacher.setCity(profileTeacherBean.getCityC());
        teacher.setAvailableOnline(profileTeacherBean.getOnlineC());

        teacher = DecorateFare.addDecoration(profileTeacherBean.getDecorations(), (Teacher)teacher);
        profileTeacherBean.setFare(Integer.toString(teacher.getFare()));
    }


    public void confirmTeacher(ProfileTeacherBean profileTeacherBean) throws SQLException {
        Teacher teacher = new Teacher();
        fillTeacher(teacher, profileTeacherBean);
        TeacherDao teacherDao = new TeacherDao();
        try {
            teacherDao.saveTeacher(teacher);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void fillTeacher(Teacher teacher, ProfileTeacherBean profileTeacherBean){
        LoggedUser loggedUser = LoggedUser.getInstance();

        //teacher.setAdminId(loggedUser.getAdmin().getUserId()); TODO fix loggedUser
        teacher.setAdminId(2);

        teacher.setName(profileTeacherBean.name());
        teacher.setSubject1(profileTeacherBean.getSubject1C());
        teacher.setSubject2(profileTeacherBean.getSubject2C());
        teacher.setSubject3(profileTeacherBean.getSubject3C());
        teacher.setQualification(profileTeacherBean.getQualificationC());
        teacher.setCity(profileTeacherBean.getCityC());
        teacher.setAvailableOnline(profileTeacherBean.getOnlineC());
        teacher.setFare(profileTeacherBean.getFareC());
    }

    public AccountBean getAccountBean() {
        AccountBean accountBean = new AccountBean();
        accountBean.setName(LoggedUser.getInstance().getStudent().getName());
        accountBean.setRole(LoggedUser.getInstance().getRole());
        return accountBean;
    }
}
