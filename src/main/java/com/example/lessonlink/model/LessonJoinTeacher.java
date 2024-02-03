package com.example.lessonlink.model;

public class LessonJoinTeacher {
    private Lesson lesson;
    private Teacher teacher;

    //builder pattern
    private LessonJoinTeacher(Builder builder) {
        this.lesson = builder.lesson;
        this.teacher = builder.teacher;
    }

    public static class Builder {
        private Lesson lesson;
        private Teacher teacher;

        public Builder lesson(Lesson lesson) {
            this.lesson = lesson;
            return this;
        }

        public Builder teacher(Teacher teacher) {
            this.teacher = teacher;
            return this;
        }

        public LessonJoinTeacher build() {
            return new LessonJoinTeacher(this);
        }
    }

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
}
