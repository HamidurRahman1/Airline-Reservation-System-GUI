package com.hrs.view.models;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *  A container to represent a complex relationship of Customers-Airports-Airlines into one.
 */
public class Reservation
{
    private Integer reservationId;
    private Customer customer;
    private Flight flight;
    private String localDate;
    private String status;
    
    public Reservation() {}
    
    public Reservation(Flight flight, String localDate, String status)
    {
        this.flight = flight;
        this.localDate = localDate;
        this.status = status;
    }
    
    public Reservation(Customer customer, Flight flight, String localDate, String status)
    {
        this.customer = customer;
        this.flight = flight;
        this.localDate = localDate;
        this.status = status;
    }
    
    public Customer getCustomer()
    {
        return customer;
    }
    
    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }
    
    public Flight getFlight()
    {
        return flight;
    }
    
    public void setFlight(Flight flight)
    {
        this.flight = flight;
    }
    
    public String getLocalDate()
    {
        return localDate;
    }
    
    public void setLocalDate(String localDate)
    {
        this.localDate = localDate;
    }
    
    public String getStatus()
    {
        return status;
    }
    
    public void setStatus(String status)
    {
        this.status = status;
    }
}
