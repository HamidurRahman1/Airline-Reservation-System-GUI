package com.hrs.service;

import com.hrs.exceptions.InvalidLoginException;
import com.hrs.test.Tester;
import com.hrs.view.models.Admin;
import com.hrs.view.models.Airline;
import com.hrs.view.models.Airplane;
import com.hrs.view.models.Airport;
import com.hrs.view.models.Customer;
import com.hrs.view.models.Destination;
import com.hrs.view.models.Flight;
import com.hrs.view.models.Reservation;
import com.hrs.view.models.Source;
import com.hrs.view.util.FieldValue;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

import static com.hrs.test.Tester.STATUS_ACTIVE;
import static com.hrs.test.Tester.STATUS_CANCELED;
import static com.hrs.test.Tester.STATUS_ON_TIME;
import static com.hrs.test.Tester.testCustomer;
import static com.hrs.test.Tester.testFlight1;
import static com.hrs.test.Tester.testFlight2;

/**
 * A service class that provides database access
 */
public class ApiService implements Services
{
    @Override
    public Set<Reservation> getAllReservationsMadeUsingSearchEngineAndAirlineGui(String airlineName)
    {
        return null;
    }
    
    @Override
    public Set<Flight> getAllFlightsByAirline(String airlineName, LocalDate localDate)
    {
        return Tester.testFlights();
    }
    
    @Override
    public Set<Flight> getAllFlightsForReservation(String query)
    {
        return Tester.testFlights();
    }
    
    @Override
    public Set<Reservation> getAllReservationsByCustomerId(Integer customerId)
    {
        Set<Reservation> reservations = new LinkedHashSet <>();
    
        Flight f2 = new Flight("8PK9",
                new Source("LGA", LocalDate.of(2019, 11, 12), "3:00 pm"),
                new Destination("TXD", LocalDate.of(2019, 11, 12), "9:00 pm"),
                STATUS_ON_TIME(), new Airline("Delta Ar."), new Airplane("D 909P"), 110f);
    
        Flight f3 = new Flight("9PU7",
                new Source("LAC", LocalDate.of(2019, 11, 20), "12:00 pm"),
                new Destination("JFK", LocalDate.of(2019, 11, 20), "6:00 pm"),
                STATUS_ON_TIME(), new Airline("Jet Blue"), new Airplane("JB P17B"), 120f);
    
        reservations.add(new Reservation(f2, LocalDate.of(2019, 11, 7), STATUS_CANCELED(), 0));
        reservations.add(new Reservation(f3, LocalDate.now(), STATUS_ACTIVE(), 1));
    
        return reservations;
    }
    
    @Override
    public Customer getCustomerByLogin(String username, String password) throws InvalidLoginException
    {
        return Tester.testCustomer();
    }
    
    @Override
    public Admin getGlobalAdminByLogin(String username, String password) throws InvalidLoginException
    {
        return Tester.admin();
    }
    
    @Override
    public Admin getAirlineAdminByLogin(String airline, String username, String password) throws InvalidLoginException
    {
        return new Admin("Hamidur", "Rahman");
    }
    
    @Override
    public Set<Reservation> getGlobalReservationsMadeUsingSearchEngine()
    {
        Set<Reservation> reservations = new LinkedHashSet <>();
        reservations.add(new Reservation(testCustomer(), testFlight1(), LocalDate.now(), STATUS_ACTIVE(), 0));
        reservations.add(new Reservation(testCustomer(), testFlight2(), LocalDate.now(), STATUS_CANCELED(), 0));
        return reservations;
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
    public Set<Flight> getAllFlightsByAirlineForReservation(String airlineName)
    {
        return Tester.testFlights();
    }
    
    @Override
    public boolean insertNewCustomer(String firstName, String lastName, String email, String password)
            throws IllegalArgumentException
    {
        try
        {
        
        }
        catch(Exception ex)
        {
        
        }
        return true;
    }
    
    @Override
    public boolean cancelReservation(Integer customerId, Integer reservationId)
    {
        return true;
    }
    
    @Override
    public boolean cancelFlight(Integer flightId)
    {
        return true;
    }
    
    @Override
    public boolean makeReservation(Integer flightIdPk, String username, String password) throws InvalidLoginException
    {
        return true;
    }
    
    @Override
    public boolean makeReservation(Integer flightIdPk, Integer customerId)
    {
        return true;
    }
    
    @Override
    public boolean makeReservationBySearchEngine(Integer flightIdPk, String username, String password) throws InvalidLoginException
    {
        return true;
    }
    
    @Override
    public boolean makeReservationBySearchEngine(Integer flightIdPk, Integer customerId)
    {
        return true;
    }
    
    @Override
    public boolean insertFlightByAirline(Flight flight) throws IllegalArgumentException
    {
        return true;
    }
    
    @Override
    public Set<Flight> getAllFlightsByAirport(String airportName)
    {
        Set<Flight> flights = new LinkedHashSet <>();
        
        flights.add(new Flight("C1D8", new Source("NY"), new Destination("TXD"), FieldValue.ON_TIME, new Airline("AME. Airline"), new Airplane("AA109K")));
        flights.add(new Flight("HS78", new Source("NY"), new Destination("LA"), FieldValue.CANCELED, new Airline("JB"), new Airplane("JB90P")));
    
        flights.add(new Flight("AY90", new Source("NC"), new Destination("NY"), FieldValue.ON_TIME, new Airline("Delta"), new Airplane("D01L")));
        flights.add(new Flight("78T6", new Source("MI"), new Destination("NY"), FieldValue.CANCELED, new Airline("JB"), new Airplane("JB9Y")));
        
        return flights;
    }
}
