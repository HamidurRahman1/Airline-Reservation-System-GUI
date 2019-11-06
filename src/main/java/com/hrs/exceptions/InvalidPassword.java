package com.hrs.exceptions;

public class InvalidPassword extends Exception
{
    private String message;
    
    public InvalidPassword(String message)
    {
        super(message);
        this.message = message;
    }
    
    @Override
    public String getMessage()
    {
        return message;
    }
}
