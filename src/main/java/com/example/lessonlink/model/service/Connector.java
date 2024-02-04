package com.example.lessonlink.model.service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connector {
    private static Connector instance = null;
    private Connection conn = null;
    private Connector() throws SQLException {
        try (InputStream input = new FileInputStream("src/main/resources/com/example/lessonlink/db.properties")) {
            Properties properties = new Properties();
            properties.load(input);

            String connectionUrl = properties.getProperty("CONNECTION_URL");
            String user = properties.getProperty("LOGIN_USER");
            String pass = properties.getProperty("LOGIN_PASS");

            conn = DriverManager.getConnection(connectionUrl, user, pass);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public static Connector getInstance() throws SQLException {

        if(instance == null){
            try {
                instance = new Connector();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return instance;
    }

    public Connection getConnection(){

        return conn;
    }
}
