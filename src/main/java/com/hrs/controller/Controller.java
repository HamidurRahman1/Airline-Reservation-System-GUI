package com.hrs.controller;

import com.hrs.service.ApiService;
import com.hrs.view.View;

/**
 * A class that navigates views and talk to database
 */
public class Controller {
    private View view;
    private ApiService apiService;

    public Controller(View view, ApiService apiService) {
        this.apiService = apiService;
        this.view = view;
    }
}
