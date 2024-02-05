package com.example.lessonlink.controller;

import com.example.lessonlink.exceptions.FailedResearchException;
import com.example.lessonlink.exceptions.FailedUpdateException;
import com.example.lessonlink.model.LoggedUser;
import com.example.lessonlink.model.dao.LessonDao;
import com.example.lessonlink.model.LessonJoinUser;
import com.example.lessonlink.view1.bean.AccountBean;
import com.example.lessonlink.view1.bean.BookingBean;

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

    public void sendEmail(BookingBean bookingBean) throws FailedUpdateException {
        LessonDao lessonDao = new LessonDao();
        for (LessonJoinUser lessonJoinUser : lessonJoinUsers) {
            if (lessonJoinUser.getLessonId() == bookingBean.getLessonId()) {
                lessonJoinUser.getLesson().setIsConfirmed(true);
                lessonDao.updateLesson(lessonJoinUser.getLesson());
                break;
            }
        }
    }

    public AccountBean getAccountBean() {
        AccountBean accountBean = new AccountBean();
        accountBean.setName(LoggedUser.getInstance().getAdmin().getName());
        accountBean.setRole(LoggedUser.getInstance().getRole());
        return accountBean;
    }
}
