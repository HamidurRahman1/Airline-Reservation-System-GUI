package com.hrs.view.models;

import java.time.LocalDate;
import java.util.Objects;

/**
 *  A container to represent a complex relationship of Customers-Airports-Airlines into one.
 */
public class Reservation
{
    private Integer reservationId;
    private Customer customer;
    private Flight flight;
    private LocalDate rsvpDate;
    private String status;
    private Integer rsvpBy;         // 0 - Search Engine, 1 - Airline web page
    
    public Reservation() {}
    
    public Reservation(Flight flight, LocalDate rsvpDate, String status, Integer rsvpBy)
    {
        this.flight = flight;
        this.rsvpDate = rsvpDate;
        this.status = status;
        this.rsvpBy = rsvpBy;
    }
    
    public Reservation(Customer customer, Flight flight, LocalDate rsvpDate, String status, Integer rsvpBy)
    {
        this.customer = customer;
        this.flight = flight;
        this.rsvpDate = rsvpDate;
        this.status = status;
        this.rsvpBy = rsvpBy;
    }
    
    public Reservation(Integer reservationId, Customer customer, Flight flight, LocalDate rsvpDate, String status, Integer rsvpBy)
    {
        this.reservationId = reservationId;
        this.customer = customer;
        this.flight = flight;
        this.rsvpDate = rsvpDate;
        this.status = status;
        this.rsvpBy = rsvpBy;
    }
    
    public Customer getCustomer()
    {
        return customer;
    }
    
    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }
    
    public Flight getFlight()
    {
        return flight;
    }
    
    public void setFlight(Flight flight)
    {
        this.flight = flight;
    }
    
    public Integer getReservationId()
    {
        return reservationId;
    }
    
    public void setReservationId(Integer reservationId)
    {
        this.reservationId = reservationId;
    }
    
    public LocalDate getRsvpDate()
    {
        return rsvpDate;
    }
    
    public void setRsvpDate(LocalDate rsvpDate)
    {
        this.rsvpDate = rsvpDate;
    }
    
    public String getStatus()
    {
        return status;
    }
    
    public void setStatus(String status)
    {
        this.status = status;
    }
    
    public Integer getRsvpBy()
    {
        return rsvpBy;
    }
    
    public void setRsvpBy(Integer rsvpBy)
    {
        this.rsvpBy = rsvpBy;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;
        if(! (o instanceof Reservation)) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(getReservationId(), that.getReservationId())
                && Objects.equals(getCustomer(), that.getCustomer())
                && Objects.equals(getFlight(), that.getFlight())
                && Objects.equals(getRsvpDate(), that.getRsvpDate())
                && Objects.equals(getStatus(), that.getStatus())
                && Objects.equals(getRsvpBy(), that.getRsvpBy());
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(getReservationId(), getCustomer(), getFlight(), getRsvpDate(), getStatus(), getRsvpBy());
    }
    
    @Override
    public String toString()
    {
        return "Reservation{" + "reservationId=" + reservationId + ", customer=" + customer + ", flight=" +
                flight + ", rsvpDate=" + rsvpDate + ", status='" + status + '\'' + ", rsvpBy=" + rsvpBy + '}';
    }
}
