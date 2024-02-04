package com.example.lessonlink.model;

import java.time.LocalDateTime;

public class LessonJoinUser {
    private Lesson lesson = new Lesson();

    private Teacher teacher = new Teacher();

    private User user = new User();

    //three methods to separate entities while populating the object
    public void initLesson(int lessonId, LocalDateTime dateTime, boolean isOnline, int teacherId, int studentId, boolean isConfirmed, boolean isPaid) {
        this.lesson.setDateTime(dateTime);
        this.lesson.setIsConfirmed(isConfirmed);
        this.lesson.setLessonId(lessonId);
        this.lesson.setIsOnline(isOnline);
        this.lesson.setIsPaid(isPaid);
        this.lesson.setTeacherId(teacherId);
        this.lesson.setStudentId(studentId);
    }

    public void initTeacher(String teacherName) {
        this.teacher.setName(teacherName);
    }

    public void initUser(String userName) {
        this.user.setName(userName);
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

    public Lesson getLesson() {
        return lesson;
    }
}
