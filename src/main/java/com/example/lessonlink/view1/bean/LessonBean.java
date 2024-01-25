package com.example.lessonlink.view1.bean;

import java.time.*;
import java.util.Date;

public class LessonBean {
    private int teacherId;
    private LocalDateTime lessonDateTime;

    private LocalDate lessonDate;
    private String lessonTime;

    private boolean isOnline;

    private boolean isPaid;

    private boolean isConfirmed;

    private String teacherName;

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public boolean getIsConfirmed() {
        return isConfirmed;
    }

    public void setIsConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }

    public boolean getIsOnline() {
        return isOnline;
    }
    public void setIsOnline(boolean online) {
        isOnline = online;
    }

    public boolean getIsPaid() {
        return isPaid;
    }
    public void setIsPaid(boolean paid) {
        isPaid = paid;
    }

    public LocalDate getLessonDate() {
        return lessonDate;
    }
    public void setLessonDate(LocalDate lessonDate) {
        this.lessonDate = lessonDate;
    }
    public String getLessonTime() {
        return lessonTime;
    }
    public void setLessonTime(String lessonTime) {
        this.lessonTime = lessonTime;
    }

    public int getTeacherId() {
        return teacherId;
    }
    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public void setLessonDateTimeFrom(Date date, String time) {
        LocalDate localDate = Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        LocalTime localTime = LocalTime.parse(time);
        this.lessonDateTime = LocalDateTime.of(localDate, localTime);
    }

    public LocalDateTime getLessonDateTime() {
        return lessonDateTime;
    }

    public boolean validate() {
        LocalDate nowDate = LocalDate.now();
        LocalTime nowTime = LocalTime.now();
        if (teacherId != -1 && lessonDate != null && lessonTime != null) {
            LocalTime lessonLocalTime = LocalTime.parse(lessonTime);
            return lessonDate.isAfter(nowDate) || (lessonDate.isEqual(nowDate) && lessonLocalTime.isAfter(nowTime));
        }
        return false;
    }
}
