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
    
    @Override
    public String toString()
    {
        return "Airport{" + "airportId=" + airportId + ", airportName='" + airportName + '\'' + '}';
    }
}
