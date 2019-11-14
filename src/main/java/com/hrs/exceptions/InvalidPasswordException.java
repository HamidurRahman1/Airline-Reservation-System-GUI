package com.hrs.exceptions;

public class InvalidPasswordException extends Exception
{
    private String message;
    
    public InvalidPasswordException(String message)
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
        return "InvalidPasswordException{" + "message='" + message + '\'' + '}';
    }
}
