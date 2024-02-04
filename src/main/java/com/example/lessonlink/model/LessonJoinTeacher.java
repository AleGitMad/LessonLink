package com.example.lessonlink.model;

import java.time.LocalDateTime;

public class LessonJoinTeacher {
    private Lesson lesson;
    private Teacher teacher;

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    //two methods to separate entities while populating the object
    public void initLesson(int lessonId, LocalDateTime dateTime, boolean isOnline, int teacherId, int studentId, boolean isConfirmed, boolean isPaid) {
        this.lesson = new Lesson(lessonId, dateTime, isOnline, teacherId, studentId, isConfirmed, isPaid);
    }
    public void initTeacher(int teacherId, String teacherName) {
        this.teacher = new Teacher();
        this.teacher.setName(teacherName);
        this.teacher.setTeacherId(teacherId);
    }

}
