package com.hrs.view.models;

public class Admin extends Person
{
    private Login login;
    
    public Admin()
    {
        super();
    }
    
    public Admin(Login login)
    {
        super();
        this.login = login;
    }
    
    public Admin(String firstName, String lastName)
    {
        super(firstName, lastName);
    }
    
    public Admin(String firstName, String lastName, Login login)
    {
        super(firstName, lastName);
        this.login = login;
    }
    
    public Login getLogin()
    {
        return login;
    }
    
    public void setLogin(Login login)
    {
        this.login = login;
    }
}
