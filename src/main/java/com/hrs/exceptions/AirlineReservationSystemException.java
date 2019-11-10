package com.hrs.exceptions;

public class AirlineReservationSystemException extends Exception
{
    private String message;
    
    public AirlineReservationSystemException(String message)
    {
        super(message);
        this.message = message;
    }
    
    @Override
    public String getMessage()
    {
        return this.message;
    }
    
    @Override
    public String toString()
    {
        return "AirlineReservationSystemException{" + "message='" + this.message + '\'' + '}';
    }
}
