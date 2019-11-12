package com.hrs.view.alerts;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

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
    
    public static void DisplayError(String title, String content)
    {
       displayAlert(title, content, Alert.AlertType.ERROR);
    }
    
    public static boolean DisplayConfirmation(String title, String content)
    {
        Optional<ButtonType> result = displayAlert(title, content, Alert.AlertType.CONFIRMATION);
        if(result.isPresent() && result.get() == ButtonType.OK) return true;
        else return false;
    }
    
    private static Optional<ButtonType> displayAlert(String title, String content, Alert.AlertType type)
    {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(content);
        return alert.showAndWait();
    }
}
