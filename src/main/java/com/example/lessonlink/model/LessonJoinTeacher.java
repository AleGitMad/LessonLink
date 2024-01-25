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
    public LessonJoinTeacher(int lessonId, LocalDateTime dateTime, boolean isOnline, int teacherId, int studentId, boolean isConfirmed, boolean isPaid, String teacherName) {
        this.lesson = new Lesson(lessonId, dateTime, isOnline, teacherId, studentId, isConfirmed, isPaid);
        this.teacher = new Teacher();
        this.teacher.setName(teacherName);
        this.teacher.setTeacherId(teacherId);
    }
}
