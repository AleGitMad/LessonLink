package com.example.lessonlink.model.dao;

import com.example.lessonlink.exceptions.FailedResearchException;
import com.example.lessonlink.model.decorator.Teacher;
import com.example.lessonlink.model.service.Connector;
import com.example.lessonlink.model.service.Query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
            throw new FailedResearchException("An error during research occurred, wrong filters or no car corresponding to what you are searching for.");
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
        teacher.setAverageRating(ReviewDao.getAverageRating(conn, rs.getInt("teacherId")));
        return teacher;
    }



}
