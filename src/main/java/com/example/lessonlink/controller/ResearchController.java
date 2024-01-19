package com.example.lessonlink.controller;

import com.example.lessonlink.exceptions.FailedResearchException;
import com.example.lessonlink.model.Teacher;
import com.example.lessonlink.model.dao.TeacherDao;
import com.example.lessonlink.view1.bean.ResearchBean;
import com.example.lessonlink.view1.bean.TeacherBean;

import java.util.List;
/*
public class ResearchController {

    public TeacherBean search(ResearchBean researchBean) throws FailedResearchException {
        List<Teacher> teachers = null;
        teachers = TeacherDao.findTeachers(researchBean.getSubject(), researchBean.getWhere(), researchBean.getIsOnline());
        TeacherBean teacherBean = new TeacherBean();
        teacherBean.setTeachers(teachers);
        return teacherBean;
    }

}
*/