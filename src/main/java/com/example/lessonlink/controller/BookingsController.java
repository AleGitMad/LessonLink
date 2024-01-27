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

    public List<BookingBean> getActiveBookings() throws FailedResearchException {
        List<BookingBean> bookingBeanList = new ArrayList<>();
        LessonDao lessonDao = new LessonDao();
        List<LessonJoinUser> lessonJoinUsers;
        int adminId = LoggedUser.getInstance().getAdmin().getUserId();

        lessonJoinUsers = lessonDao.findLessoByAdmin(adminId);

        for (LessonJoinUser lessonJoinUser : lessonJoinUsers) {
            BookingBean bookingBean = new BookingBean();
            Lesson lesson = new Lesson();

            lesson.setDateTime(lessonJoinUser.getDateTime());
            lesson.setIsConfirmed(lessonJoinUser.getConfirmed());
            lesson.setLessonId(lessonJoinUser.getLessonId());
            lesson.setIsOnline(lessonJoinUser.getOnline());
            lesson.setIsPaid(lessonJoinUser.getPaid());
            lesson.setTeacherId(lessonJoinUser.getTeacherId());
            lesson.setStudentId(lessonJoinUser.getStudentId());


            bookingBean.setTeacherName(lessonJoinUser.getTeacher());
            bookingBean.setStudentName(lessonJoinUser.getStudent());
            bookingBean.setLesson(lesson);
            bookingBeanList.add(bookingBean);
        }
        return bookingBeanList;
    }

    public void sendEmail(BookingBean bookingBean) {
        LessonDao lessonDao = new LessonDao();
        Lesson lesson = bookingBean.getLesson();
        lesson.setIsConfirmed(true);

        lessonDao.updateLesson(lesson);
    }
}
