package com.hrs.test;

import com.hrs.view.models.Arrival;
import com.hrs.view.models.Customer;
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
        arrivals.add(new Arrival("flight up", "airline", "source", "time", "status"));
        arrivals.add(new Arrival("flight", "airline", "source", "time", "status"));
        arrivals.add(new Arrival("flight", "airline", "source", "time", "status"));
        arrivals.add(new Arrival("flight", "airline", "source", "time", "status"));
        arrivals.add(new Arrival("flight", "airline", "source", "time", "status"));
        return arrivals;
    }
}
