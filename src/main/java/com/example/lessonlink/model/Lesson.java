package com.example.lessonlink.model;

import com.example.lessonlink.model.decorator.Teacher;

import java.time.LocalDateTime;

public class Lesson {
    private int lessonId;
    private LocalDateTime dateTime;
    private boolean isOnline;
    private int teacherId;
    private int studentId;

    public Lesson(int lessonId, LocalDateTime dateTime, boolean isOnline, int teacherId, int studentId) {
        this.lessonId = lessonId;
        this.dateTime = dateTime;
        this.isOnline = isOnline;
        this.teacherId = teacherId;
        this.studentId = studentId;
    }
}
