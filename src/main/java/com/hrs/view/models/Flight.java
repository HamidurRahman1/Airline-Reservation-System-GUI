package com.hrs.view.models;

import java.util.Objects;

/**
 *  A major player in this project.
 */
public class Flight
{
    private Integer flightId;
    private String flightCode;
    private Source source;
    private Destination destination;
    private Integer availableSeat;
    private String status;
    private Airline airLine;
    private Airplane airplane;
    
    public Flight() {}
    
    public Flight(String flightCode, Source source, Destination destination, Integer availableSeat, String status, Airline airLine, Airplane airplane)
    {
        this.flightCode = flightCode;
        this.source = source;
        this.destination = destination;
        this.availableSeat = availableSeat;
        this.status = status;
        this.airLine = airLine;
        this.airplane = airplane;
    }
    
    public Flight(Integer flightId, String flightCode, Source source, Destination destination, Integer availableSeat, String status, Airline airLine, Airplane airplane)
    {
        this.flightId = flightId;
        this.flightCode = flightCode;
        this.source = source;
        this.destination = destination;
        this.availableSeat = availableSeat;
        this.status = status;
        this.airLine = airLine;
        this.airplane = airplane;
    }
    
    public Integer getFlightId()
    {
        return flightId;
    }
    
    public void setFlightId(Integer flightId)
    {
        this.flightId = flightId;
    }
    
    public String getFlightCode()
    {
        return flightCode;
    }
    
    public void setFlightCode(String flightCode)
    {
        this.flightCode = flightCode;
    }
    
    public Source getSource()
    {
        return source;
    }
    
    public void setSource(Source source)
    {
        this.source = source;
    }
    
    public Destination getDestination()
    {
        return destination;
    }
    
    public void setDestination(Destination destination)
    {
        this.destination = destination;
    }
    
    public Integer getAvailableSeat()
    {
        return availableSeat;
    }
    
    public void setAvailableSeat(Integer availableSeat)
    {
        this.availableSeat = availableSeat;
    }
    
    public String getStatus()
    {
        return status;
    }
    
    public void setStatus(String status)
    {
        this.status = status;
    }
    
    public Airline getAirLine()
    {
        return airLine;
    }
    
    public void setAirLine(Airline airLine)
    {
        this.airLine = airLine;
    }
    
    public Airplane getAirplane()
    {
        return airplane;
    }
    
    public void setAirplane(Airplane airplane)
    {
        this.airplane = airplane;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;
        if(! (o instanceof Flight)) return false;
        Flight flight = (Flight) o;
        return Objects.equals(getFlightId(), flight.getFlightId())
                && Objects.equals(getFlightCode(), flight.getFlightCode())
                && Objects.equals(getSource(), flight.getSource())
                && Objects.equals(getDestination(), flight.getDestination())
                && Objects.equals(getAvailableSeat(), flight.getAvailableSeat())
                && Objects.equals(getStatus(), flight.getStatus())
                && Objects.equals(getAirLine(), flight.getAirLine())
                && Objects.equals(getAirplane(), flight.getAirplane());
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(getFlightId(), getFlightCode(), getSource(),
                getDestination(), getAvailableSeat(), getStatus(), getAirLine(), getAirplane());
    }
    
    @Override
    public String toString()
    {
        return "Flight{" + "flightId=" + flightId + ", flightCode='" + flightCode + '\'' + ", source=" + source
                + ", destination=" + destination + ", availableSeat=" + availableSeat + ", status='" + status + '\''
                + ", airLine=" + airLine + ", airplane=" + airplane + '}';
    }
}
