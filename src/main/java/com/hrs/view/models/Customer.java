package com.hrs.view.models;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * A model to represent customer.
 */
public class Customer extends Person implements Serializable
{
    private Integer customerId;
    private Login login;
    private List<Reservation> reservations = new LinkedList <>();
    private List<Flight> flights = new LinkedList <>();

    public Customer()
    {
        this(null, null, null);
    }

    public Customer(String firstName, String lastName)
    {
        this(101, firstName, lastName);
    }

    public Customer(Integer customerId, String firstName, String lastName)
    {
        super(firstName, lastName);
        this.customerId = customerId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
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
    
    public List <Flight> getFlights()
    {
        return flights;
    }
    
    public void setFlights(List <Flight> flights)
    {
        this.flights = flights;
    }
    
    @Override
    public String toString() {
        return "[" + customerId + ", " + super.getFirstName() + ", " + super.getLastName() + "]";
    }
}
