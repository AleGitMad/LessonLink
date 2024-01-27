package com.example.lessonlink.controller;

import com.example.lessonlink.exceptions.FailedResearchException;
import com.example.lessonlink.model.*;
import com.example.lessonlink.model.dao.LessonDao;
import com.example.lessonlink.model.dao.ReviewDao;
import com.example.lessonlink.model.dao.TeacherDao;
import com.example.lessonlink.view1.HistoryPageControllerG;
import com.example.lessonlink.view1.bean.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookLessonController {

    public List<TeacherBean> search(ResearchBean researchBean) throws FailedResearchException {
        TeacherDao teacherDao = new TeacherDao();
        List<Teacher> teachers = teacherDao.findTeachers(researchBean.getSubject(), researchBean.getWhere(), researchBean.getIsOnline());
        //teacherBean populated with teacher (entity) data
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

    public void insertLesson(LessonBean lessonBean) {
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

    public AccountBean getAccountBean() {
        AccountBean accountBean = new AccountBean();
        accountBean.setName(LoggedUser.getInstance().getStudent().getName());
        accountBean.setRole(LoggedUser.getInstance().getRole());
        return accountBean;
    }

    public List<LessonBean> getLessons() throws FailedResearchException {
        LessonDao lessonDao = new LessonDao();
        ReviewDao reviewDao = new ReviewDao();
        List<LessonJoinTeacher> lessons = lessonDao.findStudentLessons(LoggedUser.getInstance().getStudent().getUserId());
        List<LessonBean> lessonBeans = new ArrayList<>();
        for (LessonJoinTeacher lesson : lessons) {
            LessonBean lessonBean = new LessonBean();
            lessonBean.setLessonDate(lesson.getLesson().getDateTime().toLocalDate());
            lessonBean.setLessonDateTime(lesson.getLesson().getDateTime());
            lessonBean.setTeacherId(lesson.getLesson().getTeacherId());
            lessonBean.setIsConfirmed(lesson.getLesson().getIsConfirmed());
            lessonBean.setIsPaid(lesson.getLesson().getIsPaid());
            lessonBean.setTeacherName(lesson.getTeacher().getName());
            lessonBean.setAverageRating(reviewDao.getAverageRating(lesson.getTeacher().getTeacherId()));
            lessonBeans.add(lessonBean);
        }
        return lessonBeans;
    }

    public void attachObserverToTeacher(HistoryPageControllerG historyPageControllerG, Teacher teacher) {
        teacher.attach(historyPageControllerG);
    }

    public void insertReview(ReviewBean reviewBean) {
        Review review = new Review();
        fillReview(review, reviewBean);
        ReviewDao reviewDao = new ReviewDao();
        try {
            reviewDao.insertReview(review);
            //chiama metodo per ottenere il teacher dal database
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void fillReview(Review review, ReviewBean reviewBean) {
        review.setStars((int) reviewBean.getStars());
        review.setTeacherId(reviewBean.getTeacherId());
        review.setDate(reviewBean.getDate());
        if (reviewBean.getComment() != null) {
            review.setComment(reviewBean.getComment());
        }
    }
}
