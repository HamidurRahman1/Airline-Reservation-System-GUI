package com.hrs.view.models;

import java.io.Serializable;

public class Customer implements Serializable
{
    private Integer customerId;
    private String firstName;
    private String lastName;
    
    public Customer() {}
    
    public Customer(String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public Customer(Integer customerId, String firstName, String lastName)
    {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public Integer getCustomerId()
    {
        return customerId;
    }
    
    public void setCustomerId(Integer customerId)
    {
        this.customerId = customerId;
    }
    
    public String getFirstName()
    {
        return firstName;
    }
    
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    
    public String getLastName()
    {
        return lastName;
    }
    
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
}
