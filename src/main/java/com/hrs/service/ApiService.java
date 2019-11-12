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
import java.util.Set;

/**
 *  A interface defines all the service methods that will be invoked from View to Backend to retrieve data.
 */
public interface ApiService
{
    // Returns list of flights for global reservation, must include flights that are booked as well
    void getAllFlightsForReservation();
    
    void getAllFlightsByCustomerId(Integer customerId);
    
    void getAllFlightsByAirlineForReservation(String airlineName);
    
    Set<Flight> getAllFlightsByAirline(String airlineName);
    
    Set<Reservation> getAllReservationsByCustomerId(Integer customerId);
    
    Customer getCustomerByLogin(String username, String password)
            throws InvalidUserNameException, InvalidPasswordException;
    
    Admin getGlobalAdminByLogin(String username, String password)
            throws InvalidUserNameException, InvalidPasswordException;
    
    Admin getAirlineAdminByLogin(String airline, String username, String password)
            throws InvalidUserNameException, InvalidPasswordException;
    
    // Must include active or canceled reservations
    Set<Reservation> getGlobalReservations();
    
    Set<Reservation> getCustomerReservations(Integer customerId);
    
    Set<Airplane> getAllAirPlaneByAirLine(String airlineName);
    
    Set<Airport> getAllAirports();
    
    // Must include active and canceled reservations
    Set<Reservation> getAllReservationsByAirline(String airlineName);
    
    boolean insertNewCustomer(String firstName, String lastName, String email, String password);
    
    boolean cancelReservation(Integer customerId, Integer reservationId);
    
    void cancelReservation2testFunc(Integer customerId);
    
    boolean cancelFlight(Integer flightId);
    
    boolean makeReservation(Integer flightIdPk, String username, String password)
            throws InvalidUserNameException, InvalidPasswordException;
    
    boolean makeReservation(Integer flightIdPk, Integer customerId);
    
    boolean makeReservationBySE(Integer flightIdPk, String username, String password)
            throws InvalidUserNameException, InvalidPasswordException;
    
    boolean makeReservationBySE(Integer flightIdPk);
    
    boolean insertFlightByAirline(Flight flight);
}
