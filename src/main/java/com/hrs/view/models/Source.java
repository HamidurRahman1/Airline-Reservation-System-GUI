package com.hrs.view.models;


import java.time.LocalDate;

/**
 *  This class represent the Airport where the flight is going to take of from.
 */
public class Source extends Airport
{
    private LocalDate date;
    private String time;
    
    public Source()
    {
        super();
    }
    
    public Source(String airportName, LocalDate date, String time)
    {
        super(airportName);
        this.date = date;
        this.time = time;
    }
    
    public Source(Airport airport, LocalDate date, String time)
    {
        super(airport.getAirportName());
        this.date = date;
        this.time = time;
    }
    
    public Source(Integer airportId, String airportName, LocalDate date, String time)
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
