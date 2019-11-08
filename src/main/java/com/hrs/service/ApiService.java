package com.hrs.service;


import com.hrs.test.Tester;
import com.hrs.view.models.Customer;

import java.time.LocalDate;

/**
 * A service class that provides database access
 */
public class ApiService implements Service
{
    @Override
    public void getAllFlightsByCustomerId(Integer customerId)
    {
    
    }
    
    @Override
    public void getAllFlights()
    {
    
    }
    
    @Override
    public void getAllFlightsByAirline(String airlineName)
    {
    
    }
    
    @Override
    public Customer getCustomerByLogin(String username, String password)
    {
        Customer customer = new Customer(101, "First", "Last");
        customer.setFlights(Tester.testFlights());
        return customer;
    }
    
    @Override
    public void validateAirlineAdminLogin(String username, String password)
    {
    
    }
    
    @Override
    public boolean insertNewCustomer(String firstName, String lastName, String email, String password)
    {
        return true;
    }
    
    @Override
    public void cancelReservation(Integer customerId, LocalDate localDate, Integer flightId, Integer airlineId)
    {
    
    }
    
    @Override
    public void cancelReservation2testFunc(Integer customerId)
    {
    
    }
}
