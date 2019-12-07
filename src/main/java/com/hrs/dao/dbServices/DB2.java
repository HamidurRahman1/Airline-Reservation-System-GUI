package com.hrs.dao.dbServices;

import com.hrs.configs.Configuration;
import com.hrs.exceptions.IllegalArgumentException;
import com.hrs.exceptions.InvalidLoginException;
import com.hrs.resources.FieldValue;
import com.hrs.service.Services;

import com.hrs.view.models.Admin;
import com.hrs.view.models.Airline;
import com.hrs.view.models.Airplane;
import com.hrs.view.models.Airport;
import com.hrs.view.models.Customer;
import com.hrs.view.models.Destination;
import com.hrs.view.models.Flight;
import com.hrs.view.models.Login;
import com.hrs.view.models.Reservation;
import com.hrs.view.models.Source;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

public class DB2 implements Services
{
    private Connection connection = null;
    
    public DB2() throws ClassNotFoundException, SQLException
    {
        connection = Configuration.GET_GATEWAY().getConnection();
    }
    
    @Override
    public Set<Flight> getAllFlightsForReservation(String string) throws IllegalArgumentException
    {
        Set<Flight> flights = new LinkedHashSet <>();
        
        final String query = "select flightId, flightCode, dept_airportId, dept_airportName, dept_date, dept_time, \n"
                + "arri_airportId, arri_airportName, arri_date, arri_time, avlSeat, Status.status, Airlines.airlineId, " +
                " Airlines.airlineName,\n" + " Flights.airplaneId, Airplanes.airplaneName, fare, capacity\n"
                + " from Flights, Airlines, Airplanes, Status \n" + " where Airlines.airlineId = Flights.airlineId\n"
                + " and Airplanes.airplaneId = Flights.airplaneId\n" + " and Status.statusId = Flights.statusId\n"
                + " and Flights.dept_date >= ?";
        
        try
        {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, LocalDate.now().toString());
    
            ResultSet rs = ps.executeQuery();
    
            while (rs.next())
            {
                Source source = new Source(rs.getInt("dept_airportId"), rs.getString("dept_airportName"),
                        rs.getDate("dept_date").toLocalDate(), rs.getString("dept_time"));
                
                Destination destination = new Destination(rs.getInt("arri_airportId"), rs.getString("arri_airportName"),
                        rs.getDate("arri_date").toLocalDate(), rs.getString("arri_time"));
                
                Airplane airplane = new Airplane(rs.getInt("airplaneId"), rs.getString("airplaneName"));
                Airline airline = new Airline(rs.getInt("airlineId"), rs.getString("airlineName"));
                
                Flight flight = new Flight();
                flight.setFlightId(rs.getInt("flightId"));
                flight.setFlightCode(rs.getString("flightCode"));
                flight.setAvailableSeat(rs.getInt("avlSeat"));
                flight.setStatus(rs.getString("status"));
                flight.setFare(rs.getFloat("fare"));
                flight.setCapacity(rs.getInt("capacity"));
                flight.setSource(source);
                flight.setDestination(destination);
                flight.setAirLine(airline);
                flight.setAirplane(airplane);
                
                flights.add(flight);
            }
            
            rs.close();
            ps.close();
            
        }
        catch(SQLException ex)
        {
            throw new IllegalArgumentException(ex.getMessage());
        }
        
        return flights;
    }
    
    @Override
    public Set<Flight> getAllFlightsByAirlineForReservation(String airlineName) throws IllegalArgumentException
    {
        Set<Flight> flights = new LinkedHashSet <>();
    
        final String query = "select flightId, flightCode, dept_airportId, dept_airportName, dept_date, dept_time, "+
            "arri_airportId, arri_airportName, arri_date, arri_time, avlSeat, Status.status, Airlines.airlineId, "+
            "Airlines.airlineName, Flights.airplaneId, Airplanes.airplaneName, fare, capacity "+
        "from Flights, Airlines, Airplanes, Status where Airlines.airlineId = Flights.airlineId "+
        "and Airplanes.airplaneId = Flights.airplaneId and Status.statusId = Flights.statusId "+
        " and Flights.dept_date >= ? "+
        "and Flights.airlineId = (select airlineId from Airlines where airlineName = ? )";
        try
        {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, LocalDate.now().toString());
            ps.setString(2, airlineName);
        
            ResultSet rs = ps.executeQuery();
        
            while (rs.next())
            {
                Source source = new Source(rs.getInt("dept_airportId"), rs.getString("dept_airportName"),
                        rs.getDate("dept_date").toLocalDate(), rs.getString("dept_time"));
            
                Destination destination = new Destination(rs.getInt("arri_airportId"), rs.getString("arri_airportName"),
                        rs.getDate("arri_date").toLocalDate(), rs.getString("arri_time"));
            
                Airplane airplane = new Airplane(rs.getInt("airplaneId"), rs.getString("airplaneName"));
                Airline airline = new Airline(rs.getInt("airlineId"), rs.getString("airlineName"));
            
                Flight flight = new Flight();
                flight.setFlightId(rs.getInt("flightId"));
                flight.setFlightCode(rs.getString("flightCode"));
                flight.setAvailableSeat(rs.getInt("avlSeat"));
                flight.setStatus(rs.getString("status"));
                flight.setFare(rs.getFloat("fare"));
                flight.setCapacity(rs.getInt("capacity"));
                flight.setSource(source);
                flight.setDestination(destination);
                flight.setAirLine(airline);
                flight.setAirplane(airplane);
            
                flights.add(flight);
            }
            
            rs.close();
            ps.close();
        }
        catch(SQLException ex)
        {
            throw new IllegalArgumentException(ex.getMessage());
        }
    
        return flights;
    }
    
    @Override
    public Set<Flight> getAllFlightsByAirline(String airlineName, LocalDate localDate) throws IllegalArgumentException
    {
        Set<Flight> flights = new LinkedHashSet <>();
    
        final String query = "select flightId, flightCode, dept_airportId, dept_airportName, dept_date, dept_time, "+
                "arri_airportId, arri_airportName, arri_date, arri_time, avlSeat, Status.status, Airlines.airlineId, "+
                "Airlines.airlineName, Flights.airplaneId, Airplanes.airplaneName, fare, capacity "+
                "from Flights, Airlines, Airplanes, Status where Airlines.airlineId = Flights.airlineId "+
                "and Airplanes.airplaneId = Flights.airplaneId and Status.statusId = Flights.statusId "+
                " and Flights.dept_date >= ? "+
                " and Flights.airlineId = (select airlineId from Airlines where airlineName = ? )";
        try
        {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, LocalDate.now().toString());
            ps.setString(2, airlineName);
        
            ResultSet rs = ps.executeQuery();
        
            while (rs.next())
            {
                Source source = new Source(rs.getInt("dept_airportId"), rs.getString("dept_airportName"),
                        rs.getDate("dept_date").toLocalDate(), rs.getString("dept_time"));
            
                Destination destination = new Destination(rs.getInt("arri_airportId"), rs.getString("arri_airportName"),
                        rs.getDate("arri_date").toLocalDate(), rs.getString("arri_time"));
            
                Airplane airplane = new Airplane(rs.getInt("airplaneId"), rs.getString("airplaneName"));
                Airline airline = new Airline(rs.getInt("airlineId"), rs.getString("airlineName"));
            
                Flight flight = new Flight();
                flight.setFlightId(rs.getInt("flightId"));
                flight.setFlightCode(rs.getString("flightCode"));
                flight.setAvailableSeat(rs.getInt("avlSeat"));
                flight.setStatus(rs.getString("status"));
                flight.setFare(rs.getFloat("fare"));
                flight.setCapacity(rs.getInt("capacity"));
                flight.setSource(source);
                flight.setDestination(destination);
                flight.setAirLine(airline);
                flight.setAirplane(airplane);
            
                flights.add(flight);
            }
            
            rs.close();
            ps.close();
        }
        catch(SQLException ex)
        {
            throw new IllegalArgumentException(ex.getMessage());
        }
    
        return flights;
    }
    
    @Override
    public Set<Reservation> getAllReservationsByCustomerId(Integer customerId)
    {
        Set<Reservation> reservations = new LinkedHashSet <>();
    
        final String query = "select rsvpId, rsvpDate, rsvpStatus, rsvpBy, " +
            "Flights.flightId, flightCode, dept_airportId, dept_airportName, dept_date, dept_time, " +
            "arri_airportId, arri_airportName, arri_date, arri_time, avlSeat, Status.status, Airlines.airlineId, " +
            "Airlines.airlineName, Flights.airplaneId, Airplanes.airplaneName, fare, capacity " +
        "from Flights, Airlines, Airplanes, Status, Reservations " +
        "where Airlines.airlineId = Flights.airlineId " +
        "and Airplanes.airplaneId = Flights.airplaneId " +
        "and Status.statusId = Flights.statusId " +
        "and customerId = ? " +
        "and Flights.dept_date >= ? " +
        "and Reservations.flightId = Flights.flightId";
        
        try
        {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, customerId);
            ps.setString(2, LocalDate.now().toString());
        
            ResultSet rs = ps.executeQuery();
        
            while (rs.next())
            {
                Reservation reservation = new Reservation();
                
                reservation.setReservationId(rs.getInt("rsvpId"));
                reservation.setRsvpDate(rs.getDate("rsvpDate").toLocalDate());
                reservation.setRsvpBy(rs.getInt("rsvpBy"));
                
                String rsvpStatus = rs.getString("rsvpStatus");
                if(rsvpStatus.equalsIgnoreCase(FieldValue.ACTIVE))
                    reservation.setStatus(FieldValue.ACTIVE);
                else
                    reservation.setStatus(FieldValue.CANCELED);
                
                Source source = new Source(rs.getInt("dept_airportId"), rs.getString("dept_airportName"),
                        rs.getDate("dept_date").toLocalDate(), rs.getString("dept_time"));
            
                Destination destination = new Destination(rs.getInt("arri_airportId"), rs.getString("arri_airportName"),
                        rs.getDate("arri_date").toLocalDate(), rs.getString("arri_time"));
            
                Airplane airplane = new Airplane(rs.getInt("airplaneId"), rs.getString("airplaneName"));
                Airline airline = new Airline(rs.getInt("airlineId"), rs.getString("airlineName"));
            
                Flight flight = new Flight();
                flight.setFlightId(rs.getInt("flightId"));
                flight.setFlightCode(rs.getString("flightCode"));
                flight.setAvailableSeat(rs.getInt("avlSeat"));
                flight.setStatus(rs.getString("status"));
                flight.setFare(rs.getFloat("fare"));
                flight.setCapacity(rs.getInt("capacity"));
                flight.setSource(source);
                flight.setDestination(destination);
                flight.setAirLine(airline);
                flight.setAirplane(airplane);
                
                reservation.setFlight(flight);
                reservations.add(reservation);
            }
            
            rs.close();
            ps.close();
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
    
        return reservations;
    }
    
    @Override
    public Set<Reservation> getAllReservationsMadeUsingSearchEngineAndAirlineGui(String airlineName)
    {
        Set<Reservation> reservations = new LinkedHashSet <>();
        
        final String query = "select Flights.flightId, flightCode, dept_airportName, arri_airportName, " +
        " Flights.airplaneId, Airplanes.airplaneName, rsvpId, rsvpBy, rsvpDate, rsvpStatus " +
        " from Flights, Airlines, Airplanes, Reservations " +
        " where Airlines.airlineId = Flights.airlineId " +
        " and Airplanes.airplaneId = Flights.airplaneId " +
        " and Flights.dept_date >= ? " +
        " and Reservations.flightId = Flights.flightId " +
        " and ( Reservations.rsvpBy = 1 or Reservations.rsvpBy = 0 ) " +
        " and Flights.airlineId = (select airlineId from Airlines where airlineName = ? )  ";
    
        try
        {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, LocalDate.now().toString());
            ps.setString(2, airlineName);
        
            ResultSet rs = ps.executeQuery();
        
            while (rs.next())
            {
                Reservation reservation = new Reservation();
            
                reservation.setReservationId(rs.getInt("rsvpId"));
                reservation.setRsvpDate(rs.getDate("rsvpDate").toLocalDate());
                reservation.setRsvpBy(rs.getInt("rsvpBy"));
                String rsvpStatus = rs.getString("rsvpStatus");
                if(rsvpStatus.equalsIgnoreCase(FieldValue.ACTIVE))
                    reservation.setStatus(FieldValue.ACTIVE);
                else
                    reservation.setStatus(FieldValue.CANCELED);
            
                Source source = new Source();
                source.setAirportName(rs.getString("dept_airportName"));
            
                Destination destination = new Destination();
                destination.setAirportName(rs.getString("arri_airportName"));
            
                Airplane airplane = new Airplane(rs.getInt("airplaneId"), rs.getString("airplaneName"));
            
                Flight flight = new Flight();
                flight.setFlightId(rs.getInt("flightId"));
                flight.setFlightCode(rs.getString("flightCode"));
                flight.setSource(source);
                flight.setDestination(destination);
                flight.setAirplane(airplane);
            
                reservation.setFlight(flight);
                
                reservations.add(reservation);
            }
            
            rs.close();
            ps.close();
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
    
        return reservations;
    }
    
    @Override
    public Customer getCustomerByLogin(String username, String password) throws InvalidLoginException
    {
        Customer customer = new Customer();
        Login login = new Login();
        try
        {
            final String loginQuery = "select loginId, email, password from Logins where email = ? and password = ? ";
            
            PreparedStatement ps = connection.prepareStatement(loginQuery);
            
            ps.setString(1, username);
            ps.setString(2, password);
            
            ResultSet resultSet = ps.executeQuery();
    
            int rowCount = 0;
            if (resultSet.last())
            {
                rowCount = resultSet.getRow();
                resultSet.beforeFirst();
            }
            if (rowCount == 0)
            {
                throw new InvalidLoginException(FieldValue.NO_USER_FOUND
                        .concat(username).concat(" and password=").concat(password));
            }
    
            while(resultSet.next())
            {
                login.setLoginId(resultSet.getInt(1));
                login.setUsername(resultSet.getString(2));
                login.setPassword(resultSet.getString(3));
            }
    
            customer.setLogin(login);
    
            resultSet.close();
            ps.close();
        }
        catch(SQLException ex)
        {
            throw new InvalidLoginException(FieldValue.NO_USER_FOUND
                    .concat(username).concat(" and password=").concat(password));
        }
        
        try
        {
            final String customerQuery = "select customerId, firstName, lastName from Customers where loginId = ? ";
            
            PreparedStatement ps = connection.prepareStatement(customerQuery);
            ps.setInt(1, login.getLoginId());
            
            ResultSet resultSet = ps.executeQuery();
            
            while(resultSet.next())
            {
                customer.setCustomerId(resultSet.getInt(1));
                customer.setFirstName(resultSet.getString(2));
                customer.setLastName(resultSet.getString(3));
            }
            
            resultSet.close();
            ps.close();
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    
        try
        {
            Set<Flight> flights = new LinkedHashSet <>();
            
            final String previousFlightQuery = "select flightId, flightCode, dept_airportName, arri_airportName, " +
            " Airlines.airlineName, Airplanes.airplaneName, fare " +
            " from Flights, Airlines, Airplanes, Status where Airlines.airlineId = Flights.airlineId " +
            " and Airplanes.airplaneId = Flights.airplaneId " +
            " and Status.statusId = Flights.statusId " +
            " and Flights.dept_date >= ? " +
            " and flightId in (select flightId from Flights_Customers where customerId = ?) ";
        
            PreparedStatement ps = connection.prepareStatement(previousFlightQuery);
            ps.setString(1, LocalDate.now().toString());
            ps.setInt(2, customer.getCustomerId());
            
            ResultSet resultSet = ps.executeQuery();
        
            while(resultSet.next())
            {
                Flight flight = new Flight();
                Airline airline = new Airline(resultSet.getString("airlineName"));
                Airplane airplane = new Airplane(resultSet.getString("airplaneName"));
                Source source = new Source(resultSet.getString("dept_airportName"));
                Destination destination = new Destination(resultSet.getString("arri_airportName"));
                
                flight.setFlightId(resultSet.getInt("flightId"));
                flight.setFlightCode(resultSet.getString("flightCode"));
                flight.setFare(resultSet.getFloat("fare"));
                
                flight.setAirLine(airline);
                flight.setAirplane(airplane);
                flight.setSource(source);
                flight.setDestination(destination);
                
                flights.add(flight);
            }
            
            customer.setFlights(flights);
            
            resultSet.close();
            ps.close();
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        
        customer.setReservations(getAllReservationsByCustomerId(customer.getCustomerId()));
        
        return customer;
    }
    
    @Override
    public Admin getGlobalAdminByLogin(String username, String password) throws InvalidLoginException
    {
        Admin admin = new Admin();
        Login login = new Login();
        try
        {
            final String adminLoginQuery = "select loginId, email, password from Logins where email = ? and password = ? ";
            PreparedStatement ps = connection.prepareStatement(adminLoginQuery);
    
            ps.setString(1, username);
            ps.setString(2, password);
    
            ResultSet resultSet = ps.executeQuery();
    
            int rowCount = 0;
            if (resultSet.last())
            {
                rowCount = resultSet.getRow();
                resultSet.beforeFirst();
            }
            if (rowCount == 0)
            {
                throw new InvalidLoginException("No Global Admin found with given username="
                        .concat(username).concat(" and password=").concat(password));
            }
    
            while(resultSet.next())
            {
                login.setLoginId(resultSet.getInt(1));
                login.setUsername(resultSet.getString(2));
                login.setPassword(resultSet.getString(3));
            }
    
            admin.setLogin(login);
    
            resultSet.close();
            ps.close();
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    
        try
        {
            final String adminQuery = "select adminId, firstName, lastName from Admins where loginId = ? ";
        
            PreparedStatement ps = connection.prepareStatement(adminQuery);
            ps.setInt(1, login.getLoginId());
        
            ResultSet resultSet = ps.executeQuery();
        
            while(resultSet.next())
            {
                admin.setAdminId(resultSet.getInt(1));
                admin.setFirstName(resultSet.getString(2));
                admin.setLastName(resultSet.getString(3));
            }
        
            resultSet.close();
            ps.close();
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        
        return admin;
    }
    
    @Override
    public Admin getAirlineAdminByLogin(String airline, String username, String password) throws InvalidLoginException
    {
        Admin admin = new Admin();
        Login login = new Login();
        try
        {
            final String adminLoginQuery = "select loginId, email, password from Logins where email = ? and password = ? ";
            PreparedStatement ps = connection.prepareStatement(adminLoginQuery);
        
            ps.setString(1, username);
            ps.setString(2, password);
        
            ResultSet resultSet = ps.executeQuery();
    
            int rowCount = 0;
            if (resultSet.last())
            {
                rowCount = resultSet.getRow();
                resultSet.beforeFirst();
            }
            if (rowCount == 0)
            {
                throw new InvalidLoginException("No Airline Admin found with given username="
                        .concat(username).concat(" and password=").concat(password));
            }
        
            while(resultSet.next())
            {
                login.setLoginId(resultSet.getInt(1));
                login.setUsername(resultSet.getString(2));
                login.setPassword(resultSet.getString(3));
            }
        
            admin.setLogin(login);
        
            resultSet.close();
            ps.close();
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    
        try
        {
            final String adminQuery = "select adminId, firstName, lastName from Admins where loginId = ? \n"
                    + "and airlineId in ( select airlineId from Airlines where airlineName = ? )\n";
        
            PreparedStatement ps = connection.prepareStatement(adminQuery);
            ps.setInt(1, login.getLoginId());
            ps.setString(2, airline);
        
            ResultSet resultSet = ps.executeQuery();
        
            while(resultSet.next())
            {
                admin.setAdminId(resultSet.getInt(1));
                admin.setFirstName(resultSet.getString(2));
                admin.setLastName(resultSet.getString(3));
            }
        
            resultSet.close();
            ps.close();
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    
        return admin;
    }
    
    @Override
    public Set<Reservation> getGlobalReservationsMadeUsingSearchEngine()
    {
        Set<Reservation> reservations = new LinkedHashSet <>();
        
        final String query = "select Flights.flightId, flightCode, dept_airportName, arri_airportName, Airlines.airlineId, "+
            " Airlines.airlineName, Flights.airplaneId, Airplanes.airplaneName, fare, rsvpId, rsvpDate "+
        " from Flights, Airlines, Airplanes, Reservations "+
        " where Airlines.airlineId = Flights.airlineId "+
        " and Airplanes.airplaneId = Flights.airplaneId "+
        " and Flights.dept_date >= ? "+
        " and Reservations.flightId = Flights.flightId "+
        " and Reservations.rsvpBy = 0 ";
        
        try
        {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, LocalDate.now().toString());
        
            ResultSet rs = ps.executeQuery();
        
            while (rs.next())
            {
                Reservation reservation = new Reservation();
            
                reservation.setReservationId(rs.getInt("rsvpId"));
                reservation.setRsvpDate(rs.getDate("rsvpDate").toLocalDate());
            
                Source source = new Source();
                source.setAirportName(rs.getString("dept_airportName"));
            
                Destination destination = new Destination();
                destination.setAirportName(rs.getString("arri_airportName"));
            
                Airplane airplane = new Airplane(rs.getInt("airplaneId"), rs.getString("airplaneName"));
                Airline airline = new Airline(rs.getInt("airlineId"), rs.getString("airlineName"));
            
                Flight flight = new Flight();
                flight.setFlightId(rs.getInt("flightId"));
                flight.setFlightCode(rs.getString("flightCode"));
                flight.setFare(rs.getFloat("fare"));
                flight.setSource(source);
                flight.setDestination(destination);
                flight.setAirLine(airline);
                flight.setAirplane(airplane);
            
                reservation.setFlight(flight);
                
                reservations.add(reservation);
            }
            
            rs.close();
            ps.close();
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
    
        return reservations;
    }
    
    @Override
    public Set<Flight> getAllFlightsByAirport(String airportName)
    {
        Set<Flight> flights = new LinkedHashSet <>();
        
        final String query = "select Flights.flightId, flightCode, Airlines.airlineId, Airlines.airlineName, " +
        " Flights.airplaneId, Airplanes.airplaneName, dept_airportName, arri_airportName, Status.status, dept_date, arri_date " +
        " from Flights, Status, Airlines, Airplanes, Airports " +
        " where Flights.dept_date >= ? " +
        " and Status.statusId = Flights.statusId " +
        " and Airplanes.airplaneId = Flights.airplaneId " +
        " and Airlines.airlineId = Flights.airlineId " +
        " and dept_airportId = Airports.airportId " +
        " and ( dept_airportId in ( select airportId from Airports where airportName = ? ) or " +
        " arri_airportId in ( select airportId from Airports where airportName = ? )) ";
        
        try
        {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, LocalDate.now().toString());
            ps.setString(2, airportName);
            ps.setString(3, airportName);
    
            ResultSet rs = ps.executeQuery();
    
            while (rs.next())
            {
                Source source = new Source();
                source.setAirportName(rs.getString("dept_airportName"));
                source.setDate(rs.getDate("dept_date").toLocalDate());
        
                Destination destination = new Destination();
                destination.setAirportName(rs.getString("arri_airportName"));
                destination.setDate(rs.getDate("arri_date").toLocalDate());
        
                Airplane airplane = new Airplane(rs.getInt("airplaneId"), rs.getString("airplaneName"));
                Airline airline = new Airline(rs.getInt("airlineId"), rs.getString("airlineName"));
        
                Flight flight = new Flight();
                flight.setFlightId(rs.getInt("flightId"));
                flight.setFlightCode(rs.getString("flightCode"));
                flight.setStatus(rs.getString("status"));
                flight.setSource(source);
                flight.setDestination(destination);
                flight.setAirLine(airline);
                flight.setAirplane(airplane);
                
                flights.add(flight);
            }
            
            rs.close();
            ps.close();
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        
        return flights;
    }
    
    @Override
    public Set<Airplane> getAllAirPlaneByAirLine(String airlineName) throws IllegalArgumentException
    {
        Set<Airplane> airplanes = new LinkedHashSet <>();
        
        String query = "select airplaneId, airplaneName from Airplanes "+
                "where airlineId in (select airlineId from Airlines where airlineName = ? )";
            
        try
        {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, airlineName);
            
            ResultSet rs = ps.executeQuery();
    
            int rowCount = 0;
            if (rs.last())
            {
                rowCount = rs.getRow();
                rs.beforeFirst();
            }
            if (rowCount == 0)
            {
                throw new IllegalArgumentException("Given airline name is invalid. name=".concat(airlineName));
            }
            
            while (rs.next())
            {
                Airplane airplane = new Airplane
                        (Integer.parseInt(rs.getString("airplaneId")), rs.getString("airplaneName"));
                airplanes.add(airplane);
            }
            
            rs.close();
            ps.close();
        
        }
        catch (SQLException e)
        {
            throw new IllegalArgumentException(e.getMessage());
        }
        return airplanes;
    }
    
    @Override
    public Set<Airport> getAllAirports()
    {
        Set<Airport> airports = new LinkedHashSet <>();
    
        String query = "select airportId, airportName from Airports";
    
        try
        {
            PreparedStatement ps = this.connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
        
            while (rs.next())
            {
                Airport airplane = new Airport(Integer.parseInt(rs.getString("airportId")), rs.getString("airportName"));
                airports.add(airplane);
            }
            
            rs.close();
            ps.close();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        
        return airports;
    }
    
    @Override
    public boolean insertNewCustomer(String firstName, String lastName, String email, String password) throws IllegalArgumentException
    {
        Login login = new Login(email, password);
        
        final String query = " insert into Customers ( firstName, lastName, loginId ) values ( ? , ? , ? ) ";
        
        try
        {
            int loginId = insertLogin(login);
            
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setInt(3, loginId);
            
            ps.executeUpdate();
            
            ps.close();
        }
        catch(SQLException ex)
        {
            throw new IllegalArgumentException(ex.getMessage());
        }
        catch(Exception ex)
        {
            throw ex;
        }
        
        return true;
    }
    
    private int insertLogin(Login login) throws IllegalArgumentException
    {
        try
        {
            final String query = "insert into Logins ( email, password ) values ( ? , ? ) ";
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            ps.setString(1, login.getUsername());
            ps.setString(2, login.getPassword());
            
            ps.executeUpdate();
    
            try (ResultSet generatedKeys = ps.getGeneratedKeys())
            {
                if (generatedKeys.next())
                {
                    return generatedKeys.getInt(1);
                }
            }
            
            ps.close();
        }
        catch(SQLException ex)
        {
            throw new IllegalArgumentException(ex.getMessage());
        }
        catch(Exception ex)
        {
            throw ex;
        }
        return Integer.MIN_VALUE;
    }
    
    @Override
    public boolean insertFlightByAirline(Flight flight) throws IllegalArgumentException
    {
        try
        {
            final String insertFlightQuery = "insert into Flights ( flightCode, airlineId, airplaneId, statusId, " +
                    "fare, capacity, avlSeat, dept_airportId, dept_airportName, " +
                "dept_date, dept_time, arri_airportId, arri_airportName, arri_Date, arri_time ) values " +
                    " ( ? , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            PreparedStatement ps = connection.prepareStatement(insertFlightQuery);
            
            ps.setString(1, flight.getFlightCode());
            ps.setInt(2, flight.getAirLine().getAirlineId());
            ps.setInt(3, flight.getAirplane().getAirPlaneId());
            ps.setInt(4, 1);
            ps.setFloat(5, flight.getFare());
            ps.setInt(6, flight.getCapacity());
            ps.setInt(7, flight.getCapacity());
            
            ps.setInt(8, flight.getSource().getAirportId());
            ps.setString(9, flight.getSource().getAirportName());
            ps.setString(10, flight.getSource().getDate().toString());
            ps.setString(11, flight.getSource().getTime());
            
            ps.setInt(12, flight.getDestination().getAirportId());
            ps.setString(13, flight.getDestination().getAirportName());
            ps.setString(14, flight.getDestination().getDate().toString());
            ps.setString(15, flight.getDestination().getTime());
            
            ps.executeUpdate();
            
            ps.close();
        }
        catch(SQLException ex)
        {
            throw new IllegalArgumentException(ex.getMessage());
        }
        return true;
    }
    
    @Override
    public boolean cancelReservation(Integer customerId, Integer reservationId)
    {
        try
        {
            final String query = "select flightId from Reservations where rsvpId = ? and customerId = ? ";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, reservationId);
            ps.setInt(2, customerId);
            ResultSet rs = ps.executeQuery();
            int flightId = Integer.MIN_VALUE;
            while(rs.next())
            {
                flightId = rs.getInt(1);
            }
            rs.close();
            ps.close();
    
            final String update = "update Reservations set rsvpStatus = ? " +
                    "where rsvpId = ? and customerId = ? and flightId = ?";
            PreparedStatement ps1 = connection.prepareStatement(update);
            ps1.setString(1, FieldValue.CANCELED);
            ps1.setInt(2, reservationId);
            ps1.setInt(3, customerId);
            ps1.setInt(4, flightId);
            ps1.executeUpdate();
            ps1.close();
    
    
            final String delete = "delete from Flights_Customers where customerId = ? and flightId = ?";
            PreparedStatement ps2 = connection.prepareStatement(delete);
            ps2.setInt(1, customerId);
            ps2.setInt(2, flightId);
            ps2.executeUpdate();
            ps2.close();
    
            final String flightSeatGet = "select avlSeat from Flights where flightId = ? ";
            PreparedStatement ps3 = connection.prepareStatement(flightSeatGet);
            ps3.setInt(1, flightId);
            ResultSet resultSet = ps3.executeQuery();
            int seats = Integer.MIN_VALUE;
            while(resultSet.next())
            {
                seats = resultSet.getInt(1);
            }
            seats = seats + 1;
            resultSet.close();
            ps3.close();
            
            final String flightSeatUpdate = "update Flights set avlSeat = ? where flightId = ? ";
            PreparedStatement ps4 = connection.prepareStatement(flightSeatUpdate);
            ps4.setInt(1, seats);
            ps4.setInt(2, flightId);
            ps4.executeUpdate();
            ps4.close();
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return true;
    }
    
    @Override
    public boolean cancelFlight(Integer flightId)
    {
        try
        {
            final String update = "update Flights set statusId = ? where flightId = ? ";
            PreparedStatement p1 = connection.prepareStatement(update);
            p1.setInt(1, 2);
            p1.setInt(2, flightId);
            p1.executeUpdate();
            p1.close();
            
            final String update2 = "update Reservations set rsvpStatus = ? where flightId = ? ";
            PreparedStatement p2 = connection.prepareStatement(update2);
            p2.setString(1, FieldValue.CANCELED);
            p2.setInt(2, flightId);
            p2.executeUpdate();
            p2.close();
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return true;
    }
    
    @Override
    public boolean makeReservation(Integer flightIdPk, String username, String password) throws InvalidLoginException
    {
        Customer customer = new Customer();
        Login login = new Login();
    
        try
        {
            final String loginQuery = "select loginId, email, password from Logins where email = ? and password = ? ";
        
            PreparedStatement ps = connection.prepareStatement(loginQuery);
        
            ps.setString(1, username);
            ps.setString(2, password);
        
            ResultSet resultSet = ps.executeQuery();
    
            int rowCount = 0;
            if (resultSet.last())
            {
                rowCount = resultSet.getRow();
                resultSet.beforeFirst();
            }
            if (rowCount == 0)
            {
                throw new InvalidLoginException(FieldValue.NO_USER_FOUND
                        .concat(username).concat(" and password=").concat(password));
            }
        
            while(resultSet.next())
            {
                login.setLoginId(resultSet.getInt(1));
                login.setUsername(resultSet.getString(2));
                login.setPassword(resultSet.getString(3));
            }
        
            customer.setLogin(login);
        
            resultSet.close();
            ps.close();
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
    
        try
        {
            final String customerQuery = "select customerId from Customers where loginId = ? ";
        
            PreparedStatement ps = connection.prepareStatement(customerQuery);
            ps.setInt(1, login.getLoginId());
        
            ResultSet resultSet = ps.executeQuery();
        
            while(resultSet.next())
            {
                customer.setCustomerId(resultSet.getInt(1));
            }
        
            resultSet.close();
            ps.close();
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
    
        try
        {
            final String flightSeatGet = "select avlSeat from Flights where flightId = ? ";
            PreparedStatement ps = connection.prepareStatement(flightSeatGet);
            ps.setInt(1, flightIdPk);
            ResultSet resultSet = ps.executeQuery();
            int seats = Integer.MIN_VALUE;
            while(resultSet.next())
            {
                seats = resultSet.getInt(1);
            }
            seats = seats - 1;
            resultSet.close();
            ps.close();
        
            final String flightSeatUpdate = "update Flights set avlSeat = ? where flightId = ? ";
            PreparedStatement ps1 = connection.prepareStatement(flightSeatUpdate);
            ps1.setInt(1, seats);
            ps1.setInt(2, flightIdPk);
            ps1.executeUpdate();
            ps1.close();
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
    
        try
        {
            final String reservationQuery = "insert into Reservations ( customerId, flightId, rsvpDate, rsvpStatus, rsvpBy ) " +
                    "values ( ? , ? , ? , ? , ? )";
        
            PreparedStatement ps = connection.prepareStatement(reservationQuery);
            ps.setInt(1, customer.getCustomerId());
            ps.setInt(2, flightIdPk);
            ps.setString(3, LocalDate.now().toString());
            ps.setString(4, FieldValue.ACTIVE);
            ps.setInt(5, 1);
        
            ps.executeUpdate();
        
            ps.close();
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
    
        try
        {
            final String flightCustomer = "insert into Flights_Customers ( flightId, customerId ) "+
                    " values ( ? , ? )";
        
            PreparedStatement ps = connection.prepareStatement(flightCustomer);
            ps.setInt(1, flightIdPk);
            ps.setInt(2, customer.getCustomerId());
        
            ps.executeUpdate();
            ps.close();
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return true;
    }
    
    @Override
    public boolean makeReservation(Integer flightIdPk, Integer customerId)
    {
        try
        {
            final String flightSeatGet = "select avlSeat from Flights where flightId = ? ";
        
            PreparedStatement ps = connection.prepareStatement(flightSeatGet);
            ps.setInt(1, flightIdPk);
            ResultSet resultSet = ps.executeQuery();
            int seats = Integer.MIN_VALUE;
            while(resultSet.next())
            {
                seats = resultSet.getInt(1);
            }
            seats = seats - 1;
            resultSet.close();
            ps.close();
        
            final String flightSeatUpdate = "update Flights set avlSeat = ? where flightId = ? ";
            PreparedStatement ps1 = connection.prepareStatement(flightSeatUpdate);
            ps1.setInt(1, seats);
            ps1.setInt(2, flightIdPk);
            ps1.executeUpdate();
            ps1.close();
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
    
        try
        {
            final String reservationQuery = "insert into Reservations ( customerId, flightId, rsvpDate, rsvpStatus, rsvpBy ) " +
                    "values ( ? , ? , ? , ? , ? )";
        
            PreparedStatement ps = connection.prepareStatement(reservationQuery);
            ps.setInt(1, customerId);
            ps.setInt(2, flightIdPk);
            ps.setString(3, LocalDate.now().toString());
            ps.setString(4, FieldValue.ACTIVE);
            ps.setInt(5, 1);
            ps.executeUpdate();
            ps.close();
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
    
        try
        {
            final String flightCustomer = "insert into Flights_Customers ( flightId, customerId ) "+
                    " values ( ? , ? )";
        
            PreparedStatement ps = connection.prepareStatement(flightCustomer);
            ps.setInt(1, flightIdPk);
            ps.setInt(2, customerId);
            ps.executeUpdate();
            ps.close();
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return true;
    }
    
    @Override
    public boolean makeReservationBySearchEngine(Integer flightIdPk, String username, String password) throws InvalidLoginException
    {
        Customer customer = new Customer();
        Login login = new Login();
        
        try
        {
            final String loginQuery = "select loginId, email, password from Logins where email = ? and password = ? ";
            
            PreparedStatement ps = connection.prepareStatement(loginQuery);
            
            ps.setString(1, username);
            ps.setString(2, password);
            
            ResultSet resultSet = ps.executeQuery();
            
            int rowCount = 0;
            if (resultSet.last())
            {
                rowCount = resultSet.getRow();
                resultSet.beforeFirst();
            }
            if (rowCount == 0)
            {
                throw new InvalidLoginException(FieldValue.NO_USER_FOUND
                        .concat(username).concat(" and password=").concat(password));
            }
        
            while(resultSet.next())
            {
                login.setLoginId(resultSet.getInt(1));
                login.setUsername(resultSet.getString(2));
                login.setPassword(resultSet.getString(3));
            }
        
            customer.setLogin(login);
        
            resultSet.close();
            ps.close();
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
    
        try
        {
            final String customerQuery = "select customerId from Customers where loginId = ? ";
        
            PreparedStatement ps = connection.prepareStatement(customerQuery);
            ps.setInt(1, login.getLoginId());
        
            ResultSet resultSet = ps.executeQuery();
        
            while(resultSet.next())
            {
                customer.setCustomerId(resultSet.getInt(1));
            }
        
            resultSet.close();
            ps.close();
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
    
        try
        {
            final String flightSeatGet = "select avlSeat from Flights where flightId = ? ";
        
            PreparedStatement ps = connection.prepareStatement(flightSeatGet);
            ps.setInt(1, flightIdPk);
        
            ResultSet resultSet = ps.executeQuery();
        
            int seats = Integer.MIN_VALUE;
        
            while(resultSet.next())
            {
                seats = resultSet.getInt(1);
            }
        
            seats = seats - 1;
        
            resultSet.close();
            ps.close();
        
            final String flightSeatUpdate = "update Flights set avlSeat = ? where flightId = ? ";
        
            PreparedStatement ps1 = connection.prepareStatement(flightSeatUpdate);
            ps1.setInt(1, seats);
            ps1.setInt(2, flightIdPk);
        
            ps1.executeUpdate();
        
            ps1.close();
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
    
        try
        {
            final String reservationQuery = "insert into Reservations ( customerId, flightId, rsvpDate, rsvpStatus, rsvpBy ) " +
                    "values ( ? , ? , ? , ? , ? )";
        
            PreparedStatement ps = connection.prepareStatement(reservationQuery);
            ps.setInt(1, customer.getCustomerId());
            ps.setInt(2, flightIdPk);
            ps.setString(3, LocalDate.now().toString());
            ps.setString(4, FieldValue.ACTIVE);
            ps.setInt(5, 0);
            
            ps.executeUpdate();
            
            ps.close();
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
    
        try
        {
            final String flightCustomer = "insert into Flights_Customers ( flightId, customerId ) "+
                    " values ( ? , ? )";
        
            PreparedStatement ps = connection.prepareStatement(flightCustomer);
            ps.setInt(1, flightIdPk);
            ps.setInt(2, customer.getCustomerId());
        
            ps.executeUpdate();
            ps.close();
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        
        return true;
    }
    
    @Override
    public boolean makeReservationBySearchEngine(Integer flightIdPk, Integer customerId)
    {
        try
        {
            final String flightSeatGet = "select avlSeat from Flights where flightId = ? ";
        
            PreparedStatement ps = connection.prepareStatement(flightSeatGet);
            ps.setInt(1, flightIdPk);
        
            ResultSet resultSet = ps.executeQuery();
        
            int seats = Integer.MIN_VALUE;
        
            while(resultSet.next())
            {
                seats = resultSet.getInt(1);
            }
        
            seats = seats - 1;
        
            resultSet.close();
            ps.close();
        
            final String flightSeatUpdate = "update Flights set avlSeat = ? where flightId = ? ";
        
            PreparedStatement ps1 = connection.prepareStatement(flightSeatUpdate);
            ps1.setInt(1, seats);
            ps1.setInt(2, flightIdPk);
        
            ps1.executeUpdate();
        
            ps1.close();
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        
        try
        {
            final String reservationQuery = "insert into Reservations ( customerId, flightId, rsvpDate, rsvpStatus, rsvpBy ) " +
                    "values ( ? , ? , ? , ? , ? )";
        
            PreparedStatement ps = connection.prepareStatement(reservationQuery);
            ps.setInt(1, customerId);
            ps.setInt(2, flightIdPk);
            ps.setString(3, LocalDate.now().toString());
            ps.setString(4, FieldValue.ACTIVE);
            ps.setInt(5, 0);
        
            ps.executeUpdate();
        
            ps.close();
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
    
        try
        {
            final String flightCustomer = "insert into Flights_Customers ( flightId, customerId ) "+
                    " values ( ? , ? )";
        
            PreparedStatement ps = connection.prepareStatement(flightCustomer);
            ps.setInt(1, flightIdPk);
            ps.setInt(2, customerId);
        
            ps.executeUpdate();
            ps.close();
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        
        return true;
    }
    
    @Override
    public Airline getAirlineByName(String airlineName)
    {
        Airline airline = new Airline();
    
        final String query = "select airlineId, airlineName from Airlines where airlineName = ? ";
        try
        {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, airlineName);
            ResultSet rs = ps.executeQuery();
        
            while(rs.next())
            {
                airline.setAirlineId(rs.getInt(1));
                airline.setAirlineName(rs.getString(2));
            }
            
            rs.close();
            ps.close();
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return airline;
    }
}
