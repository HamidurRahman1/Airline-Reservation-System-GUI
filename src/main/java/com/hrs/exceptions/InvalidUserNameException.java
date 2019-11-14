package com.hrs.exceptions;

public class InvalidUserNameException extends Exception
{
    private String message;
    
    public InvalidUserNameException(String message)
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
        return "InvalidUserNameException{" + "message='" + message + '\'' + '}';
    }
}
