package com.hrs.view.models;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A model to represent customer.
 */
public class Customer extends Person
{
    private Integer customerId;
    private Login login;
    
    private Set<Reservation> reservations = new LinkedHashSet<>();
    private Set<Flight> flights = new LinkedHashSet<>();
    
    public Customer()
    {
        super();
    }
    
    public Customer(String firstName, String lastName)
    {
        super(firstName, lastName);
    }
    
    public Customer(Integer customerId, String firstName, String lastName)
    {
        super(firstName, lastName);
        this.customerId = customerId;
    }
    
    public Customer(Integer customerId, String firstName, String lastName, Login login, Set<Reservation> reservations, Set<Flight> flights)
    {
        super(firstName, lastName);
        this.customerId = customerId;
        this.login = login;
        this.reservations = reservations;
        this.flights = flights;
    }
    
    public Integer getCustomerId()
    {
        return customerId;
    }
    
    public void setCustomerId(Integer customerId)
    {
        this.customerId = customerId;
    }
    
    public Login getLogin()
    {
        return login;
    }
    
    public void setLogin(Login login)
    {
        this.login = login;
    }
    
    public Set<Reservation> getReservations()
    {
        return reservations;
    }
    
    public void setReservations(Set<Reservation> reservations)
    {
        this.reservations = reservations;
    }
    
    public Set<Flight> getFlights()
    {
        return flights;
    }
    
    public void setFlights(Set<Flight> flights)
    {
        this.flights = flights;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;
        if(! (o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(getCustomerId(), customer.getCustomerId())
                && Objects.equals(getLogin(), customer.getLogin())
                && Objects.equals(getReservations(), customer.getReservations())
                && Objects.equals(getFlights(), customer.getFlights());
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(getCustomerId(), getLogin(), getReservations(), getFlights());
    }
    
    @Override
    public String toString()
    {
        return "Customer{" + "customerId=" + customerId + ", login=" + login +'}';
    }
}
