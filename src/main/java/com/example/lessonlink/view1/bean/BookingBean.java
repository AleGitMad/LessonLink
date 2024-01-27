package com.example.lessonlink.view1.bean;


import com.example.lessonlink.model.Lesson;

public class BookingBean {
    private String teacher;
    private String student;

    private Lesson lesson;


    public void setTeacherName(String teacher) {
        this.teacher = teacher;
    }
    public void setStudentName(String student) {
        this.student = student;
    }
    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }
    public String getDateTime() {
        String y = lesson.getDateTime().toString().substring(0, 4);
        String m = lesson.getDateTime().toString().substring(5, 7);
        String d = lesson.getDateTime().toString().substring(8, 10);
        String h = lesson.getDateTime().toString().substring(11, 13);
        String i = lesson.getDateTime().toString().substring(14, 16);

        return d + "/" + m + "/" + y + " " + h + ":" + i;
    }
    public boolean getConfirmed() {
        return lesson.getIsConfirmed();
    }
    public String getTeacher() {
        return teacher;
    }
    public String getStudent() {
        return student;
    }
    public Lesson getLesson() {
        return lesson;
    }
}
