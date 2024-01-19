package com.example.lessonlink.model.dao;

import com.example.lessonlink.exceptions.FailedResearchException;
import com.example.lessonlink.model.Lesson;
import com.example.lessonlink.model.Review;
import com.example.lessonlink.model.service.Connector;
import com.example.lessonlink.model.service.Query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
                rs.getInt("studentId"));
    }
}
