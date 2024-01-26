package com.example.lessonlink.model.service;

import com.example.lessonlink.model.Lesson;
import com.example.lessonlink.model.Review;
import com.example.lessonlink.model.Teacher;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Query {
    private Query() {}

    public static ResultSet checkSignedUserByEmail(Statement stmt, String credential) throws SQLException {
        String selectStatement = String.format("SELECT * FROM Users WHERE (email = '%s')", credential);
        return stmt.executeQuery(selectStatement);
    }



    public static ResultSet findTeachers(Statement stmt, String subject, String city, boolean isOnline) throws SQLException {
        String selectedStatement = String.format("SELECT * FROM teachers WHERE (subject1 = '%s' OR subject2 = '%s' OR subject3 = '%s'", subject, subject, subject);

        if(isOnline){
            selectedStatement += ") AND availableOnline = 1"; // 1 = true, sql tinyint
        } else {
            selectedStatement += ") AND city = '" + city + "'";
        }
        return stmt.executeQuery(selectedStatement);
    }

    public static ResultSet findReviews(Statement stmt, int teacherId) throws SQLException {
        String selectedStatement = "SELECT * FROM reviews WHERE teacherId = " + teacherId;
        return stmt.executeQuery(selectedStatement);
    }

    /*
    public static ResultSet findLessons(Statement stmt, int studentId) throws SQLException {
        String selectedStatement = "SELECT * FROM lessons WHERE studentId = " + studentId;
        return stmt.executeQuery(selectedStatement);
    }
    */
    public static ResultSet findLessons(Statement stmt, int studentId) throws SQLException {
        String selectedStatement = "SELECT * FROM lessons JOIN teachers ON lessons.teacherId = teachers.teacherId WHERE studentId = " + studentId;
        return stmt.executeQuery(selectedStatement);
    }

    public static ResultSet checkSlotAvailability(Statement stmt, int teacherId, LocalDateTime dateTime) throws SQLException {
        String formattedDateTime = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String selectedStatement = "SELECT * FROM lessons WHERE teacherId = " + teacherId + " AND dateTime = '" + formattedDateTime + "'";
        return stmt.executeQuery(selectedStatement);
    }

    public static String insertLesson(Statement stmt, Lesson lesson) throws SQLException { // TODO spostare l'executeUpdate qui
        String formattedDateTime = lesson.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return String.format("INSERT INTO Lessons (dateTime, isOnline, teacherId, studentId, isConfirmed, isPaid) VALUES ('%s', %b, '%d', '%d', %b, %b)", formattedDateTime, lesson.getIsOnline(), lesson.getTeacherId(), lesson.getStudentId(), lesson.getIsConfirmed(), lesson.getIsPaid());
    }

    public static ResultSet checkCredentials(Statement stmt, String email, String password) throws SQLException {
        String query = "SELECT role FROM Users WHERE email = '" + email + "' AND password = '" + password + "'";
        return stmt.executeQuery(query);
    }

    public static void insertTeacher(Statement stmt, Teacher teacher) throws SQLException {
        String query;
        if (teacher.getSubject2() == null && teacher.getSubject3() != null) {
            query = String.format("INSERT INTO Teachers (name, subject1, subject2, subject3, fare, city, qualification, availableOnline, adminId) VALUES ('%s', '%s', null, '%s', %d, '%s', '%s', %b, %d)",
                    teacher.getName(), teacher.getSubject1(), teacher.getSubject3(), teacher.getFare(), teacher.getCity(), teacher.getQualification(), teacher.isAvailableOnline(), teacher.getAdminId());
        } else if (teacher.getSubject3() == null && teacher.getSubject2() != null) {
            query = String.format("INSERT INTO Teachers (name, subject1, subject2, subject3, fare, city, qualification, availableOnline, adminId) VALUES ('%s', '%s', '%s', null, %d, '%s', '%s', %b, %d)",
                    teacher.getName(), teacher.getSubject1(), teacher.getSubject2(), teacher.getFare(), teacher.getCity(), teacher.getQualification(), teacher.isAvailableOnline(), teacher.getAdminId());
        } else if (teacher.getSubject2() == null && teacher.getSubject3() == null) {
            query = String.format("INSERT INTO Teachers (name, subject1, subject2, subject3, fare, city, qualification, availableOnline, adminId) VALUES ('%s', '%s', null, null, %d, '%s', '%s', %b, %d)",
                    teacher.getName(), teacher.getSubject1(), teacher.getFare(), teacher.getCity(), teacher.getQualification(), teacher.isAvailableOnline(), teacher.getAdminId());
        } else {
            query = String.format("INSERT INTO Teachers (name, subject1, subject2, subject3, fare, city, qualification, availableOnline, adminId) VALUES ('%s', '%s', '%s', '%s', %d, '%s', '%s', %b, %d)",
                    teacher.getName(), teacher.getSubject1(), teacher.getSubject2(), null, teacher.getFare(), teacher.getCity(), teacher.getQualification(), teacher.isAvailableOnline(), teacher.getAdminId());
        }

        stmt.executeUpdate(query);
    }

    public static ResultSet LessonByAdmin(Statement stmt, int adminId) throws SQLException {
        String query = "SELECT * FROM lessons INNER JOIN Teachers ON Lessons.teacherId = Teachers.teacherId INNER JOIN Users ON Lessons.studentId = users.userId WHERE Teachers.adminId = " + adminId;
        return stmt.executeQuery(query);
    }

    public static void updateLesson(Statement stmt, Lesson lesson) throws SQLException {
        String query = "UPDATE Lessons SET isConfirmed = "+ lesson.getIsConfirmed() +" WHERE lessonId = " + lesson.getLessonId();
        stmt.executeUpdate(query);
    }

    public static void insertReview(Statement stmt, Review review) throws SQLException {
        String query;
        if (review.getComment() != null) {
            query = String.format("INSERT INTO Reviews (stars, comment, date, teacherId) VALUES (%d, '%s', '%s', %d)", review.getStars(), review.getComment(), review.getDate(), review.getTeacherId());
        } else {
            query = String.format("INSERT INTO Reviews (stars, date, teacherId) VALUES (%d, '%s', %d)", review.getStars(), review.getDate(), review.getTeacherId());
        }
        stmt.executeUpdate(query);
    }
}
