package com.hrs.view.models;

public class Session
{
    private static Customer customer = null;
    private static Admin admin = null;
    
    public Session() {}
    
    public boolean isCustomerInSession()
    {
        return customer != null;
    }
    
    public void addCustomerToSession(Customer customer)
    {
        Session.customer = customer;
    }
    
    public void deleteCustomerFromSession()
    {
        customer = null;
    }
    
    public boolean isAdminInSession()
    {
        return admin != null;
    }
    
    public void addAdminToSession(Admin admin)
    {
        Session.admin = admin;
    }
    
    public void deleteAdminFromSession()
    {
        admin = null;
    }
    
    public Customer getCustomer()
    {
        return customer;
    }
    
    public Admin getAdmin()
    {
        return admin;
    }
}
