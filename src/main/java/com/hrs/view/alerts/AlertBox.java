package com.hrs.view.alerts;

import javafx.scene.control.Alert;

public class AlertBox
{
    public static void DisplayWarning(String title, String content)
    {
        displayAlert(title, content, Alert.AlertType.WARNING);
    }
    
    public static void DisplayInformation(String title, String content)
    {
        displayAlert(title, content, Alert.AlertType.INFORMATION);
    }
    
    public static void displayError(String title, String content)
    {
       displayAlert(title, content, Alert.AlertType.ERROR);
    }
    
    public static void displayConfirmation(String title, String content)
    {
        displayAlert(title, content, Alert.AlertType.CONFIRMATION);
    }
    
    private static void displayAlert(String title, String content, Alert.AlertType type)
    {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
