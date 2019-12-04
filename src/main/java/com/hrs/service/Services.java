package com.hrs.service;

import com.hrs.exceptions.InvalidLoginException;
import com.hrs.exceptions.IllegalArgumentException;

import com.hrs.view.models.Admin;
import com.hrs.view.models.Airline;
import com.hrs.view.models.Airplane;
import com.hrs.view.models.Airport;
import com.hrs.view.models.Customer;
import com.hrs.view.models.Flight;
import com.hrs.view.models.Reservation;

import java.time.LocalDate;
import java.util.Set;

/**
 *  A interface defines all the service methods that will be invoked from View to Backend to retrieve data.
 */
public interface Services
{
    // Returns a Set of Flights for global reservation, must include flights that are booked as well,
    // as well as it must include all Airline's flight
    Set<Flight> getAllFlightsForReservation(String query) throws IllegalArgumentException;
    
    // Returns a set of flights for reservation by airline name, must include flights that are booked as well
    Set<Flight> getAllFlightsByAirlineForReservation(String airlineName) throws IllegalArgumentException;
    
    // Returns a set of flights from the current date to future dates, those flights will be visible to admin for
    // flight cancellation
    Set<Flight> getAllFlightsByAirline(String airlineName, LocalDate localDate) throws IllegalArgumentException;
    
    // Returns a set of reservations of a customer by id, must include reservation that are cancelled as well
    Set<Reservation> getAllReservationsByCustomerId(Integer customerId);
    
    // Returns a set of Reservation that have been made using search engine and airline gui
    // it includes all the reservations, cancelled or active once, sorted by active once and date
    Set<Reservation> getAllReservationsMadeUsingSearchEngineAndAirlineGui(String airlineName);
    
    // Returns a Customer by login, Customer must be populated with its properties.
    Customer getCustomerByLogin(String username, String password) throws InvalidLoginException;
    
    // Returns an Admin (Global) by login, Admin must be populated with its properties
    Admin getGlobalAdminByLogin(String username, String password) throws InvalidLoginException;
    
    // Returns an Airline Admin by login, Admin must be populated with its properties
    Admin getAirlineAdminByLogin(String airline, String username, String password) throws InvalidLoginException;
    
    // Returns a set of Reservation that have been made using search engine only
    Set<Reservation> getGlobalReservationsMadeUsingSearchEngine();
    
    // Returns a set of Flight that are coming or going from the given airport
    Set<Flight> getAllFlightsByAirport(String airportName);
    
    // Returns a Set of Airplane given Airline name
    Set<Airplane> getAllAirPlaneByAirLine(String airlineName) throws IllegalArgumentException;
    
    // Returns all Airports
    Set<Airport> getAllAirports();
    
    // Insert a new customer into database
    boolean insertNewCustomer(String firstName, String lastName, String email, String password) throws IllegalArgumentException;
    
    // Insert a new flight into database that has been just created by an airline admin
    boolean insertFlightByAirline(Flight flight) throws IllegalArgumentException;
    
    // Customer is canceling a reservation, customer id provided with reservation id
    boolean cancelReservation(Integer customerId, Integer reservationId);
    
    // Admin canceling a Flight, flightId provided
    boolean cancelFlight(Integer flightId);
    
    // Customer making a reservation for a flight, provided flightId and user using customer login (non-logged user)
    // Reservation is being made from Flight provider's web page (GUI)
    boolean makeReservation(Integer flightIdPk, String username, String password) throws InvalidLoginException;
    
    // Customer making a reservation for a flight, provided flightId and user using customer id
    // Reservation is being made from Flight provider's web page (GUI)
    boolean makeReservation(Integer flightIdPk, Integer customerId);
    
    // Customer making a reservation for a flight, provided flightId and user using customer login (non-logged user)
    // Reservation is being made from Search Engine page (GUI)
    boolean makeReservationBySearchEngine(Integer flightIdPk, String username, String password) throws InvalidLoginException;
    
    // Customer making a reservation for a flight, provided flightId and user using customer id
    // Reservation is being made from Search Engine page (GUI)
    boolean makeReservationBySearchEngine(Integer flightIdPk, Integer customerId);
    
    // Returns an Airline object given the name
    Airline getAirlineByName(String airlineName);
}
