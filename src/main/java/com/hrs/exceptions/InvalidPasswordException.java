package com.hrs.exceptions;

public class InvalidPasswordException extends AirlineReservationSystemException
{
    public InvalidPasswordException(String message)
    {
        super(message);
    }
}
