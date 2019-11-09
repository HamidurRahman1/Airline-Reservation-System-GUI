package com.hrs.test;

import com.hrs.view.models.Admin;
import com.hrs.view.models.Arrival;
import com.hrs.view.models.Customer;
import com.hrs.view.models.Flight;
import com.hrs.view.models.Reservation;
import com.hrs.view.models.Source;

import java.util.LinkedList;
import java.util.List;

public class Tester
{
    public static void main(String[] args)
    {
        System.out.println(testSource());
    }
    
    public static Customer testCustomer()
    {
        return new Customer("Hamidur", "Rahman");
    }
    
    public static Source testSource()
    {
        return new Source(101, "NY");
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
        flights.add(new Flight("f2", "f2", "f3", "f2", "f2", "5", "c"));
        return flights;
    }
    
    public static List<Flight> testFlights2()
    {
        List<Flight> flights = new LinkedList <>();
        flights.add(new Flight("f1", "f1", "f1", "f1", "f1", "20", "c"));
        flights.add(new Flight("f2", "f2", "f2", "f2", "f2", "5", "c"));
        return flights;
    }
    
    public static List<Reservation> testReservation()
    {
        List<Reservation> reservations = new LinkedList <>();
        reservations.add(new Reservation(new Flight("f1", "f1", "f1", "f1", "f1", "20", "c"), "r d", "ca"));
        reservations.add(new Reservation(new Flight("f2", "f2", "f2", "f2", "f2", "5", "c"), "r d", "a"));
        return reservations;
    }
    
    public static Admin admin()
    {
        return new Admin("Hamidur", "Rahman");
    }
}
