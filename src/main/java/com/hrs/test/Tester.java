package com.hrs.test;

import com.hrs.view.models.Admin;
import com.hrs.view.models.Airline;
import com.hrs.view.models.Airplane;
import com.hrs.view.models.Airport;
import com.hrs.view.models.Arrival;
import com.hrs.view.models.Customer;
import com.hrs.view.models.Destination;
import com.hrs.view.models.Flight;
import com.hrs.view.models.Login;
import com.hrs.view.models.Reservation;
import com.hrs.view.models.Source;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Tester
{
    public static void main(String[] args) {}
    
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
    
    public static Customer testCustomer()
    {
        Customer customer = new Customer("Hamidur", "Rahman");
        customer.setLogin(testLogin());
        customer.setFlights(testFlights());
//        customer.setReservations(testReservation());
        return customer;
    }
    
    public static Flight testFlight1()
    {
        return new Flight("code1", testSource1(), testDestination1(), 10, STATUS_ON_TIME(), testAirline1(), testAirplane1(), 100f);
    }
    
    public static Flight testFlight2()
    {
        return new Flight("code2", testSource2(), testDestination2(), 10, STATUS_ON_TIME(), testAirline2(), testAirplane2(), 200f);
    }
    
    public static Set<Flight> testFlights()
    {
        Set<Flight> flights = new LinkedHashSet <>();
        
        flights.add(new Flight("code1", testSource1(), testDestination1(), 5, STATUS_CANCELED(), testAirline1(), testAirplane1(), 150f));
        flights.add(new Flight("code1", testSource1(), testDestination1(), 0, STATUS_ON_TIME(), testAirline1(), testAirplane1(), 100f));
        flights.add(new Flight("code1", testSource1(), testDestination1(), 5, STATUS_CANCELED(), testAirline1(), testAirplane1(), 200f));
        
        return flights;
    }
    
    public static Set<Flight> customerFlights()
    {
        Set<Flight> flights = new LinkedHashSet <>();
        
        flights.add(new Flight("code1", testSource1(), testDestination1(), 5, STATUS_CANCELED(), testAirline1(), testAirplane1(), 150f));
        flights.add(new Flight("code1", testSource1(), testDestination1(), 9, STATUS_ON_TIME(), testAirline1(), testAirplane1(), 100f));
        flights.add(new Flight("code1", testSource1(), testDestination1(), 0, STATUS_CANCELED(), testAirline1(), testAirplane1(), 200f));
        
        return flights;
    }
    
    public static String STATUS_ON_TIME()
    {
        return new String("ON TIME");
    }
    
    public static String STATUS_CANCELED()
    {
        return new String("CANCELED");
    }
    
    public static String STATUS_ACTIVE()
    {
        return new String("CANCELED");
    }
    
    public static Airline testAirline1()
    {
        return new Airline("DELTA AIRLINE");
    }
    
    public static Airline testAirline2()
    {
        return new Airline("AMERICAN AIRLINE");
    }
    
    public static Airplane testAirplane1()
    {
        return new Airplane("DELTA 107");
    }
    
    public static Airplane testAirplane2()
    {
        return new Airplane("DELTA 109");
    }
    
    public static Airport testAirport1()
    {
        return new Airport("JFK");
    }
    
    public static Airport testAirport2()
    {
        return new Airport("LGA");
    }
    
    public static Airport testAirport3()
    {
        return new Airport("MMD");
    }
    
    public static Login testLogin()
    {
        return new Login("username", "password");
    }
    
    public static Source testSource1()
    {
        return new Source(testAirport1().getAirportName(), LocalDate.now(), "12:00 pm");
    }
    
    public static Source testSource2()
    {
        return new Source(testAirport2().getAirportName(), LocalDate.now(), "3:00 pm");
    }
    
    public static Source testSource3()
    {
        return new Source(testAirport3().getAirportName(), LocalDate.now(), "6:00 pm");
    }
    
    public static Destination testDestination1()
    {
        return new Destination(testAirport1().getAirportName(), LocalDate.now(), "3:00 am");
    }
    
    public static Destination testDestination2()
    {
        return new Destination(testAirport2().getAirportName(), LocalDate.now(), "9:00 pm");
    }
    
    public static Destination testDestination3()
    {
        return new Destination(testAirport3().getAirportName(), LocalDate.now(), "1:00 pm");
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
    
    public static Set<Reservation> testReservation()
    {
        Set<Reservation> reservations = new LinkedHashSet <>();
        reservations.add(new Reservation(testCustomer(), testFlight1(), LocalDate.now(), STATUS_ACTIVE(), 0));
        reservations.add(new Reservation(testCustomer(), testFlight2(), LocalDate.now(), STATUS_CANCELED(), 1));
        return reservations;
    }
    
    public static Set<Reservation> testReservation2()
    {
        Set<Reservation> reservations = new LinkedHashSet <>();
        reservations.add(new Reservation(testCustomer(), testFlight2(), LocalDate.now(), STATUS_CANCELED(), 0));
        reservations.add(new Reservation(testCustomer(), testFlight1(), LocalDate.now(), STATUS_CANCELED(), 1));
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
    
    public static List<Airplane> airPlanes()
    {
        List<Airplane> airports = new LinkedList <>();
        
        airports.add(new Airplane(11, "AP1"));
        airports.add(new Airplane(12, "AP2"));
        airports.add(new Airplane(13, "AP3"));
        airports.add(new Airplane(14, "AP4"));
        
        return airports;
    }
}
