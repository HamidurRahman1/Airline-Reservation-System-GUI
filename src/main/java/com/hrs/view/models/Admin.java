package com.hrs.view.models;

import java.util.Objects;

public class Admin extends Person
{
    private Login login;
    
    public Admin()
    {
        super();
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
    
    public Admin(Login login)
    {
        super();
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
    
    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;
        if(! (o instanceof Admin)) return false;
        Admin admin = (Admin) o;
        return Objects.equals(getLogin(), admin.getLogin());
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(getLogin());
    }
    
    @Override
    public String toString()
    {
        return "Admin{" + "login=" + login + " " + super.toString() +  '}';
    }
}
