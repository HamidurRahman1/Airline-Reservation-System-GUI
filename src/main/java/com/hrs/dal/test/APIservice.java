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
            String sql = "select customer_first_name, reservation_date, airport_name, airport_name\n" +
                    "from customer_info,airport_info,flight_info,reservation_info,source_info,destination_info\n" +
                    "where customer_info.customer_id = reservation_info.customer_id and \n" +
                    "reservation_info.reservation_id = flight_info.reservation_id and\n" +
                    "flight_info.source_id = source_info.source_id and\n" +
                    "flight_info.destination_id = destination_info.destination_id and\n" +
                    "source_info.airport_id = airport_info.airport_id and\n" +
                    "destination_info.airport_id = airport_info.airport_id";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {

                System.out.println(rs.getString("customer_first_name") + " " + rs.getString("reservation_date")
                        + " " + rs.getString("airport_name") + " " + rs.getString("airport_name"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void getAllFlights() {

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
