package com.hrs.exceptions;

public class InvalidUserNameException extends AirlineReservationSystemException
{
    public InvalidUserNameException(String message)
    {
        super(message);
    }
}
