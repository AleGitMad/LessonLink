package com.example.lessonlink.controller;

import com.example.lessonlink.exceptions.FailedResearchException;
import com.example.lessonlink.model.Lesson;
import com.example.lessonlink.model.dao.TeacherDao;
import com.example.lessonlink.view1.bean.ResearchBean;
import com.example.lessonlink.view1.bean.TeacherBean;

public class BookLessonController {
    private Lesson lesson;

    public TeacherBean search(ResearchBean researchBean) throws FailedResearchException {
        TeacherDao teacherDao = new TeacherDao();
        TeacherBean teacherBean = new TeacherBean();
        teacherBean.setTeachers(teacherDao.findTeachers(researchBean.getSubject(), researchBean.getWhere(), researchBean.getIsOnline()));
        return teacherBean;
    }


}
