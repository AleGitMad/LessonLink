package com.example.lessonlink.controller;

import com.example.lessonlink.exceptions.FailedResearchException;
import com.example.lessonlink.model.Lesson;
import com.example.lessonlink.model.LoggedUser;
import com.example.lessonlink.model.dao.LessonDao;
import com.example.lessonlink.model.dao.TeacherDao;
import com.example.lessonlink.model.Teacher;
import com.example.lessonlink.view1.bean.LessonBean;
import com.example.lessonlink.view1.bean.ResearchBean;
import com.example.lessonlink.view1.bean.TeacherBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookLessonController {

    public List<TeacherBean> search(ResearchBean researchBean) throws FailedResearchException {
        TeacherDao teacherDao = new TeacherDao();
        List<Teacher> teachers = teacherDao.findTeachers(researchBean.getSubject(), researchBean.getWhere(), researchBean.getIsOnline());
        List<TeacherBean> teacherBeans = new ArrayList<>();
        for (Teacher teacher : teachers) {
            TeacherBean teacherBean = new TeacherBean();
            teacherBean.setTeacherId(teacher.getTeacherId());
            teacherBean.setTeacherName(teacher.getName());
            teacherBean.setTeacherAverageRating(teacher.getAverageRating());
            teacherBean.setTeacherFare(teacher.getFare());
            teacherBean.setTeacherHasReview(teacher.getHasReviews());
            teacherBeans.add(teacherBean);
        }
        return teacherBeans;
    }

    public boolean checkSlotAvailability(LessonBean lessonBean) throws FailedResearchException{
        LessonDao lessonDao = new LessonDao();
        return lessonDao.findTeacherLessons(lessonBean.getTeacherId(), lessonBean.getLessonDateTime());
    }

    public void insertLesson(LessonBean lessonBean) throws FailedResearchException{
        Lesson lesson = new Lesson();
        fillLesson(lesson, lessonBean);
        LessonDao lessonDao = new LessonDao();
        try {
            lessonDao.insertLesson(lesson);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void fillLesson(Lesson lesson, LessonBean lessonBean) {
        lesson.setDateTime(lessonBean.getLessonDateTime());
        lesson.setIsOnline(lessonBean.getIsOnline());
        lesson.setTeacherId(lessonBean.getTeacherId());
        lesson.setStudentId(LoggedUser.getInstance().getStudent().getUserId());
        lesson.setIsConfirmed(false);
        lesson.setIsPaid(lessonBean.getIsPaid());
    }

}
