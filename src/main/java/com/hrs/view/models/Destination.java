package com.hrs.view.models;

import java.time.LocalDate;

/**
 *  This class represent the destination of a flight, meaning the Airport.
 */
public class Destination extends Airport
{
    private LocalDate date;
    private String time;
    
    public Destination()
    {
        super();
    }
    
    public Destination(String airportName, LocalDate date, String time)
    {
        super(airportName);
        this.date = date;
        this.time = time;
    }
    
    public Destination(Integer airportId, String airportName, LocalDate date, String time)
    {
        super(airportId, airportName);
        this.date = date;
        this.time = time;
    }
    
    public LocalDate getDate()
    {
        return date;
    }
    
    public void setDate(LocalDate date)
    {
        this.date = date;
    }
    
    public String getTime()
    {
        return time;
    }
    
    public void setTime(String time)
    {
        this.time = time;
    }
    
    @Override
    public String toString()
    {
        return this.getAirportName();
    }
}
