package com.hrs.view.models;

public class Arrival
{
    public String flightName;
    public String airlineName;
    public String sourceName;
    public String time;
    public String status;
    
    public Arrival(String flightName, String airlineName, String sourceName, String time, String status)
    {
        this.flightName = flightName;
        this.airlineName = airlineName;
        this.sourceName = sourceName;
        this.time = time;
        this.status = status;
    }
    
    public String getFlightName()
    {
        return flightName;
    }
    
    public void setFlightName(String flightName)
    {
        this.flightName = flightName;
    }
    
    public String getAirlineName()
    {
        return airlineName;
    }
    
    public void setAirlineName(String airlineName)
    {
        this.airlineName = airlineName;
    }
    
    public String getSourceName()
    {
        return sourceName;
    }
    
    public void setSourceName(String sourceName)
    {
        this.sourceName = sourceName;
    }
    
    public String getTime()
    {
        return time;
    }
    
    public void setTime(String time)
    {
        this.time = time;
    }
    
    public String getStatus()
    {
        return status;
    }
    
    public void setStatus(String status)
    {
        this.status = status;
    }
    
    @Override
    public String toString()
    {
        return "Arrival{" + "flightName='" + flightName + '\'' + ", airlineName='" + airlineName
                + '\'' + ", sourceName='" + sourceName + '\'' + ", time='" + time + '\'' + ", status='"
                + status + '\'' + '}';
    }
}
