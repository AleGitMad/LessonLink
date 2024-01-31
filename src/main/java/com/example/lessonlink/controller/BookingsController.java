package com.example.lessonlink.controller;

import com.example.lessonlink.exceptions.FailedResearchException;
import com.example.lessonlink.model.Lesson;
import com.example.lessonlink.model.LoggedUser;
import com.example.lessonlink.model.Teacher;
import com.example.lessonlink.model.dao.LessonDao;
import com.example.lessonlink.model.LessonJoinUser;
import com.example.lessonlink.model.dao.TeacherDao;
import com.example.lessonlink.view1.bean.BookingBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingsController {
    private List<LessonJoinUser> lessonJoinUsers;

    public List<BookingBean> getActiveBookings() throws FailedResearchException {
        List<BookingBean> bookingBeanList = new ArrayList<>();
        LessonDao lessonDao = new LessonDao();
        int adminId = LoggedUser.getInstance().getAdmin().getUserId();

        lessonJoinUsers = lessonDao.findLessoByAdmin(adminId);

        for (LessonJoinUser lessonJoinUser : lessonJoinUsers) {
            BookingBean bookingBean = new BookingBean();
//            Lesson lesson = new Lesson();
//
//            lesson.setDateTime(lessonJoinUser.getDateTime());
//            lesson.setIsConfirmed(lessonJoinUser.getConfirmed());
//            lesson.setLessonId(lessonJoinUser.getLessonId());
//            lesson.setIsOnline(lessonJoinUser.getOnline());
//            lesson.setIsPaid(lessonJoinUser.getPaid());
//            lesson.setTeacherId(lessonJoinUser.getTeacherId());
//            lesson.setStudentId(lessonJoinUser.getStudentId());


            bookingBean.setTeacherName(lessonJoinUser.getTeacher());
            bookingBean.setStudentName(lessonJoinUser.getStudent());
            bookingBean.setDate(lessonJoinUser.getDateTime());
            bookingBean.setLessonId(lessonJoinUser.getLessonId());
            bookingBean.setIsConfirmed(lessonJoinUser.getConfirmed());
            bookingBean.setTeacherId(lessonJoinUser.getTeacherId());
            bookingBean.setStudentId(lessonJoinUser.getStudentId());
            bookingBeanList.add(bookingBean);
        }
        return bookingBeanList;
    }

    public void sendEmail(BookingBean bookingBean) {
        LessonDao lessonDao = new LessonDao();
        for (LessonJoinUser lessonJoinUser : lessonJoinUsers) {
            if (lessonJoinUser.getLessonId() == bookingBean.getLessonId()) {
                lessonJoinUser.getLesson().setIsConfirmed(true);
                lessonDao.updateLesson(lessonJoinUser.getLesson());
                break;
            }
        }
//        Lesson lesson = lessonJoinUsers.get(bookingBean.getLessonId()).getLesson();
//        lesson.setIsConfirmed(true);
//
//        lessonDao.updateLesson(lesson);
    }
}
