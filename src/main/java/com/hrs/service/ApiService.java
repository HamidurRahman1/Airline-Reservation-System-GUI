package com.hrs.service;

import com.hrs.exceptions.InvalidPasswordException;
import com.hrs.exceptions.InvalidUserNameException;
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
 *  A interface defines all the service methods that will be invoked from View to Backend to retrieve data.
 */
public interface ApiService
{
    // Returns list of flights for global reservation, must include flights that are booked as well
    void getAllFlightsForReservation();
    
    void getAllFlightsByCustomerId(Integer customerId);
    
    void getAllFlightsByAirlineForReservation(String airlineName);
    
    Customer getCustomerByLogin(String username, String password)
            throws InvalidUserNameException, InvalidPasswordException;
    
    Admin getGlobalAdminByLogin(String username, String password)
            throws InvalidUserNameException, InvalidPasswordException;
    
    Admin getAirlineAdminByLogin(String airline, String username, String password)
            throws InvalidUserNameException, InvalidPasswordException;
    
    // Must include active or canceled reservations
    List<Reservation> getGlobalReservations();
    
    List<Reservation> getCustomerReservations(Integer customerId);
    
    List<AirPlane> getAllAirPlaneByAirLine(String airlineName);
    
    List<Airport> getAllAirports();
    
    // Must include active and canceled reservations
    List<Reservation> getAllReservationsByAirline(String airlineName);
    
    boolean insertNewCustomer(String firstName, String lastName, String email, String password);
    
    boolean cancelReservation(Integer customerId, LocalDate localDate, Integer flightId, Integer airlineId);
    
    void cancelReservation2testFunc(Integer customerId);
    
    boolean cancelFlight(Integer flightId);
    
    boolean makeReservation(Integer flightIdPk, String username, String password)
            throws InvalidUserNameException, InvalidPasswordException;
    
    boolean makeReservation(Integer flightIdPk, Integer customerId);
    
    boolean makeReservationBySE(Integer flightIdPk, String username, String password)
            throws InvalidUserNameException, InvalidPasswordException;
    
    boolean makeReservationBySE(Integer flightIdPk, Integer customerId);
    
    boolean insertFlightByAirline(AirLine airLine, Flight flight);
}
