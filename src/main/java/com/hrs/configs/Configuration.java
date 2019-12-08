package com.hrs.configs;

import com.hrs.dao.dbServices.DB2;
import com.hrs.dao.gateway.Gateway;
import com.hrs.service.ApiService;
import com.hrs.view.View;
import com.hrs.view.controller.Controller;
import com.hrs.view.models.Session;

import java.sql.SQLException;
import java.time.LocalDate;

public class Configuration
{
    private static Controller controller = null;
    private static ApiService apiService = null;
    private static LocalDate currentDate = null;
    private static Session session = null;
    private static View view = null;
    private static Gateway gateway = null;
    private static DB2 db2 = null;
    private static String LAST_QUERY = null;
    
    static
    {
        try
        {
            initializeGateway();
            initializeDatabaseService2();
            initializeApiService();
            initializeController();
            initializeSession();
        }
        catch(ClassNotFoundException ex)
        {
            System.out.println(ex.getMessage());
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
    private static void initializeDatabaseService2() throws ClassNotFoundException, SQLException
    {
        db2 = new DB2();
    }
    
    private static void initializeApiService()
    {
        apiService = new ApiService();
    }
    
    private static void initializeSession()
    {
        session = new Session();
    }
    
    private static void initializeController()
    {
        controller = new Controller();
    }
    
    private static void initializeGateway()
    {
        gateway = Gateway.getInstance();
    }
    
    public static Controller GET_CONTROLLER()
    {
        return controller;
    }
    
    public static void SET_CURRENT_DATE(LocalDate localDate)
    {
        currentDate = localDate;
    }
    
    public static LocalDate GET_CURRENT_DATE()
    {
        return currentDate;
    }
    
    public static void SET_QUERY(String query)
    {
        LAST_QUERY = query;
    }
    
    public static String GET_QUERY()
    {
        return LAST_QUERY;
    }
    
    public static Session GET_SESSION()
    {
        return session;
    }
    
    public static ApiService GET_API_SERVICE()
    {
        return apiService;
    }
    
    public static View GET_VIEW()
    {
        return view;
    }
    
    public static void SET_VIEW(View view)
    {
        Configuration.view = view;
    }
    
    public static Gateway GET_GATEWAY()
    {
        return gateway;
    }
    
    public static DB2 GET_DB2_SERVICE()
    {
        return db2;
    }
}
