package com.hrs.view.models;

import java.util.Objects;

public class Airplane
{
    private Integer airPlaneId;
    private String airPlaneName;
    
    public Airplane() {}
    
    public Airplane(String airPlaneName)
    {
        this.airPlaneName = airPlaneName;
    }
    
    public Airplane(Integer airPlaneId, String airPlaneName)
    {
        this.airPlaneId = airPlaneId;
        this.airPlaneName = airPlaneName;
    }
    
    public Integer getAirPlaneId()
    {
        return airPlaneId;
    }
    
    public void setAirPlaneId(Integer airPlaneId)
    {
        this.airPlaneId = airPlaneId;
    }
    
    public String getAirPlaneName()
    {
        return airPlaneName;
    }
    
    public void setAirPlaneName(String airPlaneName)
    {
        this.airPlaneName = airPlaneName;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;
        if(! (o instanceof Airplane)) return false;
        Airplane airPlane = (Airplane) o;
        return Objects.equals(getAirPlaneId(), airPlane.getAirPlaneId())
                && Objects.equals(getAirPlaneName(), airPlane.getAirPlaneName());
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(getAirPlaneId(), getAirPlaneName());
    }
    
    @Override
    public String toString()
    {
        return this.getAirPlaneName();
    }
}
