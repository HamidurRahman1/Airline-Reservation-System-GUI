package com.hrs.configs;

import com.hrs.service.ApiService;
import com.hrs.view.controller.Controller;
import com.hrs.view.models.Session;

import java.time.LocalDate;

public class Configuration
{
    private static Controller controller = null;
    private static ApiService apiService = null;
    private static LocalDate startingDate = null;
    private static Session session = null;
    
    static
    {
        initializeController();
        initializeSession();
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
    
    public static void setStartingDate(LocalDate localDate)
    {
        startingDate = localDate;
    }
    
    public static LocalDate getStartingDate()
    {
        return startingDate;
    }
    
    public static Session getSession()
    {
        return session;
    }
}
