package com.example.lessonlink.model.dao;

import com.example.lessonlink.exceptions.FailedInsertException;
import com.example.lessonlink.exceptions.FailedResearchException;
import com.example.lessonlink.model.Review;
import com.example.lessonlink.model.service.Connector;
import com.example.lessonlink.model.service.Query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReviewDao {
    private static final String STARS = "stars";
    public float getAverageRating(int teacherId) throws FailedResearchException {
        Statement stmt = null;
        Connection conn = null;
        float averageRating = 0;
        int reviews = 0;

        try {
            conn = Connector.getInstance().getConnection();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Query.findReviews(stmt, teacherId);
            while (rs.next()) {
                averageRating += rs.getInt(STARS);
                reviews++;
            }
            if (reviews != 0) {
                averageRating = averageRating / reviews;
            } else {
                averageRating = -1;
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
        return averageRating;
    }

    //method used to retrieve the average rating and the total reviews of a teacher,
    //makes things easy when adding a review in historyPage
    public float[] getAverageRatingAndTotalReviews(int teacherId) throws FailedResearchException {
        Statement stmt = null;
        Connection conn = null;
        float averageRating = 0;
        int reviews = 0;

        try {
            conn = Connector.getInstance().getConnection();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Query.findReviews(stmt, teacherId);
            while (rs.next()) {
                averageRating += rs.getInt(STARS);
                reviews++;
            }
            if (reviews != 0) {
                averageRating = averageRating / reviews;
            } else {
                averageRating = -1;
            }
        } catch (Exception e) {
            throw new FailedResearchException("An error during reviews research occurred.");
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                //not handled
            }
        }
        return new float[]{averageRating, reviews};
    }

    //reviews visualization logic not implemented
    public List<Review> findTeacherReviews(int teacherId) throws FailedResearchException {
        Statement stmt = null;
        Connection conn = null;
        List<Review> reviews = new ArrayList<>();

        try {
            conn = Connector.getInstance().getConnection();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Query.findReviews(stmt, teacherId);
            while (rs.next()) {
                reviews.add(extractReview(rs));
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
        return reviews;
    }

    private Review extractReview(ResultSet rs) throws SQLException {
        return new Review(rs.getInt("reviewId"),
                rs.getInt(STARS),
                rs.getDate("date"),
                rs.getString("comment"),
                rs.getInt("teacherId"));
    }

    public void insertReview(Review review) throws FailedInsertException {
        Statement stmt = null;
        Connection conn = null;

        try {
            conn = Connector.getInstance().getConnection();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            Query.insertReview(stmt, review);
        } catch (Exception e) {
            throw new FailedInsertException("An error during review insertion occurred.");
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
