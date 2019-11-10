package com.hrs.view.models;

public class Session
{
    private static Customer customer = null;
    private static Admin admin = null;
    
    public Session() {}
    
    public boolean isCustomerInSession()
    {
        return true;
    }
    
    public void addCustomerToSession(Customer customer) {}
    
    public void deleteCustomerFromSession() {}
    
    public boolean isAdminInSession()
    {
        return true;
    }
    
    public void addAdminToSession(Admin admin) {}
    
    public void deleteAdminFromSession() {}
}
