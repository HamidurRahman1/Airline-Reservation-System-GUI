package com.hrs.configs;

import com.hrs.service.ApiService;
import com.hrs.view.controller.Controller;

public class Configuration
{
    private static Controller controller = null;
    private static ApiService apiService = null;
    
    static
    {
        initializeController();
        // initialize the api service and other objects like controller
    }
    
    private static void initializeController()
    {
        controller = new Controller();
    }
    
    public static Controller getController()
    {
        return controller;
    }
}
