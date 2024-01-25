package com.example.lessonlink.model.dao;

import com.example.lessonlink.exceptions.FailedResearchException;
import com.example.lessonlink.model.Lesson;
import com.example.lessonlink.model.Review;
import com.example.lessonlink.model.Teacher;
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
    public List<Lesson> findStudentLessons(int studentId) throws FailedResearchException {
        Statement stmt = null;
        Connection conn = null;
        List<Lesson> lessons = new ArrayList<>();

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

    private Lesson extractLesson(Connection conn, ResultSet rs) throws SQLException {
        return new Lesson(rs.getInt("lessonId"),
                rs.getTimestamp("dateTime").toLocalDateTime(),
                rs.getBoolean("isOnline"),
                rs.getInt("teacherId"),
                rs.getInt("studentId"),
                rs.getBoolean("isConfirmed"),
                rs.getBoolean("isPaid"));
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
}
