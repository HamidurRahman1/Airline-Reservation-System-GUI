package com.hrs.service;

import com.hrs.exceptions.InvalidUserName;
import com.hrs.view.models.Admin;
import com.hrs.view.models.Customer;
import com.hrs.view.models.Reservation;

import java.time.LocalDate;
import java.util.List;

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
    public void getAdminByAirline(String airlineName);
    
    public boolean makeReservation(Integer flightIdPk, String username) throws InvalidUserName;
    public boolean makeReservation(Integer flightIdPk, Integer customerId);
    
    public void insertGlobalReservation(Integer flightIdPk);
    public Admin getGlobalAdminByLogin(String username, String password);
    
    public List<Reservation> getGlobalReservations();
}
