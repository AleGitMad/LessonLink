package com.example.lessonlink.model.dao;

import com.example.lessonlink.model.User;
import com.example.lessonlink.model.service.Connector;
import com.example.lessonlink.model.service.Query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDao {
    public String checkCredentials(String email, String password) {
        Statement stmt = null;
        Connection conn = null;
        String role = null;

        try {
            conn = Connector.getInstance().getConnection();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Query.checkCredentials(stmt, email, password);
            if (rs.next()) {
                role = rs.getString("role");
            }
        } catch (Exception se) {
            se.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                //not handled
            }
        }
        return role;
    }

    public void setUser(User user, String email) throws SQLException {
        Statement stmt = null;
        Connection conn = null;
        String role = null;

        try {
            conn = Connector.getInstance().getConnection();

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Query.checkSignedUserByEmail(stmt, email);

            rs.first(); // LoginDao ha già controllato la password

            user.setUserId(rs.getInt("userId"));
            user.setName(rs.getString("name" )); // settiamo lo stato dell'istanza di Admin/Student a runtime
            user.setEmail(rs.getString("email"));


        } catch (SQLException e) {
            //TODO: not handled. to write
        } finally {
            if(stmt!=null){
                stmt.close();
            }
        }
    }
}