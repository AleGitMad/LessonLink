package com.example.lessonlink.model;

import java.time.LocalDateTime;

public class Lesson {
    private int lessonId;
    private LocalDateTime dateTime;
    private boolean isOnline;
    private int teacherId;
    private int studentId;
    private boolean isConfirmed;
    private boolean isPaid;

    public Lesson(int lessonId, LocalDateTime dateTime, boolean isOnline, int teacherId, int studentId, boolean isConfirmed, boolean isPaid) {
        this.lessonId = lessonId;
        this.dateTime = dateTime;
        this.isOnline = isOnline;
        this.teacherId = teacherId;
        this.studentId = studentId;
        this.isConfirmed = isConfirmed;
        this.isPaid = isPaid;
    }

    public Lesson() {}

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public boolean getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(boolean online) {
        isOnline = online;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public boolean getIsConfirmed() {
        return isConfirmed;
    }

    public void setIsConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }

    public boolean getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(boolean paid) {
        isPaid = paid;
    }
}
