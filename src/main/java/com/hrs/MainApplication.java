package com.hrs;

import com.hrs.view.View;

import javafx.application.Platform;

/**
 * Application Runner
 */
public class MainApplication extends View
{
    public static void main(String[] args)
    {
        try
        {
            View.launch();
        }
        catch (RuntimeException ex)
        {
            System.out.println(ex.getMessage());
            Platform.exit();
        }
    }
}
