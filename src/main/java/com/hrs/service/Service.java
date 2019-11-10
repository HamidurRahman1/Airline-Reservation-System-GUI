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
public interface Service
{
    void getAllFlights();
    void getAllFlightsByCustomerId(Integer customerId);
    void getAllFlightsByAirline(String airlineName);
    Customer getCustomerByLogin(String username, String password)
            throws InvalidUserNameException, InvalidPasswordException;
    
    Admin getGlobalAdminByLogin(String username, String password)
            throws InvalidUserNameException, InvalidPasswordException;
    
    Admin getAdminByLogin(String airline, String username, String password)
            throws InvalidUserNameException, InvalidPasswordException;
    
    List<Reservation> getGlobalReservations();
    List<Reservation> getCustomersReservations(Integer customerId);
    
    List<AirPlane> getAllAirPlaneByAirLine(String airlineName);
    List<Airport> getAllAirports();
    
    boolean insertNewCustomer(String firstName, String lastName, String email, String password);
    void cancelReservation(Integer customerId, LocalDate localDate, Integer flightId, Integer airlineId);
    
    void cancelReservation2testFunc(Integer customerId);
    
    boolean makeReservation(Integer flightIdPk, String username, String password)
            throws InvalidUserNameException, InvalidPasswordException;
    
    boolean makeReservation(Integer flightIdPk, Integer customerId);
    
    void insertGlobalReservation(Integer flightIdPk);
    
    boolean addFlightByAirline(AirLine airLine, Flight flight);
}
