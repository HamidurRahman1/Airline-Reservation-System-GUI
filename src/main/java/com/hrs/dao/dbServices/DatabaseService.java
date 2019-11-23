package com.hrs.dao.dbServices;

import com.hrs.configs.Configuration;

import com.hrs.exceptions.IllegalArgumentException;
import com.hrs.exceptions.InvalidLoginException;

import com.hrs.service.Services;
import com.hrs.view.models.Admin;
import com.hrs.view.models.Airplane;
import com.hrs.view.models.Airport;
import com.hrs.view.models.Customer;
import com.hrs.view.models.Flight;
import com.hrs.view.models.Reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

public class DatabaseService implements Services
{
    private Connection connection = null;
    
    public DatabaseService() throws ClassNotFoundException, SQLException
    {
        connection = Configuration.GET_GATEWAY().getConnection();
    }
    
    @Override
    public Set<Flight> getAllFlightsForReservation(String query) throws IllegalArgumentException
    {
        Set<Flight> flights = new LinkedHashSet <>();
    
//        select count('a') from Flights;
//
//        SELECT * FROM Flights ORDER BY flightId asc LIMIT select count('a') from Flights) OFFSET 1
        
        return flights;
    }
    
    @Override
    public Set<Flight> getAllFlightsByAirlineForReservation(String airlineName) throws IllegalArgumentException
    {
        return null;
    }
    
    @Override
    public Set<Flight> getAllFlightsByAirline(String airlineName, LocalDate localDate) throws IllegalArgumentException
    {
        return null;
    }
    
    @Override
    public Set<Reservation> getAllReservationsByCustomerId(Integer customerId)
    {
        return null;
    }
    
    @Override
    public Set<Reservation> getAllReservationsMadeUsingSearchEngineAndAirlineGui(String airlineName)
    {
        return null;
    }
    
    @Override
    public Customer getCustomerByLogin(String username, String password) throws InvalidLoginException
    {
        return null;
    }
    
    @Override
    public Admin getGlobalAdminByLogin(String username, String password) throws InvalidLoginException
    {
        return null;
    }
    
    @Override
    public Admin getAirlineAdminByLogin(String airline, String username, String password) throws InvalidLoginException
    {
        return null;
    }
    
    @Override
    public Set<Reservation> getGlobalReservationsMadeUsingSearchEngine()
    {
        return null;
    }
    
    @Override
    public Set<Flight> getAllFlightsByAirport(String airportName)
    {
        return null;
    }
    
    @Override
    public Set<Airplane> getAllAirPlaneByAirLine(String airlineName) throws IllegalArgumentException
    {
        Set<Airplane> airplanes = new LinkedHashSet <>();
        
        try
        {
//            final String query = select airplaneId, airplaneName from Airplanes
//            where airlineId in (select airlineId from Airlines where airlineName = 'American Airlines')
        }
        catch(Exception ex)
        {
        
        }
        return airplanes;
    }
    
    @Override
    public Set<Airport> getAllAirports()
    {
        Set<Airport> airports = new LinkedHashSet <>();
        try
        {
//            select * from Airports;
        }
        catch(Exception ex)
        {
        
        }
        return airports;
    }
    
    @Override
    public boolean insertNewCustomer(String firstName, String lastName, String email, String password) throws IllegalArgumentException
    {
        return insert_customer_info(firstName, lastName, email, password);
    }
    
    private boolean insert_customer_info(String firstname, String lastname, String email, String password)
            throws IllegalArgumentException
    {
        String firstname_1 = "'" + firstname + "'";
        String lastname_1 = "'" + lastname + "'";
        String email_1 = "'" + email + "'";
        String query = "insert into customer_info(customer_first_name, customer_last_name, customer_email) values ( "
                + firstname_1 + "," + lastname_1 + "," + email_1 + ")";
        try
        {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            ps.execute();
            
            ResultSet rs = ps.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next())
            {
                generatedKey = rs.getInt(1);
            }
            return insert_customer_login(email, password, generatedKey);
        }
        catch (SQLException e)
        {
            throw new IllegalArgumentException("Email is already taken email=".concat(email));
        }
    }
    
    private boolean insert_customer_login(String username, String password, Integer customer_id) throws IllegalArgumentException
    {
        username = "'" + username + "'";
        password = "'" + password + "'";
        String query = "insert into customer_login(cust_username, cust_password, customer_id) values( "
                + username + "," + password + "," + customer_id + ")";
        try
        {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.execute();
            return true;
        }
        catch (SQLException e)
        {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @Override
    public boolean insertFlightByAirline(Flight flight) throws IllegalArgumentException
    {
        return false;
    }
    
    @Override
    public boolean cancelReservation(Integer customerId, Integer reservationId)
    {
        return false;
    }
    
    @Override
    public boolean cancelFlight(Integer flightId)
    {
        return false;
    }
    
    @Override
    public boolean makeReservation(Integer flightIdPk, String username, String password) throws InvalidLoginException
    {
        return false;
    }
    
    @Override
    public boolean makeReservation(Integer flightIdPk, Integer customerId)
    {
        return false;
    }
    
    @Override
    public boolean makeReservationBySearchEngine(Integer flightIdPk, String username, String password) throws InvalidLoginException
    {
        return false;
    }
    
    @Override
    public boolean makeReservationBySearchEngine(Integer flightIdPk, Integer customerId)
    {
        return false;
    }
}
