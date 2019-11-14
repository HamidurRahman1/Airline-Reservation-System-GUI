package com.hrs.configs;

import com.hrs.service.ApiService;
import com.hrs.view.controller.Controller;
import com.hrs.view.models.Session;

import java.time.LocalDate;

public class Configuration
{
    private static Controller controller = null;
    private static ApiService apiService = null;
    private static LocalDate currentDate = null;
    private static Session session = null;
    
    static
    {
        initializeApiService();
        initializeController();
        initializeSession();
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
    
    public static Controller getController()
    {
        return controller;
    }
    
    public static void setCurrentDate(LocalDate localDate)
    {
        currentDate = localDate;
    }
    
    public static LocalDate getCurrentDate()
    {
        return currentDate;
    }
    
    public static Session getSession()
    {
        return session;
    }
    
    public static ApiService getApiService()
    {
        return apiService;
    }
}
