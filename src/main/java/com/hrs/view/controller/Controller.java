package com.hrs.view.controller;

import com.hrs.service.ApiService;
import com.hrs.view.View;

/**
 * A class that navigates views and talk to database
 */
public class Controller
{
    private View view;
    
    public Controller() {}

    public Controller(View view)
    {
        this.view = view;
    }
    
    public View getView()
    {
        return view;
    }
    
    public void setView(View view)
    {
        this.view = view;
    }
    
    public void launchCustomerLogin()
    {
    
    }
    
    public void about()
    {
    
    }
    
    public void help()
    {
    
    }
}
