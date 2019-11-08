package com.hrs.service;

import com.hrs.view.models.Customer;
import com.hrs.view.models.Flight;

import java.time.LocalDate;

/**
 *  A interface defines all the service methods that will be invoked from View to Backend to retrieve data.
 */
public interface Service
{
    public void getAllFlights();
    public void getAllFlightsByCustomerId(Integer customerId);
    public void getAllFlightsByAirline(String airlineName);
    public Customer getCustomerByLogin(String username, String password);
    public void validateAirlineAdminLogin(String username, String password);
    
    public boolean insertNewCustomer(String firstName, String lastName, String email, String password);
    public void cancelReservation(Integer customerId, LocalDate localDate, Integer flightId, Integer airlineId);
    
    public void cancelReservation2testFunc(Integer customerId);
}
