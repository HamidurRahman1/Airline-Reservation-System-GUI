package com.hrs.view.models;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class AirLine
{
    private Integer airlineId;
    private String airlineName;
    private Set<AirPlane> airPlanes = new LinkedHashSet <>();
    
    public AirLine() {}
    
    public AirLine(String airlineName)
    {
        this.airlineName = airlineName;
    }
    
    public AirLine(Integer airlineId, String airlineName)
    {
        this.airlineId = airlineId;
        this.airlineName = airlineName;
    }
    
    public Integer getAirlineId()
    {
        return airlineId;
    }
    
    public void setAirlineId(Integer airlineId)
    {
        this.airlineId = airlineId;
    }
    
    public String getAirlineName()
    {
        return airlineName;
    }
    
    public void setAirlineName(String airlineName)
    {
        this.airlineName = airlineName;
    }
    
    public Set <AirPlane> getAirPlanes()
    {
        return airPlanes;
    }
    
    public void setAirPlanes(Set <AirPlane> airPlanes)
    {
        this.airPlanes = airPlanes;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;
        if(! (o instanceof AirLine)) return false;
        AirLine airLine = (AirLine) o;
        return Objects.equals(getAirlineId(), airLine.getAirlineId())
                && Objects.equals(getAirlineName(), airLine.getAirlineName())
                && Objects.equals(getAirPlanes(), airLine.getAirPlanes());
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(getAirlineId(), getAirlineName(), getAirPlanes());
    }
    
    @Override
    public String toString()
    {
        return "AirLine{" + "airlineId=" + airlineId + ", airlineName='" + airlineName + '\'' + '}';
    }
}
