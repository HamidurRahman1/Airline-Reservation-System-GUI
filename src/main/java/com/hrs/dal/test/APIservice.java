package com.hrs.dal.test;

import com.hrs.dal.Gateway;
import com.hrs.exceptions.InvalidUserName;
import com.hrs.test.Tester;
import com.hrs.view.models.Admin;
import com.hrs.view.models.Customer;
import com.hrs.view.models.Reservation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;


public class APIservice implements ServiceModule {

    public APIservice() {
    }

    @Override
    public void getAllFlightsByCustomerId(Integer customerId) {

        try {

            Connection connection = Gateway.getDBConnection();
            Statement statement = connection.createStatement();
            String sql = "select customer_first_name, (flight_date), source_, destination_\n" +
                    "from customer_info,flight_info,reservation_info\n" +
                    "where customer_info.customer_id = " + Integer.toString(customerId) + " and\n" +
                    "customer_info.customer_id = reservation_info.customer_id and \n" +
                    "reservation_info.reservation_id = flight_info.reservation_id\n";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {

                System.out.println(rs.getString("customer_first_name") + " " + rs.getString("flight_date")
                        + " " + rs.getString("source_") + " " + rs.getString("destination_"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void getAllFlights() {
        try {

            Connection connection = Gateway.getDBConnection();
            Statement statement = connection.createStatement();
            String sql = "select airline_name, airline_flight_name, flight_date, source_, destination_\n" +
                    "from airline_info, airline_flight_info, flight_info\n" +
                    "where airline_info.airline_id = airline_flight_info.airline_id and\n" +
                    "airline_flight_info.airline_flight_id = flight_info.airline_flight_id";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {

                System.out.println(rs.getString("airline_name") + " " + rs.getString("airline_flight_name")
                        + " " + rs.getString("flight_date") + " " + rs.getString("source_") + " " + rs.getString("destination_"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getAllFlightsByAirline(String airlineName) {

    }

    @Override
    public Customer getCustomerByLogin(String username, String password) {
        Customer customer = new Customer(101, "First", "Last");
        customer.setFlights(Tester.testFlights());
        return customer;
    }

    @Override
    public void validateAirlineAdminLogin(String username, String password) {

    }

    @Override
    public boolean insertNewCustomer(String firstName, String lastName, String email, String password) {
        return true;
    }

    @Override
    public void cancelReservation(Integer customerId, LocalDate localDate, Integer flightId, Integer airlineId) {

    }

    @Override
    public void cancelReservation2testFunc(Integer customerId) {

    }

    @Override
    public void getAdminByAirline(String airlineName) {

    }

    @Override
    public boolean makeReservation(Integer flightIdPk, String username) throws InvalidUserName {
        return true;
    }

    @Override
    public boolean makeReservation(Integer flightIdPk, Integer customerId) {
        return true;
    }

    @Override
    public void insertGlobalReservation(Integer flightIdPk) {

    }

    @Override
    public Admin getGlobalAdminByLogin(String username, String password) {
        return new Admin("Hamidur", "Rahman");
    }

    @Override
    public List<Reservation> getGlobalReservations() {
        return null;
    }

}
