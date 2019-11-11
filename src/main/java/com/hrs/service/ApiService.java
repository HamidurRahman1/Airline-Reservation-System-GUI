package com.hrs.service;


import com.hrs.dao.module.Airplane;
import com.hrs.exceptions.InvalidUserNameException;
import com.hrs.test.Tester;
import com.hrs.view.models.Admin;
import com.hrs.view.models.AirLine;
import com.hrs.view.models.AirPlane;
import com.hrs.view.models.Airport;
import com.hrs.view.models.Customer;
import com.hrs.view.models.Flight;
import com.hrs.view.models.Reservation;

import java.time.LocalDate;
import java.util.List;

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
    
    @Override
    public boolean makeReservation(Integer flightIdPk, String username, String password) throws InvalidUserNameException
    {
        return true;
    }
    
    @Override
    public boolean makeReservation(Integer flightIdPk, Integer customerId)
    {
        return true;
    }
    
    @Override
    public void insertGlobalReservation(Integer flightIdPk)
    {
    
    }
    
    @Override
    public Admin getGlobalAdminByLogin(String username, String password)
    {
        return new Admin("Hamidur", "Rahman");
    }
    
    @Override
    public List <Reservation> getGlobalReservations()
    {
        return null;
    }
    
    @Override
    public List <Reservation> getCustomersReservations(Integer customerId)
    {
        return null;
    }
    
    @Override
    public Admin getAdminByLogin(String airline, String username, String password)
    {
        return null;
    }
    
    @Override
    public List<AirPlane> getAllAirPlaneByAirLine(String airlineName)
    {
        return null;
    }
    
    @Override
    public List<Airport> getAllAirports()
    {
        return null;
    }
    
    @Override
    public boolean addFlightByAirline(AirLine airLine, Flight flight)
    {
        return true;
    }
    
    @Override
    public List<Reservation> getAllReservationsByAirline(String airlineName)
    {
        return null;
    }
    
    @Override
    public boolean cancelFlight(Integer flightId)
    {
        return false;
    }
}
