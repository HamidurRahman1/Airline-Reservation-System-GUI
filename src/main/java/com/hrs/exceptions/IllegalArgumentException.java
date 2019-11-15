package com.hrs.exceptions;

public class IllegalArgumentException extends Exception
{
    private String message;
    
    public IllegalArgumentException(String message)
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
        return "IllegalArgumentException{" + "message='" + message + '\'' + '}';
    }
}
