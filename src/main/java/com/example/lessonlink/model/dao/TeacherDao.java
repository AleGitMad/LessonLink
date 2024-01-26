package com.example.lessonlink.model.dao;

import com.example.lessonlink.exceptions.FailedResearchException;
import com.example.lessonlink.model.Lesson;
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

public class TeacherDao {

    public List<Teacher> findTeachers(String subject, String city, boolean isOnline) throws FailedResearchException {
        Statement stmt = null;
        Connection conn = null;
        List<Teacher> teachers = new ArrayList<>();

        try {
            conn = Connector.getInstance().getConnection();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Query.findTeachers(stmt, subject, city, isOnline);
            while (rs.next()) {
                teachers.add(extractTeacher(conn, rs));
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
        return teachers;
    }

    private Teacher extractTeacher(Connection conn, ResultSet rs) throws SQLException, FailedResearchException {
        Teacher teacher = new Teacher(rs.getInt("teacherId"),
                rs.getString("name"),
                rs.getString("subject1"),
                rs.getString("subject2"),
                rs.getString("subject3"),
                rs.getInt("fare"),
                rs.getString("city"),
                rs.getString("qualification"),
                rs.getBoolean("availableOnline"));
        ReviewDao reviewDao = new ReviewDao();
        if (reviewDao.getAverageRating(rs.getInt("teacherId")) != -1) {
            teacher.setAverageRating(reviewDao.getAverageRating(rs.getInt("teacherId")));
            teacher.setHasReviews(true);
        } else {
            teacher.setHasReviews(false);
        }


        return teacher;
    }

    public void saveTeacher(Teacher teacher) throws SQLException {
        Statement stmt = null;
        Connection conn = null;

        try {
            conn = Connector.getInstance().getConnection();
            stmt = conn.createStatement();

            Query.insertTeacher(stmt, teacher);
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
