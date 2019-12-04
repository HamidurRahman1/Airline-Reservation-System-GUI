package com.hrs.dao.dbServices;

import com.hrs.configs.Configuration;

import com.hrs.exceptions.IllegalArgumentException;

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

//public class DatabaseService implements Services
//{
//    private Connection connection = null;
//
//    public DatabaseService() throws ClassNotFoundException, SQLException
//    {
//        connection = Configuration.GET_GATEWAY().getConnection();
//    }
//
//    @Override
//    public Set<Flight> getAllFlightsByAirline(String airlineName, LocalDate localDate)
//    {
//        System.out.println(airlineName+localDate);
//
//        airlineName = "'" + airlineName + "'";
//        String dt = "'" + localDate.toString() + "'";
//
//        Set<Flight> flights = new LinkedHashSet<>();
//
//        String query = "select flight_info.flight_info_id, airline_flight_info.airline_flight_id, airline_info.airline_id, " +
//                "source_name, destination_name, flight_status_info," +
//                "flight_source_date, flight_dest_date, flight_max_capacity, flight_current_capacity, fare, airline_name, " +
//                "airline_flight_name, flight_fly_time, flight_land_time\n" +
//                "from flight_info, airline_info, airline_flight_info, flight_status\n" +
//                "where flight_info.airline_flight_id = airline_flight_info.airline_flight_id and\n" +
//                "airline_flight_info.airline_id = airline_info.airline_id and\n" +
//                "flight_status.airline_flight_id = airline_flight_info.airline_flight_id and\n" +
//                "airline_info.airline_name = " + airlineName + " and\n" +
//                "flight_source_date > " + dt;
//        try
//        {
//            Statement statement = this.connection.createStatement();
//            ResultSet rs = statement.executeQuery(query);
//
//            int rowcount = 0;
//            if (rs.last()) {
//                rowcount = rs.getRow();
//                rs.beforeFirst();
//            }
//            if (rowcount == 0)
//            {
//                throw new java.lang.IllegalArgumentException("Airline Name Not Found");
//            }
//
//            while (rs.next())
//            {
//                Integer flightID = Integer.parseInt(rs.getString("flight_info.flight_info_id"));
//                String flightCode = Integer.toString(rs.getString("airline_flight_name").hashCode());
//                LocalDate sourceDate = LocalDate.parse(rs.getString("flight_source_date"));
//                Source source = new Source(rs.getString("source_name"), sourceDate, rs.getString("flight_fly_time"));
//                LocalDate destinationDate = LocalDate.parse(rs.getString("flight_dest_date"));
//                Destination destination = new Destination(rs.getString("destination_name"), destinationDate, rs.getString("flight_land_time"));
//                Integer capacity = Integer.parseInt(rs.getString("flight_max_capacity"));
//                Integer availableSeat = capacity - Integer.parseInt(rs.getString("flight_current_capacity"));
//                Float fare = Float.parseFloat(rs.getString("fare"));
//                String status = rs.getString("flight_status_info");
//                Airline airLine = new Airline(Integer.parseInt(rs.getString("airline_flight_info.airline_flight_id")), rs.getString("airline_name"));
//                Airplane airplane = new Airplane(Integer.parseInt(rs.getString("airline_info.airline_id")), rs.getString("airline_flight_name"));
//
//                Flight flight = new Flight(flightID, flightCode, source, destination, availableSeat, status, airLine, airplane, fare);
//                flights.add(flight);
//            }
//        }
//        catch (SQLException e) {}
//
//        return flights;
//    }
//
//    @Override
//    public Set<Flight> getAllFlightsForReservation(String str)
//    {
//        Set<Flight> flights = new LinkedHashSet<>();
//        String current = "'" + Configuration.GET_CURRENT_DATE().toString() + "'";
//
//        String query = "select flight_info.flight_info_id, airline_flight_info.airline_flight_id,airline_info.airline_id, \n" +
//                "source_name, destination_name, flight_status_info,flight_source_date, \n" +
//                "flight_dest_date,flight_max_capacity,flight_current_capacity,fare,airline_name, \n" +
//                "airline_flight_name, flight_fly_time, flight_land_time\n" +
//                "from flight_info, airline_info, airline_flight_info, flight_status\n" +
//                "where flight_info.airline_flight_id = airline_flight_info.airline_flight_id and\n" +
//                "airline_flight_info.airline_id = airline_info.airline_id and\n" +
//                "flight_status.airline_flight_id = airline_flight_info.airline_flight_id and\n" +
//                "flight_max_capacity > flight_current_capacity and\n" +
//                "flight_status_info = 'On Time' and\n" +
//                "flight_source_date > " + current;
//
//        try
//        {
//            Statement statement = this.connection.createStatement();
//            ResultSet rs = statement.executeQuery(query);
//
//            int rowcount = 0;
//            if (rs.last())
//            {
//                rowcount = rs.getRow();
//                rs.beforeFirst();
//            }
//            if (rowcount == 0)
//            {
//                throw new java.lang.IllegalArgumentException("No Result Found");
//            }
//
//            while (rs.next())
//            {
//                Integer flightID = Integer.parseInt(rs.getString("flight_info.flight_info_id"));
//                String flightCode = Integer.toString(rs.getString("airline_flight_name").hashCode());
//                LocalDate sourceDate = LocalDate.parse(rs.getString("flight_source_date"));
//                Source source = new Source(rs.getString("source_name"), sourceDate, rs.getString("flight_fly_time"));
//                LocalDate destinationDate = LocalDate.parse(rs.getString("flight_dest_date"));
//                Destination destination = new Destination(rs.getString("destination_name"), destinationDate, rs.getString("flight_land_time"));
//                Integer capacity = Integer.parseInt(rs.getString("flight_max_capacity"));
//                Integer availableSeat = capacity - Integer.parseInt(rs.getString("flight_current_capacity"));
//                Float fare = Float.parseFloat(rs.getString("fare"));
//                String status = rs.getString("flight_status_info");
//                Airline airLine = new Airline(Integer.parseInt(rs.getString("airline_flight_info.airline_flight_id")), rs.getString("airline_name"));
//                Airplane airplane = new Airplane(Integer.parseInt(rs.getString("airline_info.airline_id")), rs.getString("airline_flight_name"));
//
//                Flight flight = new Flight(flightID, flightCode, source, destination, availableSeat, status, airLine, airplane, fare);
//                flights.add(flight);
//            }
//        }
//        catch (SQLException e) {}
//
//        return flights;
//    }
//
//    @Override
//    public Set<Reservation> getAllReservationsByCustomerId(Integer customerId) {
//
//        Set<Reservation> reservations = new LinkedHashSet<>();
//
//        String query = "select customer_info.customer_id,customer_info.customer_first_name,customer_info.customer_last_name, reservation_info.reservation_id, flight_info.flight_info_id, airline_flight_info.airline_flight_id,airline_info.airline_id,\n" +
//                "source_name, destination_name, flight_status_info,flight_source_date, \n" +
//                "flight_dest_date,flight_max_capacity,flight_current_capacity,fare,airline_name,\n" +
//                "airline_flight_name, flight_fly_time, flight_land_time, res_status, reservation_by, reservation_date\n" +
//                "from flight_info, airline_info, airline_flight_info, flight_status, customer_info, reservation_status, reservation_info\n" +
//                "where flight_info.airline_flight_id = airline_flight_info.airline_flight_id and\n" +
//                "airline_flight_info.airline_id = airline_info.airline_id and\n" +
//                "flight_status.airline_flight_id = airline_flight_info.airline_flight_id and\n" +
//                "customer_info.customer_id = " + customerId + " and\n " +
//                "customer_info.customer_id = reservation_info.customer_id and\n" +
//                "reservation_info.reservation_id = reservation_status.reservation_id and\n" +
//                "reservation_info.reservation_id = flight_info.reservation_id";
//        try
//        {
//            Statement statement = this.connection.createStatement();
//            ResultSet rs = statement.executeQuery(query);
//
//            while (rs.next()) {
//                Integer reservationID = Integer.parseInt(rs.getString("reservation_info.reservation_id"));
//                Integer customerID = Integer.parseInt(rs.getString("customer_info.customer_id"));
//                String customerFname = rs.getString("customer_info.customer_first_name");
//                String customerLname = rs.getString("customer_info.customer_last_name");
//                Integer flightID = Integer.parseInt(rs.getString("flight_info.flight_info_id"));
//                String flightCode = Integer.toString(rs.getString("airline_flight_name").hashCode());
//                LocalDate sourceDate = LocalDate.parse(rs.getString("flight_source_date"));
//                Source source = new Source(rs.getString("source_name"), sourceDate, rs.getString("flight_fly_time"));
//                LocalDate destinationDate = LocalDate.parse(rs.getString("flight_dest_date"));
//                Destination destination = new Destination(rs.getString("destination_name"), destinationDate, rs.getString("flight_land_time"));
//                Integer capacity = Integer.parseInt(rs.getString("flight_max_capacity"));
//                Integer availableSeat = capacity - Integer.parseInt(rs.getString("flight_current_capacity"));
//                Float fare = Float.parseFloat(rs.getString("fare"));
//                String status = rs.getString("flight_status_info");
//                Airline airLine = new Airline(Integer.parseInt(rs.getString("airline_flight_info.airline_flight_id")), rs.getString("airline_name"));
//                Airplane airplane = new Airplane(Integer.parseInt(rs.getString("airline_info.airline_id")), rs.getString("airline_flight_name"));
//                Flight flight = new Flight(flightID, flightCode, source, destination, availableSeat, status, airLine, airplane, fare);
//
//                Reservation reservation = new Reservation(reservationID, null, flight, LocalDate.parse(rs.getString("reservation_date")), rs.getString("res_status"), Integer.parseInt(rs.getString("reservation_by")));
//                reservations.add(reservation);
//            }
//
//            statement.close();
//            rs.close();
//        }
//        catch (SQLException e) {}
//
//        return reservations;
//    }
//
//    @Override
//    public Customer getCustomerByLogin(String username, String password) {
//
//        Set<Reservation> reservations = new LinkedHashSet<>();
//        Set<Flight> flights = new LinkedHashSet<>();
//        Customer customer = new Customer();
//
//        username = "'" + username + "'";
//        password = "'" + password + "'";
//
//        String query = "select customer_info.customer_id, customer_first_name, customer_last_name, customer_email, cust_username, cust_password\n" +
//                "from customer_info, customer_login\n" +
//                "where customer_info.customer_id = customer_login.customer_id\n" +
//                "and cust_username = " + username + " and cust_password = " + password;
//
//        Integer id = null;
//        String fname = "";
//        String lname = "";
//        Login login = new Login();
//
//        try {
//            Statement statement = this.connection.createStatement();
//            ResultSet rs = statement.executeQuery(query);
//            int rowcount = 0;
//            if (rs.last()) {
//                rowcount = rs.getRow();
//                rs.beforeFirst();
//            }
//            if (rowcount == 0) {
//                throw new java.lang.IllegalArgumentException("Customer Not Found");
//            }
//            while (rs.next()) {
//                id = Integer.parseInt(rs.getString("customer_info.customer_id"));
//                fname = rs.getString("customer_first_name");
//                lname = rs.getString("customer_last_name");
//
//                login = new Login(rs.getString("cust_username"), rs.getString("cust_password"));
//                reservations = getAllReservationsByCustomerId(id);
//            }
//
//            String query2 = "select flight_info.flight_info_id, airline_flight_info.airline_flight_id,airline_info.airline_id,\n" +
//                    "source_name, destination_name, flight_status_info,flight_source_date, \n" +
//                    "flight_dest_date,flight_max_capacity,flight_current_capacity,fare,airline_name,\n" +
//                    "airline_flight_name, flight_fly_time, flight_land_time, res_status, reservation_by, reservation_date\n" +
//                    "from flight_info, airline_info, airline_flight_info, flight_status, customer_info, reservation_status, reservation_info\n" +
//                    "where flight_info.airline_flight_id = airline_flight_info.airline_flight_id and\n" +
//                    "airline_flight_info.airline_id = airline_info.airline_id and\n" +
//                    "flight_status.airline_flight_id = airline_flight_info.airline_flight_id and\n" +
//                    "customer_info.customer_id = " + id + " and\n " +
//                    "customer_info.customer_id = reservation_info.customer_id and\n" +
//                    "reservation_info.reservation_id = reservation_status.reservation_id and\n" +
//                    "reservation_info.reservation_id = flight_info.reservation_id";
//
//            ResultSet rs2 = statement.executeQuery(query2);
//
//            while (rs2.next())
//            {
//                Integer flightID = Integer.parseInt(rs2.getString("flight_info.flight_info_id"));
//                String flightCode = Integer.toString(rs2.getString("airline_flight_name").hashCode());
//                LocalDate sourceDate = LocalDate.parse(rs2.getString("flight_source_date"));
//                Source source = new Source(rs2.getString("source_name"), sourceDate, rs2.getString("flight_fly_time"));
//                LocalDate destinationDate = LocalDate.parse(rs2.getString("flight_dest_date"));
//                Destination destination = new Destination(rs2.getString("destination_name"), destinationDate, rs2.getString("flight_land_time"));
//                Integer capacity = Integer.parseInt(rs2.getString("flight_max_capacity"));
//                Integer availableSeat = capacity - Integer.parseInt(rs2.getString("flight_current_capacity"));
//                Float fare = Float.parseFloat(rs2.getString("fare"));
//                String status = rs2.getString("flight_status_info");
//                Airline airLine = new Airline(Integer.parseInt(rs2.getString("airline_flight_info.airline_flight_id")), rs2.getString("airline_name"));
//                Airplane airplane = new Airplane(Integer.parseInt(rs2.getString("airline_info.airline_id")), rs2.getString("airline_flight_name"));
//                Flight flight = new Flight(flightID, flightCode, source, destination, availableSeat, status, airLine, airplane, fare);
//                flights.add(flight);
//            }
//            customer = new Customer(id, fname, lname, login, reservations, flights);
//
//        }
//        catch (SQLException e)
//        {
//            System.out.println(e.getMessage());
//        }
//
//        return customer;
//    }
//
//    @Override
//    public Admin getGlobalAdminByLogin(String username, String password)
//    {
//        username = "'" + username + "'";
//        password = "'" + password + "'";
//
//        String query = "select airline_admin_fname, airline_admin_lname, admin_username, admin_password\n" +
//                "from airline_admin, airline_admin_login\n" +
//                "where airline_admin.airline_admin_id = airline_admin_login.airline_admin_id and\n" +
//                "admin_username = " + username + " and admin_password = " + password;
//        Admin admin = new Admin();
//        try
//        {
//            Statement statement = this.connection.createStatement();
//            ResultSet rs = statement.executeQuery(query);
//
//            int rowcount = 0;
//            if (rs.last()) {
//                rowcount = rs.getRow();
//                rs.beforeFirst();
//            }
//            if (rowcount == 0) {
//                throw new java.lang.IllegalArgumentException("Admin Not Found");
//            }
//
//            while (rs.next()) {
//                admin = new Admin(rs.getString("airline_admin_fname"), rs.getString("admin_username"), new Login(rs.getString("admin_username"), rs.getString("admin_password")));
//            }
//
//        } catch (SQLException e) {
//
//        }
//
//        return admin;
//    }
//
//    @Override
//    public Admin getAirlineAdminByLogin(String airline, String username, String password)
//    {
//        username = "'" + username + "'";
//        password = "'" + password + "'";
//        airline = "'" + airline + "'";
//
//        String query = "select airline_admin_fname, airline_admin_lname, admin_username, admin_password, airline_name\n" +
//                "from airline_admin, airline_admin_login, airline_info\n" +
//                "where airline_admin.airline_admin_id = airline_admin_login.airline_admin_id and\n" +
//                "admin_username = " + username + " and admin_password = " + password + " and \n" +
//                "airline_info.airline_id = airline_admin.airline_id\n" +
//                "and airline_name = " + airline;
//
//        Admin admin = new Admin();
//        try
//        {
//            Statement statement = this.connection.createStatement();
//            ResultSet rs = statement.executeQuery(query);
//
//            int rowcount = 0;
//            if (rs.last()) {
//                rowcount = rs.getRow();
//                rs.beforeFirst();
//            }
//            if (rowcount == 0) {
//                throw new java.lang.IllegalArgumentException("Admin Not Found");
//            }
//
//            while (rs.next()) {
//                admin = new Admin(rs.getString("airline_admin_fname"), rs.getString("airline_admin_lname"), new Login(rs.getString("admin_username"), rs.getString("admin_password")));
//            }
//
//        }
//        catch (SQLException e) {}
//
//        return admin;
//    }
//
//    @Override
//    public Set<Reservation> getGlobalReservationsMadeUsingSearchEngine() {
//        Set<Reservation> reservations = new LinkedHashSet<>();
//
//        String query = "select cust_username, cust_password, customer_info.customer_id," +
//                "customer_info.customer_first_name,customer_info.customer_last_name, " +
//                "reservation_info.reservation_id, flight_info_id, airline_flight_info.airline_flight_id,airline_info.airline_id,\n" +
//                "source_name, destination_name, flight_status_info,flight_source_date,\n" +
//                "flight_dest_date,flight_max_capacity,flight_current_capacity,fare,airline_name,\n" +
//                "airline_flight_name, flight_fly_time, flight_land_time, res_status, reservation_by, reservation_date\n" +
//                "from customer_login, flight_info, airline_info, airline_flight_info, flight_status, customer_info, reservation_status, reservation_info\n" +
//                "where flight_info.airline_flight_id = airline_flight_info.airline_flight_id and\n" +
//                "airline_flight_info.airline_id = airline_info.airline_id and\n" +
//                "flight_status.airline_flight_id = airline_flight_info.airline_flight_id and\n" +
//                "reservation_info.reservation_id = reservation_status.reservation_id and\n" +
//                "reservation_info.reservation_id = flight_info.reservation_id and\n" +
//                "reservation_by = 0 and\n" +
//                "customer_info.customer_id = reservation_info.customer_id and\n" +
//                "customer_info.customer_id = customer_login.customer_id";
//        try {
//            Statement statement = this.connection.createStatement();
//            ResultSet rs = statement.executeQuery(query);
//            int rowcount = 0;
//            if (rs.last()) {
//                rowcount = rs.getRow();
//                rs.beforeFirst();
//            }
//            if (rowcount == 0) {
//                throw new java.lang.IllegalArgumentException("Id Not Found");
//            }
//            while (rs.next()) {
//                /*Integer customerID = Integer.parseInt(rs.getString("customer_info.customer_id"));
//                String customerFname = rs.getString("customer_info.customer_first_name");
//                String customerlname = rs.getString("customer_info.customer_last_name");
//                String custusername = rs.getString("cust_username");
//                String custpassword = rs.getString("cust_password");*/
//                Integer reservationID = Integer.parseInt(rs.getString("reservation_info.reservation_id"));
//                Integer flightID = Integer.parseInt(rs.getString("flight_info.flight_info_id"));
//                String flightCode = Integer.toString(rs.getString("airline_flight_name").hashCode());
//                LocalDate sourceDate = LocalDate.parse(rs.getString("flight_source_date"));
//                Source source = new Source(rs.getString("source_name"), sourceDate, rs.getString("flight_fly_time"));
//                LocalDate destinationDate = LocalDate.parse(rs.getString("flight_dest_date"));
//                Destination destination = new Destination(rs.getString("destination_name"), destinationDate, rs.getString("flight_land_time"));
//                Integer capacity = Integer.parseInt(rs.getString("flight_max_capacity"));
//                Integer availableSeat = capacity - Integer.parseInt(rs.getString("flight_current_capacity"));
//                Float fare = Float.parseFloat(rs.getString("fare"));
//                String status = rs.getString("flight_status_info");
//                Airline airLine = new Airline(Integer.parseInt(rs.getString("airline_flight_info.airline_flight_id")), rs.getString("airline_name"));
//                Airplane airplane = new Airplane(Integer.parseInt(rs.getString("airline_info.airline_id")), rs.getString("airline_flight_name"));
//                Flight flight = new Flight(flightID, flightCode, source, destination, availableSeat, status, airLine, airplane, fare);
//                //Set<Reservation> custReservation = getAllReservationsByCustomerId(customerID);
//
//                Reservation reservation = new Reservation(reservationID, null, flight, LocalDate.parse(rs.getString("reservation_date")), rs.getString("res_status"), Integer.parseInt(rs.getString("reservation_by")));
//                reservations.add(reservation);
//            }
//        } catch (SQLException e) {
//
//        }
//
//        return reservations;
//    }
//
//    @Override
//    public Set<Airplane> getAllAirPlaneByAirLine(String airlineName)
//    {
//        Set<Airplane> airplanes = new LinkedHashSet<>();
//        airlineName = "'" + airlineName + "'";
//        String query = "select airline_flight_id, airline_flight_name\n" +
//                "from airline_flight_info, airline_info\n" +
//                "where airline_info.airline_id = airline_flight_info.airline_id\n" +
//                "and airline_name = " + airlineName;
//        try {
//            Statement statement = this.connection.createStatement();
//            ResultSet rs = statement.executeQuery(query);
//            int rowcount = 0;
//            if (rs.last()) {
//                rowcount = rs.getRow();
//                rs.beforeFirst();
//            }
//            if (rowcount == 0) {
//                throw new java.lang.IllegalArgumentException("Airline Name has No Airplane");
//            }
//            while (rs.next()) {
//                Airplane airplane = new Airplane(Integer.parseInt(rs.getString("airline_flight_id")), rs.getString("airline_flight_name"));
//                airplanes.add(airplane);
//                //System.out.println(rs.getString("airline_flight_id") + " " + rs.getString("airline_flight_name"));
//            }
//
//        } catch (SQLException e) {
//
//        }
//        return airplanes;
//    }
//
//    @Override
//    public Set<Airport> getAllAirports() {
//        Set<Airport> airports = new LinkedHashSet<>();
//        String query = "select airport_id, airport_name\n" +
//                "from airport_info";
//        try {
//            Statement statement = this.connection.createStatement();
//            ResultSet rs = statement.executeQuery(query);
//            int rowcount = 0;
//            if (rs.last()) {
//                rowcount = rs.getRow();
//                rs.beforeFirst();
//            }
//            if (rowcount == 0) {
//                throw new java.lang.IllegalArgumentException("No Airport Found");
//            }
//            while (rs.next()) {
//                Airport airport = new Airport(Integer.parseInt(rs.getString("airport_id")), rs.getString("airport_name"));
//                airports.add(airport);
////                System.out.println(rs.getString("airport_id") + " " + rs.getString("airport_name"));
//            }
//        } catch (SQLException e) {
//
//        }
//
//        return airports;
//    }
//
//    @Override
//    public Set<Flight> getAllFlightsByAirlineForReservation(String airlineName)
//    {
//        airlineName = "'" + airlineName + "'";
//
//        Set<Flight> flights = new LinkedHashSet<>();
//
//        String current = "'" + Configuration.GET_CURRENT_DATE().toString() + "'";
//
//        String query = "select flight_info.flight_info_id, airline_flight_info.airline_flight_id,airline_info.airline_id, \n" +
//                "source_name, destination_name, flight_status_info,flight_source_date, \n" +
//                "flight_dest_date,flight_max_capacity,flight_current_capacity,fare,airline_name, \n" +
//                "airline_flight_name, flight_fly_time, flight_land_time\n" +
//                "from flight_info, airline_info, airline_flight_info, flight_status\n" +
//                "where flight_info.airline_flight_id = airline_flight_info.airline_flight_id and\n" +
//                "airline_flight_info.airline_id = airline_info.airline_id and\n" +
//                "flight_status.airline_flight_id = airline_flight_info.airline_flight_id and\n" +
//                "flight_max_capacity > flight_current_capacity and\n" +
//                "flight_status_info = 'On Time' and\n" +
//                "flight_source_date > " + current + "and airline_name = " + airlineName;
//
//        try
//        {
//            Statement statement = this.connection.createStatement();
//            ResultSet rs = statement.executeQuery(query);
//
//            int rowcount = 0;
//            if (rs.last()) {
//                rowcount = rs.getRow();
//                rs.beforeFirst();
//            }
//            if (rowcount == 0) {
//                throw new java.lang.IllegalArgumentException("No Result Found");
//            }
//
//            while (rs.next()) {
//
//                Integer flightID = Integer.parseInt(rs.getString("flight_info.flight_info_id"));
//                String flightCode = Integer.toString(rs.getString("airline_flight_name").hashCode());
//                LocalDate sourceDate = LocalDate.parse(rs.getString("flight_source_date"));
//                Source source = new Source(rs.getString("source_name"), sourceDate, rs.getString("flight_fly_time"));
//                LocalDate destinationDate = LocalDate.parse(rs.getString("flight_dest_date"));
//                Destination destination = new Destination(rs.getString("destination_name"), destinationDate, rs.getString("flight_land_time"));
//                Integer capacity = Integer.parseInt(rs.getString("flight_max_capacity"));
//                Integer availableSeat = capacity - Integer.parseInt(rs.getString("flight_current_capacity"));
//                Float fare = Float.parseFloat(rs.getString("fare"));
//                String status = rs.getString("flight_status_info");
//                Airline airLine = new Airline(Integer.parseInt(rs.getString("airline_flight_info.airline_flight_id")), rs.getString("airline_name"));
//                Airplane airplane = new Airplane(Integer.parseInt(rs.getString("airline_info.airline_id")), rs.getString("airline_flight_name"));
//
//                Flight flight = new Flight(flightID, flightCode, source, destination, availableSeat, status, airLine, airplane, fare);
//                flights.add(flight);
//            }
//        }
//        catch (SQLException e) {}
//
//        return flights;
//    }
//
//    @Override
//    public boolean insertNewCustomer(String firstName, String lastName, String email, String password) {
//        insert_customer_info(firstName, lastName, email, password);
//        return true;
//    }
//
//    @Override
//    public boolean cancelReservation(Integer customerId, Integer reservationId) {
//
//        String query = "SELECT reservation_status_id \n" +
//                "from customer_info, reservation_info, reservation_status\n" +
//                "where customer_info.customer_id = reservation_info.customer_id and\n" +
//                "reservation_info.reservation_id = reservation_status.reservation_id and\n" +
//                "customer_info.customer_id = " + Integer.toString(customerId) + "\n" +
//                "and reservation_info.reservation_id = " + Integer.toString(reservationId);
//
//        try {
//            Statement statement = this.connection.createStatement();
//            ResultSet rs = statement.executeQuery(query);
//            String res_status_id = new String();
//            while (rs.next()) {
//                res_status_id = rs.getString("reservation_status_id");
//            }
//            String query2 = "Update reservation_status\n" +
//                    "set res_status = 'CANCELLED'\n" +
//                    "where reservation_status_id = " + Integer.parseInt(res_status_id);
//            PreparedStatement ps = connection.prepareStatement(query2,
//                    Statement.RETURN_GENERATED_KEYS);
//            ps.execute();
//        } catch (SQLException e) {
//            throw new java.lang.IllegalArgumentException(e.getMessage());
//        }
//
//        return true;
//    }
//
//    @Override
//    public boolean cancelFlight(Integer flightId)
//    {
//        String status = "'" + "CANCELLED" + "'";
//        String query = "update flight_status\n" +
//                "set flight_status_info = " + status + " " +
//                "where airline_flight_id = " + flightId;
//
//        String query2 = "select reservation_status.reservation_status_id, res_status\n" +
//                "from reservation_status, reservation_info, flight_info\n" +
//                "where reservation_status.reservation_id = reservation_info.reservation_id and\n" +
//                "flight_info.reservation_id = reservation_info.reservation_id and\n" +
//                "flight_info.airline_flight_id = " + flightId;
//        try
//        {
//            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
//            ps.execute();
//            Statement statement = this.connection.createStatement();
//            ResultSet rs = statement.executeQuery(query2);
//            String query3 = new String();
//            while (rs.next())
//            {
//                Integer res_status_id = Integer.parseInt(rs.getString("reservation_status.reservation_status_id"));
//                query3 = "update reservation_status\n" +
//                        "set res_status = " + status + " " +
//                        "where reservation_status_id = " + res_status_id;
//                ps = connection.prepareStatement(query3, Statement.RETURN_GENERATED_KEYS);
//                ps.execute();
//            }
//        }
//        catch (SQLException e) { throw new java.lang.IllegalArgumentException(e.getMessage()); }
//
//        return true;
//    }
//
//    @Override
//    public boolean makeReservation(Integer flightIdPk, String username, String password)
//    {
//        String user_name = "'" + username + "'";
//        String pass_word = "'" + password + "'";
//
//        String current_date = "'" + LocalDate.now().toString() + "'";
//
//        Integer reservation_id = -1;
//        Integer customer_id = -1;
//        String customer_id_select = "select customer_info.customer_id\n" +
//                "from customer_info, customer_login\n" +
//                "where cust_username = " + user_name + " and\n" +
//                "cust_password = " + pass_word + " and\n" +
//                "customer_info.customer_id = customer_login.customer_id";
//        String flight_select = "select flight_source_date,flight_dest_date,flight_fly_time,flight_land_time,source_name,destination_name\n" +
//                "from flight_info, airline_flight_info\n" +
//                "where airline_flight_info.airline_flight_id = flight_info.airline_flight_id and\n" +
//                "airline_flight_info.airline_flight_id = " + flightIdPk.toString() + " and\n" +
//                "flight_source_date > " + current_date;
//        try {
//            Statement statement = this.connection.createStatement();
//            ResultSet rs = statement.executeQuery(customer_id_select);
//
//            while (rs.next()) {
//                customer_id = Integer.parseInt(rs.getString("customer_info.customer_id"));
//            }
//
//            reservation_id = insert_reservation_info(customer_id, "1", LocalDate.now());
//            rs = statement.executeQuery(flight_select);
//
//            LocalDate sourceDate = LocalDate.now();
//            LocalDate destDate = LocalDate.now();
//
//            String src = "";
//            String dest = "";
//            String fly_time = "";
//            String land_time = "";
//
//            while (rs.next()) {
//                sourceDate = LocalDate.parse(rs.getString("flight_source_date"));
//                destDate = LocalDate.parse(rs.getString("flight_dest_date"));
//                fly_time = rs.getString("flight_fly_time");
//                land_time = rs.getString("flight_land_time");
//                src = rs.getString("source_name");
//                dest = rs.getString("destination_name");
//            }
//            // change flight Id
//            insert_flight_info(reservation_id, 3, sourceDate, destDate, fly_time, land_time, src, dest);
//
//        } catch (SQLException | IllegalArgumentException e) {
//            throw new java.lang.IllegalArgumentException(e.getMessage());
//        }
//        return true;
//    }
//
//    @Override
//    public boolean makeReservation(Integer flightIdPk, Integer customerId)
//    {
//        String current_date = "'" + LocalDate.now().toString() + "'";
//        Integer reservation_id = -1;
//        String flight_select = "select flight_source_date,flight_dest_date,flight_fly_time,flight_land_time,source_name,destination_name\n" +
//                "from flight_info, airline_flight_info\n" +
//                "where airline_flight_info.airline_flight_id = flight_info.airline_flight_id and\n" +
//                "airline_flight_info.airline_flight_id = " + flightIdPk.toString() + " and\n" +
//                "flight_source_date > " + current_date;
//        try {
//            Statement statement = this.connection.createStatement();
//            reservation_id = insert_reservation_info(customerId, "1", LocalDate.now());
//            ResultSet rs = statement.executeQuery(flight_select);
//            LocalDate sourceDate = LocalDate.now();
//            LocalDate destDate = LocalDate.now();
//            String src = "";
//            String dest = "";
//            String fly_time = "";
//            String land_time = "";
//
//            while (rs.next()) {
//                sourceDate = LocalDate.parse(rs.getString("flight_source_date"));
//                destDate = LocalDate.parse(rs.getString("flight_dest_date"));
//                fly_time = rs.getString("flight_fly_time");
//                land_time = rs.getString("flight_land_time");
//                src = rs.getString("source_name");
//                dest = rs.getString("destination_name");
//            }
//            insert_flight_info(reservation_id, flightIdPk, sourceDate, destDate, fly_time, land_time, src, dest);
//
//        } catch (SQLException | IllegalArgumentException e) {
//            throw new java.lang.IllegalArgumentException(e.getMessage());
//        }
//        return true;
//    }
//
//    @Override
//    public boolean makeReservationBySearchEngine(Integer flightIdPk, String username, String password)
//    {
//        String user_name = "'" + username + "'";
//        String pass_word = "'" + password + "'";
//        String current_date = "'" + LocalDate.now().toString() + "'";
//        Integer reservation_id = -1;
//        Integer customer_id = -1;
//
//        String customer_id_select = "select customer_info.customer_id\n" +
//                "from customer_info, customer_login\n" +
//                "where cust_username = " + user_name + " and\n" +
//                "cust_password = " + pass_word + " and\n" +
//                "customer_info.customer_id = customer_login.customer_id";
//
//
//        // change flightId
//        String flight_select = "select flight_source_date, flight_dest_date, flight_fly_time, flight_land_time, source_name, destination_name\n" +
//                "from flight_info, airline_flight_info\n" +
//                "where airline_flight_info.airline_flight_id = flight_info.airline_flight_id and\n" +
//                "airline_flight_info.airline_flight_id = " + 3 + " and\n" +
//                "flight_source_date > " + current_date;
//        try
//        {
//            Statement statement = this.connection.createStatement();
//            ResultSet rs = statement.executeQuery(customer_id_select);
//
//            while (rs.next())
//            {
//                customer_id = Integer.parseInt(rs.getString("customer_info.customer_id"));
//            }
//
//            rs.close();
//
//            reservation_id = insert_reservation_info(customer_id, "0", LocalDate.now());
//
//            LocalDate sourceDate = LocalDate.now();
//            LocalDate destDate = LocalDate.now();
//
//            String src = "";
//            String dest = "";
//            String fly_time = "";
//            String land_time = "";
//
//            ResultSet rs2 = statement.executeQuery(flight_select);
//
//            while (rs2.next())
//            {
//                sourceDate = LocalDate.parse(rs2.getString("flight_source_date"));
//                destDate = LocalDate.parse(rs2.getString("flight_dest_date"));
//                fly_time = rs2.getString("flight_fly_time");
//                land_time = rs2.getString("flight_land_time");
//                src = rs2.getString("source_name");
//                dest = rs2.getString("destination_name");
//            }
//
//            insert_flight_info(reservation_id, 3, sourceDate, destDate, fly_time, land_time, src, dest);
//
//        } catch (SQLException | IllegalArgumentException e) {
//            throw new java.lang.IllegalArgumentException(e.getMessage());
//        }
//        return true;
//    }
//
//    @Override
//    public boolean makeReservationBySearchEngine(Integer flightIdPk, Integer customerId)
//    {
//        String current_date = "'" + LocalDate.now().toString() + "'";
//
//        Integer reservation_id = -1;
//
//        String flight_select = "select flight_source_date,flight_dest_date,flight_fly_time,flight_land_time,source_name,destination_name\n" +
//                "from flight_info, airline_flight_info\n" +
//                "where airline_flight_info.airline_flight_id = flight_info.airline_flight_id and\n" +
//                "airline_flight_info.airline_flight_id = " + flightIdPk.toString() + " and\n" +
//                "flight_source_date > " + current_date;
//
//        try
//        {
//            Statement statement = this.connection.createStatement();
//            reservation_id = insert_reservation_info(customerId, "1", LocalDate.now());
//
//            ResultSet rs = statement.executeQuery(flight_select);
//            LocalDate sourceDate = LocalDate.now();
//            LocalDate destDate = LocalDate.now();
//            String src = "";
//            String dest = "";
//            String fly_time = "";
//            String land_time = "";
//
//            while (rs.next()) {
//                sourceDate = LocalDate.parse(rs.getString("flight_source_date"));
//                destDate = LocalDate.parse(rs.getString("flight_dest_date"));
//                fly_time = rs.getString("flight_fly_time");
//                land_time = rs.getString("flight_land_time");
//                src = rs.getString("source_name");
//                dest = rs.getString("destination_name");
//            }
//            insert_flight_info(reservation_id, flightIdPk, sourceDate, destDate, fly_time, land_time, src, dest);
//
//        } catch (SQLException | IllegalArgumentException e) {
//            throw new java.lang.IllegalArgumentException(e.getMessage());
//        }
//        return true;
//    }
//
//    @Override
//    public boolean insertFlightByAirline(Flight flight) {
//        return true;
//    }
//
//    @Override
//    public Set<Flight> getAllFlightsByAirport(String airportName) {
//        airportName = "'" + airportName + "'";
//        Set<Flight> flights = new LinkedHashSet<>();
//
//        String query = "select flight_info.flight_info_id, airline_flight_info.airline_flight_id,airline_info.airline_id, \n" +
//                "source_name, destination_name, flight_status_info,flight_source_date,\n" +
//                "flight_dest_date,flight_max_capacity,flight_current_capacity,fare,airline_name, \n" +
//                "airline_flight_name, flight_fly_time, flight_land_time\n" +
//                "from flight_info, airline_info, airline_flight_info, flight_status\n" +
//                "where flight_info.airline_flight_id = airline_flight_info.airline_flight_id and\n" +
//                "airline_flight_info.airline_id = airline_info.airline_id and\n" +
//                "flight_status.airline_flight_id = airline_flight_info.airline_flight_id and\n" +
//                "(source_name = " + airportName + " or destination_name = " + airportName + ")";
//
//        try {
//            Statement statement = this.connection.createStatement();
//            ResultSet rs = statement.executeQuery(query);
//
//            int rowcount = 0;
//            if (rs.last()) {
//                rowcount = rs.getRow();
//                rs.beforeFirst();
//            }
//            if (rowcount == 0) {
//                throw new java.lang.IllegalArgumentException("No Result Found");
//            }
//
//            while (rs.next()) {
//
//                Integer flightID = Integer.parseInt(rs.getString("flight_info.flight_info_id"));
//                String flightCode = Integer.toString(rs.getString("airline_flight_name").hashCode());
//                LocalDate sourceDate = LocalDate.parse(rs.getString("flight_source_date"));
//                Source source = new Source(rs.getString("source_name"), sourceDate, rs.getString("flight_fly_time"));
//                LocalDate destinationDate = LocalDate.parse(rs.getString("flight_dest_date"));
//                Destination destination = new Destination(rs.getString("destination_name"), destinationDate, rs.getString("flight_land_time"));
//                Integer capacity = Integer.parseInt(rs.getString("flight_max_capacity"));
//                Integer availableSeat = capacity - Integer.parseInt(rs.getString("flight_current_capacity"));
//                Float fare = Float.parseFloat(rs.getString("fare"));
//                String status = rs.getString("flight_status_info");
//                Airline airLine = new Airline(Integer.parseInt(rs.getString("airline_flight_info.airline_flight_id")), rs.getString("airline_name"));
//                Airplane airplane = new Airplane(Integer.parseInt(rs.getString("airline_info.airline_id")), rs.getString("airline_flight_name"));
//
//                Flight flight = new Flight(flightID, flightCode, source, destination, availableSeat, status, airLine, airplane, fare);
//                flights.add(flight);
//            }
//        } catch (SQLException e) {
//
//        }
//
//        return flights;
//    }
//
//    public Set<Reservation> getAllReservationsMadeUsingSearchEngineAndAirlineGui(String airlineName)
//    {
//        Set<Reservation> reservations = new LinkedHashSet<>();
//        String aln = "'" + airlineName + "'";
//        String query = "select cust_username, cust_password, customer_info.customer_id," +
//                "customer_info.customer_first_name,customer_info.customer_last_name, " +
//                "reservation_info.reservation_id, flight_info_id, airline_flight_info.airline_flight_id,airline_info.airline_id,\n" +
//                "source_name, destination_name, flight_status_info,flight_source_date,\n" +
//                "flight_dest_date,flight_max_capacity,flight_current_capacity,fare,airline_name,\n" +
//                "airline_flight_name, flight_fly_time, flight_land_time, res_status, reservation_by, reservation_date\n" +
//                "from customer_login, flight_info, airline_info, airline_flight_info, flight_status, customer_info, reservation_status, reservation_info\n" +
//                "where flight_info.airline_flight_id = airline_flight_info.airline_flight_id and\n" +
//                "airline_flight_info.airline_id = airline_info.airline_id and\n" +
//                "flight_status.airline_flight_id = airline_flight_info.airline_flight_id and\n" +
//                "reservation_info.reservation_id = reservation_status.reservation_id and\n" +
//                "reservation_info.reservation_id = flight_info.reservation_id and\n" +
//                "reservation_by = 0 and\n" +
//                "customer_info.customer_id = reservation_info.customer_id and\n" +
//                "customer_info.customer_id = customer_login.customer_id and airline_name = " + aln;
//        try
//        {
//            Statement statement = this.connection.createStatement();
//            ResultSet rs = statement.executeQuery(query);
//            int rowcount = 0;
//            if (rs.last()) {
//                rowcount = rs.getRow();
//                rs.beforeFirst();
//            }
//            if (rowcount == 0) {
//                throw new java.lang.IllegalArgumentException("Id Not Found");
//            }
//            while (rs.next()) {
//                Integer customerID = Integer.parseInt(rs.getString("customer_info.customer_id"));
//                String customerFname = rs.getString("customer_info.customer_first_name");
//                String customerlname = rs.getString("customer_info.customer_last_name");
//                String custusername = rs.getString("cust_username");
//                String custpassword = rs.getString("cust_password");
//                Integer reservationID = Integer.parseInt(rs.getString("reservation_info.reservation_id"));
//                Integer flightID = Integer.parseInt(rs.getString("flight_info.flight_info_id"));
//                String flightCode = Integer.toString(rs.getString("airline_flight_name").hashCode());
//                LocalDate sourceDate = LocalDate.parse(rs.getString("flight_source_date"));
//                Source source = new Source(rs.getString("source_name"), sourceDate, rs.getString("flight_fly_time"));
//                LocalDate destinationDate = LocalDate.parse(rs.getString("flight_dest_date"));
//                Destination destination = new Destination(rs.getString("destination_name"), destinationDate, rs.getString("flight_land_time"));
//                Integer capacity = Integer.parseInt(rs.getString("flight_max_capacity"));
//                Integer availableSeat = capacity - Integer.parseInt(rs.getString("flight_current_capacity"));
//                Float fare = Float.parseFloat(rs.getString("fare"));
//                String status = rs.getString("flight_status_info");
//                Airline airLine = new Airline(Integer.parseInt(rs.getString("airline_flight_info.airline_flight_id")), rs.getString("airline_name"));
//                Airplane airplane = new Airplane(Integer.parseInt(rs.getString("airline_info.airline_id")), rs.getString("airline_flight_name"));
//                Flight flight = new Flight(flightID, flightCode, source, destination, availableSeat, status, airLine, airplane, fare);
//                //Set<Reservation> custReservation = getAllReservationsByCustomerId(customerID);
//
//                Reservation reservation = new Reservation(reservationID, null, flight, LocalDate.parse(rs.getString("reservation_date")), rs.getString("res_status"), Integer.parseInt(rs.getString("reservation_by")));
//                reservations.add(reservation);
//            }
//        } catch (SQLException e) {
//
//        }
//
//        return reservations;
//    }
//
//    private void insertAirlineAdmin(String firstname, String lastname, Integer airline_ID) {
//
//        String firstname_1 = "'" + firstname + "'";
//        String lastname_1 = "'" + lastname + "'";
//
//        String query = "insert into airline_admin(airline_id, airline_admin_fname, airline_admin_lname) values ( " + airline_ID + "," + firstname_1 + "," + lastname_1 + ")";
//        try {
//            PreparedStatement ps = connection.prepareStatement(query,
//                    Statement.RETURN_GENERATED_KEYS);
//
//            ps.execute();
//
//            ResultSet rs = ps.getGeneratedKeys();
//            int generatedKey = 0;
//            if (rs.next()) {
//                generatedKey = rs.getInt(1);
//            }
//
//            insertAdminLogin(generatedKey, firstname + lastname, "12345");
//
//        } catch (SQLException e) {
//            throw new java.lang.IllegalArgumentException("AirLine Admin Exist");
//        }
//
//    }
//
//    private void insertAdminLogin(Integer admin_ID, String username, String password) {
//
//        String username_ = "'" + username + "'";
//        String password_ = "'" + password + "'";
//
//        String query = "insert into airline_admin_login(airline_admin_id, admin_username, admin_password) values ( " + admin_ID + "," + username_ + "," + password_ + ")";
//        try {
//            PreparedStatement ps = connection.prepareStatement(query,
//                    Statement.RETURN_GENERATED_KEYS);
//
//            ps.execute();
//
//            ResultSet rs = ps.getGeneratedKeys();
//            int generatedKey = 0;
//            if (rs.next()) {
//                generatedKey = rs.getInt(1);
//            }
//
//        } catch (SQLException e) {
//            throw new java.lang.IllegalArgumentException("Admin ID Error");
//        }
//    }
//
//    private void insertAirline_flight_info(String flight_name, Integer airline_id, float fare, int flight_max_capacity, int flight_current_capacity) {
//
//        String flight_name_ = "'" + flight_name + "'";
//
//        String query = "insert into airline_flight_info(airline_flight_name, airline_id, fare, flight_max_capacity, flight_current_capacity)\n" +
//                "\tvalue ( " + flight_name_ + "," + airline_id + "," + fare + "," + flight_max_capacity + "," + flight_current_capacity + ")";
//        try {
//            PreparedStatement ps = connection.prepareStatement(query,
//                    Statement.RETURN_GENERATED_KEYS);
//
//            ps.execute();
//
//            ResultSet rs = ps.getGeneratedKeys();
//            int generatedKey = 0;
//            if (rs.next()) {
//                generatedKey = rs.getInt(1);
//            }
//
//        } catch (SQLException e) {
//            throw new java.lang.IllegalArgumentException("");
//        }
//    }
//
//    private void insert_airline_info(String airline_name) {
//
//        String airline_name_ = "'" + airline_name + "'";
//
//        String query = "insert into airline_info(airline_name) values ( " + airline_name_ + ")";
//        try {
//            PreparedStatement ps = connection.prepareStatement(query,
//                    Statement.RETURN_GENERATED_KEYS);
//
//            ps.execute();
//
//            ResultSet rs = ps.getGeneratedKeys();
//            int generatedKey = 0;
//            if (rs.next()) {
//                generatedKey = rs.getInt(1);
//            }
//
//        } catch (SQLException e) {
//            throw new java.lang.IllegalArgumentException("");
//        }
//
//    }
//
//    private void insert_airport_info(String airport_name) {
//
//        String airport_name_ = "'" + airport_name + "'";
//
//        String query = "insert into airport_info(airport_name) values ( " + airport_name_ + ")";
//        try {
//            PreparedStatement ps = connection.prepareStatement(query,
//                    Statement.RETURN_GENERATED_KEYS);
//
//            ps.execute();
//
//            ResultSet rs = ps.getGeneratedKeys();
//            int generatedKey = 0;
//            if (rs.next()) {
//                generatedKey = rs.getInt(1);
//            }
//
//        } catch (SQLException e) {
//            throw new java.lang.IllegalArgumentException("");
//        }
//
//    }
//
//    private void insert_arrival_info(Integer airport_id, Integer airline_flight_id, Integer flight_status_id) {
//
//        String query = "insert into arrival_info(airport_id, airline_flight_id, flight_status_id) values(" + airport_id + "," + airline_flight_id + "," + flight_status_id + ")";
//        try {
//            PreparedStatement ps = connection.prepareStatement(query,
//                    Statement.RETURN_GENERATED_KEYS);
//            ps.execute();
//        } catch (SQLException e) {
//            throw new java.lang.IllegalArgumentException("Err");
//        }
//
//    }
//
//    private void insert_available_flight_id(Integer airline_flight_id, LocalDate localDate) {
//    }
//
//    private void insert_customer_info(String firstname, String lastname, String email, String password) {
//        String firstname_1 = "'" + firstname + "'";
//        String lastname_1 = "'" + lastname + "'";
//        String email_1 = "'" + email + "'";
//        String query = "insert into customer_info(customer_first_name, customer_last_name, customer_email) values ( " + firstname_1 + "," + lastname_1 + "," + email_1 + ")";
//        try {
//            PreparedStatement ps = connection.prepareStatement(query,
//                    Statement.RETURN_GENERATED_KEYS);
//
//            ps.execute();
//
//            ResultSet rs = ps.getGeneratedKeys();
//            int generatedKey = 0;
//            if (rs.next()) {
//                generatedKey = rs.getInt(1);
//            }
//            insert_customer_login(email, password, generatedKey);
//        } catch (SQLException e) {
//            throw new java.lang.IllegalArgumentException(" name is already Taken");
//        }
//
//    }
//
//    private void insert_customer_login(String username, String password, Integer customer_id) {
//
//        username = "'" + username + "'";
//        password = "'" + password + "'";
//        String query = "insert into customer_login(cust_username, cust_password, customer_id) values( " + username + "," + password + "," + customer_id + ")";
//        try {
//            PreparedStatement ps = connection.prepareStatement(query,
//                    Statement.RETURN_GENERATED_KEYS);
//            ps.execute();
//        } catch (SQLException e) {
//            throw new java.lang.IllegalArgumentException("Err");
//        }
//    }
//
//    private void insert_departures_info(Integer airport_id, Integer airline_flight_id, Integer flight_status_id) {
//        String query = "insert into departures_info(airport_id, airline_flight_id, " +
//                "flight_status_id) values( " + airport_id + "," + airline_flight_id + "," + flight_status_id
//                + ")";
//        try {
//            PreparedStatement ps = connection.prepareStatement(query,
//                    Statement.RETURN_GENERATED_KEYS);
//            ps.execute();
//        } catch (SQLException e) {
//            throw new java.lang.IllegalArgumentException("Err");
//        }
//    }
//
//    private void insert_destination_info(Integer airport_id) {
//        String query = "insert into destination_info(airport_id) values (2);" + airport_id + ")";
//        try {
//            PreparedStatement ps = connection.prepareStatement(query,
//                    Statement.RETURN_GENERATED_KEYS);
//            ps.execute();
//        } catch (SQLException e) {
//            throw new java.lang.IllegalArgumentException("Err");
//        }
//    }
//
//    private void insert_source_info(Integer airport_id) {
//        String query = "insert into source_info(airport_id) values (2);" + airport_id + ")";
//        try {
//            PreparedStatement ps = connection.prepareStatement(query,
//                    Statement.RETURN_GENERATED_KEYS);
//            ps.execute();
//        } catch (SQLException e) {
//            throw new java.lang.IllegalArgumentException("Err");
//        }
//    }
//
//    private void insert_flight_info(Integer reservation_id, Integer airline_flight_id, LocalDate sourceDate, LocalDate destination_date, String fly_time, String land_time, String source_name, String destination_name)
//    {
//        String source = "'" + source_name + "'";
//        String dest = "'" + destination_name + "'";
//        String flyTime = "'" + fly_time + "'";
//        String landTime = "'" + land_time + "'";
//        String sDate = "'" + sourceDate + "'";
//        String dDate = "'" + destination_date + "'";
//
//        String query = "insert into flight_info ( reservation_id, airline_flight_id, flight_source_date, flight_dest_date, flight_fly_time, flight_land_time, source_name, destination_name ) \n" +
//                "\t values ( '" + reservation_id + "' , '" + airline_flight_id + "' , " + sDate + " , " + dDate + " , " + flyTime + " , " + landTime + " , " + source + " , " + dest + " ) ; ";
//        try
//        {
//            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
//            ps.execute();
//        }
//        catch (SQLException e)
//        {
//            System.out.println(e.getMessage());
//            throw new java.lang.IllegalArgumentException("Insert Flight ");
//        }
//
//    }
//
//    private void insert_flight_status_info(Integer airline_flight_id, String flight_status) {
//
//        String query = "insert into flight_status(airline_flight_id,flight_status_info) values ( " + airline_flight_id + "," + flight_status + ")";
//        try {
//            PreparedStatement ps = connection.prepareStatement(query,
//                    Statement.RETURN_GENERATED_KEYS);
//            ps.execute();
//        } catch (SQLException e) {
//            throw new java.lang.IllegalArgumentException("Err");
//        }
//
//    }
//
//    private int insert_reservation_info(Integer customer_id, String reservation_by, LocalDate localDate)
//            throws IllegalArgumentException
//    {
//        String date = "'" + localDate.toString() + "'";
//        String rvb = "'" + reservation_by + "'";
//        String query = "insert into reservation_info(customer_id, reservation_by, reservation_date) values( "
//                + customer_id + "," + rvb + "," + date + ")";
//        int reservation_key = -1;
//        try {
//            PreparedStatement ps = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
//            ps.execute();
//            ResultSet rs = ps.getGeneratedKeys();
//            int generatedKey = 0;
//            if (rs.next()) {
//                generatedKey = rs.getInt(1);
//            }
//            reservation_key = generatedKey;
//            insert_reservation_status(generatedKey, "ACTIVE");
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//            throw new java.lang.IllegalArgumentException("Reservation Info");
//        }
//
//        return reservation_key;
//    }
//
//    private void insert_reservation_status(Integer reservation_id, String res_status) throws IllegalArgumentException {
//        String res_status_ = "'" + res_status + "'";
//        String query = "INSERT INTO reservation_status(reservation_id, res_status) VALUES ( " + reservation_id + "," + res_status_ + ")";
//        try {
//            PreparedStatement ps = connection.prepareStatement(query,
//                    Statement.RETURN_GENERATED_KEYS);
//            ps.execute();
//            ResultSet rs = ps.getGeneratedKeys();
//            int generatedKey = 0;
//            if (rs.next()) {
//                generatedKey = rs.getInt(1);
//            }
//        } catch (SQLException e) {
//            throw new IllegalArgumentException("Reservation Status");
//        }
//    }
//}
