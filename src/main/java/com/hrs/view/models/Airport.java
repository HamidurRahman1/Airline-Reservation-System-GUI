package com.hrs.view.models;

/**
 *  A base class for Source and Destination
 */
public class Airport
{
    private Integer airportId;
    private String airportName;
    
    public Airport() {}
    
    public Airport(String airportName)
    {
        this.airportName = airportName;
    }
    
    public Airport(Integer airportId, String airportName)
    {
        this.airportName = airportName;
        this.airportId = airportId;
    }
    
    public String getAirportName()
    {
        return airportName;
    }
    
    public Integer getAirportId()
    {
        return airportId;
    }
    
    public void setAirportId(Integer airportId)
    {
        this.airportId = airportId;
    }
    
    public void setAirportName(String airportName)
    {
        this.airportName = airportName;
    }
    
    @Override
    public String toString()
    {
        return this.getAirportName();
    }
}
