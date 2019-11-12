package com.hrs.exceptions;

public class InvalidEmailException extends AirlineReservationSystemException
{
    public InvalidEmailException(String message)
    {
        super(message);
    }
}
