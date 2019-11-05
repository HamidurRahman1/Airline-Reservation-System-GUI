package com.hrs.view.models;

import java.io.Serializable;
import java.util.List;

/**
 *  A model to represent customer.
 */
public class Customer extends Person implements Serializable
{
    private Integer customerId;
    private Login login;
    
    public Customer()
    {
        this(null, null, null);
    }
    
    public Customer(String firstName, String lastName)
    {
        this(null, firstName, lastName);
    }
    
    public Customer(Integer customerId, String firstName, String lastName)
    {
        super(firstName, lastName);
        this.customerId = customerId;
    }
    
    public Integer getCustomerId()
    {
        return customerId;
    }
    
    public void setCustomerId(Integer customerId)
    {
        this.customerId = customerId;
    }
    
    @Override
    public String toString()
    {
        return "[" + customerId + ", " + super.getFirstName() + ", " + super.getLastName() + "]";
    }
}
