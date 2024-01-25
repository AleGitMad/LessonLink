package com.example.lessonlink.model.dao;

import com.example.lessonlink.exceptions.FailedResearchException;
import com.example.lessonlink.model.Lesson;
import com.example.lessonlink.model.LessonJoinTeacher;
import com.example.lessonlink.model.LessonJoinUser;
import com.example.lessonlink.model.service.Connector;
import com.example.lessonlink.model.service.Query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LessonDao {
    public List<LessonJoinTeacher> findStudentLessons(int studentId) throws FailedResearchException {
        Statement stmt = null;
        Connection conn = null;
        List<LessonJoinTeacher> lessons = new ArrayList<>();

        try {
            conn = Connector.getInstance().getConnection();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Query.findLessons(stmt, studentId);
            while (rs.next()) {
                lessons.add(extractLesson(conn, rs));
            }
        } catch (Exception se) {
            se.printStackTrace();
            throw new FailedResearchException("An error during research occurred.");
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                //not handled
            }
        }
        return lessons;
    }

    private LessonJoinTeacher extractLesson(Connection conn, ResultSet rs) throws SQLException {
        return new LessonJoinTeacher(rs.getInt("lessonId"),
                rs.getTimestamp("dateTime").toLocalDateTime(),
                rs.getBoolean("isOnline"),
                rs.getInt("teacherId"),
                rs.getInt("studentId"),
                rs.getBoolean("isConfirmed"),
                rs.getBoolean("isPaid"),
                rs.getString("teachers.name"));
    }

    public boolean findTeacherLessons(int teacherId, LocalDateTime dateTime) throws FailedResearchException {
        Statement stmt = null;
        Connection conn = null;

        try {
            conn = Connector.getInstance().getConnection();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Query.checkSlotAvailability(stmt, teacherId, dateTime);
            if (rs.first()) {
                return false;
            }
        } catch (Exception se) {
            se.printStackTrace();
            throw new FailedResearchException("An error during research occurred.");
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                //not handled
            }
        }
        return true;
    }

    public void insertLesson(Lesson lesson) throws SQLException {
        Statement stmt = null;
        Connection conn = null;

        try {
            conn = Connector.getInstance().getConnection();
            stmt = conn.createStatement();

            String insertStatement = Query.insertLesson(stmt, lesson);

            stmt.executeUpdate(insertStatement);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                //not handled
            }
        }
    }


    public List<LessonJoinUser> findLessoByAdmin(int adminId) throws FailedResearchException {
        List<LessonJoinUser> lessonsJoinAdmin = new ArrayList<>();

        Statement stmt = null;
        Connection conn = null;


        try {
            conn = Connector.getInstance().getConnection();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Query.LessonByAdmin(stmt, adminId);
            while (rs.next()) {
                lessonsJoinAdmin.add(extractLessonJoinAdmin(rs));
            }
        } catch (Exception se) {
            se.printStackTrace();
            throw new FailedResearchException("An error during research occurred.");
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                //not handled
            }
        }
        return lessonsJoinAdmin;
    }

    private LessonJoinUser extractLessonJoinAdmin(ResultSet rs) throws SQLException {
            return new LessonJoinUser(rs.getString("users.name"),
                rs.getString("teachers.name"),
                rs.getTimestamp("dateTime").toLocalDateTime(),
                rs.getBoolean("isConfirmed"));
    }
}
