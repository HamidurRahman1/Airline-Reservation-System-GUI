package com.hrs.test;

import com.hrs.dao.dbServices.DB2;
import com.hrs.resources.FieldValue;
import com.hrs.view.models.Flight;

import java.sql.SQLException;
import java.util.Set;

public class DBTester
{
    private static DB2 db = null;
    
    static
    {
        try
        {
            db = new DB2();
        }
        catch(ClassNotFoundException ex)
        {
            System.out.println(ex.getMessage());
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
    public static void main(String[] args)
    {
    
    }
    
    private static void testAllFlightsByAirlineForRSVP()
    {
        try
        {
            System.out.println(db.getAllFlightsByAirlineForReservation(FieldValue.AR_AMERICAN));
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
    private static void testAllFlightsForRSVP()
    {
        try
        {
            Set <Flight> flights = db.getAllFlightsForReservation(" ");
            System.out.println(flights.size());
            System.out.println(flights);
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
    private static void testAirports()
    {
        System.out.println(db.getAllAirports());
    }
    
    private static void testAirplanes()
    {
        try
        {
            System.out.println(db.getAllAirPlaneByAirLine(FieldValue.AR_AMERICAN));
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
    
}
