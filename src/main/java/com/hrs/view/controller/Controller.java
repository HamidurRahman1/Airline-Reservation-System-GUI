package com.hrs.view.controller;

import com.hrs.configs.Configuration;
import com.hrs.service.ApiService;
import com.hrs.view.View;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.time.LocalDate;

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
    
    public void eventGetDate()
    {
        Stage stage = new Stage();
        HBox hBox = new HBox();
        
        DatePicker datePicker = new DatePicker();
        
        datePicker.setDayCellFactory(picker -> new DateCell()
        {
            public void updateItem(LocalDate date, boolean empty)
            {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();
                setDisable(empty || date.compareTo(today) < 0 );
            }
        });
        
        datePicker.setOnAction(e ->
        {
            Configuration.setStartingDate(datePicker.getValue());
            hBox.getChildren().remove(0);
            hBox.getChildren().remove(0);
            hBox.getChildren().add(new Label("selected date is: " + Configuration.getStartingDate()));
            
        });
    
        Label label = new Label("Please select a date:");
        
        hBox.getChildren().addAll(label, datePicker);
        Scene scene = new Scene(hBox);
        stage.setHeight(200);
        stage.setWidth(300);
        stage.setScene(scene);
        stage.show();
    }
    
    public void eventLaunchCustomerLogin() {}
    
    public void eventInsertCustomer(String firstName, String lastName, String email, String password)
    {
        System.out.println("Customer login: " + firstName + " " + lastName + ' ' +  email + " " + password);
        
        // if success then switch back scenes
    }
    
    public void eventSearchBar(String query)
    {
        System.out.println(query);
    }
    
    public void eventAbout() {}
    
    public void eventHelp() {}
}
