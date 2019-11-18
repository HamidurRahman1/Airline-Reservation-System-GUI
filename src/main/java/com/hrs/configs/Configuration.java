package com.hrs.configs;

import com.hrs.service.ApiService;
import com.hrs.view.View;
import com.hrs.view.controller.Controller;
import com.hrs.view.models.Session;

import java.time.LocalDate;

public class Configuration
{
    private static Controller controller = null;
    private static ApiService apiService = null;
    private static LocalDate currentDate = null;
    private static Session session = null;
    private static View view = null;
    
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
}
