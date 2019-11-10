package com.hrs.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Gateway {

    // Singleton pattern for jdbc connection

    public static String url= "jdbc:mysql://localhost:3306/AirlineReservationDataBase";
    private static final String user = "root";
    private static final String pass = "*Codarx1971#";
    private static final String className = "com.mysql.cj.jdbc.Driver";

    private static Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        return DriverManager.getConnection(url, user, pass);
    }

    private static Connection myDbconnection = null;

    public static Connection getDBConnection() throws SQLException {
        if(null == myDbconnection){
            myDbconnection = getConnection();
        }
        return myDbconnection;
    }

}
