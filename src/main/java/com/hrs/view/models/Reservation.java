package com.hrs.view.models;

import java.io.Serializable;

/**
 *  A container to represent a complex relationship of Customers-Airports-Airlines into one.
 */
public class Reservation implements Serializable
{
    private Customer customer;
    private Airport source;
    private Airport destination;
    private Flight flight;
}
