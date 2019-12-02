package com.hrs.dao.gateway;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Gateway
{
    private static Gateway singleton = new Gateway();
    
    public static Gateway getInstance()
    {
        return singleton;
    }
    
    private Gateway() {}
    
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "mysql?";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String DATABASE = "cs370p2";
    private static final String QUESTION = "?";
    private static final String AND = "&";
    private static final String SSL = "useSSL=false";
    private static final String AUTO_CONNECT = "autoReconnect=true";
    private static final String TIMEZONE = "useLegacyDatetimeCode=false&serverTimezone=America/New_York";
    
    
    private static Connection connection = null;
    
    public Connection getConnection() throws ClassNotFoundException, SQLException
    {
        if(connection == null || connection.isClosed())
        {
            Class.forName(getDRIVER());
            connection = DriverManager.getConnection(getURL()+ getDATABASE() + getQUESTION() + getTIMEZONE() + getAND()
                            + getAutoConnect() + getAND()+ getSSL(),
                    getUserName(), getPASSWORD());
            return connection;
        }
        return connection;
    }
    
    public void closeConnection() throws SQLException
    {
        if(connection == null) return;
        if(!connection.isClosed()) connection.close();
    }
    
    public static String getUserName() {
        return USER_NAME;
    }
    
    public static String getPASSWORD() {
        return PASSWORD;
    }
    
    public static String getDRIVER() {
        return DRIVER;
    }
    
    public static String getURL() {
        return URL;
    }
    
    public static String getDATABASE()
    {
        return DATABASE;
    }
    
    public static String getQUESTION()
    {
        return QUESTION;
    }
    
    public static String getAND()
    {
        return AND;
    }
    
    public static String getSSL()
    {
        return SSL;
    }
    
    public static String getAutoConnect()
    {
        return AUTO_CONNECT;
    }
    
    public static String getTIMEZONE()
    {
        return TIMEZONE;
    }
}
