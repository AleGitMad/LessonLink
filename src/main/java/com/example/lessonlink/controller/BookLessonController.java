package com.example.lessonlink.controller;

import com.example.lessonlink.exceptions.FailedResearchException;
import com.example.lessonlink.model.Lesson;
import com.example.lessonlink.model.dao.TeacherDao;
import com.example.lessonlink.model.decorator.Teacher;
import com.example.lessonlink.view1.bean.ResearchBean;
import com.example.lessonlink.view1.bean.ResultsBean;
import com.example.lessonlink.view1.bean.TeacherBean;

import java.util.List;

public class BookLessonController {
    private Lesson lesson;

    public ResultsBean search(ResearchBean researchBean) throws FailedResearchException {
        TeacherDao teacherDao = new TeacherDao();
        ResultsBean resultsBean = new ResultsBean();
        resultsBean.setTeachers(teacherDao.findTeachers(researchBean.getSubject(), researchBean.getWhere(), researchBean.getIsOnline()));
        return resultsBean;
    }


}
