package com.hrs.exceptions;

public class InvalidUserName extends Exception
{
    private String message;
    
    public InvalidUserName(String message)
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
