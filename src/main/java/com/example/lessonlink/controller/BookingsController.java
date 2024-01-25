package com.example.lessonlink.controller;

import com.example.lessonlink.exceptions.FailedResearchException;
import com.example.lessonlink.model.dao.LessonDao;
import com.example.lessonlink.model.LessonJoinUser;
import com.example.lessonlink.view1.bean.BookingBean;

import java.util.ArrayList;
import java.util.List;

public class BookingsController {

    public List<BookingBean> getActiveBookings() throws FailedResearchException {
        List<BookingBean> bookingBeanList = new ArrayList<>();
        LessonDao lessonDao = new LessonDao();
        List<LessonJoinUser> lessonJoinUsers;
        int adminId = 2;
        //adminId = LoggedUser.getInstance().getAdmin().getUserId();

        lessonJoinUsers = lessonDao.findLessoByAdmin(adminId);

        for (LessonJoinUser lessonJoinUser : lessonJoinUsers) {
            BookingBean bookingBean = new BookingBean();
            bookingBean.setDateTime(lessonJoinUser.getDateTime());
            bookingBean.setConfirmed(lessonJoinUser.getConfirmed());
            bookingBean.setLessonId(lessonJoinUser.getTeacher());
            bookingBean.setStudentId(lessonJoinUser.getStudent());
            bookingBeanList.add(bookingBean);
        }
        return bookingBeanList;
    }

    public void sendEmail(List<BookingBean> bookingBeans) {
    }
}
