package com.example.lessonlink.model.dao;

import com.example.lessonlink.exceptions.FailedInsertException;
import com.example.lessonlink.exceptions.FailedResearchException;
import com.example.lessonlink.exceptions.FailedUpdateException;
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

    private static final String TEACHER_ID = "teacherId";

    public List<LessonJoinTeacher> findStudentLessons(int studentId) throws FailedResearchException {
        Statement stmt = null;
        Connection conn;
        List<LessonJoinTeacher> lessons = new ArrayList<>();

        try {
            conn = Connector.getInstance().getConnection();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Query.findLessons(stmt, studentId);
            while (rs.next()) {
                lessons.add(extractLesson(rs));
            }
        } catch (Exception e) {
            throw new FailedResearchException("An error while retrieving lessons occurred.");
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

    private LessonJoinTeacher extractLesson(ResultSet rs) throws SQLException {

        LessonJoinTeacher lessonJoinTeacher = new LessonJoinTeacher();
        lessonJoinTeacher.initLesson(rs.getInt("lessonId"),
                rs.getTimestamp("dateTime").toLocalDateTime(),
                rs.getBoolean("isOnline"),
                rs.getInt(TEACHER_ID),
                rs.getInt("studentId"),
                rs.getBoolean("isConfirmed"),
                rs.getBoolean("isPaid"));
        lessonJoinTeacher.initTeacher(rs.getInt(TEACHER_ID), rs.getString("teachers.name"));
        return lessonJoinTeacher;
    }

    public boolean findTeacherLessons(int teacherId, LocalDateTime dateTime) throws FailedResearchException {
        Statement stmt = null;
        Connection conn;

        try {
            conn = Connector.getInstance().getConnection();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Query.checkSlotAvailability(stmt, teacherId, dateTime);
            if (rs.first()) {
                return false;
            }
        } catch (Exception e) {
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

    public void insertLesson(Lesson lesson) throws FailedInsertException {
        Statement stmt = null;
        Connection conn = null;

        try {
            conn = Connector.getInstance().getConnection();
            stmt = conn.createStatement();
            Query.insertLesson(stmt, lesson);
        } catch (Exception e) {
            throw new FailedInsertException("An error during lesson insertion occurred.");
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
            ResultSet rs = Query.lessonByAdmin(stmt, adminId);
            while (rs.next()) {
                lessonsJoinAdmin.add(extractLessonJoinAdmin(rs));
            }
        } catch (Exception e) {
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
            LessonJoinUser lessonJoinUser = new LessonJoinUser();
            lessonJoinUser.initLesson(rs.getInt("lessonId"),
                    rs.getTimestamp("dateTime").toLocalDateTime(),
                    rs.getBoolean("isOnline"),
                    rs.getInt("teacherId"),
                    rs.getInt("studentId"),
                    rs.getBoolean("isConfirmed"),
                    rs.getBoolean("isPaid"));
            lessonJoinUser.initTeacher(rs.getString("teachers.name"));
            lessonJoinUser.initUser(rs.getString("users.name"));
        return lessonJoinUser;
    }

    public void updateLesson(Lesson lesson) throws FailedUpdateException{
        Statement stmt = null;
        Connection conn = null;

        try {
            conn = Connector.getInstance().getConnection();
            stmt = conn.createStatement();

            Query.updateLesson(stmt, lesson);
        } catch (Exception e) {
            throw new FailedUpdateException("An error during lesson update occurred.");
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                //not handled
            }
        }
    }
}
