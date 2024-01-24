package com.example.lessonlink.model.service;

import com.example.lessonlink.model.Teacher;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

    public static ResultSet findLessons(Statement stmt, int studentId) throws SQLException {
        String selectedStatement = "SELECT * FROM lessons WHERE studentId = " + studentId;
        return stmt.executeQuery(selectedStatement);
    }

    public static ResultSet checkCredentials(Statement stmt, String email, String password) throws SQLException {
        String query = "SELECT role FROM Users WHERE email = '" + email + "' AND password = '" + password + "'";
        return stmt.executeQuery(query);
    }

    public static String insertTeacher(Statement stmt, Teacher teacher) throws SQLException {
        return String.format("INSERT INTO Teachers (name, subject1, subject2, subject3, fare, city, qualification, availableOnline) VALUES ('%s', '%s', '%s', '%s', %d, '%s', '%s', %b)",
                teacher.getName(), teacher.getSubject1(), teacher.getSubject2(), teacher.getSubject3(), teacher.getFare(), teacher.getCity(), teacher.getQualification(), teacher.isAvailableOnline());
    }
}
