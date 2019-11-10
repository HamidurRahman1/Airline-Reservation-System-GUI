package com.hrs.dal.test;

import com.hrs.exceptions.InvalidUserName;
import com.hrs.test.Tester;
import com.hrs.view.models.Admin;
import com.hrs.view.models.Customer;
import com.hrs.view.models.Reservation;

import java.time.LocalDate;
import java.util.List;


public class APIservice implements ServiceModule {

    @Override
    public void getAllFlightsByCustomerId(Integer customerId) {

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
