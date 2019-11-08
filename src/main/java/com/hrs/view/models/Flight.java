package com.hrs.view.models;

import java.time.LocalDate;

/**
 *  A major player in this project.
 */
public class Flight
{
    public Integer flightId;
    public String flightName;
    public String source;
    public String destination;
    public String date;
    public String status;
    public String airline;
    
    public Flight()
    {
    
    }
    
    public Flight(String flightName, String airline, String source, String destination, String date, String status)
    {
        this.flightName = flightName;
        this.airline = airline;
        this.source = source;
        this.destination = destination;
        this.date = date;
        this.status = status;
    }
    
    public Flight(Integer flightId, String flightName, String airline, String source, String destination, String date, String status)
    {
        this.flightId = flightId;
        this.flightName = flightName;
        this.airline = airline;
        this.source = source;
        this.destination = destination;
        this.date = date;
        this.status = status;
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
