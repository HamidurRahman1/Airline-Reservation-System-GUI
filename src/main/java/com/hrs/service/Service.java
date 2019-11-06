package com.hrs.service;

/**
 *  A interface defines all the service methods that will be invoked from View to Backend to retrieve data.
 */
public interface Service
{
    public void getAllFlights();
    public void getAllFlightsByAirline(String airlineName);
    public void validateCustomerLogin(String username, String password);
    public void validateAirlineAdminLogin(String username, String password);
    
    public void insertNewCustomer(String firstName, String lastName, String email, String password);
}
