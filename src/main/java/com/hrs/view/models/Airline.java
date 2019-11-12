package com.hrs.view.models;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class Airline
{
    private Integer airlineId;
    private String airlineName;
    private Set<Airplane> airplanes = new LinkedHashSet <>();
    
    public Airline() {}
    
    public Airline(String airlineName)
    {
        this.airlineName = airlineName;
    }
    
    public Airline(Integer airlineId, String airlineName)
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
    
    public Set <Airplane> getAirplanes()
    {
        return airplanes;
    }
    
    public void setAirplanes(Set <Airplane> airplanes)
    {
        this.airplanes = airplanes;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;
        if(! (o instanceof Airline)) return false;
        Airline airLine = (Airline) o;
        return Objects.equals(getAirlineId(), airLine.getAirlineId())
                && Objects.equals(getAirlineName(), airLine.getAirlineName())
                && Objects.equals(getAirplanes(), airLine.getAirplanes());
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(getAirlineId(), getAirlineName(), getAirplanes());
    }
    
    @Override
    public String toString()
    {
        return "Airline{" + "airlineId=" + airlineId + ", airlineName='" + airlineName + '\'' + '}';
    }
}
