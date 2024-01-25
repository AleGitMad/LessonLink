package com.example.lessonlink.model;

import com.example.lessonlink.model.Lesson;

public class LessonJoinUser {
    private Lesson lesson;
    private String teacherName;
    private String studentName;

    public String getTeacher() {
        return teacherName;
    }

    public String getStudent() {
        return studentName;
    }

    public boolean getConfirmed() {
        return lesson.getIsConfirmed();
    }

    public String getDateTime() {
        return lesson.getDateTime().toString();
    }
}
