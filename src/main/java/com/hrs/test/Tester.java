package com.hrs.test;

import com.hrs.view.models.Admin;
import com.hrs.view.models.AirPlane;
import com.hrs.view.models.Airport;
import com.hrs.view.models.Arrival;
import com.hrs.view.models.Customer;
import com.hrs.view.models.Flight;
import com.hrs.view.models.Reservation;
import com.hrs.view.models.Source;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Tester extends Application
{
    @Override
    public void start(Stage stage) throws Exception
    {// Set title for the stage
        stage.setTitle("creating combo box ");
    
        // Create a tile pane
        TilePane r = new TilePane();
    
        // Create a label
        Label description_label =
                new Label("This is a combo box example ");
    
        // Weekdays
        List<String> week_days = Arrays.asList("Monday", "Tuesday", "Wednesday", "Thrusday", "Friday");
    
        // Create a combo box
        ChoiceBox <String> combo_box = new ChoiceBox <>(FXCollections.observableArrayList(week_days));
    
        ChoiceBox<String> combo_box2 = new ChoiceBox<>(FXCollections.observableArrayList(week_days));
    
        // Label to display the selected menuitem
        Label selected = new Label("default item selected");
    
        // Create action event
        EventHandler<ActionEvent> event = new EventHandler <ActionEvent>()
        {
            public void handle(ActionEvent e)
            {
                selected.setText(combo_box.getValue() + " selected");
                System.out.println(week_days.size());
                combo_box2.setItems(FXCollections.observableList(Arrays.asList("a", "as")));
            }
        };
    
        // Set on action
        combo_box.setOnAction(event);
    
        // Create a tile pane
        TilePane tile_pane = new TilePane(combo_box, combo_box2, selected);
    
        // Create a scene
        Scene scene = new Scene(tile_pane, 200, 200);
    
        // Set the scene
        stage.setScene(scene);
    
        stage.show();
    }
    
    public static void main(String[] args)
    {
        launch();
    }
    
    public static Customer testCustomer()
    {
        Customer customer = new Customer("Hamidur", "Rahman");
        customer.setFlights(testFlights());
        customer.setReservations(testReservation());
        return customer;
    }
    
    public static Source testSource()
    {
        return new Source();
    }
    
    public static List<Arrival> arrivals()
    {
        List<Arrival> arrivals = new LinkedList <>();
        arrivals.add(new Arrival("flight", "airline", "source", "time", "status"));
        arrivals.add(new Arrival("flight", "airline", "source", "time", "status"));
        arrivals.add(new Arrival("flight", "airline", "source", "time", "status"));
        arrivals.add(new Arrival("flight", "airline", "source", "time", "status"));
        arrivals.add(new Arrival("flight", "airline", "source", "time", "status"));
        return arrivals;
    }
    
    public static List<Arrival> arrivals2()
    {
        List<Arrival> arrivals = new LinkedList <>();
        arrivals.add(new Arrival("flight up", "airline", "source", "time", "a"));
        arrivals.add(new Arrival("flight", "airline", "source", "time", "status"));
        arrivals.add(new Arrival("flight", "airline", "source", "time", "status"));
        arrivals.add(new Arrival("flight", "airline", "source", "time", "status"));
        arrivals.add(new Arrival("flight", "airline", "source", "time", "status"));
        return arrivals;
    }
    
    public static List<Flight> testFlights()
    {
        List<Flight> flights = new LinkedList <>();
        flights.add(new Flight("f1", "f1", "f1", "f1", "f1", "10", "open"));
        flights.add(new Flight("f3", "f3", "f3", "f3", "f3", "20", "c"));
        flights.add(new Flight("f2", "f2", "f3", "f2", "f2", "50", "c"));
        return flights;
    }
    
    public static List<Flight> testFlights2()
    {
        List<Flight> flights = new LinkedList <>();
        flights.add(new Flight("f1", "f1", "f1", "f1", "f1", "8", "c"));
        flights.add(new Flight("f2", "f2", "f2", "f2", "f2", "5", "c"));
        return flights;
    }
    
    public static List<Reservation> testReservation()
    {
        List<Reservation> reservations = new LinkedList <>();
        reservations.add(new Reservation(new Flight("f1", "f1", "f1", "f1", "f1", "20", "c"), "r d", "c"));
        reservations.add(new Reservation(new Flight("f2", "f2", "f2", "f2", "f2", "5", "c"), "r d", "a"));
        return reservations;
    }
    
    public static List<Reservation> testReservation2()
    {
        List<Reservation> reservations = new LinkedList <>();
        reservations.add(new Reservation(new Flight("f1", "f1", "f1", "f1", "f1", "20", "c"), "r d", "c"));
        return reservations;
    }
    
    public static Admin admin()
    {
        return new Admin("Hamidur", "Rahman");
    }
    
    public static List<Airport> airports()
    {
        List<Airport> airports = new LinkedList <>();
        
        airports.add(new Airport(101, "A1"));
        airports.add(new Airport(102, "A2"));
        airports.add(new Airport(103, "A3"));
        airports.add(new Airport(104, "A4"));
        
        return airports;
    }
    
    public static List<AirPlane> airPlanes()
    {
        List<AirPlane> airports = new LinkedList <>();
        
        airports.add(new AirPlane(11, "AP1"));
        airports.add(new AirPlane(12, "AP2"));
        airports.add(new AirPlane(13, "AP3"));
        airports.add(new AirPlane(14, "AP4"));
        
        return airports;
    }
}
