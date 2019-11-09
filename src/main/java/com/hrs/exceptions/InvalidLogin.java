package com.hrs.exceptions;

public class InvalidLogin extends Exception
{
    private String message;
    
    public InvalidLogin(String message)
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
