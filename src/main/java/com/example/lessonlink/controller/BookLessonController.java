package com.example.lessonlink.controller;

import com.example.lessonlink.exceptions.FailedResearchException;
import com.example.lessonlink.model.Lesson;
import com.example.lessonlink.model.dao.TeacherDao;
import com.example.lessonlink.model.decorator.Teacher;
import com.example.lessonlink.view1.bean.ResearchBean;
import com.example.lessonlink.view1.bean.TeacherBean;

import java.util.ArrayList;
import java.util.List;

public class BookLessonController {
    private Lesson lesson;

    public List<TeacherBean> search(ResearchBean researchBean) throws FailedResearchException {
        TeacherDao teacherDao = new TeacherDao();
        List<Teacher> teachers = teacherDao.findTeachers(researchBean.getSubject(), researchBean.getWhere(), researchBean.getIsOnline());
        List<TeacherBean> teacherBeans = new ArrayList<>();
        for (Teacher teacher : teachers) {
            TeacherBean teacherBean = new TeacherBean();
            teacherBean.setTeacherName(teacher.getName());
            teacherBean.setTeacherAverageRating(teacher.getAverageRating());
            teacherBean.setTeacherFare(teacher.getFare());
            teacherBean.setTeacherHasReview(teacher.getHasReviews());
            teacherBeans.add(teacherBean);
        }
        return teacherBeans;
    }


}
