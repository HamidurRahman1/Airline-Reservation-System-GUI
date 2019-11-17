package com.hrs.exceptions;

public class InvalidLoginException extends Exception
{
    private String message;
    
    public InvalidLoginException(String message)
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
        return "InvalidLoginException{" + "message='" + message + '\'' + '}';
    }
}
