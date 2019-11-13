package com.hrs.view.models;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

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
    private Float fare;
    private Integer capacity;
    private Set<Customer> customers = new LinkedHashSet <>();
    
    public Flight() {}
    
    public Flight(String flightCode, Source source, Destination destination, String status, Airline airLine, Airplane airplane)
    {
        this.flightCode = flightCode;
        this.source = source;
        this.destination = destination;
        this.status = status;
        this.airLine = airLine;
        this.airplane = airplane;
    }
    
    public Flight(String flightCode, Source source, Destination destination, String status, Airline airLine, Airplane airplane, Float fare)
    {
        this.flightCode = flightCode;
        this.source = source;
        this.destination = destination;
        this.status = status;
        this.airLine = airLine;
        this.airplane = airplane;
        this.fare = fare;
    }
    
    public Flight(String flightCode, Source source, Destination destination, Integer availableSeat, String status, Airline airLine, Airplane airplane, Float fare)
    {
        this.flightCode = flightCode;
        this.source = source;
        this.destination = destination;
        this.availableSeat = availableSeat;
        this.status = status;
        this.airLine = airLine;
        this.airplane = airplane;
        this.fare = fare;
    }
    
    public Flight(Integer flightId, String flightCode, Source source, Destination destination, Integer availableSeat, String status, Airline airLine, Airplane airplane, Float fare)
    {
        this.flightId = flightId;
        this.flightCode = flightCode;
        this.source = source;
        this.destination = destination;
        this.availableSeat = availableSeat;
        this.status = status;
        this.airLine = airLine;
        this.airplane = airplane;
        this.fare = fare;
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
    
    public Float getFare()
    {
        return fare;
    }
    
    public void setFare(Float fare)
    {
        this.fare = fare;
    }
    
    public Set<Customer> getCustomers()
    {
        return customers;
    }
    
    public void setCustomers(Set<Customer> customers)
    {
        this.customers = customers;
    }
    
    public Integer getCapacity()
    {
        return capacity;
    }
    
    public void setCapacity(Integer capacity)
    {
        this.capacity = capacity;
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
                && Objects.equals(getAirplane(), flight.getAirplane())
                && Objects.equals(fare, flight.fare);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(getFlightId(), getFlightCode(), getSource(),
                getDestination(), getAvailableSeat(), getStatus(), getAirLine(), getAirplane(), fare);
    }
    
    @Override
    public String toString()
    {
        return "Flight{" + "flightId=" + flightId + ", flightCode='" + flightCode + '\'' + ", source=" + source
                + ", destination=" + destination + ", availableSeat=" + availableSeat + ", status='" + status + '\''
                + ", airLine=" + airLine + ", airplane=" + airplane + ", fare=" + fare + '}';
    }
}
