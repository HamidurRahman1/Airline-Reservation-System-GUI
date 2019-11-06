package com.hrs.view.models;


/**
 *  This class represent the Airport where the flight is going to take of from.
 */
public class Source extends Airport
{
    public Source()
    {
        super();
    }
    
    public Source(String airportName)
    {
        super(airportName);
    }
    
    public Source(Integer airportId, String airportName)
    {
        super(airportId, airportName);
    }
    
    @Override
    public String toString()
    {
        return super.toString();
    }
}
