package com.example.lessonlink.model;

import com.example.lessonlink.model.Lesson;

import java.time.LocalDateTime;

public class LessonJoinUser {
    private Lesson lesson;

    private Teacher teacher;

    private User user;

    public LessonJoinUser(String username, String teachername, LocalDateTime dateTime, boolean isConfirmed) {
        lesson = new Lesson();
        teacher = new Teacher();
        user = new User();
        this.lesson.setDateTime(dateTime);
        this.lesson.setIsConfirmed(isConfirmed);
        this.teacher.setName(teachername);
        this.user.setName(username);
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

    public String getDateTime() {
        return lesson.getDateTime().toString();
    }
}
