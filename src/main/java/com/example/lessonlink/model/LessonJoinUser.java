package com.example.lessonlink.model;

import com.example.lessonlink.model.Lesson;

import java.time.LocalDateTime;

public class LessonJoinUser {
    private Lesson lesson;

    private Teacher teacher;

    private User user;

    public LessonJoinUser(String username, String teachername, LocalDateTime dateTime, boolean isConfirmed, int lessonId, boolean isOnline, boolean isPaid, int teacherId, int studentId) {
        lesson = new Lesson();
        teacher = new Teacher();
        user = new User();
        this.teacher.setName(teachername);
        this.user.setName(username);
        this.lesson.setDateTime(dateTime);
        this.lesson.setIsConfirmed(isConfirmed);
        this.lesson.setLessonId(lessonId);
        this.lesson.setIsOnline(isOnline);
        this.lesson.setIsPaid(isPaid);
        this.lesson.setTeacherId(teacherId);
        this.lesson.setStudentId(studentId);
    }


    public String getTeacher() {
        return teacher.getName();
    }

    public String getStudent() {
        return user.getName();
    }

    public boolean getConfirmed() {
        return lesson.getIsConfirmed();
    }

    public LocalDateTime getDateTime() {
        return lesson.getDateTime();
    }

    public int getLessonId() {
        return lesson.getLessonId();
    }

    public boolean getOnline() {
        return lesson.getIsOnline();
    }

    public boolean getPaid() {
        return lesson.getIsPaid();
    }

    public int getTeacherId() {
        return lesson.getTeacherId();
    }

    public int getStudentId() {
        return lesson.getStudentId();
    }
}
