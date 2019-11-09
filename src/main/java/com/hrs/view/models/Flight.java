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
    public String status;       // status -> onTime / canceled
    public String airline;
    public String fare;
    
    public Boolean isFull;
    
    public Flight() {}
    
    public Flight(String flightName, String source, String destination, String airline, String date, String fare, String status)
    {
        this.flightName = flightName;
        this.airline = airline;
        this.source = source;
        this.destination = destination;
        this.date = date;
        this.status = status;
        this.fare = fare;
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
    
    public Integer getFlightId()
    {
        return flightId;
    }
    
    public void setFlightId(Integer flightId)
    {
        this.flightId = flightId;
    }
    
    public String getFlightName()
    {
        return flightName;
    }
    
    public void setFlightName(String flightName)
    {
        this.flightName = flightName;
    }
    
    public String getSource()
    {
        return source;
    }
    
    public void setSource(String source)
    {
        this.source = source;
    }
    
    public String getDestination()
    {
        return destination;
    }
    
    public void setDestination(String destination)
    {
        this.destination = destination;
    }
    
    public String getDate()
    {
        return date;
    }
    
    public void setDate(String date)
    {
        this.date = date;
    }
    
    public String getAirline()
    {
        return airline;
    }
    
    public void setAirline(String airline)
    {
        this.airline = airline;
    }
    
    public String getFare()
    {
        return fare;
    }
    
    public void setFare(String fare)
    {
        this.fare = fare;
    }
}
