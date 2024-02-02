package com.example.lessonlink.controller;

import com.example.lessonlink.exceptions.FailedInsertException;
import com.example.lessonlink.exceptions.FailedResearchException;
import com.example.lessonlink.model.*;
import com.example.lessonlink.model.dao.LessonDao;
import com.example.lessonlink.model.dao.ReviewDao;
import com.example.lessonlink.model.dao.TeacherDao;
import com.example.lessonlink.view1.bean.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookLessonController {

    //keep track of the teachers the student had lessons with
    List<Teacher> teachersInHistory = new ArrayList<>();

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

    public boolean checkSlotAvailability(LessonBean lessonBean) throws FailedResearchException {
        LessonDao lessonDao = new LessonDao();
        return lessonDao.findTeacherLessons(lessonBean.getTeacherId(), lessonBean.getLessonDateTime());
    }

    public void insertLesson(LessonBean lessonBean) throws FailedInsertException {
        Lesson lesson = new Lesson();
        fillLesson(lesson, lessonBean);
        LessonDao lessonDao = new LessonDao();
        try {
            lessonDao.insertLesson(lesson);
        } catch (Exception e) {
            e.printStackTrace();
            throw new FailedInsertException("An error during insertion occurred.");
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

            //lessonBean population
            LessonBean lessonBean = new LessonBean();
            lessonBean.setLessonDate(lesson.getLesson().getDateTime().toLocalDate());
            lessonBean.setLessonDateTime(lesson.getLesson().getDateTime());
            lessonBean.setTeacherId(lesson.getLesson().getTeacherId());
            lessonBean.setIsConfirmed(lesson.getLesson().getIsConfirmed());
            lessonBean.setIsPaid(lesson.getLesson().getIsPaid());

            lessonBean.setTeacherName(lesson.getTeacher().getName());
            float[] ratingAndReviews = reviewDao.getAverageRatingAndTotalReviews(lesson.getTeacher().getTeacherId());
            lessonBean.setAverageRating(ratingAndReviews[0]);

            lessonBeans.add(lessonBean);

            //teachersInHistory population
            Teacher teacher = lesson.getTeacher();
            teacher.setAverageRating(ratingAndReviews[0]);
            teacher.setTotalReviews((int) ratingAndReviews[1]);
            this.teachersInHistory.add(teacher);
        }
        return lessonBeans;
    }

    public Teacher getTeacherById(int teacherId) {
        Optional<Teacher> teacherOptional = teachersInHistory.stream().filter(teacher ->
                        teacher.getTeacherId() == teacherId).findFirst();
        if (teacherOptional.isPresent()) {
            return teacherOptional.get();
        } else {
            return null;
        }
    }

    public void insertReview(ReviewBean reviewBean, Teacher teacherToUpdate) throws FailedInsertException {
        Review review = new Review();
        fillReview(review, reviewBean);
        ReviewDao reviewDao = new ReviewDao();
        try {
            //modifica valore averageReview
            teacherToUpdate.setTotalReviews(teacherToUpdate.getTotalReviews() + 1);
            teacherToUpdate.setAverageRating(
                    (teacherToUpdate.getAverageRating()*(teacherToUpdate.getTotalReviews()-1)
                            + reviewBean.getStars()) / teacherToUpdate.getTotalReviews());
            //insert review in db
            reviewDao.insertReview(review);
        } catch (Exception e) {
            e.printStackTrace();
            throw new FailedInsertException("An error during insertion occurred.");
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
