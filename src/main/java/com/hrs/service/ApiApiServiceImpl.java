package com.hrs.service;

import com.hrs.exceptions.InvalidPasswordException;
import com.hrs.exceptions.InvalidUserNameException;
import com.hrs.test.Tester;
import com.hrs.view.models.Admin;
import com.hrs.view.models.Airline;
import com.hrs.view.models.Airplane;
import com.hrs.view.models.Airport;
import com.hrs.view.models.Customer;
import com.hrs.view.models.Flight;
import com.hrs.view.models.Reservation;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static com.hrs.test.Tester.STATUS_ACTIVE;
import static com.hrs.test.Tester.STATUS_CANCELED;
import static com.hrs.test.Tester.testCustomer;
import static com.hrs.test.Tester.testFlight1;
import static com.hrs.test.Tester.testFlight2;

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
    public Set<Flight> getAllFlightsByAirline(String airlineName)
    {
        return new LinkedHashSet <>();
    }
    
    @Override
    public Set<Reservation> getAllReservationsByCustomerId(Integer customerId)
    {
        return null;
    }
    
    @Override
    public Customer getCustomerByLogin(String username, String password) throws InvalidUserNameException, InvalidPasswordException
    {
        return null;
    }
    
    @Override
    public Admin getGlobalAdminByLogin(String username, String password) throws InvalidUserNameException, InvalidPasswordException
    {
        return Tester.admin();
    }
    
    @Override
    public Admin getAirlineAdminByLogin(String airline, String username, String password) throws InvalidUserNameException, InvalidPasswordException
    {
        return new Admin("Hamidur", "Rahman");
    }
    
    @Override
    public Set<Reservation> getGlobalReservations()
    {
        Set<Reservation> reservations = new LinkedHashSet <>();
        reservations.add(new Reservation(testCustomer(), testFlight1(), LocalDate.now(), STATUS_ACTIVE(), 0));
        reservations.add(new Reservation(testCustomer(), testFlight2(), LocalDate.now(), STATUS_CANCELED(), 0));
        return reservations;
    }
    
    @Override
    public Set<Reservation> getCustomerReservations(Integer customerId)
    {
        return null;
    }
    
    @Override
    public Set<Airplane> getAllAirPlaneByAirLine(String airlineName)
    {
        Set<Airplane> airplanes = new LinkedHashSet <>();
        airplanes.add(new Airplane(11, "AP1"));
        airplanes.add(new Airplane(12, "AP2"));
        airplanes.add(new Airplane(13, "AP3"));
        airplanes.add(new Airplane(14, "AP4"));
        return airplanes;
    }
    
    @Override
    public Set<Airport> getAllAirports()
    {
        Set<Airport> airports = new LinkedHashSet <>();
        airports.add(new Airport(101, "A1"));
        airports.add(new Airport(102, "A2"));
        airports.add(new Airport(103, "A3"));
        airports.add(new Airport(104, "A4"));
        return airports;
    }
    
    @Override
    public Set<Reservation> getAllReservationsByAirline(String airlineName)
    {
        return null;
    }
    
    @Override
    public boolean insertNewCustomer(String firstName, String lastName, String email, String password)
    {
        return false;
    }
    
    @Override
    public boolean cancelReservation(Integer customerId, Integer reservationId)
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
    public boolean makeReservationBySE(Integer flightIdPk)
    {
        return false;
    }
    
    @Override
    public boolean insertFlightByAirline(Flight flight)
    {
        return true;
    }
}
