package com.hrs.service;

import com.hrs.configs.Configuration;
import com.hrs.dao.dbServices.DB2;
import com.hrs.exceptions.IllegalArgumentException;
import com.hrs.exceptions.InvalidLoginException;

import com.hrs.resources.FieldValue;
import com.hrs.view.models.Admin;
import com.hrs.view.models.Airline;
import com.hrs.view.models.Airplane;
import com.hrs.view.models.Airport;
import com.hrs.view.models.Customer;
import com.hrs.view.models.Flight;
import com.hrs.view.models.Reservation;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Set;

/**
 * A service class that provides database access
 */
public class ApiService implements Services
{
    private DB2 databaseService = Configuration.GET_DB2_SERVICE();
    
    // first character has to be letter, must contain at least 4 character and no more then 14 character (number, letter and _)
    public static final String passRegEx = "^[a-zA-Z0-9]\\w{3,14}$";

    //character@test.test
    public static final String emailRegEx = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    //name validation
    public static final String nameRegEx = "^[a-zA-Z]\\w{3,14}$";
    
    @Override
    public Set<Flight> getAllFlightsForReservation(String query) throws IllegalArgumentException
    {
        return databaseService.getAllFlightsForReservation(query);
    }
    
    @Override
    public Set<Flight> getAllFlightsByAirlineForReservation(String airlineName) throws IllegalArgumentException
    {
        return databaseService.getAllFlightsByAirlineForReservation(airlineName);
    }
    
    @Override
    public Set<Flight> getAllFlightsByAirline(String airlineName, LocalDate localDate) throws IllegalArgumentException
    {
        return databaseService.getAllFlightsByAirline(airlineName, Configuration.GET_CURRENT_DATE());
    }
    
    @Override
    public Set<Reservation> getAllReservationsByCustomerId(Integer customerId)
    {
        return databaseService.getAllReservationsByCustomerId(customerId);
    }
    
    @Override
    public Set<Reservation> getAllReservationsMadeUsingSearchEngineAndAirlineGui(String airlineName)
    {
        return databaseService.getAllReservationsMadeUsingSearchEngineAndAirlineGui(airlineName);
    }
    
    @Override
    public Customer getCustomerByLogin(String username, String password) throws InvalidLoginException
    {
        return databaseService.getCustomerByLogin(username, password);
    }
    
    @Override
    public Admin getGlobalAdminByLogin(String username, String password) throws InvalidLoginException
    {
        return databaseService.getGlobalAdminByLogin(username, password);
    }
    
    @Override
    public Admin getAirlineAdminByLogin(String airline, String username, String password) throws InvalidLoginException
    {
        return databaseService.getAirlineAdminByLogin(airline, username, password);
    }
    
    @Override
    public Set<Reservation> getGlobalReservationsMadeUsingSearchEngine()
    {
        return databaseService.getGlobalReservationsMadeUsingSearchEngine();
    }
    
    @Override
    public Set<Flight> getAllFlightsByAirport(String airportName)
    {
        return databaseService.getAllFlightsByAirport(airportName);
    }
    
    @Override
    public Set<Airplane> getAllAirPlaneByAirLine(String airlineName) throws IllegalArgumentException
    {
        return databaseService.getAllAirPlaneByAirLine(airlineName);
    }
    
    @Override
    public Set<Airport> getAllAirports()
    {
        return databaseService.getAllAirports();
    }
    
    @Override
    public boolean insertNewCustomer(String firstName, String lastName, String email, String password) throws IllegalArgumentException
    {
        if(!firstName.matches(nameRegEx))
            throw new IllegalArgumentException(("Invalid first name provided, length must be at least 4 char long. " +
                    "given firstName=").concat(firstName));
        if(!lastName.matches(nameRegEx))
            throw new IllegalArgumentException(("Invalid last name provided, length must be at least 4 char long. " +
                    "given lastName=").concat(lastName));
        if(!email.matches(emailRegEx))
            throw new IllegalArgumentException(("Invalid email provided. given email=").concat(email));
        if(!password.matches(passRegEx))
            throw new IllegalArgumentException("Invalid password provided, length must be at least 4 har long. " +
                    "given password=".concat(password));
    
        return databaseService.insertNewCustomer(firstName, lastName, email, password);
    }
    
    @Override
    public boolean insertFlightByAirline(Flight flight) throws IllegalArgumentException
    {
        return databaseService.insertFlightByAirline(flight);
    }
    
    @Override
    public boolean cancelReservation(Integer customerId, Integer reservationId)
    {
        return databaseService.cancelReservation(customerId, reservationId);
    }
    
    @Override
    public boolean cancelFlight(Integer flightId)
    {
        return databaseService.cancelFlight(flightId);
    }
    
    @Override
    public boolean makeReservation(Integer flightIdPk, String username, String password) throws InvalidLoginException
    {
        return databaseService.makeReservation(flightIdPk, username, password);
    }
    
    @Override
    public boolean makeReservation(Integer flightIdPk, Integer customerId)
    {
        return databaseService.makeReservation(flightIdPk, customerId);
    }
    
    @Override
    public boolean makeReservationBySearchEngine(Integer flightIdPk, String username, String password) throws InvalidLoginException
    {
        return databaseService.makeReservationBySearchEngine(flightIdPk, username, password);
    }
    
    @Override
    public boolean makeReservationBySearchEngine(Integer flightIdPk, Integer customerId)
    {
        return databaseService.makeReservationBySearchEngine(flightIdPk, customerId);
    }
    
    @Override
    public Airline getAirlineByName(String airlineName)
    {
        return databaseService.getAirlineByName(airlineName);
    }
}
