package com.example.lessonlink.model.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Query {
    private Query() {}

    public static ResultSet checkSignedUserByEmail(Statement stmt, String credential) throws SQLException {
        String selectStatement = String.format("SELECT * FROM user WHERE (email = '%s')", credential);
        return stmt.executeQuery(selectStatement);
    }

    public static void addProfile(Statement stmt, String firstName, String lastName, String email, String password, String role) throws SQLException {
        String insertStatement = String.format("INSERT INTO `user`(firstname,lastname,email,password,role) VALUES ('%s','%s','%s','%s','%s')", firstName,lastName,email,password,role);
        stmt.executeUpdate(insertStatement);
    }

    public static ResultSet isEmailExisting(Statement stmt, String email) throws  SQLException{
        String selectStatement = String.format("SELECT * FROM user WHERE email = '%s'", email);
        return stmt.executeQuery(selectStatement);
    }
    public static ResultSet findTeacher(Statement stmt, String brand, String startingPrice, String maxPrice, String startingMileage, String maxMileage) throws SQLException {
        String selectedStatement = "SELECT * FROM ad WHERE sold = '0' ";

        if(!startingPrice.isEmpty()){
            selectedStatement += " AND cost > " + startingPrice;
        }
        if(!maxPrice.isEmpty()){
            selectedStatement += " AND cost < " + maxPrice;
        }
        selectedStatement += " AND idcar IN (SELECT idcar FROM car WHERE true = true ";

        if(!brand.isEmpty()){
            selectedStatement += " AND brand = '" + brand + "'";
        }
        if(!startingMileage.isEmpty()){
            selectedStatement += " AND mileage > " + startingMileage;
        }
        if(!maxMileage.isEmpty()){
            selectedStatement += " AND mileage < " + maxMileage;
        }
        selectedStatement += ")";
        return stmt.executeQuery(selectedStatement);
    }

}
