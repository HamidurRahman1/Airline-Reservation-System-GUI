package com.hrs.service;

import com.hrs.exceptions.InvalidPasswordException;
import com.hrs.exceptions.InvalidUserNameException;
import com.hrs.view.models.Admin;
import com.hrs.view.models.Airline;
import com.hrs.view.models.Airplane;
import com.hrs.view.models.Airport;
import com.hrs.view.models.Customer;
import com.hrs.view.models.Flight;
import com.hrs.view.models.Reservation;

import java.time.LocalDate;
import java.util.List;

/**
 * A service class that provides database access
 */
public class ApiApiServiceImpl implements ApiService
{
    @Override
    public void getAllFlightsForReservation()
    {
    
    }
    
    @Override
    public void getAllFlightsByCustomerId(Integer customerId)
    {
    
    }
    
    @Override
    public void getAllFlightsByAirlineForReservation(String airlineName)
    {
    
    }
    
    @Override
    public Customer getCustomerByLogin(String username, String password) throws InvalidUserNameException, InvalidPasswordException
    {
        return null;
    }
    
    @Override
    public Admin getGlobalAdminByLogin(String username, String password) throws InvalidUserNameException, InvalidPasswordException
    {
        return null;
    }
    
    @Override
    public Admin getAirlineAdminByLogin(String airline, String username, String password) throws InvalidUserNameException, InvalidPasswordException
    {
        return null;
    }
    
    @Override
    public List <Reservation> getGlobalReservations()
    {
        return null;
    }
    
    @Override
    public List <Reservation> getCustomerReservations(Integer customerId)
    {
        return null;
    }
    
    @Override
    public List <Airplane> getAllAirPlaneByAirLine(String airlineName)
    {
        return null;
    }
    
    @Override
    public List <Airport> getAllAirports()
    {
        return null;
    }
    
    @Override
    public List <Reservation> getAllReservationsByAirline(String airlineName)
    {
        return null;
    }
    
    @Override
    public boolean insertNewCustomer(String firstName, String lastName, String email, String password)
    {
        return true;
    }
    
    @Override
    public boolean cancelReservation(Integer customerId, LocalDate localDate, Integer flightId, Integer airlineId)
    {
        return false;
    }
    
    @Override
    public void cancelReservation2testFunc(Integer customerId)
    {
    
    }
    
    @Override
    public boolean cancelFlight(Integer flightId)
    {
        return false;
    }
    
    @Override
    public boolean makeReservation(Integer flightIdPk, String username, String password) throws InvalidUserNameException, InvalidPasswordException
    {
        return false;
    }
    
    @Override
    public boolean makeReservation(Integer flightIdPk, Integer customerId)
    {
        return false;
    }
    
    @Override
    public boolean makeReservationBySE(Integer flightIdPk, String username, String password) throws InvalidUserNameException, InvalidPasswordException
    {
        return false;
    }
    
    @Override
    public boolean makeReservationBySE(Integer flightIdPk, Integer customerId)
    {
        return false;
    }
    
    @Override
    public boolean insertFlightByAirline(Airline airLine, Flight flight)
    {
        return false;
    }
}
