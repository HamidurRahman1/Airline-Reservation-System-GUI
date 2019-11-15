package com.hrs.exceptions;

public class InvalidEmailException extends Exception
{
    private String message;
    
    public InvalidEmailException(String message)
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
        return "InvalidEmailException{" + "message='" + message + '\'' + '}';
    }
}
