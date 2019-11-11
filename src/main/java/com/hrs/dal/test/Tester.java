package com.hrs.dal.test;

import com.hrs.dal.Gateway;

import java.sql.*;

public class Tester {

    public static void main(String args[]) throws SQLException {

        APIservice as = new APIservice();
        as.getAllFlightsByCustomerId(1);
        System.out.println("ALL FLIGHTS: ");
        as.getAllFlights();

    }

}
